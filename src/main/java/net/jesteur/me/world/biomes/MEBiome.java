package net.jesteur.me.world.biomes;

import net.minecraft.block.Block;
import net.minecraft.registry.RegistryKey;
import net.minecraft.world.biome.Biome;

public class MEBiome {
    public float height;
    public RegistryKey<Biome> biome;
    public Block surfaceBlock;
    public Block underSurfaceBlock;
    public Block stoneBlock;
    public Block deepStoneBlock;

    public float heightChange = 8;

    public MEBiome(float height, RegistryKey<Biome> biome, Block surfaceBlock, Block underSurfaceBlock, Block stoneBlock, Block deepStoneBlock) {
        this.height = height;
        this.biome = biome;
        this.surfaceBlock = surfaceBlock;
        this.underSurfaceBlock = underSurfaceBlock;
        this.stoneBlock = stoneBlock;
        this.deepStoneBlock = deepStoneBlock;
    }
}
