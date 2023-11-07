package eu.ansquare.starr.entity.render;

import eu.ansquare.starr.StarR;
import eu.ansquare.starr.entity.BoyshieldEntity;
import eu.ansquare.starr.entity.PalicaEntity;
import eu.ansquare.starr.entity.model.BoyshieldEntityModel;
import eu.ansquare.starr.entity.model.PalicaEntityModel;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.render.OverlayTexture;
import net.minecraft.client.render.RenderLayer;
import net.minecraft.client.render.VertexConsumerProvider;
import net.minecraft.client.render.entity.EntityRendererFactory;
import net.minecraft.client.render.entity.ProjectileEntityRenderer;
import net.minecraft.client.render.entity.model.EntityModel;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.util.Identifier;
import net.minecraft.util.math.Axis;
import net.minecraft.util.math.MathHelper;

public class BoyshieldEntityRenderer<T extends BoyshieldEntity> extends ProjectileEntityRenderer<T> {
	private EntityModel<BoyshieldEntity> model = null;
	public BoyshieldEntityRenderer(EntityRendererFactory.Context ctx) {
		super(ctx);
	}


	@Override
	public void render(T entity, float yaw, float tickDelta, MatrixStack matrices, VertexConsumerProvider vertexConsumers, int light) {
		if (model == null) {

			model = new BoyshieldEntityModel(MinecraftClient.getInstance().getEntityModelLoader().getModelPart(BoyshieldEntityModel.LAYER_LOCATION));
		} else {
			matrices.push();
			matrices.multiply(Axis.Y_POSITIVE.rotationDegrees(MathHelper.lerp(tickDelta, entity.prevYaw, entity.getYaw())));
			model.render(matrices, vertexConsumers.getBuffer(RenderLayer.getEntityTranslucent(getTexture(entity))), light, OverlayTexture.DEFAULT_UV, 1f, 1f, 1f, 1f);
			matrices.pop();
		}
	}

	@Override
	public Identifier getTexture(BoyshieldEntity entity) {
		return new Identifier(StarR.MODID, "textures/entity/boyshield/boyshield.png");
	}
}
