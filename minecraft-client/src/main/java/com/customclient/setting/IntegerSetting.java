package com.customclient.setting;

/**
 * Настройка целочисленного значения с мин/макс
 */
public class IntegerSetting extends Setting<Integer> {
    private final int min;
    private final int max;
    
    public IntegerSetting(String name, String description, int defaultValue, int min, int max) {
        super(name, description, defaultValue);
        this.min = min;
        this.max = max;
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
    
    public int getValueInt() {
        return value;
    }
}
