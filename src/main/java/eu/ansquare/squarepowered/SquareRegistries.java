package eu.ansquare.squarepowered;

import eu.ansquare.squarepowered.util.editation.Editation;
import eu.ansquare.squarepowered.worldstructure.WorldStructureFactory;
import net.fabricmc.fabric.api.event.registry.FabricRegistryBuilder;
import net.minecraft.registry.Registry;
import net.minecraft.screen.ScreenHandlerType;

public class SquareRegistries {
	public static final Registry<WorldStructureFactory> WORLD_STRUCTURE;
	public static final Registry<ScreenHandlerType> ACTION_SCREEN;
	public static final Registry<Editation> EDITATIONS;


	static {
		ACTION_SCREEN = FabricRegistryBuilder.createSimple(ScreenHandlerType.class, Squarepowered.id("action_screen")).buildAndRegister();
		WORLD_STRUCTURE = FabricRegistryBuilder.createSimple(WorldStructureFactory.class, Squarepowered.id("world_structures")).buildAndRegister();
		EDITATIONS = FabricRegistryBuilder.createSimple(Editation.class, Squarepowered.id("editations")).buildAndRegister();
	}

}
