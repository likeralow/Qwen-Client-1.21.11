package com.customclient.gui.clickgui;

import net.minecraft.client.MinecraftClient;
import net.minecraft.client.gui.DrawContext;

import com.customclient.setting.BooleanSetting;

/**
 * Компонент булевой настройки
 */
public class BooleanComponent extends SettingComponent {
    private static final MinecraftClient mc = MinecraftClient.getInstance();
    
    private final BooleanSetting setting;
    
    public BooleanComponent(BooleanSetting setting, int x, int y, int width, int height) {
        super(x, y, width, height);
        this.setting = setting;
    }
    
    @Override
    public void render(DrawContext context, int mouseX, int mouseY, float delta) {
        // Рендер фона
        context.fill(x, y, x + width, y + height, 0xFF303030);
        
        // Рендер названия
        context.drawTextWithShadow(
            mc.textRenderer,
            setting.getName(),
            x + 2,
            y + (height - 8) / 2,
            0xFFFFFF
        );
        
        // Рендер индикатора состояния
        String valueStr = setting.getValue() ? "✓" : "✗";
        int color = setting.getValue() ? 0xFF00FF00 : 0xFFFF0000;
        context.drawTextWithShadow(
            mc.textRenderer,
            valueStr,
            x + width - mc.textRenderer.getWidth(valueStr) - 2,
            y + (height - 8) / 2,
            color
        );
    }
    
    @Override
    public boolean mouseClicked(double mouseX, double mouseY, int button) {
        if (mouseX >= x && mouseX <= x + width && mouseY >= y && mouseY <= y + height) {
            if (button == 0) { // ЛКМ - переключить
                setting.toggle();
                return true;
            }
        }
        return false;
    }
}
