package eu.ansquare.starr.superdude;

import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

public class SuperDudes {
	public static final SuperDude TEST_SUPER_DUDE = new TestSuperDude();
	public static SuperDude getSuperDude(String type){
		switch (type){
			case "testType":
				return TEST_SUPER_DUDE;
			case "npot":
				return TEST_SUPER_DUDE;
			default:
				return null;
		}
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
