package com.example.examplemod.entities;

import com.example.examplemod.ExampleMod;
import cpw.mods.fml.common.registry.EntityRegistry;
import net.minecraft.entity.EnumCreatureType;
import net.minecraft.world.biome.BiomeGenBase;

public class ModEntities {

    public static void init() {
       // registerMonster(EntityEva01.class, "Eva01", 0x532D82, 0x76B502);
        registerMonster(EntitySachiel.class, "Sachiel", 0x111111, 0xFF0000);
    }

    private static void registerMonster(Class entityClass, String name, int color1, int color2) {
        int id = EntityRegistry.findGlobalUniqueEntityId();
        EntityRegistry.registerGlobalEntityID(entityClass, name, id, color1, color2);
        EntityRegistry.registerModEntity(entityClass, name, id, ExampleMod.instance, 64, 1, true);
        EntityRegistry.addSpawn(entityClass, 2, 1, 1, EnumCreatureType.monster, BiomeGenBase.ocean, BiomeGenBase.beach);
    }
}
