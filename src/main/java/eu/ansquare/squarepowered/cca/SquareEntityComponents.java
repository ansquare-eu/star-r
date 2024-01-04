package eu.ansquare.squarepowered.cca;

import dev.onyxstudios.cca.api.v3.component.ComponentKey;
import dev.onyxstudios.cca.api.v3.component.ComponentRegistryV3;
import dev.onyxstudios.cca.api.v3.entity.EntityComponentFactoryRegistry;
import dev.onyxstudios.cca.api.v3.entity.EntityComponentInitializer;
import dev.onyxstudios.cca.api.v3.entity.RespawnCopyStrategy;
import eu.ansquare.squarepowered.Squarepowered;
import net.minecraft.entity.LivingEntity;
import net.minecraft.util.Identifier;

public class SquareEntityComponents implements EntityComponentInitializer {
	public static final ComponentKey<SavedLocationComponent> SAVED_LOCATION_COMPONENT =
			ComponentRegistryV3.INSTANCE.getOrCreate(Squarepowered.id("saved_location"), SavedLocationComponent.class);
	public static final ComponentKey<ClientStatesComponent> CLIENT_STATE_COMPONENT =
			ComponentRegistryV3.INSTANCE.getOrCreate(Squarepowered.id("client_states"), ClientStatesComponent.class);
	@Override
	public void registerEntityComponentFactories(EntityComponentFactoryRegistry registry) {
		registry.registerFor(LivingEntity.class, SAVED_LOCATION_COMPONENT, world -> new SavedLocationComponent());
		registry.registerForPlayers(SAVED_LOCATION_COMPONENT, player -> new SavedLocationComponent(), RespawnCopyStrategy.ALWAYS_COPY);
		registry.registerFor(LivingEntity.class, CLIENT_STATE_COMPONENT, world -> new ClientStatesComponent());

	}
}
