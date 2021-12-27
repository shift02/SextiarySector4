package shift.sextiarysector;

import net.minecraftforge.common.loot.GlobalLootModifierSerializer;
import net.minecraftforge.registries.RegistryObject;
import shift.sextiarysector.loot.SSBaseLootModifier;
import shift.sextiarysector.loot.SSLeafLootModifier;

/**
 * バニラの各種ルート処理に介入するクラス
 */
public class SSLootModifiers {

    //葉っぱ
    public static final RegistryObject<GlobalLootModifierSerializer<SSLeafLootModifier>> LEAF = SextiarySector4.LOOT_MODIFIER.register("drop_leaf", SSLeafLootModifier.Serializer::new);

    //イカの刺身
    public static final RegistryObject<GlobalLootModifierSerializer<SSBaseLootModifier>> SQUID_SASHIMI = SextiarySector4.LOOT_MODIFIER.register("drop_squid_sashimi", SSBaseLootModifier.Serializer::new);


    static void register() {
    }

}
