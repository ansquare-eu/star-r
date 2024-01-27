package eu.ansquare.squarepowered.item;

import eu.ansquare.squarepowered.Squarepowered;
import eu.ansquare.squarepowered.power.SquarePowers;
import eu.ansquare.starr.StarR;
import net.fabricmc.fabric.api.itemgroup.v1.ItemGroupEvents;
import net.minecraft.item.*;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.registry.RegistryKey;
import net.minecraft.util.Identifier;
import org.quiltmc.qsl.item.setting.api.QuiltItemSettings;

import java.util.LinkedHashMap;
import java.util.Map;

public class SquareItems {
	private static final Map<Item, Identifier> ITEMS = new LinkedHashMap<>();
	private static <T extends Item> T createItem(String name, T item, RegistryKey<ItemGroup>... itemGroups){
		ITEMS.put(item, Squarepowered.id(name));
		for (RegistryKey<ItemGroup> itemGroup:itemGroups) {
			ItemGroupEvents.modifyEntriesEvent(itemGroup).register(content -> {
				content.addItem(item);
			});
		}
		return item;
	}
	private static <T extends Item> T createGrouplessItem(String name, T item){
		ITEMS.put(item, Squarepowered.id(name));
		return item;
	}
	public static void init() {
		ITEMS.keySet().forEach(item -> Registry.register(Registries.ITEM, ITEMS.get(item), item));
	}
	public static final Item SELECTOR = createItem("selector", new SelectorItem(new QuiltItemSettings().maxCount(1)), ItemGroups.TOOLS_AND_UTILITIES);
}
