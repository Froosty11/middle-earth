// Made with Blockbench 4.8.1
// Exported for Minecraft version 1.17+ for Yarn
// Paste this class into your mod and generate all required imports

package net.jesteur.me.entity.monsters.barrow_wight;
import net.minecraft.client.model.Dilation;
import net.minecraft.client.model.ModelData;
import net.minecraft.client.model.ModelPart;
import net.minecraft.client.model.ModelPartBuilder;
import net.minecraft.client.model.ModelPartData;
import net.minecraft.client.model.ModelTransform;
import net.minecraft.client.model.TexturedModelData;
import net.minecraft.client.render.VertexConsumer;
import net.minecraft.client.render.entity.model.EntityModel;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.entity.Entity;

public class BarrowWightModel extends EntityModel<Entity> {
	private final ModelPart cloak;

	private final ModelPart head;
	private final ModelPart right_arm2;
	private final ModelPart left_arm2;
	private final ModelPart left_leg2;
	private final ModelPart right_leg2;
	private final ModelPart bottom_jaw;
	public BarrowWightModel(ModelPart root) {
		this.cloak = root.getChild("cloak");
		this.head = root.getChild("head");
		this.right_arm2 = root.getChild("right_arm2");
		this.left_arm2 = root.getChild("left_arm2");
		this.left_leg2 = root.getChild("left_leg2");
		this.right_leg2 = root.getChild("right_leg2");
		this.bottom_jaw = root.getChild("bottom_jaw");
	}
	public static TexturedModelData getTexturedModelData() {
		ModelData modelData = new ModelData();
		ModelPartData modelPartData = modelData.getRoot();
		ModelPartData cloak = modelPartData.addChild("cloak", ModelPartBuilder.create().uv(0, 18).cuboid(-10.0F, -21.5702F, -7.9125F, 20.0F, 3.0F, 7.0F, new Dilation(0.0F))
		.uv(36, 28).cuboid(-5.0F, -10.0F, -1.9125F, 10.0F, 8.0F, 8.0F, new Dilation(0.0F))
		.uv(47, 8).cuboid(-3.0F, -19.5702F, -10.9125F, 6.0F, 4.0F, 10.0F, new Dilation(0.0F)), ModelTransform.of(0.0F, 12.0F, 3.0F, -0.1745F, 0.0F, 0.0F));

		ModelPartData cube_r1 = cloak.addChild("cube_r1", ModelPartBuilder.create().uv(0, 0).cuboid(-10.01F, -20.0F, -4.5F, 20.1F, 11.0F, 7.0F, new Dilation(0.0F)), ModelTransform.of(0.0F, 0.0F, 0.0F, 0.1745F, 0.0F, 0.0F));

		ModelPartData cube_r2 = cloak.addChild("cube_r2", ModelPartBuilder.create().uv(26, 20).cuboid(0.75F, 2.6614F, -4.0419F, 0.0F, 5.0F, 8.0F, new Dilation(0.0F)), ModelTransform.of(-5.0F, -8.6614F, 2.1294F, -0.0066F, 0.0065F, 0.3008F));

		ModelPartData cube_r3 = cloak.addChild("cube_r3", ModelPartBuilder.create().uv(56, 39).cuboid(-1.0F, -7.3386F, -1.2919F, 0.0F, 6.0F, 5.0F, new Dilation(0.0F)), ModelTransform.of(-5.0F, -8.6614F, 2.1294F, 0.4326F, -0.1125F, 0.2461F));

		ModelPartData cube_r4 = cloak.addChild("cube_r4", ModelPartBuilder.create().uv(30, 44).cuboid(-4.991F, -14.3386F, 2.7081F, 9.99F, 10.0F, 6.0F, new Dilation(0.0F)), ModelTransform.of(0.0F, 12.3386F, -0.8706F, 0.1745F, 0.0F, 0.0F));

		ModelPartData cube_r5 = cloak.addChild("cube_r5", ModelPartBuilder.create().uv(64, 31).cuboid(-4.0F, -1.1345F, -0.4734F, 8.0F, 5.0F, 0.0F, new Dilation(0.0F)), ModelTransform.of(0.0F, -14.6614F, 4.1294F, 0.6109F, 0.0F, 0.0F));

		ModelPartData cube_r6 = cloak.addChild("cube_r6", ModelPartBuilder.create().uv(0, 48).cuboid(0.0F, 3.6614F, -3.0419F, 10.0F, 4.0F, 0.0F, new Dilation(0.0F)), ModelTransform.of(-5.0F, -8.6614F, 2.1294F, -0.3054F, 0.0F, 0.0F));

		ModelPartData cube_r7 = cloak.addChild("cube_r7", ModelPartBuilder.create().uv(64, 27).cuboid(0.0F, 3.9114F, 2.9581F, 10.0F, 4.0F, 0.0F, new Dilation(0.0F)), ModelTransform.of(-5.0F, -8.6614F, 2.1294F, 0.3491F, 0.0F, 0.0F));

		ModelPartData cube_r8 = cloak.addChild("cube_r8", ModelPartBuilder.create().uv(0, 23).cuboid(1.0F, -7.3386F, -1.2919F, 0.0F, 6.0F, 5.0F, new Dilation(0.0F)), ModelTransform.of(5.0F, -8.6614F, 2.1294F, 0.4326F, 0.1125F, -0.2461F));

		ModelPartData cube_r9 = cloak.addChild("cube_r9", ModelPartBuilder.create().uv(54, 14).cuboid(-0.75F, 2.6614F, -4.0419F, 0.0F, 5.0F, 8.0F, new Dilation(0.0F)), ModelTransform.of(5.0F, -8.6614F, 2.1294F, -0.0066F, -0.0065F, -0.3008F));

		ModelPartData cube_r10 = cloak.addChild("cube_r10", ModelPartBuilder.create().uv(69, 0).cuboid(-4.0F, 0.2274F, -0.0767F, 8.0F, 4.0F, 0.0F, new Dilation(0.0F)), ModelTransform.of(0.0F, -21.0F, 1.0F, 0.7418F, 0.0F, 0.0F));

		ModelPartData cube_r11 = cloak.addChild("cube_r11", ModelPartBuilder.create().uv(57, 55).cuboid(-4.0F, -6.8641F, -4.8937F, 8.0F, 12.0F, 5.0F, new Dilation(0.0F)), ModelTransform.of(0.0F, -14.6614F, 3.1294F, 0.3491F, 0.0F, 0.0F));

		ModelPartData head = modelPartData.addChild("head", ModelPartBuilder.create().uv(0, 52).cuboid(-4.0F, -3.7443F, -9.2617F, 8.0F, 6.0F, 8.0F, new Dilation(0.1F))
		.uv(69, 7).cuboid(-3.5F, -3.9943F, -8.7617F, 7.0F, 6.0F, 0.0F, new Dilation(0.0F))
		.uv(47, 0).cuboid(-4.0F, 2.5F, -9.25F, 8.0F, 1.0F, 6.0F, new Dilation(0.1F))
		.uv(0, 28).cuboid(-4.0F, -3.8386F, -9.6294F, 8.0F, 10.0F, 10.0F, new Dilation(0.2F)), ModelTransform.pivot(0.0F, -6.6614F, -1.8706F));

		ModelPartData right_arm2 = modelPartData.addChild("right_arm2", ModelPartBuilder.create(), ModelTransform.pivot(-8.0F, -7.0F, 2.0F));

		ModelPartData cube_r12 = right_arm2.addChild("cube_r12", ModelPartBuilder.create().uv(44, 61).cuboid(0.5F, -9.0567F, -4.5853F, 3.0F, 26.0F, 3.0F, new Dilation(0.0F)), ModelTransform.of(-2.0F, 8.0F, 2.0F, -0.2182F, 0.0436F, 0.0F));

		ModelPartData left_arm2 = modelPartData.addChild("left_arm2", ModelPartBuilder.create(), ModelTransform.pivot(8.0F, -7.0F, 2.0F));

		ModelPartData cube_r13 = left_arm2.addChild("cube_r13", ModelPartBuilder.create().uv(32, 60).cuboid(16.5F, -9.0567F, -4.5853F, 3.0F, 26.0F, 3.0F, new Dilation(0.0F)), ModelTransform.of(-18.0F, 8.0F, 2.0F, -0.2182F, 0.0436F, 0.0F));

		ModelPartData left_leg2 = modelPartData.addChild("left_leg2", ModelPartBuilder.create().uv(0, 66).cuboid(-2.2296F, -1.0567F, -1.4252F, 3.0F, 14.0F, 3.0F, new Dilation(0.0F)), ModelTransform.pivot(4.0F, 11.0F, 6.0F));

		ModelPartData right_leg2 = modelPartData.addChild("right_leg2", ModelPartBuilder.create().uv(12, 66).cuboid(-1.7296F, -2.0567F, -1.4252F, 3.0F, 14.0F, 3.0F, new Dilation(0.0F)), ModelTransform.pivot(-3.0F, 12.0F, 6.0F));

		ModelPartData bottom_jaw = modelPartData.addChild("bottom_jaw", ModelPartBuilder.create(), ModelTransform.of(0.0F, -3.6614F, -2.8706F, -0.3054F, 0.0F, 0.0F));

		ModelPartData bottom_jaw_r1 = bottom_jaw.addChild("bottom_jaw_r1", ModelPartBuilder.create().uv(62, 44).cuboid(-3.99F, 0.0F, -5.0F, 7.98F, 3.0F, 6.0F, new Dilation(0.0F)), ModelTransform.of(0.0F, -1.0F, -4.0F, 0.5672F, 0.0F, 0.0F));
		return TexturedModelData.of(modelData, 128, 128);
	}
	@Override
	public void setAngles(Entity entity, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {
	}
	@Override
	public void render(MatrixStack matrices, VertexConsumer vertexConsumer, int light, int overlay, float red, float green, float blue, float alpha) {
		cloak.render(matrices, vertexConsumer, light, overlay, red, green, blue, alpha);
		head.render(matrices, vertexConsumer, light, overlay, red, green, blue, alpha);
		right_arm2.render(matrices, vertexConsumer, light, overlay, red, green, blue, alpha);
		left_arm2.render(matrices, vertexConsumer, light, overlay, red, green, blue, alpha);
		left_leg2.render(matrices, vertexConsumer, light, overlay, red, green, blue, alpha);
		right_leg2.render(matrices, vertexConsumer, light, overlay, red, green, blue, alpha);
		bottom_jaw.render(matrices, vertexConsumer, light, overlay, red, green, blue, alpha);
	}
}