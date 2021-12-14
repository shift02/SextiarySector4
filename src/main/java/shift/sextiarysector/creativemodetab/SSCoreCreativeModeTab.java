package shift.sextiarysector.creativemodetab;

import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.ItemStack;
import org.jetbrains.annotations.NotNull;
import shift.sextiarysector.SSItems;
import shift.sextiarysector.SextiarySector4;

public class SSCoreCreativeModeTab extends CreativeModeTab {

    private String langId;

    public SSCoreCreativeModeTab() {
        super(SextiarySector4.MOD_ID + ".core");
        langId = SextiarySector4.MOD_ID + ".core";
    }

    @Override
    @NotNull
    public ItemStack makeIcon() {
        return new ItemStack(SSItems.LEAF.get());
    }

    public String getLangId() {
        return this.langId;
    }
}
