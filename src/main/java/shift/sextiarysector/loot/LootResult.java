package shift.sextiarysector.loot;

import com.google.common.base.Suppliers;
import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import net.minecraft.world.item.Item;
import net.minecraftforge.registries.ForgeRegistries;

import java.util.function.Supplier;

public class LootResult {

    public static final Supplier<Codec<LootResult>> CODEC = Suppliers.memoize(() -> RecordCodecBuilder.create(inst ->
            inst.group(
                    ForgeRegistries.ITEMS.getCodec().fieldOf("item").forGetter(m -> m.item)
            ).apply(inst, LootResult::new)
    ));

    private final Item item;

    public LootResult(Item item) {
        this.item = item;
    }

    public Item getItem() {
        return item;
    }

    Codec<LootResult> codec() {
        return CODEC.get();
    }

}
