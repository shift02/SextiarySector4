package shift.sextiarysector.data.client.block;

import net.minecraft.core.Direction;
import net.minecraft.data.DataGenerator;
import net.minecraft.world.level.block.Block;
import net.minecraftforge.client.model.generators.BlockModelBuilder;
import net.minecraftforge.client.model.generators.BlockStateProvider;
import net.minecraftforge.client.model.generators.MultiPartBlockStateBuilder;
import net.minecraftforge.common.data.ExistingFileHelper;
import shift.sextiarysector.SSBlocks;
import shift.sextiarysector.SextiarySector4;
import shift.sextiarysector.block.LeafBlock;

public class LeafBedBlockStateProvider extends BlockStateProvider {

    public LeafBedBlockStateProvider(DataGenerator gen, ExistingFileHelper exFileHelper) {
        super(gen, SextiarySector4.MOD_ID, exFileHelper);
    }

    @Override
    protected void registerStatesAndModels() {

        final BlockModelBuilder leafBase = createLeaf("base", 2, 0.001f, 14, 16 - 0.001f);
        final BlockModelBuilder leafWest = createLeaf("west", 2, 0.001f, 16 - 0.001f, 16 - 0.001f);
        final BlockModelBuilder leafEast = createLeaf("east", 0.001f, 0.001f, 14, 16 - 0.001f);
        final BlockModelBuilder leafFull = createLeaf("full", 0.001f, 0.001f, 16 - 0.001f, 16 - 0.001f);

        final BlockModelBuilder bottomWood = models()
                .getBuilder(name(SSBlocks.LEAF_BED.get()) + "_bottom_wood")
                .parent(models().getExistingFile(mcLoc("block/block")))
                .texture("particle", "#wood")
                .texture("wood", mcLoc("block/oak_planks"))
                .element()
                .from(0, 0, 0)
                .to(16, 2, 2)
                .allFaces((direction, faceBuilder) -> faceBuilder.texture("#wood"))
                .end();

        final BlockModelBuilder topWood = models()
                .getBuilder(name(SSBlocks.LEAF_BED.get()) + "_top_wood")
                .parent(models().getExistingFile(mcLoc("block/block")))
                .texture("particle", "#wood")
                .texture("wood", mcLoc("block/spruce_planks"))
                .element()
                .from(0, 2, 0)
                .to(16, 4, 2)
                .allFaces((direction, faceBuilder) -> faceBuilder.texture("#wood"))
                .end();


        MultiPartBlockStateBuilder multipartBuilder = getMultipartBuilder(SSBlocks.LEAF_BED.get());

        multipartBuilder.part()
                .modelFile(leafBase)
                .uvLock(true)
                .addModel()
                .condition(LeafBlock.getConnectedProperty(Direction.EAST), false)
                .condition(LeafBlock.getConnectedProperty(Direction.WEST), false)
                .end();

        multipartBuilder.part()
                .modelFile(leafWest)
                .uvLock(true)
                .addModel()
                .condition(LeafBlock.getConnectedProperty(Direction.EAST), true)
                .condition(LeafBlock.getConnectedProperty(Direction.WEST), false)
                .end();

        multipartBuilder.part()
                .modelFile(leafEast)
                .uvLock(true)
                .addModel()
                .condition(LeafBlock.getConnectedProperty(Direction.EAST), false)
                .condition(LeafBlock.getConnectedProperty(Direction.WEST), true)
                .end();

        multipartBuilder.part()
                .modelFile(leafFull)
                .uvLock(true)
                .addModel()
                .condition(LeafBlock.getConnectedProperty(Direction.EAST), true)
                .condition(LeafBlock.getConnectedProperty(Direction.WEST), true)
                .end();


        for (final Direction direction : Direction.Plane.HORIZONTAL) {
            multipartBuilder
                    .part()
                    .modelFile(direction.getAxis() == Direction.Axis.X ? bottomWood : topWood)
                    .uvLock(true)
                    .rotationY(getRotationY(direction))
                    .addModel()
                    .condition(LeafBlock.getConnectedProperty(direction), false);
        }

    }

    private BlockModelBuilder createLeaf(String name, float fromX, float fromZ, float toX, float toZ) {
        return models()
                .getBuilder(name(SSBlocks.LEAF_BED.get()) + "_leaf_" + name)
                .parent(models().getExistingFile(mcLoc("block/block")))
                .texture("particle", "#leaf_1")
                .texture("leaf_1", mcLoc("block/acacia_leaves"))
                .texture("leaf_2", mcLoc("block/spruce_leaves"))
                .texture("leaf_3", mcLoc("block/oak_leaves"))
                .element()
                .from(fromX, 0.01f, fromZ)
                .to(toX, 1, toZ)
                .allFaces((direction, faceBuilder) -> faceBuilder.texture("#leaf_1").tintindex(0))
                .end()
                .element()
                .from(fromX, 1, fromZ)
                .to(toX, 2.001f, toZ)
                .allFaces((direction, faceBuilder) -> faceBuilder.texture("#leaf_2").tintindex(0))
                .end()
                .element()
                .from(fromX, 2.001f, fromZ)
                .to(toX, 3, toZ)
                .allFaces((direction, faceBuilder) -> faceBuilder.texture("#leaf_3").tintindex(0))
                .end();
    }

    private String name(Block block) {
        return block.getRegistryName().getPath();
    }

    private static final int DEFAULT_ANGLE_OFFSET = 180;

    private int getRotationY(final Direction direction) {
        return getRotationY(direction, DEFAULT_ANGLE_OFFSET);
    }

    private int getRotationY(final Direction direction, final int offset) {
        return direction.getAxis().isVertical() ? 0 : (((int) direction.toYRot()) + offset) % 360;
    }

}
