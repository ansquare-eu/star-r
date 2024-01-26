package eu.ansquare.starr.mixin;

import eu.ansquare.sbd.BlockDataApi;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.server.network.ServerPlayerInteractionManager;
import net.minecraft.util.math.BlockPos;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.gen.Accessor;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(ServerPlayerInteractionManager.class)
public abstract class ServerPlayerInteractionManagerAccessor {
	@Shadow
	@Final
	protected ServerPlayerEntity player;

	@Inject(method = "tryBreakBlock", at = @At("RETURN"), cancellable = true)
	public void tryBreakBlock(BlockPos pos, CallbackInfoReturnable<Boolean> cir) {
		//TODO move to SBD
		if(cir.getReturnValueZ()){
			BlockDataApi.setBoolean(pos, player.getServerWorld(), "no_drop", false);
		}
	}
}
