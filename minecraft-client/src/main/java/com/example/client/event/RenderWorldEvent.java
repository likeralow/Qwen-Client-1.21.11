package com.example.client.event;

/**
 * Событие рендеринга мира
 */
public class RenderWorldEvent {
    private final float partialTicks;

    public RenderWorldEvent(float partialTicks) {
        this.partialTicks = partialTicks;
    }

    public float getPartialTicks() {
        return partialTicks;
    }
}
