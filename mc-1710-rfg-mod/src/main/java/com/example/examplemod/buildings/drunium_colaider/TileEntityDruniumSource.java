package com.example.examplemod.buildings.drunium_colaider;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.IInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;
import net.minecraft.tileentity.TileEntity;
import net.minecraftforge.common.util.Constants;

public class TileEntityDruniumSource extends TileEntity implements IInventory {
    public int currentDrunentum = 0;
    public int cooling = 0;

    private ItemStack[] inventory = new ItemStack[4];

    @Override
    public void updateEntity() {
        if (worldObj.isRemote) return;
    }

    @Override
    public int getSizeInventory() {
        return inventory.length;
    }

    @Override
    public ItemStack getStackInSlot(int slot) {
        return inventory[slot];
    }

    @Override
    public ItemStack decrStackSize(int slot, int amount) {
        if (inventory[slot] != null) {
            ItemStack itemstack;
            if (inventory[slot].stackSize <= amount) {
                itemstack = inventory[slot];
                inventory[slot] = null;
                return itemstack;
            } else {
                itemstack = inventory[slot].splitStack(amount);
                if (inventory[slot].stackSize == 0) inventory[slot] = null;
                return itemstack;
            }
        }
        return null;
    }

    @Override
    public ItemStack getStackInSlotOnClosing(int slot) {
        if (inventory[slot] != null) {
            ItemStack itemstack = inventory[slot];
            inventory[slot] = null;
            return itemstack;
        }
        return null;
    }

    @Override
    public void setInventorySlotContents(int slot, ItemStack stack) {
        inventory[slot] = stack;
        if (stack != null && stack.stackSize > getInventoryStackLimit()) {
            stack.stackSize = getInventoryStackLimit();
        }
        markDirty();
    }

    @Override
    public String getInventoryName() {
        return "container.drun_collider";
    }

    @Override
    public boolean hasCustomInventoryName() {
        return false;
    }

    @Override
    public int getInventoryStackLimit() {
        return 64;
    }

    @Override
    public boolean isUseableByPlayer(EntityPlayer player) {
        return worldObj.getTileEntity(xCoord, yCoord, zCoord) == this &&
                player.getDistanceSq(xCoord + 0.5D, yCoord + 0.5D, zCoord + 0.5D) <= 64.0D;
    }

    @Override
    public void openInventory() {}

    @Override
    public void closeInventory() {}

    @Override
    public boolean isItemValidForSlot(int slot, ItemStack stack) {
        return true;
    }


    @Override
    public void writeToNBT(NBTTagCompound nbt) {
        super.writeToNBT(nbt);
        nbt.setInteger("Drun", currentDrunentum);
        nbt.setInteger("Cooling", cooling);

        NBTTagList list = new NBTTagList();
        for (int i = 0; i < inventory.length; ++i) {
            if (inventory[i] != null) {
                NBTTagCompound tag = new NBTTagCompound();
                tag.setByte("Slot", (byte) i);
                inventory[i].writeToNBT(tag);
                list.appendTag(tag);
            }
        }
        nbt.setTag("Items", list);
    }

    @Override
    public void readFromNBT(NBTTagCompound nbt) {
        super.readFromNBT(nbt);
        this.currentDrunentum = nbt.getInteger("Drun");
        this.cooling = nbt.getInteger("Cooling");

        NBTTagList list = nbt.getTagList("Items", Constants.NBT.TAG_COMPOUND);
        inventory = new ItemStack[getSizeInventory()];
        for (int i = 0; i < list.tagCount(); ++i) {
            NBTTagCompound tag = list.getCompoundTagAt(i);
            int slot = tag.getByte("Slot") & 255;
            if (slot >= 0 && slot < inventory.length) {
                inventory[slot] = ItemStack.loadItemStackFromNBT(tag);
            }
        }
    }
}
