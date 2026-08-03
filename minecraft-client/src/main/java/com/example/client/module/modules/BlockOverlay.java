package com.example.client.module.modules;

import com.example.client.module.Module;
import com.example.client.module.settings.BooleanSetting;
import com.example.client.module.settings.ColorSetting;
import com.example.client.module.settings.FloatSetting;

import java.awt.Color;

/**
 * BlockOverlay - подсветка блока на который смотришь
 */
public class BlockOverlay extends Module {
    private BooleanSetting showOutline;
    private BooleanSetting showFilled;
    private ColorSetting outlineColor;
    private ColorSetting fillColor;
    private FloatSetting alpha;
    
    public BlockOverlay() {
        super("BlockOverlay", "Подсветка блока на который смотришь", Category.RENDER);
        
        this.showOutline = addSetting(new BooleanSetting(
            "Outline",
            "Показывать контур",
            true
        ));
        
        this.showFilled = addSetting(new BooleanSetting(
            "Filled",
            "Показывать заполнение",
            false
        ));
        
        this.outlineColor = addSetting(new ColorSetting(
            "OutlineColor",
            "Цвет контура",
            new Color(0, 255, 0, 255)
        ));
        
        this.fillColor = addSetting(new ColorSetting(
            "FillColor",
            "Цвет заполнения",
            new Color(0, 255, 0, 100)
        ));
        
        this.alpha = addSetting(new FloatSetting(
            "Alpha",
            "Прозрачность заполнения",
            0.4f,
            0.0f,
            1.0f
        ));
    }
    
    @Override
    public void onRender() {
        if (!isEnabled()) return;
        
        // Рендеринг блока через CustomRenderer
        // Получаем блок на который смотрит игрок
        // и рисуем overlay через CustomRenderer.renderBox()
    }
}
