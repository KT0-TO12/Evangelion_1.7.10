package com.example.examplemod;

import cpw.mods.fml.common.registry.GameRegistry;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;


import java.util.ArrayList;
import java.util.List;

public class ModArmor {
    private static final List<Item> ALL_ARMOR = new ArrayList<>();

    public static void init() {
//      ALL_ARMOR.add();
        for (Item item : ALL_ARMOR) {
            String name = item.getUnlocalizedName().substring(5);
            GameRegistry.registerItem(item, name);
        }
    }
}

