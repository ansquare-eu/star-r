package eu.ansquare.starr.client.wearables.model;

import com.mojang.blaze3d.vertex.VertexConsumer;
import eu.ansquare.starr.StarR;
import net.minecraft.client.model.*;
import net.minecraft.client.render.RenderLayer;
import net.minecraft.client.render.entity.model.EntityModelLayer;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.util.Identifier;

public class FaceWearableModel extends Model {
	public static final EntityModelLayer LAYER_LOCATION = new EntityModelLayer(new Identifier(StarR.MODID, "thing"), "main");
	private final ModelPart thing;

	public FaceWearableModel(ModelPart root) {
		super(RenderLayer::getEntityTranslucent);

		this.thing = root.getChild("thing");
	}
	public static TexturedModelData getTexturedModelData() {
		ModelData meshdefinition = new ModelData ();
		ModelPartData partdefinition = meshdefinition.getRoot();
		ModelPartData thing = partdefinition.addChild("thing", ModelPartBuilder.create().uv(0, 0).cuboid(-4.0F, -32.0F, -4.5F, 8.0F, 8.0F, 0.0F, new Dilation(0.0F)), ModelTransform.pivot(0.0F, 24.0F, 0.0F));

		return TexturedModelData.of(meshdefinition, 8, 8);
	}
	@Override
	public void render(MatrixStack matrices, VertexConsumer vertexConsumer, int packedLight, int packedOverlay, float red, float green, float blue, float alpha) {
		matrices.push();
		thing.render(matrices, vertexConsumer, packedLight, packedOverlay, red, green, blue, alpha);
		matrices.pop();
	}
}
