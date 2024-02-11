package eu.ansquare.starr.client;

import eu.ansquare.starr.blocks.ModBlocks;
import eu.ansquare.starr.client.particle.ModParticles;
import eu.ansquare.starr.client.wearables.TrinketRenderers;
import eu.ansquare.starr.client.wearables.model.FaceWearableModel;
import eu.ansquare.starr.client.wearables.model.SkiModel;
import eu.ansquare.starr.client.wearables.model.cape.CapeWearableModel;
import eu.ansquare.starr.client.wearables.model.cape.SimpleCapeWearableModel;
import eu.ansquare.starr.client.wearables.model.hare.HareOneModel;
import eu.ansquare.starr.client.wearables.model.hare.HareTwoModel;
import eu.ansquare.starr.entity.ModEntities;
import eu.ansquare.starr.entity.model.BoyshieldEntityModel;
import eu.ansquare.starr.entity.model.PalicaEntityModel;
import eu.ansquare.starr.entity.render.BoyshieldEntityRenderer;
import eu.ansquare.starr.entity.render.PalicaEntityRenderer;
import eu.ansquare.starr.items.ModItems;
import net.fabricmc.fabric.api.client.rendering.v1.ColorProviderRegistry;
import net.fabricmc.fabric.api.client.rendering.v1.EntityModelLayerRegistry;
import net.fabricmc.fabric.api.client.rendering.v1.EntityRendererRegistry;
import net.minecraft.client.render.RenderLayer;
import net.minecraft.client.render.entity.FlyingItemEntityRenderer;
import org.quiltmc.loader.api.ModContainer;
import org.quiltmc.qsl.base.api.entrypoint.client.ClientModInitializer;
import org.quiltmc.qsl.block.extensions.api.client.BlockRenderLayerMap;

public class StarRClient implements ClientModInitializer {
	@Override
	public void onInitializeClient(ModContainer mod) {
		ModParticles.init();
		initModelLayers();
		initEntityRenderers();
		initTrinketRenderers();
		initBlockRenderLayers();
		initColorProviders();
	}
	private void initModelLayers(){
		EntityModelLayerRegistry.registerModelLayer(PalicaEntityModel.LAYER_LOCATION, PalicaEntityModel::getTexturedModelData);
		EntityModelLayerRegistry.registerModelLayer(BoyshieldEntityModel.LAYER_LOCATION, BoyshieldEntityModel::getTexturedModelData);
		EntityModelLayerRegistry.registerModelLayer(CapeWearableModel.LAYER_LOCATION, CapeWearableModel::getTexturedModelData);
		EntityModelLayerRegistry.registerModelLayer(SimpleCapeWearableModel.LAYER_LOCATION, SimpleCapeWearableModel::getTexturedModelData);
		EntityModelLayerRegistry.registerModelLayer(FaceWearableModel.LAYER_LOCATION, FaceWearableModel::getTexturedModelData);
		EntityModelLayerRegistry.registerModelLayer(HareOneModel.LAYER_LOCATION, HareOneModel::getTexturedModelData);
		EntityModelLayerRegistry.registerModelLayer(HareTwoModel.LAYER_LOCATION, HareTwoModel::getTexturedModelData);
		EntityModelLayerRegistry.registerModelLayer(SkiModel.LAYER_LOCATION, SkiModel::getTexturedModelData);
	}
	private void initEntityRenderers(){
		EntityRendererRegistry.register(ModEntities.PALICA, (context) -> {
			return new PalicaEntityRenderer(context);
		});
		EntityRendererRegistry.register(ModEntities.BOYSHIELD, (context) -> {
			return new BoyshieldEntityRenderer<>(context);
		});
		EntityRendererRegistry.register(ModEntities.HONEYBALL, (context) ->
				new FlyingItemEntityRenderer(context));
		EntityRendererRegistry.register(ModEntities.SLIMEBALL, (context) ->
				new FlyingItemEntityRenderer(context));
	}
	private void initTrinketRenderers(){
		TrinketRenderers.registerSimpleWearables(ModItems.WHITE_SKI);
		TrinketRenderers.registerTwoStateRenderers(ModItems.CAPE);
	}
	private void initBlockRenderLayers(){
		BlockRenderLayerMap.put(RenderLayer.getTranslucent(), ModBlocks.FORCEFIELD);
	}
	private void initColorProviders(){
		ColorProviderRegistry.BLOCK.register(ModBlocks.FORCEFIELD, ModBlocks.FORCEFIELD);
		ColorProviderRegistry.ITEM.register(ModItems.MEGASWORD_MATERIALS, ModItems.MEGASWORD);
	}
}
