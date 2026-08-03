package com.customclient.setting;

import java.awt.Color;

/**
 * Настройка цвета с поддержкой rainbow
 */
public class ColorSetting extends Setting<Color> {
    private boolean rainbow;
    
    public ColorSetting(String name, String description, Color defaultValue) {
        super(name, description, defaultValue);
        this.rainbow = false;
    }
    
    public ColorSetting(String name, String description, int defaultRGB) {
        super(name, description, new Color(defaultRGB));
        this.rainbow = false;
    }
    
    public boolean isRainbow() {
        return rainbow;
    }
    
    public void setRainbow(boolean rainbow) {
        this.rainbow = rainbow;
    }
    
    public void toggleRainbow() {
        this.rainbow = !this.rainbow;
    }
    
    public Color getColor() {
        if (rainbow) {
            return Color.getHSBColor((System.currentTimeMillis() % 3600) / 3600f, 0.5f, 1f);
        }
        return value;
    }
    
    public int getRed() {
        return getColor().getRed();
    }
    
    public int getGreen() {
        return getColor().getGreen();
    }
    
    public int getBlue() {
        return getColor().getBlue();
    }
    
    public int getAlpha() {
        return getColor().getAlpha();
    }
}
