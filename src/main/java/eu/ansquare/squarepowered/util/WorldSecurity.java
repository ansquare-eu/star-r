package eu.ansquare.squarepowered.util;

import eu.ansquare.sbd.BlockDataApi;
import eu.ansquare.squarepowered.Squarepowered;
import eu.ansquare.squarepowered.block.WorldAnchorBlock;
import eu.ansquare.squarepowered.power.ModifySpatialAnchorRangePower;
import eu.ansquare.squarepowered.util.changelogging.ChangeLogger;
import net.minecraft.block.BlockState;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.text.Text;
import net.minecraft.util.math.BlockPos;

public class WorldSecurity {
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
	public static boolean changeBlockState(BlockState state, BlockPos pos, ServerWorld world, ServerPlayerEntity player, boolean checkAnchor, boolean checkState, boolean willNoDrop, boolean logChange){
		if(canChangeBlockState(state, pos, world, player, checkAnchor, checkState)){
			boolean no_drop = state.isAir() ? false : willNoDrop;
			if(logChange){
				ChangeLogger.changeAndLog(world, pos, state, player, no_drop);
			} else {
				BlockDataApi.setBoolean(pos, world, "no_drop", no_drop);
				world.setBlockState(pos, state);
			}
			return true;
		}
		return false;
	}
	public static boolean canChangeBlockState(BlockState state, BlockPos pos, ServerWorld world, ServerPlayerEntity player, boolean checkAnchor, boolean checkState){
		if(checkAnchor){
			if(WorldAnchorBlock.isInVicinity(8, false, pos, world)){
				return false;
			}
		}
		return true;
	}
}
