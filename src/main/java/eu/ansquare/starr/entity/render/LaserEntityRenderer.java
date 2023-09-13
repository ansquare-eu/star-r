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
import org.joml.Quaternionf;

public class LaserEntityRenderer<T extends LaserEntity> extends EntityRenderer<T> {
	public static final Identifier BEAM_TEXTURE = new Identifier(StarR.MODID, "textures/laser/laser.png");

	public LaserEntityRenderer(EntityRendererFactory.Context ctx) {
		super(ctx);
	}
	public void render(T entity, float yaw, float tickDelta, MatrixStack matrices, VertexConsumerProvider vertexConsumers, int light){
		matrices.multiply(Axis.Y_POSITIVE.rotationDegrees(MathHelper.lerp(tickDelta, entity.prevYaw, entity.getYaw()) - 90.0F));
		matrices.multiply(Axis.Z_POSITIVE.rotationDegrees(MathHelper.lerp(tickDelta, entity.prevPitch, entity.getPitch()) + 90.0F));
		for (int z = 1; z <= entity.getLenght(); z++) {
			renderBeam(matrices, vertexConsumers, BEAM_TEXTURE, tickDelta, 1.0f, entity.getWorld().getTime(), 0, z, new float[]{1, 0, 1}, 0.2F, 0.25F);
		}

	}
	public static void renderBeam(MatrixStack matrices, VertexConsumerProvider vertexConsumers, Identifier textureId, float tickDelta, float heightScale, long worldTime, int yOffset, int maxY, float[] color, float innerRadius, float outerRadius) {
		int i = yOffset + maxY;
		matrices.push();
		matrices.translate(0.5, 0.0, 0.5);
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
}
