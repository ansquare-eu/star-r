package eu.ansquare.starr.superdude.readyroad;

import eu.ansquare.starr.power.offense.PointerPower;
import eu.ansquare.starr.power.transport.AirwalkPower;
import eu.ansquare.starr.superdude.SuperDude;
import eu.ansquare.starr.util.power.FlightType;
import eu.ansquare.starr.util.power.PowerOrder;
import net.minecraft.block.Blocks;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.attribute.EntityAttributeModifier;
import net.minecraft.entity.attribute.EntityAttributes;

import java.awt.*;
import java.util.UUID;

public class SkierSuperDude extends SuperDude {
	public SkierSuperDude(FlightType flight, Color color) {
		super(flight, color);
	}

	@Override
	public void initPowers() {
		addPower(PowerOrder.FIRST, new AirwalkPower(Blocks.ICE, 20));
		addPower(PowerOrder.SECOND, new PointerPower(((entity, player) -> {
			if(entity instanceof LivingEntity living){
				living.setFrozenTicks(1000);
			}
		}), 64));
	}

	@Override
	public void initModifiers() {
		this.attributeModifiers.put(EntityAttributes.GENERIC_MOVEMENT_SPEED, new EntityAttributeModifier(UUID.fromString("2b242343-82be-4831-aea2-32c79ffe34f0".toUpperCase()), this.id.toString(), 0.5000000596046448, EntityAttributeModifier.Operation.MULTIPLY_TOTAL));

	}

	@Override
	public String queryMessage() {
		return "Extremely rendom";
	}
}
