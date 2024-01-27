package eu.ansquare.starr.mixin;

import eu.ansquare.squarepowered.power.PreventFreezingPower;
import io.github.apace100.apoli.component.PowerHolderComponent;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.ItemEntity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(Entity.class)
public abstract class EntityMixin {

	@Inject(method = "dropStack(Lnet/minecraft/item/ItemStack;)Lnet/minecraft/entity/ItemEntity;", at = @At("HEAD"), cancellable = true)
	public void onDropStack(ItemStack stack, CallbackInfoReturnable<ItemEntity> cir){
		if(stack.getOrCreateNbt().getBoolean("no_drop")){
			cir.setReturnValue(null);
		}
	}
}
