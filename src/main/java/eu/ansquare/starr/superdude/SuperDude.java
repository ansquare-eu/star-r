package eu.ansquare.starr.superdude;

import com.google.common.collect.Multimap;
import com.google.common.collect.MultimapBuilder;
import eu.ansquare.starr.power.Incrementable;
import eu.ansquare.starr.power.Power;
import eu.ansquare.starr.power.ToggleablePower;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.attribute.EntityAttribute;
import net.minecraft.entity.attribute.EntityAttributeModifier;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.server.network.ServerPlayerEntity;

import java.awt.*;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

public abstract class SuperDude {
	public boolean flying;
	public Color color;

	public SuperDude(boolean flying, Color color) {
		this.flying = flying;
		this.color = color;
		powers = new HashMap<>();
		attributeModifiers = MultimapBuilder.hashKeys().hashSetValues().build();
		initPowers();
	}

	public Map<PowerOrder, Power> powers;
	public Multimap<EntityAttribute, EntityAttributeModifier> attributeModifiers;

	public abstract String getName();
	public abstract void initPowers();
	public abstract void initModifiers();
	public Power getPower(PowerOrder order){
		return this.powers.get(order);
	}
	public abstract String queryMessage();
	public boolean hasPower(PowerOrder order){
		return this.powers.containsKey(order);
	}
	public Map<PowerOrder, Power> getPowers(){
		return powers;
	}
	public void onApply(PlayerEntity player){
		player.getAttributes().addTemporaryModifiers(attributeModifiers);
	}
	public void executeActiveTicks(LivingEntity entity){
		for (PowerOrder powerOrd : powers.keySet()) {
			Power power = powers.get(powerOrd);
			if (power instanceof ToggleablePower) {
				if (((ToggleablePower) power).isActiveFor(entity.getUuid())) {
					((ToggleablePower) power).activeTick(entity);
				}
			}
		}
	}
	public void executeIncrements(LivingEntity entity, boolean bool){
		for (PowerOrder powerOrd : powers.keySet()) {
			Power power = powers.get(powerOrd);
			if (power instanceof Incrementable) {
				if(power instanceof ToggleablePower){
					if(((ToggleablePower) power).isActiveFor(entity.getUuid())){
						((Incrementable) power).increment(entity.getUuid(), bool);
					}
				} else {
				((Incrementable) power).increment(entity.getUuid(), bool);
			}}
		}
	}}
