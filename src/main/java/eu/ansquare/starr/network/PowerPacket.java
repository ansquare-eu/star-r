package eu.ansquare.starr.network;

import dev.emi.trinkets.api.TrinketItem;
import eu.ansquare.starr.cca.StarREntityComponents;
import eu.ansquare.starr.power.Power;
import eu.ansquare.starr.superdude.PowerOrder;
import eu.ansquare.starr.superdude.SuperDude;
import eu.ansquare.starr.util.datasaving.IDataSaver;
import net.fabricmc.fabric.api.entity.FakePlayer;
import net.minecraft.network.PacketByteBuf;
import net.minecraft.server.MinecraftServer;
import net.minecraft.server.network.ServerPlayNetworkHandler;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.text.Text;
import org.quiltmc.qsl.networking.api.PacketSender;

public class PowerPacket {

	public static void receive(MinecraftServer server, ServerPlayerEntity player, ServerPlayNetworkHandler handler, PacketByteBuf buf, PacketSender responseSender){
		PowerOrder powerOrder = buf.readEnumConstant(PowerOrder.class);
		StarREntityComponents.SUPER_DUDE_COMPONENT.maybeGet(player).ifPresent(superDudeComponent -> superDudeComponent.getPower(powerOrder).onActivate(player));
	}
}
