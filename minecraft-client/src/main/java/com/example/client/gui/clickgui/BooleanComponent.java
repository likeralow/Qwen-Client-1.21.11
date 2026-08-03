package com.example.client.gui.clickgui;

import com.example.client.module.settings.BooleanSetting;

/**
 * Компонент булевой настройки (checkbox)
 */
public class BooleanComponent extends SettingComponent {
    private BooleanSetting setting;
    
    public BooleanComponent(BooleanSetting setting, int x, int y, int width) {
        super(setting, x, y, width, 12);
        this.setting = setting;
    }
    
    @Override
    public void render(int mouseX, int mouseY, float partialTicks) {
        // Рендеринг названия настройки
        
        // Рендеринг checkbox (квадратик справа)
        // Если включено - закрашен, если выключено - пустой
    }
    
    @Override
    public void mouseClicked(int mouseX, int mouseY, int mouseButton) {
        if (mouseButton == 0 && isMouseOver(mouseX, mouseY)) {
            setting.toggle();
        }
    }
    
    @Override
    public void mouseReleased(int mouseX, int mouseY, int state) {}
    
    @Override
    public void mouseClickMove(int mouseX, int mouseY, int clickedMouseButton, long timeSinceLastClick) {}
    
    @Override
    public void keyTyped(char typedChar, int keyCode) {}
}
