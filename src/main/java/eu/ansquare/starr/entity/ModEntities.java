package eu.ansquare.starr.entity;

import eu.ansquare.starr.StarR;
import net.minecraft.entity.EntityDimensions;
import net.minecraft.entity.EntityType;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;
import org.quiltmc.qsl.entity.api.QuiltEntityTypeBuilder;

import java.util.LinkedHashMap;
import java.util.Map;

public class ModEntities {
	private static final Map<EntityType, Identifier> ENTITIES = new LinkedHashMap();

	public static final EntityType PALICA = createEntity("palica", QuiltEntityTypeBuilder.<PalicaEntity>create().entityFactory(PalicaEntity::new).setDimensions(EntityDimensions.fixed(0.2f, 1f)).build());
	public static final EntityType BOYSHIELD = createEntity("boyshield", QuiltEntityTypeBuilder.<BoyshieldEntity>create().entityFactory(BoyshieldEntity::new).setDimensions(EntityDimensions.fixed(0.2f, 1f)).build());
	public static final EntityType HONEYBALL = createEntity("honey_ball", QuiltEntityTypeBuilder.<HoneyballEntity>create().entityFactory(HoneyballEntity::new).setDimensions(EntityDimensions.fixed(0.25F, 0.25F)).maxBlockTrackingRange(4).trackingTickInterval(10).build());
	public static final EntityType SLIMEBALL = createEntity("slime_ball", QuiltEntityTypeBuilder.<SlimeballEntity>create().entityFactory(SlimeballEntity::new).setDimensions(EntityDimensions.fixed(0.25F, 0.25F)).maxBlockTrackingRange(4).trackingTickInterval(10).build());

	public static <T extends EntityType> T createEntity(String name, T entity){
		ENTITIES.put(entity, new Identifier(StarR.MODID, name));
		return entity;
	}
	public static void init(){
		ENTITIES.keySet().forEach(entityType -> Registry.register(Registries.ENTITY_TYPE, ENTITIES.get(entityType), entityType));
	}
}
