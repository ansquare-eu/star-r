package eu.ansquare.starr.power.transport;

import eu.ansquare.starr.power.ToggleablePower;
import eu.ansquare.starr.util.power.ServerTeleportHandler;
import net.minecraft.entity.LivingEntity;
import net.minecraft.server.network.ServerPlayerEntity;

public class TeleportOthersPower extends ToggleablePower {


	@Override
	public void activationAction(ServerPlayerEntity player) {

	}

	@Override
	public void deactivationAction(ServerPlayerEntity player) {
		ServerTeleportHandler.removeTask(player);
	}

	@Override
	public void activeTick(LivingEntity entity) {

	}
}
