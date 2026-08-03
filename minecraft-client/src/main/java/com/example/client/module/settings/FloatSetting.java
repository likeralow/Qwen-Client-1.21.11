package com.example.client.module.settings;

/**
 * Настройка с плавающим значением (слайдер)
 */
public class FloatSetting extends Setting<Float> {
    private float min;
    private float max;
    
    public FloatSetting(String name, String description, float defaultValue, float min, float max) {
        super(name, description, defaultValue);
        this.min = min;
        this.max = max;
    }
    
    @Override
    public String getType() {
        return "Float";
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
}
