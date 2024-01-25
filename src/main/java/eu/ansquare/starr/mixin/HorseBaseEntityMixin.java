package eu.ansquare.starr.mixin;

import eu.ansquare.squarepowered.cca.MultiInventoryComponent;
import net.minecraft.entity.*;
import net.minecraft.entity.passive.AnimalEntity;
import net.minecraft.entity.passive.HorseBaseEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.inventory.InventoryChangedListener;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.world.World;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(HorseBaseEntity.class)
public abstract class HorseBaseEntityMixin extends AnimalEntity implements InventoryChangedListener, RideableOpenableInventory, Tameable, JumpingMount, Saddleable {
	@Shadow
	public abstract boolean isTame();

	protected HorseBaseEntityMixin(EntityType<? extends AnimalEntity> entityType, World world) {
		super(entityType, world);
	}
	@Inject(method = "interactMob", at = @At("HEAD"), cancellable = true)
	public void squarepowered_onInteract(PlayerEntity player, Hand hand, CallbackInfoReturnable<ActionResult> cir){
		if(MultiInventoryComponent.isPreventTransfer(player)){
		if (!this.hasPassengers() && !this.isBaby()) {
			if (this.isTame()) {
				cir.setReturnValue(ActionResult.success(this.getWorld().isClient));
			}
		}}
	}
}
