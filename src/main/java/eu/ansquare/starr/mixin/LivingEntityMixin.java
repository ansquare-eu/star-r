package eu.ansquare.starr.mixin;

import eu.ansquare.starr.power.Power;
import eu.ansquare.starr.power.ToggleablePower;
import eu.ansquare.starr.superdude.SuperDude;
import eu.ansquare.starr.util.datasaving.IDataSaver;
import eu.ansquare.starr.util.datasaving.SuperdudeDataManager;
import eu.ansquare.starr.superdude.PowerOrder;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.world.World;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(LivingEntity.class)
public abstract class LivingEntityMixin extends Entity {
	@Shadow
	public abstract boolean canHaveStatusEffect(StatusEffectInstance effect);

	public LivingEntityMixin(EntityType<?> variant, World world) {
		super(variant, world);
	}

	@Inject(method = "baseTick", at = @At("HEAD"))
	public void tick(CallbackInfo ci) {
		if (!this.getWorld().isClient()) {
			if (SuperdudeDataManager.get(((IDataSaver) this)) != null) {
				SuperDude superDude = SuperdudeDataManager.get(((IDataSaver) this));
				for (PowerOrder powerOrd : superDude.getPowers().keySet()) {
					Power power = superDude.getPower(powerOrd);
					if (power instanceof ToggleablePower) {
						if (((ToggleablePower) power).isActiveFor(this.getUuid())) {
							((ToggleablePower) power).activeTick((LivingEntity) ((Object) this));
						}
					}
				}
			}
		}
	}
	@Inject(method = "isFallFlying", at = @At("HEAD"), cancellable = true)
	public void onIsFallFlying(CallbackInfoReturnable<Boolean> cir){
		if(!this.getWorld().isClient()){
			if(SuperdudeDataManager.get(((IDataSaver) this)) != null){
				cir.setReturnValue(SuperdudeDataManager.get(((IDataSaver) this)).isFlying((PlayerEntity) ((Object) this)));
			}
		}
	}
}
