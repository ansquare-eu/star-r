package eu.ansquare.starr.entity;

import eu.ansquare.starr.items.ModItems;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.entity.effect.StatusEffects;
import net.minecraft.entity.mob.BlazeEntity;
import net.minecraft.entity.projectile.thrown.ThrownItemEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.particle.ItemStackParticleEffect;
import net.minecraft.particle.ParticleEffect;
import net.minecraft.particle.ParticleTypes;
import net.minecraft.util.hit.EntityHitResult;
import net.minecraft.util.hit.HitResult;
import net.minecraft.world.World;

public class HoneyballEntity extends ThrownItemEntity {
	public HoneyballEntity(EntityType<? extends ThrownItemEntity> entityType, World world) {
		super(entityType, world);
	}
	public HoneyballEntity(World world, LivingEntity owner) {
		super(ModEntities.HONEYBALL, owner, world);
	}

	public HoneyballEntity(World world, double x, double y, double z) {
		super(ModEntities.HONEYBALL, x, y, z, world);
	}
	@Override
	protected Item getDefaultItem() {
		return ModItems.HONEYBALL;
	}
	private ParticleEffect getParticleParameters() {
		ItemStack itemStack = this.getItem();
		return (ParticleEffect)(itemStack.isEmpty() ? new ItemStackParticleEffect(ParticleTypes.ITEM, new ItemStack(getDefaultItem())) : new ItemStackParticleEffect(ParticleTypes.ITEM, itemStack));
	}

	public void handleStatus(byte status) {
		if (status == 3) {
			ParticleEffect particleEffect = this.getParticleParameters();

			for(int i = 0; i < 8; ++i) {
				this.getWorld().addParticle(particleEffect, this.getX(), this.getY(), this.getZ(), 0.0, 0.0, 0.0);
			}
		}

	}

	protected void onEntityHit(EntityHitResult entityHitResult) {
		super.onEntityHit(entityHitResult);
		Entity entity = entityHitResult.getEntity();
		if(entity instanceof LivingEntity livingEntity){
			livingEntity.addStatusEffect(new StatusEffectInstance(StatusEffects.SLOWNESS, 15 * 20, 1));
		}
	}

	protected void onCollision(HitResult hitResult) {
		super.onCollision(hitResult);
		if (!this.getWorld().isClient) {
			this.getWorld().sendEntityStatus(this, (byte)3);
			this.discard();
		}

	}
}
