package com.customclient.setting;

import java.awt.Color;

/**
 * Базовый класс для всех настроек модулей
 */
public class Setting<T> {
    protected String name;
    protected String description;
    protected T value;
    protected T defaultValue;
    
    public Setting(String name, String description, T defaultValue) {
        this.name = name;
        this.description = description;
        this.value = defaultValue;
        this.defaultValue = defaultValue;
    }
    
    public String getName() {
        return name;
    }
    
    public String getDescription() {
        return description;
    }
    
    public T getValue() {
        return value;
    }
    
    public void setValue(T value) {
        this.value = value;
    }
    
    public T getDefaultValue() {
        return defaultValue;
    }
    
    public void reset() {
        this.value = defaultValue;
    }
}
