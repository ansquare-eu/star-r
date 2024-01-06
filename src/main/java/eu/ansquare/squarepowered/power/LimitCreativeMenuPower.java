package eu.ansquare.squarepowered.power;

import eu.ansquare.squarepowered.Squarepowered;
import eu.ansquare.squarepowered.util.SquareDataTypes;
import io.github.apace100.apoli.component.PowerHolderComponent;
import io.github.apace100.apoli.power.Power;
import io.github.apace100.apoli.power.PowerType;
import io.github.apace100.apoli.power.factory.PowerFactory;
import io.github.apace100.calio.data.SerializableData;
import io.github.apace100.calio.data.SerializableDataTypes;
import net.minecraft.entity.LivingEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;

import java.util.Collection;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;

public class LimitCreativeMenuPower extends Power {
	public LimitCreativeMenuPower(PowerType<?> type, LivingEntity entity, List<Item> items) {
		super(type, entity);
		this.items = items;
	}
	public final List<Item> items;

	public Collection<ItemStack> filterStacks(Collection<ItemStack> toFilter){
		List<Item> collected = new LinkedList<>();
		PowerHolderComponent.getPowers(entity, LimitCreativeMenuPower.class).forEach(limitCreativeMenuPower -> collected.addAll(limitCreativeMenuPower.items));
		toFilter.removeIf(stack -> collected.contains(stack.getItem()));


		return toFilter;
	}
	public static PowerFactory createFactory() {
		return new PowerFactory<LimitCreativeMenuPower>(Squarepowered.id("limit_creative_menu"),
				new SerializableData()
						.add("removed", SquareDataTypes.ITEMS),
				data ->
						(type, player) -> {
							LimitCreativeMenuPower power = new LimitCreativeMenuPower(type, player, data.get("removed"));
							return power;
						})
				.allowCondition();
	}
}
