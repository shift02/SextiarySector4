package shift.sextiarysector;

import net.minecraft.world.food.FoodProperties;
import net.minecraft.world.item.Item;
import net.minecraftforge.registries.RegistryObject;
import shift.sextiarysector.item.SSBaseItem;
import shift.sextiarysector.item.SSShieldItem;

/**
 * SS4のアイテム管理クラス
 */
public class SSItems {

    // ---------------------
    // Core (コア)
    // ---------------------
    public static final RegistryObject<Item> LEAF = SextiarySector4.ITEMS.register("leaf", () -> new SSBaseItem(new Item.Properties().tab(SSTabs.TAB_CORE)).setBurnTime(50));

    public static final RegistryObject<Item> PLASTIC_SHIELD = SextiarySector4.ITEMS.register("plastic_shield", () -> new SSShieldItem(
            new Item.Properties().durability(336).tab(SSTabs.TAB_CORE)).enableCustomTexture());

    // ---------------------
    // Forestry (林業)
    // ---------------------
    public static final RegistryObject<Item> SAP_BOTTLE = SextiarySector4.ITEMS.register("sap_bottle", () -> new SSBaseItem(
            new Item.Properties().stacksTo(1).tab(SSTabs.TAB_FORESTRY)).enableCustomTexture());

    public static final RegistryObject<Item> PLASTIC = SextiarySector4.ITEMS.register("plastic", () -> new SSBaseItem(
            new Item.Properties().stacksTo(1).tab(SSTabs.TAB_FORESTRY)));

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
