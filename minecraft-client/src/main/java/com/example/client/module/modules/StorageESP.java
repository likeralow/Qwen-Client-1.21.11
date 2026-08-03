package com.example.client.module.modules;

import com.example.client.module.Module;
import com.example.client.module.settings.BooleanSetting;
import com.example.client.module.settings.ColorSetting;
import com.example.client.module.settings.FloatSetting;
import com.example.client.module.settings.ModeSetting;

import java.awt.Color;

/**
 * StorageESP - подсветка сундуков и других хранилищ
 */
public class StorageESP extends Module {
    private BooleanSetting showChests;
    private BooleanSetting showEnderChests;
    private BooleanSetting showBarrels;
    private BooleanSetting showShulkerBoxes;
    private BooleanSetting showTrappedChests;
    private ModeSetting renderMode;
    private ColorSetting chestColor;
    private FloatSetting range;
    
    public StorageESP() {
        super("StorageESP", "Подсветка сундуков и хранилищ", Category.RENDER);
        
        this.showChests = addSetting(new BooleanSetting(
            "Chests",
            "Показывать обычные сундуки",
            true
        ));
        
        this.showEnderChests = addSetting(new BooleanSetting(
            "EnderChests",
            "Показывать эндер-сундуки",
            true
        ));
        
        this.showBarrels = addSetting(new BooleanSetting(
            "Barrels",
            "Показывать бочки",
            true
        ));
        
        this.showShulkerBoxes = addSetting(new BooleanSetting(
            "ShulkerBoxes",
            "Показывать шалкер-боксы",
            true
        ));
        
        this.showTrappedChests = addSetting(new BooleanSetting(
            "TrappedChests",
            "Показывать сундуки-ловушки",
            false
        ));
        
        this.renderMode = addSetting(new ModeSetting(
            "RenderMode",
            "Режим рендеринга",
            "Outline",
            "Outline", "Filled", "Both"
        ));
        
        this.chestColor = addSetting(new ColorSetting(
            "Color",
            "Цвет подсветки",
            new Color(255, 215, 0, 255) // Золотой цвет
        ));
        
        this.range = addSetting(new FloatSetting(
            "Range",
            "Дальность прорисовки",
            64.0f,
            10.0f,
            128.0f
        ));
    }
    
    @Override
    public void onRender() {
        if (!isEnabled()) return;
        
        // Поиск всех TileEntity в радиусе range
        // Проверка типа (Chest, EnderChest, Barrel, ShulkerBox)
        // Рендеринг через CustomRenderer.renderBox()
    }
}
