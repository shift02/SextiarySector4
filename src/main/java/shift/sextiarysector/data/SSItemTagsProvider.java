package shift.sextiarysector.data;

import net.minecraft.data.DataGenerator;
import net.minecraft.data.tags.BlockTagsProvider;
import net.minecraft.data.tags.ItemTagsProvider;
import net.minecraftforge.common.data.ExistingFileHelper;
import org.jetbrains.annotations.Nullable;
import shift.sextiarysector.SSItems;
import shift.sextiarysector.SSTags;
import shift.sextiarysector.SextiarySector4;

public class SSItemTagsProvider extends ItemTagsProvider {

    public SSItemTagsProvider(DataGenerator dataGenerator, BlockTagsProvider blockTagsProvider, @Nullable ExistingFileHelper existingFileHelper) {
        super(dataGenerator, blockTagsProvider, SextiarySector4.MOD_ID, existingFileHelper);
    }

    @Override
    public void addTags() {
        tag(SSTags.Items.PLASTIC).add(SSItems.PLASTIC.get());
    }

}
