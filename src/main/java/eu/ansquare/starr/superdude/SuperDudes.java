package eu.ansquare.starr.superdude;

import eu.ansquare.starr.ModRegistries;
import eu.ansquare.starr.StarR;
import eu.ansquare.starr.cca.StarREntityComponents;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.registry.Registry;
import net.minecraft.registry.RegistryKey;
import net.minecraft.util.Identifier;

import java.awt.*;
import java.util.*;

public class SuperDudes {
	private static Map<SuperDude, Identifier> SUPERDUDES = new HashMap<>();

	public static final SuperDude TEST_SUPER_DUDE = create("testtype", new TestSuperDude(true, new Color(0x0B4AC9)));
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
