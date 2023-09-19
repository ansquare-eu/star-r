package eu.ansquare.starr.power;

import eu.ansquare.starr.StarR;
import eu.ansquare.starr.cca.StarREntityComponents;
import eu.ansquare.starr.cca.SuperDudeComponent;
import eu.ansquare.starr.superdude.SuperDudes;
import net.fabricmc.fabric.api.entity.event.v1.EntityElytraEvents;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.server.network.ServerPlayerEntity;

import java.awt.*;
import java.util.Optional;

public class Powers {
	public static final TestTransformationPower TEST_TRANSFORMATION_POWER = new TestTransformationPower();
	public static final LaserPower TEST_LASER_POWER = new LaserPower(new Color(0x271BC9), 1);
	public static final TelekinesisPower TELEKINESIS_POWER = new TelekinesisPower();
	public static final EmptyPower EMPTY_POWER = new EmptyPower();

	public static void init(){
		EntityElytraEvents.CUSTOM.register((entity, tickElytra) -> {
			return true;
			/*Optional<SuperDudeComponent> component = StarREntityComponents.SUPER_DUDE_COMPONENT.maybeGet(entity);
			if(component.isPresent()){
				StarR.LOGGER.info("Fly my children");
				return component.get().isFlying();
			}
			StarR.LOGGER.warn("F off");*/
		});
	}
}
