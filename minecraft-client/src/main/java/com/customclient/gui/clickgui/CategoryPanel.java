package com.customclient.gui.clickgui;

import java.util.ArrayList;
import java.util.List;

import net.minecraft.client.MinecraftClient;
import net.minecraft.client.gui.DrawContext;
import net.minecraft.client.render.RenderTickCounter;

import com.customclient.module.Module;
import com.customclient.module.ModuleManager;

/**
 * Панель категории в ClickGUI
 */
public class CategoryPanel {
    private static final MinecraftClient mc = MinecraftClient.getInstance();
    
    private final String title;
    private int x, y, width, height;
    private final Module.Category category;
    
    private final List<ModuleButton> buttons = new ArrayList<>();
    
    private boolean dragging = false;
    private int dragOffsetX, dragOffsetY;
    
    private boolean extended = true;
    
    public CategoryPanel(String title, int x, int y, int width, int height, Module.Category category) {
        this.title = title;
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
        this.category = category;
        
        // Создаем кнопки для каждого модуля в категории
        int buttonY = y + height;
        for (Module module : ModuleManager.getModulesByCategory(category)) {
            buttons.add(new ModuleButton(module, x, buttonY, width, 16));
            buttonY += 17;
        }
    }
    
    public void render(DrawContext context, int mouseX, int mouseY, float delta) {
        // Рендер заголовка панели
        context.fill(x, y, x + width, y + height, 0xFF404040);
        context.drawTextWithShadow(
            mc.textRenderer,
            title + " (" + buttons.size() + ")",
            x + 2,
            y + (height - 8) / 2,
            0xFFFFFF
        );
        
        // Рендер границ
        context.drawHorizontalLine(x, x + width, y, 0xFF606060);
        context.drawVerticalLine(x, y, y + height, 0xFF606060);
        context.drawHorizontalLine(x, x + width, y + height, 0xFF606060);
        context.drawVerticalLine(x + width, y, y + height, 0xFF606060);
        
        // Рендер кнопок модулей если панель раскрыта
        if (extended) {
            for (ModuleButton button : buttons) {
                button.render(context, mouseX, mouseY, delta);
            }
        }
    }
    
    public boolean mouseClicked(double mouseX, double mouseY, int button) {
        // Проверка клика по заголовку для перетаскивания
        if (mouseX >= x && mouseX <= x + width && mouseY >= y && mouseY <= y + height) {
            if (button == 0) { // ЛКМ
                dragging = true;
                dragOffsetX = (int)(mouseX - x);
                dragOffsetY = (int)(mouseY - y);
                return true;
            } else if (button == 1) { // ПКМ - свернуть/развернуть
                extended = !extended;
                return true;
            }
        }
        
        // Клик по кнопкам модулей
        if (extended) {
            for (ModuleButton modButton : buttons) {
                if (modButton.mouseClicked(mouseX, mouseY, button)) {
                    return true;
                }
            }
        }
        
        return false;
    }
    
    public void mouseReleased(double mouseX, double mouseY, int button) {
        dragging = false;
        for (ModuleButton modButton : buttons) {
            modButton.mouseReleased(mouseX, mouseY, button);
        }
    }
    
    public boolean mouseDragged(double mouseX, double mouseY, int button, double deltaX, double deltaY) {
        if (dragging) {
            x = (int)(mouseX - dragOffsetX);
            y = (int)(mouseY - dragOffsetY);
            
            // Обновляем позиции кнопок
            int buttonY = y + height;
            for (ModuleButton modButton : buttons) {
                modButton.setPosition(x, buttonY);
                buttonY += 17;
            }
            
            return true;
        }
        
        for (ModuleButton modButton : buttons) {
            if (modButton.mouseDragged(mouseX, mouseY, button, deltaX, deltaY)) {
                return true;
            }
        }
        
        return false;
    }
    
    public int getX() { return x; }
    public int getY() { return y; }
    public int getWidth() { return width; }
    public int getHeight() { return height; }
}
