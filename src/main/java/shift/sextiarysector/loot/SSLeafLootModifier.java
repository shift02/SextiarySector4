package shift.sextiarysector.loot;

import com.google.gson.JsonObject;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.storage.loot.LootContext;
import net.minecraft.world.level.storage.loot.predicates.LootItemBlockStatePropertyCondition;
import net.minecraft.world.level.storage.loot.predicates.LootItemCondition;
import net.minecraftforge.common.loot.GlobalLootModifierSerializer;
import net.minecraftforge.common.loot.LootModifier;
import org.jetbrains.annotations.NotNull;
import shift.sextiarysector.SSItems;

import java.util.Arrays;
import java.util.List;

/**
 * 葉っぱをドロップさせる処理
 */
public class SSLeafLootModifier extends LootModifier {

    public SSLeafLootModifier(LootItemCondition[] conditionsIn) {
        super(conditionsIn);
    }

    @NotNull
    @Override
    protected List<ItemStack> doApply(List<ItemStack> generatedLoot, LootContext context) {

        generatedLoot.add(new ItemStack(SSItems.LEAF.get(),1));

        return generatedLoot;
    }

    public static class Serializer extends GlobalLootModifierSerializer<SSLeafLootModifier> {

        @Override
        public SSLeafLootModifier read(ResourceLocation name, JsonObject object, LootItemCondition[] conditionsIn) {
            return new SSLeafLootModifier(conditionsIn);
        }

        @Override
        public JsonObject write(SSLeafLootModifier instance) {
            return this.makeConditions(instance.conditions);
        }
    }

}
