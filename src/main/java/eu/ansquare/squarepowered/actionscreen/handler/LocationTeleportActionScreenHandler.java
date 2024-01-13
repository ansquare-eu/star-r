package eu.ansquare.squarepowered.actionscreen.handler;

import eu.ansquare.squarepowered.actionscreen.SquareActionScreens;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.PlayerInventory;

public class LocationTeleportActionScreenHandler extends ActionScreenHandler{
	public LocationTeleportActionScreenHandler(int id, PlayerInventory inv) {
		super(SquareActionScreens.TELEPORT_SCREEN, id);
	}

	@Override
	public boolean canUse(PlayerEntity player) {
		return true;
	}
}
