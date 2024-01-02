package eu.ansquare.squarepowered.actionscreen.client;

import eu.ansquare.squarepowered.Squarepowered;
import eu.ansquare.squarepowered.actionscreen.LocationTeleportActionScreenHandler;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.screen.ScreenHandler;
import net.minecraft.text.Text;
import net.minecraft.util.Identifier;

public class LocationTeleportScreen extends ActionScreen<LocationTeleportActionScreenHandler> {
	public LocationTeleportScreen(ScreenHandler handler, PlayerInventory inventory, Text title) {
		super(handler, inventory, title, Squarepowered.id("textures/screen/teleportscreen.png"), 176, 166);
	}

	@Override
	public void sendPacket(int actionId) {

	}
}
