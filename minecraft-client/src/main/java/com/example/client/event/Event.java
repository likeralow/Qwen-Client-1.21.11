package com.example.client.event;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Consumer;

/**
 * Простая система событий для клиента
 */
public class Event<T> {
    private final List<T> listeners = new ArrayList<>();

    public void register(T listener) {
        listeners.add(listener);
    }

    public void unregister(T listener) {
        listeners.remove(listener);
    }

    @SuppressWarnings("unchecked")
    public void invoke(Consumer<T> invoker) {
        for (T listener : listeners) {
            invoker.accept(listener);
        }
    }

    public int getListenerCount() {
        return listeners.size();
    }
}
