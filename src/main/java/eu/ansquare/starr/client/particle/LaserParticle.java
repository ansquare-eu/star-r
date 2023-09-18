package eu.ansquare.starr.client.particle;

import com.sammy.lodestone.systems.rendering.particle.world.GenericParticle;
import com.sammy.lodestone.systems.rendering.particle.world.WorldParticleEffect;
import net.fabricmc.fabric.impl.client.particle.FabricSpriteProviderImpl;
import net.minecraft.client.world.ClientWorld;
import net.minecraft.world.RaycastContext;

public class LaserParticle extends GenericParticle {
	public LaserParticle(ClientWorld world, WorldParticleEffect data, FabricSpriteProviderImpl spriteProvider, double x, double y, double z, double velocityX, double velocityY, double velocityZ) {
		super(world, data, spriteProvider, x, y, z, velocityX, velocityY, velocityZ);
		setSprite(random.nextInt(3));
	}
}
