package com.example.client.module.settings;

/**
 * Настройка с целочисленным значением
 */
public class IntegerSetting extends Setting<Integer> {
    private int min;
    private int max;
    
    public IntegerSetting(String name, String description, int defaultValue, int min, int max) {
        super(name, description, defaultValue);
        this.min = min;
        this.max = max;
    }
    
    @Override
    public String getType() {
        return "Integer";
    }
    
    public int getMin() {
        return min;
    }
    
    public int getMax() {
        return max;
    }
    
    public void setValue(int value) {
        this.value = Math.max(min, Math.min(max, value));
    }
}
