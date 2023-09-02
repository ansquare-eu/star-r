package eu.ansquare.starr.entity;

import eu.ansquare.starr.StarR;
import net.fabricmc.fabric.api.itemgroup.v1.ItemGroupEvents;
import net.minecraft.entity.EntityDimensions;
import net.minecraft.entity.EntityType;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroups;
import net.minecraft.item.SpawnEggItem;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;
import org.quiltmc.qsl.entity.api.QuiltEntityTypeBuilder;
import org.quiltmc.qsl.item.setting.api.QuiltItemSettings;

import java.util.LinkedHashMap;
import java.util.Map;

public class ModEntities {
	private static final Map<EntityType, Identifier> ENTITIES = new LinkedHashMap();

	public static final EntityType CAPE = createEntity("cape", QuiltEntityTypeBuilder.<CapeEntity>createLiving().entityFactory(CapeEntity::new).setDimensions(EntityDimensions.fixed(0.1f, 0.1f)).defaultAttributes(CapeEntity.createJellyfishAttributes()).build());
	public static <T extends EntityType> T createEntity(String name, T entity){
		ENTITIES.put(entity, new Identifier(StarR.MODID, name));
		return entity;
	}
	public static void init(){
		ENTITIES.keySet().forEach(entityType -> Registry.register(Registries.ENTITY_TYPE, ENTITIES.get(entityType), entityType));
	}
}