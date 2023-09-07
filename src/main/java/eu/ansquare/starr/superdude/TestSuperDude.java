package eu.ansquare.starr.superdude;


import eu.ansquare.starr.power.Power;
import eu.ansquare.starr.power.Powers;

public class TestSuperDude extends SuperDude{
	@Override
	public String getName() {
		return "testType";
	}

	@Override
	public void initPowers() {
		this.powers.put(PowerOrder.TRANSFORMATION, Powers.TEST_TRANSFORMATION_POWER);
		this.powers.put(PowerOrder.FIRST, Powers.TEST_LASER_POWER);
		this.powers.put(PowerOrder.SECOND, Powers.FLIGHT_POWER);
	}
	@Override
	public String queryMessage() {
		return "Message123";
	}
}
