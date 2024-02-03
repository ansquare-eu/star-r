package eu.ansquare.squarepowered.util;

import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.GameMode;

import java.util.*;

public class LocalizationHandler {
	private static Map<ServerPlayerEntity, ServerPlayerEntity> map = new HashMap<>();
	private static Map<ServerPlayerEntity, Vec3d> locations = new HashMap<>();
	private static Map<ServerPlayerEntity, ServerWorld> worlds = new HashMap<>();

	public static void tick(){
		List<ServerPlayerEntity> list = new ArrayList<>(map.size());
		map.forEach(((player, player2) -> {
			if(player.getServerWorld() != player2.getServerWorld()){
				list.add(player);
			} else if(player.distanceTo(player2) > 5f){
				list.add(player);
			}
		}));
		list.forEach(player -> cancel(player));
	}
	public static boolean begin(ServerPlayerEntity player, ServerPlayerEntity player2){
		if(map.containsKey(player)) return false;
		player.changeGameMode(GameMode.SPECTATOR);
		locations.put(player, player.getPos());
		worlds.put(player, player.getServerWorld());
		SquareMiscUtils.teleportTo(player, player2);
		map.put(player, player2);
		return true;
	}
	public static boolean end(ServerPlayerEntity player){
		if(!map.containsKey(player)) return false;
		player.changeGameMode(GameMode.SURVIVAL);
		locations.remove(player);
		worlds.remove(player);
		map.remove(player);
		return true;
	}
	public static boolean cancel(ServerPlayerEntity player){
		if(!map.containsKey(player)) return false;
		player.changeGameMode(GameMode.SURVIVAL);
		SquareMiscUtils.teleport(player, worlds.get(player), locations.get(player));
		locations.remove(player);
		worlds.remove(player);
		map.remove(player);
		return true;
	}
	public static boolean is(ServerPlayerEntity player){
		return map.containsKey(player);
	}
}
