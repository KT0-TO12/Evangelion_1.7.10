package com.example.examplemod;

import cpw.mods.fml.common.registry.GameRegistry;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;

public class ModRecipes {

    public static void init() {
        //nail
        GameRegistry.addRecipe(new ItemStack(ModItems.nail, 4), "I","I",
                'I', Items.iron_ingot
        );
        //bat
        GameRegistry.addRecipe(new ItemStack(ModItems.battery, 1), "G","R","G",
                'G', Items.gold_ingot,
                'R', Items.redstone
        );

        // positron_rifle
        GameRegistry.addRecipe(new ItemStack(ModWeapons.positron_rifle), "BMB","III"," D ",
                'B', ModItems.battery,
                'M', ModItems.microchip,
                'I', Blocks.iron_block,
                'D', Items.diamond
        );
        //microchip
        GameRegistry.addRecipe(new ItemStack(ModItems.microchip), "BBB","BMB","IDI",
                'B', Items.iron_ingot,
                'M', Items.flint,
                'I', Items.redstone,
                'D', Items.gold_nugget
        );
    }
}
