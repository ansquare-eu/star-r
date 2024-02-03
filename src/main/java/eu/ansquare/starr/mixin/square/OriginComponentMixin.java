package eu.ansquare.starr.mixin.square;

import eu.ansquare.squarepowered.Squarepowered;
import io.github.apace100.origins.component.PlayerOriginComponent;
import io.github.apace100.origins.origin.Origin;
import io.github.apace100.origins.origin.OriginLayer;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(PlayerOriginComponent.class)
public class OriginComponentMixin {
	@Inject(method = "setOrigin", at = @At("TAIL"), remap = false)
	public void squarepowered_onSetOrigin(OriginLayer layer, Origin origin, CallbackInfo ci){
		Squarepowered.log("mr log sky", 1);
	}
}
