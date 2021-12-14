package shift.sextiarysector.client;

import net.minecraft.client.renderer.BiomeColors;
import net.minecraft.world.level.FoliageColor;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.client.event.ColorHandlerEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import shift.sextiarysector.SSBlocks;
import shift.sextiarysector.SextiarySector4;

/**
 * ブロックやアイテムに着色するクラス
 */
@Mod.EventBusSubscriber(modid = SextiarySector4.MOD_ID, value = Dist.CLIENT, bus = Mod.EventBusSubscriber.Bus.MOD)
public class SSColourManager {

    @SubscribeEvent
    public static void registerBlockColourHandlers(final ColorHandlerEvent.Block event) {

        event.getBlockColors().register((blockState, blockAndTintGetter, blockPos, __) -> {
            return blockAndTintGetter != null && blockPos != null ? BiomeColors.getAverageFoliageColor(blockAndTintGetter, blockPos) : FoliageColor.getDefaultColor();
        }, SSBlocks.LEAF_BED.get());

        event.getBlockColors().register((blockState, blockAndTintGetter, blockPos, __) -> {
            return 0x58c758;
        }, SSBlocks.LEAF_BLOCK.get());

    }

    @SubscribeEvent
    public static void registerBlockColourHandlers(final ColorHandlerEvent.Item event) {

        event.getItemColors().register((itemStack, i) -> {
            return 0x58c758;
        }, SSBlocks.LEAF_BLOCK.get());

    }

}
