package shift.sextiarysector.data;

import net.minecraft.data.DataGenerator;
import net.minecraft.data.tags.BlockTagsProvider;
import net.minecraftforge.common.data.ExistingFileHelper;
import org.jetbrains.annotations.Nullable;
import shift.sextiarysector.SextiarySector4;

public class SSBlockTagsProvider extends BlockTagsProvider {
    public SSBlockTagsProvider(DataGenerator generator, @Nullable ExistingFileHelper existingFileHelper) {
        super(generator, SextiarySector4.MOD_ID, existingFileHelper);
    }

    @Override
    public void addTags() {

    }
}
