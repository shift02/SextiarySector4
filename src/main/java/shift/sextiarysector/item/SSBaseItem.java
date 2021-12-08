package shift.sextiarysector.item;

import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.RecipeType;

import javax.annotation.Nullable;

public class SSBaseItem extends Item implements ISimpleTexture {

    private int burnTime = -1;

    public SSBaseItem(Properties p_41383_) {
        super(p_41383_);
    }

    @Override
    public String getTextureName() {
        return null;
    }

    public SSBaseItem setBurnTime(int burnTime){
        this.burnTime = burnTime;
        return this;
    }

    public int getBurnTime(ItemStack itemStack, @Nullable RecipeType<?> recipeType)
    {
        return burnTime;
    }
}
