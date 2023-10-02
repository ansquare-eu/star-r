package eu.ansquare.starr.network;

import eu.ansquare.starr.client.StarRClient;
import eu.ansquare.starr.client.screen.TeleportSelectScreen;
import eu.ansquare.starr.util.network.ClientLaser;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.network.ClientPlayNetworkHandler;
import net.minecraft.network.PacketByteBuf;
import net.minecraft.text.Text;
import org.quiltmc.qsl.networking.api.PacketSender;

public class ShowTeleportScreenPacket {
	public static void receive(MinecraftClient minecraftClient, ClientPlayNetworkHandler clientPlayNetworkHandler, PacketByteBuf packetByteBuf, PacketSender packetSender) {
	}
}
