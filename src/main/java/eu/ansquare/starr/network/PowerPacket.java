package eu.ansquare.starr.network;

import dev.emi.trinkets.api.TrinketItem;
import eu.ansquare.starr.power.Power;
import eu.ansquare.starr.superdude.PowerOrder;
import eu.ansquare.starr.superdude.SuperDude;
import eu.ansquare.starr.util.datasaving.IDataSaver;
import eu.ansquare.starr.util.datasaving.SuperdudeDataManager;
import net.fabricmc.fabric.api.entity.FakePlayer;
import net.minecraft.network.PacketByteBuf;
import net.minecraft.server.MinecraftServer;
import net.minecraft.server.network.ServerPlayNetworkHandler;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.text.Text;
import org.quiltmc.qsl.networking.api.PacketSender;

public class PowerPacket {
	public static final String NO_SUPERHERO = "message.starr.not_superhero";
	public static final String LACKS_POWER = "message.starr.lack_power";

	public static void receive(MinecraftServer server, ServerPlayerEntity player, ServerPlayNetworkHandler handler, PacketByteBuf buf, PacketSender responseSender){
		PowerOrder powerOrder = buf.readEnumConstant(PowerOrder.class);
		SuperDude superDude = SuperdudeDataManager.get(((IDataSaver) player));
		if(superDude != null){
			if(superDude.hasPower(powerOrder)){
				Power power = superDude.getPower(powerOrder);
				power.onActivate(player);
			}

			else {
				player.sendMessage(Text.translatable(LACKS_POWER), true);
			}
		} else {
			player.sendMessage(Text.translatable(NO_SUPERHERO), true);
		}
	}
}
