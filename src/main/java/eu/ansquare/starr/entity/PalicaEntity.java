package eu.ansquare.starr.entity;

import eu.ansquare.starr.items.ModItems;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.projectile.PersistentProjectileEntity;
import net.minecraft.entity.projectile.ProjectileEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.particle.ParticleTypes;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;

public class PalicaEntity extends PersistentProjectileEntity {
	int ticksUntilRemoval = -1;
	public PalicaEntity(EntityType<? extends PersistentProjectileEntity> entityType, World world) {
		super(entityType, world);
	}
	public void tick(){
		super.tick();

		if (this.inGround) {
			if (this.ticksUntilRemoval == -1) {
				this.ticksUntilRemoval = 2;
			}

			if (this.ticksUntilRemoval > 0) {
				this.ticksUntilRemoval--;
				if (this.ticksUntilRemoval <= 0) {
					this.remove(RemovalReason.DISCARDED);
				}
			}
		} /*else {
			Vec3d vec3d = this.getVelocity();
			double e = vec3d.x;
			double f = vec3d.y;
			double g = vec3d.z;
			double l = vec3d.horizontalLength();
			this.setPitch((float)(MathHelper.atan2(f, l) * 57.2957763671875));
			this.setPitch(updateRotation(this.prevPitch, this.getPitch()));
			this.setYaw(updateRotation(this.prevYaw, this.getYaw()));
		}

		 Will be kept until captain r coding
		if (this.age < 10) {
			for (LivingEntity livingEntity : world.getEntitiesByClass(LivingEntity.class, this.getBoundingBox().expand(1f), LivingEntity::isAlive)) {
				this.onEntityHit(new EntityHitResult(livingEntity));
				this.kill();
			}
		}*/
	}
	@Override
	protected ItemStack asItemStack() {
		return new ItemStack(ModItems.PALICA, 1);
	}
}
