package com.customclient.gui.clickgui;

import java.util.ArrayList;
import java.util.List;

import net.minecraft.client.MinecraftClient;
import net.minecraft.client.gui.DrawContext;

import com.customclient.module.Module;
import com.customclient.setting.Setting;
import com.customclient.setting.BooleanSetting;

/**
 * Кнопка модуля в ClickGUI
 */
public class ModuleButton {
    private static final MinecraftClient mc = MinecraftClient.getInstance();
    
    private final Module module;
    private int x, y, width, height;
    
    private boolean extended = false;
    private final List<SettingComponent> settingComponents = new ArrayList<>();
    
    public ModuleButton(Module module, int x, int y, int width, int height) {
        this.module = module;
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
        
        // Создаем компоненты для каждой настройки
        int settingY = y + height + 1;
        for (Setting<?> setting : module.getSettings()) {
            if (setting instanceof BooleanSetting) {
                settingComponents.add(new BooleanComponent((BooleanSetting) setting, x + 2, settingY, width - 4, 14));
                settingY += 15;
            }
            // TODO: Добавить другие типы настроек
        }
    }
    
    public void render(DrawContext context, int mouseX, int mouseY, float delta) {
        // Рендер фона кнопки
        int color = module.isEnabled() ? 0xFF408040 : 0xFF404040;
        context.fill(x, y, x + width, y + height, color);
        
        // Рендер названия модуля
        context.drawTextWithShadow(
            mc.textRenderer,
            module.getName(),
            x + 2,
            y + (height - 8) / 2,
            0xFFFFFF
        );
        
        // Индикатор состояния
        String status = module.isEnabled() ? "On" : "Off";
        context.drawTextWithShadow(
            mc.textRenderer,
            status,
            x + width - mc.textRenderer.getWidth(status) - 2,
            y + (height - 8) / 2,
            module.isEnabled() ? 0x00FF00 : 0xFF0000
        );
        
        // Рендер настроек если кнопка раскрыта
        if (extended) {
            for (SettingComponent component : settingComponents) {
                component.render(context, mouseX, mouseY, delta);
            }
        }
    }
    
    public boolean mouseClicked(double mouseX, double mouseY, int button) {
        // Проверка клика по кнопке модуля
        if (mouseX >= x && mouseX <= x + width && mouseY >= y && mouseY <= y + height) {
            if (button == 0) { // ЛКМ - включить/выключить
                module.toggle();
                return true;
            } else if (button == 1) { // ПКМ - раскрыть настройки
                extended = !extended;
                return true;
            }
        }
        
        // Клик по настройкам
        if (extended) {
            for (SettingComponent component : settingComponents) {
                if (component.mouseClicked(mouseX, mouseY, button)) {
                    return true;
                }
            }
        }
        
        return false;
    }
    
    public void mouseReleased(double mouseX, double mouseY, int button) {
        for (SettingComponent component : settingComponents) {
            component.mouseReleased(mouseX, mouseY, button);
        }
    }
    
    public boolean mouseDragged(double mouseX, double mouseY, int button, double deltaX, double deltaY) {
        for (SettingComponent component : settingComponents) {
            if (component.mouseDragged(mouseX, mouseY, button, deltaX, deltaY)) {
                return true;
            }
        }
        return false;
    }
    
    public void setPosition(int x, int y) {
        this.x = x;
        this.y = y;
        
        // Обновляем позиции компонентов настроек
        int settingY = y + height + 1;
        for (SettingComponent component : settingComponents) {
            component.setPosition(x + 2, settingY);
            settingY += 15;
        }
    }
}
