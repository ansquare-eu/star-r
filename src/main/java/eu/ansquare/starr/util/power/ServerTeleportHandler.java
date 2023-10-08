package eu.ansquare.starr.util.power;

import eu.ansquare.starr.cca.StarREntityComponents;
import net.minecraft.server.network.ServerPlayerEntity;

public class ServerTeleportHandler {
	public static void teleport(ServerPlayerEntity player, int i){
		if(StarREntityComponents.TELEPORT_LOC_COMPONENT.maybeGet(player).isPresent()){
			int[] loc = StarREntityComponents.TELEPORT_LOC_COMPONENT.get(player).getLoc(i);
			if(loc.length >= 3){
				teleport(player, loc[0], loc[1], loc[2]);
			}
		}

	}
	public static void teleport(ServerPlayerEntity player, int x, int y, int z){
		player.teleport(x, y , z);
	}
}
