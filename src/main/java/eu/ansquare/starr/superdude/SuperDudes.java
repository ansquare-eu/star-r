package eu.ansquare.starr.superdude;

import eu.ansquare.starr.cca.StarREntityComponents;
import net.minecraft.entity.player.PlayerEntity;

import java.awt.*;
import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

public class SuperDudes {
	public static final SuperDude TEST_SUPER_DUDE = new TestSuperDude(false, new Color(0x0B4AC9));
	public static final SuperDude EMPTY = new EmptySuperDude();

	public static SuperDude getSuperDude(String type){
		switch (type){
			case "testType":
				return TEST_SUPER_DUDE;
			default:
				return EMPTY;
		}
	}
	public static void applyToPlayer(PlayerEntity player, SuperDude superDude){
		StarREntityComponents.SUPER_DUDE_COMPONENT.maybeGet(player).ifPresent(superDudeComponent -> superDudeComponent.setType(superDude));
		superDude.onApply(player);
	}
	public static void removeFromPlayer(PlayerEntity player){
		StarREntityComponents.SUPER_DUDE_COMPONENT.maybeGet(player).ifPresent(superDudeComponent -> {
			superDudeComponent.getType().onRemove(player);
			superDudeComponent.setType(SuperDudes.EMPTY);
		});
	}
	public static Set<UUID> flying = new HashSet<>();
	public static void changeFlying(UUID uuid){
		if(flying.contains(uuid)){
			flying.remove(uuid);
		} else {
			flying.add(uuid);
		}
	}
	public static boolean isFlying(UUID uuid){
		return flying.contains(uuid);
	}
}
