package eu.ansquare.squarepowered.action.bientity;

import eu.ansquare.squarepowered.Squarepowered;
import eu.ansquare.squarepowered.action.entity.WorldStructureAction;
import eu.ansquare.squarepowered.util.SquareDataTypes;
import io.github.apace100.apoli.power.factory.action.ActionFactory;
import io.github.apace100.calio.data.SerializableData;
import io.github.apace100.calio.data.SerializableDataTypes;
import net.minecraft.entity.Entity;
import net.minecraft.entity.FallingBlockEntity;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.util.Pair;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;
import org.apache.commons.lang3.tuple.Triple;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

public class TelekinesActions {
	public static Map<UUID, Entity> entities = new HashMap<>();
	public static Map<UUID, Integer> distances = new HashMap<>();
	public static UUID nextTaskedEntity = null;
	public static void startAction(SerializableData.Instance data, Pair<Entity, Entity> entity) {
		if (entity.getLeft() instanceof ServerPlayerEntity && !entities.containsValue(entity.getRight())) {
			if(entity.getLeft().getUuid().equals(nextTaskedEntity)) nextTaskedEntity = null;
			entities.put(entity.getLeft().getUuid(), entity.getRight());
			entity.getRight().setInvulnerable(true);
		}
	}
	public static void endAction(SerializableData.Instance data, Entity entity) {
		if(entity instanceof ServerPlayerEntity && entities.containsKey(entity.getUuid())){
			entities.get(entity.getUuid()).setInvulnerable(false);
			entities.get(entity.getUuid()).setNoGravity(false);
			entities.remove(entity.getUuid());
		}
	}
	public static void setTaskAction(SerializableData.Instance data, Entity entity){
		if(entity instanceof ServerPlayerEntity) nextTaskedEntity = entity.getUuid();
	}
	public static void grabBlockAction(SerializableData.Instance data, Triple<World, BlockPos, Direction> stuff){
		if(nextTaskedEntity != null && stuff.getLeft() instanceof ServerWorld world){
			if(world.getEntity(nextTaskedEntity) instanceof ServerPlayerEntity player){
				FallingBlockEntity fallingBlock = FallingBlockEntity.fall(world, stuff.getMiddle(), world.getBlockState(stuff.getMiddle()));
				fallingBlock.setNoGravity(true);
				entities.put(player.getUuid(), fallingBlock);
				nextTaskedEntity = null;

			}
		}
	}
	public static void tickAction(SerializableData.Instance data, Entity entity) {
		if(!distances.containsKey(entity.getUuid())){
			distances.put(entity.getUuid(), data.getInt("default_distance"));
		}
		if(entities.containsKey(entity.getUuid())){
			Entity entity1 = entities.get(entity.getUuid());
			Vec3d playerDir = entity.getRotationVec(1f).normalize().multiply(distances.get(entity.getUuid()));
			Vec3d dest = entity.getEyePos().add(playerDir);
			entity1.teleport(dest.x, dest.y, dest.z);
		}
	}
	public static void incrementAction(SerializableData.Instance data, Entity entity) {
		if(entity instanceof ServerPlayerEntity){
		if(data.getBoolean("increment")){
			distances.put(entity.getUuid(), distances.get(entity.getUuid()) + data.getInt("amount"));
		} else {
			distances.put(entity.getUuid(), distances.get(entity.getUuid()) - data.getInt("amount"));
		}}
	}
	public static ActionFactory<Entity> getSetTaskFactory() {
		return new ActionFactory<>(Squarepowered.id("telekinesis_task"),
				new SerializableData(),
				TelekinesActions::setTaskAction
		);
	}
	public static ActionFactory<Entity> getIncrementFactory() {
		return new ActionFactory<>(Squarepowered.id("telekinesis_increment"),
				new SerializableData()
						.add("increment", SerializableDataTypes.BOOLEAN)
						.add("amount", SerializableDataTypes.INT),
				TelekinesActions::incrementAction
		);
	}
	public static ActionFactory<Triple<World, BlockPos, Direction>> getGrabBlockFactory() {
		return new ActionFactory<>(Squarepowered.id("telekinesis_block"),
				new SerializableData(),
				TelekinesActions::grabBlockAction
		);
	}
	public static ActionFactory<Pair<Entity, Entity>> getStartFactory() {
		return new ActionFactory<>(Squarepowered.id("telekinesis_start"),
				new SerializableData(),
				TelekinesActions::startAction
		);
	}
	public static ActionFactory<Entity> getEndFactory() {
		return new ActionFactory<>(Squarepowered.id("telekinesis_end"),
				new SerializableData(),
				TelekinesActions::endAction
		);
	}
	public static ActionFactory<Entity> getTickFactory() {
		return new ActionFactory<>(Squarepowered.id("telekinesis_tick"),
				new SerializableData()
						.add("default_distance", SerializableDataTypes.INT, 5),
				TelekinesActions::tickAction
		);
	}
}
