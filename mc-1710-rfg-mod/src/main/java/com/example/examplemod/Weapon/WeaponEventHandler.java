package com.example.examplemod.Weapon;

import com.example.examplemod.ModItems;
import com.example.examplemod.ModWeapons;
import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.util.*;
import net.minecraft.world.World;
import net.minecraftforge.event.entity.player.PlayerInteractEvent;

import java.util.List;

public class WeaponEventHandler {

    @SubscribeEvent
    public void onPlayerInteract(PlayerInteractEvent event) {
        if (event.action == PlayerInteractEvent.Action.RIGHT_CLICK_AIR || event.action == PlayerInteractEvent.Action.RIGHT_CLICK_BLOCK) {
            EntityPlayer player = event.entityPlayer;
            ItemStack heldItem = player.getHeldItem();

            if (heldItem != null && heldItem.getItem() == ModWeapons.positron_rifle) {

                if (player.capabilities.isCreativeMode || (player.inventory.hasItem(ModItems.battery) && player.inventory.hasItem(ModItems.nail))) {
                    shootPositronRifle(player);
                    if (!player.worldObj.isRemote && !player.capabilities.isCreativeMode) {
                        player.inventory.consumeInventoryItem(ModItems.battery);
                        player.inventory.consumeInventoryItem(ModItems.nail);
                    }
                } else {
                    if (player.worldObj.isRemote) {
                        player.worldObj.playSoundAtEntity(player, "random.click", 1.0F, 0.5F);
                        player.addChatMessage(new ChatComponentText(EnumChatFormatting.RED + "Недостаточно ресурсов (Батарейка + Стержень)!"));
                    }
                }
            }
        }
    }

    private void shootPositronRifle(EntityPlayer player) {
        World world = player.worldObj;
        double maxDist = 120.0;
        MovingObjectPosition mop = player.rayTrace(maxDist, 1.0F);

        double endX, endY, endZ;
        double actualDist;

        if (mop != null) {
            endX = mop.hitVec.xCoord;
            endY = mop.hitVec.yCoord;
            endZ = mop.hitVec.zCoord;
            actualDist = player.getDistance(endX, endY, endZ);
        } else {
            endX = player.posX + player.getLookVec().xCoord * maxDist;
            endY = player.posY + player.getEyeHeight() + player.getLookVec().yCoord * maxDist;
            endZ = player.posZ + player.getLookVec().zCoord * maxDist;
            actualDist = maxDist;
        }

        if (world.isRemote) {
            for (double i = 0; i < actualDist; i += 0.5) {
                double px = player.posX + player.getLookVec().xCoord * i;
                double py = (player.posY + player.getEyeHeight() - 0.2) + player.getLookVec().yCoord * i;
                double pz = player.posZ + player.getLookVec().zCoord * i;

                world.spawnParticle("fireworksSpark", px, py, pz, 0, 0, 0);
                if (i % 2 == 0) {
                    world.spawnParticle("reddust", px, py, pz, -1.0, 1.0, 1.0); // Голубое ядро
                }
            }
        }

        if (!world.isRemote) {
            world.newExplosion(player, endX, endY, endZ, 6.0F, true, true);

            AxisAlignedBB area = AxisAlignedBB.getBoundingBox(endX - 8, endY - 8, endZ - 8, endX + 8, endY + 8, endZ + 8);
            List entities = world.getEntitiesWithinAABB(EntityLivingBase.class, area);

            for (Object obj : entities) {
                if (obj instanceof EntityLivingBase) {
                    EntityLivingBase target = (EntityLivingBase) obj;
                    target.attackEntityFrom(DamageSource.causePlayerDamage(player), 450.0F);
                    target.setFire(5);
                }
            }

            if (player.getHeldItem() != null) {
                player.getHeldItem().damageItem(1, player);
            }
        }

        world.playSoundAtEntity(player, "ambient.weather.thunder", 2.0F, 0.5F);

        player.motionX -= player.getLookVec().xCoord * 1.5;
        player.motionZ -= player.getLookVec().zCoord * 1.5;

        if (world.isRemote) {
            player.rotationPitch -= 7.0F;
        }
    }
}
