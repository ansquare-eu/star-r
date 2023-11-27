package eu.ansquare.starr.power.creation;

import eu.ansquare.starr.power.Power;
import eu.ansquare.starr.power.ToggleablePower;
import eu.ansquare.starr.util.item.ItemArrayProvider;
import eu.ansquare.starr.util.item.ItemUtils;
import net.minecraft.client.gui.screen.ingame.CreativeInventoryScreen;
import net.minecraft.entity.LivingEntity;
import net.minecraft.screen.GenericContainerScreenHandler;
import net.minecraft.screen.ScreenHandler;
import net.minecraft.screen.ScreenHandlerType;
import net.minecraft.screen.SimpleNamedScreenHandlerFactory;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.text.Text;
import net.minecraft.world.GameMode;

public class CreativeMenuPower extends ToggleablePower {

	@Override
	public void activationAction(ServerPlayerEntity player) {
		player.interactionManager.changeGameMode(GameMode.CREATIVE);
	}

	@Override
	public void deactivationAction(ServerPlayerEntity player) {
		player.interactionManager.changeGameMode(GameMode.SURVIVAL);

	}

	@Override
	public void activeTick(LivingEntity entity) {

	}
}
