package eu.ansquare.starr.power;

import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;

public class FlightPower extends ToggleablePower{
	public FlightPower(){};

	@Override
	public String getName() {
		return "flight";
	}

	@Override
	public void activeTick(LivingEntity entity) {
	}
}
