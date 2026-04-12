package com.example.examplemod.client.render;

import net.minecraft.client.renderer.entity.RenderLiving;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.client.model.AdvancedModelLoader;
import net.minecraftforge.client.model.IModelCustom;
import org.lwjgl.opengl.GL11;

public class RenderEva01 extends RenderLiving {
    private final IModelCustom model;
    private final ResourceLocation texture;

    public RenderEva01() {
        // Указываем путь к OBJ Евы
        super(null, 2.0F); // Тень размером 2 блока
        this.model = AdvancedModelLoader.loadModel(new ResourceLocation("ev", "models/entity/eva01.obj"));
        this.texture = new ResourceLocation("ev", "textures/model/eva01.png");
    }

    @Override
    protected void preRenderCallback(EntityLivingBase entity, float partialTickTime) {
        // Масштабируем модель, чтобы она была реально гигантской
        GL11.glScalef(6.0F, 6.0F, 6.0F);
        // Поворачиваем, если модель стоит спиной
        GL11.glRotatef(180, 0, 1, 0);
    }

    @Override
    protected ResourceLocation getEntityTexture(Entity entity) {
        return texture;
    }

    @Override
    public void doRender(Entity entity, double x, double y, double z, float yaw, float partialTickTime) {
        GL11.glPushMatrix();
        GL11.glTranslated(x, y, z);
        this.bindTexture(texture);
        this.model.renderAll();
        GL11.glPopMatrix();
    }
}
