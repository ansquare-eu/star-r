package eu.ansquare.starr.network.c2s;

import eu.ansquare.starr.cca.StarREntityComponents;
import eu.ansquare.starr.util.power.PowerOrder;
import net.minecraft.network.PacketByteBuf;
import net.minecraft.server.MinecraftServer;
import net.minecraft.server.network.ServerPlayNetworkHandler;
import net.minecraft.server.network.ServerPlayerEntity;
import org.quiltmc.qsl.networking.api.PacketSender;

public class PowerPacket {

	public static void receive(MinecraftServer server, ServerPlayerEntity player, ServerPlayNetworkHandler handler, PacketByteBuf buf, PacketSender responseSender){
		PowerOrder powerOrder = buf.readEnumConstant(PowerOrder.class);
		server.execute(() -> 		StarREntityComponents.SUPER_DUDE_COMPONENT.maybeGet(player).ifPresent(superDudeComponent -> superDudeComponent.getPower(powerOrder).onActivate(player)));
	}
}
