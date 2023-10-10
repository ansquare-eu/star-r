package eu.ansquare.starr.screenhandler;

import eu.ansquare.starr.StarR;
import net.fabricmc.fabric.api.screenhandler.v1.ScreenHandlerRegistry;
import net.minecraft.screen.ScreenHandlerType;
import net.minecraft.util.Identifier;

public class ModScreenHandlers {
	public static final ScreenHandlerType<TeleportScreenHandler> TELEPORT_SCREEN;
	public static void init(){
	}
	static {
		TELEPORT_SCREEN = ScreenHandlerRegistry.registerSimple(new Identifier(StarR.MODID, "teleport_screen"), TeleportScreenHandler::new);
	}
}
