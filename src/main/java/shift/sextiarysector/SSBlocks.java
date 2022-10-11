package shift.sextiarysector;

import net.minecraft.world.item.BedItem;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.material.Material;
import net.minecraftforge.registries.RegistryObject;
import shift.sextiarysector.block.LeafBlock;
import shift.sextiarysector.block.SapCauldronBlock;
import shift.sextiarysector.block.SpileBlock;
import shift.sextiarysector.item.SSBaseBlockItem;

import java.util.function.Function;
import java.util.function.Supplier;

/**
 * SS4のブロック管理クラス
 */
public class SSBlocks {

    // ---------------------
    // Core (コア)
    // ---------------------
    public static final RegistryObject<Block> LEAF_BLOCK = registerBlockWithCustomItem("leaf_block",
            () -> new Block(BlockBehaviour.Properties.of(Material.LEAVES).sound(SoundType.GRASS).strength(0.2F).noOcclusion()),
            block -> new SSBaseBlockItem(block, new Item.Properties().tab(SSTabs.TAB_CORE)).setBurnTime(450)
    );

    public static final RegistryObject<Block> LEAF_BED = registerBlockWithCustomItem("leaf_bed",
            () -> new LeafBlock(BlockBehaviour.Properties.of(Material.LEAVES).sound(SoundType.GRASS).strength(0.2F).noOcclusion()),
            block -> new BedItem(block, new Item.Properties().tab(SSTabs.TAB_CORE))
    );

    // ---------------------
    // Forestry (林業)
    // ---------------------
    public static final RegistryObject<Block> SPILE = registerBlockWithCustomItem("spile",
            () -> new SpileBlock(BlockBehaviour.Properties.of(Material.LEAVES).sound(SoundType.METAL).strength(0.2F).noOcclusion().randomTicks()),
            block -> new SSBaseBlockItem(block, new Item.Properties().tab(SSTabs.TAB_FORESTRY))
    );

    //樹液入の大鎌
    public static final RegistryObject<SapCauldronBlock> SAP_CAULDRON = register("sap_cauldron",
            () -> new SapCauldronBlock(BlockBehaviour.Properties.copy(Blocks.CAULDRON)),
            SSTabs.TAB_FORESTRY
    );

    static void register() {
    }

    /**
     * Blockのみの登録
     */
    private static <T extends Block> RegistryObject<T> registerBlockOnly(String name, Supplier<T> block) {
        return SextiarySector4.BLOCKS.register(name, block);
    }

    /**
     * 特殊なBlockItemの登録
     */
    private static <T extends Block, X extends BlockItem> RegistryObject<T> registerBlockWithCustomItem(String name, Supplier<T> block, Function<T, X> item) {
        RegistryObject<T> ret = registerBlockOnly(name, block);
        SextiarySector4.ITEMS.register(name, () -> item.apply(ret.get()));
        return ret;
    }

    private static <T extends Block> RegistryObject<T> register(String name, Supplier<T> block, CreativeModeTab pCategory) {
        RegistryObject<T> ret = registerBlockOnly(name, block);
        SextiarySector4.ITEMS.register(name, () -> new BlockItem(ret.get(), new Item.Properties().tab(pCategory)));
        return ret;
    }

}
