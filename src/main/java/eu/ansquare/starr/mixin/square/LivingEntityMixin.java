package eu.ansquare.starr.mixin.square;

import eu.ansquare.squarepowered.power.PreventFreezingPower;
import io.github.apace100.apoli.component.PowerHolderComponent;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.world.World;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(LivingEntity.class)
public abstract class LivingEntityMixin extends Entity {
	public LivingEntityMixin(EntityType<?> variant, World world) {
		super(variant, world);
	}
	@Inject(method = "canFreeze", at = @At("TAIL"), cancellable = true)
	public void onCanFreeze(CallbackInfoReturnable<Boolean> cir){
		if(PowerHolderComponent.hasPower((LivingEntity)(Object) this, PreventFreezingPower.class)){
			cir.setReturnValue(false);
		}
	}
}
