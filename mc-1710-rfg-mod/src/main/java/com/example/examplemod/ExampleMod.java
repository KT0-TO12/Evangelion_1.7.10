package com.example.examplemod;

import com.example.examplemod.Weapon.ItemRenderPositronRifle;
import com.example.examplemod.Weapon.WeaponEventHandler;
import com.example.examplemod.render.RenderEva01;
import com.example.examplemod.entities.EntityEva01;
import com.example.examplemod.entities.EntitySachiel;
import com.example.examplemod.entities.ModEntities;
import com.example.examplemod.impact.ImpactEffects;
import com.example.examplemod.impact.ImpactHandler;
import com.example.examplemod.impact.WorldEvents;
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
        ModWeapons.init();
        ModItems.init();
    }

    @EventHandler
    public void init(FMLInitializationEvent event) {
        ModEntities.init();
        ModRecipes.init();
        ModBlocks.init();

        ImpactHandler impactHandler = new ImpactHandler();
        WeaponEventHandler weaponHandler = new WeaponEventHandler();
        MinecraftForge.EVENT_BUS.register(impactHandler);
        MinecraftForge.EVENT_BUS.register(weaponHandler);
        FMLCommonHandler.instance().bus().register(impactHandler);
        if (event.getSide() == Side.CLIENT) {
            cpw.mods.fml.client.registry.ClientRegistry.bindTileEntitySpecialRenderer(
                    com.example.examplemod.buildings.TileEntityAutoCannon.class,
                    new com.example.examplemod.render.RenderAutoCannon()
            );
        }
        if (event.getSide() == Side.CLIENT) {
            MinecraftForge.EVENT_BUS.register(new ImpactEffects());
            WorldEvents.init();
            MinecraftForgeClient.registerItemRenderer(
                    ModWeapons.positron_rifle,
                    new ItemRenderPositronRifle()
            );
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
