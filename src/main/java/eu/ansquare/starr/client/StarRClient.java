package eu.ansquare.starr.client;

import com.sammy.lodestone.systems.rendering.particle.world.WorldParticleEffect;
import eu.ansquare.starr.StarR;
import eu.ansquare.starr.blocks.ModBlocks;
import eu.ansquare.starr.client.particle.LaserParticleType;
import eu.ansquare.starr.client.screen.TeleportSelectScreen;
import eu.ansquare.starr.client.wearables.TrinketRenderers;
import eu.ansquare.starr.client.wearables.TwoStateWearableModels;
import eu.ansquare.starr.client.wearables.model.CapeWearableModel;
import eu.ansquare.starr.client.wearables.model.FaceWearableModel;
import eu.ansquare.starr.client.wearables.model.hare.HareOneModel;
import eu.ansquare.starr.client.wearables.model.hare.HareTwoModel;
import eu.ansquare.starr.entity.LaserEntity;
import eu.ansquare.starr.entity.ModEntities;
import eu.ansquare.starr.entity.model.CapeEntityModel;
import eu.ansquare.starr.entity.model.PalicaEntityModel;
import eu.ansquare.starr.entity.render.CapeEntityRenderer;
import eu.ansquare.starr.entity.render.LaserEntityRenderer;
import eu.ansquare.starr.entity.render.PalicaEntityRenderer;
import eu.ansquare.starr.items.GetItemTypes;
import eu.ansquare.starr.items.wearable.WearableItem;
import eu.ansquare.starr.items.ModItems;
import eu.ansquare.starr.network.ModPackets;
import eu.ansquare.starr.screenhandler.ModScreenHandlers;
import eu.ansquare.starr.util.network.ClientLaserHolder;
import net.fabricmc.fabric.api.client.rendering.v1.EntityModelLayerRegistry;
import net.fabricmc.fabric.api.client.rendering.v1.EntityRendererRegistry;
import net.fabricmc.fabric.api.client.screenhandler.v1.ScreenRegistry;
import net.minecraft.client.gui.screen.ingame.HandledScreens;
import net.minecraft.client.render.RenderLayer;
import net.minecraft.client.render.entity.model.EntityModelLayer;
import net.minecraft.particle.ParticleEffect;
import net.minecraft.util.Identifier;
import org.quiltmc.loader.api.ModContainer;
import org.quiltmc.qsl.base.api.entrypoint.client.ClientModInitializer;
import org.quiltmc.qsl.block.extensions.api.client.BlockRenderLayerMap;

public class StarRClient implements ClientModInitializer {
	public static ClientLaserHolder LASER_HOLDER= new ClientLaserHolder();
	public static final EntityModelLayer MODEL_CAPE_LAYER = new EntityModelLayer(new Identifier(StarR.MODID, "cape"), "main");

	@Override
	public void onInitializeClient(ModContainer mod) {

		EntityModelLayerRegistry.registerModelLayer(MODEL_CAPE_LAYER, CapeEntityModel::getTexturedModelData);
		EntityRendererRegistry.register(ModEntities.CAPE, (context) -> {
			return new CapeEntityRenderer(context);
		});
		EntityModelLayerRegistry.registerModelLayer(PalicaEntityModel.LAYER_LOCATION, PalicaEntityModel::getTexturedModelData);
		EntityRendererRegistry.register(ModEntities.PALICA, (context) -> {
			return new PalicaEntityRenderer(context);
		});
		EntityRendererRegistry.register(ModEntities.LASER, (context) -> {
			return new LaserEntityRenderer(context);
		});
		EntityModelLayerRegistry.registerModelLayer(CapeWearableModel.LAYER_LOCATION, CapeWearableModel::getTexturedModelData);
		EntityModelLayerRegistry.registerModelLayer(FaceWearableModel.LAYER_LOCATION, FaceWearableModel::getTexturedModelData);
		EntityModelLayerRegistry.registerModelLayer(HareOneModel.LAYER_LOCATION, HareOneModel::getTexturedModelData);
		EntityModelLayerRegistry.registerModelLayer(HareTwoModel.LAYER_LOCATION, HareTwoModel::getTexturedModelData);
		ModKeyBinds.init();
		ModPackets.initS2C();
		TrinketRenderers.registerCapeRenderers(GetItemTypes.getCapes());
		TrinketRenderers.registerTwoStateRenderers(GetItemTypes.getTwoStateWearables());
		BlockRenderLayerMap.put(RenderLayer.getTranslucent(), ModBlocks.FORCEFIELD);
		HandledScreens.register(ModScreenHandlers.TELEPORT_SCREEN, TeleportSelectScreen::new);

	}
}
