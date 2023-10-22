package eu.ansquare.starr.power;

import eu.ansquare.starr.network.ModPackets;
import net.minecraft.server.network.ServerPlayerEntity;
import org.quiltmc.qsl.networking.api.PacketByteBufs;
import org.quiltmc.qsl.networking.api.ServerPlayNetworking;

public class TornadoPower extends Power{
	@Override
	public void onActivate(ServerPlayerEntity player) {
		ServerPlayNetworking.send(player.getServerWorld().getPlayers(), ModPackets.SPAWN_PARTICLE_EFFECT, PacketByteBufs.create().writeString("tornado"));
	}
}
