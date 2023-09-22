package eu.ansquare.starr.client.features;

import com.sammy.lodestone.handlers.RenderHandler;
import com.sammy.lodestone.setup.LodestoneRenderLayers;
import com.sammy.lodestone.systems.rendering.VFXBuilders;
import eu.ansquare.starr.StarR;
import eu.ansquare.starr.client.StarRClient;
import eu.ansquare.starr.util.network.ClientLaser;
import net.minecraft.client.model.ModelPart;
import net.minecraft.client.network.AbstractClientPlayerEntity;
import net.minecraft.client.render.RenderLayer;
import net.minecraft.client.render.VertexConsumerProvider;
import net.minecraft.client.render.entity.feature.FeatureRenderer;
import net.minecraft.client.render.entity.feature.FeatureRendererContext;
import net.minecraft.client.render.entity.model.PlayerEntityModel;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.util.Identifier;
import net.minecraft.util.math.Axis;
import net.minecraft.util.math.MathHelper;
import org.joml.Quaternionf;

import java.awt.*;

public class LaserFeatureRenderer extends FeatureRenderer<AbstractClientPlayerEntity, PlayerEntityModel<AbstractClientPlayerEntity>> {
	public static final Identifier LASER_TEXTURE = new Identifier(StarR.MODID, "textures/laser/laser.png");
	private static final RenderLayer LAYER;
	public LaserFeatureRenderer(FeatureRendererContext<AbstractClientPlayerEntity, PlayerEntityModel<AbstractClientPlayerEntity>> context) {
		super(context);
	}

	@Override
	public void render(MatrixStack matrices, VertexConsumerProvider vertexConsumers, int light, AbstractClientPlayerEntity entity, float limbAngle, float limbDistance, float tickDelta, float animationProgress, float headYaw, float headPitch) {
		if(StarRClient.LASER_HOLDER.MAP.containsKey(entity.getUuid())){
			ClientLaser laser = StarRClient.LASER_HOLDER.MAP.get(entity.getUuid());
			Color color = laser.COLOR;
			matrices.push();
			VFXBuilders.WorldVFXBuilder builder = VFXBuilders.createWorld().setPosColorTexLightmapDefaultFormat();
			float lerp = 0.0F;
			//if (entity.age <= 60) {
				//lerp = Easing.EXPO_IN.ease(MathHelper.lerp(MathHelper.clamp((float)entity.age / 60.0f, 0.0F, 1.0F), 0.0F, 1.0F), 0.0F, 1.0F, 1.0F);
			//} else {
				lerp = 1f;//Easing.SINE_OUT.ease(MathHelper.lerp(MathHelper.clamp(((float)entity.age - 60.0F) / 60.0F, 0.0F, 1.0F), 1.0F, 0.0F), 0.0F, 1.0F, 1.0F);
			//}
			float minSize = 1.0f * lerp;
			float maxSize = 2.0f * lerp;
			float inc = 0.5F;
			for(float size = minSize; size <= maxSize; size += inc) {
				float alpha = MathHelper.lerp((size - minSize) / (maxSize - minSize), 0.2F, 0.8F);

				float x = (float)MathHelper.lerp((double)tickDelta, entity.prevX, entity.getX());
				float y = (float)MathHelper.lerp((double)tickDelta, entity.prevY, entity.getY());
				float z = (float)MathHelper.lerp((double)tickDelta, entity.prevZ, entity.getZ());
				builder.setColor(color).setOffset(-x, -y, -z ).setAlpha(alpha).renderBeam(RenderHandler.DELAYED_RENDER.getBuffer(LAYER), matrices, entity.getPos().add(0, -1, 0), entity.getPos().add(entity.getRotationVec(tickDelta).multiply(10)), size);
			}

			matrices.pop();
		}
	}
	static {
		LAYER = LodestoneRenderLayers.ADDITIVE_TEXTURE.apply(LASER_TEXTURE);
	}
}
