package eu.ansquare.starr.network.s2c;

import eu.ansquare.starr.StarR;
import eu.ansquare.starr.client.StarRClient;
import eu.ansquare.starr.client.particle.ParticleEffects;
import eu.ansquare.starr.util.network.ClientPlayerState;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.network.ClientPlayNetworkHandler;
import net.minecraft.client.network.ClientPlayerEntity;
import net.minecraft.client.world.ClientWorld;
import net.minecraft.network.PacketByteBuf;
import org.quiltmc.qsl.networking.api.PacketSender;

public class TogglePlayerStatePacket {
	public static void receive(MinecraftClient minecraftClient, ClientPlayNetworkHandler clientPlayNetworkHandler, PacketByteBuf packetByteBuf, PacketSender packetSender) {
		StarRClient.LASER_HOLDER.toggle(packetByteBuf.readEnumConstant(ClientPlayerState.class), packetByteBuf.readUuid());
	}
}
