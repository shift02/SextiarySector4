package shift.sextiarysector.data;

import net.minecraft.data.DataGenerator;
import net.minecraftforge.common.data.LanguageProvider;
import shift.sextiarysector.SSBlocks;
import shift.sextiarysector.SSItems;
import shift.sextiarysector.SSTabs;
import shift.sextiarysector.SextiarySector4;
import shift.sextiarysector.creativemodetab.SSCoreCreativeModeTab;

public class SSLanguageProvider extends LanguageProvider {
    public SSLanguageProvider(DataGenerator gen) {
        super(gen, SextiarySector4.MOD_ID, "en_us");
    }

    @Override
    public String getName() {
        return "SextiarySector4 " + super.getName();
    }

    @Override
    protected void addTranslations() {
        addBlocks();
        addItems();
        addCreativeModeTabs();
    }

    private void addBlocks() {
        addBlock(SSBlocks.LEAF_BLOCK, "Leaf Block");
        addBlock(SSBlocks.LEAF_BED, "Leaf Bed");

        addBlock(SSBlocks.SPILE, "Spile");
    }

    private void addItems() {
        addItem(SSItems.LEAF, "Leaf");

        addItem(SSItems.SQUID_SASHIMI, "Squid Sashimi");
        addItem(SSItems.SQUID_GRILLED, "Squid Grilled");
    }

    private void addCreativeModeTabs() {
        add(SSTabs.TAB_CORE, "SextiarySector4 -Core-");
        add(SSTabs.TAB_FORESTRY, "SextiarySector4 -Forestry-");
        add(SSTabs.TAB_FISHERIES, "SextiarySector4 -Fisheries-");
    }

    public void add(SSCoreCreativeModeTab key, String name) {
        add("itemGroup." + key.getLangId(), name);
    }

}
