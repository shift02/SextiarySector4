package shift.sextiarysector.loot;

import com.google.common.base.Suppliers;
import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import it.unimi.dsi.fastutil.objects.ObjectArrayList;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.storage.loot.LootContext;
import net.minecraft.world.level.storage.loot.predicates.LootItemCondition;
import net.minecraftforge.common.loot.IGlobalLootModifier;
import net.minecraftforge.common.loot.LootModifier;
import org.jetbrains.annotations.NotNull;
import shift.sextiarysector.SSItems;

import java.util.function.Supplier;

/**
 * 葉っぱをドロップさせる処理
 */
public class SSLeafLootModifier extends LootModifier {

    public static final Supplier<Codec<SSLeafLootModifier>> CODEC = Suppliers.memoize(() -> RecordCodecBuilder.create(inst -> codecStart(inst)
            .apply(inst, SSLeafLootModifier::new)
    ));

    public SSLeafLootModifier(LootItemCondition[] conditionsIn) {
        super(conditionsIn);
    }

    @NotNull
    @Override
    protected ObjectArrayList<ItemStack> doApply(ObjectArrayList<ItemStack> generatedLoot, LootContext context) {

        //実際のドロップリスト
        //本当はJsonから取得してゴニョゴニョする必要がありそうだけど手抜き
        generatedLoot.add(new ItemStack(SSItems.LEAF.get(), 1));

        return generatedLoot;

    }

    @Override
    public Codec<? extends IGlobalLootModifier> codec() {
        return CODEC.get();
    }
    
}
