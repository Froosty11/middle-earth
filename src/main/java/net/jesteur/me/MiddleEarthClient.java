package net.jesteur.me;

import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.blockrenderlayer.v1.BlockRenderLayerMap;
import net.fabricmc.fabric.api.client.model.ModelLoadingRegistry;
import net.fabricmc.fabric.api.client.rendering.v1.EntityRendererRegistry;
import net.fabricmc.fabric.api.client.rendering.v1.HudRenderCallback;
import net.jesteur.me.block.ModNatureBlocks;
import net.jesteur.me.datageneration.VariantsModelProvider;
import net.jesteur.me.entity.ModEntities;
import net.jesteur.me.entity.barrow_wights.BarrowWightEntityRenderer;
import net.jesteur.me.entity.crab.CrabRenderer;
import net.jesteur.me.entity.dwarves.durin.DurinDwarfRenderer;
import net.jesteur.me.entity.elves.galadhrim.GaladhrimElfRenderer;
import net.jesteur.me.entity.hobbits.HobbitRenderer;
import net.jesteur.me.entity.model.ModEntityModels;
import net.jesteur.me.entity.orcs.mordor.MordorOrcRenderer;
import net.jesteur.me.entity.spear.JavelinEntityRenderer;
import net.jesteur.me.entity.trolls.cave.CaveTrollRenderer;
import net.jesteur.me.entity.trolls.snow.SnowTrollRenderer;
import net.jesteur.me.item.utils.ModModelPredicateProvider;
import net.jesteur.me.mixin.InGameHUDInvoker;
import net.jesteur.me.statusEffects.Hallucination;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.gui.hud.InGameHud;
import net.minecraft.client.render.*;
import net.minecraft.client.render.entity.FlyingItemEntityRenderer;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.util.Identifier;

import java.util.Collection;
import java.util.concurrent.atomic.AtomicInteger;

public class MiddleEarthClient implements ClientModInitializer {

    @Override
    public void onInitializeClient() {
        HudRenderCallback.EVENT.register((drawContext, tickDelta) -> {
            int intensity = 0;
            boolean isHallucinating = false;

            Collection<StatusEffectInstance> c =  MinecraftClient.getInstance().player.getStatusEffects();
            for (StatusEffectInstance statusEffect:
                 c) {
                MiddleEarth.LOGGER.info(statusEffect.getEffectType().toString());
                if(statusEffect.getEffectType() instanceof Hallucination){
                    isHallucinating = true;
                    break;
                }
            }
            if(isHallucinating){
            InGameHud ingamehud = MinecraftClient.getInstance().inGameHud;
            InGameHUDInvoker inGameHUDInvoker = (InGameHUDInvoker) ingamehud;
            inGameHUDInvoker.renderOverlayInvoker(drawContext,new Identifier("me", "textures/entities/barrow_wights/overlay.png"), 1);
            }

        });

        ModEntityModels.getModels();
        EntityRendererRegistry.register(ModEntities.BARROW_WIGHT, BarrowWightEntityRenderer::new);
        EntityRendererRegistry.register(ModEntities.CAVE_TROLL, CaveTrollRenderer::new);
        EntityRendererRegistry.register(ModEntities.DURIN_FOLK, DurinDwarfRenderer::new);
        EntityRendererRegistry.register(ModEntities.HOBBIT, HobbitRenderer::new);
        EntityRendererRegistry.register(ModEntities.GALADHRIM_ELF, GaladhrimElfRenderer::new);
        EntityRendererRegistry.register(ModEntities.MORDOR_ORC, MordorOrcRenderer::new);
        EntityRendererRegistry.register(ModEntities.SNOW_TROLL, SnowTrollRenderer::new);

        // Items
        ModelLoadingRegistry.INSTANCE.registerModelProvider(((manager, out) -> new VariantsModelProvider().provideExtraModels(manager, out)));
        EntityRendererRegistry.register(ModEntities.PEBBLE, FlyingItemEntityRenderer::new);
        EntityRendererRegistry.register(ModEntities.SPEAR, JavelinEntityRenderer::new);

        // Animals
        EntityRendererRegistry.register(ModEntities.CRAB, CrabRenderer::new);

        ModModelPredicateProvider.registerBowModel();

        initializeRenderLayerMap();
    }

    private void initializeRenderLayerMap() {
        BlockRenderLayerMap.INSTANCE.putBlock(ModNatureBlocks.MORDOR_LICHEN, RenderLayer.getCutout());
        BlockRenderLayerMap.INSTANCE.putBlock(ModNatureBlocks.MORDOR_LICHEN_FAN, RenderLayer.getCutout());
        BlockRenderLayerMap.INSTANCE.putBlock(ModNatureBlocks.BROWN_GRASS, RenderLayer.getCutout());
        BlockRenderLayerMap.INSTANCE.putBlock(ModNatureBlocks.GREEN_SHRUB, RenderLayer.getCutout());
        BlockRenderLayerMap.INSTANCE.putBlock(ModNatureBlocks.TAN_SHRUB, RenderLayer.getCutout());
        BlockRenderLayerMap.INSTANCE.putBlock(ModNatureBlocks.STRAWBERRY_BUSH, RenderLayer.getCutout());
        BlockRenderLayerMap.INSTANCE.putBlock(ModNatureBlocks.TOUGH_BERRY_BUSH, RenderLayer.getCutout());
        BlockRenderLayerMap.INSTANCE.putBlock(ModNatureBlocks.YELLOW_FLOWER, RenderLayer.getCutout());
    }
}
