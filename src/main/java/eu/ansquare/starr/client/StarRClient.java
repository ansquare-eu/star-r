package eu.ansquare.starr.client;

import eu.ansquare.starr.StarR;
import eu.ansquare.starr.blocks.ModBlocks;
import eu.ansquare.starr.client.particle.ModParticles;
import eu.ansquare.starr.client.wearables.TrinketRenderers;
import eu.ansquare.starr.client.wearables.model.SkiModel;
import eu.ansquare.starr.client.wearables.model.cape.CapeWearableModel;
import eu.ansquare.starr.client.wearables.model.FaceWearableModel;
import eu.ansquare.starr.client.wearables.model.cape.SimpleCapeWearableModel;
import eu.ansquare.starr.client.wearables.model.hare.HareOneModel;
import eu.ansquare.starr.client.wearables.model.hare.HareTwoModel;
import eu.ansquare.starr.entity.ModEntities;
import eu.ansquare.starr.entity.model.BoyshieldEntityModel;
import eu.ansquare.starr.entity.model.PalicaEntityModel;
import eu.ansquare.starr.entity.render.BoyshieldEntityRenderer;
import eu.ansquare.starr.entity.render.LaserEntityRenderer;
import eu.ansquare.starr.entity.render.PalicaEntityRenderer;
import eu.ansquare.starr.items.GetItemTypes;
import eu.ansquare.starr.items.ModItems;
import eu.ansquare.starr.util.network.ClientPlayerHolder;
import net.fabricmc.fabric.api.client.rendering.v1.ColorProviderRegistry;
import net.fabricmc.fabric.api.client.rendering.v1.EntityModelLayerRegistry;
import net.fabricmc.fabric.api.client.rendering.v1.EntityRendererRegistry;
import net.minecraft.client.color.block.BlockColorProvider;
import net.minecraft.client.gui.screen.ingame.HandledScreens;
import net.minecraft.client.render.RenderLayer;
import net.minecraft.client.render.entity.model.BoatEntityModel;
import net.minecraft.client.render.entity.model.EntityModelLayer;
import net.minecraft.util.Identifier;
import net.minecraft.util.Pair;
import org.quiltmc.loader.api.ModContainer;
import org.quiltmc.qsl.base.api.entrypoint.client.ClientModInitializer;
import org.quiltmc.qsl.block.extensions.api.client.BlockRenderLayerMap;

public class StarRClient implements ClientModInitializer {
	public static ClientPlayerHolder LASER_HOLDER= new ClientPlayerHolder();
	@Override
	public void onInitializeClient(ModContainer mod) {

		EntityModelLayerRegistry.registerModelLayer(PalicaEntityModel.LAYER_LOCATION, PalicaEntityModel::getTexturedModelData);
		EntityRendererRegistry.register(ModEntities.PALICA, (context) -> {
			return new PalicaEntityRenderer(context);
		});
		EntityModelLayerRegistry.registerModelLayer(BoyshieldEntityModel.LAYER_LOCATION, BoyshieldEntityModel::getTexturedModelData);
		EntityRendererRegistry.register(ModEntities.BOYSHIELD, (context) -> {
			return new BoyshieldEntityRenderer<>(context);
		});
		EntityRendererRegistry.register(ModEntities.LASER, (context) -> {
			return new LaserEntityRenderer(context);
		});
		EntityModelLayerRegistry.registerModelLayer(CapeWearableModel.LAYER_LOCATION, CapeWearableModel::getTexturedModelData);
		EntityModelLayerRegistry.registerModelLayer(SimpleCapeWearableModel.LAYER_LOCATION, SimpleCapeWearableModel::getTexturedModelData);
		EntityModelLayerRegistry.registerModelLayer(FaceWearableModel.LAYER_LOCATION, FaceWearableModel::getTexturedModelData);
		EntityModelLayerRegistry.registerModelLayer(HareOneModel.LAYER_LOCATION, HareOneModel::getTexturedModelData);
		EntityModelLayerRegistry.registerModelLayer(HareTwoModel.LAYER_LOCATION, HareTwoModel::getTexturedModelData);
		EntityModelLayerRegistry.registerModelLayer(SkiModel.LAYER_LOCATION, SkiModel::getTexturedModelData);

		ModParticles.init();
		TrinketRenderers.registerSimpleWearables(GetItemTypes.getSimpleWearables());
		TrinketRenderers.registerTwoStateRenderers(GetItemTypes.getTwoStateWearables());
		BlockRenderLayerMap.put(RenderLayer.getTranslucent(), ModBlocks.FORCEFIELD);
		ColorProviderRegistry.BLOCK.register(ModBlocks.FORCEFIELD, ModBlocks.FORCEFIELD);
		ColorProviderRegistry.ITEM.register(ModItems.MEGASWORD_MATERIALS, ModItems.MEGASWORD);
	}
}
