package shift.sextiarysector;

import net.minecraft.world.item.BedItem;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.material.Material;
import net.minecraftforge.registries.RegistryObject;
import shift.sextiarysector.block.LeafBlock;
import shift.sextiarysector.item.SSBaseBlockItem;

import java.util.function.Function;
import java.util.function.Supplier;

/**
 * SS4のブロック管理クラス
 */
public class SSBlocks {

    public static final RegistryObject<Block> LEAF_BLOCK = registerBlockWithCustomItem("leaf_block", () ->
                    new Block(BlockBehaviour.Properties.of(Material.LEAVES).sound(SoundType.GRASS).strength(0.2F).noOcclusion()),
            (RegistryObject<Block> ret) -> () ->
                    new SSBaseBlockItem(ret.get(), new Item.Properties().tab(SSTabs.TAB_CORE)).setBurnTime(450)
    );

    public static final RegistryObject<Block> LEAF_BED = registerBlockWithCustomItem("leaf_bed", () ->
                    new LeafBlock(BlockBehaviour.Properties.of(Material.LEAVES)
                            .sound(SoundType.GRASS).strength(0.2F).noOcclusion()),
            (RegistryObject<Block> ret) -> () ->
                    new BedItem(ret.get(), new Item.Properties().tab(SSTabs.TAB_CORE))
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
    private static <T extends Block, X extends BlockItem> RegistryObject<T> registerBlockWithCustomItem(String name, Supplier<T> block, Function<RegistryObject<T>, Supplier<X>> item) {
        RegistryObject<T> ret = registerBlockOnly(name, block);
        SextiarySector4.ITEMS.register(name, item.apply(ret));
        return ret;
    }

    private static <T extends Block> RegistryObject<T> register(String name, Supplier<T> block) {
        RegistryObject<T> ret = registerBlockOnly(name, block);
        SextiarySector4.ITEMS.register(name, () -> new BlockItem(ret.get(), new Item.Properties().tab(CreativeModeTab.TAB_BUILDING_BLOCKS)));
        return ret;
    }

}
