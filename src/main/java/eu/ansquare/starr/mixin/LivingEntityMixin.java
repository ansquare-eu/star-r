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

	public LivingEntityMixin(EntityType<?> variant, World world) {
		super(variant, world);
	}

	@Inject(method = "baseTick", at = @At("HEAD"))
	public void tick(CallbackInfo ci) {
		if (!this.getWorld().isClient()) {
			if (SuperdudeDataManager.get(((IDataSaver) this)) != null) {
				SuperDude superDude = SuperdudeDataManager.get(((IDataSaver) this));
				superDude.executeActiveTicks((LivingEntity) ((Object) this));
			}
		}
	}
}
