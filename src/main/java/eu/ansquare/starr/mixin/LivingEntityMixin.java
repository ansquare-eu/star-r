package eu.ansquare.starr.mixin;

import eu.ansquare.starr.cca.StarREntityComponents;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.world.World;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

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
}
