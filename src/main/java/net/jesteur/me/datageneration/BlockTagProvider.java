package net.jesteur.me.datageneration;

import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricTagProvider;
import net.jesteur.me.block.OreRockSets;
import net.jesteur.me.datageneration.content.tags.*;
import net.minecraft.block.Block;
import net.minecraft.item.Item;
import net.minecraft.item.Items;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.registry.RegistryWrapper;
import net.minecraft.registry.tag.ItemTags;
import net.minecraft.registry.tag.TagKey;
import net.minecraft.util.Identifier;

import java.util.concurrent.CompletableFuture;

import com.mojang.datafixers.types.templates.Tag;

public class BlockTagProvider extends FabricTagProvider.BlockTagProvider {

    public BlockTagProvider(FabricDataOutput output, CompletableFuture<RegistryWrapper.WrapperLookup> registriesFuture) {
        super(output, registriesFuture);
    }

    @Override
    protected void configure(RegistryWrapper.WrapperLookup arg) {
        var mineablePickaxe = getOrCreateTagBuilder(TagKey.of(RegistryKeys.BLOCK, new Identifier("minecraft", "mineable/pickaxe")));
        var mineableAxe = getOrCreateTagBuilder(TagKey.of(RegistryKeys.BLOCK, new Identifier("minecraft", "mineable/axe")));
        mineableAxe.add(MineableAxe.blocks.toArray(new Block[0]));
        mineablePickaxe.add(MineablePickaxe.blocks.toArray(new Block[0]));

        getOrCreateTagBuilder(TagKey.of(RegistryKeys.BLOCK, new Identifier("minecraft", "buttons"))).add(Buttons.buttons.toArray(new Block[0]));
        getOrCreateTagBuilder(TagKey.of(RegistryKeys.BLOCK, new Identifier("minecraft", "fences"))).add(Fences.fences.toArray(new Block[0]));
        getOrCreateTagBuilder(TagKey.of(RegistryKeys.BLOCK, new Identifier("minecraft", "fence_gates"))).add(FenceGates.fenceGates.toArray(new Block[0]));
        getOrCreateTagBuilder(TagKey.of(RegistryKeys.BLOCK, new Identifier("minecraft", "logs"))).add(Logs.logs.toArray(new Block[0]));
        getOrCreateTagBuilder(TagKey.of(RegistryKeys.BLOCK, new Identifier("minecraft", "pressure_plates"))).add(PressurePlates.pressurePlates.toArray(new Block[0]));
        getOrCreateTagBuilder(TagKey.of(RegistryKeys.BLOCK, new Identifier("minecraft", "walls"))).add(Walls.walls.toArray(new Block[0]));
        getOrCreateTagBuilder(TagKey.of(RegistryKeys.BLOCK, new Identifier("minecraft", "planks"))).add(Planks.planks.toArray(new Block[0]));


        //Ores
        TagKey<Block> iron_ores = TagKey.of(RegistryKeys.BLOCK, new Identifier("minecraft", "iron_ores"));
        TagKey<Block> gold_ores = TagKey.of(RegistryKeys.BLOCK, new Identifier("minecraft", "gold_ores"));
        TagKey<Block> cores = TagKey.of(RegistryKeys.BLOCK, new Identifier("c", "ores")); //Create Ores list

        for (OreRockSets.OreRockSet set : OreRockSets.sets) {
        getOrCreateTagBuilder(iron_ores)
            .add(set.iron_ore());
        getOrCreateTagBuilder(gold_ores) 
            .add(set.gold_ore());
        getOrCreateTagBuilder(cores)
            .add(set.iron_ore())
            .add(set.gold_ore());
        }

    }
}
