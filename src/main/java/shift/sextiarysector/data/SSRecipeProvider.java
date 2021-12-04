package shift.sextiarysector.data;

import net.minecraft.data.DataGenerator;
import net.minecraft.data.recipes.FinishedRecipe;
import net.minecraft.data.recipes.RecipeProvider;
import net.minecraft.data.recipes.ShapelessRecipeBuilder;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.block.Blocks;
import shift.sextiarysector.SextiarySector4;

import java.util.function.Consumer;

public class SSRecipeProvider extends RecipeProvider {
    public SSRecipeProvider(DataGenerator generatorIn) {
        super(generatorIn);
    }

    @Override
    protected void buildCraftingRecipes(Consumer<FinishedRecipe> consumer)
    {

        ShapelessRecipeBuilder.shapeless(Items.DIAMOND, 1)
                .requires(Blocks.DIRT)
                .unlockedBy("has_item", has(Blocks.DIRT))
                .save(consumer);

    }

    private static ResourceLocation modId(String path) {
        return new ResourceLocation(SextiarySector4.MOD_ID, path);
    }
}
