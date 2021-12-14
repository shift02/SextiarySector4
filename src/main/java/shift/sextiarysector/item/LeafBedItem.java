package shift.sextiarysector.item;

import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockState;
import org.jetbrains.annotations.NotNull;

public class LeafBedItem extends SSBaseBlockItem {
    public LeafBedItem(Block block, Properties properties) {
        super(block, properties);
    }

    protected boolean placeBlock(BlockPlaceContext placeContext, @NotNull BlockState blockState) {
        return placeContext.getLevel().setBlock(placeContext.getClickedPos(), blockState, 26);
    }
}
