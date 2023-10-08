package eu.ansquare.starr.network.c2s;

import eu.ansquare.starr.cca.StarREntityComponents;
import eu.ansquare.starr.power.Power;
import eu.ansquare.starr.util.network.BoolEnum;
import eu.ansquare.starr.util.power.ServerTeleportHandler;
import net.minecraft.network.PacketByteBuf;
import net.minecraft.server.MinecraftServer;
import net.minecraft.server.network.ServerPlayNetworkHandler;
import net.minecraft.server.network.ServerPlayerEntity;
import org.quiltmc.qsl.networking.api.PacketSender;

public class TeleportToSavedPacket {
	public static void receive(MinecraftServer server, ServerPlayerEntity player, ServerPlayNetworkHandler handler, PacketByteBuf buf, PacketSender responseSender){
		int order = Integer.parseInt(buf.readString());
		ServerTeleportHandler.teleport(player, order);
	}
}
