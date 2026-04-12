package com.example.examplemod.buildings;

import net.minecraft.client.gui.inventory.GuiContainer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.util.ResourceLocation;
import org.lwjgl.opengl.GL11;

public class GuiAutoCannon extends GuiContainer {
    private static final ResourceLocation texture = new ResourceLocation("minecraft", "textures/gui/container/generic_54.png");

    public GuiAutoCannon(InventoryPlayer playerInv, TileEntityAutoCannon te) {
        super(new ContainerAutoCannon(playerInv, te));
    }

    @Override
    protected void drawGuiContainerBackgroundLayer(float partialTicks, int mouseX, int mouseY) {
        GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
        this.mc.getTextureManager().bindTexture(texture);
        int x = (width - xSize) / 2;
        int y = (height - ySize) / 2;
        this.drawTexturedModalRect(x, y, 0, 0, xSize, ySize);
    }
}
