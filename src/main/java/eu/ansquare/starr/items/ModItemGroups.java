package eu.ansquare.starr.items;

import eu.ansquare.starr.StarR;
import eu.ansquare.starr.blocks.ModBlocks;
import net.fabricmc.fabric.api.itemgroup.v1.FabricItemGroup;
import net.minecraft.item.Item;
import net.minecraft.item.ItemConvertible;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.registry.RegistryKey;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.text.Text;
import net.minecraft.util.Identifier;

import java.util.HashMap;
import java.util.Map;

public class ModItemGroups {
	private static Map<ItemGroup, RegistryKey<ItemGroup>> map = new HashMap<>();
	public static final RegistryKey<ItemGroup> STARR_BLOCKS = createRegistryKey("starr_blocks");
	public static final RegistryKey<ItemGroup> STARR_WEAPONS = createRegistryKey("starr_weapons");
	public static final RegistryKey<ItemGroup> STARR_ITEMS = createRegistryKey("starr_items");
	public static final RegistryKey<ItemGroup> STARR_ARMOR = createRegistryKey("starr_armor");

	private static RegistryKey<ItemGroup> createRegistryKey(String name) {
		return RegistryKey.of(RegistryKeys.ITEM_GROUP, new Identifier(StarR.MODID, name));
	}
	private static ItemGroup createGroup(RegistryKey<ItemGroup> key, String name, ItemConvertible item){
		ItemGroup group = FabricItemGroup.builder().name(Text.translatable("itemgroup.starr." + name)).icon(() -> new ItemStack(item)).build();
		map.put(group, key);
		return group;
	}
	public static void init(){
		createGroup(STARR_BLOCKS, "blocks", ModBlocks.KUFOR);
		createGroup(STARR_WEAPONS, "weapons", ModItems.PALICA);
		createGroup(STARR_ITEMS, "items", ModItems.CAPE);
		createGroup(STARR_ARMOR, "armor", ModItems.CAPTAIN_RS_CHESTPLATE);
		map.keySet().forEach(itemGroup -> Registry.register(Registries.ITEM_GROUP, map.get(itemGroup), itemGroup));
	}
}
