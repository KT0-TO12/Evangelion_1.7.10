package com.example.examplemod;

import cpw.mods.fml.common.registry.GameRegistry;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;

public class ModItems {

    public static Item battery;
    public static Item nail;
    public static Item microchip;
    public static void init() {
        microchip = new Item()
                .setUnlocalizedName("microchip")
                .setTextureName(ExampleMod.MODID + ":microchip")
                .setCreativeTab(CreativeTabs.tabCombat);

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
        GameRegistry.registerItem(microchip, "microchip");
    }
}
