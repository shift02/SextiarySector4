package shift.sextiarysector.client;

import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
import shift.sextiarysector.SextiarySector4;

/**
 * ブロックの描画モードを設定するクラス
 */
@Mod.EventBusSubscriber(modid = SextiarySector4.MOD_ID, bus = Mod.EventBusSubscriber.Bus.MOD, value = Dist.CLIENT)
public class SSRenderLayerManager {

    @SubscribeEvent
    public static void setupRenderLayer(final FMLClientSetupEvent event) {
        event.enqueueWork(() -> {
            //透明なテクスチャ
            //ItemBlockRenderTypes.setRenderLayer(SSBlocks.LEAF_BED.get(), RenderType.cutout());

            //ItemBlockRenderTypes.setRenderLayer(SSBlocks.LEAF_BLOCK.get(), RenderType.cutout());

            //ItemBlockRenderTypes.setRenderLayer(SSBlocks.SPILE.get(), RenderType.cutout());
        });
    }

}
