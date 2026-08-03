package com.example.client.mixin;

import com.example.client.CustomClient;
import com.example.client.event.EventManager;
import com.example.client.event.RenderGuiEvent;
import com.example.client.event.RenderWorldEvent;
import net.minecraft.client.Minecraft;
import org.lwjgl.glfw.GLFW;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

/**
 * Миксин для внедрения кастомных событий в рендеринг и обработку ввода
 */
@Mixin(Minecraft.class)
public class MinecraftMixin {

    /**
     * Внедряется после рендеринга мира, вызывает наше событие
     */
    @Inject(method = "renderLevel", at = @At("TAIL"))
    private void onRenderWorld(float partialTicks, long nanoTime, boolean renderBlockOutline, CallbackInfo ci) {
        EventManager.RENDER_WORLD.invoke(event -> {
            // Обработка события рендеринга мира
        });
    }

    /**
     * Внедряется в тик игры для обработки обновлений модулей
     */
    @Inject(method = "tick", at = @At("HEAD"))
    private void onTick(CallbackInfo ci) {
        CustomClient client = CustomClient.getInstance();
        if (client != null) {
            client.onTick();
        }
    }

    /**
     * Перехват нажатий клавиш для обработки биндов модулей и ClickGUI
     */
    @Inject(method = "handleKeyInput", at = @At("HEAD"), cancellable = true)
    private void onKeyInput(int keyCode, int scanCode, int action, int modifiers, CallbackInfo ci) {
        CustomClient client = CustomClient.getInstance();
        if (client != null) {
            client.onKeyPressed(keyCode, scanCode, action, modifiers);
        }
    }

    /**
     * Внедряется после рендеринга GUI
     */
    @Inject(method = "runTick", at = @At("TAIL"))
    private void onRenderGui(boolean renderLevel, CallbackInfo ci) {
        if (renderLevel) {
            EventManager.RENDER_GUI.invoke(event -> {
                // Обработка события рендеринга GUI
            });
        }
    }
}
