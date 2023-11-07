package eu.ansquare.starr.entity.model;

import com.mojang.blaze3d.vertex.VertexConsumer;
import eu.ansquare.starr.StarR;
import eu.ansquare.starr.entity.BoyshieldEntity;
import eu.ansquare.starr.entity.PalicaEntity;
import net.minecraft.client.model.*;
import net.minecraft.client.render.RenderLayer;
import net.minecraft.client.render.entity.model.EntityModel;
import net.minecraft.client.render.entity.model.EntityModelLayer;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.util.Identifier;

public class BoyshieldEntityModel extends EntityModel<BoyshieldEntity> {
	public static final EntityModelLayer LAYER_LOCATION = new EntityModelLayer(new Identifier(StarR.MODID, "boyshield"), "main");
	private final ModelPart bone;

	public BoyshieldEntityModel(ModelPart root) {
		super(RenderLayer::getEntityTranslucent);
		this.bone = root.getChild("bone");
	}

	public static TexturedModelData getTexturedModelData() {
		ModelData meshdefinition = new ModelData();
		ModelPartData partdefinition = meshdefinition.getRoot();

		ModelPartData bone = partdefinition.addChild("bone", ModelPartBuilder.create().uv(0, 11).cuboid(-11.0F, -13.0F, 8.0F, 6.0F, 11.0F, 1.0F, new Dilation(0.0F))
				.uv(2, 12).cuboid(-12.0F, -10.0F, 8.0F, 1.0F, 4.0F, 1.0F, new Dilation(0.0F))
				.uv(5, 1).cuboid(-5.0F, -10.0F, 8.0F, 1.0F, 4.0F, 1.0F, new Dilation(0.0F))
				.uv(13, 9).cuboid(-10.0F, -2.0F, 8.0F, 4.0F, 1.0F, 1.0F, new Dilation(0.0F)), ModelTransform.pivot(8.0F, 24.0F, -8.0F));

		return TexturedModelData.of(meshdefinition, 32, 32);
	}
	@Override
	public void setAngles(BoyshieldEntity entity, float limbAngle, float limbDistance, float animationProgress, float headYaw, float headPitch) {

	}

	@Override
	public void render(MatrixStack matrices, VertexConsumer vertices, int light, int overlay, float red, float green, float blue, float alpha) {
		bone.render(matrices, vertices, light, overlay, red, green, blue, alpha);
	}
}

