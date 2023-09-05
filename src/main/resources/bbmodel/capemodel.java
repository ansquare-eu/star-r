// Made with Blockbench 4.8.3
// Exported for Minecraft version 1.17 or later with Mojang mappings
// Paste this class into your mod and generate all required imports


public class capemodel<T extends Entity> extends EntityModel<T> {
	// This layer location should be baked with EntityRendererProvider.Context in the entity renderer and passed into this model's constructor
	public static final ModelLayerLocation LAYER_LOCATION = new ModelLayerLocation(new ResourceLocation("modid", "capemodel"), "main");
	private final ModelPart bone;

	public capemodel(ModelPart root) {
		this.bone = root.getChild("bone");
	}

	public static LayerDefinition createBodyLayer() {
		MeshDefinition meshdefinition = new MeshDefinition();
		PartDefinition partdefinition = meshdefinition.getRoot();

		PartDefinition bone = partdefinition.addOrReplaceChild("bone", CubeListBuilder.create().texOffs(0, 28).addBox(-1.0F, -26.0F, -5.0F, 2.0F, 26.0F, 2.0F, new CubeDeformation(0.0F))
		.texOffs(0, 0).addBox(-3.0F, -26.0F, -3.0F, 2.0F, 26.0F, 2.0F, new CubeDeformation(0.0F))
		.texOffs(16, 0).addBox(-3.0F, -26.0F, 1.0F, 2.0F, 26.0F, 2.0F, new CubeDeformation(0.0F))
		.texOffs(8, 0).addBox(-1.0F, -26.0F, 3.0F, 2.0F, 26.0F, 2.0F, new CubeDeformation(0.0F))
		.texOffs(24, 0).addBox(-1.0F, -26.0F, -1.0F, 2.0F, 26.0F, 2.0F, new CubeDeformation(0.0F))
		.texOffs(30, 26).addBox(-1.0F, -26.0F, -3.0F, 2.0F, 2.0F, 2.0F, new CubeDeformation(0.0F))
		.texOffs(24, 28).addBox(-1.0F, -26.0F, 1.0F, 2.0F, 2.0F, 2.0F, new CubeDeformation(0.0F))
		.texOffs(16, 28).addBox(1.0F, -26.0F, 2.0F, 2.0F, 2.0F, 2.0F, new CubeDeformation(0.0F))
		.texOffs(31, 31).addBox(3.0F, -26.0F, 2.0F, 2.0F, 2.0F, 1.0F, new CubeDeformation(0.0F))
		.texOffs(8, 28).addBox(1.0F, -26.0F, -4.0F, 2.0F, 2.0F, 2.0F, new CubeDeformation(0.0F))
		.texOffs(32, 0).addBox(3.0F, -26.0F, -3.0F, 2.0F, 2.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 24.0F, 3.0F, 0.0F, 1.5708F, 0.0F));

		return LayerDefinition.create(meshdefinition, 64, 64);
	}

	@Override
	public void setupAnim(T entity, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {

	}

	@Override
	public void renderToBuffer(PoseStack poseStack, VertexConsumer vertexConsumer, int packedLight, int packedOverlay, float red, float green, float blue, float alpha) {
		bone.render(poseStack, vertexConsumer, packedLight, packedOverlay, red, green, blue, alpha);
	}
}