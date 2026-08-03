package com.example.client.module.modules;

import com.example.client.module.Module;
import com.example.client.module.settings.BooleanSetting;
import com.example.client.module.settings.FloatSetting;

/**
 * FullBright - максимальная яркость без гамма-настроек
 */
public class FullBright extends Module {
    private BooleanSetting smoothTransition;
    private FloatSetting brightness;
    
    public FullBright() {
        super("FullBright", "Максимальная яркость в любое время", Category.RENDER);
        
        this.smoothTransition = addSetting(new BooleanSetting(
            "Smooth", 
            "Плавный переход яркости", 
            true
        ));
        
        this.brightness = addSetting(new FloatSetting(
            "Brightness",
            "Уровень яркости",
            1.0f,
            0.5f,
            1.0f
        ));
    }
    
    @Override
    public void onEnable() {
        // Применяем яркость при включении
        applyBrightness();
    }
    
    @Override
    public void onDisable() {
        // Возвращаем обычную яркость при выключении
        resetBrightness();
    }
    
    @Override
    public void onTick() {
        // Обновляем яркость каждый тик
        applyBrightness();
    }
    
    private void applyBrightness() {
        // Здесь будет код установки гаммы через миксин
        // Minecraft.getMinecraft().gameSettings.gammaSetting = brightness.getValue();
    }
    
    private void resetBrightness() {
        // Возврат к стандартной гамме
        // Minecraft.getMinecraft().gameSettings.gammaSetting = 1.0f;
    }
}
