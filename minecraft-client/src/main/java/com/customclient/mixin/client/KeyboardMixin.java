package com.customclient.mixin.client;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import net.minecraft.client.Keyboard;
import net.minecraft.client.MinecraftClient;

import com.customclient.module.ModuleManager;

/**
 * Миксин для Keyboard - обработка нажатий клавиш
 */
@Mixin(Keyboard.class)
public class KeyboardMixin {
    
    @Inject(method = "onKey", at = @At("HEAD"))
    private void onKey(long window, int key, int scancode, int action, int modifiers, CallbackInfo ci) {
        if (action == 1) { // Key pressed
            ModuleManager.onKey(key);
        }
    }
}
