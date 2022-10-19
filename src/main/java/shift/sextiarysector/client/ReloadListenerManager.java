package shift.sextiarysector.client;

import net.minecraft.client.renderer.BlockEntityWithoutLevelRenderer;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.common.util.NonNullLazy;
import net.minecraftforge.event.AddReloadListenerEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.loading.FMLEnvironment;
import net.minecraftforge.fml.loading.FMLLoader;
import shift.sextiarysector.SextiarySector4;
import shift.sextiarysector.client.renderer.RendererShield;

@Mod.EventBusSubscriber(modid = SextiarySector4.MOD_ID, value = Dist.CLIENT, bus = Mod.EventBusSubscriber.Bus.FORGE)
public class ReloadListenerManager {

    public static final NonNullLazy<BlockEntityWithoutLevelRenderer> rendererShield = NonNullLazy.of(RendererShield::new);
    
    @SubscribeEvent
    public static void registerAddReloadListenerEvent(final AddReloadListenerEvent event) {

        if (FMLEnvironment.dist == Dist.CLIENT && !FMLLoader.getLaunchHandler().isData()) {
            event.addListener(rendererShield.get());
        }

    }

}
