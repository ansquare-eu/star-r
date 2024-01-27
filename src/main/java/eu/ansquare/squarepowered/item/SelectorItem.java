package eu.ansquare.squarepowered.item;

import eu.ansquare.squarepowered.cca.SavedLocationComponent;
import eu.ansquare.squarepowered.util.PrettyPosUtil;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemUsageContext;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.text.Text;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.util.TypedActionResult;
import net.minecraft.world.World;

public class SelectorItem extends Item {
	public SelectorItem(Settings settings) {
		super(settings);
	}
	public ActionResult useOnBlock(ItemUsageContext context) {
		if(context.getPlayer() instanceof ServerPlayerEntity player){
			if(context.shouldCancelInteraction()){
				SavedLocationComponent.setSelTwo(player, context.getBlockPos());
				player.sendMessage(Text.translatable("message.starr.selected.two", context.getBlockPos().toShortString()), true);
			} else {
				SavedLocationComponent.setSelOne(player, context.getBlockPos());
				player.sendMessage(Text.translatable("message.starr.selected.one", context.getBlockPos().toShortString()), true);

			}
			return ActionResult.SUCCESS;
		}
		return ActionResult.PASS;
	}
}
