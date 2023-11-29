package eu.ansquare.starr.power.protection;

import eu.ansquare.starr.power.DelayedPower;
import eu.ansquare.starr.power.Power;
import net.minecraft.server.network.ServerPlayerEntity;

public class HealSelfPower extends DelayedPower {
	public int health;
	public HealSelfPower(int delay, int health) {
		super(delay);
		this.health = health;
	}

	@Override
	public void activateAllowed(ServerPlayerEntity player) {
		player.heal(health);
	}
}
