package shift.sextiarysector.client;

import net.minecraft.client.renderer.BiomeColors;
import net.minecraft.world.level.FoliageColor;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.client.event.RegisterColorHandlersEvent;
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
    public static void registerBlockColourHandlers(final RegisterColorHandlersEvent.Block event) {


        event.register((blockState, blockAndTintGetter, blockPos, tintIndex) -> {
            return blockAndTintGetter != null && blockPos != null ? BiomeColors.getAverageFoliageColor(blockAndTintGetter, blockPos) : FoliageColor.getDefaultColor();
        }, SSBlocks.LEAF_BED.get());

        event.register((blockState, blockAndTintGetter, blockPos, tintIndex) -> {
            return 0x58c758;
        }, SSBlocks.LEAF_BLOCK.get());

        event.register((blockState, blockAndTintGetter, blockPos, tintIndex) -> {
            return 0xff8c00;
        }, SSBlocks.SAP_CAULDRON.get());

    }

    @SubscribeEvent
    public static void registerBlockColourHandlers(final RegisterColorHandlersEvent.Item event) {

        event.register((itemStack, tintIndex) -> {
            return 0x58c758;
        }, SSBlocks.LEAF_BLOCK.get());

        event.register((itemStack, tintIndex) -> {
            return 0xff8c00;
        }, SSBlocks.SAP_CAULDRON.get());

    }

}
