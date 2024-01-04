package eu.ansquare.squarepowered.action.entity;

import eu.ansquare.squarepowered.Squarepowered;
import eu.ansquare.squarepowered.cca.ClientStatesComponent;
import eu.ansquare.squarepowered.cca.SquareEntityComponents;
import io.github.apace100.apoli.power.factory.action.ActionFactory;
import io.github.apace100.calio.data.SerializableData;
import io.github.apace100.calio.data.SerializableDataTypes;
import net.minecraft.entity.Entity;
import net.minecraft.server.network.ServerPlayerEntity;

public class LaserEntityActions {
	public static void activateLaser(SerializableData.Instance data, Entity entity){
		if(entity instanceof ServerPlayerEntity player){
			if(SquareEntityComponents.CLIENT_STATE_COMPONENT.isProvidedBy(player)){
				ClientStatesComponent component = SquareEntityComponents.CLIENT_STATE_COMPONENT.get(player);
				component.setColors(data.getInt("red"), data.getInt("green"), data.getInt("blue"));
				component.laser = true;
				SquareEntityComponents.CLIENT_STATE_COMPONENT.sync(player);
			}
		}
	}
	public static ActionFactory<Entity> getActivateFactory(){
		return new ActionFactory<>(Squarepowered.id("activate_laser"),
				new SerializableData()
						.add("red", SerializableDataTypes.INT)
						.add("green", SerializableDataTypes.INT)
						.add("blue", SerializableDataTypes.INT),
				LaserEntityActions::activateLaser);
	}public static void deactivateLaser(SerializableData.Instance data, Entity entity){
		if(entity instanceof ServerPlayerEntity player){
			if(SquareEntityComponents.CLIENT_STATE_COMPONENT.isProvidedBy(player)){
				ClientStatesComponent component = SquareEntityComponents.CLIENT_STATE_COMPONENT.get(player);
				component.laser = false;
				SquareEntityComponents.CLIENT_STATE_COMPONENT.sync(player);
			}
		}
	}
	public static ActionFactory<Entity> getDeactivateFactory(){
		return new ActionFactory<>(Squarepowered.id("deactivate_laser"),
				new SerializableData(),
				LaserEntityActions::deactivateLaser);
	}
}
