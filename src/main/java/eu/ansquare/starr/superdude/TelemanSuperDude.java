package eu.ansquare.starr.superdude;

import eu.ansquare.starr.power.Powers;
import eu.ansquare.starr.util.power.FlightType;

import java.awt.*;

public class TelemanSuperDude extends SuperDude{
	public TelemanSuperDude(FlightType flight, Color color) {
		super(flight, color);
	}

	@Override
	public void initPowers() {
		addPower(PowerOrder.FIRST, Powers.TELEPORT_POWER);
		addPower(PowerOrder.SECOND, Powers.TELEKINESIS_POWER);
		addPower(PowerOrder.THIRD, Powers.AUTO_MSG_POWER);
	}

	@Override
	public void initModifiers() {

	}

	@Override
	public String queryMessage() {
		return "tele";
	}
}
