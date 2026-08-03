package com.customclient.module;

import java.util.ArrayList;
import java.util.List;

import com.customclient.setting.Setting;

/**
 * Базовый класс для всех модулей
 */
public class Module {
    protected String name;
    protected String description;
    protected Category category;
    protected int bind; // Код клавиши (GLFW)
    protected boolean enabled;
    protected boolean drawn;
    protected List<Setting<?>> settings;
    
    public Module(String name, String description, Category category) {
        this.name = name;
        this.description = description;
        this.category = category;
        this.bind = -1; // Нет бинда по умолчанию
        this.enabled = false;
        this.drawn = true;
        this.settings = new ArrayList<>();
    }
    
    /**
     * Вызывается при включении модуля
     */
    public void onEnable() {}
    
    /**
     * Вызывается при выключении модуля
     */
    public void onDisable() {}
    
    /**
     * Вызывается каждый тик когда модуль активен
     */
    public void onTick() {}
    
    /**
     * Вызывается при рендере мира
     */
    public void onRenderWorld(float tickDelta) {}
    
    /**
     * Вызывается при рендере GUI
     */
    public void onRenderGUI(float tickDelta) {}
    
    public String getName() {
        return name;
    }
    
    public String getDescription() {
        return description;
    }
    
    public Category getCategory() {
        return category;
    }
    
    public int getBind() {
        return bind;
    }
    
    public void setBind(int bind) {
        this.bind = bind;
    }
    
    public boolean isEnabled() {
        return enabled;
    }
    
    public void toggle() {
        this.enabled = !this.enabled;
        if (enabled) {
            onEnable();
        } else {
            onDisable();
        }
    }
    
    public void setEnabled(boolean enabled) {
        if (this.enabled != enabled) {
            this.enabled = enabled;
            if (enabled) {
                onEnable();
            } else {
                onDisable();
            }
        }
    }
    
    public boolean isDrawn() {
        return drawn;
    }
    
    public void setDrawn(boolean drawn) {
        this.drawn = drawn;
    }
    
    public List<Setting<?>> getSettings() {
        return settings;
    }
    
    public void addSetting(Setting<?> setting) {
        settings.add(setting);
    }
    
    @SuppressWarnings("unchecked")
    public <T extends Setting<?>> T getSetting(Class<T> clazz, String name) {
        for (Setting<?> setting : settings) {
            if (clazz.isInstance(setting) && setting.getName().equals(name)) {
                return (T) setting;
            }
        }
        return null;
    }
    
    /**
     * Категории модулей
     */
    public enum Category {
        COMBAT("Combat"),
        MOVEMENT("Movement"),
        RENDER("Render"),
        PLAYER("Player"),
        WORLD("World"),
        MISC("Misc");
        
        private final String displayName;
        
        Category(String displayName) {
            this.displayName = displayName;
        }
        
        public String getDisplayName() {
            return displayName;
        }
    }
}
