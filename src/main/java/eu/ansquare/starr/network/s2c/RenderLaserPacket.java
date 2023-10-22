package eu.ansquare.starr.network.s2c;

import eu.ansquare.starr.client.StarRClient;
import eu.ansquare.starr.util.network.ClientLaser;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.network.ClientPlayNetworkHandler;
import net.minecraft.network.PacketByteBuf;
import org.quiltmc.qsl.networking.api.PacketSender;

public class RenderLaserPacket {
	public static void receive(MinecraftClient minecraftClient, ClientPlayNetworkHandler clientPlayNetworkHandler, PacketByteBuf packetByteBuf, PacketSender packetSender) {
		StarRClient.LASER_HOLDER.MAP.put(packetByteBuf.readUuid(), new ClientLaser(Integer.parseInt(packetByteBuf.readString()), packetByteBuf.readFloat(), packetByteBuf.readDouble()));
	}
	//Integer.parseInt(packetByteBuf.readString())
}
