package eu.ansquare.starr.items;

import eu.ansquare.starr.StarR;
import eu.ansquare.starr.items.testing.GetSuperTypeTesterItem;
import eu.ansquare.starr.items.testing.ResetSuperTypeTesterItem;
import eu.ansquare.starr.items.testing.SetSuperTypeTesterItem;
import net.fabricmc.fabric.api.itemgroup.v1.ItemGroupEvents;
import net.minecraft.item.*;
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

    public static final ArmorMaterial TOTTEST = new TottestMaterial();
    public static final Item TOTTEST_HELMET = createItem("tottest_helmet", new ArmorItem(TOTTEST, ArmorItem.ArmorSlot.HELMET, new QuiltItemSettings().maxCount(1)), ItemGroups.COMBAT);
    public static final Item TOTTEST_CHESTPLATE = createItem("tottest_chestplate", new ArmorItem(TOTTEST, ArmorItem.ArmorSlot.CHESTPLATE, new QuiltItemSettings().maxCount(1)), ItemGroups.COMBAT);
    public static final Item TOTTEST_LEGGINGS = createItem("tottest_leggings", new ArmorItem(TOTTEST, ArmorItem.ArmorSlot.LEGGINGS, new QuiltItemSettings().maxCount(1)), ItemGroups.COMBAT);
    public static final Item TOTTEST_BOOTS = createItem("tottest_boots", new ArmorItem(TOTTEST, ArmorItem.ArmorSlot.BOOTS, new QuiltItemSettings().maxCount(1)), ItemGroups.COMBAT);
    public static final ArmorMaterial LO = new LoMaterial();
    public static final Item LO_CHESTPLATE = createItem("lo_chestplate", new ArmorItem(LO, ArmorItem.ArmorSlot.CHESTPLATE, new QuiltItemSettings().maxCount(1)), ItemGroups.COMBAT);
    public static final Item LO_LEGGINGS = createItem("lo_leggings", new ArmorItem(LO, ArmorItem.ArmorSlot.LEGGINGS, new QuiltItemSettings().maxCount(1)), ItemGroups.COMBAT);
    public static final Item LO_BOOTS = createItem("lo_boots", new ArmorItem(LO, ArmorItem.ArmorSlot.BOOTS, new QuiltItemSettings().maxCount(1)), ItemGroups.COMBAT);
    public static final Item SUPER_TYPE_SET_TEST = createItem("super_type_set_test", new SetSuperTypeTesterItem(new QuiltItemSettings()), ItemGroups.COMBAT);
    public static final Item SUPER_TYPE_GET_TEST = createItem("super_type_get_test", new GetSuperTypeTesterItem(new QuiltItemSettings()), ItemGroups.COMBAT);
    public static final Item RESET_TESTER = createItem("reset_tester", new ResetSuperTypeTesterItem(new QuiltItemSettings()), ItemGroups.COMBAT);

}
