package eu.ansquare.squarepowered.power;

import eu.ansquare.squarepowered.Squarepowered;
import io.github.apace100.apoli.power.Power;
import io.github.apace100.apoli.power.PowerType;
import io.github.apace100.apoli.power.factory.PowerFactory;
import io.github.apace100.calio.data.SerializableData;
import net.minecraft.entity.LivingEntity;

public class PreventFreezingPower extends Power {
	public PreventFreezingPower(PowerType<?> type, LivingEntity entity) {
		super(type, entity);
	}
	public static PowerFactory createFactory() {
		return new PowerFactory<PreventFreezingPower>(Squarepowered.id("prevent_freezing"),
				new SerializableData(),
				data ->
						(type, player) -> {
							PreventFreezingPower power = new PreventFreezingPower(type, player);
							return power;
						})
				.allowCondition();
	}
}
