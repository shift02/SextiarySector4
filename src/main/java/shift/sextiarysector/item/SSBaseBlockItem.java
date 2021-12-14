package shift.sextiarysector.item;

import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.RecipeType;
import net.minecraft.world.level.block.Block;

import javax.annotation.Nullable;

public class SSBaseBlockItem extends BlockItem {


    private int burnTime = -1;

    public SSBaseBlockItem(Block block, Properties properties) {
        super(block, properties);
    }


    public SSBaseBlockItem setBurnTime(int burnTime) {
        this.burnTime = burnTime;
        return this;
    }

    public int getBurnTime(ItemStack itemStack, @Nullable RecipeType<?> recipeType) {
        return burnTime;
    }
}
