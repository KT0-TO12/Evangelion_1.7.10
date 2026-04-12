package com.example.examplemod.render;

import net.minecraft.client.model.ModelIronGolem;
import net.minecraft.client.renderer.entity.RenderLiving;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.util.ResourceLocation;
import org.lwjgl.opengl.GL11;

public class RenderSachiel extends RenderLiving {

    private static final ResourceLocation texture = new ResourceLocation("ev", "textures/model/sachiel.png");

    public RenderSachiel() {
        super(new ModelIronGolem(), 2.0F);
    }
    @Override
    protected void preRenderCallback(EntityLivingBase entity, float partialTickTime) {
        GL11.glScalef(5.0F, 5.0F, 5.0F);
    }

    @Override
    protected ResourceLocation getEntityTexture(Entity entity) {
        return texture;
    }
}
