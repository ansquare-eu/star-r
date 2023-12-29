package eu.ansquare.squarepowered;

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
	@Override
	public void onInitialize(ModContainer mod) {
		LOGGER.info("Hello Quilt world from Squarepowered lib!");
		LOGGER.info("Squarepowered lib successfully initialized!");
	}
}
