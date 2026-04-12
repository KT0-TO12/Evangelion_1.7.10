package com.example.examplemod.buildings;

import com.example.examplemod.ModItems;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.monster.IMob;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.IInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.AxisAlignedBB;
import java.util.List;

public class TileEntityAutoCannon extends TileEntity implements IInventory {
    private ItemStack[] inventory = new ItemStack[2];
    private int fireCooldown = 0;

    @Override
    public void updateEntity() {
        if (worldObj.isRemote) return;

        if (fireCooldown > 0) fireCooldown--;

        if (hasResources()) {
            List targets = worldObj.getEntitiesWithinAABB(EntityLivingBase.class,
                    AxisAlignedBB.getBoundingBox(xCoord-30, yCoord-10, zCoord-30, xCoord+30, yCoord+10, zCoord+30));

            for (Object obj : targets) {
                EntityLivingBase target = (EntityLivingBase) obj;
                if (target instanceof IMob && !target.isDead && fireCooldown <= 0) {
                    shoot(target);
                    consumeResources();
                    fireCooldown = 10;
                    break;
                }
            }
        }
    }

    private boolean hasResources() {
        return inventory[0] != null && inventory[0].getItem() == ModItems.battery &&
                inventory[1] != null && inventory[1].getItem() == ModItems.nail;
    }

    private void consumeResources() {
        this.decrStackSize(0, 1);
        this.decrStackSize(1, 1);
    }

    private void shoot(EntityLivingBase target) {
        worldObj.playSoundEffect(xCoord, yCoord, zCoord, "random.explode", 0.3F, 1.5F);
        target.attackEntityFrom(net.minecraft.util.DamageSource.generic, 5.0F);
    }

    // Методы IInventory (обязательно для GUI)
    @Override public int getSizeInventory() { return inventory.length; }
    @Override public ItemStack getStackInSlot(int i) { return inventory[i]; }
    @Override public String getInventoryName() { return "Auto-Cannon"; }
    @Override public int getInventoryStackLimit() { return 64; }
    @Override public boolean isUseableByPlayer(EntityPlayer p) { return true; }
    @Override public void openInventory() {}
    @Override public void closeInventory() {}
    @Override public boolean hasCustomInventoryName() { return false; }
    @Override public boolean isItemValidForSlot(int i, ItemStack s) { return true; }

    @Override
    public ItemStack decrStackSize(int i, int count) {
        if (inventory[i] != null) {
            ItemStack itemstack;
            if (inventory[i].stackSize <= count) {
                itemstack = inventory[i];
                inventory[i] = null;
                return itemstack;
            } else {
                itemstack = inventory[i].splitStack(count);
                if (inventory[i].stackSize == 0) inventory[i] = null;
                return itemstack;
            }
        }
        return null;
    }

    @Override
    public ItemStack getStackInSlotOnClosing(int i) { return inventory[i]; }
    @Override
    public void setInventorySlotContents(int i, ItemStack s) { inventory[i] = s; }

    // Сохранение инвентаря при перезагрузке мира
    @Override
    public void writeToNBT(NBTTagCompound nbt) {
        super.writeToNBT(nbt);
        NBTTagList list = new NBTTagList();
        for (int i = 0; i < inventory.length; i++) {
            if (inventory[i] != null) {
                NBTTagCompound slot = new NBTTagCompound();
                slot.setByte("Slot", (byte)i);
                inventory[i].writeToNBT(slot);
                list.appendTag(slot);
            }
        }
        nbt.setTag("Items", list);
    }

    @Override
    public void readFromNBT(NBTTagCompound nbt) {
        super.readFromNBT(nbt);
        NBTTagList list = nbt.getTagList("Items", 10);
        for (int i = 0; i < list.tagCount(); i++) {
            NBTTagCompound slot = list.getCompoundTagAt(i);
            int j = slot.getByte("Slot");
            if (j >= 0 && j < inventory.length) inventory[j] = ItemStack.loadItemStackFromNBT(slot);
        }
    }
}
