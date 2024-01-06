package eu.ansquare.squarepowered.action.entity;

import eu.ansquare.squarepowered.Squarepowered;
import eu.ansquare.squarepowered.cca.ClientStatesComponent;
import eu.ansquare.squarepowered.cca.SquareEntityComponents;
import io.github.apace100.apoli.power.factory.action.ActionFactory;
import io.github.apace100.apoli.util.MiscUtil;
import io.github.apace100.calio.data.SerializableData;
import io.github.apace100.calio.data.SerializableDataTypes;
import net.minecraft.entity.Entity;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.server.world.ServerWorld;

import java.util.Optional;

public class SummonMountedAction {
	public static void action(SerializableData.Instance data, Entity entity){
		if(entity.getWorld() instanceof ServerWorld world){
			Optional<Entity> opt$entityToSpawn = MiscUtil.getEntityWithPassengers(
					world,
					data.get("entity"),
					data.get("tag"),
					entity.getPos().add(0, entity.getEyeHeight(entity.getPose()), 0),
					entity.getYaw(),
					entity.getPitch()
			);

			if (opt$entityToSpawn.isEmpty()) return;
			Entity entity1 = opt$entityToSpawn.get();
			entity1.startRiding(entity);
			world.spawnEntity(entity1);
		}
	}
	public static ActionFactory<Entity> getFactory(){
		return new ActionFactory<>(Squarepowered.id("summon_mounted"),
				new SerializableData()
						.add("entity", SerializableDataTypes.ENTITY_TYPE)
						.add("tag", SerializableDataTypes.NBT, new NbtCompound()),
				SummonMountedAction::action);
	}
}
