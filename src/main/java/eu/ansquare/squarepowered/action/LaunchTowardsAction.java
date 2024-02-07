package eu.ansquare.squarepowered.action;

import eu.ansquare.squarepowered.Squarepowered;
import io.github.apace100.apoli.power.factory.action.ActionFactory;
import io.github.apace100.calio.data.SerializableData;
import io.github.apace100.calio.data.SerializableDataTypes;
import net.minecraft.command.argument.EntityAnchorArgumentType;
import net.minecraft.entity.Entity;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.util.Pair;

public class LaunchTowardsAction {
	public static void action(SerializableData.Instance data, Pair<Entity, Entity> entities){
		if(entities.getLeft().getWorld() instanceof ServerWorld world){
			if(data.getBoolean("rotate")) entities.getLeft().lookAt(EntityAnchorArgumentType.EntityAnchor.EYES, entities.getRight().getEyePos());
			entities.getLeft().addVelocity(entities.getRight().getPos().subtract(entities.getLeft().getEyePos()).normalize().multiply(data.getInt("speed")));
			entities.getLeft().velocityModified = true;
		}
	}
	public static ActionFactory<Pair<Entity, Entity>> getFactory(){
		return new ActionFactory<>(Squarepowered.id("launch_to"),
				new SerializableData()
						.add("rotate", SerializableDataTypes.BOOLEAN, true)
						.add("speed", SerializableDataTypes.INT, 10),
				LaunchTowardsAction::action);
	}
}
