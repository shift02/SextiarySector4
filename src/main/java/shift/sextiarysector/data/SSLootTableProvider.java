package shift.sextiarysector.data;

import com.google.common.collect.ImmutableList;
import com.google.common.collect.Sets;
import com.mojang.datafixers.util.Pair;
import net.minecraft.data.DataGenerator;
import net.minecraft.data.loot.LootTableProvider;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.storage.loot.BuiltInLootTables;
import net.minecraft.world.level.storage.loot.LootTable;
import net.minecraft.world.level.storage.loot.LootTables;
import net.minecraft.world.level.storage.loot.ValidationContext;
import net.minecraft.world.level.storage.loot.parameters.LootContextParamSet;
import net.minecraft.world.level.storage.loot.parameters.LootContextParamSets;
import org.jetbrains.annotations.NotNull;
import shift.sextiarysector.SextiarySector4;
import shift.sextiarysector.data.loot.SSBlockLootTables;

import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.function.BiConsumer;
import java.util.function.Consumer;
import java.util.function.Supplier;
import java.util.stream.Collectors;

/**
 * 各種ドロップアイテムのJson生成
 */
public class SSLootTableProvider extends LootTableProvider {

    private final List<Pair<Supplier<Consumer<BiConsumer<ResourceLocation, LootTable.Builder>>>, LootContextParamSet>> lootTableGenerators = ImmutableList.of(
            Pair.of(SSBlockLootTables::new, LootContextParamSets.BLOCK)//Block
    );

    public SSLootTableProvider(final DataGenerator dataGeneratorIn) {
        super(dataGeneratorIn);
    }

    @Override
    @NotNull
    protected List<Pair<Supplier<Consumer<BiConsumer<ResourceLocation, LootTable.Builder>>>, LootContextParamSet>> getTables() {
        return lootTableGenerators;
    }

    @Override
    protected void validate(final Map<ResourceLocation, LootTable> map, final @NotNull ValidationContext validationContext) {

        final Set<ResourceLocation> modLootTableIds = BuiltInLootTables
                .all()
                .stream()
                .filter(lootTable -> lootTable.getNamespace().equals(SextiarySector4.MOD_ID))
                .collect(Collectors.toSet());

        for (final ResourceLocation resourcelocation : Sets.difference(modLootTableIds, map.keySet())) {
            validationContext.reportProblem("Missing mod loot table: " + resourcelocation);
        }

        map.forEach((id, lootTable) -> LootTables.validate(validationContext, id, lootTable));
    }


    @Override
    @NotNull
    public String getName() {
        return "SextiarySectorLootTables";
    }
}
