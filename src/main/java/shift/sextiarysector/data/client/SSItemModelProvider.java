package shift.sextiarysector.data.client;

import net.minecraft.client.renderer.block.model.BlockModel;
import net.minecraft.client.renderer.block.model.ItemTransforms;
import net.minecraft.data.DataGenerator;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;
import net.minecraftforge.client.model.generators.ItemModelBuilder;
import net.minecraftforge.client.model.generators.ItemModelProvider;
import net.minecraftforge.client.model.generators.ModelFile;
import net.minecraftforge.common.data.ExistingFileHelper;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;
import shift.sextiarysector.SSBlocks;
import shift.sextiarysector.SSItems;
import shift.sextiarysector.SextiarySector4;
import shift.sextiarysector.item.ISimpleTexture;

public class SSItemModelProvider extends ItemModelProvider {
    public SSItemModelProvider(DataGenerator generator, ExistingFileHelper existingFileHelper) {
        super(generator, SextiarySector4.MOD_ID, existingFileHelper);
    }

    @Override
    protected void registerModels() {

        ModelFile itemGenerated = getExistingFile(mcLoc("item/generated"));

        //simpleItem(itemGenerated, "leaf");

        for (RegistryObject<Item> entry : SextiarySector4.ITEMS.getEntries()) {
            if (entry.get() instanceof ISimpleTexture simpleTexture && !simpleTexture.hasCustomTexture()) {
                String textureName = simpleTexture.getTextureName();
                simpleItem(itemGenerated, textureName != null ? textureName : entry.getId().getPath());
            }
        }

        bottleItem(itemGenerated, name(SSItems.SAP_BOTTLE.get()));

        shieldItem(itemGenerated, name(SSItems.PLASTIC_SHIELD.get()));

        //Block
        simpleItem(itemGenerated, "leaf_bed");

        withExistingParent("leaf_block", modLoc("block/leaf_block"));

        simpleItem(itemGenerated, "spile");

        withExistingParent(name(SSBlocks.SAP_CAULDRON.get()), modLoc("block/sap_cauldron_full"));

    }

    private ItemModelBuilder simpleItem(ModelFile itemGenerated, String name) {
        return getBuilder(name).parent(itemGenerated).texture("layer0", "item/" + name);
    }

    private ItemModelBuilder bottleItem(ModelFile itemGenerated, String name) {
        return getBuilder(name)
                .parent(itemGenerated)
                .texture("layer0", mcLoc("item/potion"))
                .texture("layer1", mcLoc("item/potion_overlay"));
    }

    private void shieldItem(ModelFile itemGenerated, String name) {
        getBuilder(name + "_blocking")
                .parent(new ModelFile.UncheckedModelFile("builtin/entity"))
                .guiLight(BlockModel.GuiLight.FRONT)
                .transforms()
                .transform(ItemTransforms.TransformType.THIRD_PERSON_RIGHT_HAND).rotation(45, 135, 0).translation(3.51f, 11, -2).scale(1, 1, 1).end()
                .transform(ItemTransforms.TransformType.THIRD_PERSON_LEFT_HAND).rotation(45, 135, 0).translation(13.51f, 3, 5).scale(1, 1, 1).end()
                .transform(ItemTransforms.TransformType.FIRST_PERSON_RIGHT_HAND).rotation(0, 180, -5).translation(-15, 5, -11).scale(1.25f, 1.25f, 1.25f).end()
                .transform(ItemTransforms.TransformType.FIRST_PERSON_LEFT_HAND).rotation(0, 180, -5).translation(5, 5, -11).scale(1.25f, 1.25f, 1.25f).end()
                .transform(ItemTransforms.TransformType.GUI).rotation(15, -25, -5).translation(2, 3, 0).scale(0.65f, 0.65f, 0.65f).end()
                .end();

        getBuilder(name)
                .parent(new ModelFile.UncheckedModelFile("builtin/entity"))
                .guiLight(BlockModel.GuiLight.FRONT)
                .texture("particle", modLoc("entity/plastic_shield"))
                .transforms()
                .transform(ItemTransforms.TransformType.THIRD_PERSON_RIGHT_HAND).rotation(0, 90, 0).translation(10, 6, -4).scale(1, 1, 1).end()
                .transform(ItemTransforms.TransformType.THIRD_PERSON_LEFT_HAND).rotation(0, 90, 0).translation(10, 6, 12).scale(1, 1, 1).end()
                .transform(ItemTransforms.TransformType.FIRST_PERSON_RIGHT_HAND).rotation(0, 180, 5).translation(-10, 2, -10).scale(1.25f, 1.25f, 1.25f).end()
                .transform(ItemTransforms.TransformType.FIRST_PERSON_LEFT_HAND).rotation(0, 180, 5).translation(10, 0, -10).scale(1.25f, 1.25f, 1.25f).end()
                .transform(ItemTransforms.TransformType.GUI).rotation(15, -25, -5).translation(2, 3, 0).scale(0.65f, 0.65f, 0.65f).end()
                .transform(ItemTransforms.TransformType.FIXED).rotation(0, 180, 0).translation(-4.5f, 4.5f, -5).scale(0.55f, 0.55f, 0.55f).end()
                .transform(ItemTransforms.TransformType.GROUND).rotation(0, 0, 0).translation(2, 4, 2).scale(0.25f, 0.25f, 0.25f).end()
                .end()
                .override().predicate(new ResourceLocation("blocking"), 1).model(getExistingFile(modLoc(name + "_blocking")));
    }

    private String name(Item item) {
        return ForgeRegistries.ITEMS.getKey(item).getPath();
    }

    private String name(Block block) {
        return ForgeRegistries.BLOCKS.getKey(block).getPath();
    }
}
