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
    public static final ArmorMaterial SKIN = new SkinMaterial(); 
    public static final Item SKIN_HELMET = createItem("skin_helmet", new ArmorItem(SKIN, ArmorItem.ArmorSlot.HELMET, new QuiltItemSettings().maxCount(1)), ItemGroups.COMBAT); 
    public static final Item SKIN_CHESTPLATE = createItem("skin_chestplate", new ArmorItem(SKIN, ArmorItem.ArmorSlot.CHESTPLATE, new QuiltItemSettings().maxCount(1)), ItemGroups.COMBAT); 
    public static final Item SKIN_LEGGINGS = createItem("skin_leggings", new ArmorItem(SKIN, ArmorItem.ArmorSlot.LEGGINGS, new QuiltItemSettings().maxCount(1)), ItemGroups.COMBAT); 
    public static final Item SKIN_BOOTS = createItem("skin_boots", new ArmorItem(SKIN, ArmorItem.ArmorSlot.BOOTS, new QuiltItemSettings().maxCount(1)), ItemGroups.COMBAT); 
    public static final ArmorMaterial CAPTAIN_RS = new CaptainrsMaterial(); 
    public static final Item CAPTAIN_RS_HELMET = createItem("captain_rs_helmet", new ArmorItem(CAPTAIN_RS, ArmorItem.ArmorSlot.HELMET, new QuiltItemSettings().maxCount(1)), ItemGroups.COMBAT); 
    public static final Item CAPTAIN_RS_CHESTPLATE = createItem("captain_rs_chestplate", new ArmorItem(CAPTAIN_RS, ArmorItem.ArmorSlot.CHESTPLATE, new QuiltItemSettings().maxCount(1)), ItemGroups.COMBAT); 
    public static final Item CAPTAIN_RS_LEGGINGS = createItem("captain_rs_leggings", new ArmorItem(CAPTAIN_RS, ArmorItem.ArmorSlot.LEGGINGS, new QuiltItemSettings().maxCount(1)), ItemGroups.COMBAT); 
    public static final Item CAPTAIN_RS_BOOTS = createItem("captain_rs_boots", new ArmorItem(CAPTAIN_RS, ArmorItem.ArmorSlot.BOOTS, new QuiltItemSettings().maxCount(1)), ItemGroups.COMBAT); 

}
