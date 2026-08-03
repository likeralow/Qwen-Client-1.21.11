package com.example.client.gui;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.client.gui.components.Button;
import net.minecraft.client.gui.screens.Screen;
import net.minecraft.network.chat.Component;

/**
 * Пример кастомного GUI экрана
 */
public class CustomGuiScreen extends Screen {
    private final Screen parent;

    public CustomGuiScreen(Screen parent) {
        super(Component.literal("Custom Client GUI"));
        this.parent = parent;
    }

    @Override
    protected void init() {
        // Кнопка закрытия
        this.addRenderableWidget(Button.builder(Component.literal("Close"), button -> {
            this.onClose();
        }).bounds(this.width / 2 - 50, this.height / 2 + 20, 100, 20).build());

        // Кнопка примера действия
        this.addRenderableWidget(Button.builder(Component.literal("Click Me!"), button -> {
            Minecraft.getInstance().player.displayClientMessage(Component.literal("Button clicked!"), true);
        }).bounds(this.width / 2 - 50, this.height / 2 - 20, 100, 20).build());
    }

    @Override
    public void render(GuiGraphics guiGraphics, int mouseX, int mouseY, float partialTicks) {
        // Рендеринг фона
        this.renderBackground(guiGraphics, mouseX, mouseY, partialTicks);
        
        // Заголовок
        guiGraphics.drawCenteredString(this.font, this.title, this.width / 2, 40, 0xFFFFFF);
        
        // Информация о клиенте
        guiGraphics.drawString(this.font, "Custom Minecraft Client v1.0", 10, 10, 0x00FF00);
        guiGraphics.drawString(this.font, "Features: Mixins, Events, Custom Render", 10, 25, 0xFFFF00);

        super.render(guiGraphics, mouseX, mouseY, partialTicks);
    }

    @Override
    public boolean shouldCloseOnEsc() {
        return true;
    }

    @Override
    public void onClose() {
        if (this.parent != null) {
            Minecraft.getInstance().setScreen(parent);
        } else {
            super.onClose();
        }
    }
}
