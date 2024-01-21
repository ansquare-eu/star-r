package eu.ansquare.squarepowered.action;

import eu.ansquare.squarepowered.Squarepowered;
import eu.ansquare.squarepowered.cca.MultiInventoryComponent;
import io.github.apace100.apoli.power.factory.action.ActionFactory;
import io.github.apace100.calio.data.SerializableData;
import io.github.apace100.calio.data.SerializableDataTypes;
import net.minecraft.entity.Entity;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.server.world.ServerWorld;

public class SetInventoryAction {
	public static void action(SerializableData.Instance data, Entity entity){
		if(entity instanceof ServerPlayerEntity player){
			MultiInventoryComponent.set(player, data.getInt("inventory"));
			MultiInventoryComponent.setProperties(player, data.getBoolean("prevent_item_transfer"), data.getBoolean("no_drop"));

		}
	}
	public static ActionFactory<Entity> getFactory(){
		return new ActionFactory<>(Squarepowered.id("set_inventory"),
				new SerializableData()
						.add("inventory", SerializableDataTypes.INT, -1)
						.add("prevent_item_transfer", SerializableDataTypes.BOOLEAN, false)
						.add("no_drop", SerializableDataTypes.BOOLEAN, false),
				SetInventoryAction::action);
	}
}
