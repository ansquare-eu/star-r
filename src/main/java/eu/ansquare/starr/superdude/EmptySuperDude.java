package eu.ansquare.starr.superdude;

import eu.ansquare.starr.util.power.FlightType;

import java.awt.*;

public class EmptySuperDude extends SuperDude{
	public EmptySuperDude() {
		super(FlightType.NONE, new Color(0xFFFFFF));
	}

	@Override
	public void initPowers() {
	}

	@Override
	public void initModifiers() {
	}

	@Override
	public String queryMessage() {
		return "lars this house is empty";
	}
}
