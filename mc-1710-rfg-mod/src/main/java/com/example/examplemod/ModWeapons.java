package com.example.examplemod;

import cpw.mods.fml.common.registry.GameRegistry;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import net.minecraft.item.ItemSword;
import net.minecraftforge.common.util.EnumHelper;

import java.util.ArrayList;
import java.util.List;

public class ModWeapons {
    private static final List<Item> ALL_WEAPONS = new ArrayList<>();

    public static final Item.ToolMaterial PROG_KNIFE_MAT = EnumHelper.addToolMaterial("PROG_KNIFE_MAT", 3, 1500, 35.0F, 34.0F, 10);
    public static Item prog_knife = new ItemSword(PROG_KNIFE_MAT)
            .setUnlocalizedName("prog_knife")
            .setTextureName(ExampleMod.MODID + ":prog_knife")
            .setCreativeTab(CreativeTabs.tabCombat);
    public static final Item.ToolMaterial LANCE_MAT = EnumHelper.addToolMaterial("LANCE_MAT", 4, -1, 10.0F, 150.0F, 15);
    public static Item lance_longinus = new ItemSword(LANCE_MAT)
            .setUnlocalizedName("lance_longinus")
            .setTextureName(ExampleMod.MODID + ":lance_longinus")
            .setCreativeTab(CreativeTabs.tabCombat);
    public static Item positron_rifle = new Item()
            .setUnlocalizedName("positron_rifle")
            .setTextureName(ExampleMod.MODID + ":positron_rifle")
            .setCreativeTab(CreativeTabs.tabCombat)
            .setMaxStackSize(1);
    public static void init() {
        ALL_WEAPONS.add(prog_knife);
        ALL_WEAPONS.add(lance_longinus);
        ALL_WEAPONS.add(positron_rifle);
        for (Item item : ALL_WEAPONS) {
            String name = item.getUnlocalizedName().substring(5);
            GameRegistry.registerItem(item, name);
        }
    }
}