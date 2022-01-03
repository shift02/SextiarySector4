package shift.sextiarysector.data.client.block;

import net.minecraft.data.DataGenerator;
import net.minecraft.world.level.block.Block;
import net.minecraftforge.client.model.generators.BlockModelBuilder;
import net.minecraftforge.client.model.generators.BlockStateProvider;
import net.minecraftforge.common.data.ExistingFileHelper;
import shift.sextiarysector.SSBlocks;
import shift.sextiarysector.SextiarySector4;

/**
 * 樹液取るやつのモデル
 */
public class SpileBlockStateProvider extends BlockStateProvider {

    public SpileBlockStateProvider(DataGenerator gen, ExistingFileHelper exFileHelper) {
        super(gen, SextiarySector4.MOD_ID, exFileHelper);
    }

    @Override
    protected void registerStatesAndModels() {

        final BlockModelBuilder spile = models()
                .getBuilder(name(SSBlocks.SPILE.get()))
                //.parent(models().getExistingFile(mcLoc("item/handheld")))
                .texture("base", mcLoc("block/anvil"))
                .texture("particle", "#base")
                .element()
                .from(6, 4, 0)
                .to(10, 8, 1)
                .allFaces((direction, faceBuilder) -> faceBuilder.texture("#base"))
                .end()
                .element()
                .from(6, 4, 1)
                .to(7, 8, 5)
                .allFaces((direction, faceBuilder) -> faceBuilder.texture("#base"))
                .end()
                .element()
                .from(9, 4, 1)
                .to(10, 8, 5)
                .allFaces((direction, faceBuilder) -> faceBuilder.texture("#base"))
                .end()
                .element()
                .from(7, 7, 1)
                .to(9, 8, 5)
                .allFaces((direction, faceBuilder) -> faceBuilder.texture("#base"))
                .end()
                .element()
                .from(7, 4, 1)
                .to(9, 5, 5)
                .allFaces((direction, faceBuilder) -> faceBuilder.texture("#base"))
                .end();

        horizontalBlock(SSBlocks.SPILE.get(), spile, 0);

    }

    private String name(Block block) {
        return block.getRegistryName().getPath();
    }

}
