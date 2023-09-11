package eu.ansquare.starr.entity.model;

import com.mojang.blaze3d.vertex.VertexConsumer;
import eu.ansquare.starr.StarR;
import eu.ansquare.starr.entity.PalicaEntity;
import net.minecraft.client.model.*;
import net.minecraft.client.render.RenderLayer;
import net.minecraft.client.render.entity.model.EntityModel;
import net.minecraft.client.render.entity.model.EntityModelLayer;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.util.Identifier;

public class PalicaEntityModel extends EntityModel<PalicaEntity> {
	public static final EntityModelLayer LAYER_LOCATION = new EntityModelLayer(new Identifier(StarR.MODID, "palica"), "main");
	private final ModelPart bone;

	public PalicaEntityModel(ModelPart root) {
		super(RenderLayer::getEntityTranslucent);
		this.bone = root.getChild("bone");
	}

	public static TexturedModelData getTexturedModelData() {
		ModelData meshdefinition = new ModelData();
		ModelPartData partdefinition = meshdefinition.getRoot();

		ModelPartData bone = partdefinition.addChild("bone", ModelPartBuilder.create().uv(0, 0).cuboid(-9.0F, -20.0F, 8.0F, 2.0F, 20.0F, 2.0F, new Dilation(0.0F))
				.uv(12, 8).cuboid(-9.0F, -22.0F, 8.0F, 2.0F, 1.0F, 2.0F, new Dilation(0.0F))
				.uv(16, 0).cuboid(-10.0F, -24.0F, 7.0F, 4.0F, 3.0F, 4.0F, new Dilation(0.0F))
				.uv(0, 18).cuboid(-10.0F, -21.0F, 7.0F, 4.0F, 1.0F, 4.0F, new Dilation(0.0F)), ModelTransform.pivot(8.0F, 24.0F, -9.0F));

		return TexturedModelData.of(meshdefinition, 32, 32);
	}
	@Override
	public void setAngles(PalicaEntity entity, float limbAngle, float limbDistance, float animationProgress, float headYaw, float headPitch) {

	}

	@Override
	public void render(MatrixStack matrices, VertexConsumer vertices, int light, int overlay, float red, float green, float blue, float alpha) {
		bone.render(matrices, vertices, light, overlay, red, green, blue, alpha);
	}
}
