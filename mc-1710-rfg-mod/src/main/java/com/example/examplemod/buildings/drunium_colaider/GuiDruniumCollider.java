package com.example.examplemod.buildings.drunium_colaider;

import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.gui.inventory.GuiContainer;
import net.minecraft.entity.player.InventoryPlayer;
import org.lwjgl.opengl.GL11;

public class GuiDruniumCollider extends GuiContainer {
    private TileEntityDruniumSource te;

    public GuiDruniumCollider(InventoryPlayer inv, TileEntityDruniumSource te) {
        super(new ContainerDruniumCollider(inv, te));
        this.te = te;
    }

    @Override
    public void initGui() {
        super.initGui();
        // Добавляем кнопку "START"
        this.buttonList.add(new GuiButton(1, guiLeft + 60, guiTop + 50, 50, 20, "START"));
    }

    @Override
    protected void actionPerformed(GuiButton button) {
        if (button.id == 1) {
            // Отправляем пакет на сервер, что кнопка нажата
            // В 1.7.10 проще всего использовать:
            this.mc.playerController.windowClick(this.inventorySlots.windowId, -999, 1, 4, this.mc.thePlayer);
        }
    }

    @Override
    protected void drawGuiContainerBackgroundLayer(float p, int mX, int mY) {
        int c = (int)(te.cooling * 50 / 15000);
        this.drawTexturedModalRect(guiLeft + 10, guiTop + 10, 0, 200, c, 10);
    }
}
