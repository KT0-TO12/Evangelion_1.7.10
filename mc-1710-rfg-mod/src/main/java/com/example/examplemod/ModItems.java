package com.example.examplemod;

import cpw.mods.fml.common.registry.GameRegistry;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;

public class ModItems {

    public static Item battery;
    public static Item nail;
    public static Item microchip;
    public static Item kvantodrunovaya_chastica;
    public static Item kvant_fog;
    public static Item kvant_drun;
    public static void init() {
        kvantodrunovaya_chastica = new Item()
                .setUnlocalizedName("kvantodrunovaya_chastica")
                .setTextureName(ExampleMod.MODID + ":kvantodrunovaya_chastica")
                .setCreativeTab(CreativeTabs.tabRedstone);
        kvant_fog = new Item()
                .setUnlocalizedName("kvant_fog")
                .setTextureName(ExampleMod.MODID + ":kvant_fog")
                .setCreativeTab(CreativeTabs.tabRedstone);
        kvant_drun = new Item()
                .setUnlocalizedName("kvant_drun")
                .setTextureName(ExampleMod.MODID + ":kvant_drun")
                .setCreativeTab(CreativeTabs.tabRedstone);
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

        GameRegistry.registerItem(kvantodrunovaya_chastica, "kvantodrunovaya_chastica");
        GameRegistry.registerItem(kvant_fog, "kvant_fog");
        GameRegistry.registerItem(kvant_drun, "kvant_drun");
        GameRegistry.registerItem(battery, "battery");
        GameRegistry.registerItem(nail, "nail");
        GameRegistry.registerItem(microchip, "microchip");
    }
}
