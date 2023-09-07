package eu.ansquare.starr.superdude;

import eu.ansquare.starr.power.FlightPower;
import eu.ansquare.starr.power.Power;
import eu.ansquare.starr.power.ToggleablePower;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

public abstract class SuperDude {
	public SuperDude() {
		powers = new HashMap<>();
		initPowers();
	}

	public Map<PowerOrder, Power> powers;
	public abstract String getName();
	public abstract void initPowers();
	public Power getPower(PowerOrder order){
		return this.powers.get(order);
	}
	public abstract String queryMessage();
	public boolean hasPower(PowerOrder order){
		return this.powers.containsKey(order);
	}
	public Map<PowerOrder, Power> getPowers(){
		return powers;
	}
	public boolean isFlying(PlayerEntity player){
		for (PowerOrder powerOrd : powers.keySet()) {
			Power power = powers.get(powerOrd);
			if (power instanceof FlightPower) {
				if (((ToggleablePower) power).isActiveFor(player.getUuid())) {
					return true;
				}
			}
			return false;
		}
		return false;
	}
}
