package eu.ansquare.starr.superdude;

import java.awt.*;

public class EmptySuperDude extends SuperDude{
	public EmptySuperDude() {
		super(false, new Color(0xFFFFFF));
	}

	@Override
	public String getName() {
		return "empty";
	}

	@Override
	public void initPowers() {
	}

	@Override
	public String queryMessage() {
		return "lars this house is empty";
	}
}
