package eu.ansquare.starr.power.offense;

import eu.ansquare.starr.network.ModPackets;
import eu.ansquare.starr.power.Power;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.text.Text;
import org.quiltmc.qsl.networking.api.PacketByteBufs;
import org.quiltmc.qsl.networking.api.ServerPlayNetworking;

public class TornadoPower extends Power {
	@Override
	public void onActivate(ServerPlayerEntity player) {
		ServerPlayNetworking.send(player.getServerWorld().getPlayers(), ModPackets.SPAWN_PARTICLE_EFFECT, PacketByteBufs.create().writeString("tornado").writeUuid(player.getUuid()));

	}
}
