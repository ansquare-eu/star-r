package eu.ansquare.starr.items;

import eu.ansquare.starr.StarR;
import eu.ansquare.starr.client.wearables.model.hare.HareOneModel;
import eu.ansquare.starr.client.wearables.model.hare.HareTwoModel;
import eu.ansquare.starr.items.material.CaptainrsMaterial;
import eu.ansquare.starr.items.material.MegaSword;
import eu.ansquare.starr.items.wearable.*;
import net.fabricmc.fabric.api.itemgroup.v1.ItemGroupEvents;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.model.Model;
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
	private static <T extends Item> T createItem(String name, T item, RegistryKey<ItemGroup>... itemGroups){
		ITEMS.put(item, new Identifier(StarR.MODID, name));
		for (RegistryKey<ItemGroup> itemGroup:itemGroups) {
			ItemGroupEvents.modifyEntriesEvent(itemGroup).register(content -> {
				content.addItem(item);
			});
		}
		return item;
	}
	private static <T extends Item> T createGrouplessItem(String name, T item){
		ITEMS.put(item, new Identifier(StarR.MODID, name));
		return item;
	}
	public static void init() {
		ITEMS.keySet().forEach(item -> Registry.register(Registries.ITEM, ITEMS.get(item), item));
	}
	public static final CapeModelProvider capeModelProvider = new CapeModelProvider();
	public static final ArmorMaterial CAPTAIN_RS = new CaptainrsMaterial();
    public static final Item CAPTAIN_RS_CHESTPLATE = createItem("captain_rs_chestplate", new ArmorItem(CAPTAIN_RS, ArmorItem.ArmorSlot.CHESTPLATE, new QuiltItemSettings().maxCount(1)), ItemGroups.COMBAT, ModItemGroups.STARR_ARMOR);
    public static final Item CAPTAIN_RS_LEGGINGS = createItem("captain_rs_leggings", new ArmorItem(CAPTAIN_RS, ArmorItem.ArmorSlot.LEGGINGS, new QuiltItemSettings().maxCount(1)), ItemGroups.COMBAT, ModItemGroups.STARR_ARMOR);
    public static final Item CAPTAIN_RS_BOOTS = createItem("captain_rs_boots", new ArmorItem(CAPTAIN_RS, ArmorItem.ArmorSlot.BOOTS, new QuiltItemSettings().maxCount(1)), ItemGroups.COMBAT, ModItemGroups.STARR_ARMOR);
    public static final CapeWearable CAPE = createItem("cape", new CapeWearable(new QuiltItemSettings().maxCount(1), "textures/wearable/cape/caprcape.png", "textures/wearable/cape/caprscape.png","cape", capeModelProvider, false), ItemGroups.COMBAT, ModItemGroups.STARR_ITEMS);
    public static final TwoStateWearable HARE = createItem("hare", new TwoStateWearable(new QuiltItemSettings().maxCount(1), "textures/wearable/hare.png", "textures/wearable/hare.png", "hare", new TwoStateModelProvider() {
		@Override
		public Model getModel() {
			return getFirstModel();
		}

		@Override
		public Model getFirstModel() {
			return new HareOneModel(MinecraftClient.getInstance().getEntityModelLoader().getModelPart(HareOneModel.LAYER_LOCATION));
		}

		@Override
		public Model getSecondModel() {
			return new HareTwoModel(MinecraftClient.getInstance().getEntityModelLoader().getModelPart(HareTwoModel.LAYER_LOCATION));
		}
	}, true), ItemGroups.COMBAT, ModItemGroups.STARR_ITEMS);
    public static final Item PALICA = createItem("palica", new PalicaItem(new QuiltItemSettings()), ItemGroups.COMBAT, ModItemGroups.STARR_WEAPONS);
	public static final MegaSword MEGASWORD_MATERIALS = new MegaSword();
	public static final Item FORCESWORD = createItem("forcesword", new SwordItem(MEGASWORD_MATERIALS, 20, 1, new QuiltItemSettings().maxCount(1)), ModItemGroups.STARR_WEAPONS);
	public static final Item BOYSHIELD = createItem("boyshield", new BoyshieldItem(new QuiltItemSettings().maxCount(1)), ModItemGroups.STARR_WEAPONS);
	public static final Item MEGASWORD = createItem("megasword", new SwordItem(MEGASWORD_MATERIALS, 20, 1, new QuiltItemSettings().maxCount(1)), ModItemGroups.STARR_WEAPONS);

}
