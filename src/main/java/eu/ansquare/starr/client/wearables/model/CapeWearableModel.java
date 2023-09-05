package eu.ansquare.starr.client.wearables.model;

import com.mojang.blaze3d.vertex.VertexConsumer;
import eu.ansquare.starr.StarR;
import net.minecraft.client.model.*;
import net.minecraft.client.render.RenderLayer;
import net.minecraft.client.render.entity.model.EntityModelLayer;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.util.Identifier;

public class CapeWearableModel extends Model {
	public static final EntityModelLayer LAYER_LOCATION = new EntityModelLayer(new Identifier(StarR.MODID, "capewearable"), "main");
	private final ModelPart capewearable;

	public CapeWearableModel(ModelPart root) {
		super(RenderLayer::getEntityTranslucent);

		this.capewearable = root.getChild("capewearable");
	}

	public static TexturedModelData getTexturedModelData() {
		ModelData meshdefinition = new ModelData ();
		ModelPartData partdefinition = meshdefinition.getRoot();

		ModelPartData bone = partdefinition.addChild("capewearable", ModelPartBuilder.create().uv(0, 28).cuboid(-1.0F, -26.0F, -5.0F, 2.0F, 26.0F, 2.0F, new Dilation(0.0F))
				.uv(0, 0).cuboid(-3.0F, -26.0F, -3.0F, 2.0F, 26.0F, 2.0F, new Dilation(0.0F))
				.uv(16, 0).cuboid(-3.0F, -26.0F, 1.0F, 2.0F, 26.0F, 2.0F, new Dilation(0.0F))
				.uv(8, 0).cuboid(-1.0F, -26.0F, 3.0F, 2.0F, 26.0F, 2.0F, new Dilation(0.0F))
				.uv(24, 0).cuboid(-1.0F, -26.0F, -1.0F, 2.0F, 26.0F, 2.0F, new Dilation(0.0F))
				.uv(30, 26).cuboid(-1.0F, -26.0F, -3.0F, 2.0F, 2.0F, 2.0F, new Dilation(0.0F))
				.uv(24, 28).cuboid(-1.0F, -26.0F, 1.0F, 2.0F, 2.0F, 2.0F, new Dilation(0.0F))
				.uv(16, 28).cuboid(1.0F, -26.0F, 2.0F, 2.0F, 2.0F, 2.0F, new Dilation(0.0F))
				.uv(31, 31).cuboid(3.0F, -26.0F, 2.0F, 2.0F, 2.0F, 1.0F, new Dilation(0.0F))
				.uv(8, 28).cuboid(1.0F, -26.0F, -4.0F, 2.0F, 2.0F, 2.0F, new Dilation(0.0F))
				.uv(32, 0).cuboid(3.0F, -26.0F, -3.0F, 2.0F, 2.0F, 1.0F, new Dilation(0.0F)), ModelTransform.of(0.0F, 24.0F, 3.0F, 0.0F, 1.5708F, 0.0F));

		return TexturedModelData.of(meshdefinition, 64, 64);
	}



	@Override
	public void render(MatrixStack matrices, VertexConsumer vertexConsumer, int packedLight, int packedOverlay, float red, float green, float blue, float alpha) {
		matrices.push();
		capewearable.render(matrices, vertexConsumer, packedLight, packedOverlay, red, green, blue, alpha);
		matrices.pop();
	}
	public void changeAngles(ModelPart thing, float x, float y, float z){
		thing.yaw = y;
		thing.pitch = x;
		thing.roll = z;
	}
}
