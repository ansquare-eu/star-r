package eu.ansquare.starr.client.wearables.model;

import com.mojang.blaze3d.vertex.VertexConsumer;
import eu.ansquare.starr.StarR;
import net.minecraft.client.model.*;
import net.minecraft.client.render.RenderLayer;
import net.minecraft.client.render.entity.model.EntityModelLayer;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.util.Identifier;

public class SkiModel extends Model {
	public static final EntityModelLayer LAYER_LOCATION = new EntityModelLayer(StarR.id("ski"), "main");
	private final ModelPart ski;

	public SkiModel(ModelPart root) {
		super(RenderLayer::getEntityTranslucent);

		this.ski = root.getChild("ski");
	}

	public static TexturedModelData getTexturedModelData() {
		ModelData meshdefinition = new ModelData();
		ModelPartData partdefinition = meshdefinition.getRoot();
		ModelPartData bone = partdefinition.addChild("ski", ModelPartBuilder.create().uv(0, 0).cuboid(-1.0F, -1.0F, -16.0F, 2.0F, 1.0F, 2.0F, new Dilation(0.0F))
				.uv(0, 0).cuboid(-2.0F, -1.0F, -14.0F, 4.0F, 1.0F, 24.0F, new Dilation(0.0F)), ModelTransform.pivot(0.0F, 24.0F, 0.0F));
		return TexturedModelData.of(meshdefinition, 64, 64);
	}



	@Override
	public void render(MatrixStack matrices, VertexConsumer vertexConsumer, int packedLight, int packedOverlay, float red, float green, float blue, float alpha) {
		matrices.push();
		ski.render(matrices, vertexConsumer, packedLight, packedOverlay, red, green, blue, alpha);
		matrices.pop();
	}
	public void changeAngles(ModelPart thing, float x, float y, float z){
		thing.yaw = y;
		thing.pitch = x;
		thing.roll = z;
	}
}
