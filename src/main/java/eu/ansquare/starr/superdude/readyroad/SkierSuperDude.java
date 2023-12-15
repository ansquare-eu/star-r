package eu.ansquare.starr.superdude.readyroad;

import eu.ansquare.starr.power.AnonymousToggleablePower;
import eu.ansquare.starr.power.offense.PointerPower;
import eu.ansquare.starr.power.transport.AirwalkPower;
import eu.ansquare.starr.power.utility.EffectPower;
import eu.ansquare.starr.power.utility.ToggleableAttributePower;
import eu.ansquare.starr.superdude.SuperDude;
import eu.ansquare.starr.util.power.FlightType;
import eu.ansquare.starr.util.power.PowerOrder;
import net.minecraft.block.Blocks;
import net.minecraft.enchantment.FrostWalkerEnchantment;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.attribute.EntityAttribute;
import net.minecraft.entity.attribute.EntityAttributeModifier;
import net.minecraft.entity.attribute.EntityAttributes;
import net.minecraft.entity.effect.StatusEffect;
import net.minecraft.entity.effect.StatusEffects;
import net.minecraft.particle.DustParticleEffect;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.util.math.Vec3d;
import org.joml.Vector3f;
import org.quiltmc.loader.impl.lib.sat4j.core.Vec;

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
		addPower(PowerOrder.THIRD, new AnonymousToggleablePower((entity -> FrostWalkerEnchantment.freezeWater(entity, entity.getWorld(), entity.getBlockPos(), 2))));
		addPower(PowerOrder.FOURTH, new EffectPower(StatusEffects.JUMP_BOOST, 2));
	}

	@Override
	public void initModifiers() {
		this.attributeModifiers.put(EntityAttributes.GENERIC_MOVEMENT_SPEED, new EntityAttributeModifier(UUID.fromString("2b242343-82be-4831-aea2-32c79ffe34f0".toUpperCase()), this.id.toString(), 0.5000000596046448, EntityAttributeModifier.Operation.MULTIPLY_TOTAL));

	}

	@Override
	public String queryMessage() {
		return "Extremely random";
	}
}
