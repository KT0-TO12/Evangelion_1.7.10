package com.example.examplemod;

import com.example.examplemod.buildings.BlockAutoCannon;
import com.example.examplemod.buildings.drunium_colaider.BlockCollider;
import com.example.examplemod.buildings.drunium_colaider.TileEntityDruniumAmplifier;
import com.example.examplemod.buildings.drunium_colaider.TileEntityDruniumSensor;
import com.example.examplemod.buildings.drunium_colaider.TileEntityDruniumSource;
import cpw.mods.fml.common.registry.GameRegistry;
import net.minecraft.block.Block;
import net.minecraft.creativetab.CreativeTabs;

public class ModBlocks {
    public static Block auto_cannon;
    public static Block drun_source;
    public static Block drun_amplifier;
    public static Block drun_sensor;

    public static void init() {
        auto_cannon = new BlockAutoCannon().setCreativeTab(CreativeTabs.tabCombat);
        drun_source = new BlockCollider(0, "drun_source").setCreativeTab(CreativeTabs.tabRedstone);
        drun_amplifier = new BlockCollider(1, "drun_amplifier").setCreativeTab(CreativeTabs.tabRedstone);
        drun_sensor = new BlockCollider(2, "drun_sensor").setCreativeTab(CreativeTabs.tabRedstone);

        GameRegistry.registerBlock(auto_cannon, "auto_cannon");
        GameRegistry.registerBlock(drun_source, "drun_source");
        GameRegistry.registerBlock(drun_amplifier, "drun_amplifier");
        GameRegistry.registerBlock(drun_sensor, "drun_sensor");

        GameRegistry.registerTileEntity(TileEntityDruniumSource.class, "TESource");
        GameRegistry.registerTileEntity(TileEntityDruniumAmplifier.class, "TEAmplifier");
        GameRegistry.registerTileEntity(TileEntityDruniumSensor.class, "TESensor");
    }
}
