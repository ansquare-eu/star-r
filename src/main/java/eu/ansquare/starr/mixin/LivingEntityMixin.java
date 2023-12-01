package eu.ansquare.starr.mixin;

import eu.ansquare.starr.cca.StarREntityComponents;
import eu.ansquare.starr.superdude.SuperDude;
import eu.ansquare.starr.superdude.SuperDudes;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.world.World;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(LivingEntity.class)
public abstract class LivingEntityMixin extends Entity {

	public LivingEntityMixin(EntityType<?> variant, World world) {
		super(variant, world);
	}

	@Inject(method = "baseTick", at = @At("HEAD"))
	public void tick(CallbackInfo ci) {
		if (!this.getWorld().isClient()) {
			StarREntityComponents.SUPER_DUDE_COMPONENT.maybeGet((LivingEntity) ((Object) this)).ifPresent(superDudeComponent -> superDudeComponent.getType().executeActiveTicks((LivingEntity) ((Object) this)));
		}
	}
	@Inject(method = "canFreeze", at = @At("TAIL"), cancellable = true)
	public void onCanFreeze(CallbackInfoReturnable<Boolean> cir){
		if(SuperDudes.isEntityOf((LivingEntity) (Object) this, SuperDudes.SKIER)){
			cir.setReturnValue(false);
		}
	}
}
