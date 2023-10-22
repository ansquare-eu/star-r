package eu.ansquare.starr.power;

import eu.ansquare.starr.cca.StarREntityComponents;
import net.fabricmc.fabric.api.entity.event.v1.EntityElytraEvents;

import java.awt.*;

public class Powers {
	public static final TestTransformationPower TEST_TRANSFORMATION_POWER = new TestTransformationPower();
	public static final LaserPower TEST_LASER_POWER = new LaserPower(new Color(0x0B4AC9), 2);
	public static final TelekinesisPower TELEKINESIS_POWER = new TelekinesisPower();
	public static final ForcefieldPower FORCEFIELD_POWER = new ForcefieldPower();
	public static final TeleportPower TELEPORT_POWER = new TeleportPower();
	public static final ItemGuiPower ITEM_GUI_POWER = new ItemGuiPower();
	public static final InvisPower INVIS_POWER = new InvisPower();
	public static final UnderwaterPower UNDERWATER_POWER = new UnderwaterPower();
	public static final TornadoPower TORNADO_POWER = new TornadoPower();
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
