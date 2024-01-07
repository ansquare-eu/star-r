package eu.ansquare.squarepowered.util;

import eu.ansquare.squarepowered.Squarepowered;
import eu.ansquare.squarepowered.power.ModifySpatialAnchorRangePower;
import eu.ansquare.starr.blocks.WorldAnchorBlock;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.text.Text;
import net.minecraft.util.math.BlockPos;

public class AnchorChecker {
	public static boolean checkSpatial(boolean sendMessages, BlockPos location, PlayerEntity player){
		Squarepowered.log(String.valueOf(ModifySpatialAnchorRangePower.getFinalRange(player)), 0);
		if(WorldAnchorBlock.isInVicinity(ModifySpatialAnchorRangePower.getFinalRange(player), true, location, player.getWorld())){
			player.sendMessage(Text.translatable("message.starr.anchored.destination"), true);
			return false;
		} else if(WorldAnchorBlock.isInVicinity(ModifySpatialAnchorRangePower.getFinalRange(player), true, player.getBlockPos(), player.getWorld())){
			player.sendMessage(Text.translatable("message.starr.anchored.origin"), true);
			return false;
		}
		return true;
	}
	public static boolean checkLocalSpatial(boolean sendMessages, BlockPos location, PlayerEntity player){
		if(WorldAnchorBlock.isInVicinity(ModifySpatialAnchorRangePower.getFinalRange(player), true, location, player.getWorld())){
			player.sendMessage(Text.translatable("message.starr.anchored.local"), true);
			return false;
		}
		return true;
	}
}
