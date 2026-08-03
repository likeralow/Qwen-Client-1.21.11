package com.example.client.module;

import com.example.client.module.settings.Setting;
import java.util.ArrayList;
import java.util.List;

/**
 * Базовый класс для всех модулей
 */
public abstract class Module {
    protected String name;
    protected String description;
    protected Category category;
    protected boolean enabled;
    protected int bind; // keyCode для активации
    protected List<Setting<?>> settings;
    
    public Module(String name, String description, Category category) {
        this.name = name;
        this.description = description;
        this.category = category;
        this.enabled = false;
        this.bind = -1; // No bind by default
        this.settings = new ArrayList<>();
    }
    
    public String getName() {
        return name;
    }
    
    public String getDescription() {
        return description;
    }
    
    public Category getCategory() {
        return category;
    }
    
    public boolean isEnabled() {
        return enabled;
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
    
    public void toggle() {
        setEnabled(!enabled);
    }
    
    public int getBind() {
        return bind;
    }
    
    public void setBind(int bind) {
        this.bind = bind;
    }
    
    public List<Setting<?>> getSettings() {
        return settings;
    }
    
    protected <T extends Setting<?>> T addSetting(T setting) {
        settings.add(setting);
        return setting;
    }
    
    public Setting<?> getSettingByName(String name) {
        for (Setting<?> setting : settings) {
            if (setting.getName().equalsIgnoreCase(name)) {
                return setting;
            }
        }
        return null;
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
     * Вызывается каждый кадр когда модуль активен
     */
    public void onRender() {}
    
    public enum Category {
        COMBAT("Combat"),
        MOVEMENT("Movement"),
        RENDER("Render"),
        PLAYER("Player"),
        MISC("Misc"),
        WORLD("World");
        
        private final String displayName;
        
        Category(String displayName) {
            this.displayName = displayName;
        }
        
        public String getDisplayName() {
            return displayName;
        }
    }
}
