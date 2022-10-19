package shift.sextiarysector;

import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.ItemTags;
import net.minecraft.tags.TagKey;
import net.minecraft.world.item.Item;

public class SSTags {

    public static class Items {
        private static void init() {
        }

        public static final TagKey<Item> PLASTIC = tag("plastic");

        private static TagKey<Item> tag(String name) {
            return ItemTags.create(new ResourceLocation("forge", name));
        }

    }

}
