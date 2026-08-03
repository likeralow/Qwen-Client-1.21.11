package com.example.client.module;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Менеджер модулей - хранит и управляет всеми модулями
 */
public class ModuleManager {
    private static ModuleManager instance;
    private Map<String, Module> modulesByName;
    private Map<Module.Category, List<Module>> modulesByCategory;
    
    private ModuleManager() {
        modulesByName = new HashMap<>();
        modulesByCategory = new HashMap<>();
        
        for (Module.Category category : Module.Category.values()) {
            modulesByCategory.put(category, new ArrayList<>());
        }
    }
    
    public static ModuleManager getInstance() {
        if (instance == null) {
            instance = new ModuleManager();
        }
        return instance;
    }
    
    public void registerModule(Module module) {
        modulesByName.put(module.getName().toLowerCase(), module);
        modulesByCategory.get(module.getCategory()).add(module);
    }
    
    public Module getModuleByName(String name) {
        return modulesByName.get(name.toLowerCase());
    }
    
    public List<Module> getModulesByCategory(Module.Category category) {
        return modulesByCategory.get(category);
    }
    
    public List<Module> getAllModules() {
        return new ArrayList<>(modulesByName.values());
    }
    
    public List<Module> getEnabledModules() {
        List<Module> enabled = new ArrayList<>();
        for (Module module : modulesByName.values()) {
            if (module.isEnabled()) {
                enabled.add(module);
            }
        }
        return enabled;
    }
    
    public void onTick() {
        for (Module module : getEnabledModules()) {
            module.onTick();
        }
    }
    
    public void onRender() {
        for (Module module : getEnabledModules()) {
            module.onRender();
        }
    }
    
    public void onKeybind(int keyCode) {
        for (Module module : getAllModules()) {
            if (module.getBind() == keyCode) {
                module.toggle();
            }
        }
    }
}
