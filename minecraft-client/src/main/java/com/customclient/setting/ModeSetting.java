package com.customclient.setting;

/**
 * Настройка выбора режима из списка
 */
public class ModeSetting extends Setting<String> {
    private final String[] modes;
    
    public ModeSetting(String name, String description, String defaultValue, String... modes) {
        super(name, description, defaultValue);
        this.modes = modes;
    }
    
    public String[] getModes() {
        return modes;
    }
    
    public int getCurrentIndex() {
        for (int i = 0; i < modes.length; i++) {
            if (modes[i].equals(value)) {
                return i;
            }
        }
        return 0;
    }
    
    public void nextMode() {
        int index = getCurrentIndex();
        index = (index + 1) % modes.length;
        this.value = modes[index];
    }
    
    public void previousMode() {
        int index = getCurrentIndex();
        index = (index - 1 + modes.length) % modes.length;
        this.value = modes[index];
    }
}
