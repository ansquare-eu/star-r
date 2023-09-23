package eu.ansquare.starr.power;

import eu.ansquare.starr.network.ModPackets;
import net.minecraft.network.PacketByteBuf;
import net.minecraft.server.network.ServerPlayerEntity;
import org.quiltmc.qsl.networking.api.PacketByteBufs;
import org.quiltmc.qsl.networking.api.ServerPlayNetworking;

public class TeleportPower extends Power{
	@Override
	public String getName() {
		return "teleport";
	}

	@Override
	public void onActivate(ServerPlayerEntity player) {
		ServerPlayNetworking.send(player, ModPackets.SHOW_TELEPORT_SCREEN_PACKET_ID, PacketByteBufs.create());
	}
}
