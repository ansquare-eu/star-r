package eu.ansquare.starr.mixin.client;

import eu.ansquare.squarepowered.power.CreativeInvPower;
import eu.ansquare.starr.client.StarRClient;
import io.github.apace100.apoli.component.PowerHolderComponent;
import net.minecraft.client.MinecraftClient;
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

	@Shadow
	@Final
	private MinecraftClient client;

	@Inject(method = "hasCreativeInventory", at = @At("TAIL"), cancellable = true)
	public void onHasCreativeInventory(CallbackInfoReturnable<Boolean> cir) {
		cir.setReturnValue(cir.getReturnValue() || PowerHolderComponent.hasPower(client.player, CreativeInvPower.class));
	}
	@Inject(method = "clickCreativeStack", at = @At("TAIL"), cancellable = true)
	public void onClickStack(ItemStack stack, int slotId, CallbackInfo ci) {
		if (PowerHolderComponent.hasPower(client.player, CreativeInvPower.class) && !gameMode.isCreative() && this.networkHandler.isEnabled(stack.getItem().getRequiredFlags())) {
			this.networkHandler.sendPacket(new CreativeInventoryActionC2SPacket(slotId, stack));
		}
	}
}
