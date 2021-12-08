package shift.sextiarysector;

import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.Item;
import net.minecraftforge.registries.RegistryObject;
import shift.sextiarysector.item.SSBaseItem;

/**
 * SS4のアイテム管理クラス
 */
public class SSItems {

    public static final RegistryObject<Item> LEAF = SextiarySector4.ITEMS.register("leaf", () -> new SSBaseItem(new Item.Properties().tab(CreativeModeTab.TAB_MATERIALS)).setBurnTime(50));

    static void register() {}

}
