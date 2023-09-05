package eu.ansquare.starr.entity;

import eu.ansquare.starr.StarR;
import net.minecraft.client.option.KeyBind;
import net.minecraft.entity.*;
import net.minecraft.entity.attribute.DefaultAttributeContainer;
import net.minecraft.entity.attribute.EntityAttributes;
import net.minecraft.entity.damage.DamageSource;
import net.minecraft.entity.data.DataTracker;
import net.minecraft.entity.data.TrackedData;
import net.minecraft.entity.data.TrackedDataHandlerRegistry;
import net.minecraft.entity.mob.MobEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.projectile.ProjectileEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.util.Arm;
import net.minecraft.util.Identifier;
import net.minecraft.world.EntityView;
import net.minecraft.world.LocalDifficulty;
import net.minecraft.world.ServerWorldAccess;
import net.minecraft.world.World;
import org.jetbrains.annotations.Nullable;

import java.util.Optional;
import java.util.UUID;

public class CapeEntity extends LivingEntity{
	private static final TrackedData<Optional<UUID>> HOST = DataTracker.registerData(CapeEntity.class, TrackedDataHandlerRegistry.OPTIONAL_UUID);
	private static Iterable<ItemStack> armorItems;
	public CapeEntity(EntityType<? extends LivingEntity> variant, World world) {
		super(variant, world);
	}
	@Override
	public void initDataTracker(){
		super.initDataTracker();
		this.dataTracker.startTracking(HOST, Optional.empty());
	}
	@Override
	public Iterable<ItemStack> getArmorItems() {
		return null;
	}

	@Override
	public ItemStack getEquippedStack(EquipmentSlot slot) {
		return this.activeItemStack;
	}

	@Override
	public void equipStack(EquipmentSlot slot, ItemStack stack) {

	}

	public void tick(){
		super.tick();
		if(this.dataTracker.get(HOST).isEmpty()){
			PlayerEntity player = this.getWorld().getClosestPlayer(this, 32);
			this.dataTracker.set(HOST, Optional.of(player.getUuid()));
		}
		else {
			PlayerEntity player = this.getWorld().getPlayerByUuid(this.dataTracker.get(HOST).get());
			this.setPos(player.getX(),player.getY(), player.getZ());
			this.setYaw(player.getYaw());
			this.setPitch(player.getPitch());
		}
	}

	public static DefaultAttributeContainer.Builder createJellyfishAttributes() {

		return MobEntity.createAttributes().add(EntityAttributes.GENERIC_ATTACK_KNOCKBACK, 0.0D).add(EntityAttributes.GENERIC_KNOCKBACK_RESISTANCE, 1d).add(EntityAttributes.GENERIC_MAX_HEALTH, 100.0D).add(EntityAttributes.GENERIC_MOVEMENT_SPEED, 0D);

	}
	@Override
	public void pushAway(Entity entity){

	}
	/*
	@Override
	public boolean damage(DamageSource source, float amount) {
		return false;
	}
	*/
	@Override
	public Arm getMainArm() {
		return Arm.RIGHT;
	}

}
