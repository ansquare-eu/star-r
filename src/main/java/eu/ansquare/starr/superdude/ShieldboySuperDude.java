package eu.ansquare.starr.superdude;

import eu.ansquare.starr.items.ModItems;
import eu.ansquare.starr.power.Powers;
import eu.ansquare.starr.power.creation.CreateItemPower;
import eu.ansquare.starr.util.power.FlightType;

import java.awt.*;

public class ShieldboySuperDude extends SuperDude{
	public ShieldboySuperDude(FlightType flight, Color color) {
		super(flight, color);
	}

	@Override
	public void initPowers() {
		addPower(PowerOrder.FIRST, Powers.FORCEFIELD_POWER);
		addPower(PowerOrder.SECOND, new CreateItemPower(ModItems.FORCESWORD, 1, true));
	}

	@Override
	public void initModifiers() {

	}

	@Override
	public String queryMessage() {
		return "im not making the captain readyroad america shield";
	}
}
