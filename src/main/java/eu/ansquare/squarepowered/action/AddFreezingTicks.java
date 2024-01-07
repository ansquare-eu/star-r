package eu.ansquare.squarepowered.action;

import eu.ansquare.squarepowered.Squarepowered;
import io.github.apace100.apoli.power.factory.action.ActionFactory;
import io.github.apace100.calio.data.SerializableData;
import io.github.apace100.calio.data.SerializableDataTypes;
import net.minecraft.entity.Entity;
import net.minecraft.server.world.ServerWorld;

public class AddFreezingTicks {
	public static void action(SerializableData.Instance data, Entity entity){
		Squarepowered.log(String.valueOf(data.getInt("amount")), 0);
		if(entity.getWorld() instanceof ServerWorld world){
			entity.setFrozenTicks(entity.getFrozenTicks() + data.getInt("amount"));
		}
	}
	public static ActionFactory<Entity> getFactory(){
		return new ActionFactory<>(Squarepowered.id("add_freezing_ticks"),
				new SerializableData()
						.add("amount", SerializableDataTypes.INT, 0),
				AddFreezingTicks::action);
	}
}
