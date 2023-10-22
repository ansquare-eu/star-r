package eu.ansquare.starr.client.particle;

import eu.ansquare.starr.items.ModItems;
import net.fabricmc.fabric.api.particle.v1.FabricParticleTypes;
import net.minecraft.client.world.ClientWorld;
import net.minecraft.particle.ParticleEffect;
import net.minecraft.util.math.Vec3d;

public class ParticleEffects {
	public static void spawn(String type, double x, double y, double z, ClientWorld world){
		switch (type){
			case "tornado":
				spawnTornado(x, y ,z ,world);
				break;
		}
	}
	public static void spawnTornado(double x, double y, double z, ClientWorld world){
		world.addParticle(ModParticles.TORNADO, x - 1, y, z - 1, 0, 0, 0);
		world.addParticle(ModParticles.TORNADO, x + 1, y, z - 1, 0, 0, 0);
		world.addParticle(ModParticles.TORNADO, x - 1, y, z + 1, 0, 0, 0);
		world.addParticle(ModParticles.TORNADO, x + 1, y, z + 1, 0, 0, 0);

	}
}
