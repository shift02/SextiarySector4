package shift.sextiarysector.data;

import net.minecraft.data.DataGenerator;
import net.minecraft.data.recipes.FinishedRecipe;
import net.minecraft.data.recipes.RecipeProvider;
import net.minecraft.data.recipes.ShapedRecipeBuilder;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.ItemTags;
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

    }

    private static ResourceLocation modId(String path) {
        return new ResourceLocation(SextiarySector4.MOD_ID, path);
    }
}
