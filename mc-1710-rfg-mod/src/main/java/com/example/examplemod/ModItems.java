package com.example.examplemod;

import com.example.examplemod.ExampleMod;
import cpw.mods.fml.common.registry.GameRegistry;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;

public class ModItems {

    public static Item battery;
    public static Item nail;

    public static void init() {
        battery = new Item()
                .setUnlocalizedName("battery")
                .setTextureName(ExampleMod.MODID + ":battery")
                .setCreativeTab(CreativeTabs.tabCombat);

        nail = new Item()
                .setUnlocalizedName("nail")
                .setTextureName(ExampleMod.MODID + ":nail")
                .setCreativeTab(CreativeTabs.tabCombat);

        GameRegistry.registerItem(battery, "battery");
        GameRegistry.registerItem(nail, "nail");
    }
}
