package eu.ansquare.starr.blocks;

import eu.ansquare.starr.StarR;
import net.fabricmc.fabric.api.itemgroup.v1.ItemGroupEvents;
import net.fabricmc.fabric.impl.content.registry.FuelRegistryImpl;
import net.minecraft.block.Block;
import net.minecraft.item.*;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.registry.RegistryKey;
import net.minecraft.util.Identifier;
import org.quiltmc.qsl.block.extensions.api.QuiltBlockSettings;
import org.quiltmc.qsl.item.setting.api.QuiltItemSettings;
import java.util.LinkedHashMap;
import java.util.Map;

public class ModBlocks {
	private static final Map<Block, Identifier> BLOCKS = new LinkedHashMap<>();
	private static final Map<Item, Identifier> BLOCKITEMS = new LinkedHashMap<>();
	private static <T extends Block> T createBlock(String name, T block, RegistryKey<ItemGroup> itemGroup){
		BLOCKS.put(block, new Identifier(StarR.MODID, name));
		BLOCKITEMS.put(new BlockItem(block, new QuiltItemSettings()), new Identifier(StarR.MODID, name));
		ItemGroupEvents.modifyEntriesEvent(itemGroup).register(content -> {
			content.addItem(block);
		});
		return block;
	}
	public static void init() {
		BLOCKS.keySet().forEach(item -> Registry.register(Registries.BLOCK, BLOCKS.get(item), item));
		BLOCKITEMS.keySet().forEach(item -> Registry.register(Registries.ITEM, BLOCKITEMS.get(item), item));
	}
    public static final Block FORCEFIELD = createBlock("forcefield", new Block(QuiltBlockSettings.create()), ItemGroups.BUILDING_BLOCKS);

}
