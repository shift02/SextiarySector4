package shift.sextiarysector.data;

import net.minecraft.data.DataGenerator;
import net.minecraft.resources.ResourceLocation;
import net.minecraftforge.common.data.ExistingFileHelper;
import net.minecraftforge.data.event.GatherDataEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import shift.sextiarysector.SextiarySector4;
import shift.sextiarysector.data.client.SSItemModelProvider;
import shift.sextiarysector.data.client.block.LeafBedBlockStateProvider;
import shift.sextiarysector.data.client.block.SSBlockStateProvider;
import shift.sextiarysector.data.client.block.SpileBlockStateProvider;

/**
 * SS4の各種Jsonを出力する
 */
@Mod.EventBusSubscriber(modid = SextiarySector4.MOD_ID, bus = Mod.EventBusSubscriber.Bus.MOD)
public final class SSDataGenerators {

    public static final ResourceLocation RENDERTYPE_CUTOUT = new ResourceLocation("cutout");

    private SSDataGenerators() {
    }

    @SubscribeEvent
    public static void gatherData(GatherDataEvent event) {
        DataGenerator gen = event.getGenerator();
        ExistingFileHelper existingFileHelper = event.getExistingFileHelper();

        gen.addProvider(event.includeServer(), new SSRecipeProvider(gen));
        gen.addProvider(event.includeServer(), new SSGlobalLootModifierProvider(gen));

        gen.addProvider(event.includeServer(), new SSLootTableProvider(gen));

        SSBlockTagsProvider blockTags = new SSBlockTagsProvider(gen, existingFileHelper);
        gen.addProvider(event.includeServer(), blockTags);
        gen.addProvider(event.includeServer(), new SSItemTagsProvider(gen, blockTags, existingFileHelper));


        //client側
        gen.addProvider(event.includeServer(), new SSLanguageProvider(gen));

        //Block
        gen.addProvider(event.includeServer(), new SSBlockStateProvider(gen, existingFileHelper));
        gen.addProvider(event.includeServer(), new LeafBedBlockStateProvider(gen, existingFileHelper));
        gen.addProvider(event.includeServer(), new SpileBlockStateProvider(gen, existingFileHelper));

        //Item
        gen.addProvider(event.includeServer(), new SSItemModelProvider(gen, existingFileHelper));

    }

}
