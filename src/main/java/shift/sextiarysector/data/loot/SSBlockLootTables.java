package shift.sextiarysector.data.loot;

import net.minecraft.data.loot.BlockLoot;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.properties.BedPart;
import net.minecraftforge.registries.RegistryObject;
import org.jetbrains.annotations.NotNull;
import shift.sextiarysector.SSBlocks;
import shift.sextiarysector.SextiarySector4;
import shift.sextiarysector.block.LeafBlock;

import java.util.stream.Collectors;

/**
 * ブロック破壊時にドロップするアイテムの設定
 */
public class SSBlockLootTables extends BlockLoot {
    @Override
    protected void addTables() {

        //葉っぱベッド
        this.add(SSBlocks.LEAF_BED.get(), (block) -> createSinglePropConditionTable(block, LeafBlock.PART, BedPart.HEAD));

        dropSelf(SSBlocks.LEAF_BLOCK.get());

        /*
        for (RegistryObject<Block> entry : SextiarySector4.BLOCKS.getEntries()) {
            dropSelf(entry.get());
        }*/

    }

    @Override
    @NotNull
    protected Iterable<Block> getKnownBlocks() {
        return SextiarySector4.BLOCKS.getEntries().stream()
                .map(RegistryObject::get)
                .collect(Collectors.toList());
    }


}
