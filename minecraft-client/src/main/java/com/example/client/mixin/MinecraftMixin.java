package com.example.client.mixin;

import com.example.client.event.EventManager;
import com.example.client.event.RenderGuiEvent;
import com.example.client.event.RenderWorldEvent;
import net.minecraft.client.Minecraft;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

/**
 * Миксин для внедрения кастомных событий в рендеринг
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
