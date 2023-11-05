package eu.ansquare.starr.power.transport;

import eu.ansquare.starr.power.ToggleablePower;
import net.minecraft.entity.LivingEntity;
import net.minecraft.server.network.ServerPlayerEntity;

public class CreatifeFlightPower extends ToggleablePower {
	@Override
	public void activationAction(ServerPlayerEntity player) {
		player.getAbilities().allowFlying = true;
		player.sendAbilitiesUpdate();
	}

	@Override
	public void deactivationAction(ServerPlayerEntity player) {
		player.getAbilities().allowFlying = false;
		player.sendAbilitiesUpdate();
	}

	@Override
	public void activeTick(LivingEntity entity) {

	}
}
