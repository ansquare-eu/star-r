package eu.ansquare.starr.network.c2s;

import eu.ansquare.starr.cca.StarREntityComponents;
import net.minecraft.network.PacketByteBuf;
import net.minecraft.server.MinecraftServer;
import net.minecraft.server.network.ServerPlayNetworkHandler;
import net.minecraft.server.network.ServerPlayerEntity;
import org.quiltmc.qsl.networking.api.PacketSender;

public class SaveLocPacket {
	public static void receive(MinecraftServer server, ServerPlayerEntity player, ServerPlayNetworkHandler handler, PacketByteBuf buf, PacketSender responseSender){
		int order = Integer.parseInt(buf.readString());
		int[] loc = new int[] {player.getBlockX(), player.getBlockY(), player.getBlockZ()};
		StarREntityComponents.TELEPORT_LOC_COMPONENT.maybeGet(player).ifPresent(teleportLocComponent -> teleportLocComponent.writeLoc(order, loc));
	}
}
