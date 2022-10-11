package shift.sextiarysector.block;

import net.minecraft.Util;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.util.RandomSource;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.LevelReader;
import net.minecraft.world.level.block.*;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.minecraft.world.level.block.state.properties.BooleanProperty;
import net.minecraft.world.level.block.state.properties.DirectionProperty;
import net.minecraft.world.level.material.Material;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.Shapes;
import net.minecraft.world.phys.shapes.VoxelShape;
import org.jetbrains.annotations.NotNull;
import shift.sextiarysector.SSBlocks;

import javax.annotation.Nullable;
import java.util.Map;

/**
 * 木から樹液を採取するブロック
 */
public class SpileBlock extends Block {

    /**
     * 向き
     */
    public static final DirectionProperty FACING = BlockStateProperties.HORIZONTAL_FACING;

    //当たり判定
    protected static final VoxelShape SPILE_NORTH_AABB = Shapes.box(0.375D, 0.25, 0.6875, 0.625, 0.5, 1.0D);
    protected static final VoxelShape SPILE_SOUTH_AABB = Shapes.box(0.375D, 0.25, 0.0D, 0.625, 0.5, 0.3125D);
    protected static final VoxelShape SPILE_WEST_AABB = Shapes.box(0.6875, 0.25, 0.375D, 1.0D, 0.5, 0.625D);
    protected static final VoxelShape SPILE_EAST_AABB = Shapes.box(0.000D, 0.25, 0.375, 0.3125, 0.5, 0.625D);

    public static final Map<Direction, BooleanProperty> PROPERTY_BY_DIRECTION = PipeBlock.PROPERTY_BY_DIRECTION.entrySet().stream().filter((directionBooleanPropertyEntry) -> {
        return directionBooleanPropertyEntry.getKey().getAxis().isHorizontal();
    }).collect(Util.toMap());

    public SpileBlock(Properties properties) {
        super(properties);

        this.registerDefaultState(this.stateDefinition.any().setValue(FACING, Direction.NORTH));

    }

    @Override
    protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> blockStateBuilder) {
        blockStateBuilder.add(FACING);
    }

    @Override
    public void randomTick(BlockState pState, ServerLevel pLevel, BlockPos pPos, RandomSource pRandom) {
        if (!pLevel.isClientSide()) {
            super.randomTick(pState, pLevel, pPos, pRandom);

            if (this.isSap(pState, pLevel, pPos, pRandom)) {
                this.addSap(pState, pLevel, pPos, pRandom);
            }
        }
    }

    public boolean isSap(BlockState state, ServerLevel levelReader, BlockPos blockPos, RandomSource pRandom) {

        Direction direction = state.getValue(FACING);
        BlockPos blockpos = blockPos.relative(direction.getOpposite());
        BlockState blockstate = levelReader.getBlockState(blockpos);

        if (blockstate.getMaterial() != Material.WOOD) {
            return false;
        }
        return true;

    }

    public void addSap(BlockState pState, ServerLevel pLevel, BlockPos pos, RandomSource pRandom) {

        BlockPos cP = pos.below();
        Block downBlock = pLevel.getBlockState(cP).getBlock();

        SapCauldronBlock cPB = SSBlocks.SAP_CAULDRON.get();

        if (cPB == null) return;

        if (!(downBlock == Blocks.CAULDRON || downBlock instanceof SapCauldronBlock)) return;

        //空の場合
        if (downBlock == Blocks.CAULDRON) {

            pLevel.setBlockAndUpdate(cP, cPB.defaultBlockState());
            //cPB.addWaterLevel(pLevel, cP, pLevel.getBlockState(cP), 1);

        }

        //すでに液体が入っている場合
        if (cPB.equals(downBlock)) {
            cPB.addWaterLevel(pLevel, cP, pLevel.getBlockState(cP), 1);
        }

    }


    //方角関係の処理

    /**
     * 当たり判定
     */
    @NotNull
    public VoxelShape getShape(@NotNull BlockState blockState, @NotNull BlockGetter blockGetter, @NotNull BlockPos blockPos, @NotNull CollisionContext collisionContext) {
        switch (blockState.getValue(FACING)) {
            case EAST:
                return SPILE_EAST_AABB;
            case WEST:
                return SPILE_WEST_AABB;
            case SOUTH:
                return SPILE_SOUTH_AABB;
            case NORTH:
                return SPILE_NORTH_AABB;
            default:
                break;
        }

        return SPILE_EAST_AABB;
    }

    /**
     * ワールドの存在できるか
     */
    public boolean canSurvive(BlockState state, LevelReader levelReader, BlockPos blockPos) {
        Direction direction = state.getValue(FACING);
        BlockPos blockpos = blockPos.relative(direction.getOpposite());
        BlockState blockstate = levelReader.getBlockState(blockpos);
        return blockstate.isFaceSturdy(levelReader, blockpos, direction);
    }

    /**
     * ワールドに設置できるか
     */
    @Nullable
    public BlockState getStateForPlacement(BlockPlaceContext placeContext) {

        BlockState blockstate = this.defaultBlockState();
        LevelReader levelreader = placeContext.getLevel();
        BlockPos blockpos = placeContext.getClickedPos();
        Direction[] directions = placeContext.getNearestLookingDirections();

        for (Direction direction : directions) {
            if (direction.getAxis().isHorizontal()) {
                Direction direction1 = direction.getOpposite();
                blockstate = blockstate.setValue(FACING, direction1);
                if (blockstate.canSurvive(levelreader, blockpos)) {
                    return blockstate;
                }
            }
        }

        return null;
    }

    /**
     * 特定の方角でブロックの更新があった場合の処理
     */
    public BlockState updateShape(BlockState state, Direction direction, BlockState blockState, LevelAccessor levelAccessor, BlockPos blockPos, BlockPos facingPos) {
        return direction.getOpposite() == state.getValue(FACING) && !state.canSurvive(levelAccessor, blockPos) ? Blocks.AIR.defaultBlockState() : state;
    }

    public BlockState rotate(BlockState state, Rotation rotation) {
        return state.setValue(FACING, rotation.rotate(state.getValue(FACING)));
    }

    public BlockState mirror(BlockState blockState, Mirror mirror) {
        return blockState.rotate(mirror.getRotation(blockState.getValue(FACING)));
    }

}
