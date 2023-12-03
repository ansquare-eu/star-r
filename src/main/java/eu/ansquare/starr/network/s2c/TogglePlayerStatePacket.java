package eu.ansquare.starr.network.s2c;

import eu.ansquare.starr.StarR;
import eu.ansquare.starr.client.StarRClient;
import eu.ansquare.starr.util.network.ClientPlayerState;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.gui.screen.ingame.InventoryScreen;
import net.minecraft.client.network.ClientPlayNetworkHandler;
import net.minecraft.client.network.ClientPlayerEntity;
import net.minecraft.client.world.ClientWorld;
import net.minecraft.network.PacketByteBuf;
import org.quiltmc.qsl.networking.api.PacketSender;

import java.util.UUID;

public class TogglePlayerStatePacket {
	public static void receive(MinecraftClient minecraftClient, ClientPlayNetworkHandler clientPlayNetworkHandler, PacketByteBuf packetByteBuf, PacketSender packetSender) {
		ClientPlayerState state = packetByteBuf.readEnumConstant(ClientPlayerState.class);
		UUID uuid = packetByteBuf.readUuid();
		if(state == ClientPlayerState.CREATIVE){
			minecraftClient.execute(() ->{
				StarRClient.LASER_HOLDER.add(state);
				}
			);
		}
	}
}
