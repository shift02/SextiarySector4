package shift.sextiarysector.data;

import net.minecraft.data.DataGenerator;
import net.minecraftforge.common.data.ExistingFileHelper;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.forge.event.lifecycle.GatherDataEvent;
import shift.sextiarysector.SextiarySector4;
import shift.sextiarysector.data.client.SSItemModelProvider;
import shift.sextiarysector.data.client.block.LeafBedBlockStateProvider;
import shift.sextiarysector.data.client.block.SSBlockStateProvider;

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
        gen.addProvider(new SSLanguageProvider(gen));

        //Block
        gen.addProvider(new SSBlockStateProvider(gen, existingFileHelper));
        gen.addProvider(new LeafBedBlockStateProvider(gen, existingFileHelper));

        //Item
        gen.addProvider(new SSItemModelProvider(gen, existingFileHelper));

    }

}
