package eu.ansquare.starr.client.particle;

import net.minecraft.client.particle.*;
import net.minecraft.client.world.ClientWorld;
import net.minecraft.particle.DefaultParticleType;
import org.jetbrains.annotations.Nullable;

public class TornadoParticle extends SpriteBillboardParticle {
	protected TornadoParticle(ClientWorld clientWorld, double d, double e, double f) {
		super(clientWorld, d, e, f);
		this.maxAge = 100;
	}
	public void tick() {
		this.scale = 2;
		if (this.age++ >= this.maxAge) {
			this.markDead();
		}
	}
	public ParticleTextureSheet getType() {
		return ParticleTextureSheet.PARTICLE_SHEET_TRANSLUCENT;
	}
	public static class Factory implements ParticleFactory<DefaultParticleType> {
		private final SpriteProvider spriteProvider;

		public Factory(SpriteProvider spriteProvider) {
			this.spriteProvider = spriteProvider;
		}

		public Particle createParticle(DefaultParticleType particleEffect, ClientWorld clientWorld, double x, double y, double z, double g, double h, double i) {
			return new TornadoParticle(clientWorld, x, y, z);
		}
	}
}
