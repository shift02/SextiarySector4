package shift.sextiarysector.creativemodetab;

import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.ItemLike;
import org.jetbrains.annotations.NotNull;
import shift.sextiarysector.SextiarySector4;

import java.util.function.Supplier;

public class SSCoreCreativeModeTab extends CreativeModeTab {

    private String langId;

    private final Supplier<ItemLike> itemIcon;

    public SSCoreCreativeModeTab(String key, Supplier<ItemLike> itemIcon) {
        super(SextiarySector4.MOD_ID + "." + key);
        this.langId = SextiarySector4.MOD_ID + "." + key;
        this.itemIcon = itemIcon;
    }

    @Override
    @NotNull
    public ItemStack makeIcon() {
        return new ItemStack(this.itemIcon.get());
    }

    public String getLangId() {
        return this.langId;
    }
}
