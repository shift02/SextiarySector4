package shift.sextiarysector.data;

import net.minecraft.data.DataGenerator;
import net.minecraft.data.recipes.FinishedRecipe;
import net.minecraft.data.recipes.RecipeProvider;
import net.minecraft.data.recipes.ShapedRecipeBuilder;
import net.minecraft.data.recipes.SimpleCookingRecipeBuilder;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.ItemTags;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.level.ItemLike;
import shift.sextiarysector.SSBlocks;
import shift.sextiarysector.SSItems;
import shift.sextiarysector.SextiarySector4;

import java.util.function.Consumer;

/**
 * レシピを出力する
 */
public class SSRecipeProvider extends RecipeProvider {
    public SSRecipeProvider(DataGenerator generatorIn) {
        super(generatorIn);
    }

    @Override
    protected void buildCraftingRecipes(Consumer<FinishedRecipe> consumer) {

//        ShapelessRecipeBuilder.shapeless(Items.DIAMOND, 1)
//                .requires(Blocks.DIRT)
//                .unlockedBy("has_item", has(Blocks.DIRT))
//                .save(consumer, modId("dirt_to_diamond"));
//
//        SimpleCookingRecipeBuilder.campfireCooking(
//                        Ingredient.of(Blocks.DIRT),
//                        Items.DIAMOND, 0.7f, 200)
//                .unlockedBy("has_item", has(Blocks.DIRT))
//                .save(consumer, modId("dirt_to_diamond_from_campfire_cooking"));

        //草
        ShapedRecipeBuilder.shaped(SSBlocks.LEAF_BLOCK.get())
                .define('#', SSItems.LEAF.get())
                .pattern("###")
                .pattern("###")
                .pattern("###")
                .unlockedBy("has_item", has(SSItems.LEAF.get()))
                .save(consumer);

        //草ベッド
        ShapedRecipeBuilder.shaped(SSBlocks.LEAF_BED.get())
                .define('#', ItemTags.PLANKS)
                .define('W', SSBlocks.LEAF_BLOCK.get())
                .pattern("WWW")
                .pattern("###")
                .unlockedBy("has_item", has(SSItems.LEAF.get()))
                .save(consumer);

        //イカ焼き
        cookingFood(consumer,
                Ingredient.of(SSItems.SQUID_SASHIMI.get()),
                SSItems.SQUID_GRILLED.get(),
                SSItems.SQUID_SASHIMI.get(),
                "squid_sashimi_to_squid_grilled"
        );

    }

    private void cookingFood(Consumer<FinishedRecipe> consumer, Ingredient pIngredient, ItemLike pResult, ItemLike unlocked, String path) {

        SimpleCookingRecipeBuilder.campfireCooking(
                        pIngredient,
                        pResult, 0.7f, 600)
                .unlockedBy("has_item", has(unlocked))
                .save(consumer, modId(path + "_from_campfire_cooking"));

        SimpleCookingRecipeBuilder.smelting(
                        pIngredient,
                        pResult, 0.7f, 200)
                .unlockedBy("has_item", has(unlocked))
                .save(consumer, modId(path + "_from_smelting"));

        SimpleCookingRecipeBuilder.smoking(
                        pIngredient,
                        pResult, 0.7f, 100)
                .unlockedBy("has_item", has(unlocked))
                .save(consumer, modId(path + "_from_smoking"));

    }

    private static ResourceLocation modId(String path) {
        return new ResourceLocation(SextiarySector4.MOD_ID, path);
    }
}
