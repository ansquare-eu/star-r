package eu.ansquare.starr.superdude;

import eu.ansquare.starr.ModRegistries;
import eu.ansquare.starr.StarR;
import eu.ansquare.starr.cca.StarREntityComponents;
import eu.ansquare.starr.superdude.readyroad.*;
import eu.ansquare.starr.util.power.FlightType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;

import java.awt.*;
import java.util.*;

public class SuperDudes {
	private static Map<SuperDude, Identifier> SUPERDUDES = new HashMap<>();

	public static final SuperDude TEST_SUPER_DUDE = create("testtype", new TestSuperDude(FlightType.NORMAL, new Color(0xFF0000)));
	public static final SuperDude TELEMAN = create("teleman", new TelemanSuperDude(FlightType.CREATIVE, new Color(0x4DF18F)));
	public static final SuperDude SHIELDBOY = create("shieldboy", new ShieldboySuperDude(FlightType.NONE, new Color(0x5BDA12)));
	public static final SuperDude CAPTAIN_R = create("captain_r", new CaptainRSuperDude(FlightType.NORMAL, new Color(0x0B4AC9)));
	public static final SuperDude LOCALIZATOR = create("localizator", new LocalizatorSuperDude(FlightType.NONE, new Color(0xA85858)));
	public static final SuperDude BRAIN = create("brain", new BrainSuperDude(FlightType.CREATIVE, new Color(0x5A9A73)));
	public static final SuperDude SKIER = create("skier", new SkierSuperDude(FlightType.CREATIVE, new Color(0x9BFFD7)));
	public static final SuperDude EMPTY = create("empty", new EmptySuperDude());
	public static <T extends SuperDude> T create(String modid, String name, T superDude){
		Identifier id = new Identifier(modid, name);
		superDude.id = id;
		SUPERDUDES.put(superDude, id);
		return superDude;
	}
	public static <T extends SuperDude> T create(String name, T superDude){
		return create(StarR.MODID, name, superDude);
	}
	public static void init(){
		SUPERDUDES.keySet().forEach(superDude -> {
			superDude.init(SUPERDUDES.get(superDude));
			Registry.register(ModRegistries.SUPER_DUDES, SUPERDUDES.get(superDude), superDude);
		});
	}
	public static SuperDude getSuperDude(Identifier type){
		return ModRegistries.SUPER_DUDES.get(type);
	}
	public static void applyToPlayer(PlayerEntity player, SuperDude superDude){
		removeFromPlayer(player);
		StarREntityComponents.SUPER_DUDE_COMPONENT.maybeGet(player).ifPresent(superDudeComponent -> superDudeComponent.setType(superDude));
		superDude.onApply(player);
	}
	public static void removeFromPlayer(PlayerEntity player){
		StarREntityComponents.SUPER_DUDE_COMPONENT.maybeGet(player).ifPresent(superDudeComponent -> {
			superDudeComponent.getType().onRemove(player);
			superDudeComponent.setType(SuperDudes.EMPTY);
		});
	}
	public static boolean isEntityOf(LivingEntity entity, SuperDude superDude){
		if(StarREntityComponents.SUPER_DUDE_COMPONENT.isProvidedBy(entity)){
			if (StarREntityComponents.SUPER_DUDE_COMPONENT.get(entity).getType().equals(superDude)){
				return true;
			}
		}
		return false;
	}
}
