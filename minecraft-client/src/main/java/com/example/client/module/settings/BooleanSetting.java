package com.example.client.module.settings;

/**
 * Булева настройка (вкл/выкл)
 */
public class BooleanSetting extends Setting<Boolean> {
    
    public BooleanSetting(String name, String description, boolean defaultValue) {
        super(name, description, defaultValue);
    }
    
    @Override
    public String getType() {
        return "Boolean";
    }
    
    public void toggle() {
        this.value = !this.value;
    }
}
