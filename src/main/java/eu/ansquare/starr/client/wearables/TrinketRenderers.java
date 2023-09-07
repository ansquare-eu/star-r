package eu.ansquare.starr.client.wearables;

import dev.emi.trinkets.api.SlotReference;
import dev.emi.trinkets.api.client.TrinketRenderer;
import dev.emi.trinkets.api.client.TrinketRendererRegistry;
import eu.ansquare.starr.client.wearables.model.CapeWearableModel;
import eu.ansquare.starr.client.wearables.model.FaceWearableModel;
import eu.ansquare.starr.client.wearables.model.hare.HareOneModel;
import eu.ansquare.starr.client.wearables.model.hare.HareTwoModel;
import eu.ansquare.starr.items.wearable.TwoStateWearable;
import eu.ansquare.starr.items.wearable.WearableItem;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.model.Model;
import net.minecraft.client.network.AbstractClientPlayerEntity;
import net.minecraft.client.render.OverlayTexture;
import net.minecraft.client.render.RenderLayer;
import net.minecraft.client.render.VertexConsumerProvider;
import net.minecraft.client.render.entity.model.EntityModel;
import net.minecraft.client.render.entity.model.PlayerEntityModel;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.entity.LivingEntity;
import net.minecraft.item.ItemStack;

public class TrinketRenderers {
	public static void registerCapeRenderers(WearableItem... items){
		for (WearableItem item:items) {
			TrinketRendererRegistry.registerRenderer(item, new TrinketRenderer() {
				private Model model = null;
				@Override
				public void render(ItemStack stack, SlotReference slotReference, EntityModel<? extends LivingEntity> contextModel, MatrixStack matrices, VertexConsumerProvider vertexConsumers, int light, LivingEntity entity, float limbAngle, float limbDistance, float tickDelta, float animationProgress, float headYaw, float headPitch) {
					if (model == null) {
						model = new CapeWearableModel(MinecraftClient.getInstance().getEntityModelLoader().getModelPart(CapeWearableModel.LAYER_LOCATION));
					} else if (!entity.isInvisible()) {
						matrices.push();
						model.render(matrices, vertexConsumers.getBuffer(RenderLayer.getEntityCutout(item.getTexture())), light, OverlayTexture.DEFAULT_UV, 1f, 1f, 1f, 1f);
						matrices.pop();
					}
				}
			});
		}
	}
	public static void registerMaskRenderers(WearableItem... items){
		for (WearableItem item:items) {
			TrinketRendererRegistry.registerRenderer(item, new TrinketRenderer() {
				private Model model = null;
				@Override
				public void render(ItemStack stack, SlotReference slotReference, EntityModel<? extends LivingEntity> contextModel, MatrixStack matrices, VertexConsumerProvider vertexConsumers, int light, LivingEntity entity, float limbAngle, float limbDistance, float tickDelta, float animationProgress, float headYaw, float headPitch) {
					if (model == null) {
						model = new FaceWearableModel(MinecraftClient.getInstance().getEntityModelLoader().getModelPart(FaceWearableModel.LAYER_LOCATION));
					} else if (!entity.isInvisible()) {
						matrices.push();
						((PlayerEntityModel<AbstractClientPlayerEntity>) contextModel).head.rotate(matrices);
						model.render(matrices, vertexConsumers.getBuffer(RenderLayer.getEntityCutout(item.getTexture())), light, OverlayTexture.DEFAULT_UV, 1f, 1f, 1f, 1f);
						matrices.pop();
					}
				}
			});
		}
	}
	public static void registerTwoStateRenderers(TwoStateWearable... items){
		for (TwoStateWearable item:items) {
			TrinketRendererRegistry.registerRenderer(item, new TrinketRenderer() {
				private Model model = null;
				private Model secondModel = null;

				@Override
				public void render(ItemStack stack, SlotReference slotReference, EntityModel<? extends LivingEntity> contextModel, MatrixStack matrices, VertexConsumerProvider vertexConsumers, int light, LivingEntity entity, float limbAngle, float limbDistance, float tickDelta, float animationProgress, float headYaw, float headPitch) {
					if (model == null) {

						model = new HareOneModel(MinecraftClient.getInstance().getEntityModelLoader().getModelPart(HareOneModel.LAYER_LOCATION));
						secondModel = new HareTwoModel(MinecraftClient.getInstance().getEntityModelLoader().getModelPart(HareTwoModel.LAYER_LOCATION));
					} else if (!entity.isInvisible()) {
						matrices.push();
						((PlayerEntityModel<AbstractClientPlayerEntity>) contextModel).head.rotate(matrices);
						if(item.getState(stack)){
							secondModel.render(matrices, vertexConsumers.getBuffer(RenderLayer.getEntityCutout(item.getTexture())), light, OverlayTexture.DEFAULT_UV, 1f, 1f, 1f, 1f);
						}
						else {
							model.render(matrices, vertexConsumers.getBuffer(RenderLayer.getEntityCutout(item.getTexture())), light, OverlayTexture.DEFAULT_UV, 1f, 1f, 1f, 1f);
						}
						matrices.pop();
					}
				}
			});
		}
	}
}