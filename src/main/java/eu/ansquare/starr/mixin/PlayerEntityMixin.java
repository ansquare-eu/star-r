package eu.ansquare.starr.mixin;

import eu.ansquare.starr.util.datasaving.IDataSaver;
import eu.ansquare.starr.util.datasaving.SuperdudeDataManager;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.world.World;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(PlayerEntity.class)
public abstract class PlayerEntityMixin extends LivingEntity {
	protected PlayerEntityMixin(EntityType<? extends LivingEntity> entityType, World world) {
		super(entityType, world);
	}

	@Inject(method = "checkFallFlying", at = @At("HEAD"), cancellable = true)
	public void onCheckFallFlying(CallbackInfoReturnable<Boolean> cir){
		if(!this.getWorld().isClient()){
			if(SuperdudeDataManager.get(((IDataSaver) this)) != null){
				cir.setReturnValue(SuperdudeDataManager.get(((IDataSaver) this)).isFlying((PlayerEntity) ((Object) this)));
			}
		}
	}
}
