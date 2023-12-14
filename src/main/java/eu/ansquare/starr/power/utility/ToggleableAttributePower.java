package eu.ansquare.starr.power.utility;

import eu.ansquare.starr.power.ToggleablePower;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.attribute.EntityAttribute;
import net.minecraft.entity.attribute.EntityAttributeInstance;
import net.minecraft.entity.attribute.EntityAttributeModifier;
import net.minecraft.server.network.ServerPlayerEntity;

public class ToggleableAttributePower extends ToggleablePower {
	public final EntityAttributeModifier modifier;
	public final EntityAttribute attribute;

	public ToggleableAttributePower(EntityAttribute attribute, EntityAttributeModifier modifier) {
		this.modifier = modifier;
		this.attribute = attribute;
	}

	@Override
	public void activationAction(ServerPlayerEntity player) {
		EntityAttributeInstance entityAttributeInstance = player.getAttributes().createIfAbsent(attribute);
		if (entityAttributeInstance != null) {

			entityAttributeInstance.removeModifier(modifier);
			entityAttributeInstance.addPersistentModifier(modifier);
		}
	}

	@Override
	public void deactivationAction(ServerPlayerEntity player) {
		EntityAttributeInstance entityAttributeInstance = player.getAttributes().createIfAbsent(attribute);
		if (entityAttributeInstance != null) {

			entityAttributeInstance.removeModifier(modifier);
		}
	}

	@Override
	public void activeTick(LivingEntity entity) {

	}
}
