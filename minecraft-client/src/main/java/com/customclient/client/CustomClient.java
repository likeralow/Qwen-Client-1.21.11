package com.customclient.client;

import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.client.event.lifecycle.v1.ClientTickEvents;
import net.fabricmc.fabric.api.client.keybinding.v1.KeyBindingHelper;
import net.minecraft.client.option.KeyBinding;
import net.minecraft.client.util.InputUtil;
import org.lwjgl.glfw.GLFW;

import com.customclient.module.ModuleManager;
import com.customclient.gui.clickgui.ClickGui;
import com.customclient.event.EventSystem;

public class CustomClient implements ClientModInitializer {
    
    public static final String MOD_ID = "customclient";
    public static KeyBinding clickGuiKey;
    
    @Override
    public void onInitializeClient() {
        System.out.println("[CustomClient] Инициализация клиента...");
        
        // Инициализация менеджеров
        EventSystem.init();
        ModuleManager.init();
        
        // Регистрация клавиши для ClickGUI (Правый Shift)
        clickGuiKey = KeyBindingHelper.registerKeyBinding(new KeyBinding(
            "key.customclient.clickgui",
            InputUtil.Type.KEYSYM,
            GLFW.GLFW_KEY_RIGHT_SHIFT,
            "category." + MOD_ID
        ));
        
        // Подписка на тики клиента
        ClientTickEvents.END_CLIENT_TICK.register(client -> {
            while (clickGuiKey.wasPressed()) {
                if (client.currentScreen == null) {
                    client.setScreen(new ClickGui());
                }
            }
            
            // Вызов событий тика для модулей
            ModuleManager.onTick();
        });
        
        System.out.println("[CustomClient] Клиент успешно инициализирован!");
        System.out.println("[CustomClient] Нажмите Правый Shift для открытия ClickGUI");
    }
}
