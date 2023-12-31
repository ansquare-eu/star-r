package eu.ansquare.squarepowered.power;

import eu.ansquare.squarepowered.action.entity.AirwalkEntityAction;
import io.github.apace100.apoli.power.factory.PowerFactory;
import io.github.apace100.apoli.power.factory.PowerFactorySupplier;
import io.github.apace100.apoli.registry.ApoliRegistries;
import net.minecraft.registry.Registry;

public class SquarePowers {
	public static void init(){
	}
	private static void register(PowerFactory<?> powerFactory) {
		Registry.register(ApoliRegistries.POWER_FACTORY, powerFactory.getSerializerId(), powerFactory);
	}

	private static void register(PowerFactorySupplier<?> factorySupplier) {
		register(factorySupplier.createFactory());
	}
}
