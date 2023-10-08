package eu.ansquare.starr.network.s2c;

import eu.ansquare.starr.client.StarRClient;
import eu.ansquare.starr.util.network.ClientLaser;
import eu.ansquare.starr.util.network.ClientLaserHolder;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.network.ClientPlayNetworkHandler;
import net.minecraft.network.PacketByteBuf;
import net.minecraft.server.MinecraftServer;
import net.minecraft.server.network.ServerPlayNetworkHandler;
import net.minecraft.server.network.ServerPlayerEntity;
import org.quiltmc.qsl.networking.api.PacketSender;

public class RenderLaserPacket {
	public static void receive(MinecraftClient minecraftClient, ClientPlayNetworkHandler clientPlayNetworkHandler, PacketByteBuf packetByteBuf, PacketSender packetSender) {
		StarRClient.LASER_HOLDER.MAP.put(packetByteBuf.readUuid(), new ClientLaser(Integer.parseInt(packetByteBuf.readString())));
	}
}
