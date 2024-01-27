package eu.ansquare.starr.mixin;

import eu.ansquare.squarepowered.cca.MultiInventoryComponent;
import net.minecraft.entity.Entity;
import net.minecraft.entity.ItemEntity;
import net.minecraft.entity.decoration.ItemFrameEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemFrameItem;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;
import org.spongepowered.asm.mixin.injection.callback.LocalCapture;

@Mixin(ItemFrameEntity.class)
public abstract class ItemFrameEntityMixin {

	@Inject(method = "interact", at = @At(value = "INVOKE", target = "Lnet/minecraft/entity/decoration/ItemFrameEntity;setHeldItemStack(Lnet/minecraft/item/ItemStack;)V"), cancellable = true, locals = LocalCapture.CAPTURE_FAILHARD)
	public void onDropStack(PlayerEntity player, Hand hand, CallbackInfoReturnable<ActionResult> cir, ItemStack itemStack){
		if(MultiInventoryComponent.isNoDrop(player)){
			itemStack.getOrCreateNbt().putBoolean("no_drop", true);
		}
	}
}
