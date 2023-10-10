package eu.ansquare.starr.entity;

import eu.ansquare.starr.StarR;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.Ownable;
import net.minecraft.entity.data.DataTracker;
import net.minecraft.entity.data.TrackedData;
import net.minecraft.entity.data.TrackedDataHandlerRegistry;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.network.PacketByteBuf;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.world.World;
import org.jetbrains.annotations.Nullable;
import org.quiltmc.qsl.entity.networking.api.extended_spawn_data.QuiltExtendedSpawnDataEntity;

import java.awt.*;
import java.util.Optional;
import java.util.UUID;

public class LaserEntity extends Entity implements Ownable, QuiltExtendedSpawnDataEntity {
	private static final TrackedData<Integer> COLOR = DataTracker.registerData(LaserEntity.class, TrackedDataHandlerRegistry.INTEGER);
	private static final TrackedData<Optional<UUID>> OWNER_UUID = DataTracker.registerData(LaserEntity.class, TrackedDataHandlerRegistry.OPTIONAL_UUID);

	@Nullable
	private Entity owner;
	private int ownerlessTicks = -1;


	public LaserEntity(EntityType<?> variant, World world) {
		super(variant, world);
	}
	public void setOwner(@Nullable Entity entity) {
		if (entity != null) {
			this.owner = entity;
			this.dataTracker.set(OWNER_UUID, Optional.of(entity.getUuid()));
		}

	}

	@Nullable
	public Entity getOwner() {
		if (this.owner != null && !this.owner.isRemoved()) {
			StarR.LOGGER.info("has owener");
			return this.owner;
		} else if (this.dataTracker.get(OWNER_UUID).isPresent() && this.getWorld() instanceof ServerWorld) {
			StarR.LOGGER.info("has owener uuid");
			this.owner = ((ServerWorld)this.getWorld()).getEntity(this.dataTracker.get(OWNER_UUID).get());
			return this.owner;
		} else {
			StarR.LOGGER.info("has no owener");
			return null;
		}
	}
	@Override
	protected void initDataTracker() {
		this.dataTracker.startTracking(COLOR, 0xFFFFFF);

		this.dataTracker.startTracking(OWNER_UUID, Optional.empty());
	}

	@Override
	protected void readCustomDataFromNbt(NbtCompound nbt) {

	}
	public void delete(){
		this.remove(RemovalReason.DISCARDED);
	}
	@Override
	protected void writeCustomDataToNbt(NbtCompound nbt) {

	}
	/*public void tick(){
		super.tick();
		if((!this.getWorld().isClient() && !Powers.TEST_LASER_POWER.entityMap.containsValue(this)) || (this.getWorld().isClient() && !StarRClient.LASER_HOLDER.SET.contains(this))) {
			if (this.getOwner() != null) {
				this.ownerlessTicks = -1;
				this.setPitch(this.getOwner().getPitch());
				this.setYaw((this.getOwner().getHeadYaw() * -1) + 180);
				this.setPosition(this.getOwner().getEyePos());
			} else {
				if (ownerlessTicks == -1) {

					ownerlessTicks = 40;
				}
				if (this.ownerlessTicks > 0) {
					this.ownerlessTicks--;
					if (this.ownerlessTicks <= 0) {

						this.delete();
					}
				}
			}
		} else if (this.getOwner() != null) {
			@Nullable Predicate<Entity> filter = entity -> {
				if(entity.getType() == EntityType.PLAYER){
					return false;
				}
				else {
					return true;
				}
			};
			this.setPitch(this.getOwner().getPitch());
			this.setYaw(this.getOwner().getHeadYaw());
			this.setPosition(this.getOwner().getEyePos().add(0, -1, 0));
			float damage = StarREntityComponents.SUPER_DUDE_COMPONENT.maybeGet(this.getOwner()).map(SuperDudeComponent::getLaserPower).orElse(0);
			Double distance = 64d;
			Vec3d playerDir = this.getOwner().getRotationVec(1f).normalize().multiply(64);
			//BlockHitResult trace = player.getWorld().raycastBlock(player.getEyePos(), player.getRotationVec(1), 128, 1, filter);
			EntityHitResult trace = ProjectileUtil.raycast(this.getOwner(), this.getOwner().getEyePos(), this.getOwner().getEyePos().add(playerDir), Box.of(this.getOwner().getEyePos(), 1, 1, 1).expand(distance), filter, distance*distance*distance);
			if(trace != null){
				trace.getEntity().damage(trace.getEntity().getDamageSources().playerAttack(((PlayerEntity) this.getOwner())), damage);
			}
			*//*BlockHitResult result = this.getWorld().raycast(new RaycastContext(this.getPos(), newLoc, RaycastContext.ShapeType.VISUAL, RaycastContext.FluidHandling.ANY, this));
			if(result != null){
				double distance = result.getPos().distanceTo(this.getPos());
				StarR.LOGGER.info("Ceil distance = " + distance);
				lenght = (int) Math.ceil(distance);
				StarR.LOGGER.info("Int distance = " + lenght);
				this.dataTracker.set(LENGHT, lenght);
			}*//*
		}

	}*/
	public void setColor(Color color){
		this.dataTracker.set(COLOR, color.getRGB());
	}
	public Color getColor(){
		return new Color(this.dataTracker.get(COLOR));
	}

	@Override
	public void writeAdditionalSpawnData(PacketByteBuf buffer) {
		if(this.dataTracker.get(OWNER_UUID).isPresent()){
			buffer.writeUuid(this.dataTracker.get(OWNER_UUID).get());
		}
	}

	@Override
	public void readAdditionalSpawnData(PacketByteBuf buffer) {
		this.dataTracker.set(OWNER_UUID, Optional.of(buffer.readUuid()));
	}
}
