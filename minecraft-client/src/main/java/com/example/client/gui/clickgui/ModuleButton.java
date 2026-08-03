package com.example.client.gui.clickgui;

import com.example.client.module.Module;
import com.example.client.module.ModuleManager;
import com.example.client.module.settings.*;

import java.awt.Color;
import java.util.ArrayList;
import java.util.List;

/**
 * Компонент кнопки модуля в ClickGUI
 */
public class ModuleButton {
    private Module module;
    private int x, y, width, height;
    private boolean extended;
    private List<SettingComponent> settingComponents;
    private int extensionHeight;
    
    public ModuleButton(Module module, int x, int y, int width, int height) {
        this.module = module;
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
        this.extended = false;
        this.settingComponents = new ArrayList<>();
        this.extensionHeight = 0;
        
        // Создаём компоненты для всех настроек модуля
        int offsetY = height + 2;
        for (Setting<?> setting : module.getSettings()) {
            SettingComponent component = createSettingComponent(setting, x, y + offsetY);
            settingComponents.add(component);
            offsetY += component.getHeight() + 1;
            extensionHeight += component.getHeight() + 1;
        }
    }
    
    private SettingComponent createSettingComponent(Setting<?> setting, int x, int y) {
        if (setting instanceof BooleanSetting) {
            return new BooleanComponent((BooleanSetting) setting, x, y, width);
        } else if (setting instanceof FloatSetting) {
            return new FloatComponent((FloatSetting) setting, x, y, width);
        } else if (setting instanceof IntegerSetting) {
            return new IntegerComponent((IntegerSetting) setting, x, y, width);
        } else if (setting instanceof ModeSetting) {
            return new ModeComponent((ModeSetting) setting, x, y, width);
        } else if (setting instanceof ColorSetting) {
            return new ColorComponent((ColorSetting) setting, x, y, width);
        }
        return null;
    }
    
    public void render(int mouseX, int mouseY, float partialTicks) {
        // Рендеринг кнопки модуля
        // Фон кнопки (цвет зависит от включен/выключен)
        
        // Текст названия модуля
        
        // Индикатор состояния (вкл/выкл)
        
        // Если расширено - рендерим настройки
        if (extended) {
            for (SettingComponent component : settingComponents) {
                if (component != null) {
                    component.render(mouseX, mouseY, partialTicks);
                }
            }
        }
    }
    
    public void mouseClicked(int mouseX, int mouseY, int mouseButton) {
        // Проверка клика по кнопке модуля
        if (isMouseOver(mouseX, mouseY)) {
            if (mouseButton == 0) { // ЛКМ - включить/выключить модуль
                module.toggle();
            } else if (mouseButton == 1) { // ПКМ - расширить/свернуть
                extended = !extended;
            }
            return;
        }
        
        // Если расширено - проверяем клики по настройкам
        if (extended) {
            for (SettingComponent component : settingComponents) {
                if (component != null) {
                    component.mouseClicked(mouseX, mouseY, mouseButton);
                }
            }
        }
    }
    
    public void mouseReleased(int mouseX, int mouseY, int state) {
        if (extended) {
            for (SettingComponent component : settingComponents) {
                if (component != null) {
                    component.mouseReleased(mouseX, mouseY, state);
                }
            }
        }
    }
    
    public void mouseClickMove(int mouseX, int mouseY, int clickedMouseButton, long timeSinceLastClick) {
        if (extended) {
            for (SettingComponent component : settingComponents) {
                if (component != null) {
                    component.mouseClickMove(mouseX, mouseY, clickedMouseButton, timeSinceLastClick);
                }
            }
        }
    }
    
    public void keyTyped(char typedChar, int keyCode) {
        if (extended) {
            for (SettingComponent component : settingComponents) {
                if (component != null) {
                    component.keyTyped(typedChar, keyCode);
                }
            }
        }
    }
    
    public boolean isMouseOver(int mouseX, int mouseY) {
        int currentHeight = extended ? height + extensionHeight : height;
        return mouseX >= x && mouseX <= x + width &&
               mouseY >= y && mouseY <= y + currentHeight;
    }
    
    public int getHeight() {
        return extended ? height + extensionHeight : height;
    }
    
    public void updatePosition(int newX, int newY) {
        this.x = newX;
        this.y = newY;
        
        int offsetY = height + 2;
        for (SettingComponent component : settingComponents) {
            if (component != null) {
                component.updatePosition(newX, newY + offsetY);
                offsetY += component.getHeight() + 1;
            }
        }
    }
    
    public Module getModule() {
        return module;
    }
    
    public boolean isExtended() {
        return extended;
    }
}
