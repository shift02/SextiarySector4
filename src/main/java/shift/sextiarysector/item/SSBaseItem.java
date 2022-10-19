package shift.sextiarysector.item;

import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.RecipeType;

import javax.annotation.Nullable;

public class SSBaseItem extends Item implements ISimpleTexture {

    private int burnTime = -1;

    private boolean customTexture = false;

    public SSBaseItem(Properties properties) {
        super(properties);
    }

    public SSBaseItem enableCustomTexture() {
        customTexture = true;
        return this;
    }

    @Override
    public boolean hasCustomTexture() {
        return customTexture;
    }

    @Override
    public String getTextureName() {
        return null;
    }

    public SSBaseItem setBurnTime(int burnTime) {
        this.burnTime = burnTime;
        return this;
    }

    public int getBurnTime(ItemStack itemStack, @Nullable RecipeType<?> recipeType) {
        return burnTime;
    }
}
