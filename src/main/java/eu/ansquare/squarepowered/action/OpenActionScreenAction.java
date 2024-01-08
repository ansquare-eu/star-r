package eu.ansquare.squarepowered.action;

import eu.ansquare.squarepowered.SquareRegistries;
import eu.ansquare.squarepowered.Squarepowered;
import eu.ansquare.squarepowered.cca.SquareEntityComponents;
import io.github.apace100.apoli.power.factory.action.ActionFactory;
import io.github.apace100.calio.data.SerializableData;
import io.github.apace100.calio.data.SerializableDataTypes;
import net.minecraft.entity.Entity;
import net.minecraft.entity.MovementFlag;
import net.minecraft.screen.ScreenHandlerType;
import net.minecraft.screen.SimpleNamedScreenHandlerFactory;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.text.Text;
import net.minecraft.util.Pair;
import net.minecraft.world.World;

import java.util.HashMap;
import java.util.Map;

public class OpenActionScreenAction {
	public static Map<ServerPlayerEntity, Entity> map = new HashMap<>();
	public static void action(SerializableData.Instance data, Entity entity){
		ScreenHandlerType type = SquareRegistries.ACTION_SCREEN.get(data.getId("screen"));
		if(entity instanceof ServerPlayerEntity player){
			SquareEntityComponents.SAVED_LOCATION_COMPONENT.sync(player);
			player.openHandledScreen(new SimpleNamedScreenHandlerFactory(((i, playerInventory, playerEntity) -> type.create(i, playerInventory)), Text.translatable("screen.starr."+data.getId("screen").getPath()+".title")));
		}
	}
	public static ActionFactory<Entity> getOpenActionScreenFactory() {
		return new ActionFactory<>(Squarepowered.id("open_action_screen"),
				new SerializableData()
						.add("screen", SerializableDataTypes.IDENTIFIER),
				OpenActionScreenAction::action
		);
	}
	public static void addTaskAction(SerializableData.Instance data, Pair<Entity, Entity> entities){
		if(entities.getLeft() instanceof ServerPlayerEntity actor){
			map.put(actor, entities.getRight());
		}
	}
	public static ActionFactory<Pair<Entity, Entity>> getAddTaskFactory() {
		return new ActionFactory<>(Squarepowered.id("add_task"),
				new SerializableData(),
				OpenActionScreenAction::addTaskAction
		);
	}
	public static void removeTaskAction(SerializableData.Instance data, Entity entity){
		if(entity instanceof ServerPlayerEntity actor){
			map.remove(actor);
		}
	}
	public static boolean processTeleportation(ServerPlayerEntity player, double x, double y, double z, ServerWorld world){
		if(map.containsKey(player)){
			map.get(player).teleport(world, x ,y ,z, MovementFlag.ALL, player.getYaw(), player.getPitch());
			return true;
		} else {
			player.teleport(world, x ,y ,z, player.getYaw(), player.getPitch());
			return false;
		}
	}
	public static ActionFactory<Entity> getRemoveTaskFactory() {
		return new ActionFactory<>(Squarepowered.id("remove_task"),
				new SerializableData(),
				OpenActionScreenAction::removeTaskAction
		);
	}
}
