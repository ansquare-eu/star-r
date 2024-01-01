package eu.ansquare.squarepowered.action.entity;

import eu.ansquare.squarepowered.Squarepowered;
import io.github.apace100.apoli.Apoli;
import io.github.apace100.apoli.power.factory.action.ActionFactory;
import io.github.apace100.apoli.power.factory.action.EntityActions;
import io.github.apace100.apoli.registry.ApoliRegistries;
import io.github.apace100.calio.data.SerializableData;
import net.minecraft.entity.Entity;
import net.minecraft.registry.Registry;

public class SquareEntityActions {
	public static void init(){
		register(AirwalkEntityAction.getFactory());
		register(new ActionFactory<>(Squarepowered.id("airwalk_stop"), new SerializableData(),
				AirwalkEntityAction::delete));
		register(WorldStructureAction.getCreateFactory());
		register(WorldStructureAction.getDeleteFactory());
	}
	private static void register(ActionFactory<Entity> actionFactory) {
		Registry.register(ApoliRegistries.ENTITY_ACTION, actionFactory.getSerializerId(), actionFactory);
	}
}
