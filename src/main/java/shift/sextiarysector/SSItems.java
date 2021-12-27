package shift.sextiarysector;

import net.minecraft.world.food.FoodProperties;
import net.minecraft.world.item.Item;
import net.minecraftforge.registries.RegistryObject;
import shift.sextiarysector.item.SSBaseItem;

/**
 * SS4のアイテム管理クラス
 */
public class SSItems {

    // ---------------------
    // Core (コア)
    // ---------------------
    public static final RegistryObject<Item> LEAF = SextiarySector4.ITEMS.register("leaf", () -> new SSBaseItem(new Item.Properties().tab(SSTabs.TAB_CORE)).setBurnTime(50));

    // ---------------------
    // Fisheries (水産)
    // ---------------------
    public static final RegistryObject<Item> SQUID_SASHIMI = SextiarySector4.ITEMS.register("squid_sashimi", () -> new SSBaseItem(
            new Item.Properties().food(new FoodProperties.Builder().nutrition(3).saturationMod(0.5F).build()).tab(SSTabs.TAB_FISHERIES)));

    public static final RegistryObject<Item> SQUID_GRILLED = SextiarySector4.ITEMS.register("squid_grilled", () -> new SSBaseItem(
            new Item.Properties().food(new FoodProperties.Builder().nutrition(7).saturationMod(0.8F).build()).tab(SSTabs.TAB_FISHERIES)));


    static void register() {
    }

}
