package com.customclient.module;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Менеджер всех модулей
 */
public class ModuleManager {
    private static final Map<String, Module> modules = new HashMap<>();
    private static final List<Module> moduleList = new ArrayList<>();
    
    public static void init() {
        System.out.println("[ModuleManager] Инициализация модулей...");
        
        // Combat
        register(new ClickPearl());
        
        // Player
        register(new FullBright());
        register(new AutoFish());
        
        // Render
        register(new BlockOverlay());
        register(new StorageESP());
        
        System.out.println("[ModuleManager] Зарегистрировано " + modules.size() + " модулей");
    }
    
    public static void register(Module module) {
        modules.put(module.getName(), module);
        moduleList.add(module);
    }
    
    public static Module getModule(String name) {
        return modules.get(name);
    }
    
    @SuppressWarnings("unchecked")
    public static <T extends Module> T getModule(Class<T> clazz) {
        for (Module module : moduleList) {
            if (clazz.isInstance(module)) {
                return (T) module;
            }
        }
        return null;
    }
    
    public static List<Module> getModules() {
        return moduleList;
    }
    
    public static List<Module> getModulesByCategory(Module.Category category) {
        List<Module> result = new ArrayList<>();
        for (Module module : moduleList) {
            if (module.getCategory() == category) {
                result.add(module);
            }
        }
        return result;
    }
    
    public static void onTick() {
        for (Module module : moduleList) {
            if (module.isEnabled()) {
                module.onTick();
            }
        }
    }
    
    public static void onRenderWorld(float tickDelta) {
        for (Module module : moduleList) {
            if (module.isEnabled() && module.isDrawn()) {
                module.onRenderWorld(tickDelta);
            }
        }
    }
    
    public static void onRenderGUI(float tickDelta) {
        for (Module module : moduleList) {
            if (module.isEnabled() && module.isDrawn()) {
                module.onRenderGUI(tickDelta);
            }
        }
    }
    
    public static void onKey(int key) {
        for (Module module : moduleList) {
            if (module.getBind() == key) {
                module.toggle();
            }
        }
    }
}
