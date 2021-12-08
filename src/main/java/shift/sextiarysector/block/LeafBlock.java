package shift.sextiarysector.block;

import net.minecraft.Util;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.network.chat.TranslatableComponent;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.npc.Villager;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.level.*;
import net.minecraft.world.level.block.*;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.*;
import net.minecraft.world.level.material.FluidState;
import net.minecraft.world.level.material.PushReaction;
import net.minecraft.world.level.pathfinder.PathComputationType;
import net.minecraft.world.phys.AABB;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.VoxelShape;
import org.jetbrains.annotations.NotNull;

import javax.annotation.Nullable;
import java.util.List;
import java.util.Map;


/**
 * 葉っぱベッド
 */
public class LeafBlock extends Block {

    //ベッドの向き
    public static final DirectionProperty FACING = BlockStateProperties.HORIZONTAL_FACING;

    //頭か足
    public static final EnumProperty<BedPart> PART = BlockStateProperties.BED_PART;

    //誰かが寝ているかどうか
    public static final BooleanProperty OCCUPIED = BlockStateProperties.OCCUPIED;

    //それぞれの接続
    public static final BooleanProperty NORTH = BlockStateProperties.NORTH;
    public static final BooleanProperty EAST = BlockStateProperties.EAST;
    public static final BooleanProperty SOUTH = BlockStateProperties.SOUTH;
    public static final BooleanProperty WEST = BlockStateProperties.WEST;

    protected static final Map<Direction, BooleanProperty> PROPERTY_BY_DIRECTION = PipeBlock.PROPERTY_BY_DIRECTION.entrySet().stream().filter((directionBooleanPropertyEntry) -> {
        return directionBooleanPropertyEntry.getKey().getAxis().isHorizontal();
    }).collect(Util.toMap());

    protected static final VoxelShape BASE = Block.box(0.0D, 0.0D, 0.0D, 16.0D, 4.0D, 16.0D);

    public LeafBlock(Properties properties) {
        super(properties);
        //デフォルトの状態
        this.registerDefaultState(this.stateDefinition.any()
                .setValue(PART, BedPart.FOOT)//足から設置
                .setValue(OCCUPIED, Boolean.FALSE)//デフォは誰も寝ていない
        );
    }

    /**
     * ベッドかどうか
     */
    public boolean isBed(BlockState state, BlockGetter world, BlockPos pos, @Nullable Entity player) {
        return true;
    }

    public boolean skipRendering(@NotNull BlockState blockState, BlockState blockState1, @NotNull Direction direction) {
        return blockState1.is(this) || super.skipRendering(blockState, blockState1, direction);
    }

    /**
     * ベッドを右クリックした時の処理
     */
    @NotNull
    public InteractionResult use(
            @NotNull BlockState blockState,
            Level level,
            @NotNull BlockPos blockPos,
            @NotNull Player player,
            @NotNull InteractionHand interactionHand,
            @NotNull BlockHitResult blockHitResult) {

        //clientの場合はそのまま返す
        if (level.isClientSide) {
            return InteractionResult.CONSUME;
        }


        if (blockState.getValue(PART) != BedPart.HEAD) {
            blockPos = blockPos.relative(blockState.getValue(FACING));
            blockState = level.getBlockState(blockPos);
            if (!blockState.is(this)) {
                return InteractionResult.CONSUME;
            }
        }

        if (!canSetSpawn(level)) {
            level.removeBlock(blockPos, false);
            BlockPos blockpos = blockPos.relative(blockState.getValue(FACING).getOpposite());
            if (level.getBlockState(blockpos).is(this)) {
                level.removeBlock(blockpos, false);
            }

            //爆発
            level.explode((Entity) null, DamageSource.badRespawnPointExplosion(), (ExplosionDamageCalculator) null, (double) blockPos.getX() + 0.5D, (double) blockPos.getY() + 0.5D, (double) blockPos.getZ() + 0.5D, 5.0F, true, Explosion.BlockInteraction.DESTROY);
            return InteractionResult.SUCCESS;
        } else if (blockState.getValue(OCCUPIED)) {

            //村人
            if (!this.kickVillagerOutOfBed(level, blockPos)) {
                player.displayClientMessage(new TranslatableComponent("block.minecraft.bed.occupied"), true);
            }

            return InteractionResult.SUCCESS;
        } else {
            //寝る
            player.startSleepInBed(blockPos).ifLeft((bedSleepingProblem) -> {
                if (bedSleepingProblem != null) {
                    player.displayClientMessage(bedSleepingProblem.getMessage(), true);
                }

            });
            return InteractionResult.SUCCESS;
        }

    }

    /**
     * 寝れるところかどうか
     */
    public static boolean canSetSpawn(Level level) {
        return level.dimensionType().bedWorks();
    }

    /**
     * 村人がベッドで寝ていたら起こしてどかす
     */
    private boolean kickVillagerOutOfBed(Level level, BlockPos blockPos) {
        List<Villager> list = level.getEntitiesOfClass(Villager.class, new AABB(blockPos), LivingEntity::isSleeping);
        if (list.isEmpty()) {
            return false;
        } else {
            list.get(0).stopSleeping();
            return true;
        }
    }

    /**
     * ブロックへの落下
     */
    public void fallOn(@NotNull Level level, @NotNull BlockState blockState, @NotNull BlockPos pos, @NotNull Entity entity, float damage) {
        super.fallOn(level, blockState, pos, entity, damage * 0.5F);
    }

    /**
     * 接続できるかどうか
     */
    public boolean connectsTo(BlockState blockState) {
        Block block = blockState.getBlock();
        return block instanceof LeafBlock;
    }

    @NotNull
    public BlockState updateShape(
            @NotNull BlockState blockState,
            Direction direction,
            @NotNull BlockState addBlockState,
            @NotNull LevelAccessor levelAccessor,
            @NotNull BlockPos blockPos,
            @NotNull BlockPos addBlockPos) {

        //接続関係
        if (direction.getAxis().getPlane() == Direction.Plane.HORIZONTAL) {
            blockState = blockState.setValue(PROPERTY_BY_DIRECTION.get(direction), this.connectsTo(addBlockState));
        }

        //頭か足か
        if (direction == getNeighbourDirection(blockState.getValue(PART), blockState.getValue(FACING))) {

            if (addBlockState.is(this) && addBlockState.getValue(PART) != blockState.getValue(PART)) {
                return blockState.setValue(OCCUPIED, addBlockState.getValue(OCCUPIED));
            } else {
                return Blocks.AIR.defaultBlockState();
            }

        } else {
            return super.updateShape(blockState, direction, addBlockState, levelAccessor, blockPos, addBlockPos);
        }

    }

    private static Direction getNeighbourDirection(BedPart bedPart, Direction direction) {
        return bedPart == BedPart.FOOT ? direction : direction.getOpposite();
    }

    /**
     * 破壊時
     */
    public void playerWillDestroy(Level level, @NotNull BlockPos blockPos, @NotNull BlockState blockState, @NotNull Player player) {
        if (!level.isClientSide && player.isCreative()) {
            BedPart bedpart = blockState.getValue(PART);
            if (bedpart == BedPart.FOOT) {
                BlockPos blockpos = blockPos.relative(getNeighbourDirection(bedpart, blockState.getValue(FACING)));
                BlockState blockstate = level.getBlockState(blockpos);
                if (blockstate.is(this) && blockstate.getValue(PART) == BedPart.HEAD) {
                    level.setBlock(blockpos, Blocks.AIR.defaultBlockState(), 0b10_0011);
                    level.levelEvent(player, 2001, blockpos, Block.getId(blockstate));
                }
            }
        }

        super.playerWillDestroy(level, blockPos, blockState, player);
    }

    /**
     * 設置時の処理
     */
    @Nullable
    public BlockState getStateForPlacement(BlockPlaceContext blockPlaceContext) {

        BlockPos blockpos = blockPlaceContext.getClickedPos();
        Level level = blockPlaceContext.getLevel();

        //ベッドの向き関係
        Direction direction = blockPlaceContext.getHorizontalDirection();
        BlockPos blockPosHead = blockpos.relative(direction);
        //木の接続
        FluidState fluidstate = blockPlaceContext.getLevel().getFluidState(blockPlaceContext.getClickedPos());
        BlockPos blockpos1 = blockpos.north();
        BlockPos blockpos2 = blockpos.east();
        BlockPos blockpos3 = blockpos.south();
        BlockPos blockpos4 = blockpos.west();
        BlockState blockstate = level.getBlockState(blockpos1);
        BlockState blockstate1 = level.getBlockState(blockpos2);
        BlockState blockstate2 = level.getBlockState(blockpos3);
        BlockState blockstate3 = level.getBlockState(blockpos4);

        if (level.getBlockState(blockPosHead).canBeReplaced(blockPlaceContext) && level.getWorldBorder().isWithinBounds(blockPosHead)) {
            return this.defaultBlockState()
                    .setValue(FACING, direction)
                    .setValue(NORTH, this.connectsTo(blockstate))
                    .setValue(EAST, this.connectsTo(blockstate1))
                    .setValue(SOUTH, this.connectsTo(blockstate2))
                    .setValue(WEST, this.connectsTo(blockstate3));
        } else {
            //ワールドからはみ出ている時は設置しない
            return null;
        }

    }

    @NotNull
    public VoxelShape getShape(@NotNull BlockState blockState, @NotNull BlockGetter blockGetter, @NotNull BlockPos blockPos, @NotNull CollisionContext collisionContext) {
        return BASE;
    }

    public static Direction getConnectedDirection(BlockState state) {
        Direction direction = state.getValue(FACING);
        return state.getValue(PART) == BedPart.HEAD ? direction.getOpposite() : direction;
    }

    @NotNull
    public PushReaction getPistonPushReaction(@NotNull BlockState blockState) {
        return PushReaction.DESTROY;
    }

    protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> blockStateBuilder) {
        blockStateBuilder.add(FACING, PART, OCCUPIED, NORTH, EAST, WEST, SOUTH);
    }

    /**
     * 設置後の処理
     */
    public void setPlacedBy(@NotNull Level level, @NotNull BlockPos blockPos, @NotNull BlockState blockState, @Nullable LivingEntity livingEntity, @NotNull ItemStack itemStack) {
        super.setPlacedBy(level, blockPos, blockState, livingEntity, itemStack);
        if (!level.isClientSide) {
            BlockPos blockpos = blockPos.relative(blockState.getValue(FACING));
            level.setBlock(blockpos, blockState.setValue(PART, BedPart.HEAD), 3);
            level.blockUpdated(blockPos, Blocks.AIR);
            blockState.updateNeighbourShapes(level, blockPos, 3);
        }

    }

    /**
     * エンティティが歩ける場所として認識するか
     */
    public boolean isPathfindable(@NotNull BlockState blockState, @NotNull BlockGetter blockGetter, @NotNull BlockPos blockPos, @NotNull PathComputationType pathComputationType) {
        return false;
    }

    //向き関係
    @NotNull
    public BlockState rotate(BlockState blockState, Rotation rotation) {
        return blockState.setValue(FACING, rotation.rotate(blockState.getValue(FACING)));
    }

    @NotNull
    public BlockState mirror(BlockState blockState, Mirror mirror) {
        return blockState.rotate(mirror.getRotation(blockState.getValue(FACING)));
    }

    public static Property<Boolean> getConnectedProperty(final Direction direction) {
        return PROPERTY_BY_DIRECTION.get(direction);
    }

}
