package eu.ansquare.starr.entity.render;

import eu.ansquare.starr.StarR;
import eu.ansquare.starr.client.StarRClient;
import eu.ansquare.starr.client.wearables.model.FaceWearableModel;
import eu.ansquare.starr.entity.CapeEntity;
import eu.ansquare.starr.entity.PalicaEntity;
import eu.ansquare.starr.entity.model.CapeEntityModel;
import eu.ansquare.starr.entity.model.PalicaEntityModel;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.model.Model;
import net.minecraft.client.network.AbstractClientPlayerEntity;
import net.minecraft.client.render.OverlayTexture;
import net.minecraft.client.render.RenderLayer;
import net.minecraft.client.render.VertexConsumerProvider;
import net.minecraft.client.render.entity.EntityRenderer;
import net.minecraft.client.render.entity.EntityRendererFactory;
import net.minecraft.client.render.entity.LivingEntityRenderer;
import net.minecraft.client.render.entity.ProjectileEntityRenderer;
import net.minecraft.client.render.entity.model.EntityModel;
import net.minecraft.client.render.entity.model.PlayerEntityModel;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.util.Identifier;
import net.minecraft.util.math.Axis;
import net.minecraft.util.math.MathHelper;

public class PalicaEntityRenderer<T extends PalicaEntity> extends ProjectileEntityRenderer<T> {
	private EntityModel<PalicaEntity> model = null;
	public PalicaEntityRenderer(EntityRendererFactory.Context ctx) {
		super(ctx);
	}


	@Override
	public void render(T entity, float yaw, float tickDelta, MatrixStack matrices, VertexConsumerProvider vertexConsumers, int light) {
		if (model == null) {

			model = new PalicaEntityModel(MinecraftClient.getInstance().getEntityModelLoader().getModelPart(PalicaEntityModel.LAYER_LOCATION));
		} else {
			matrices.push();
			matrices.multiply(Axis.Y_POSITIVE.rotationDegrees(MathHelper.lerp(tickDelta, entity.prevYaw, entity.getYaw()) - 90.0F));
			matrices.multiply(Axis.Z_POSITIVE.rotationDegrees(MathHelper.lerp(tickDelta, entity.prevPitch, entity.getPitch())));
			model.render(matrices, vertexConsumers.getBuffer(RenderLayer.getEntityCutout(getTexture(entity))), light, OverlayTexture.DEFAULT_UV, 1f, 1f, 1f, 1f);
			matrices.pop();
		}
	}

	@Override
	public Identifier getTexture(PalicaEntity entity) {
		return new Identifier(StarR.MODID, "textures/entity/palica/palica.png");
	}
}
