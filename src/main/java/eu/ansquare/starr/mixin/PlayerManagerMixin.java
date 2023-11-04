package eu.ansquare.starr.mixin;

import eu.ansquare.starr.StarR;
import eu.ansquare.starr.power.Power;
import eu.ansquare.starr.power.Powers;
import net.minecraft.network.message.MessageType;
import net.minecraft.network.message.OutgoingMessage;
import net.minecraft.network.message.SignedChatMessage;
import net.minecraft.server.PlayerManager;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.text.Text;
import org.jetbrains.annotations.Nullable;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import java.util.List;
import java.util.function.Predicate;

@Mixin(PlayerManager.class)
public class PlayerManagerMixin {
	@Shadow
	@Final
	private List<ServerPlayerEntity> players;

	@Inject(method = "sendChatMessage(Lnet/minecraft/network/message/SignedChatMessage;Lnet/minecraft/server/network/ServerPlayerEntity;Lnet/minecraft/network/message/MessageType$Parameters;)V", at = @At("HEAD"), cancellable = true)
	public void onBroadcastChatMessage(SignedChatMessage message, ServerPlayerEntity sender, MessageType.Parameters parameters, CallbackInfo ci){
		if(Powers.AUTO_MSG_POWER.isActiveFor(sender.getUuid())){
			String[] splitten = message.body().content().split(" ");
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
