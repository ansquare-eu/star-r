package eu.ansquare.starr.superdude;


import eu.ansquare.starr.power.Power;
import eu.ansquare.starr.power.Powers;
import net.minecraft.entity.attribute.EntityAttribute;
import net.minecraft.entity.attribute.EntityAttributeModifier;
import net.minecraft.entity.attribute.EntityAttributes;

import java.awt.*;
import java.util.UUID;

public class TestSuperDude extends SuperDude{
	public TestSuperDude(boolean flying, Color color) {
		super(flying, color);
	}

	@Override
	public String getName() {
		return "testType";
	}

	@Override
	public void initPowers() {
		this.powers.put(PowerOrder.TRANSFORMATION, Powers.TEST_TRANSFORMATION_POWER);
		this.powers.put(PowerOrder.FIRST, Powers.TELEKINESIS_POWER);
		this.powers.put(PowerOrder.SECOND, Powers.TEST_LASER_POWER);
		this.powers.put(PowerOrder.THIRD, Powers.FORCEFIELD_POWER);
		this.powers.put(PowerOrder.FOURTH, Powers.TELEPORT_POWER);
	}

	@Override
	public void initModifiers() {
		this.attributeModifiers.put(EntityAttributes.GENERIC_MOVEMENT_SPEED, new EntityAttributeModifier(UUID.fromString("2b242343-82be-4931-aea2-32c79ffe34f0".toUpperCase()), this.getName(), 0.5000000596046448, EntityAttributeModifier.Operation.MULTIPLY_TOTAL));
	}

	@Override
	public String queryMessage() {
		return "Message123";
	}
}
