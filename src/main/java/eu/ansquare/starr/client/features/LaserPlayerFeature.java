package eu.ansquare.starr.client.features;

import eu.ansquare.starr.client.StarRClient;
import eu.ansquare.starr.util.network.ClientLaser;
import eu.ansquare.starr.util.network.ClientLaserHolder;
import net.minecraft.client.network.AbstractClientPlayerEntity;
import net.minecraft.client.render.VertexConsumerProvider;
import net.minecraft.client.render.entity.feature.FeatureRenderer;
import net.minecraft.client.render.entity.feature.FeatureRendererContext;
import net.minecraft.client.render.entity.model.PlayerEntityModel;
import net.minecraft.client.util.math.MatrixStack;

import java.awt.*;

public class LaserPlayerFeature extends FeatureRenderer<AbstractClientPlayerEntity, PlayerEntityModel<AbstractClientPlayerEntity>> {
	public LaserPlayerFeature(FeatureRendererContext<AbstractClientPlayerEntity, PlayerEntityModel<AbstractClientPlayerEntity>> context) {
		super(context);
	}

	@Override
	public void render(MatrixStack matrices, VertexConsumerProvider vertexConsumers, int light, AbstractClientPlayerEntity entity, float limbAngle, float limbDistance, float tickDelta, float animationProgress, float headYaw, float headPitch) {
		if(StarRClient.LASER_HOLDER.MAP.containsKey(entity.getUuid())){
			ClientLaser laser = StarRClient.LASER_HOLDER.MAP.get(entity.getUuid());
			Color color = laser.COLOR;
		}
	}
}
