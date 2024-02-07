package eu.ansquare.squarepowered.action;

import eu.ansquare.squarepowered.Squarepowered;
import eu.ansquare.squarepowered.util.LocalizationHandler;
import io.github.apace100.apoli.power.factory.action.ActionFactory;
import io.github.apace100.apoli.registry.ApoliRegistries;
import io.github.apace100.calio.data.SerializableData;
import io.github.apace100.calio.data.SerializableDataTypes;
import net.minecraft.entity.Entity;
import net.minecraft.registry.Registry;
import net.minecraft.server.network.ServerPlayerEntity;
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
		registerEntity(new ActionFactory<>(Squarepowered.id("end_localize"), new SerializableData(), ((instance, entity) -> {
			if(entity instanceof ServerPlayerEntity player) LocalizationHandler.end(player);
		})));
		registerEntity(WorldStructureAction.getCreateFactory());
		registerEntity(WorldStructureAction.getDeleteFactory());
		registerEntity(TelekinesActions.getEndFactory());
		registerEntity(TelekinesActions.getTickFactory());
		registerBiEntity(TelekinesActions.getStartFactory());
		registerEntity(TelekinesActions.getSetTaskFactory());
		registerBlock(TelekinesActions.getGrabBlockFactory());
		registerEntity(TelekinesActions.getIncrementFactory());
		registerEntity(OpenActionScreenAction.getOpenActionScreenFactory());
		registerEntity(OpenContainerEntityAction.getOpenActionScreenFactory());
		registerEntity(LaserEntityActions.getActivateFactory());
		registerEntity(LaserEntityActions.getDeactivateFactory());
		registerEntity(SummonMountedAction.getFactory());
		registerEntity(AddFreezingTicks.getFactory());
		registerEntity(FreezeWaterAction.getFactory());
		registerEntity(OpenActionScreenAction.getRemoveTaskFactory());
		registerBiEntity(OpenActionScreenAction.getAddTaskFactory());
		registerEntity(SetInventoryAction.getFactory());
		registerEntity(TpToDimensionAction.getFactory());
		registerBiEntity(AggravateActions.getFromFactory());
		registerEntity(AggravateActions.getAroundFactory());
		registerBiEntity(LaunchTowardsAction.getFactory());
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
