package eu.ansquare.squarepowered.action;

import eu.ansquare.squarepowered.Squarepowered;
import io.github.apace100.apoli.power.factory.action.ActionFactory;
import io.github.apace100.calio.data.SerializableData;
import io.github.apace100.calio.data.SerializableDataTypes;
import net.minecraft.enchantment.FrostWalkerEnchantment;
import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.server.world.ServerWorld;

public class FreezeWaterAction {
	public static void action(SerializableData.Instance data, Entity entity){
		if(entity.getWorld() instanceof ServerWorld world && entity instanceof LivingEntity living){
			FrostWalkerEnchantment.freezeWater(living, world, living.getBlockPos(), data.getInt("level"));
		}
	}
	public static ActionFactory<Entity> getFactory(){
		return new ActionFactory<>(Squarepowered.id("freeze_water"),
				new SerializableData()
						.add("level", SerializableDataTypes.INT, 1),
				FreezeWaterAction::action);
	}
}
