package com.example.client.gui.clickgui;

import com.example.client.module.settings.ModeSetting;

/**
 * Компонент mode настройки (выбор из списка)
 */
public class ModeComponent extends SettingComponent {
    private ModeSetting setting;
    
    public ModeComponent(ModeSetting setting, int x, int y, int width) {
        super(setting, x, y, width, 12);
        this.setting = setting;
    }
    
    @Override
    public void render(int mouseX, int mouseY, float partialTicks) {
        // Рендеринг названия настройки и текущего режима
        
        // Текущий режим отображается справа
    }
    
    @Override
    public void mouseClicked(int mouseX, int mouseY, int mouseButton) {
        if (mouseButton == 0 && isMouseOver(mouseX, mouseY)) {
            setting.nextMode(); // Переключение на следующий режим
        }
    }
    
    @Override
    public void mouseReleased(int mouseX, int mouseY, int state) {}
    
    @Override
    public void mouseClickMove(int mouseX, int mouseY, int clickedMouseButton, long timeSinceLastClick) {}
    
    @Override
    public void keyTyped(char typedChar, int keyCode) {}
}
