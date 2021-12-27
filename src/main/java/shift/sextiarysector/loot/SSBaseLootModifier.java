package shift.sextiarysector.loot;

import com.google.gson.JsonObject;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.GsonHelper;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.storage.loot.LootContext;
import net.minecraft.world.level.storage.loot.predicates.LootItemCondition;
import net.minecraftforge.common.loot.GlobalLootModifierSerializer;
import net.minecraftforge.common.loot.LootModifier;
import net.minecraftforge.registries.ForgeRegistries;
import org.jetbrains.annotations.NotNull;

import java.util.List;

/**
 * アイテムをドロップさせる処理をベースクラス
 */
public class SSBaseLootModifier extends LootModifier {

    private final Item dropItem;

    /**
     * @param conditionsIn ドロップする条件
     * @param dropItem     ドロップアイテム
     */
    public SSBaseLootModifier(LootItemCondition[] conditionsIn, Item dropItem) {
        super(conditionsIn);
        this.dropItem = dropItem;
    }

    @NotNull
    @Override
    protected List<ItemStack> doApply(List<ItemStack> generatedLoot, LootContext context) {

        generatedLoot.add(new ItemStack(dropItem, 1));

        return generatedLoot;
    }

    public static class Serializer extends GlobalLootModifierSerializer<SSBaseLootModifier> {

        @Override
        public SSBaseLootModifier read(ResourceLocation name, JsonObject object, LootItemCondition[] conditionsIn) {
            Item dropItem = ForgeRegistries.ITEMS.getValue(new ResourceLocation((GsonHelper.getAsString(object.get("result").getAsJsonObject(), "item"))));
            return new SSBaseLootModifier(conditionsIn, dropItem);
        }

        @Override
        public JsonObject write(SSBaseLootModifier instance) {
            JsonObject jsonObject = this.makeConditions(instance.conditions);
            JsonObject result = new JsonObject();
            jsonObject.add("result", result);
            result.addProperty("item", ForgeRegistries.ITEMS.getKey(instance.dropItem).toString());
            return jsonObject;
        }

    }

}
