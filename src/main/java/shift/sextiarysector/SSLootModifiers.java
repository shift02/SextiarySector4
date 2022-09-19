package shift.sextiarysector;

import com.mojang.serialization.Codec;
import net.minecraftforge.registries.RegistryObject;
import shift.sextiarysector.loot.SSBaseLootModifier;
import shift.sextiarysector.loot.SSLeafLootModifier;

/**
 * バニラの各種ルート処理に介入するクラス
 */
public class SSLootModifiers {

    //葉っぱ
    public static final RegistryObject<Codec<SSLeafLootModifier>> LEAF = SextiarySector4.LOOT_MODIFIER.register("drop_leaf", SSLeafLootModifier.CODEC);

    //イカの刺身
    public static final RegistryObject<Codec<SSBaseLootModifier>> SQUID_SASHIMI = SextiarySector4.LOOT_MODIFIER.register("drop_squid_sashimi", SSBaseLootModifier.CODEC);


    static void register() {
    }

}
