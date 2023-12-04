package eu.ansquare.starr.superdude.readyroad;

import eu.ansquare.starr.items.ModItems;
import eu.ansquare.starr.power.Powers;
import eu.ansquare.starr.power.offense.LaserPower;
import eu.ansquare.starr.util.power.PowerOrder;
import eu.ansquare.starr.superdude.SuperDude;
import eu.ansquare.starr.util.power.FlightType;
import net.minecraft.util.Pair;

import java.awt.*;

public class CaptainRSuperDude extends SuperDude {
	public CaptainRSuperDude(FlightType flight, Color color) {
		super(flight, color);
	}

	@Override
	public void initPowers() {
		addPower(PowerOrder.FIRST, new LaserPower(color, 2, (entity, handStack, offhandStack) -> {
			if(handStack.isOf(ModItems.PALICA)){
				return new Pair<>(-0.5f, 0.5f);
			} else {
				return new Pair<>(-0.5f, -0.4f);
			}
		}));
		addPower(PowerOrder.SECOND, Powers.FORCEFIELD_POWER);
		addPower(PowerOrder.THIRD, Powers.TELEKINESIS_POWER);
		addPower(PowerOrder.FOURTH, Powers.TELEPORT_POWER);
		addPower(PowerOrder.FIFTH, Powers.TORNADO_POWER);
	}

	@Override
	public void initModifiers() {

	}

	@Override
	public String queryMessage() {
		return "readyroad's self insert which he doesn't admit";
	}
}
