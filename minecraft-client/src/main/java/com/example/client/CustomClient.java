package com.example.client;

import com.example.client.event.EventManager;
import com.example.client.gui.CustomGuiScreen;
import com.example.client.gui.clickgui.ClickGui;
import com.example.client.module.Module;
import com.example.client.module.ModuleManager;
import com.example.client.module.modules.*;
import com.example.client.render.CustomRenderer;
import net.minecraft.client.Minecraft;
import net.minecraft.network.chat.Component;
import org.lwjgl.glfw.GLFW;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Основной класс кастомного Minecraft клиента
 * 
 * Особенности:
 * - Mixin система для внедрения кода в Minecraft
 * - Система событий (Event System)
 * - Кастомный рендерер (ESP, линии, коробки)
 * - Полноценная система модулей с настройками
 * - ClickGUI для управления модулями
 */
public class CustomClient {
    public static final String MOD_ID = "customclient";
    public static final Logger LOGGER = LoggerFactory.getLogger(MOD_ID);

    private static CustomClient instance;
    private ModuleManager moduleManager;

    public CustomClient() {
        instance = this;
        LOGGER.info("Custom Client initialized!");
    }

    public static CustomClient getInstance() {
        return instance;
    }

    /**
     * Инициализация клиента
     */
    public void init() {
        EventManager.init();
        LOGGER.info("Event Manager initialized");
        
        // Инициализация менеджера модулей
        moduleManager = ModuleManager.getInstance();
        registerModules();
        LOGGER.info("Module Manager initialized with {} modules", moduleManager.getAllModules().size());
        
        // Пример регистрации обработчика событий
        EventManager.RENDER_WORLD.register(event -> {
            float partialTicks = event.getPartialTicks();
            
            // Рендеринг активных модулей
            if (moduleManager != null) {
                moduleManager.onRender();
            }
        });

        EventManager.RENDER_GUI.register(event -> {
            // Рендеринг на GUI (например, ArrayList модулей)
            renderArrayList();
        });

        LOGGER.info("Custom Client fully loaded!");
    }
    
    /**
     * Регистрация всех модулей
     */
    private void registerModules() {
        // Combat
        moduleManager.registerModule(new ClickPearl());
        
        // Render
        moduleManager.registerModule(new FullBright());
        moduleManager.registerModule(new BlockOverlay());
        moduleManager.registerModule(new StorageESP());
        
        // Player
        moduleManager.registerModule(new AutoFish());
        
        // Можно добавить больше модулей здесь
    }
    
    /**
     * Рендеринг ArrayList (список активных модулей)
     */
    private void renderArrayList() {
        if (moduleManager == null) return;
        
        int yOffset = 5;
        for (Module module : moduleManager.getEnabledModules()) {
            // CustomRenderer.drawString(module.getName(), 5, yOffset, 0xFFFFFFFF);
            yOffset += 10;
        }
    }

    /**
     * Открытие кастомного GUI
     */
    public void openCustomGui() {
        Minecraft mc = Minecraft.getInstance();
        mc.setScreen(new CustomGuiScreen(mc.screen));
    }
    
    /**
     * Открытие ClickGUI
     */
    public void openClickGui() {
        Minecraft mc = Minecraft.getInstance();
        mc.setScreen(new ClickGui());
    }
    
    /**
     * Обработка нажатий клавиш
     */
    public void onKeyPressed(int keyCode, int scanCode, int action, int modifiers) {
        if (keyCode == GLFW.GLFW_KEY_RIGHT_SHIFT && action == GLFW.GLFW_KEY_PRESS) {
            // Правый Shift для открытия ClickGUI
            openClickGui();
            return;
        }
        
        // Обработка биндов модулей
        if (moduleManager != null) {
            moduleManager.onKeybind(keyCode);
        }
    }

    /**
     * Обновление каждый тик
     */
    public void onTick() {
        if (moduleManager != null) {
            moduleManager.onTick();
        }
    }

    /**
     * Пример использования рендерера
     */
    public void renderExample() {
        // Пример рендеринга линии
        // CustomRenderer.renderLine(start, end, 1.0f, 0.0f, 0.0f, 1.0f);
        
        // Пример рендеринга коробки
        // CustomRenderer.renderBox(center, width, height, depth, 0.0f, 1.0f, 0.0f, 1.0f);
    }
    
    public ModuleManager getModuleManager() {
        return moduleManager;
    }
}
