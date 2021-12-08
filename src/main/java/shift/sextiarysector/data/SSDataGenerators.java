package shift.sextiarysector.data;

import net.minecraft.data.DataGenerator;
import net.minecraftforge.common.data.ExistingFileHelper;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.forge.event.lifecycle.GatherDataEvent;
import shift.sextiarysector.SextiarySector4;
import shift.sextiarysector.data.client.SSBlockStateProvider;
import shift.sextiarysector.data.client.SSItemModelProvider;

/**
 * SS4の各種Jsonを出力する
 */
@Mod.EventBusSubscriber(modid = SextiarySector4.MOD_ID, bus = Mod.EventBusSubscriber.Bus.MOD)
public final class SSDataGenerators {

    private SSDataGenerators() {
    }

    @SubscribeEvent
    public static void gatherData(GatherDataEvent event) {
        DataGenerator gen = event.getGenerator();
        ExistingFileHelper existingFileHelper = event.getExistingFileHelper();

        gen.addProvider(new SSRecipeProvider(gen));
        gen.addProvider(new SSGlobalLootModifierProvider(gen));

        gen.addProvider(new SSLootTableProvider(gen));

        //client側
        gen.addProvider(new SSItemModelProvider(gen, existingFileHelper));
        gen.addProvider(new SSBlockStateProvider(gen, existingFileHelper));


    }

}
