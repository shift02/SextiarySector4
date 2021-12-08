package shift.sextiarysector;

import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.Item;
import net.minecraftforge.common.loot.GlobalLootModifierSerializer;
import net.minecraftforge.registries.RegistryObject;
import shift.sextiarysector.item.SSBaseItem;
import shift.sextiarysector.loot.SSLeafLootModifier;

/**
 * バニラの各種ルート処理に介入するクラス
 */
public class SSLootModifiers {

    public static final RegistryObject<GlobalLootModifierSerializer<SSLeafLootModifier>> LEAF = SextiarySector4.LOOT_MODIFIER.register("drop_leaf", SSLeafLootModifier.Serializer::new);

    static void register() {}

}
