package eu.ansquare.starr.client.particle;

import com.mojang.serialization.Codec;
import com.sammy.lodestone.systems.rendering.particle.world.WorldParticleEffect;
import net.fabricmc.fabric.impl.client.particle.FabricSpriteProviderImpl;
import net.minecraft.client.particle.FlameParticle;
import net.minecraft.client.particle.Particle;
import net.minecraft.client.particle.ParticleFactory;
import net.minecraft.client.particle.SpriteProvider;
import net.minecraft.client.world.ClientWorld;
import net.minecraft.particle.ParticleEffect;
import net.minecraft.particle.ParticleType;

public class LaserParticleType extends ParticleType<WorldParticleEffect> {
	public LaserParticleType() {
		super(false, WorldParticleEffect.DESERIALIZER);
	}

	@Override
	public Codec<WorldParticleEffect> getCodec() {
		return WorldParticleEffect.codecFor(this);
	}
	public record LaserParticleFactory(SpriteProvider sprite) implements ParticleFactory<WorldParticleEffect> {
		@Override
		public Particle createParticle(WorldParticleEffect data, ClientWorld world, double x, double y, double z, double mx, double my, double mz) {
			return new LaserParticle(world, data, (FabricSpriteProviderImpl) sprite, x, y, z, mx, my, mz);
		}
	}
}
