package shift.sextiarysector.data.client;

import net.minecraft.data.DataGenerator;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;
import net.minecraftforge.client.model.generators.ItemModelBuilder;
import net.minecraftforge.client.model.generators.ItemModelProvider;
import net.minecraftforge.client.model.generators.ModelFile;
import net.minecraftforge.common.data.ExistingFileHelper;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;
import shift.sextiarysector.SSBlocks;
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
            if (entry.get() instanceof ISimpleTexture simpleTexture) {
                String textureName = simpleTexture.getTextureName();
                simpleItem(itemGenerated, textureName != null ? textureName : entry.getId().getPath());
            }
        }

        simpleItem(itemGenerated, "leaf_bed");

        withExistingParent("leaf_block", modLoc("block/leaf_block"));

        simpleItem(itemGenerated, "spile");

        withExistingParent(name(SSBlocks.SAP_CAULDRON.get()), modLoc("block/sap_cauldron_full"));

    }

    private ItemModelBuilder simpleItem(ModelFile itemGenerated, String name) {
        return getBuilder(name).parent(itemGenerated).texture("layer0", "item/" + name);
    }

    private String name(Block block) {
        return ForgeRegistries.BLOCKS.getKey(block).getPath();
    }
}
