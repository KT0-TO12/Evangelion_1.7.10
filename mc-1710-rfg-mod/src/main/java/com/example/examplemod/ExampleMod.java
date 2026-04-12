package com.example.examplemod;

import com.example.examplemod.Weapon.ItemRenderPositronRifle;
import com.example.examplemod.Weapon.WeaponEventHandler;
import com.example.examplemod.client.render.RenderEva01;
import com.example.examplemod.entities.EntityEva01;
import com.example.examplemod.entities.EntitySachiel;
import com.example.examplemod.entities.ModEntities;
import com.example.examplemod.impact.ImpactEffects;
import com.example.examplemod.impact.ImpactHandler;
import com.example.examplemod.render.RenderSachiel;
import cpw.mods.fml.client.registry.RenderingRegistry;
import cpw.mods.fml.common.FMLCommonHandler;
import cpw.mods.fml.common.Mod;
import cpw.mods.fml.common.Mod.EventHandler;
import cpw.mods.fml.common.Mod.Instance;
import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;
import cpw.mods.fml.relauncher.Side;
import net.minecraftforge.client.MinecraftForgeClient;
import net.minecraftforge.common.MinecraftForge;

@Mod(modid = ExampleMod.MODID, version = ExampleMod.VERSION)
public class ExampleMod {
    public static final String MODID = "ev";
    public static final String VERSION = "1.0";

    @Instance(MODID)
    public static ExampleMod instance;

    @EventHandler
    public void preInit(FMLPreInitializationEvent event) {
        // Регистрация контента (предметы, блоки)
        ModWeapons.init();
        ModItems.init();
    }

    @EventHandler
    public void init(FMLInitializationEvent event) {
        // 1. Общая регистрация (Логика и Существа)
        ModEntities.init();

        ImpactHandler impactHandler = new ImpactHandler();
        WeaponEventHandler weaponHandler = new WeaponEventHandler();

        MinecraftForge.EVENT_BUS.register(impactHandler);
        MinecraftForge.EVENT_BUS.register(weaponHandler);
        FMLCommonHandler.instance().bus().register(impactHandler);

        // 2. Клиентская регистрация (Визуал и Рендеры)
        if (event.getSide() == Side.CLIENT) {
            // Регистрация эффектов мира
            MinecraftForge.EVENT_BUS.register(new ImpactEffects());
            WorldEvents.init();

            // Регистрация 3D модели пушки
            MinecraftForgeClient.registerItemRenderer(
                    ModWeapons.positron_rifle,
                    new ItemRenderPositronRifle()
            );

            // Регистрация рендеров существ
            RenderingRegistry.registerEntityRenderingHandler(
                    EntityEva01.class,
                    new RenderEva01()
            );

            RenderingRegistry.registerEntityRenderingHandler(
                    EntitySachiel.class,
                    new RenderSachiel()
            );
        }
    }
}
