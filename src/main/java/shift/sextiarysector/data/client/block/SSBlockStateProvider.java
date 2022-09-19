package shift.sextiarysector.data.client.block;

import net.minecraft.data.DataGenerator;
import net.minecraft.world.level.block.Block;
import net.minecraftforge.client.model.generators.BlockModelBuilder;
import net.minecraftforge.client.model.generators.BlockStateProvider;
import net.minecraftforge.common.data.ExistingFileHelper;
import net.minecraftforge.registries.ForgeRegistries;
import shift.sextiarysector.SSBlocks;
import shift.sextiarysector.SextiarySector4;

import static shift.sextiarysector.data.SSDataGenerators.RENDERTYPE_CUTOUT;

public class SSBlockStateProvider extends BlockStateProvider {

    public SSBlockStateProvider(DataGenerator gen, ExistingFileHelper exFileHelper) {
        super(gen, SextiarySector4.MOD_ID, exFileHelper);
    }

    @Override
    protected void registerStatesAndModels() {

        final BlockModelBuilder leafBlock = models()
                .getBuilder(name(SSBlocks.LEAF_BLOCK.get()))
                .parent(models().getExistingFile(mcLoc("block/block")))
                .renderType(RENDERTYPE_CUTOUT)
                .texture("particle", "#all")
                .texture("all", mcLoc("block/oak_leaves"))
                .texture("all1", mcLoc("block/jungle_leaves"))
                .element()
                .from(0, 0, 0)
                .to(16, 16, 16)
                .allFaces((direction, faceBuilder) -> faceBuilder.texture("#all").tintindex(0))
                .end()
                .element()
                .from(0.001f, 0.001f, 0.001f)
                .to(16 - 0.001f, 16 - 0.001f, 16 - 0.001f)
                .allFaces((direction, faceBuilder) -> faceBuilder.texture("#all1").tintindex(0))
                .end();
        simpleBlock(SSBlocks.LEAF_BLOCK.get(), leafBlock);


    }

    private String name(Block block) {
        return ForgeRegistries.BLOCKS.getKey(block).getPath();
    }
}
