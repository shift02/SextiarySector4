package shift.sextiarysector.data;

import net.minecraft.advancements.critereon.EnchantmentPredicate;
import net.minecraft.advancements.critereon.EntityPredicate;
import net.minecraft.advancements.critereon.ItemPredicate;
import net.minecraft.advancements.critereon.MinMaxBounds;
import net.minecraft.data.DataGenerator;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.enchantment.Enchantments;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.LeavesBlock;
import net.minecraft.world.level.storage.loot.LootContext;
import net.minecraft.world.level.storage.loot.predicates.*;
import net.minecraftforge.common.data.GlobalLootModifierProvider;
import net.minecraftforge.common.loot.LootTableIdCondition;
import net.minecraftforge.registries.ForgeRegistries;
import shift.sextiarysector.SSItems;
import shift.sextiarysector.SextiarySector4;
import shift.sextiarysector.loot.LootResult;
import shift.sextiarysector.loot.SSBaseLootModifier;
import shift.sextiarysector.loot.SSLeafLootModifier;

import java.util.Map;

/**
 * 既存のJsonとは別でドロップ処理を動作させる
 */
public class SSGlobalLootModifierProvider extends GlobalLootModifierProvider {

    public SSGlobalLootModifierProvider(DataGenerator gen) {
        super(gen, SextiarySector4.MOD_ID);
    }

    @Override
    protected void start() {

        //葉っぱBlockから葉っぱをドロップ
        for (Map.Entry<ResourceKey<Block>, Block> entry : ForgeRegistries.BLOCKS.getEntries()) {
            if (entry.getValue() instanceof LeavesBlock leavesBlock) {
                add("drop_leaf_form_" + entry.getKey().location().getPath(), new SSLeafLootModifier(new LootItemCondition[]{
                        InvertedLootItemCondition.invert(// Not
                                AlternativeLootItemCondition.alternative( // OR
                                        //ハサミとシルクタッチの時発動しないように
                                        MatchTool.toolMatches(ItemPredicate.Builder.item().of(Items.SHEARS)),
                                        MatchTool.toolMatches(ItemPredicate.Builder.item().hasEnchantment(new EnchantmentPredicate(Enchantments.SILK_TOUCH, MinMaxBounds.Ints.atLeast(1))))
                                )
                        ).build(),
                        LootItemBlockStatePropertyCondition.hasBlockStateProperties(leavesBlock).build()
                }));
            }
        }

        //ゾンビからも葉っぱをドロップするように
        add("drop_leaf_form_zombie", new SSLeafLootModifier(new LootItemCondition[]{
                LootItemEntityPropertyCondition.hasProperties(
                        LootContext.EntityTarget.THIS,
                        EntityPredicate.Builder.entity().of(EntityType.ZOMBIE)
                ).build()
        }));

        add("drop_leaf_form_fishing", new SSLeafLootModifier(new LootItemCondition[]{
                LootTableIdCondition.builder(new ResourceLocation("minecraft", "gameplay/fishing")).build()
        }));

        //イカから刺し身のドロップ
        add("drop_squid_sashimi_form_squid", new SSBaseLootModifier(
                new LootItemCondition[]{
                        LootItemEntityPropertyCondition.hasProperties(
                                LootContext.EntityTarget.THIS,
                                EntityPredicate.Builder.entity().of(EntityType.SQUID)
                        ).build()
                },
                new LootResult(SSItems.SQUID_SASHIMI.get().asItem())
        ));


    }
}
