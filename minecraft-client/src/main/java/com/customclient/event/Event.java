package com.customclient.event;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

/**
 * Базовый класс для всех событий
 */
public class Event<T> {
    private final CopyOnWriteArrayList<EventListener<T>> listeners = new CopyOnWriteArrayList<>();
    
    public void addListener(EventListener<T> listener) {
        listeners.add(listener);
    }
    
    public void removeListener(EventListener<T> listener) {
        listeners.remove(listener);
    }
    
    public void invoke(T event) {
        for (EventListener<T> listener : listeners) {
            listener.onEvent(event);
        }
    }
    
    @FunctionalInterface
    public interface EventListener<T> {
        void onEvent(T event);
    }
}
