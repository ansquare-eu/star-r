package eu.ansquare.starr.entity;

import eu.ansquare.starr.entity.projectile.ReturningProjectileEntity;
import eu.ansquare.starr.items.ModItems;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.EquipmentSlot;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.attribute.DefaultAttributeContainer;
import net.minecraft.entity.attribute.EntityAttributes;
import net.minecraft.entity.data.DataTracker;
import net.minecraft.entity.data.TrackedData;
import net.minecraft.entity.data.TrackedDataHandlerRegistry;
import net.minecraft.entity.mob.MobEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.util.Arm;
import net.minecraft.world.World;

import java.util.Optional;
import java.util.UUID;

public class CapeEntity extends ReturningProjectileEntity {

	public CapeEntity(EntityType<? extends ReturningProjectileEntity> variant, World world) {
		super(variant, world);
	}


	@Override
	protected ItemStack asItemStack() {
		return new ItemStack(ModItems.CAPE);
	}


}
