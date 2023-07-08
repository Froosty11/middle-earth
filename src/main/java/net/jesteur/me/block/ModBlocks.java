package net.jesteur.me.block;

import net.fabricmc.fabric.api.item.v1.FabricItemSettings;
import net.fabricmc.fabric.api.itemgroup.v1.ItemGroupEvents;
import net.fabricmc.fabric.api.object.builder.v1.block.FabricBlockSettings;
import net.jesteur.me.MiddleEarth;
import net.jesteur.me.item.utils.ModItemGroups;
import net.minecraft.block.*;
import net.minecraft.item.BlockItem;
import net.minecraft.item.Item;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.sound.BlockSoundGroup;
import net.minecraft.util.Identifier;

public class ModBlocks {
    public static final float DIRT_STRENGTH = 0.6f;
    public static final float SLAB_RESISTANCE = 6.0f; // From explosions

    public static final Block MORDOR_DIRT = registerBlock("mordor_dirt",
            new Block(FabricBlockSettings.copyOf(Blocks.DIRT).strength(DIRT_STRENGTH).sounds(BlockSoundGroup.GRAVEL)));

    public static final Block MITHRIL_BLOCK = registerBlock("mithril_block",
            new Block(FabricBlockSettings.copyOf(Blocks.IRON_BLOCK).strength(6f).requiresTool()));
    public static final Block MITHRIL_ORE = registerBlock("mithril_ore",
            new Block(FabricBlockSettings.copyOf(Blocks.STONE).strength(4f).requiresTool()));

    public static Block registerBlock(String name, Block block) {
        registerBlockItem(name, block);
        return Registry.register(Registries.BLOCK, new Identifier(MiddleEarth.MOD_ID, name), block);
    }

    static Item registerBlockItem(String name, Block block) {
        var item =  Registry.register(Registries.ITEM, new Identifier(MiddleEarth.MOD_ID, name),
                new BlockItem(block, new FabricItemSettings()));
        Item.BLOCK_ITEMS.put(block, item);
        ModItemGroups.BLOCKS_CONTENTS.add(item.getDefaultStack());
        return item;
    }

    public static void registerModBlocks() {
        MiddleEarth.LOGGER.debug("Registering ModBlocks for " + MiddleEarth.MOD_ID);
    }
}
