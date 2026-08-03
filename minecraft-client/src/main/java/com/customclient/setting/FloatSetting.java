package com.customclient.setting;

/**
 * Настройка плавающего значения с мин/макс
 */
public class FloatSetting extends Setting<Float> {
    private final float min;
    private final float max;
    
    public FloatSetting(String name, String description, float defaultValue, float min, float max) {
        super(name, description, defaultValue);
        this.min = min;
        this.max = max;
    }
    
    public float getMin() {
        return min;
    }
    
    public float getMax() {
        return max;
    }
    
    public void setValue(float value) {
        this.value = Math.max(min, Math.min(max, value));
    }
    
    public float getValueFloat() {
        return value;
    }
}
