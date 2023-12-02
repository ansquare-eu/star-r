package eu.ansquare.starr.superdude;


import eu.ansquare.starr.items.SuperDudeInventories;
import eu.ansquare.starr.power.Powers;
import eu.ansquare.starr.util.item.ItemArrayProvider;
import eu.ansquare.starr.util.power.FlightType;
import eu.ansquare.starr.util.power.PowerOrder;
import net.minecraft.entity.attribute.EntityAttributeModifier;
import net.minecraft.entity.attribute.EntityAttributes;
import net.minecraft.item.Item;

import java.awt.*;
import java.util.UUID;

public class TestSuperDude extends SuperDude implements ItemArrayProvider {
	public TestSuperDude(FlightType flight, Color color) {
		super(flight, color);
	}

	@Override
	public void initPowers() {
		addPower(PowerOrder.TRANSFORMATION, Powers.TEST_TRANSFORMATION_POWER);
		addPower(PowerOrder.FIRST, Powers.TELEKINESIS_POWER);
		addPower(PowerOrder.SECOND, Powers.TEST_LASER_POWER);
		addPower(PowerOrder.THIRD, Powers.FORCEFIELD_POWER);
		addPower(PowerOrder.FOURTH, Powers.TELEPORT_POWER);
		addPower(PowerOrder.FIFTH, Powers.ITEM_GUI_POWER);
		addPower(PowerOrder.SIXTH, Powers.INVIS_POWER);
		addPower(PowerOrder.SEVENTH, Powers.UNDERWATER_POWER);
		addPower(PowerOrder.EIGHT, Powers.TORNADO_POWER);
		addPower(PowerOrder.NINHT, Powers.AUTO_MSG_POWER);
	}

	@Override
	public void initModifiers() {
		this.attributeModifiers.put(EntityAttributes.GENERIC_MOVEMENT_SPEED, new EntityAttributeModifier(UUID.fromString("2b242343-82be-4931-aea2-32c79ffe34f0".toUpperCase()), this.id.toString(), 0.5000000596046448, EntityAttributeModifier.Operation.MULTIPLY_TOTAL));
		this.attributeModifiers.put(EntityAttributes.GENERIC_ATTACK_DAMAGE, new EntityAttributeModifier(UUID.fromString("86100454-fdb0-4111-af63-96ca5bc05a73".toUpperCase()), this.id.toString(), 4.0, EntityAttributeModifier.Operation.ADDITION));
	}

	@Override
	public String queryMessage() {
		return "Message123";
	}

	@Override
	public Item[] get() {
		return SuperDudeInventories.testDude();
	}
}
