package eu.ansquare.starr.network.s2c;

import eu.ansquare.starr.client.particle.ParticleEffects;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.network.ClientPlayNetworkHandler;
import net.minecraft.client.network.ClientPlayerEntity;
import net.minecraft.client.world.ClientWorld;
import net.minecraft.network.PacketByteBuf;
import org.quiltmc.qsl.networking.api.PacketSender;

public class SpawnParticleEffectPacket {
	public static void receive(MinecraftClient minecraftClient, ClientPlayNetworkHandler clientPlayNetworkHandler, PacketByteBuf packetByteBuf, PacketSender packetSender) {
		String type = packetByteBuf.readString();
		ClientPlayerEntity player = minecraftClient.player;
		ParticleEffects.spawn(type, player.getX(), player.getY(), player.getZ(), (ClientWorld) player.getWorld());
	}

}
