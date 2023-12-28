package eu.ansquare.starr;

import eu.ansquare.starr.blocks.ModBlocks;
import eu.ansquare.starr.entity.ModEntities;
import eu.ansquare.starr.items.ModItemGroups;
import eu.ansquare.starr.items.ModItems;
import net.minecraft.util.Identifier;
import org.quiltmc.loader.api.ModContainer;
import org.quiltmc.qsl.base.api.entrypoint.ModInitializer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class StarR implements ModInitializer {
	// This logger is used to write text to the console and the log file.
	// It is considered best practice to use your mod name as the logger's name.
	// That way, it's clear which mod wrote info, warnings, and errors.
	public static final Logger LOGGER = LoggerFactory.getLogger("Star R");
	public static final String MODID = "starr";
	public static Identifier id(String path){
		return new Identifier(MODID, path);
	}

	@Override
	public void onInitialize(ModContainer mod) {
		LOGGER.info("Hello Quilt world from {}!", mod.metadata().name());
		ModCommands.init();
		ModItemGroups.init();
		ModBlocks.init();
		ModItems.init();
		ModEntities.init();
		LOGGER.info("{} succesfully intialized!", mod.metadata().name());
	}

}
