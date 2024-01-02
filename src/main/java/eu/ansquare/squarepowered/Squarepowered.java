package eu.ansquare.squarepowered;

import eu.ansquare.squarepowered.action.SquareActions;
import eu.ansquare.squarepowered.action.SquareActions;
import eu.ansquare.squarepowered.actionscreen.SquareActionScreens;
import eu.ansquare.squarepowered.client.SquarepoweredClient;
import eu.ansquare.squarepowered.power.SquarePowers;
import eu.ansquare.squarepowered.worldstructure.SquareWorldStructures;
import eu.ansquare.starr.StarR;
import net.minecraft.util.Identifier;
import org.quiltmc.loader.api.ModContainer;
import org.quiltmc.qsl.base.api.entrypoint.ModInitializer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Squarepowered implements ModInitializer {
	public static final Logger LOGGER = LoggerFactory.getLogger("Squarepowered");
	public static final String MODID = StarR.MODID;
	public static Identifier id(String path){
		return StarR.id(path);
	}
	public static void log(String string, int severity, Object... objects){
		switch (severity){
			case 1:
				LOGGER.warn(string, objects);
				break;
			case 2:
				LOGGER.error(string, objects);
				break;
			default:
				LOGGER.info(string, objects);
		}
	}
	@Override
	public void onInitialize(ModContainer mod) {
		LOGGER.info("Hello Quilt world from Squarepowered lib!");
		SquarePowers.init();
		SquareActions.init();
		SquareWorldStructures.init();
		SquareActionScreens.init();
		LOGGER.info("Squarepowered lib successfully initialized!");
	}
}
