package com.example.client.gui.clickgui;

import com.example.client.module.settings.FloatSetting;

/**
 * Компонент float настройки (слайдер)
 */
public class FloatComponent extends SettingComponent {
    private FloatSetting setting;
    private boolean dragging;
    
    public FloatComponent(FloatSetting setting, int x, int y, int width) {
        super(setting, x, y, width, 12);
        this.setting = setting;
        this.dragging = false;
    }
    
    @Override
    public void render(int mouseX, int mouseY, float partialTicks) {
        // Рендеринг названия настройки и текущего значения
        
        // Рендеринг слайдера (полоска с ползунком)
        // Позиция ползунка зависит от значения
    }
    
    @Override
    public void mouseClicked(int mouseX, int mouseY, int mouseButton) {
        if (mouseButton == 0 && isMouseOver(mouseX, mouseY)) {
            dragging = true;
            updateValue(mouseX);
        }
    }
    
    @Override
    public void mouseReleased(int mouseX, int mouseY, int state) {
        dragging = false;
    }
    
    @Override
    public void mouseClickMove(int mouseX, int mouseY, int clickedMouseButton, long timeSinceLastClick) {
        if (dragging) {
            updateValue(mouseX);
        }
    }
    
    @Override
    public void keyTyped(char typedChar, int keyCode) {}
    
    private void updateValue(int mouseX) {
        float ratio = (float)(mouseX - x) / (float)width;
        ratio = Math.max(0.0f, Math.min(1.0f, ratio));
        
        float newValue = setting.getMin() + ratio * (setting.getMax() - setting.getMin());
        setting.setValue(newValue);
    }
}
