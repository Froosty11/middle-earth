package net.jesteur.me.datageneration;

import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricBlockLootTableProvider;
import net.jesteur.me.block.OreRockSets;
import net.jesteur.me.datageneration.content.loot_tables.BlockDrops;
import net.jesteur.me.datageneration.content.loot_tables.LeavesDrops;
import net.jesteur.me.datageneration.content.models.SimpleBlockModel;
import net.jesteur.me.datageneration.content.tags.MineablePickaxe;
import net.minecraft.block.Block;
import net.minecraft.data.server.loottable.BlockLootTableGenerator;
import net.minecraft.item.Item;
import net.minecraft.item.Items;

public class BlockLootTableProvider extends FabricBlockLootTableProvider {

    protected BlockLootTableProvider(FabricDataOutput dataOutput) {
        super(dataOutput);
    }
    @Override
    public void generate() {
        /*for (OreRockSets.OreRockSet set : OreRockSets.sets) { //Deprecated because fortune no worky. 
            addDrop(set.gold_ore(), drops(Items.RAW_GOLD));
            addDrop(set.iron_ore(), drops(Items.RAW_IRON));
        }*/


        for (Block block : BlockDrops.blocks) {
            addDrop(block);
        }
        for (Block block : LeavesDrops.blocks) {
            addDrop(block, BlockLootTableGenerator.dropsWithShears(block));
        }
    }
}
