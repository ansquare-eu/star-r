package eu.ansquare.starr.entity;

import eu.ansquare.starr.items.ModItems;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.data.DataTracker;
import net.minecraft.entity.data.TrackedData;
import net.minecraft.entity.data.TrackedDataHandlerRegistry;
import net.minecraft.entity.projectile.PersistentProjectileEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.world.World;

public class PalicaEntity extends ReturningProjectileEntity {
	private static final TrackedData<String> TYPE = DataTracker.registerData(PalicaEntity.class, TrackedDataHandlerRegistry.STRING);

	public PalicaEntity(EntityType<? extends PersistentProjectileEntity> entityType, World world) {
		super(entityType, world);
	}
	protected void initDataTracker(){
		super.initDataTracker();
		this.dataTracker.startTracking(TYPE, Type.CAPTAIN_R.toString());
	}

	public void tick(){
		super.tick();

/*		if (this.inGround) {
			if (this.ticksUntilGroundRemoval == -1) {
				this.ticksUntilGroundRemoval = 2;
			}

			if (this.ticksUntilGroundRemoval > 0) {
				this.ticksUntilGroundRemoval--;
				if (this.ticksUntilGroundRemoval <= 0) {
					this.remove(RemovalReason.DISCARDED);
				}
			}
			this.retrieve();
		}
		if(ticksUntilRetrieval > 0) {
			this.ticksUntilRetrieval--;
			if(this.ticksUntilRetrieval <= 0){
				if(this.getOwner() instanceof PlayerEntity){
					this.tryPickup(((PlayerEntity) this.getOwner()));
					this.remove(RemovalReason.DISCARDED);
				}
			}
		}*//*else {
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
	public void readCustomDataFromNbt(NbtCompound tag) {
		super.readCustomDataFromNbt(tag);

		if (tag.contains("CaptainType")) {
			this.setCaptainType(Type.valueOf(tag.getString("CaptainType")));
		}
	}
	public Type getCaptainType() {
		return Type.valueOf(this.dataTracker.get(TYPE));
	}

	public void setCaptainType(Type type) {
		this.dataTracker.set(TYPE, type.toString());
	}

	@Override
	public void writeCustomDataToNbt(NbtCompound tag) {
		super.writeCustomDataToNbt(tag);
		tag.putString("CaptainType", this.getCaptainType().toString());
	}
	public enum Type{
		CAPTAIN_R,
		CAPTAIN_D
	}
}
