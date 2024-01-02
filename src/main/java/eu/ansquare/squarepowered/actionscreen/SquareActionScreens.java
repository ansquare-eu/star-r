package eu.ansquare.squarepowered.actionscreen;

import eu.ansquare.squarepowered.SquareRegistries;
import eu.ansquare.squarepowered.Squarepowered;
import net.minecraft.feature_flags.FeatureFlags;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.screen.ScreenHandler;
import net.minecraft.screen.ScreenHandlerType;
import net.minecraft.util.Identifier;

public class SquareActionScreens {
	public static ScreenHandlerType TELEPORT_SCREEN = new ScreenHandlerType(LocationTeleportActionScreenHandler::new, FeatureFlags.DEFAULT_SET);
	public static void init(){
		register(Squarepowered.id("teleport_location"), TELEPORT_SCREEN);
	}
	private static ScreenHandlerType register(Identifier id, ScreenHandlerType type){
		Registry.register(SquareRegistries.ACTION_SCREEN, id, type);
		return Registry.register(Registries.SCREEN_HANDLER_TYPE, id, type);
	}
}
