package com.example.examplemod.buildings;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.inventory.Container;
import net.minecraft.inventory.Slot;
import net.minecraft.item.ItemStack;

public class ContainerAutoCannon extends Container {
    private TileEntityAutoCannon te;

    public ContainerAutoCannon(InventoryPlayer playerInv, TileEntityAutoCannon te) {
        this.te = te;
        this.addSlotToContainer(new Slot(te, 0, 56, 35));
        this.addSlotToContainer(new Slot(te, 1, 104, 35));

        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 9; j++) {
                this.addSlotToContainer(new Slot(playerInv, j + i * 9 + 9, 8 + j * 18, 84 + i * 18));
            }
        }
        for (int i = 0; i < 9; i++) {
            this.addSlotToContainer(new Slot(playerInv, i, 8 + i * 18, 142));
        }
    }

    @Override
    public boolean canInteractWith(EntityPlayer p) { return te.isUseableByPlayer(p); }

    @Override
    public ItemStack transferStackInSlot(EntityPlayer p, int i) {
        // Логика Shift-Click (пока оставим пустой для простоты)
        return null;
    }
}
