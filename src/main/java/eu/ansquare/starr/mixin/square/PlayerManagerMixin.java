package eu.ansquare.starr.mixin.square;

import eu.ansquare.squarepowered.power.AutoMsgPower;
import io.github.apace100.apoli.component.PowerHolderComponent;
import net.minecraft.network.message.MessageType;
import net.minecraft.network.message.OutgoingMessage;
import net.minecraft.network.message.SignedChatMessage;
import net.minecraft.server.PlayerManager;
import net.minecraft.server.network.ServerPlayerEntity;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import java.util.List;

@Mixin(PlayerManager.class)
public abstract class PlayerManagerMixin {
	@Shadow
	@Final
	private List<ServerPlayerEntity> players;

	@Shadow
	public abstract void sendChatMessage(SignedChatMessage message, ServerPlayerEntity sender, MessageType.Parameters parameters);
	@Inject(method = "sendChatMessage(Lnet/minecraft/network/message/SignedChatMessage;Lnet/minecraft/server/network/ServerPlayerEntity;Lnet/minecraft/network/message/MessageType$Parameters;)V", at = @At("HEAD"), cancellable = true)
	public void onSendChatMessage(SignedChatMessage message, ServerPlayerEntity sender, MessageType.Parameters parameters, CallbackInfo ci){
		String[] splitten = message.body().content().split(" ");
		if(PowerHolderComponent.hasPower(sender, AutoMsgPower.class)){
			OutgoingMessage outgoingMessage = OutgoingMessage.create(message);
			for (ServerPlayerEntity player:players) {
				if(player.getName().getString().equalsIgnoreCase(splitten[0])){
					player.sendChatMessage(outgoingMessage, false, parameters);
				}
			}
			ci.cancel();
		}
	}
}
