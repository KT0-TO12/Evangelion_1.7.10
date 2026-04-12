package com.example.examplemod.render;

import com.example.examplemod.buildings.ContainerAutoCannon;
import com.example.examplemod.buildings.GuiAutoCannon;
import com.example.examplemod.buildings.TileEntityAutoCannon;
import cpw.mods.fml.common.network.IGuiHandler;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.world.World;

public class GuiHandler implements IGuiHandler {

    @Override
    public Object getServerGuiElement(int ID, EntityPlayer player, World world, int x, int y, int z) {
        if (ID == 0) { // Наш ID для автопушки
            TileEntityAutoCannon te = (TileEntityAutoCannon) world.getTileEntity(x, y, z);
            return new ContainerAutoCannon(player.inventory, te);
        }
        return null;
    }

    @Override
    public Object getClientGuiElement(int ID, EntityPlayer player, World world, int x, int y, int z) {
        if (ID == 0) {
            TileEntityAutoCannon te = (TileEntityAutoCannon) world.getTileEntity(x, y, z);
            return new GuiAutoCannon(player.inventory, te);
        }
        return null;
    }
}
