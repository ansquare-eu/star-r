package eu.ansquare.starr.cca;

import dev.onyxstudios.cca.api.v3.component.ComponentKey;
import dev.onyxstudios.cca.api.v3.component.ComponentRegistryV3;
import dev.onyxstudios.cca.api.v3.entity.EntityComponentFactoryRegistry;
import dev.onyxstudios.cca.api.v3.entity.EntityComponentInitializer;
import eu.ansquare.starr.StarR;
import net.minecraft.entity.LivingEntity;
import net.minecraft.util.Identifier;

public final class StarREntityComponents implements EntityComponentInitializer {
	public static final ComponentKey<SuperDudeComponent> SUPER_DUDE_COMPONENT =
			ComponentRegistryV3.INSTANCE.getOrCreate(new Identifier(StarR.MODID, "superdude"), SuperDudeComponent.class);
	public static final ComponentKey<TeleportLocComponent> TELEPORT_LOC_COMPONENT =
			ComponentRegistryV3.INSTANCE.getOrCreate(new Identifier(StarR.MODID, "teleportloc"), TeleportLocComponent.class);
	@Override
	public void registerEntityComponentFactories(EntityComponentFactoryRegistry registry) {
		registry.registerFor(LivingEntity.class, SUPER_DUDE_COMPONENT, world -> new SuperDudeComponent());
		registry.registerFor(LivingEntity.class, TELEPORT_LOC_COMPONENT, world -> new TeleportLocComponent());
	}
}
