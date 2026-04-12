package com.example.examplemod.impact;

import com.example.examplemod.ModWeapons;
import cpw.mods.fml.common.FMLCommonHandler;
import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import cpw.mods.fml.common.gameevent.TickEvent;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.client.Minecraft;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.ChatComponentTranslation;
import net.minecraft.util.ChatStyle;
import net.minecraft.util.EnumChatFormatting;
import net.minecraft.world.World;
import net.minecraftforge.event.CommandEvent;
import net.minecraftforge.event.entity.living.LivingEvent.LivingUpdateEvent;

import java.util.List;

public class ImpactHandler {

    public static boolean thirdImpactStarted = false;
    private int impactTimer = 0;
    private static final int TIME_UNTIL_THE_END = 2400;

    @SubscribeEvent
    public void onLivingUpdate(LivingUpdateEvent event) {
        if (thirdImpactStarted) {
            EntityLivingBase entity = event.entityLiving;
            if (!(entity instanceof EntityPlayer) && !entity.isDead) {
                if (!entity.worldObj.isRemote) {
                    entity.entityDropItem(new ItemStack(Items.dye, 1, 14), 0.5F);
                }
                entity.setHealth(0.0F);
            }
        }
    }

    @SubscribeEvent
    public void onPlayerTick(TickEvent.PlayerTickEvent event) {
        if (event.phase == TickEvent.Phase.END) {
            EntityPlayer player = event.player;
            World world = player.worldObj;

            if (!thirdImpactStarted) {
                checkLances(player);
            } else {
                player.addPotionEffect(new PotionEffect(Potion.confusion.id, 160, 0, true));
                if (!world.isRemote && world.rand.nextInt(15) == 0) {
                    dissolveWorld(player);
                }
            }
        }
    }

    private void dissolveWorld(EntityPlayer player) {
        int x = (int)player.posX + player.worldObj.rand.nextInt(10) - 5;
        int z = (int)player.posZ + player.worldObj.rand.nextInt(10) - 5;
        int y = (int)player.posY - 1;
        player.worldObj.setBlock(x, y, z, Blocks.air);
    }

    @SubscribeEvent
    public void onWorldTick(TickEvent.WorldTickEvent event) {
        if (thirdImpactStarted && !event.world.isRemote) {
            event.world.setWorldTime(18000);

            impactTimer++;
            if (impactTimer >= TIME_UNTIL_THE_END) {
                executeFinalSequence(event.world);
            }
        }
    }
    private void executeFinalSequence(World world) {
        thirdImpactStarted = false;

        if (FMLCommonHandler.instance().getSide() == Side.CLIENT) {
            openCredits();
        }
        List<EntityPlayer> players = world.playerEntities;
        for (EntityPlayer p : players) {
            p.mountEntity(null);
            if (p instanceof net.minecraft.entity.player.EntityPlayerMP) {
                ((net.minecraft.entity.player.EntityPlayerMP)p).playerNetServerHandler.kickPlayerFromServer("Мир растворился.");
            }
        }
    }

    @SideOnly(Side.CLIENT)
    private void openCredits() {
        Minecraft.getMinecraft().displayGuiScreen(new GuiImpactCredits());
    }

    private void checkLances(EntityPlayer player) {
        int count = 0;
        for (ItemStack stack : player.inventory.mainInventory) {
            if (stack != null && stack.getItem() == ModWeapons.lance_longinus) {
                count += stack.stackSize;
            }
        }
        if (count >= 2) triggerThirdImpact(player);
    }

    private void triggerThirdImpact(EntityPlayer player) {
        thirdImpactStarted = true;
        World world = player.worldObj;

        if (!world.isRemote) {
            world.setWorldTime(18000);
            world.getWorldInfo().setRaining(true);
            world.getWorldInfo().setThundering(true);
        }

        for (Object obj : world.playerEntities) {
            EntityPlayer p = (EntityPlayer) obj;
            p.addChatMessage(new ChatComponentTranslation("msg.ev.impact_start")
                    .setChatStyle(new ChatStyle().setColor(EnumChatFormatting.DARK_RED)));
            world.playSoundAtEntity(p, "evangelion:third_impact_song", 1.0F, 1.0F);
        }
    }

    @SubscribeEvent
    public void onCommand(CommandEvent event) {
        if (thirdImpactStarted) event.setCanceled(true);
    }
}