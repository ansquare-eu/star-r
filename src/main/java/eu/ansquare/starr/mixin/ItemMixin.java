package eu.ansquare.starr.mixin;

import eu.ansquare.starr.entity.HoneyballEntity;
import eu.ansquare.starr.entity.SlimeballEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.util.Hand;
import net.minecraft.util.TypedActionResult;
import net.minecraft.world.World;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(Item.class)
public class ItemMixin {
	@Inject(method = "use", at = @At("HEAD"), cancellable = true)
	public void starr_throwSlime(World world, PlayerEntity user, Hand hand, CallbackInfoReturnable<TypedActionResult<ItemStack>> cir) {
		ItemStack stack = user.getStackInHand(hand);
		if(!world.isClient){
			if(stack.isOf(Items.SLIME_BALL)){
				SlimeballEntity entity = new SlimeballEntity(world, user);
				entity.setItem(stack);
				entity.setProperties(user, user.getPitch(), user.getYaw(), 0.0F, 1.5F, 1.0F);
				world.spawnEntity(entity);
			}
		}
	}
}
