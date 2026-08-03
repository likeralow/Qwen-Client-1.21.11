package com.example.client.event;

/**
 * Событие рендеринGUI
 */
public class RenderGuiEvent {
    private final float partialTicks;

    public RenderGuiEvent(float partialTicks) {
        this.partialTicks = partialTicks;
    }

    public float getPartialTicks() {
        return partialTicks;
    }
}
