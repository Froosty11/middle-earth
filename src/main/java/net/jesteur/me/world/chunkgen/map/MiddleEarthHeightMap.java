package net.jesteur.me.world.chunkgen.map;

import net.jesteur.me.utils.noises.PerlinNoise;
import net.jesteur.me.world.biomes.MEBiome;
import net.jesteur.me.world.biomes.MEBiomesData;

import java.awt.image.BufferedImage;

public class MiddleEarthHeightMap {
    public static final int SMOOTH_BRUSH_SIZE = 2;
    public static final int PERLIN_STRETCH_X = 210;
    public static final int PERLIN_STRETCH_Y = 180;
    public static final int PERLIN_STRETCH_X2 = 33;
    public static final int PERLIN_STRETCH_Y2 = 33;
    public static final int PERLIN_HEIGHT_RANGE = 90;
    public static final float MOUNTAIN_HEIGHT_RANGE = 2.5f;
    public static final int MOUNTAIN_START_HEIGHT = 16; // Height depending on the Biome Data.
    public static final int PERLIN_HEIGHT_OFFSET = 8;
    public static final int STONE_HEIGHT = 50;
    public static final int HEIGHT = 8 + STONE_HEIGHT;
    public static final int DIRT_HEIGHT = 3 + HEIGHT;

    private static BufferedImage heightMapImage;

    public static int latitude; // Horizontal
    public static int longitude; // Vertical


    public static void applyHeightMapImage(BufferedImage newHeightMapImage) {
        heightMapImage = newHeightMapImage;
        latitude = heightMapImage.getHeight();
        longitude = heightMapImage.getWidth();
    }

    public static float getPerlinHeight(int x, int z) {
        return 1;
    }

    public static float getHeight(int x, int z) {
        return 1;
    }

    public static float getSmoothHeight(int x, int z) {
        float total = 0;
        for(int i = -SMOOTH_BRUSH_SIZE; i <= SMOOTH_BRUSH_SIZE; i++) {
            for(int j = -SMOOTH_BRUSH_SIZE; j <= SMOOTH_BRUSH_SIZE; j++) {
                if(!isCoordinateInBounds(x + i, z + j)) total += MEBiomesData.defaultBiome.height;
                else total += ((float)((heightMapImage.getRGB(x + i, z + j)>>16)&0xFF) / 4) + MEBiomesData.MINIMAL_HEIGHT;
            }
        }

        return total / ((SMOOTH_BRUSH_SIZE * 2 + 1) * (SMOOTH_BRUSH_SIZE * 2 + 1));
    }

    public static boolean isCoordinateInBounds(int x, int z) {
        if(x < 0 || z < 0) return false;
        return (x < longitude && z < latitude);
    }

    // Going to be useful for making roads with curves.
    static float getPointOnBezierCurve(float h0, float h1, float h2, float t)
    {
        float a = lerp(h0, h1, t);
        float b = lerp(h1, h2, t);
        float d = lerp(a, b, t);

        return d;
    }

    public static float lerp(float a, float b, float interpolation) {
        return a + interpolation * (b - a);
    }
}
