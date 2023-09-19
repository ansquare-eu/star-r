package eu.ansquare.starr.network;

import eu.ansquare.starr.cca.StarREntityComponents;
import eu.ansquare.starr.power.Power;
import eu.ansquare.starr.superdude.PowerOrder;
import eu.ansquare.starr.superdude.SuperDude;
import eu.ansquare.starr.util.datasaving.IDataSaver;
import eu.ansquare.starr.util.network.BoolEnum;
import net.minecraft.network.PacketByteBuf;
import net.minecraft.server.MinecraftServer;
import net.minecraft.server.network.ServerPlayNetworkHandler;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.text.Text;
import org.quiltmc.qsl.networking.api.PacketSender;

public class ASPacket {
	public static void receive(MinecraftServer server, ServerPlayerEntity player, ServerPlayNetworkHandler handler, PacketByteBuf buf, PacketSender responseSender){
		boolean bool;
		if(buf.readEnumConstant(BoolEnum.class) == BoolEnum.TRUE){
			bool = true;
		} else {
			bool = false;
		}
		StarREntityComponents.SUPER_DUDE_COMPONENT.maybeGet(player).ifPresent(superDudeComponent -> superDudeComponent.getType().executeIncrements(player, bool));
	}
}
