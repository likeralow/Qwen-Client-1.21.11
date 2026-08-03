package com.example.client.gui.clickgui;

import com.example.client.module.Module;
import net.minecraft.client.gui.GuiScreen;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Полноценный ClickGUI экран
 */
public class ClickGui extends GuiScreen {
    private List<CategoryPanel> categoryPanels;
    private int mouseX, mouseY;
    
    // Цвета GUI (можно вынести в конфиг)
    private final int backgroundColor = 0x80000000; // Полупрозрачный черный
    private final int headerColor = 0xFF333333;     // Темно-серый для заголовков
    private final int enabledColor = 0xFF00AA00;    // Зеленый для включенных модулей
    private final int disabledColor = 0xFF555555;   // Серый для выключенных
    
    public ClickGui() {
        this.categoryPanels = new ArrayList<>();
    }
    
    @Override
    public void initGui() {
        super.initGui();
        
        // Создаём панели для каждой категории
        int panelWidth = 120;
        int spacing = 10;
        int startX = spacing;
        int startY = spacing;
        
        int xOffset = startX;
        for (Module.Category category : Module.Category.values()) {
            categoryPanels.add(new CategoryPanel(category, xOffset, startY, panelWidth));
            xOffset += panelWidth + spacing;
            
            // Перенос на следующую строку если не помещается
            if (xOffset + panelWidth > width) {
                xOffset = startX;
                startY += 200; // Примерная высота панели
            }
        }
    }
    
    @Override
    public void drawScreen(int mouseX, int mouseY, float partialTicks) {
        this.mouseX = mouseX;
        this.mouseY = mouseY;
        
        // Фон
        drawRect(0, 0, width, height, backgroundColor);
        
        // Рендеринг всех панелей
        for (CategoryPanel panel : categoryPanels) {
            panel.render(mouseX, mouseY, partialTicks);
        }
        
        // Заголовок
        drawCenteredString(fontRenderer, "ClickGUI - Example Client", width / 2, 5, 0xFFFFFF);
        
        // Подсказка
        drawString(fontRenderer, "ЛКМ - Вкл/Выкл | ПКМ - Меню | Drag - ЛКМ по заголовку", 5, height - 15, 0xAAAAAA);
    }
    
    @Override
    protected void mouseClicked(int mouseX, int mouseY, int mouseButton) throws IOException {
        super.mouseClicked(mouseX, mouseY, mouseButton);
        
        for (CategoryPanel panel : categoryPanels) {
            panel.mouseClicked(mouseX, mouseY, mouseButton);
        }
    }
    
    @Override
    protected void mouseReleased(int mouseX, int mouseY, int state) {
        super.mouseReleased(mouseX, mouseY, state);
        
        for (CategoryPanel panel : categoryPanels) {
            panel.mouseReleased(mouseX, mouseY, state);
        }
    }
    
    @Override
    protected void mouseClickMove(int mouseX, int mouseY, int clickedMouseButton, long timeSinceLastClick) {
        super.mouseClickMove(mouseX, mouseY, clickedMouseButton, timeSinceLastClick);
        
        for (CategoryPanel panel : categoryPanels) {
            panel.mouseClickMove(mouseX, mouseY, clickedMouseButton, timeSinceLastClick);
        }
    }
    
    @Override
    protected void keyTyped(char typedChar, int keyCode) throws IOException {
        // Закрытие GUI по ESC
        if (keyCode == 1) { // ESC
            mc.displayGuiScreen(null);
            return;
        }
        
        for (CategoryPanel panel : categoryPanels) {
            panel.keyTyped(typedChar, keyCode);
        }
        
        super.keyTyped(typedChar, keyCode);
    }
    
    @Override
    public boolean doesGuiPauseGame() {
        return false; // Не паузировать игру при открытом GUI
    }
    
    public List<CategoryPanel> getCategoryPanels() {
        return categoryPanels;
    }
}
