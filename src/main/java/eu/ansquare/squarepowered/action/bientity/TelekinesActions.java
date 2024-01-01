package eu.ansquare.squarepowered.action.bientity;

import eu.ansquare.squarepowered.Squarepowered;
import eu.ansquare.squarepowered.action.entity.WorldStructureAction;
import eu.ansquare.squarepowered.util.SquareDataTypes;
import io.github.apace100.apoli.power.factory.action.ActionFactory;
import io.github.apace100.calio.data.SerializableData;
import io.github.apace100.calio.data.SerializableDataTypes;
import net.minecraft.entity.Entity;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.util.Pair;
import net.minecraft.util.math.Vec3d;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

public class TelekinesActions {
	public static Map<UUID, Entity> entities = new HashMap<>();
	public static void startAction(SerializableData.Instance data, Pair<Entity, Entity> entity) {
		if (entity.getLeft() instanceof ServerPlayerEntity && !entities.containsValue(entity.getRight())) {
			entities.put(entity.getLeft().getUuid(), entity.getRight());
			entity.getRight().setInvulnerable(true);
		}
	}
	public static void endAction(SerializableData.Instance data, Entity entity) {
		if(entity instanceof ServerPlayerEntity && entities.containsKey(entity.getUuid())){
			entities.get(entity.getUuid()).setInvulnerable(false);
			entities.remove(entity.getUuid());
		}
	}
	public static void tickAction(SerializableData.Instance data, Entity entity) {
		if(entities.containsKey(entity.getUuid())){
			Entity entity1 = entities.get(entity.getUuid());
			Vec3d playerDir = entity.getRotationVec(1f).normalize().multiply(5);
			Vec3d dest = entity.getEyePos().add(playerDir);
			entity1.teleport(dest.x, dest.y, dest.z);
		}
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
				new SerializableData(),
				TelekinesActions::tickAction
		);
	}
}
