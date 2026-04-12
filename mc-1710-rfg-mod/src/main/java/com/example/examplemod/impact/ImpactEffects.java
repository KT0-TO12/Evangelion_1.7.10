package com.example.examplemod.impact;

import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.world.World;
import net.minecraftforge.client.event.EntityViewRenderEvent;
import net.minecraftforge.event.entity.living.LivingDeathEvent;

@SideOnly(Side.CLIENT)
public class ImpactEffects {

    @SubscribeEvent
    public void onFogColor(EntityViewRenderEvent.FogColors event) {
        if (ImpactHandler.thirdImpactStarted) {
            event.red = 0.6F;
            event.green = 0.0F;
            event.blue = 0.0F;
        }
    }

    @SubscribeEvent
    public void onFogDensity(EntityViewRenderEvent.FogDensity event) {
        if (ImpactHandler.thirdImpactStarted) {
            event.density = 0.05F;
            event.setCanceled(true);
        }
    }

    @SubscribeEvent
    public void onEntityDeath(LivingDeathEvent event) {
        if (ImpactHandler.thirdImpactStarted) {
            EntityLivingBase entity = event.entityLiving;
            spawnImpactCross(entity.worldObj, entity.posX, entity.posY, entity.posZ);
        }
    }

    private void spawnImpactCross(World world, double x, double y, double z) {
        for (int h = 0; h < 40; h++) {
            world.spawnParticle("fireworksSpark", x, y + h, z, 0, 0.1, 0);
        }
        double barY = y + 25;
        for (double i = -7; i <= 7; i += 0.5) {
            world.spawnParticle("fireworksSpark", x + i, barY, z, 0, 0, 0);
        }
    }
}