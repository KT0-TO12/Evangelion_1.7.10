package com.example.examplemod;

import net.minecraft.world.biome.BiomeGenBase;

public class WorldEvents {
    public static void init() {
        for (BiomeGenBase biome : BiomeGenBase.getBiomeGenArray()) {
            if (biome != null) {
                biome.waterColorMultiplier = 0xFF0000;
            }
        }
    }
}
