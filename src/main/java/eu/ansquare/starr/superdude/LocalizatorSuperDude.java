package eu.ansquare.starr.superdude;

import eu.ansquare.starr.power.Powers;
import eu.ansquare.starr.util.power.FlightType;

import java.awt.*;

public class LocalizatorSuperDude extends SuperDude{
	public LocalizatorSuperDude(FlightType flight, Color color) {
		super(flight, color);
	}

	@Override
	public void initPowers() {
		addPower(PowerOrder.FIRST, Powers.TELEPORT_POWER);
		addPower(PowerOrder.SECOND, Powers.TELEPORT_OTHERS_POWER);
	}

	@Override
	public void initModifiers() {

	}

	@Override
	public String queryMessage() {
		return "The dumbest name with the dumbest power";
	}
}
