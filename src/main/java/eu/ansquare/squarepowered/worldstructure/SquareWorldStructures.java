package eu.ansquare.squarepowered.worldstructure;

import eu.ansquare.SquareRegistries;
import eu.ansquare.squarepowered.Squarepowered;
import net.minecraft.registry.Registry;

public class SquareWorldStructures {
	public static void init(){
		register(new WorldStructureFactory(Squarepowered.id("hollow_cube"),WorldStructure::hollowCube));
	}
	private static void register(WorldStructureFactory factory){
		Registry.register(SquareRegistries.WORLD_STRUCTURE, factory.id, factory);
	}
}
