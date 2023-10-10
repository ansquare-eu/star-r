package eu.ansquare.starr.entity.damage;

import eu.ansquare.starr.StarR;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.damage.DamageSource;
import net.minecraft.entity.damage.DamageType;
import net.minecraft.registry.RegistryKey;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.util.Identifier;
import net.minecraft.world.World;

public class ModDamageStuff {
	public static final RegistryKey<DamageType> LASER_DAMAGE_TYPE = RegistryKey.of(RegistryKeys.DAMAGE_TYPE, new Identifier(StarR.MODID, "laser_damage_type"));

	public static DamageSource of(World world, RegistryKey<DamageType> key, LivingEntity attacker) {
		return new DamageSource(world.getRegistryManager().get(RegistryKeys.DAMAGE_TYPE).getHolderOrThrow(key), attacker);
	}
}
