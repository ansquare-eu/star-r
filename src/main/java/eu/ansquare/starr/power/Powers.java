package eu.ansquare.starr.power;

import eu.ansquare.starr.superdude.SuperDudes;
import net.fabricmc.fabric.api.entity.event.v1.EntityElytraEvents;
import net.minecraft.entity.player.PlayerEntity;

public class Powers {
	public static final TestTransformationPower TEST_TRANSFORMATION_POWER = new TestTransformationPower();
	public static final LaserPower TEST_LASER_POWER = new LaserPower(0xFF00E6, 1);
	public static final FlightPower FLIGHT_POWER = new FlightPower();
	public static final TelekinesisPower TELEKINESIS_POWER = new TelekinesisPower();
	public static void init(){
		EntityElytraEvents.CUSTOM.register((entity, tickElytra) -> {
			if(entity instanceof PlayerEntity) {
				if (SuperDudes.TEST_SUPER_DUDE.isFlying(((PlayerEntity) entity))){
					return true;
				}
			}
			return false;
		});
	}
}
