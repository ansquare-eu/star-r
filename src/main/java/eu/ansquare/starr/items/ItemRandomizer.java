package eu.ansquare.starr.items;

import eu.ansquare.starr.util.item.ItemArrayProvider;
import net.minecraft.item.Item;
import net.minecraft.registry.Registries;

import java.util.Arrays;
import java.util.Random;

public interface ItemRandomizer extends ItemArrayProvider {
	default Item generate(){
		Item item = Registries.ITEM.get(new Random().nextInt(Registries.ITEM.size()));
		if(Arrays.stream(get()).allMatch(item1 -> item1 == item)){
			return generate();
		}
		return item;
	}
}
