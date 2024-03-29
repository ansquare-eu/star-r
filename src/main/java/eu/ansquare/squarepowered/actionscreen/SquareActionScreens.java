package eu.ansquare.squarepowered.actionscreen;

import eu.ansquare.squarepowered.SquareRegistries;
import eu.ansquare.squarepowered.Squarepowered;
import eu.ansquare.squarepowered.actionscreen.client.LocalizeScreen;
import eu.ansquare.squarepowered.actionscreen.client.LocationTeleportScreen;
import eu.ansquare.squarepowered.actionscreen.client.SaveTeleportScreen;
import eu.ansquare.squarepowered.actionscreen.client.WorldEditScreen;
import eu.ansquare.squarepowered.actionscreen.handler.LocalizeActionScreenHandler;
import eu.ansquare.squarepowered.actionscreen.handler.LocationTeleportActionScreenHandler;
import eu.ansquare.squarepowered.actionscreen.handler.SaveTeleportActionScreenHandler;
import eu.ansquare.squarepowered.actionscreen.handler.WorldEditScreenHandler;
import net.minecraft.client.gui.screen.ingame.HandledScreens;
import net.minecraft.feature_flags.FeatureFlags;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.screen.ScreenHandlerType;
import net.minecraft.util.Identifier;

public class SquareActionScreens {
	public static ScreenHandlerType TELEPORT_SCREEN = new ScreenHandlerType(LocationTeleportActionScreenHandler::new, FeatureFlags.DEFAULT_SET);
	public static ScreenHandlerType SAVE_TELEPORT_SCREEN = new ScreenHandlerType(SaveTeleportActionScreenHandler::new, FeatureFlags.DEFAULT_SET);
	public static ScreenHandlerType LOCALIZE_SCREEN = new ScreenHandlerType(LocalizeActionScreenHandler::new, FeatureFlags.DEFAULT_SET);
	public static ScreenHandlerType WORLD_EDIT_SCREEN = new ScreenHandlerType(WorldEditScreenHandler::new, FeatureFlags.DEFAULT_SET);


	public static void init(){
		register(Squarepowered.id("teleport_location"), TELEPORT_SCREEN);
		register(Squarepowered.id("teleport_save"), SAVE_TELEPORT_SCREEN);
		register(Squarepowered.id("localize"), LOCALIZE_SCREEN);
		register(Squarepowered.id("world_edit"), WORLD_EDIT_SCREEN);
	}
	public static void clientInit(){
		HandledScreens.register(SquareActionScreens.TELEPORT_SCREEN, LocationTeleportScreen::new);
		HandledScreens.register(SquareActionScreens.SAVE_TELEPORT_SCREEN, SaveTeleportScreen::new);
		HandledScreens.register(SquareActionScreens.LOCALIZE_SCREEN, LocalizeScreen::new);
		HandledScreens.register(SquareActionScreens.WORLD_EDIT_SCREEN, WorldEditScreen::new);
	}
	private static ScreenHandlerType register(Identifier id, ScreenHandlerType type){
		Registry.register(SquareRegistries.ACTION_SCREEN, id, type);
		return Registry.register(Registries.SCREEN_HANDLER_TYPE, id, type);
	}
}
