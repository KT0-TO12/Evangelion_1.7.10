package com.example.examplemod.buildings.drunium_colaider;

import com.example.examplemod.ints.drunoviycolaider;
import net.minecraft.client.gui.inventory.GuiContainer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.util.ResourceLocation;
import org.lwjgl.opengl.GL11;

public class GuiDruniumCollider extends GuiContainer {

    private static final ResourceLocation texture = new ResourceLocation("ev", "textures/gui/drun_collider.png");
    private TileEntityDruniumSource te;

    public GuiDruniumCollider(InventoryPlayer inv, TileEntityDruniumSource te) {
        super(new ContainerDruniumCollider(inv, te));
        this.te = te;
        this.xSize = 176;
        this.ySize = 166;
    }

    @Override
    protected void drawGuiContainerBackgroundLayer(float partialTicks, int mouseX, int mouseY) {
        GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
        this.mc.getTextureManager().bindTexture(texture);

        int k = (this.width - this.xSize) / 2;
        int l = (this.height - this.ySize) / 2;
        this.drawTexturedModalRect(k, l, 0, 0, this.xSize, this.ySize);

        int coolingLevel = getScaledLevel(te.drunentum_cooling, drunoviycolaider.drunentum_cooling_max, 12);
        this.drawTexturedModalRect(k + 25, l + 18, 176, coolingLevel * 16, 18, 18);
        int drunLevel = getScaledLevel(te.currentDrunentum, drunoviycolaider.drunentum_max, 12);
        this.drawTexturedModalRect(k + 50, l + 18, 176, drunLevel * 16, 18, 18);
        if (te.drunentum_cooling > 0) {
            this.drawTexturedModalRect(k + 27, l + 38, 176, 0, 14, 14);
        }
    }

    private int getScaledLevel(int current, int max, int levels) {
        if (current <= 0 || max <= 0) return 0;
        int level = current * levels / max;
        return level >= levels ? levels - 1 : level;
    }
}
