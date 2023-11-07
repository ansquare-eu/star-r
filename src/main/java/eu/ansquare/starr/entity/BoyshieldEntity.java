package eu.ansquare.starr.entity;

import eu.ansquare.starr.entity.projectile.ReturningProjectileEntity;
import eu.ansquare.starr.items.ModItems;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.projectile.PersistentProjectileEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;

public class BoyshieldEntity extends ReturningProjectileEntity {
	public BoyshieldEntity(EntityType<? extends PersistentProjectileEntity> entityType, World world) {
		super(entityType, world);
	}

	@Override
	public ItemStack asItemStack() {
		return new ItemStack(ModItems.BOYSHIELD, 1);
	}
}
