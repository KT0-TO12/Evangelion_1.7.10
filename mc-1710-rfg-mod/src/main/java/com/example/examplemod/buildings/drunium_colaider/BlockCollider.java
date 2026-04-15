package com.example.examplemod.buildings.drunium_colaider;

import com.example.examplemod.ExampleMod;
import net.minecraft.block.BlockContainer;
import net.minecraft.block.material.Material;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;

public class BlockCollider extends BlockContainer {
    private final int type; // 0-Источник, 1-Усилитель, 2-Датчик

    public BlockCollider(int type, String name) {
        super(Material.iron);
        this.type = type;
        this.setBlockName(name);
        this.setHardness(3.0F);
    }

    @Override
    public boolean onBlockActivated(World world, int x, int y, int z, EntityPlayer player, int side, float hitX, float hitY, float hitZ) {
        if (!world.isRemote) {
            if (this.type == 0) {

                player.openGui(ExampleMod.instance, 1, world, x, y, z);
            }
        }
        return true;
    }

    @Override
    public TileEntity createNewTileEntity(World world, int meta) {
        if (type == 0) return new TileEntityDruniumSource();
        if (type == 1) return new TileEntityDruniumAmplifier();
        if (type == 2) return new TileEntityDruniumSensor();
        return null;
    }

    @Override
    public int getRenderType() { return -1; }

    @Override
    public boolean isOpaqueCube() { return false; }

    @Override
    public boolean renderAsNormalBlock() { return false; }
}
