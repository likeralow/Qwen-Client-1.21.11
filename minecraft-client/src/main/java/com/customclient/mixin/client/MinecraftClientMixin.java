package com.customclient.mixin.client;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import net.minecraft.client.MinecraftClient;
import net.minecraft.client.gui.screen.Screen;

import com.customclient.event.EventSystem;
import com.customclient.module.ModuleManager;

/**
 * Миксин для MinecraftClient - рендеринг мира
 */
@Mixin(MinecraftClient.class)
public class MinecraftClientMixin {
    
    @Inject(method = "render", at = @At("HEAD"))
    private void onRenderHead(boolean tick, CallbackInfo ci) {
        // Вызываем событие рендера мира для ESP и других визуальных эффектов
        ModuleManager.onRenderWorld(0f);
    }
}
