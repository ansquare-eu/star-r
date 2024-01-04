package eu.ansquare.squarepowered.power;

import eu.ansquare.squarepowered.Squarepowered;
import io.github.apace100.apoli.data.ApoliDataTypes;
import io.github.apace100.apoli.power.Active;
import io.github.apace100.apoli.power.Power;
import io.github.apace100.apoli.power.PowerType;
import io.github.apace100.apoli.power.TogglePower;
import io.github.apace100.apoli.power.factory.PowerFactory;
import io.github.apace100.apoli.power.factory.action.ActionFactory;
import io.github.apace100.calio.data.SerializableData;
import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;

public class CreativeInvPower extends Power {
	public CreativeInvPower(PowerType<?> type, LivingEntity entity) {
		super(type, entity);
	}
	public static PowerFactory createFactory() {
		return new PowerFactory<CreativeInvPower>(Squarepowered.id("creative_inv"),
				new SerializableData(),
				data ->
						(type, player) -> {
							CreativeInvPower power = new CreativeInvPower(type, player);
							return power;
						})
				.allowCondition();
	}
}
