package com.example.examplemod.render;

import net.minecraft.client.renderer.tileentity.TileEntitySpecialRenderer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.client.model.AdvancedModelLoader;
import net.minecraftforge.client.model.IModelCustom;
import org.lwjgl.opengl.GL11;

public class RenderAutoCannon extends TileEntitySpecialRenderer {
    private final IModelCustom model;
    private final ResourceLocation texture;

    public RenderAutoCannon() {
        model = AdvancedModelLoader.loadModel(new ResourceLocation("ev", "models/block/auto_cannon.obj"));
        texture = new ResourceLocation("ev", "textures/model/auto_cannon.png");

    }

    @Override
    public void renderTileEntityAt(TileEntity te, double x, double y, double z, float partialTicks) {
        GL11.glPushMatrix();
        GL11.glTranslated(x + 0.5, y, z + 0.5);

        this.bindTexture(texture);
        model.renderAll();
        GL11.glPopMatrix();
    }
}
