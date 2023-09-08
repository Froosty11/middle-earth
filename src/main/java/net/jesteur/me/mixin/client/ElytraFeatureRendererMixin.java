package net.jesteur.me.mixin.client;

import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.jesteur.me.MiddleEarth;
import net.jesteur.me.model.equipment.CloakCapeModel;
import net.jesteur.me.item.ModEquipmentItems;
import net.jesteur.me.utils.IntToRGB;
import net.minecraft.client.render.OverlayTexture;
import net.minecraft.client.render.RenderLayer;
import net.minecraft.client.render.VertexConsumer;
import net.minecraft.client.render.VertexConsumerProvider;
import net.minecraft.client.render.entity.feature.ElytraFeatureRenderer;
import net.minecraft.client.render.entity.feature.FeatureRenderer;
import net.minecraft.client.render.entity.feature.FeatureRendererContext;
import net.minecraft.client.render.entity.model.EntityModel;
import net.minecraft.client.render.item.ItemRenderer;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.entity.EquipmentSlot;
import net.minecraft.entity.LivingEntity;
import net.minecraft.item.*;
import net.minecraft.util.Identifier;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;


import java.awt.*;

@Environment(EnvType.CLIENT)
@Mixin(ElytraFeatureRenderer.class)
public abstract class ElytraFeatureRendererMixin<T extends LivingEntity, M extends EntityModel<T>> extends FeatureRenderer<T, M> {
    private static final Identifier CLOAK_CAPE_TEXTURE = new Identifier(MiddleEarth.MOD_ID, "textures/models/armor/cloak_features.png");
    private static final Identifier FUR_CLOAK_CAPE_TEXTURE = new Identifier(MiddleEarth.MOD_ID, "textures/models/armor/fur_cloak_features.png");

    private final CloakCapeModel<T> cloakCapeModel = new CloakCapeModel<>(CloakCapeModel.getTexturedModelData().createModel());

    public ElytraFeatureRendererMixin(FeatureRendererContext<T, M> context) {
        super(context);
    }

    @Inject(method = "render", at = @At("HEAD"), cancellable = true)
    private void render(MatrixStack matrices, VertexConsumerProvider vertexConsumers, int light, T entity, float limbAngle, float limbDistance, float tickDelta, float animationProgress, float headYaw, float headPitch, CallbackInfo info) {
    {
        ItemStack itemStack = entity.getEquippedStack(EquipmentSlot.CHEST);
        Item item = itemStack.getItem();
        // Dyeable cloak
        if (item == ModEquipmentItems.CLOAK || item == ModEquipmentItems.TUNIC_CLOAK) {
            matrices.push();

            this.getContextModel().copyStateTo(this.cloakCapeModel);

            VertexConsumer vertexConsumer = ItemRenderer.getArmorGlintConsumer(vertexConsumers, RenderLayer.getArmorCutoutNoCull(CLOAK_CAPE_TEXTURE), false, itemStack.hasGlint());

            Color rgb = IntToRGB.ex(((DyeableItem)itemStack.getItem()).getColor(itemStack));
            this.cloakCapeModel.render(matrices, vertexConsumer, light, OverlayTexture.DEFAULT_UV, rgb.getRed()/255f, rgb.getGreen()/255f, rgb.getBlue()/255f, 1.0F);
            this.cloakCapeModel.setAngles(entity, limbAngle, limbDistance, animationProgress, headYaw, headPitch);
            matrices.pop();
            info.cancel();
        }
        // Fur cloak
        if (item == ModEquipmentItems.FUR_CLOAK || item == ModEquipmentItems.CHAINMAIL_CLOAK) {
            matrices.push();

            this.getContextModel().copyStateTo(this.cloakCapeModel);

            VertexConsumer vertexConsumer = ItemRenderer.getArmorGlintConsumer(vertexConsumers, RenderLayer.getArmorCutoutNoCull(FUR_CLOAK_CAPE_TEXTURE), false, itemStack.hasGlint());

            this.cloakCapeModel.render(matrices, vertexConsumer, light, OverlayTexture.DEFAULT_UV, 1.0f, 1.0f, 1.0f, 1.0F);
            this.cloakCapeModel.setAngles(entity, limbAngle, limbDistance, animationProgress, headYaw, headPitch);
            matrices.pop();
            info.cancel();
        }
    }}
}
