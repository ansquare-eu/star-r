package eu.ansquare.starr.power;

import eu.ansquare.starr.cca.StarREntityComponents;
import eu.ansquare.starr.power.creation.ItemGuiPower;
import eu.ansquare.starr.power.protection.ForcefieldPower;
import eu.ansquare.starr.power.protection.InvisPower;
import eu.ansquare.starr.power.protection.UnderwaterPower;
import eu.ansquare.starr.power.offense.LaserPower;
import eu.ansquare.starr.power.offense.TelekinesisPower;
import eu.ansquare.starr.power.offense.TornadoPower;
import eu.ansquare.starr.power.transformation.TestTransformationPower;
import eu.ansquare.starr.power.transport.TeleportOthersPower;
import eu.ansquare.starr.power.transport.TeleportPower;
import eu.ansquare.starr.power.utility.AutoMsgPower;
import eu.ansquare.starr.power.utility.LocalizePower;
import net.fabricmc.fabric.api.entity.event.v1.EntityElytraEvents;
import net.minecraft.util.Pair;

import java.awt.*;

public class Powers {
	public static final TestTransformationPower TEST_TRANSFORMATION_POWER = new TestTransformationPower();
	public static final LaserPower TEST_LASER_POWER = new LaserPower(new Color(0x0B4AC9), 2, ((entity, handStack, offhandStack) -> new Pair<>(0f, 0f)));
	public static final TelekinesisPower TELEKINESIS_POWER = new TelekinesisPower();
	public static final ForcefieldPower FORCEFIELD_POWER = new ForcefieldPower();
	public static final TeleportPower TELEPORT_POWER = new TeleportPower();
	public static final ItemGuiPower ITEM_GUI_POWER = new ItemGuiPower();
	public static final InvisPower INVIS_POWER = new InvisPower();
	public static final UnderwaterPower UNDERWATER_POWER = new UnderwaterPower();
	public static final TornadoPower TORNADO_POWER = new TornadoPower();
	public static final AutoMsgPower AUTO_MSG_POWER = new AutoMsgPower();
	public static final TeleportOthersPower TELEPORT_OTHERS_POWER = new TeleportOthersPower();
	public static final LocalizePower LOCALIZE_POWER = new LocalizePower();
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
