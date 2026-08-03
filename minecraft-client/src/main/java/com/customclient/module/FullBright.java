package com.customclient.module;

import net.minecraft.entity.effect.StatusEffects;

import com.customclient.setting.BooleanSetting;

/**
 * FullBright - максимальная яркость
 */
public class FullBright extends Module {
    private final BooleanSetting saturation = new BooleanSetting("Saturation", "Добавляет насыщенность", true);
    
    public FullBright() {
        super("FullBright", "Максимальная яркость", Category.PLAYER);
        addSetting(saturation);
    }
    
    @Override
    public void onEnable() {
        if (mc.player != null && saturation.getValue()) {
            mc.player.addStatusEffect(new net.minecraft.entity.effect.StatusEffectInstance(
                StatusEffects.NIGHT_VISION, Integer.MAX_VALUE, 0, false, false, true));
        }
    }
    
    @Override
    public void onDisable() {
        if (mc.player != null) {
            mc.player.removeStatusEffect(StatusEffects.NIGHT_VISION);
        }
    }
    
    @Override
    public void onTick() {
        if (saturation.getValue() && mc.player != null) {
            mc.player.addStatusEffect(new net.minecraft.entity.effect.StatusEffectInstance(
                StatusEffects.NIGHT_VISION, Integer.MAX_VALUE, 0, false, false, true));
        }
    }
}
