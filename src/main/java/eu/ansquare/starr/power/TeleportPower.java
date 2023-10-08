package eu.ansquare.starr.power;

import eu.ansquare.starr.network.ModPackets;
import eu.ansquare.starr.screenhandler.ModScreenHandlers;
import eu.ansquare.starr.screenhandler.TeleportScreenHandler;
import net.minecraft.network.PacketByteBuf;
import net.minecraft.screen.MerchantScreenHandler;
import net.minecraft.screen.SimpleNamedScreenHandlerFactory;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.text.Text;
import net.minecraft.village.TradeOfferList;
import org.quiltmc.qsl.networking.api.PacketByteBufs;
import org.quiltmc.qsl.networking.api.ServerPlayNetworking;

import java.util.OptionalInt;

public class TeleportPower extends Power{
	@Override
	public String getName() {
		return "teleport";
	}

	@Override
	public void onActivate(ServerPlayerEntity player) {
		player.openHandledScreen(new SimpleNamedScreenHandlerFactory((syncId, playerInventory, playerx) -> new TeleportScreenHandler(syncId, playerInventory), Text.literal("enjoy")));
	}
}
