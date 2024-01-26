package eu.ansquare.squarepowered.util.editation;

import eu.ansquare.squarepowered.SquareRegistries;
import eu.ansquare.squarepowered.Squarepowered;
import eu.ansquare.squarepowered.power.*;
import io.github.apace100.apoli.power.Power;
import io.github.apace100.apoli.power.factory.PowerFactory;
import io.github.apace100.apoli.registry.ApoliRegistries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;

public class SquareEditations {
	public static void init(){
		registerTwoPoint(((one, two, block) -> {}), Squarepowered.id("set"));
	}
	private static void registerTwoPoint(TwoPointWorldEditation editation, Identifier id) {
		Registry.register(SquareRegistries.EDITATIONS, id, editation);
	}
}
