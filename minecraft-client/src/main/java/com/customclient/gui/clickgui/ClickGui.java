package com.customclient.gui.clickgui;

import java.util.ArrayList;
import java.util.List;

import net.minecraft.client.MinecraftClient;
import net.minecraft.client.gui.DrawContext;
import net.minecraft.client.gui.screen.Screen;
import net.minecraft.text.Text;

import com.customclient.module.Module;
import com.customclient.module.ModuleManager;

/**
 * ClickGUI - основной экран GUI для управления модулями
 */
public class ClickGui extends Screen {
    private static final MinecraftClient mc = MinecraftClient.getInstance();
    
    private final List<CategoryPanel> panels = new ArrayList<>();
    
    public ClickGui() {
        super(Text.literal("ClickGUI"));
        
        // Создаем панели для каждой категории
        int x = 20;
        int y = 20;
        int panelWidth = 120;
        int panelHeight = 20;
        int gap = 15;
        
        for (Module.Category category : Module.Category.values()) {
            panels.add(new CategoryPanel(
                category.getDisplayName(),
                x,
                y,
                panelWidth,
                panelHeight,
                category
            ));
            x += panelWidth + gap;
        }
    }
    
    @Override
    protected void init() {
        super.init();
    }
    
    @Override
    public void render(DrawContext context, int mouseX, int mouseY, float delta) {
        // Рендер фона
        this.renderBackground(context, mouseX, mouseY, delta);
        
        // Рендер панелей
        for (CategoryPanel panel : panels) {
            panel.render(context, mouseX, mouseY, delta);
        }
        
        // Заголовок
        context.drawTextWithShadow(
            mc.textRenderer,
            "Custom Client ClickGUI",
            width / 2 - mc.textRenderer.getWidth("Custom Client ClickGUI") / 2,
            5,
            0xFFFFFF
        );
        
        super.render(context, mouseX, mouseY, delta);
    }
    
    @Override
    public boolean mouseClicked(double mouseX, double mouseY, int button) {
        for (CategoryPanel panel : panels) {
            if (panel.mouseClicked(mouseX, mouseY, button)) {
                return true;
            }
        }
        return super.mouseClicked(mouseX, mouseY, button);
    }
    
    @Override
    public boolean mouseReleased(double mouseX, double mouseY, int button) {
        for (CategoryPanel panel : panels) {
            panel.mouseReleased(mouseX, mouseY, button);
        }
        return super.mouseReleased(mouseX, mouseY, button);
    }
    
    @Override
    public boolean mouseDragged(double mouseX, double mouseY, int button, double deltaX, double deltaY) {
        for (CategoryPanel panel : panels) {
            if (panel.mouseDragged(mouseX, mouseY, button, deltaX, deltaY)) {
                return true;
            }
        }
        return super.mouseDragged(mouseX, mouseY, button, deltaX, deltaY);
    }
    
    @Override
    public boolean keyPressed(int keyCode, int scanCode, int modifiers) {
        if (keyCode == 257 || keyCode == 265) { // Enter или Escape
            mc.setScreen(null);
            return true;
        }
        return super.keyPressed(keyCode, scanCode, modifiers);
    }
    
    @Override
    public boolean shouldPause() {
        return false;
    }
}
