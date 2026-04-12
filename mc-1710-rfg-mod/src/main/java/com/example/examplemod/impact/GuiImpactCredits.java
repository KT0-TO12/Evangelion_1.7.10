package com.example.examplemod.impact;

import net.minecraft.client.gui.GuiScreen;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.util.ResourceLocation;
import org.lwjgl.opengl.GL11;

public class GuiImpactCredits extends GuiScreen {
    private int timer = 0;
    private int currentFrame = 0;
    private float alpha = 0.0F;
    private boolean fadingIn = true;
    private boolean musicStarted = false;

    private static final ResourceLocation[] frames = {
            new ResourceLocation("evangelion", "textures/gui/flashback/flash_1.png"),
            new ResourceLocation("evangelion", "textures/gui/flashback/flash_2.png"),
            new ResourceLocation("evangelion", "textures/gui/flashback/flash_3.png"),
            new ResourceLocation("evangelion", "textures/gui/flashback/flash_4.png"),
            new ResourceLocation("evangelion", "textures/gui/flashback/flash_5.png"),
            new ResourceLocation("evangelion", "textures/gui/flashback/flash_6.png"),
            new ResourceLocation("evangelion", "textures/gui/flashback/flash_7.png"),
            new ResourceLocation("evangelion", "textures/gui/flashback/flash_8.png")
    };

    @Override
    public void initGui() {
        if (!musicStarted) {
            this.mc.thePlayer.playSound("evangelion:credits_theme", 1.0F, 1.0F);
            musicStarted = true;
        }
    }

    @Override
    protected void keyTyped(char typedChar, int keyCode) {
        if (keyCode == 1) {
        } else {
            super.keyTyped(typedChar, keyCode);
        }
    }

    @Override
    public void drawScreen(int mouseX, int mouseY, float partialTicks) {
        drawRect(0, 0, this.width, this.height, 0xFF000000);
        timer++;

        if (fadingIn) {
            alpha += 0.015F;
            if (alpha >= 1.0F) {
                alpha = 1.0F;
                if (timer % 50 == 0) fadingIn = false;
            }
        } else {
            alpha -= 0.015F;
            if (alpha <= 0.0F) {
                alpha = 0.0F;
                if (currentFrame < frames.length - 1) {
                    currentFrame++;
                    fadingIn = true;
                } else if (timer > 640) {
                    this.mc.shutdown();
                }
            }
        }

        this.mc.getTextureManager().bindTexture(frames[currentFrame]);
        GL11.glEnable(GL11.GL_BLEND);
        GL11.glBlendFunc(GL11.GL_SRC_ALPHA, GL11.GL_ONE_MINUS_SRC_ALPHA);
        GL11.glColor4f(1.0F, 1.0F, 1.0F, alpha);

        drawFullScreenTexture(0, 0, this.width, this.height);
        GL11.glDisable(GL11.GL_BLEND);
        super.drawScreen(mouseX, mouseY, partialTicks);
    }

    private void drawFullScreenTexture(int x, int y, int width, int height) {
        Tessellator tessellator = Tessellator.instance;
        tessellator.startDrawingQuads();
        tessellator.addVertexWithUV(x, y + height, 0, 0, 1);
        tessellator.addVertexWithUV(x + width, y + height, 0, 1, 1);
        tessellator.addVertexWithUV(x + width, y, 0, 1, 0);
        tessellator.addVertexWithUV(x, y, 0, 0, 0);
        tessellator.draw();
    }

    @Override
    public boolean doesGuiPauseGame() { return true; }
}