package eu.ansquare.starr.network.s2c;

import eu.ansquare.starr.client.StarRClient;
import eu.ansquare.starr.util.network.ClientLaser;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.network.ClientPlayNetworkHandler;
import net.minecraft.network.PacketByteBuf;
import org.quiltmc.qsl.networking.api.PacketSender;

import java.util.UUID;

public class RenderLaserPacket {
	public static void receive(MinecraftClient minecraftClient, ClientPlayNetworkHandler clientPlayNetworkHandler, PacketByteBuf packetByteBuf, PacketSender packetSender) {
		UUID uuid = packetByteBuf.readUuid();
		int color = packetByteBuf.readInt();
		float x = packetByteBuf.readFloat();
		float y = packetByteBuf.readFloat();
		minecraftClient.execute(() -> StarRClient.LASER_HOLDER.LASER_MAP.put(uuid, new ClientLaser(color, x, y)));
	}
}
