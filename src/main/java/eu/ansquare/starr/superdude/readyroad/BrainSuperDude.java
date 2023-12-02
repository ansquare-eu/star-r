package eu.ansquare.starr.superdude.readyroad;

import eu.ansquare.starr.power.Powers;
import eu.ansquare.starr.power.protection.HealSelfPower;
import eu.ansquare.starr.util.power.PowerOrder;
import eu.ansquare.starr.superdude.SuperDude;
import eu.ansquare.starr.util.power.FlightType;

import java.awt.*;

public class BrainSuperDude extends SuperDude {
	public BrainSuperDude(FlightType flight, Color color) {
		super(flight, color);
	}

	@Override
	public void initPowers() {
		addPower(PowerOrder.FIRST, Powers.TELEKINESIS_POWER);
		addPower(PowerOrder.SECOND, Powers.CREATIVE_MENU_POWER);
		addPower(PowerOrder.THIRD, new HealSelfPower(100, 2));
	}

	@Override
	public void initModifiers() {

	}

	@Override
	public String queryMessage() {
		return "Readyroad's another self insert";
	}
}
