package eu.ansquare.starr.superdude;

import eu.ansquare.starr.power.Powers;
import eu.ansquare.starr.power.creation.CreateItemPower;
import eu.ansquare.starr.power.creation.CreativeMenuPower;
import eu.ansquare.starr.util.power.FlightType;

import java.awt.*;

public class BrainSuperDude extends SuperDude{
	public BrainSuperDude(FlightType flight, Color color) {
		super(flight, color);
	}

	@Override
	public void initPowers() {
		addPower(PowerOrder.FIRST, Powers.TELEKINESIS_POWER);
		addPower(PowerOrder.SECOND, new CreativeMenuPower());
	}

	@Override
	public void initModifiers() {

	}

	@Override
	public String queryMessage() {
		return "Readyroad's another self insert";
	}
}
