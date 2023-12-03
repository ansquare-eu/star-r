package eu.ansquare.starr.client.particle;

import net.minecraft.client.particle.*;
import net.minecraft.client.world.ClientWorld;
import net.minecraft.particle.DefaultParticleType;
import org.jetbrains.annotations.Nullable;

public class TornadoParticle extends SpriteBillboardParticle {
	private final SpriteProvider spriteProvider;
	private final double velX;
	private final double velY;
	private final double velZ;

	protected TornadoParticle(ClientWorld clientWorld, double x, double y, double z, double i, double j , double k, SpriteProvider provider) {
		super(clientWorld, x, y, z);
		this.spriteProvider = provider;
		this.setSpriteForAge(provider);
		this.maxAge = 100;
		this.velX = i;
		this.velY = j;
		this.velZ = k;
	}
	public void tick() {
		this.scale = 2;
		this.prevPosX = this.x;
		this.prevPosY = this.y;
		this.prevPosZ = this.z;
		this.move(velX, velY, velZ);
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
			return new TornadoParticle(clientWorld, x, y, z, g, h, i, spriteProvider);
		}
	}
}
