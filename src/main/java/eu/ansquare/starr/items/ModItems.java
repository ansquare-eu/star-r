package eu.ansquare.starr.items;

import eu.ansquare.starr.StarR;
import net.fabricmc.fabric.api.itemgroup.v1.ItemGroupEvents;
import net.minecraft.item.Item;
import net.minecraft.item.ItemConvertible;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemGroups;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.registry.RegistryKey;
import net.minecraft.util.Identifier;
import org.quiltmc.qsl.item.setting.api.QuiltItemSettings;

import java.util.LinkedHashMap;
import java.util.Map;

public class ModItems {
	private static final Map<Item, Identifier> ITEMS = new LinkedHashMap<>();
	private static <T extends Item> T createItem(String name, T item, RegistryKey<ItemGroup> itemGroup){
		ITEMS.put(item, new Identifier(StarR.MODID, name));
		ItemGroupEvents.modifyEntriesEvent(itemGroup).register(content -> {
			content.addItem(item);
		});
		return item;
	}
	public static void init() {
		ITEMS.keySet().forEach(item -> Registry.register(Registries.ITEM, ITEMS.get(item), item));
	}

    public static final Item HAGAR_ITEM = createItem("hagar_item", new Item(new QuiltItemSettings()), ItemGroups.COMBAT);
    public static final Item HAGAR_HAHA = createItem("hagar_haha", new Item(new QuiltItemSettings()), ItemGroups.COMBAT);

}
