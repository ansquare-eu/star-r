package eu.ansquare.starr.util.power;

import eu.ansquare.starr.StarR;
import eu.ansquare.starr.blocks.WorldAnchorBlock;
import eu.ansquare.starr.cca.StarREntityComponents;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.text.Text;
import net.minecraft.util.math.BlockPos;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

public class ServerTeleportHandler {
	private static Map<ServerPlayerEntity, ServerPlayerEntity> tasks = new HashMap<>();
	public static void addTask(ServerPlayerEntity teleporter, ServerPlayerEntity victim){
		tasks.put(teleporter, victim);
	}
	public static void removeTask(ServerPlayerEntity teleporter){
		tasks.remove(teleporter);
	}
	public static void teleport(ServerPlayerEntity player, int i){
		if(StarREntityComponents.TELEPORT_LOC_COMPONENT.maybeGet(player).isPresent()){
			int[] loc = StarREntityComponents.TELEPORT_LOC_COMPONENT.get(player).getLoc(i);
			if(loc.length >= 3){
				teleport(player, loc[0], loc[1], loc[2]);
			}
		}

	}
	public static void teleport(ServerPlayerEntity player, int x, int y, int z){
		ServerPlayerEntity finalplayer;
		if(tasks.containsKey(player)){
			finalplayer = tasks.get(player);
		} else {
			finalplayer = player;
		}
		removeTask(player);
		if(WorldAnchorBlock.isInVicinity(true, new BlockPos(x, y ,z), player.getWorld())){
			player.sendMessage(Text.translatable("message.starr.anchored.destination"), true);
			return;
		} else if(WorldAnchorBlock.isInVicinity(true, finalplayer.getBlockPos(), player.getWorld())){
			player.sendMessage(Text.translatable("message.starr.anchored.origin"), true);
			return;
		}
		finalplayer.teleport(x, y , z);
	}
}
