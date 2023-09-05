package eu.ansquare.starr.client;

import eu.ansquare.starr.StarR;
import eu.ansquare.starr.client.wearables.TrinketRenderers;
import eu.ansquare.starr.client.wearables.model.CapeWearableModel;
import eu.ansquare.starr.client.wearables.model.FaceWearableModel;
import eu.ansquare.starr.entity.ModEntities;
import eu.ansquare.starr.entity.model.CapeEntityModel;
import eu.ansquare.starr.entity.render.CapeEntityRenderer;
import eu.ansquare.starr.items.GetItemTypes;
import eu.ansquare.starr.items.wearable.WearableItem;
import eu.ansquare.starr.items.ModItems;
import eu.ansquare.starr.network.ModPackets;
import net.fabricmc.fabric.api.client.rendering.v1.EntityModelLayerRegistry;
import net.fabricmc.fabric.api.client.rendering.v1.EntityRendererRegistry;
import net.minecraft.client.render.entity.model.EntityModelLayer;
import net.minecraft.util.Identifier;
import org.quiltmc.loader.api.ModContainer;
import org.quiltmc.qsl.base.api.entrypoint.client.ClientModInitializer;

public class StarRClient implements ClientModInitializer {
	public static final EntityModelLayer MODEL_CAPE_LAYER = new EntityModelLayer(new Identifier(StarR.MODID, "cape"), "main");

	@Override
	public void onInitializeClient(ModContainer mod) {
		EntityModelLayerRegistry.registerModelLayer(MODEL_CAPE_LAYER, CapeEntityModel::getTexturedModelData);
		EntityRendererRegistry.register(ModEntities.CAPE, (context) -> {
			return new CapeEntityRenderer(context);
		});
		EntityModelLayerRegistry.registerModelLayer(CapeWearableModel.LAYER_LOCATION, CapeWearableModel::getTexturedModelData);
		EntityModelLayerRegistry.registerModelLayer(FaceWearableModel.LAYER_LOCATION, FaceWearableModel::getTexturedModelData);
		ModKeyBinds.init();
		ModPackets.initS2C();
		TrinketRenderers.registerCapeRenderers(GetItemTypes.getCapes());

	}
}
