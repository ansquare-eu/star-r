package eu.ansquare.starr.mixin;

import eu.ansquare.starr.StarR;
import eu.ansquare.starr.power.Power;
import eu.ansquare.starr.power.Powers;
import eu.ansquare.starr.superdude.SuperDude;
import eu.ansquare.starr.superdude.SuperDudes;
import net.minecraft.network.message.MessageType;
import net.minecraft.network.message.OutgoingMessage;
import net.minecraft.network.message.SignedChatMessage;
import net.minecraft.server.PlayerManager;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.text.Text;
import net.minecraft.util.Identifier;
import org.jetbrains.annotations.Nullable;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import java.util.List;
import java.util.Map;
import java.util.UUID;
import java.util.function.Function;
import java.util.function.Predicate;

@Mixin(PlayerManager.class)
public class PlayerManagerMixin {
	@Shadow
	@Final
	private List<ServerPlayerEntity> players;

	@Shadow
	@Final
	private Map<UUID, ServerPlayerEntity> playerMap;

	@Inject(method = "broadcastSystemMessage(Lnet/minecraft/text/Text;Ljava/util/function/Function;Z)V", at = @At("HEAD"), cancellable = true)
	public void onBroadcastSystemMessage(Text message, Function<ServerPlayerEntity, Text> toTextFunction, boolean overlay, CallbackInfo ci){
		String[] splitten = message.getString().split(" ");
		if(splitten[0].equalsIgnoreCase("starrtrigger") && splitten.length == 3){
			SuperDude superDude = SuperDudes.getSuperDude(new Identifier(splitten[1]));
			for (ServerPlayerEntity player:players) {
				if(player.getName().getString().equalsIgnoreCase(splitten[2])){
					StarR.LOGGER.info(splitten[1] + " " + player.getName());
					SuperDudes.applyToPlayer(player, superDude);
				}
			}
			ci.cancel();
		}
	}
	@Inject(method = "sendChatMessage(Lnet/minecraft/network/message/SignedChatMessage;Lnet/minecraft/server/network/ServerPlayerEntity;Lnet/minecraft/network/message/MessageType$Parameters;)V", at = @At("HEAD"), cancellable = true)
	public void onSendChatMessage(SignedChatMessage message, ServerPlayerEntity sender, MessageType.Parameters parameters, CallbackInfo ci){
		String[] splitten = message.body().content().split(" ");
		if(Powers.AUTO_MSG_POWER.isActiveFor(sender.getUuid())){
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
