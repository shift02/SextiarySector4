package shift.sextiarysector.loot;

import com.google.common.base.Suppliers;
import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import it.unimi.dsi.fastutil.objects.ObjectArrayList;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.storage.loot.LootContext;
import net.minecraft.world.level.storage.loot.predicates.LootItemCondition;
import net.minecraftforge.common.loot.IGlobalLootModifier;
import net.minecraftforge.common.loot.LootModifier;
import org.jetbrains.annotations.NotNull;

import java.util.function.Supplier;

/**
 * アイテムをドロップさせる処理をベースクラス
 */
public class SSBaseLootModifier extends LootModifier {

    public static final Supplier<Codec<SSBaseLootModifier>> CODEC = Suppliers.memoize(() -> RecordCodecBuilder.create(inst -> codecStart(inst)
            .and(inst.group(LootResult.CODEC.get().fieldOf("result").<SSBaseLootModifier>forGetter(m -> new LootResult(m.dropItem))).t1())
            .apply(inst, SSBaseLootModifier::new)
    ));


    private final Item dropItem;

    /**
     * @param conditionsIn ドロップする条件
     * @param result       ドロップアイテム
     */
    public SSBaseLootModifier(LootItemCondition[] conditionsIn, LootResult result) {
        super(conditionsIn);
        this.dropItem = result.getItem();
    }

    @NotNull
    @Override
    protected ObjectArrayList<ItemStack> doApply(ObjectArrayList<ItemStack> generatedLoot, LootContext context) {

        generatedLoot.add(new ItemStack(dropItem, 1));

        return generatedLoot;
    }

    @Override
    public Codec<? extends IGlobalLootModifier> codec() {
        return CODEC.get();
    }
//
//    public static class Serializer extends Codec<SSBaseLootModifier> {
//
//        @Override
//        public SSBaseLootModifier read(ResourceLocation name, JsonObject object, LootItemCondition[] conditionsIn) {
//            Item dropItem = ForgeRegistries.ITEMS.getValue(new ResourceLocation((GsonHelper.getAsString(object.get("result").getAsJsonObject(), "item"))));
//            return new SSBaseLootModifier(conditionsIn, dropItem);
//        }
//
//        @Override
//        public JsonObject write(SSBaseLootModifier instance) {
//            JsonObject jsonObject = this.makeConditions(instance.conditions);
//            JsonObject result = new JsonObject();
//            jsonObject.add("result", result);
//            result.addProperty("item", ForgeRegistries.ITEMS.getKey(instance.dropItem).toString());
//            return jsonObject;
//        }
//
//    }

}
