package eu.ansquare.squarepowered.actionscreen;

import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.screen.ScreenHandlerType;
import org.jetbrains.annotations.Nullable;

public class LocationTeleportActionScreenHandler extends ActionScreenHandler{
	public LocationTeleportActionScreenHandler(int id, PlayerInventory inv) {
		super(SquareActionScreens.TELEPORT_SCREEN, id);
	}

	@Override
	public boolean canUse(PlayerEntity player) {
		return false;
	}
}
