package com.example.client.gui.clickgui;

import com.example.client.module.Module;
import com.example.client.module.ModuleManager;

import java.util.ArrayList;
import java.util.List;

/**
 * Категория (панель) в ClickGUI
 */
public class CategoryPanel {
    private Module.Category category;
    private int x, y, width, height;
    private boolean dragged;
    private int dragOffsetX, dragOffsetY;
    private List<ModuleButton> moduleButtons;
    private boolean extended;
    
    public CategoryPanel(Module.Category category, int x, int y, int width) {
        this.category = category;
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = 16; // Высота заголовка
        this.dragged = false;
        this.dragOffsetX = 0;
        this.dragOffsetY = 0;
        this.extended = true;
        this.moduleButtons = new ArrayList<>();
        
        // Создаём кнопки для всех модулей в категории
        int offsetY = height + 2;
        for (Module module : ModuleManager.getInstance().getModulesByCategory(category)) {
            ModuleButton button = new ModuleButton(module, x + 2, y + offsetY, width - 4, 14);
            moduleButtons.add(button);
            offsetY += button.getHeight() + 1;
        }
    }
    
    public void render(int mouseX, int mouseY, float partialTicks) {
        // Рендеринг заголовка категории
        
        // Текст названия категории
        
        // Если расширено - рендерим кнопки модулей
        if (extended) {
            for (ModuleButton button : moduleButtons) {
                button.render(mouseX, mouseY, partialTicks);
            }
        }
    }
    
    public void mouseClicked(int mouseX, int mouseY, int mouseButton) {
        // Проверка клика по заголовку
        if (mouseX >= x && mouseX <= x + width &&
            mouseY >= y && mouseY <= y + height) {
            
            if (mouseButton == 0) { // ЛКМ - начать перетаскивание
                dragged = true;
                dragOffsetX = mouseX - x;
                dragOffsetY = mouseY - y;
            } else if (mouseButton == 1) { // ПКМ - свернуть/развернуть
                extended = !extended;
            }
            return;
        }
        
        // Если расширено - проверяем клики по кнопкам модулей
        if (extended) {
            for (ModuleButton button : moduleButtons) {
                button.mouseClicked(mouseX, mouseY, mouseButton);
            }
        }
    }
    
    public void mouseReleased(int mouseX, int mouseY, int state) {
        dragged = false;
        
        if (extended) {
            for (ModuleButton button : moduleButtons) {
                button.mouseReleased(mouseX, mouseY, state);
            }
        }
    }
    
    public void mouseClickMove(int mouseX, int mouseY, int clickedMouseButton, long timeSinceLastClick) {
        if (dragged) {
            this.x = mouseX - dragOffsetX;
            this.y = mouseY - dragOffsetY;
            updateButtonPositions();
        }
        
        if (extended) {
            for (ModuleButton button : moduleButtons) {
                button.mouseClickMove(mouseX, mouseY, clickedMouseButton, timeSinceLastClick);
            }
        }
    }
    
    public void keyTyped(char typedChar, int keyCode) {
        if (extended) {
            for (ModuleButton button : moduleButtons) {
                button.keyTyped(typedChar, keyCode);
            }
        }
    }
    
    private void updateButtonPositions() {
        int offsetY = height + 2;
        for (ModuleButton button : moduleButtons) {
            button.updatePosition(x + 2, y + offsetY);
            offsetY += button.getHeight() + 1;
        }
    }
    
    public int getTotalHeight() {
        if (!extended) return height;
        
        int totalHeight = height;
        for (ModuleButton button : moduleButtons) {
            totalHeight += button.getHeight() + 1;
        }
        return totalHeight;
    }
    
    public Module.Category getCategory() {
        return category;
    }
    
    public int getX() {
        return x;
    }
    
    public int getY() {
        return y;
    }
    
    public int getWidth() {
        return width;
    }
}
