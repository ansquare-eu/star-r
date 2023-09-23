package eu.ansquare.starr.power;

import net.minecraft.entity.LivingEntity;
import net.minecraft.server.network.ServerPlayerEntity;

public class CreatifeFlightPower extends ToggleablePower{
	@Override
	public String getName() {
		return "creaflight";
	}

	@Override
	public void activationAction(ServerPlayerEntity player) {
		player.getAbilities().allowFlying = true;
	}

	@Override
	public void deactivationAction(ServerPlayerEntity player) {
		player.getAbilities().allowFlying = false;
	}

	@Override
	public void activeTick(LivingEntity entity) {

	}
}
