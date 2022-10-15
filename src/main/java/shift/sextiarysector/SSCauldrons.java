package shift.sextiarysector;

import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.stats.Stats;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.ItemUtils;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.block.LayeredCauldronBlock;
import net.minecraft.world.level.gameevent.GameEvent;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import shift.sextiarysector.block.SapCauldronBlock;

public class SSCauldrons {

    public void setup(final FMLCommonSetupEvent event) {

        SapCauldronBlock.SAP_INTERACTION.put(Items.GLASS_BOTTLE, (blockState, level, blockPos, player, hand, itemStack) -> {
            if (!level.isClientSide) {
                Item item = itemStack.getItem();
                player.setItemInHand(hand, ItemUtils.createFilledResult(itemStack, player, new ItemStack(SSItems.SAP_BOTTLE.get())));
                player.awardStat(Stats.USE_CAULDRON);
                player.awardStat(Stats.ITEM_USED.get(item));
                LayeredCauldronBlock.lowerFillLevel(blockState, level, blockPos);
                level.playSound((Player) null, blockPos, SoundEvents.BOTTLE_FILL, SoundSource.BLOCKS, 1.0F, 1.0F);
                level.gameEvent((Entity) null, GameEvent.FLUID_PICKUP, blockPos);
            }

            return InteractionResult.sidedSuccess(level.isClientSide);
        });

    }

}
