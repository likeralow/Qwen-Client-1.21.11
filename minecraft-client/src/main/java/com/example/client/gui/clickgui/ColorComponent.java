package com.example.client.gui.clickgui;

import com.example.client.module.settings.ColorSetting;

/**
 * Компонент color настройки (выбор цвета)
 */
public class ColorComponent extends SettingComponent {
    private ColorSetting setting;
    private boolean dragging;
    
    public ColorComponent(ColorSetting setting, int x, int y, int width) {
        super(setting, x, y, width, 12);
        this.setting = setting;
        this.dragging = false;
    }
    
    @Override
    public void render(int mouseX, int mouseY, float partialTicks) {
        // Рендеринг названия настройки
        
        // Отображение текущего цвета (цветной прямоугольник справа)
        
        // Индикатор rainbow режима если включен
    }
    
    @Override
    public void mouseClicked(int mouseX, int mouseY, int mouseButton) {
        if (isMouseOver(mouseX, mouseY)) {
            if (mouseButton == 0) {
                dragging = true;
                updateColor(mouseX);
            } else if (mouseButton == 1) {
                // ПКМ - переключение rainbow режима
                setting.setRainbow(!setting.isRainbow());
            }
        }
    }
    
    @Override
    public void mouseReleased(int mouseX, int mouseY, int state) {
        dragging = false;
    }
    
    @Override
    public void mouseClickMove(int mouseX, int mouseY, int clickedMouseButton, long timeSinceLastClick) {
        if (dragging) {
            updateColor(mouseX);
        }
    }
    
    @Override
    public void keyTyped(char typedChar, int keyCode) {}
    
    private void updateColor(int mouseX) {
        // Простая реализация - изменение только одного канала
        // Для полноценного выбора цвета нужен цветовой picker
        float ratio = (float)(mouseX - x) / (float)width;
        ratio = Math.max(0.0f, Math.min(1.0f, ratio));
        
        int colorValue = (int)(ratio * 255);
        // Здесь можно обновлять R, G или B канал
        // Для простоты обновляем все каналы одинаково (оттенки серого)
        // В полной реализации нужен отдельный GUI для выбора цвета
    }
}
