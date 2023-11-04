package net.jukoz.me.datageneration.content.loot_tables;

import net.jukoz.me.block.ModNatureBlocks;
import net.jukoz.me.block.crop.*;
import net.jukoz.me.item.ModFoodItems;
import net.jukoz.me.item.ModRessourceItems;
import net.minecraft.block.Block;
import net.minecraft.item.Item;
import net.minecraft.loot.condition.BlockStatePropertyLootCondition;
import net.minecraft.predicate.StatePredicate;

import java.util.ArrayList;
import java.util.List;

public class CropDrops {
    public static List<CropDrop> crops = new ArrayList<>() {
        {
            add(new CropDrop(BlockStatePropertyLootCondition.builder(ModNatureBlocks.TOMATO_CROP).properties(StatePredicate.Builder.create().exactMatch(TomatoCropBlock.AGE, 3)),
                    ModNatureBlocks.TOMATO_CROP, ModFoodItems.TOMATO, ModRessourceItems.TOMATO_SEEDS));
            add(new CropDrop(BlockStatePropertyLootCondition.builder(ModNatureBlocks.BELL_PEPPER_CROP).properties(StatePredicate.Builder.create().exactMatch(BellpepperCropBlock.AGE, 4)),
                    ModNatureBlocks.BELL_PEPPER_CROP, ModFoodItems.BELL_PEPPER, ModRessourceItems.BELL_PEPPER_SEEDS));
            add(new CropDrop(BlockStatePropertyLootCondition.builder(ModNatureBlocks.CUCUMBER_CROP).properties(StatePredicate.Builder.create().exactMatch(CucumberCropBlock.AGE, 3)),
                    ModNatureBlocks.CUCUMBER_CROP, ModFoodItems.CUCUMBER, ModRessourceItems.CUCUMBER_SEEDS));
            add(new CropDrop(BlockStatePropertyLootCondition.builder(ModNatureBlocks.FLAX_CROP).properties(StatePredicate.Builder.create().exactMatch(FlaxCropBlock.AGE, 3)),
                    ModNatureBlocks.FLAX_CROP, ModRessourceItems.FLAX, ModRessourceItems.FLAX_SEEDS));
            add(new CropDrop(BlockStatePropertyLootCondition.builder(ModNatureBlocks.GARLIC_CROP).properties(StatePredicate.Builder.create().exactMatch(GarlicCropBlock.AGE, 3)),
                    ModNatureBlocks.GARLIC_CROP, ModFoodItems.GARLIC, ModFoodItems.GARLIC));
            add(new CropDrop(BlockStatePropertyLootCondition.builder(ModNatureBlocks.LEEK_CROP).properties(StatePredicate.Builder.create().exactMatch(LeekCropBlock.AGE, 3)),
                    ModNatureBlocks.LEEK_CROP, ModFoodItems.LEEK, ModRessourceItems.LEEK_SEEDS));
            add(new CropDrop(BlockStatePropertyLootCondition.builder(ModNatureBlocks.LETTUCE_CROP).properties(StatePredicate.Builder.create().exactMatch(LettuceCropBlock.AGE, 3)),
                    ModNatureBlocks.LETTUCE_CROP, ModFoodItems.LETTUCE, ModRessourceItems.LETTUCE_SEEDS));
            add(new CropDrop(BlockStatePropertyLootCondition.builder(ModNatureBlocks.ONION_CROP).properties(StatePredicate.Builder.create().exactMatch(OnionCropBlock.AGE, 3)),
                    ModNatureBlocks.ONION_CROP, ModFoodItems.ONION, ModFoodItems.ONION));
            add(new CropDrop(BlockStatePropertyLootCondition.builder(ModNatureBlocks.PIPEWEED_CROP).properties(StatePredicate.Builder.create().exactMatch(PipeweedCropBlock.AGE, 3)),
                    ModNatureBlocks.PIPEWEED_CROP, ModRessourceItems.PIPEWEED, ModRessourceItems.PIPEWEED_SEEDS));
        }
    };
    public static List<CropDrop> wild_crops = new ArrayList<>() {
        {
            add(new CropDrop(null, ModNatureBlocks.WILD_PIPEWEED, ModRessourceItems.PIPEWEED, null));
        }
    };

    public static class CropDrop {
        public BlockStatePropertyLootCondition.Builder builder;
        public Block crop_block;
        public Item fruit;
        public Item seeds;

        public CropDrop(BlockStatePropertyLootCondition.Builder builder, Block block, Item fruit, Item seeds) {
            this.builder = builder;
            this.crop_block = block;
            this.fruit = fruit;
            this.seeds = seeds;
        }

    }
}
