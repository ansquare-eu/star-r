package eu.ansquare.squarepowered.actionscreen;

import eu.ansquare.squarepowered.actionscreen.ActionScreenHandler;
import eu.ansquare.squarepowered.actionscreen.SquareActionScreens;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.screen.ScreenHandlerType;
import org.jetbrains.annotations.Nullable;

public class SaveTeleportActionScreenHandler extends ActionScreenHandler {
	public PlayerEntity player;
	public SaveTeleportActionScreenHandler(int syncId, PlayerInventory inventory) {
		super(SquareActionScreens.SAVE_TELEPORT_SCREEN, syncId);
		player = inventory.player;
	}

	@Override
	public boolean canUse(PlayerEntity player) {
		return true;
	}
}
