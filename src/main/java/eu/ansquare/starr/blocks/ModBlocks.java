package eu.ansquare.starr.blocks;

import eu.ansquare.starr.StarR;
import eu.ansquare.starr.items.ModItemGroups;
import net.fabricmc.fabric.api.itemgroup.v1.ItemGroupEvents;
import net.minecraft.block.Block;
import net.minecraft.block.Blocks;
import net.minecraft.block.piston.PistonBehavior;
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
	private static <T extends Block> T createBlockAndItem(String name, T block, RegistryKey<ItemGroup>... itemGroup){
		BLOCKS.put(block, new Identifier(StarR.MODID, name));
		BLOCKITEMS.put(new BlockItem(block, new QuiltItemSettings()), new Identifier(StarR.MODID, name));
		for (RegistryKey<ItemGroup> group:itemGroup) {
			ItemGroupEvents.modifyEntriesEvent(group).register(content -> content.addItem(block));
		}
		return block;
	}
	private static <T extends Block> T createBlock(String name, T block){
		BLOCKS.put(block, new Identifier(StarR.MODID, name));
		return block;
	}
	public static void init() {
		BLOCKS.keySet().forEach(item -> Registry.register(Registries.BLOCK, BLOCKS.get(item), item));
		BLOCKITEMS.keySet().forEach(item -> Registry.register(Registries.ITEM, BLOCKITEMS.get(item), item));
	}
    public static final TintedBlock FORCEFIELD = createBlock("forcefield", new TintedBlock(QuiltBlockSettings.create().nonOpaque().dropsNothing().strength(-1.0F, 3600000.0F).pistonBehavior(PistonBehavior.IGNORE), 0x00FF00));
	public static final Block TV = createBlockAndItem("tv", new TvBlock(QuiltBlockSettings.create().pistonBehavior(PistonBehavior.IGNORE).nonOpaque()), ItemGroups.FUNCTIONAL_BLOCKS, ModItemGroups.STARR_BLOCKS);
	public static final Block KUFOR = createBlockAndItem("kufor", new RandomKuforBlock(QuiltBlockSettings.create().pistonBehavior(PistonBehavior.IGNORE).nonOpaque(), new ItemConvertible[]{Blocks.COMMAND_BLOCK, Blocks.CHAIN_COMMAND_BLOCK, Blocks.REPEATING_COMMAND_BLOCK, Blocks.STRUCTURE_BLOCK, Blocks.JIGSAW, Blocks.STRUCTURE_VOID, Blocks.DRAGON_EGG}), ItemGroups.FUNCTIONAL_BLOCKS, ModItemGroups.STARR_BLOCKS);
	public static final Block WORLD_ANCHOR = createBlockAndItem("world_anchor", new WorldAnchorBlock(QuiltBlockSettings.copyOf(Blocks.AMETHYST_BLOCK), false), ItemGroups.FUNCTIONAL_BLOCKS, ModItemGroups.STARR_BLOCKS);
	public static final Block SPATIAL_ANCHOR = createBlockAndItem("spatial_anchor", new WorldAnchorBlock(QuiltBlockSettings.copyOf(Blocks.AMETHYST_BLOCK), true), ItemGroups.FUNCTIONAL_BLOCKS, ModItemGroups.STARR_BLOCKS);

}
