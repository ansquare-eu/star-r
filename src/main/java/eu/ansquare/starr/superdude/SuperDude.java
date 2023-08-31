package eu.ansquare.starr.superdude;

import eu.ansquare.starr.power.Power;

import java.util.HashMap;
import java.util.Map;

public abstract class SuperDude {
	public SuperDude() {
		powers = new HashMap<>();
	}

	private Map<PowerOrder, Power> powers;
	public abstract String getName();
	public abstract void initPowers();
	public Power getPower(PowerOrder order){
		return powers.get(order);
	}
	public abstract String queryMessage();
}
