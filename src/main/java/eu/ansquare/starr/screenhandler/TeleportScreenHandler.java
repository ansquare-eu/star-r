package eu.ansquare.starr.screenhandler;

import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.screen.ScreenHandler;
import net.minecraft.screen.ScreenHandlerType;
import org.jetbrains.annotations.Nullable;

public class TeleportScreenHandler extends ScreenHandler {
	protected TeleportScreenHandler(@Nullable ScreenHandlerType<?> type, int syncId) {
		super(type, syncId);
	}

	@Override
	public ItemStack quickTransfer(PlayerEntity player, int fromIndex) {
		return null;
	}

	@Override
	public boolean canUse(PlayerEntity player) {
		return false;
	}
}
