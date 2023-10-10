package eu.ansquare.starr.items;

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

public class PalicaItem extends Item {
	public PalicaItem(Settings settings) {
		super(settings);
	}
	public TypedActionResult<ItemStack> use(World world, PlayerEntity user, Hand hand) {
		ItemStack stack = user.getStackInHand(hand);
		if (!world.isClient) {
			PalicaEntity entity = (PalicaEntity) ModEntities.PALICA.create(world);
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
