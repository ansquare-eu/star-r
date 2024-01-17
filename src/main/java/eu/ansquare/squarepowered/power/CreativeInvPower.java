package eu.ansquare.squarepowered.power;

import eu.ansquare.squarepowered.Squarepowered;
import io.github.apace100.apoli.component.PowerHolderComponent;
import io.github.apace100.apoli.data.ApoliDataTypes;
import io.github.apace100.apoli.power.Active;
import io.github.apace100.apoli.power.Power;
import io.github.apace100.apoli.power.PowerType;
import io.github.apace100.apoli.power.TogglePower;
import io.github.apace100.apoli.power.factory.PowerFactory;
import io.github.apace100.apoli.power.factory.action.ActionFactory;
import io.github.apace100.calio.data.SerializableData;
import io.github.apace100.calio.data.SerializableDataTypes;
import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;

import java.util.List;

public class CreativeInvPower extends Power {
	public final boolean sign;
	public CreativeInvPower(PowerType<?> type, LivingEntity entity, boolean sign) {
		super(type, entity);
		this.sign = sign;
	}
	public static boolean areStacksSigned(LivingEntity entity){
		List<CreativeInvPower> list = PowerHolderComponent.getPowers(entity, CreativeInvPower.class);
		return list.stream().anyMatch(creativeInvPower -> creativeInvPower.sign == true);
	}
	public static PowerFactory createFactory() {
		return new PowerFactory<CreativeInvPower>(Squarepowered.id("creative_inv"),
				new SerializableData()
						.add("sign_stacks", SerializableDataTypes.BOOLEAN, true),
				data ->
						(type, player) -> {
							CreativeInvPower power = new CreativeInvPower(type, player, data.getBoolean("sign_stacks"));
							return power;
						})
				.allowCondition();
	}
}
