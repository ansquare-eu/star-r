package eu.ansquare.starr.power.protection;

import eu.ansquare.starr.power.ToggleablePower;
import net.minecraft.entity.LivingEntity;
import net.minecraft.server.network.ServerPlayerEntity;

public class UnderwaterPower extends ToggleablePower {
	@Override
	public void activationAction(ServerPlayerEntity player) {

	}

	@Override
	public void deactivationAction(ServerPlayerEntity player) {

	}

	@Override
	public void activeTick(LivingEntity entity) {
		entity.setAir(10);
	}
}
