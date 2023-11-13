package eu.ansquare.starr.superdude;

import eu.ansquare.starr.power.Incrementable;
import eu.ansquare.starr.power.Power;
import eu.ansquare.starr.power.ToggleablePower;
import eu.ansquare.starr.util.power.FlightType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.attribute.EntityAttribute;
import net.minecraft.entity.attribute.EntityAttributeInstance;
import net.minecraft.entity.attribute.EntityAttributeModifier;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.util.Identifier;

import java.awt.*;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

public abstract class SuperDude {
	public Identifier id;
	public FlightType flight;
	public Color color;

	public SuperDude(FlightType flight, Color color) {
		this.flight = flight;
		this.color = color;
		powers = new HashMap<>();
		attributeModifiers = new HashMap<>();
	}
	public void init(Identifier id){
		this.id = id;
		initPowers();
		initModifiers();
	}
	public Map<PowerOrder, Power> powers;
	public Map<EntityAttribute, EntityAttributeModifier> attributeModifiers;

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
		if(flight == FlightType.CREATIVE){
			player.getAbilities().allowFlying = true;
			player.sendAbilitiesUpdate();
		}
		Iterator iterator = this.attributeModifiers.entrySet().iterator();

		while(iterator.hasNext()) {
			Map.Entry<EntityAttribute, EntityAttributeModifier> entry = (Map.Entry)iterator.next();
			EntityAttributeInstance entityAttributeInstance = player.getAttributes().createIfAbsent(entry.getKey());
			if (entityAttributeInstance != null) {
				EntityAttributeModifier entityAttributeModifier = entry.getValue();
				entityAttributeInstance.removeModifier(entityAttributeModifier);
				entityAttributeInstance.addPersistentModifier(entityAttributeModifier);
			}
		}
	}
	public void addPower(PowerOrder order, Power power){
		this.powers.put(order, power);
	}

	public void onRemove(PlayerEntity player) {
		if(flight == FlightType.CREATIVE){
			player.getAbilities().allowFlying = false;
			player.getAbilities().flying = false;
			player.sendAbilitiesUpdate();
		}
		Iterator iterator = this.attributeModifiers.entrySet().iterator();

		while(iterator.hasNext()) {
			Map.Entry<EntityAttribute, EntityAttributeModifier> entry = (Map.Entry)iterator.next();
			EntityAttributeInstance entityAttributeInstance = player.getAttributes().createIfAbsent(entry.getKey());
			if (entityAttributeInstance != null) {
				EntityAttributeModifier entityAttributeModifier = entry.getValue();
				entityAttributeInstance.removeModifier(entityAttributeModifier);
			}
		}
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
