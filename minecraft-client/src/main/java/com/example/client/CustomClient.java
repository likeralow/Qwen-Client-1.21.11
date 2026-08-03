package com.example.client;

import com.example.client.event.EventManager;
import com.example.client.gui.CustomGuiScreen;
import com.example.client.render.CustomRenderer;
import net.minecraft.client.Minecraft;
import net.minecraft.network.chat.Component;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Основной класс кастомного Minecraft клиента
 * 
 * Особенности:
 * - Mixin система для внедрения кода в Minecraft
 * - Система событий (Event System)
 * - Кастомный рендерер (ESP, линии, коробки)
 * - Кастомный GUI
 */
public class CustomClient {
    public static final String MOD_ID = "customclient";
    public static final Logger LOGGER = LoggerFactory.getLogger(MOD_ID);

    private static CustomClient instance;

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
        
        // Пример регистрации обработчика событий
        EventManager.RENDER_WORLD.register(event -> {
            // Здесь можно добавить ESP или другие визуальные эффекты
            float partialTicks = event.getPartialTicks();
            // CustomRenderer.renderLine(...) можно вызвать здесь
        });

        EventManager.RENDER_GUI.register(event -> {
            // Рендеринг на GUI
        });

        LOGGER.info("Custom Client fully loaded!");
    }

    /**
     * Открытие кастомного GUI
     */
    public void openCustomGui() {
        Minecraft mc = Minecraft.getInstance();
        mc.setScreen(new CustomGuiScreen(mc.screen));
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
}
