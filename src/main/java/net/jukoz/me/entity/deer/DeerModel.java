package net.jukoz.me.entity.deer;

import net.jukoz.me.entity.beasts.trolls.TrollAnimations;
import net.minecraft.client.model.*;
import net.minecraft.client.render.VertexConsumer;
import net.minecraft.client.render.entity.model.SinglePartEntityModel;
import net.minecraft.client.util.math.MatrixStack;

public class DeerModel extends SinglePartEntityModel<DeerEntity> {
    private final ModelPart deer;
    public DeerModel(ModelPart root) {
        this.deer = root.getChild("deer");
    }
    public static TexturedModelData getTexturedModelData() {
        ModelData modelData = new ModelData();
        ModelPartData modelPartData = modelData.getRoot();
        ModelPartData deer = modelPartData.addChild("deer", ModelPartBuilder.create(), ModelTransform.pivot(0.0F, 24.0F, 0.0F));

        ModelPartData Upperbody = deer.addChild("Upperbody", ModelPartBuilder.create(), ModelTransform.pivot(0.0F, -15.5F, -3.5F));

        ModelPartData Chest = Upperbody.addChild("Chest", ModelPartBuilder.create().uv(35, 15).cuboid(-5.5F, -5.5F, -8.5F, 11.0F, 11.0F, 9.0F, new Dilation(0.0F))
                .uv(0, 24).cuboid(-5.0F, -4.5F, 0.5F, 10.0F, 10.0F, 11.0F, new Dilation(0.0F))
                .uv(72, 49).cuboid(-2.0F, -2.5F, 6.5F, 4.0F, 3.0F, 7.0F, new Dilation(0.0F)), ModelTransform.pivot(0.0F, -1.0F, -2.0F));

        ModelPartData head = Upperbody.addChild("Head", ModelPartBuilder.create(), ModelTransform.of(0.0F, -0.9137F, -8.8708F, -0.5672F, 0.0F, 0.0F));

        ModelPartData neck = head.addChild("neck", ModelPartBuilder.create(), ModelTransform.pivot(0.0F, 0.7641F, 0.6357F));

        neck.addChild("neck_r1", ModelPartBuilder.create().uv(0, 0).cuboid(-2.5F, -3.3504F, -8.5F, 5.0F, 7.0F, 17.0F, new Dilation(-0.1F)), ModelTransform.of(0.0F, -5.0F, -3.0F, -1.0036F, 0.0F, 0.0F));

        ModelPartData headnoneck = neck.addChild("head_no_neck", ModelPartBuilder.create(), ModelTransform.of(0.0F, -9.5365F, -6.0671F, 0.2182F, 0.0F, 0.0F));

        headnoneck.addChild("left_ear", ModelPartBuilder.create().uv(27, 0).cuboid(-1.0F, -2.5F, -0.5F, 2.0F, 5.0F, 1.0F, new Dilation(0.0F)), ModelTransform.of(3.0F, -2.5889F, -0.3614F, 0.1678F, -0.2564F, 0.9819F));

        headnoneck.addChild("right_ear", ModelPartBuilder.create().uv(36, 45).cuboid(-1.0F, -2.5F, -0.5F, 2.0F, 5.0F, 1.0F, new Dilation(0.0F)), ModelTransform.of(-3.0F, -2.5889F, -0.3614F, 0.1678F, 0.2564F, -0.9819F));

        ModelPartData Head_r1 = headnoneck.addChild("Head_r1", ModelPartBuilder.create().uv(62, 5).cuboid(-3.0F, -1.0F, -6.8F, 4.0F, 4.0F, 4.0F, new Dilation(0.1F)), ModelTransform.of(1.0F, -1.7163F, -1.5432F, 0.4363F, 0.0F, 0.0F));

        ModelPartData Head_r2 = headnoneck.addChild("Head_r2", ModelPartBuilder.create().uv(33, 3).cuboid(-3.0F, -3.0F, -2.4569F, 6.0F, 5.0F, 7.0F, new Dilation(0.1F)), ModelTransform.of(0.0F, -0.7072F, -1.3409F, 0.4363F, 0.0F, 0.0F));

        ModelPartData Antlers = head.addChild("Antlers", ModelPartBuilder.create(), ModelTransform.of(-3.0F, -20.0863F, -8.1292F, 0.0873F, 0.0F, 0.0F));

        ModelPartData cube_r1 = Antlers.addChild("cube_r1", ModelPartBuilder.create().uv(2, 59).mirrored().cuboid(-2.0F, -7.5F, -6.2F, 0.0F, 15.0F, 15.0F, new Dilation(0.0F)).mirrored(false), ModelTransform.of(0.0F, 0.0F, 0.0F, 0.3309F, -0.4862F, -0.3936F));

        ModelPartData cube_r2 = Antlers.addChild("cube_r2", ModelPartBuilder.create().uv(2, 59).cuboid(2.0F, -7.5F, -6.2F, 0.0F, 15.0F, 15.0F, new Dilation(0.0F)), ModelTransform.of(6.0F, 0.0F, 0.0F, 0.3309F, 0.4862F, 0.3936F));

        ModelPartData BackLegsLeft = deer.addChild("BackLegsLeft", ModelPartBuilder.create().uv(0, 57).cuboid(-1.5F, -1.0F, -2.0F, 3.0F, 12.0F, 4.0F, new Dilation(0.0F)), ModelTransform.pivot(3.49F, -11.0F, 4.0F));

        ModelPartData BackLegsRight = deer.addChild("BackLegsRight", ModelPartBuilder.create().uv(38, 57).cuboid(-1.5F, -1.0F, -2.0F, 3.0F, 12.0F, 4.0F, new Dilation(0.0F)), ModelTransform.pivot(-3.49F, -11.0F, 4.0F));

        ModelPartData FrontLegsLeft = deer.addChild("FrontLegsLeft", ModelPartBuilder.create().uv(12, 57).cuboid(-1.5F, -1.0F, -2.0F, 3.0F, 12.0F, 4.0F, new Dilation(0.0F)), ModelTransform.pivot(3.5F, -11.0F, -11.0F));

        ModelPartData FrontLegsRight = deer.addChild("FrontLegsRight", ModelPartBuilder.create().uv(24, 57).cuboid(-1.5F, -1.0F, -2.0F, 3.0F, 12.0F, 4.0F, new Dilation(0.0F)), ModelTransform.pivot(-3.5F, -11.0F, -11.0F));
        return TexturedModelData.of(modelData, 128, 128);
    }

    @Override
    public void render(MatrixStack matrices, VertexConsumer vertexConsumer, int light, int overlay, float red, float green, float blue, float alpha) {
        this.deer.render(matrices, vertexConsumer, light, overlay, red, green, blue, alpha);
    }

    @Override
    public ModelPart getPart() {
        return this.deer;
    }

    @Override
    public void setAngles(DeerEntity entity, float limbAngle, float limbDistance, float animationProgress, float headYaw, float headPitch) {
        this.getPart().traverse().forEach(ModelPart::resetTransform);

        this.animateMovement(DeerAnimations.WALK, limbAngle, limbDistance, 1f, 1f);
        this.updateAnimation(entity.idleAnimationState, DeerAnimations.IDLE, animationProgress, 1f);
    }
}

