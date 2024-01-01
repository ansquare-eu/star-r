package eu.ansquare;

import eu.ansquare.squarepowered.Squarepowered;
import eu.ansquare.squarepowered.worldstructure.WorldStructureFactory;
import io.github.apace100.apoli.power.factory.PowerFactory;
import net.fabricmc.fabric.api.event.registry.FabricRegistryBuilder;
import net.minecraft.registry.Registry;

public class SquareRegistries {
	public static final Registry<WorldStructureFactory> WORLD_STRUCTURE;
	static {
		WORLD_STRUCTURE = FabricRegistryBuilder.createSimple(WorldStructureFactory.class, Squarepowered.id("world_structures")).buildAndRegister();
	}

}
