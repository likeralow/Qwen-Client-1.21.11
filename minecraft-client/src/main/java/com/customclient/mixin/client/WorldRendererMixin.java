package com.customclient.mixin.client;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import net.minecraft.client.render.WorldRenderer;

import com.customclient.event.EventSystem;

/**
 * Миксин для WorldRenderer - рендеринг мира
 */
@Mixin(WorldRenderer.class)
public class WorldRendererMixin {
    
    @Inject(method = "render", at = @At("TAIL"))
    private void onRenderTail(CallbackInfo ci) {
        // Вызываем событие после рендера мира для оверлеев
        EventSystem.RENDER_WORLD.invoke(new EventSystem.RenderWorldEvent(0f));
    }
}
