package com.example.client.event;

/**
 * Менеджер событий клиента
 */
public class EventManager {
    public static final Event<RenderWorldEvent> RENDER_WORLD = new Event<>();
    public static final Event<RenderGuiEvent> RENDER_GUI = new Event<>();

    public static void init() {
        // Инициализация менеджера событий
    }
}
