package eu.ansquare.starr.power.offense;

import eu.ansquare.starr.client.particle.ModParticles;
import eu.ansquare.starr.network.ModPackets;
import eu.ansquare.starr.power.Power;
import net.minecraft.particle.ParticleEffect;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.text.Text;
import org.quiltmc.qsl.networking.api.PacketByteBufs;
import org.quiltmc.qsl.networking.api.ServerPlayNetworking;

public class TornadoPower extends Power {
	@Override
	public void onActivate(ServerPlayerEntity player) {
		player.getServerWorld().spawnParticles(ModParticles.TORNADO, player.getX(), player.getY(), player.getZ(),
				0, 1, 0, 0,
				100);
	}
}
