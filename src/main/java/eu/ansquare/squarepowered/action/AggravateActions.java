package eu.ansquare.squarepowered.action;

import eu.ansquare.squarepowered.Squarepowered;
import io.github.apace100.apoli.power.factory.action.ActionFactory;
import io.github.apace100.calio.data.SerializableData;
import io.github.apace100.calio.data.SerializableDataTypes;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.mob.MobEntity;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.util.Pair;
import net.minecraft.util.TypeFilter;

import java.util.List;

public class AggravateActions {
	public static void aggravateFrom(SerializableData.Instance data, Pair<Entity, Entity> entity){
		if(entity.getRight() instanceof LivingEntity target && entity.getRight().getWorld() instanceof ServerWorld world){
			Entity actor = entity.getLeft();
			EntityType type = data.get("entity_type");
			List<Entity> list = world.getEntitiesByType(type, (entity1 -> entity1.distanceTo(actor) <= 10));
			list.forEach(entity1 -> {
				if(entity1 instanceof MobEntity mob){
					mob.setTarget(target);
				}
			});
		}
	}
	public static void aggravateAround(SerializableData.Instance data, Entity entity){
		if(entity instanceof LivingEntity livingEntity && entity.getWorld() instanceof ServerWorld world){
			EntityType type = data.get("entity_type");
			List<Entity> list = world.getEntitiesByType(type, (entity1 -> entity1.distanceTo(livingEntity) <= 10));
			list.forEach(entity1 -> {
				if(entity1 instanceof MobEntity mob){
					mob.setTarget(livingEntity);
				}
			});
		}
	}
	public static ActionFactory<Pair<Entity, Entity>> getFromFactory(){
		return new ActionFactory<>(Squarepowered.id("aggravate_from"),
				new SerializableData()
						.add("entity_type", SerializableDataTypes.ENTITY_TYPE)
						.add("range", SerializableDataTypes.INT, 10),
				AggravateActions::aggravateFrom);
	}
	public static ActionFactory<Entity> getAroundFactory(){
		return new ActionFactory<>(Squarepowered.id("aggravate_around"),
				new SerializableData()
						.add("entity_type", SerializableDataTypes.ENTITY_TYPE)
						.add("range", SerializableDataTypes.INT, 10),
				AggravateActions::aggravateAround);
	}
}
