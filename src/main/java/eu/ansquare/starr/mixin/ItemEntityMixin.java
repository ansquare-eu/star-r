package eu.ansquare.starr.mixin;

import eu.ansquare.squarepowered.cca.MultiInventoryComponent;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.ItemEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.world.World;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(ItemEntity.class)
public abstract class ItemEntityMixin extends Entity {
	public ItemEntityMixin(EntityType<?> variant, World world) {
		super(variant, world);
	}

	@Inject(method = "onPlayerCollision", at = @At("HEAD"), cancellable = true)
	public void squarepowered_onPlayerCollision(PlayerEntity player, CallbackInfo ci) {
		if(this.getWorld() instanceof ServerWorld){
			if(MultiInventoryComponent.isPreventTransfer(player)) ci.cancel();
		}
	}
}
