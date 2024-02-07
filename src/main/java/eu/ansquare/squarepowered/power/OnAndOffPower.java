package eu.ansquare.squarepowered.power;

import eu.ansquare.squarepowered.Squarepowered;
import io.github.apace100.apoli.Apoli;
import io.github.apace100.apoli.component.PowerHolderComponent;
import io.github.apace100.apoli.data.ApoliDataTypes;
import io.github.apace100.apoli.power.Active;
import io.github.apace100.apoli.power.PowerType;
import io.github.apace100.apoli.power.TogglePower;
import io.github.apace100.apoli.power.factory.PowerFactory;
import io.github.apace100.apoli.power.factory.action.ActionFactory;
import io.github.apace100.calio.data.SerializableData;
import io.github.apace100.calio.data.SerializableDataTypes;
import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;

import java.util.function.Consumer;

public class OnAndOffPower extends TogglePower {
	private final Consumer<Entity> entityActionOn;
	private final Consumer<Entity> entityActionOff;

	public OnAndOffPower(PowerType<?> type, LivingEntity entity, Consumer<Entity> onAction, Consumer<Entity> offAction, boolean activeDefault) {
		super(type, entity, activeDefault, true);
		this.entityActionOn = onAction;
		this.entityActionOff = offAction;
	}

	@Override
	public void onUse() {
		super.onUse();
		if(this.isActive()) entityActionOn.accept(entity);
		if(!this.isActive()) entityActionOff.accept(entity);

	}
	public static PowerFactory createFactory() {
		return new PowerFactory<TogglePower>(Squarepowered.id("on_and_off"),
				new SerializableData()
						.add("on_action", ApoliDataTypes.ENTITY_ACTION)
						.add("off_action", ApoliDataTypes.ENTITY_ACTION)
						.add("key", ApoliDataTypes.BACKWARDS_COMPATIBLE_KEY, new Active.Key())
						.add("active_by_default", SerializableDataTypes.BOOLEAN, false),
				data ->
						(type, player) -> {
							OnAndOffPower power = new OnAndOffPower(type, player,
									(ActionFactory<Entity>.Instance)data.get("on_action"),
									(ActionFactory<Entity>.Instance)data.get("off_action"),
									data.getBoolean("active_by_default"));
							power.setKey(data.get("key"));
							return power;
						})
				.allowCondition();
	}
}
