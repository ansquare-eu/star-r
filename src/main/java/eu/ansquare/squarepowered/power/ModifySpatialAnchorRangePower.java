package eu.ansquare.squarepowered.power;

import eu.ansquare.squarepowered.Squarepowered;
import io.github.apace100.apoli.component.PowerHolderComponent;
import io.github.apace100.apoli.power.Power;
import io.github.apace100.apoli.power.PowerType;
import io.github.apace100.apoli.power.factory.PowerFactory;
import io.github.apace100.calio.data.SerializableData;
import io.github.apace100.calio.data.SerializableDataTypes;
import net.minecraft.entity.LivingEntity;

import java.util.Comparator;
import java.util.List;

public class ModifySpatialAnchorRangePower extends Power {
	public final int amount;
	public final int priority;
	public ModifySpatialAnchorRangePower(PowerType<?> type, LivingEntity entity, int amount, int priority) {
		super(type, entity);
		this.amount = amount;
		this.priority = priority;
	}
	public int getPriority(){
		return priority;
	}
	public static int getFinalRange(LivingEntity entity){
		List<ModifySpatialAnchorRangePower> list = PowerHolderComponent.getPowers(entity, ModifySpatialAnchorRangePower.class);
		if(list.size() > 0){
			return list.stream().sorted(Comparator.comparing(ModifySpatialAnchorRangePower::getPriority)).toList().get(0).amount;
		} else {
			return 8;
		}
	}
	public static PowerFactory createFactory() {
		return new PowerFactory<ModifySpatialAnchorRangePower>(Squarepowered.id("modify_spatial_anchor_range"),
				new SerializableData()
						.add("amount", SerializableDataTypes.INT, 8)
						.add("priority", SerializableDataTypes.INT, 0),
				data ->
						(type, player) -> {
							ModifySpatialAnchorRangePower power = new ModifySpatialAnchorRangePower(type, player, data.getInt("amount"), data.getInt("priority"));
							return power;
						})
				.allowCondition();
	}
}
