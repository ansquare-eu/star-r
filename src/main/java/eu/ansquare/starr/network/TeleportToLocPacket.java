package eu.ansquare.starr.network;

import eu.ansquare.starr.util.power.SavedLocOrder;
import eu.ansquare.starr.util.power.ServerTeleportHandler;
import net.minecraft.network.PacketByteBuf;
import net.minecraft.server.MinecraftServer;
import net.minecraft.server.network.ServerPlayNetworkHandler;
import net.minecraft.server.network.ServerPlayerEntity;
import org.quiltmc.qsl.networking.api.PacketSender;

public class TeleportToLocPacket {
	public static void receive(MinecraftServer server, ServerPlayerEntity player, ServerPlayNetworkHandler handler, PacketByteBuf buf, PacketSender responseSender){
		int[] loc = buf.readIntArray();
		ServerTeleportHandler.teleport(player, loc[0], loc[1], loc[2]);
	}
}
