package eu.ansquare.starr.items;

import eu.ansquare.starr.entity.BoyshieldEntity;
import eu.ansquare.starr.entity.ModEntities;
import eu.ansquare.starr.entity.PalicaEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.projectile.PersistentProjectileEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.util.TypedActionResult;
import net.minecraft.world.World;

public class BoyshieldItem extends CustomShieldItem {
	public BoyshieldItem(Settings settings) {
		super(settings);
	}
	public TypedActionResult<ItemStack> use(World world, PlayerEntity user, Hand hand) {
		ItemStack stack = user.getStackInHand(hand);
		if (!world.isClient) {
			BoyshieldEntity entity = (BoyshieldEntity) ModEntities.BOYSHIELD.create(world);
			entity.maxDistance = 20;
			entity.setOwner(user);
			entity.setPosition(user.getEyePos());
			entity.setNoGravity(true);
			entity.pickupType = PersistentProjectileEntity.PickupPermission.ALLOWED;
			entity.setVelocity(user.getRotationVec(1).normalize().multiply(1.1));
			world.spawnEntity(entity);
		}
		if (!user.getAbilities().creativeMode) {
			stack.decrement(1);
		}
		return new TypedActionResult<>(ActionResult.SUCCESS, stack);
	}
}
