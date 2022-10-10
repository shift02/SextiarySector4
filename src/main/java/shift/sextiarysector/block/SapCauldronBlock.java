package shift.sextiarysector.block;

import it.unimi.dsi.fastutil.objects.Object2ObjectOpenHashMap;
import net.minecraft.Util;
import net.minecraft.core.cauldron.CauldronInteraction;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.biome.Biome;
import net.minecraft.world.level.block.LayeredCauldronBlock;

import java.util.Map;
import java.util.function.Predicate;

public class SapCauldronBlock extends LayeredCauldronBlock {

    /**
     * 右クリック時の動作
     */
    public static Map<Item, CauldronInteraction> SAP_INTERACTION = newInteractionMap();

    /**
     * 雨などで増えるかどうか
     */
    public static final Predicate<Biome.Precipitation> SAP_FILL = (biomesPrecipitation) -> {
        return false;
    };

    private static Map<Item, CauldronInteraction> newInteractionMap() {
        return Util.make(new Object2ObjectOpenHashMap<>(), (openHashMap) -> {
            openHashMap.defaultReturnValue((blockState, level, blockPos, player, interactionHand, itemStack) -> {
                return InteractionResult.PASS;
            });
        });
    }

    public SapCauldronBlock(Properties pProperties) {
        super(pProperties, SAP_FILL, SAP_INTERACTION);
    }
}
