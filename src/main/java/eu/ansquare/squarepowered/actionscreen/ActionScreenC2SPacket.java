package eu.ansquare.squarepowered.actionscreen;

import eu.ansquare.squarepowered.actionscreen.action.ScreenAction;
import eu.ansquare.squarepowered.actionscreen.action.TeleportLocationScreenAction;
import net.minecraft.network.PacketByteBuf;
import net.minecraft.server.MinecraftServer;
import net.minecraft.server.network.ServerPlayNetworkHandler;
import net.minecraft.server.network.ServerPlayerEntity;
import org.quiltmc.qsl.networking.api.PacketSender;

import java.util.Date;

public class ActionScreenC2SPacket {

	public static void receive(MinecraftServer server, ServerPlayerEntity player, ServerPlayNetworkHandler handler, PacketByteBuf buf, PacketSender responseSender) {
		ScreenAction action = identify(buf);
		server.execute(action.run(player));
	}
	public static ScreenAction identify(PacketByteBuf buf){

		switch (buf.readInt()){
			default -> {
				return new TeleportLocationScreenAction(buf);
			}
		}
	}

}
