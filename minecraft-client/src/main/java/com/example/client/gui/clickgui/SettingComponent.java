package com.example.client.gui.clickgui;

import com.example.client.module.settings.Setting;

/**
 * Базовый класс для компонентов настроек
 */
public abstract class SettingComponent {
    protected Setting<?> setting;
    protected int x, y, width, height;
    
    public SettingComponent(Setting<?> setting, int x, int y, int width, int height) {
        this.setting = setting;
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
    }
    
    public abstract void render(int mouseX, int mouseY, float partialTicks);
    
    public abstract void mouseClicked(int mouseX, int mouseY, int mouseButton);
    
    public abstract void mouseReleased(int mouseX, int mouseY, int state);
    
    public abstract void mouseClickMove(int mouseX, int mouseY, int clickedMouseButton, long timeSinceLastClick);
    
    public abstract void keyTyped(char typedChar, int keyCode);
    
    public int getHeight() {
        return height;
    }
    
    public void updatePosition(int newX, int newY) {
        this.x = newX;
        this.y = newY;
    }
    
    public boolean isMouseOver(int mouseX, int mouseY) {
        return mouseX >= x && mouseX <= x + width &&
               mouseY >= y && mouseY <= y + height;
    }
}
