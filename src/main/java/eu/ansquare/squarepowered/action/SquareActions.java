package eu.ansquare.squarepowered.action;

import eu.ansquare.squarepowered.Squarepowered;
import eu.ansquare.squarepowered.action.bientity.TelekinesActions;
import eu.ansquare.squarepowered.action.entity.AirwalkEntityAction;
import eu.ansquare.squarepowered.action.entity.WorldStructureAction;
import io.github.apace100.apoli.Apoli;
import io.github.apace100.apoli.power.factory.action.ActionFactory;
import io.github.apace100.apoli.power.factory.action.BiEntityActions;
import io.github.apace100.apoli.power.factory.action.EntityActions;
import io.github.apace100.apoli.registry.ApoliRegistries;
import io.github.apace100.calio.data.SerializableData;
import net.minecraft.entity.Entity;
import net.minecraft.registry.Registry;
import net.minecraft.util.Pair;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.world.World;
import org.apache.commons.lang3.tuple.Triple;

public class SquareActions {
	public static void init(){
		registerEntity(AirwalkEntityAction.getFactory());
		registerEntity(new ActionFactory<>(Squarepowered.id("airwalk_stop"), new SerializableData(),
				AirwalkEntityAction::delete));
		registerEntity(WorldStructureAction.getCreateFactory());
		registerEntity(WorldStructureAction.getDeleteFactory());
		registerEntity(TelekinesActions.getEndFactory());
		registerEntity(TelekinesActions.getTickFactory());
		registerBiEntity(TelekinesActions.getStartFactory());
		registerEntity(TelekinesActions.getSetTaskFactory());
		registerBlock(TelekinesActions.getGrabBlockFactory());
	}
	private static void registerEntity(ActionFactory<Entity> actionFactory) {
		Registry.register(ApoliRegistries.ENTITY_ACTION, actionFactory.getSerializerId(), actionFactory);
	}
	private static void registerBlock(ActionFactory<Triple<World, BlockPos, Direction>> actionFactory) {
		Registry.register(ApoliRegistries.BLOCK_ACTION, actionFactory.getSerializerId(), actionFactory);
	}
	private static void registerBiEntity(ActionFactory<Pair<Entity, Entity>> actionFactory) {
		Registry.register(ApoliRegistries.BIENTITY_ACTION, actionFactory.getSerializerId(), actionFactory);
	}
}
