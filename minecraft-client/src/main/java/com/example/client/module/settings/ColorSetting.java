package com.example.client.module.settings;

import java.awt.Color;

/**
 * Настройка цвета (Color4j)
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
    
    @Override
    public String getType() {
        return "Color";
    }
    
    public boolean isRainbow() {
        return rainbow;
    }
    
    public void setRainbow(boolean rainbow) {
        this.rainbow = rainbow;
    }
    
    public int getRed() {
        return value.getRed();
    }
    
    public int getGreen() {
        return value.getGreen();
    }
    
    public int getBlue() {
        return value.getBlue();
    }
    
    public int getAlpha() {
        return value.getAlpha();
    }
}
