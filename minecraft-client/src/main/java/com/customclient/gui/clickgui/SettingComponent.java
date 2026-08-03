package com.customclient.gui.clickgui;

import net.minecraft.client.gui.DrawContext;

/**
 * Базовый класс для компонентов настроек
 */
public abstract class SettingComponent {
    protected int x, y, width, height;
    
    public SettingComponent(int x, int y, int width, int height) {
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
    }
    
    public abstract void render(DrawContext context, int mouseX, int mouseY, float delta);
    
    public abstract boolean mouseClicked(double mouseX, double mouseY, int button);
    
    public void mouseReleased(double mouseX, double mouseY, int button) {}
    
    public boolean mouseDragged(double mouseX, double mouseY, int button, double deltaX, double deltaY) {
        return false;
    }
    
    public void setPosition(int x, int y) {
        this.x = x;
        this.y = y;
    }
    
    public int getX() { return x; }
    public int getY() { return y; }
    public int getWidth() { return width; }
    public int getHeight() { return height; }
}
