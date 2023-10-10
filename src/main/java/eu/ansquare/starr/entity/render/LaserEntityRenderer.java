package eu.ansquare.starr.entity.render;

import com.mojang.blaze3d.vertex.VertexConsumer;
import eu.ansquare.starr.StarR;
import eu.ansquare.starr.entity.LaserEntity;
import net.minecraft.client.render.OverlayTexture;
import net.minecraft.client.render.RenderLayer;
import net.minecraft.client.render.VertexConsumerProvider;
import net.minecraft.client.render.entity.EntityRenderer;
import net.minecraft.client.render.entity.EntityRendererFactory;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.util.Identifier;
import net.minecraft.util.math.Axis;
import net.minecraft.util.math.MathHelper;
import org.joml.Matrix3f;
import org.joml.Matrix4f;

import java.awt.*;

public class LaserEntityRenderer<T extends LaserEntity> extends EntityRenderer<T> {
	public static final Identifier LASER_TEXTURE = new Identifier(StarR.MODID, "textures/laser/laser.png");
	//private static final RenderLayer LAYER;

	public LaserEntityRenderer(EntityRendererFactory.Context ctx) {
		super(ctx);
	}
	public void render(T entity, float yaw, float tickDelta, MatrixStack matrices, VertexConsumerProvider vertexConsumers, int light){
//		matrices.push();
//		VFXBuilders.WorldVFXBuilder builder = VFXBuilders.createWorld().setPosColorTexLightmapDefaultFormat();
//		float lerp = 0.0F;
//		if (entity.age <= 60) {
//			lerp = Easing.EXPO_IN.ease(MathHelper.lerp(MathHelper.clamp((float)entity.age / 60.0f, 0.0F, 1.0F), 0.0F, 1.0F), 0.0F, 1.0F, 1.0F);
//		} else {
//			lerp = 1f;//Easing.SINE_OUT.ease(MathHelper.lerp(MathHelper.clamp(((float)entity.age - 60.0F) / 60.0F, 0.0F, 1.0F), 1.0F, 0.0F), 0.0F, 1.0F, 1.0F);
//		}
//		matrices.multiply(Axis.Y_POSITIVE.rotationDegrees(MathHelper.lerp(tickDelta, entity.prevYaw, entity.getYaw()) * -1));
//		matrices.multiply(Axis.X_POSITIVE.rotationDegrees(MathHelper.lerp(tickDelta, entity.prevPitch, entity.getPitch()) + 90.0F));
//		float minSize = 1.0f * lerp;
//		float maxSize = 2.0f * lerp;
//		float inc = 0.5F;
//		Color color = entity.getColor();
//		for(float size = minSize; size <= maxSize; size += inc) {
//			float alpha = MathHelper.lerp((size - minSize) / (maxSize - minSize), 0.2F, 0.8F);
//
//			float x = (float)MathHelper.lerp((double)tickDelta, entity.prevX, entity.getX());
//			float y = (float)MathHelper.lerp((double)tickDelta, entity.prevY, entity.getY());
//			float z = (float)MathHelper.lerp((double)tickDelta, entity.prevZ, entity.getZ());
//			builder.setColor(color).setOffset(-x, -y, -z ).setAlpha(alpha).renderBeam(RenderHandler.DELAYED_RENDER.getBuffer(LAYER), matrices, entity.getPos().add(0, -1, 0), entity.getPos().add(0.0, 10, 0.0), size);
//		}

		matrices.pop();
		super.render(entity, yaw, tickDelta, matrices, vertexConsumers, 15728880);
		/*float x = (float) MathHelper.lerp(tickDelta, entity.lastRenderX, entity.getX());
		float y = (float) MathHelper.lerp(tickDelta, entity.lastRenderY, entity.getY());
		float z = (float) MathHelper.lerp(tickDelta, entity.lastRenderZ, entity.getZ());
		float j = MathHelper.lerp(tickDelta, entity.prevYaw, entity.getYaw()) - 90.0f;
		float k = MathHelper.lerp(tickDelta, entity.prevYaw, entity.getYaw()) + 90.0f;
		renderParticleBeam(1, entity, tickDelta);
		for (int i = 1; i <= entity.getLenght(); i++) {
			//renderParticleBeam(i, entity, tickDelta);
			//renderBeam(matrices, vertexConsumers, BEAM_TEXTURE, tickDelta, 1.0f, entity.getWorld().getTime(), 0, i, new float[]{1, 1, 1}, 0.2F, 0.25F);
		}*/

	}
	public void renderParticleBeam(int offset, T entity, float tickDelta){
		/*//Vec3d multiplier = rot.multiply(offset);
		//Vec3d newPos = pos.add(multiplier);
		//float x = (float) MathHelper.lerp(tickDelta, entity.lastRenderX, entity.getX());
		//float y = (float) MathHelper.lerp(tickDelta, entity.lastRenderY, entity.getY()) + offset;
		//float z = (float) MathHelper.lerp(tickDelta, entity.lastRenderZ, entity.getZ());
		double x = entity.getX();
		double z = entity.getZ();
		double y = entity.getY();
		//float j = MathHelper.lerp(tickDelta, entity.prevYaw, entity.getYaw()) - 90.0f;
		//float k = MathHelper.lerp(tickDelta, entity.prevYaw, entity.getYaw()) + 90.0f;
		for (int i = 0; i < 2; i++) {
			WorldParticleBuilder.create(StarRClient.LASER)
					.setSpinData(SpinParticleData.create((float) (entity.getWorld().random.nextGaussian() / 5f)).build())
					.setScaleData(GenericParticleData.create(1f, 0f).setEasing(Easing.CIRC_OUT).build())
					.setTransparencyData(GenericParticleData.create(1f).build())
					.setColorData(ColorParticleData.create(new Color(0xFF3C00), new Color(0xFFCB00)).setEasing(Easing.CIRC_OUT).build())
					.enableNoClip()
					.setLifetime(20)
					.spawn(entity.getWorld(), x + entity.getWorld().random.nextGaussian() / 20f, y + (entity.getHeight() / 2f) + entity.getWorld().random.nextGaussian() / 20f, z + entity.getWorld().random.nextGaussian() / 20f);
		}
*/
	}
	public static void renderBeam(MatrixStack matrices, VertexConsumerProvider vertexConsumers, Identifier textureId, float tickDelta, float heightScale, long worldTime, int yOffset, int maxY, float[] color, float innerRadius, float outerRadius) {
		int i = yOffset + maxY;
		matrices.push();
		matrices.translate(0.0, 0.0, -0.5);
		float f = (float)Math.floorMod(worldTime, 40) + tickDelta;
		float g = maxY < 0 ? f : -f;
		float h = MathHelper.fractionalPart(g * 0.2F - (float)MathHelper.floor(g * 0.1F));
		float j = color[0];
		float k = color[1];
		float l = color[2];
		matrices.push();
		matrices.multiply(Axis.Y_POSITIVE.rotationDegrees(f * 2.25F - 45.0F));
		float m = 0.0F;
		float p = 0.0F;
		float q = -innerRadius;
		float r = 0.0F;
		float s = 0.0F;
		float t = -innerRadius;
		float u = 0.0F;
		float v = 1.0F;
		float w = -1.0F + h;
		float x = (float)maxY * heightScale * (0.5F / innerRadius) + w;
		renderBeamLayer(matrices, vertexConsumers.getBuffer(RenderLayer.getBeaconBeam(textureId, false)), j, k, l, 1.0F, yOffset, i, 0.0F, innerRadius, innerRadius, 0.0F, q, 0.0F, 0.0F, t, 0.0F, 1.0F, x, w);
		matrices.pop();
		/*m = -outerRadius;
		float n = -outerRadius;
		p = -outerRadius;
		q = -outerRadius;
		u = 0.0F;
		v = 1.0F;
		w = -1.0F + h;
		x = (float)maxY * heightScale + w;

		renderBeamLayer(matrices, vertexConsumers.getBuffer(RenderLayer.getBeaconBeam(textureId, true)), j, k, l, 0.125F, yOffset, i, m, n, outerRadius, p, q, outerRadius, outerRadius, outerRadius, 0.0F, 1.0F, x, w);
		 */
		matrices.pop();
	}

	private static void renderBeamLayer(MatrixStack matrices, VertexConsumer vertices, float red, float green, float blue, float alpha, int yOffset, int height, float x1, float z1, float x2, float z2, float x3, float z3, float x4, float z4, float u1, float u2, float v1, float v2) {
		MatrixStack.Entry entry = matrices.peek();
		Matrix4f matrix4f = entry.getModel();
		Matrix3f matrix3f = entry.getNormal();
		renderBeamFace(matrix4f, matrix3f, vertices, red, green, blue, alpha, yOffset, height, x1, z1, x2, z2, u1, u2, v1, v2);
		renderBeamFace(matrix4f, matrix3f, vertices, red, green, blue, alpha, yOffset, height, x4, z4, x3, z3, u1, u2, v1, v2);
		renderBeamFace(matrix4f, matrix3f, vertices, red, green, blue, alpha, yOffset, height, x2, z2, x4, z4, u1, u2, v1, v2);
		renderBeamFace(matrix4f, matrix3f, vertices, red, green, blue, alpha, yOffset, height, x3, z3, x1, z1, u1, u2, v1, v2);
	}

	private static void renderBeamFace(Matrix4f matrix, Matrix3f normalMatrix, VertexConsumer vertices, float red, float green, float blue, float alpha, int yOffset, int height, float x1, float z1, float x2, float z2, float u1, float u2, float v1, float v2) {
		renderBeamVertex(matrix, normalMatrix, vertices, red, green, blue, alpha, height, x1, z1, u2, v1);
		renderBeamVertex(matrix, normalMatrix, vertices, red, green, blue, alpha, yOffset, x1, z1, u2, v2);
		renderBeamVertex(matrix, normalMatrix, vertices, red, green, blue, alpha, yOffset, x2, z2, u1, v2);
		renderBeamVertex(matrix, normalMatrix, vertices, red, green, blue, alpha, height, x2, z2, u1, v1);
	}

	private static void renderBeamVertex(Matrix4f matrix, Matrix3f normalMatrix, VertexConsumer vertices, float red, float green, float blue, float alpha, int y, float x, float z, float u, float v) {
		vertices.vertex(matrix, x, (float)y, z).color(red, green, blue, alpha).uv(u, v).overlay(OverlayTexture.DEFAULT_UV).light(15728880).normal(normalMatrix, 0.0F, 1.0F, 0.0F).next();
	}
	@Override
	public Identifier getTexture(LaserEntity entity) {
		return null;
	}
	//static {
	//	LAYER = LodestoneRenderLayers.ADDITIVE_TEXTURE.apply(LASER_TEXTURE);
	//}
}
