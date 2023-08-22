package net.jesteur.me.block;

import net.fabricmc.fabric.api.object.builder.v1.block.FabricBlockSettings;
import net.jesteur.me.MiddleEarth;
import net.minecraft.block.*;

public class OreRockSets {
    public static final float STONE_STRENGTH = 1.5f;
    public static final float COBBLE_STRENGTH = 1.65f;
    public static final float BRICKS_STRENGTH = 1.75f;
    public static final float IRON_ORE_STRENGTH = 3.0f;
    public static final float GOLD_ORE_STRENGTH = 3.0f;

    public static OreRockSet ASHEN_ROCK = registerOreSet("ashen_rock", 1.2f, null);
    public static OreRockSet BLUE_ROCK = registerOreSet("blue_rock", 1.2f, null);
    public static OreRockSet DOLOMITE = registerOreSet("dolomite", 1.2f, null);
    public static OreRockSet FROZEN_BLUE_ROCK = registerOreSet("frozen_blue_rock", 1.4f, null);
    public static OreRockSet CALCITE = registerOreSet("calcite", 1.0f, null);

    
    public static OreRockSet[] sets = new OreRockSet[] {
            ASHEN_ROCK,
            BLUE_ROCK,
            DOLOMITE,
            FROZEN_BLUE_ROCK,
            CALCITE
    };

    public record OreRockSet(Block iron_ore, Block gold_ore) {
    }
    public static OreRockSet registerOreSet(String rockname, float strength_mult, Block source){
        Block iron_ore = ModBlocks.registerBlock(
            rockname + "_iron_ore", new Block(FabricBlockSettings.copyOf(Blocks.IRON_ORE).strength(
                IRON_ORE_STRENGTH*strength_mult).requiresTool()));        
        Block gold_ore = ModBlocks.registerBlock(
            rockname + "_gold_ore", new Block(FabricBlockSettings.copyOf(Blocks.GOLD_ORE).strength(
                GOLD_ORE_STRENGTH*strength_mult).requiresTool()));
        return new OreRockSet(iron_ore,gold_ore);

    }
    public static void registerModBlockSets() {
        MiddleEarth.LOGGER.debug("Registering OreSets for " + MiddleEarth.MOD_ID);
    }
}
