package eu.ansquare.starr.mixin;

import eu.ansquare.squarepowered.Squarepowered;
import eu.ansquare.squarepowered.worlddata.GlobalSuperdudeData;
import eu.ansquare.starr.StarR;
import io.github.apace100.origins.Origins;
import io.github.apace100.origins.component.OriginComponent;
import io.github.apace100.origins.component.PlayerOriginComponent;
import io.github.apace100.origins.origin.Origin;
import io.github.apace100.origins.origin.OriginLayer;
import io.github.apace100.origins.origin.OriginLayers;
import io.github.apace100.origins.registry.ModComponents;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.util.Identifier;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(OriginComponent.class)
public interface OriginComponentMixin {

	@Inject(method = "onChosen", at = @At("HEAD"), remap = false, cancellable = true)
	private static void squarepowered_onSetOrigin(PlayerEntity player, boolean hadOriginBefore, CallbackInfo ci){
		if(player.getWorld() instanceof ServerWorld world){
			if(world.getGameRules().getBoolean(StarR.EXCLUSIVE_SUPERDUDES)){
				Identifier identifier = ModComponents.ORIGIN.get(player).getOrigin(OriginLayers.getLayer(StarR.id("superdude"))).getIdentifier();
				if(GlobalSuperdudeData.putOrReplace(world.getServer(), player.getUuid(), identifier)) {}
			}
		}
	}
}
