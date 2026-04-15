package com.example.examplemod;

import com.example.examplemod.buildings.ContainerAutoCannon;
import com.example.examplemod.buildings.GuiAutoCannon;
import com.example.examplemod.buildings.TileEntityAutoCannon;
import com.example.examplemod.buildings.drunium_colaider.ContainerDruniumCollider;
import com.example.examplemod.buildings.drunium_colaider.GuiDruniumCollider;
import com.example.examplemod.buildings.drunium_colaider.TileEntityDruniumSource;

import cpw.mods.fml.common.network.IGuiHandler;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;

public class GuiHandler implements IGuiHandler {

    public static final int AUTO_CANNON_ID = 0;
    public static final int DRUN_COLLIDER_ID = 1;

    @Override
    public Object getServerGuiElement(int ID, EntityPlayer player, World world, int x, int y, int z) {
        TileEntity te = world.getTileEntity(x, y, z);

        if (ID == AUTO_CANNON_ID) {
            if (te instanceof TileEntityAutoCannon) {
                return new ContainerAutoCannon(player.inventory, (TileEntityAutoCannon) te);
            }
        }

        if (ID == DRUN_COLLIDER_ID) {
            if (te instanceof TileEntityDruniumSource) {
                return new ContainerDruniumCollider(player.inventory, (TileEntityDruniumSource) te);
            }
        }

        return null;
    }

    @Override
    public Object getClientGuiElement(int ID, EntityPlayer player, World world, int x, int y, int z) {
        TileEntity te = world.getTileEntity(x, y, z);

        if (ID == AUTO_CANNON_ID) {
            if (te instanceof TileEntityAutoCannon) {
                return new GuiAutoCannon(player.inventory, (TileEntityAutoCannon) te);
            }
        }

        if (ID == DRUN_COLLIDER_ID) {
            if (te instanceof TileEntityDruniumSource) {
                return new GuiDruniumCollider(player.inventory, (TileEntityDruniumSource) te);
            }
        }

        return null;
    }
}
