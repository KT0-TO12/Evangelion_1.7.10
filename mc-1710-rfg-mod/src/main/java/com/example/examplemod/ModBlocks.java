package com.example.examplemod;

import com.example.examplemod.buildings.BlockAutoCannon;
import cpw.mods.fml.common.registry.GameRegistry;
import net.minecraft.block.Block;

public class ModBlocks {
    public static Block auto_cannon;

    public static void init() {
        auto_cannon = new BlockAutoCannon();
        GameRegistry.registerBlock(auto_cannon, "auto_cannon");
    }
}
