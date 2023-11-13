package eu.ansquare.starr.superdude;

import eu.ansquare.starr.power.Powers;
import eu.ansquare.starr.power.offense.LaserPower;
import eu.ansquare.starr.util.power.FlightType;
import net.minecraft.util.Pair;

import java.awt.*;

public class CaptainRSuperDude extends SuperDude{
	public CaptainRSuperDude(FlightType flight, Color color) {
		super(flight, color);
	}

	@Override
	public void initPowers() {
		addPower(PowerOrder.FIRST, new LaserPower(color, 2, (entity, handStack, offhandStack) -> new Pair<>(0f, 0f)));
		addPower(PowerOrder.SECOND, Powers.FORCEFIELD_POWER);
		addPower(PowerOrder.THIRD, Powers.TELEKINESIS_POWER);
		addPower(PowerOrder.FOURTH, Powers.TELEPORT_POWER);
	}

	@Override
	public void initModifiers() {

	}

	@Override
	public String queryMessage() {
		return "readyroad's self insert which he doesn't admit";
	}
}
