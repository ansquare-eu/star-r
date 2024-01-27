package eu.ansquare.squarepowered.cca;

import dev.onyxstudios.cca.api.v3.component.ComponentKey;
import dev.onyxstudios.cca.api.v3.component.ComponentRegistryV3;
import dev.onyxstudios.cca.api.v3.entity.EntityComponentFactoryRegistry;
import dev.onyxstudios.cca.api.v3.entity.EntityComponentInitializer;
import dev.onyxstudios.cca.api.v3.entity.RespawnCopyStrategy;
import eu.ansquare.squarepowered.Squarepowered;
import net.minecraft.entity.LivingEntity;

public class SquareComponents implements EntityComponentInitializer {
	public static final ComponentKey<SavedLocationComponent> SAVED_LOCATION_COMPONENT =
			ComponentRegistryV3.INSTANCE.getOrCreate(Squarepowered.id("saved_location"), SavedLocationComponent.class);
	public static final ComponentKey<ClientStatesComponent> CLIENT_STATE_COMPONENT =
			ComponentRegistryV3.INSTANCE.getOrCreate(Squarepowered.id("client_states"), ClientStatesComponent.class);
	public static final ComponentKey<MultiInventoryComponent> MULTI_INVENTORY =
			ComponentRegistryV3.INSTANCE.getOrCreate(Squarepowered.id("multi_inventory"), MultiInventoryComponent.class);


	@Override
	public void registerEntityComponentFactories(EntityComponentFactoryRegistry registry) {
		registry.registerFor(LivingEntity.class, SAVED_LOCATION_COMPONENT, entity -> new SavedLocationComponent(entity));
		registry.registerForPlayers(SAVED_LOCATION_COMPONENT, player -> new SavedLocationComponent(player), RespawnCopyStrategy.ALWAYS_COPY);
		registry.registerFor(LivingEntity.class, CLIENT_STATE_COMPONENT, entity -> new ClientStatesComponent());
		registry.registerForPlayers(MULTI_INVENTORY, player -> new MultiInventoryComponent(), RespawnCopyStrategy.ALWAYS_COPY);
		registry.registerFor(LivingEntity.class, MULTI_INVENTORY, entity -> new MultiInventoryComponent());
	}
}
