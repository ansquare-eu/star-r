package eu.ansquare.starr.superdude;

import eu.ansquare.starr.power.Power;

public class TestSuperDude extends SuperDude{
	@Override
	public String getName() {
		return "testSuperDude";
	}

	@Override
	public void initPowers() {
	}

	@Override
	public String queryMessage() {
		return "Message123";
	}
}
