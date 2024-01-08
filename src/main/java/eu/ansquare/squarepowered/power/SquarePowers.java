package eu.ansquare.squarepowered.power;

import eu.ansquare.squarepowered.Squarepowered;
import io.github.apace100.apoli.power.Power;
import io.github.apace100.apoli.power.factory.PowerFactory;
import io.github.apace100.apoli.power.factory.PowerFactorySupplier;
import io.github.apace100.apoli.registry.ApoliRegistries;
import net.minecraft.registry.Registry;

public class SquarePowers {
	public static void init(){
		register(OnAndOffPower.createFactory());
		register(CreativeInvPower.createFactory());
		register(PreventFreezingPower.createFactory());
		register(LimitCreativeMenuPower.createFactory());
		register(ModifySpatialAnchorRangePower.createFactory());
		register(Power.createSimpleFactory((powerType, entity) -> new AutoMsgPower(powerType, entity), Squarepowered.id("auto_msg")).allowCondition());
	}
	private static void register(PowerFactory<?> powerFactory) {
		Registry.register(ApoliRegistries.POWER_FACTORY, powerFactory.getSerializerId(), powerFactory);
	}

	private static void register(PowerFactorySupplier<?> factorySupplier) {
		register(factorySupplier.createFactory());
	}
}
