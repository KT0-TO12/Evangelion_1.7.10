package com.example.examplemod.buildings;

import com.example.examplemod.ExampleMod;
import net.minecraft.block.BlockContainer;
import net.minecraft.block.material.Material;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;

public class BlockAutoCannon extends BlockContainer {

    public BlockAutoCannon() {
        super(Material.iron);
        this.setBlockName("auto_cannon");
        this.setCreativeTab(CreativeTabs.tabCombat);
        this.setHardness(5.0F);
        this.setResistance(10.0F);
        // Устанавливаем размер хитбокса (1x1x1 блок)
        this.setBlockBounds(0.0F, 0.0F, 0.0F, 1.0F, 1.0F, 1.0F);
    }

    @Override
    public TileEntity createNewTileEntity(World world, int meta) {
        return new TileEntityAutoCannon();
    }

    // Открываем GUI при клике правой кнопкой мыши
    @Override
    public boolean onBlockActivated(World world, int x, int y, int z, EntityPlayer player, int side, float hitX, float hitY, float hitZ) {
        if (!world.isRemote) {
            // ID 0 — это будет наш GuiAutoCannon
            player.openGui(ExampleMod.instance, 0, world, x, y, z);
        }
        return true;
    }

    @Override
    public int getRenderType() {
        return -1;
    }

    @Override
    public boolean isOpaqueCube() {
        return false;
    }

    @Override
    public boolean renderAsNormalBlock() {
        return false;
    }
}
