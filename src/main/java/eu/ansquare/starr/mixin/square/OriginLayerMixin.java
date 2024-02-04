package eu.ansquare.starr.mixin.square;

import eu.ansquare.squarepowered.Squarepowered;
import eu.ansquare.squarepowered.worlddata.GlobalSuperdudeData;
import io.github.apace100.origins.component.PlayerOriginComponent;
import io.github.apace100.origins.origin.Origin;
import io.github.apace100.origins.origin.OriginLayer;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.network.PacketByteBuf;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.util.Identifier;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(OriginLayer.class)
public abstract class OriginLayerMixin {
	@Shadow
	private Identifier identifier;


	@Inject(method = "contains(Lio/github/apace100/origins/origin/Origin;Lnet/minecraft/entity/player/PlayerEntity;)Z", at = @At("HEAD"), cancellable = true)
	public void squarepowered_onSetOrigin(Origin origin, PlayerEntity playerEntity, CallbackInfoReturnable<Boolean> cir){
		if(playerEntity.getWorld() instanceof ServerWorld world){
			if(world.getGameRules().getBoolean(Squarepowered.EXCLUSIVE_SUPERDUDES)){
				if(identifier.equals(Squarepowered.id("superdude"))){
					if(GlobalSuperdudeData.isTaken(world.getServer(), origin.getIdentifier())){
						cir.setReturnValue(false);
					}
				}
			}
		}
	}
}
