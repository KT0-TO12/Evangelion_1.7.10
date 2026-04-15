package com.example.examplemod.buildings.drunium_colaider;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.inventory.Container;
import net.minecraft.inventory.Slot;
import net.minecraft.item.ItemStack;

public class ContainerDruniumCollider extends Container {
    private TileEntityDruniumSource te;

    public ContainerDruniumCollider(InventoryPlayer playerInv, TileEntityDruniumSource te) {
        this.te = te;

        this.addSlotToContainer(new Slot(te, 0, 44, 15));
        this.addSlotToContainer(new Slot(te, 1, 44, 36));
        this.addSlotToContainer(new Slot(te, 2, 80, 56));
        this.addSlotToContainer(new Slot(te, 3, 116, 25));

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
    public boolean canInteractWith(EntityPlayer player) {
        return te.isUseableByPlayer(player);
    }

    @Override
    public ItemStack transferStackInSlot(EntityPlayer player, int slotIndex) {
        ItemStack itemstack = null;
        Slot slot = (Slot)this.inventorySlots.get(slotIndex);

        if (slot != null && slot.getHasStack()) {
            ItemStack itemstack1 = slot.getStack();
            itemstack = itemstack1.copy();

            if (slotIndex < 4) {
                if (!this.mergeItemStack(itemstack1, 4, 40, true)) {
                    return null;
                }
            } else {
                if (!this.mergeItemStack(itemstack1, 0, 2, false)) {
                    return null;
                }
            }

            if (itemstack1.stackSize == 0) {
                slot.putStack((ItemStack)null);
            } else {
                slot.onSlotChanged();
            }
        }
        return itemstack;
    }
}
