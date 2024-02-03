package eu.ansquare.squarepowered.condition;

import eu.ansquare.squarepowered.Squarepowered;
import eu.ansquare.squarepowered.util.LocalizationHandler;
import io.github.apace100.apoli.power.factory.condition.ConditionFactory;
import io.github.apace100.apoli.registry.ApoliRegistries;
import io.github.apace100.calio.data.SerializableData;
import net.minecraft.entity.Entity;
import net.minecraft.registry.Registry;
import net.minecraft.server.network.ServerPlayerEntity;

public class SquareConditions {
	public static void init(){
		registerEntity(new ConditionFactory<>(Squarepowered.id("is_localizing"), new SerializableData(), (instance, entity) -> {
			if(entity instanceof ServerPlayerEntity player){
				return LocalizationHandler.is(player);
			}
			return false;
		}));
	}
	private static void registerEntity(ConditionFactory<Entity> conditionFactory) {
		Registry.register(ApoliRegistries.ENTITY_CONDITION, conditionFactory.getSerializerId(), conditionFactory);
	}
}
