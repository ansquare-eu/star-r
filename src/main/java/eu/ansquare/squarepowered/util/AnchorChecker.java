package eu.ansquare.squarepowered.util;

import eu.ansquare.starr.blocks.WorldAnchorBlock;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.text.Text;
import net.minecraft.util.math.BlockPos;

public class AnchorChecker {
	public static boolean checkSpatial(boolean sendMessages, BlockPos location, PlayerEntity player){

		if(WorldAnchorBlock.isInVicinity(true, location, player.getWorld())){
			player.sendMessage(Text.translatable("message.starr.anchored.destination"), true);
			return false;
		} else if(WorldAnchorBlock.isInVicinity(true, player.getBlockPos(), player.getWorld())){
			player.sendMessage(Text.translatable("message.starr.anchored.origin"), true);
			return false;
		}
		return true;
	}
}
