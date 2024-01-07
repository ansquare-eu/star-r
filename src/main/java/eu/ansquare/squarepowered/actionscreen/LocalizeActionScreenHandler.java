package eu.ansquare.squarepowered.actionscreen;

import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.PlayerInventory;

public class LocalizeActionScreenHandler extends ActionScreenHandler{
	public LocalizeActionScreenHandler(int id, PlayerInventory inv) {
		super(SquareActionScreens.LOCALIZE_SCREEN, id);
	}

	@Override
	public boolean canUse(PlayerEntity player) {
		return true;
	}
}
