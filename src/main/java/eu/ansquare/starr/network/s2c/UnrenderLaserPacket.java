package eu.ansquare.starr.network.s2c;

import eu.ansquare.starr.client.StarRClient;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.network.ClientPlayNetworkHandler;
import net.minecraft.network.PacketByteBuf;
import org.quiltmc.qsl.networking.api.PacketSender;

import java.util.UUID;

public class UnrenderLaserPacket {
	public static void receive(MinecraftClient minecraftClient, ClientPlayNetworkHandler clientPlayNetworkHandler, PacketByteBuf packetByteBuf, PacketSender packetSender) {
		UUID uuid = packetByteBuf.readUuid();
		minecraftClient.execute(() -> StarRClient.LASER_HOLDER.LASER_MAP.remove(uuid));
	}
}
