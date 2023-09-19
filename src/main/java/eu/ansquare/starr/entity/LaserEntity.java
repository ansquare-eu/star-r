package eu.ansquare.starr.entity;

import eu.ansquare.starr.StarR;
import eu.ansquare.starr.cca.StarREntityComponents;
import eu.ansquare.starr.power.LaserPower;
import eu.ansquare.starr.power.Power;
import eu.ansquare.starr.power.Powers;
import eu.ansquare.starr.superdude.PowerOrder;
import eu.ansquare.starr.superdude.SuperDude;
import eu.ansquare.starr.util.datasaving.IDataSaver;
import net.minecraft.entity.*;
import net.minecraft.entity.data.DataTracker;
import net.minecraft.entity.data.TrackedData;
import net.minecraft.entity.data.TrackedDataHandlerRegistry;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.util.hit.BlockHitResult;
import net.minecraft.util.math.Vec3d;
import net.minecraft.util.shape.VoxelShape;
import net.minecraft.world.LocalDifficulty;
import net.minecraft.world.RaycastContext;
import net.minecraft.world.ServerWorldAccess;
import net.minecraft.world.World;
import org.jetbrains.annotations.Nullable;

import java.awt.*;
import java.util.Optional;
import java.util.UUID;

public class LaserEntity extends Entity implements Ownable {
	private static final TrackedData<Integer> LENGHT = DataTracker.registerData(LaserEntity.class, TrackedDataHandlerRegistry.INTEGER);
	private static final TrackedData<Optional<UUID>> OWNER_UUID = DataTracker.registerData(LaserEntity.class, TrackedDataHandlerRegistry.OPTIONAL_UUID);

	@Nullable
	private Entity owner;
	private int ownerlessTicks = -1;
	private int lenght = 1;


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
		this.dataTracker.startTracking(LENGHT, 1);
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
	public void tick(){
		super.tick();
		if(!Powers.TEST_LASER_POWER.entityMap.containsValue(this)) {
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

			this.setPitch(this.getOwner().getPitch());
			this.setYaw((this.getOwner().getHeadYaw() * -1) + 180);
			this.setPosition(this.getOwner().getEyePos());
			/*Vec3d playerDir = this.getRotationVec(1f).normalize().multiply(32);
			Vec3d newLoc = this.getPos().add(playerDir);
			BlockHitResult result = this.getWorld().raycast(new RaycastContext(this.getPos(), newLoc, RaycastContext.ShapeType.VISUAL, RaycastContext.FluidHandling.ANY, this));
			if(result != null){
				double distance = result.getPos().distanceTo(this.getPos());
				StarR.LOGGER.info("Ceil distance = " + distance);
				lenght = (int) Math.ceil(distance);
				StarR.LOGGER.info("Int distance = " + lenght);
				this.dataTracker.set(LENGHT, lenght);
			}*/
		}
	}
	public int getLenght(){

		return this.dataTracker.get(LENGHT);
	}
	public Color getColor(){
		if(this.getOwner() != null){
			StarR.LOGGER.info("get color");
			if(StarREntityComponents.SUPER_DUDE_COMPONENT.maybeGet(this.getOwner()).isPresent()){
				return StarREntityComponents.SUPER_DUDE_COMPONENT.get(this.getOwner()).getCustomColor();
			}
		}
		return new Color(0xFF0059);
	}
}
