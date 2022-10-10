package shift.sextiarysector.data.client.block;

import com.google.common.collect.Lists;
import net.minecraft.data.DataGenerator;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.LayeredCauldronBlock;
import net.minecraftforge.client.model.generators.BlockModelBuilder;
import net.minecraftforge.client.model.generators.BlockStateProvider;
import net.minecraftforge.client.model.generators.MultiPartBlockStateBuilder;
import net.minecraftforge.common.data.ExistingFileHelper;
import net.minecraftforge.registries.ForgeRegistries;
import shift.sextiarysector.SSBlocks;
import shift.sextiarysector.SextiarySector4;

import java.util.List;

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


        //樹液入ブロック
        final List<String> multipartSapList = Lists.newArrayList("level1", "level2", "full");
        MultiPartBlockStateBuilder multipartBuilder = getMultipartBuilder(SSBlocks.SAP_CAULDRON.get());

        for (int i = 0; i < multipartSapList.size(); i++) {
            String multipartSap = multipartSapList.get(i);

            final BlockModelBuilder sapCauldronLevel1 = models()
                    .getBuilder(name(SSBlocks.SAP_CAULDRON.get()) + "_" + multipartSap)
                    .parent(models().getExistingFile(mcLoc("block/template_cauldron_" + multipartSap)))
                    .texture("content", "minecraft:block/water_still");

            multipartBuilder.part()
                    .modelFile(sapCauldronLevel1)
                    .uvLock(true)
                    .addModel()
                    .condition(LayeredCauldronBlock.LEVEL, i + 1)
                    .end();
        }

    }

    private String name(Block block) {
        return ForgeRegistries.BLOCKS.getKey(block).getPath();
    }
}
