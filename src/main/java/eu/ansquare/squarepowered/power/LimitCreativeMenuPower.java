package eu.ansquare.squarepowered.power;

import eu.ansquare.squarepowered.Squarepowered;
import io.github.apace100.apoli.power.Power;
import io.github.apace100.apoli.power.PowerType;
import io.github.apace100.apoli.power.factory.PowerFactory;
import io.github.apace100.calio.data.SerializableData;
import io.github.apace100.calio.data.SerializableDataTypes;
import net.minecraft.entity.LivingEntity;
import net.minecraft.item.ItemStack;

import java.util.Collection;
import java.util.List;

public class LimitCreativeMenuPower extends Power {
	public LimitCreativeMenuPower(PowerType<?> type, LivingEntity entity, List<ItemStack> stacks) {
		super(type, entity);
		this.stacks = stacks;
	}
	public final List<ItemStack> stacks;

	public Collection<ItemStack> filterStacks(Collection<ItemStack> toFilter){
		toFilter.removeAll(stacks);
		return toFilter;
	}
	public static PowerFactory createFactory() {
		return new PowerFactory<LimitCreativeMenuPower>(Squarepowered.id("limit_creative_menu"),
				new SerializableData()
						.add("removed_stacks", SerializableDataTypes.ITEM_STACKS),
				data ->
						(type, player) -> {
							LimitCreativeMenuPower power = new LimitCreativeMenuPower(type, player, data.get("removed_stacks"));
							return power;
						})
				.allowCondition();
	}
}
