package eu.ansquare.squarepowered.action.entity;

import eu.ansquare.SquareRegistries;
import eu.ansquare.squarepowered.Squarepowered;
import eu.ansquare.squarepowered.util.SquareDataTypes;
import eu.ansquare.squarepowered.worldstructure.WorldStructure;
import eu.ansquare.squarepowered.worldstructure.WorldStructureFactory;
import io.github.apace100.apoli.power.factory.action.ActionFactory;
import io.github.apace100.calio.data.SerializableData;
import io.github.apace100.calio.data.SerializableDataTypes;
import net.minecraft.entity.Entity;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.util.Pair;
import net.minecraft.util.math.BlockPos;

import java.util.*;

public class WorldStructureAction {
	public static Map<ServerPlayerEntity, WorldStructure> map = new HashMap();
	public static void delete(SerializableData.Instance data, Entity entity){
		if (entity instanceof ServerPlayerEntity player) {
			if(map.containsKey(player)){
				WorldStructure worldStructure = map.get(player);
				worldStructure.delete();
			}
		}
	}
	public static void create(SerializableData.Instance data, Entity entity) {
		if (entity instanceof ServerPlayerEntity player) {
			if(map.containsKey(player)){
				delete(data, entity);
			}
			WorldStructureFactory factory = SquareRegistries.WORLD_STRUCTURE.get(data.getId("world_structure"));
			WorldStructure structure = factory.build(data);
			structure.construct(player.getServerWorld(), player.getBlockPos(), data.get("blocks"));
		}
	}

	public static ActionFactory<Entity> getCreateFactory() {
		return new ActionFactory<>(Squarepowered.id("create_world_structure"),
				new SerializableData()
						.add("blocks", SquareDataTypes.BLOCK_STATES)
						.add("diameter", SerializableDataTypes.INT),
				WorldStructureAction::create
		);
	}
	public static ActionFactory<Entity> getDeleteFactory() {
		return new ActionFactory<>(Squarepowered.id("delete_world_structure"),
				new SerializableData(),
				WorldStructureAction::delete
		);
	}
}
