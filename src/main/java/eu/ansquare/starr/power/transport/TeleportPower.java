package eu.ansquare.starr.power.transport;

import eu.ansquare.starr.power.Power;
import eu.ansquare.starr.screenhandler.TeleportScreenHandler;
import net.minecraft.screen.SimpleNamedScreenHandlerFactory;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.text.Text;

public class TeleportPower extends Power {

	@Override
	public void onActivate(ServerPlayerEntity player) {
		player.openHandledScreen(new SimpleNamedScreenHandlerFactory((syncId, playerInventory, playerx) -> new TeleportScreenHandler(syncId, playerInventory), Text.literal("enjoy")));
	}
}
