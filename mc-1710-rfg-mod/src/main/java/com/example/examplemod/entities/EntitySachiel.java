package com.example.examplemod.entities;

import com.example.examplemod.ModWeapons;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.ai.*;
import net.minecraft.entity.monster.EntityMob;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.item.ItemStack;
import net.minecraft.util.DamageSource;
import net.minecraft.util.MathHelper;
import net.minecraft.world.World;

public class EntitySachiel extends EntityMob {
    private int eyeAttackTimer = 0;

    public EntitySachiel(World world) {
        super(world);
        this.setSize(4.0F, 12.0F);
        this.experienceValue = 1000;
        this.tasks.addTask(1, new EntityAISwimming(this));
        this.tasks.addTask(2, new EntityAIAttackOnCollide(this, 1.0D, false));
        this.tasks.addTask(3, new EntityAIWander(this, 0.8D));
        this.targetTasks.addTask(1, new EntityAIHurtByTarget(this, false));
        this.targetTasks.addTask(2, new EntityAINearestAttackableTarget(this, EntityPlayer.class, 0, true));
    }
    @Override
    public float getEyeHeight() {
        return 10.5F;
    }
    @Override
    protected void applyEntityAttributes() {
        super.applyEntityAttributes();
        this.getEntityAttribute(SharedMonsterAttributes.maxHealth).setBaseValue(400.0D);
        this.getEntityAttribute(SharedMonsterAttributes.movementSpeed).setBaseValue(0.3D);
        this.getEntityAttribute(SharedMonsterAttributes.attackDamage).setBaseValue(30.0D);
        this.getEntityAttribute(SharedMonsterAttributes.followRange).setBaseValue(128.0D);
    }

    @Override
    public boolean attackEntityFrom(DamageSource source, float amount) {
        if (source.getEntity() instanceof EntityPlayer) {
            EntityPlayer player = (EntityPlayer) source.getEntity();
            ItemStack held = player.getHeldItem();
            boolean canPierce = held != null && (held.getItem() == ModWeapons.prog_knife ||
                    held.getItem() == ModWeapons.positron_rifle ||
                    held.getItem() == ModWeapons.lance_longinus);
            if (!canPierce) {
                this.worldObj.playSoundAtEntity(this, "minecart.base", 1.5F, 0.5F);
                return false;
            }
        }
        return super.attackEntityFrom(source, amount);
    }

    @Override
    public void onLivingUpdate() {
        super.onLivingUpdate();
        if (!this.worldObj.isRemote) {
            if (this.isCollidedHorizontally) destroyBlocks();

            EntityLivingBase target = this.getAttackTarget();
            if (target != null) {
                eyeAttackTimer++;
                if (eyeAttackTimer >= 80 && this.getDistanceSqToEntity(target) > 30) {
                    worldObj.newExplosion(this, target.posX, target.posY, target.posZ, 4F, false, false);
                    target.attackEntityFrom(DamageSource.causeMobDamage(this), 20F);
                    eyeAttackTimer = 0;
                }
            }
        }
    }

    private void destroyBlocks() {
        for (int x = -3; x <= 3; x++)
            for (int z = -3; z <= 3; z++)
                for (int y = 0; y <= 6; y++) {
                    int bx = MathHelper.floor_double(posX + x);
                    int by = MathHelper.floor_double(posY + y);
                    int bz = MathHelper.floor_double(posZ + z);
                    if (worldObj.getBlock(bx, by, bz) != Blocks.bedrock) worldObj.setBlockToAir(bx, by, bz);
                }
    }

    @Override
    protected boolean isAIEnabled() { return true; }
}