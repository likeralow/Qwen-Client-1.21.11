package com.example.client.module.settings;

import java.util.List;
import java.util.ArrayList;

/**
 * Настройка с выбором из списка (mode)
 */
public class ModeSetting extends Setting<String> {
    private List<String> modes;
    
    public ModeSetting(String name, String description, String defaultValue, String... modes) {
        super(name, description, defaultValue);
        this.modes = new ArrayList<>();
        for (String mode : modes) {
            this.modes.add(mode);
        }
    }
    
    @Override
    public String getType() {
        return "Mode";
    }
    
    public List<String> getModes() {
        return modes;
    }
    
    public int getCurrentIndex() {
        return modes.indexOf(value);
    }
    
    public void nextMode() {
        int index = getCurrentIndex();
        if (index >= 0 && index < modes.size() - 1) {
            this.value = modes.get(index + 1);
        } else {
            this.value = modes.get(0);
        }
    }
}
