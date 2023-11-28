package eu.ansquare.starr.mixin.client;

import eu.ansquare.starr.client.StarRClient;
import net.minecraft.client.network.ClientPlayNetworkHandler;
import net.minecraft.client.network.ClientPlayerInteractionManager;
import net.minecraft.item.ItemStack;
import net.minecraft.network.packet.c2s.play.CreativeInventoryActionC2SPacket;
import net.minecraft.world.GameMode;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(ClientPlayerInteractionManager.class)
public class ClientPlayerInteractionManagerMixin {
	@Shadow
	private GameMode gameMode;

	@Shadow
	@Final
	private ClientPlayNetworkHandler networkHandler;

	@Inject(method = "hasCreativeInventory", at = @At("TAIL"), cancellable = true)
	public void onHasCreativeInventory(CallbackInfoReturnable<Boolean> cir) {
		cir.setReturnValue(cir.getReturnValue() || StarRClient.LASER_HOLDER.readCreative());
	}
	@Inject(method = "clickCreativeStack", at = @At("TAIL"), cancellable = true)
	public void onClickStack(ItemStack stack, int slotId, CallbackInfo ci) {
		if (StarRClient.LASER_HOLDER.isFalseCreative && !gameMode.isCreative() && this.networkHandler.isEnabled(stack.getItem().getRequiredFlags())) {
			this.networkHandler.sendPacket(new CreativeInventoryActionC2SPacket(slotId, stack));
		}
	}
}
