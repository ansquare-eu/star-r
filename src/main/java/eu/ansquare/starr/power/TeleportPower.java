package eu.ansquare.starr.power;

import eu.ansquare.starr.screenhandler.TeleportScreenHandler;
import net.minecraft.screen.SimpleNamedScreenHandlerFactory;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.text.Text;

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
