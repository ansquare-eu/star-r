package eu.ansquare.starr.client;

import com.mojang.blaze3d.platform.InputUtil;
import eu.ansquare.starr.StarR;
import eu.ansquare.starr.client.wearables.TrinketRenderers;
import eu.ansquare.starr.client.wearables.model.CapeWearableModel;
import eu.ansquare.starr.entity.CapeEntity;
import eu.ansquare.starr.entity.ModEntities;
import eu.ansquare.starr.entity.model.CapeEntityModel;
import eu.ansquare.starr.entity.render.CapeEntityRenderer;
import eu.ansquare.starr.items.CapeItem;
import eu.ansquare.starr.items.ModItems;
import eu.ansquare.starr.network.ModPackets;
import net.fabricmc.fabric.api.client.event.lifecycle.v1.ClientTickEvents;
import net.fabricmc.fabric.api.client.keybinding.v1.KeyBindingHelper;
import net.fabricmc.fabric.api.client.rendering.v1.EntityModelLayerRegistry;
import net.fabricmc.fabric.api.client.rendering.v1.EntityRendererRegistry;
import net.minecraft.client.option.KeyBind;
import net.minecraft.client.render.entity.feature.CapeFeatureRenderer;
import net.minecraft.client.render.entity.model.EntityModelLayer;
import net.minecraft.text.Text;
import net.minecraft.util.Identifier;
import org.lwjgl.glfw.GLFW;
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
		ModKeyBinds.init();
		ModPackets.initS2C();
		TrinketRenderers.registerCapeRenderer(((CapeItem) ModItems.CAPE));
	}
}
