package eu.ansquare.squarepowered.power;

import io.github.apace100.apoli.power.Power;
import io.github.apace100.apoli.power.PowerType;
import net.minecraft.entity.LivingEntity;

public class AutoMsgPower extends Power {
	public AutoMsgPower(PowerType<?> type, LivingEntity entity) {
		super(type, entity);
	}
}
