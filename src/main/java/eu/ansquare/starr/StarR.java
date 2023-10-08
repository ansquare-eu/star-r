package eu.ansquare.starr;

import eu.ansquare.starr.blocks.ModBlocks;
import eu.ansquare.starr.entity.ModEntities;
import eu.ansquare.starr.items.ModItems;
import eu.ansquare.starr.network.ModPackets;
import eu.ansquare.starr.power.Powers;
import eu.ansquare.starr.screenhandler.ModScreenHandlers;
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

	@Override
	public void onInitialize(ModContainer mod) {
		LOGGER.info("Hello Quilt world from {}!", mod.metadata().name());
		ModBlocks.init();
		ModItems.init();
		ModEntities.init();
		ModPackets.initC2S();
		ModScreenHandlers.init();
		Powers.init();
		LOGGER.info("{} succesfully intialized!", mod.metadata().name());
	}

}
