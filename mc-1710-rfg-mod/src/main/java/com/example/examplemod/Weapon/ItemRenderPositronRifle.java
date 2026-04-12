package com.example.examplemod.Weapon;

import net.minecraft.client.Minecraft;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.client.IItemRenderer;
import net.minecraftforge.client.model.AdvancedModelLoader;
import net.minecraftforge.client.model.IModelCustom;
import org.lwjgl.opengl.GL11;

public class ItemRenderPositronRifle implements IItemRenderer {

    private final IModelCustom model;
    private final ResourceLocation texture;

    public ItemRenderPositronRifle() {
        this.model = AdvancedModelLoader.loadModel(new ResourceLocation("ev", "models/item/positron_rifle.obj"));
        this.texture = new ResourceLocation("ev", "textures/model/positron_rifle.png");
    }

    @Override
    public boolean handleRenderType(ItemStack item, ItemRenderType type) {
        return true;
    }

    @Override
    public boolean shouldUseRenderHelper(ItemRenderType type, ItemStack item, ItemRendererHelper helper) {
        return true;
    }

    @Override
    public void renderItem(ItemRenderType type, ItemStack item, Object... data) {
        GL11.glPushMatrix();

        Minecraft.getMinecraft().renderEngine.bindTexture(texture);
        GL11.glEnable(GL11.GL_BLEND);
        GL11.glBlendFunc(GL11.GL_SRC_ALPHA, GL11.GL_ONE_MINUS_SRC_ALPHA);
        if (type == ItemRenderType.EQUIPPED_FIRST_PERSON) {
            GL11.glRotatef(-45.0F, 0, 1, 0);
            GL11.glTranslated(0.7, 0.2, 0.4);
            GL11.glScalef(1.5F, 1.5F, 2.5F);
        }

        else if (type == ItemRenderType.EQUIPPED) {
            GL11.glScalef(1.5F, 1.5F, 1.5F);
            GL11.glTranslated(0.4, 0.2, 0.2);
            GL11.glRotatef(180.0F, 0, 1, 0);
        }
        else if (type == ItemRenderType.INVENTORY) {

            GL11.glScalef(0.8F, 0.8F, 0.8F);
            GL11.glTranslated(0, -0.5, 0);
            GL11.glRotatef(90, 0, 1, 0);
        }

        this.model.renderAll();

        GL11.glDisable(GL11.GL_BLEND);
        GL11.glPopMatrix();
    }
}
