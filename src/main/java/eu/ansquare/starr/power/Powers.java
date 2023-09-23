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
	public static final LaserPower TEST_LASER_POWER = new LaserPower(new Color(0x271BC9), 2);
	public static final TelekinesisPower TELEKINESIS_POWER = new TelekinesisPower();
	public static final ForcefieldPower FORCEFIELD_POWER = new ForcefieldPower();
	public static final TeleportPower TELEPORT_POWER = new TeleportPower();

	public static final EmptyPower EMPTY_POWER = new EmptyPower();

	public static void init(){
		EntityElytraEvents.CUSTOM.register((entity, tickElytra) -> {
			if(StarREntityComponents.SUPER_DUDE_COMPONENT.maybeGet(entity).isPresent()){
				return StarREntityComponents.SUPER_DUDE_COMPONENT.get(entity).canFly();
			}
			return false;
		});
	}
}
