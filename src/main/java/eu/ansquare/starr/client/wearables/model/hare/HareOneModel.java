package eu.ansquare.starr.client.wearables.model.hare;

import com.mojang.blaze3d.vertex.VertexConsumer;
import eu.ansquare.starr.StarR;
import net.minecraft.client.model.*;
import net.minecraft.client.render.RenderLayer;
import net.minecraft.client.render.entity.model.EntityModelLayer;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.util.Identifier;

public class HareOneModel extends Model {
	public static final EntityModelLayer LAYER_LOCATION = new EntityModelLayer(new Identifier(StarR.MODID, "hare1"), "main");
	private final ModelPart hare1;

	public HareOneModel(ModelPart root) {
		super(RenderLayer::getEntityTranslucent);

		this.hare1 = root.getChild("hare1");
	}

	public static TexturedModelData getTexturedModelData() {
		ModelData meshdefinition = new ModelData ();
		ModelPartData partdefinition = meshdefinition.getRoot();

		ModelPartData bone = partdefinition.addChild("hare1", ModelPartBuilder.create().uv(0, 14).cuboid(-4.0F, -35.0F, -3.0F, 8.0F, 3.0F, 7.0F, new Dilation(0.0F))
				.uv(0, 0).cuboid(-5.0F, -32.0F, -8.0F, 10.0F, 1.0F, 13.0F, new Dilation(0.0F)), ModelTransform.pivot(0.0F, 24.0F, 0.0F));

		return TexturedModelData.of(meshdefinition, 64, 64);
	}



	@Override
	public void render(MatrixStack matrices, VertexConsumer vertexConsumer, int packedLight, int packedOverlay, float red, float green, float blue, float alpha) {
		matrices.push();
		hare1.render(matrices, vertexConsumer, packedLight, packedOverlay, red, green, blue, alpha);
		matrices.pop();
	}
	public void changeAngles(ModelPart thing, float x, float y, float z){
		thing.yaw = y;
		thing.pitch = x;
		thing.roll = z;
	}
}
