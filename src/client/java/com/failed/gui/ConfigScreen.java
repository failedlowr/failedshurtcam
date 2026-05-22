package com.failed.gui;

import com.failed.config.Config;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.client.gui.screens.Screen;
import net.minecraft.client.gui.components.Button;
import net.minecraft.network.chat.Component;

public class ConfigScreen extends Screen {
    private final Screen parent;

    public ConfigScreen(Screen parent) {
        super(Component.literal("FailedsHurtCam Config"));
        this.parent = parent;
    }

    @Override
    protected void init() {
        this.addRenderableWidget(Button.builder(
            Component.literal("Disable HurtCam: " + (Config.disableHurtCam ? "ON" : "OFF")),
            button -> {
                Config.disableHurtCam = !Config.disableHurtCam;
                button.setMessage(Component.literal("Disable HurtCam: " + (Config.disableHurtCam ? "ON" : "OFF")));
                Config.save();
            }
        ).bounds(this.width / 2 - 100, this.height / 2 - 20, 200, 20).build());

        this.addRenderableWidget(Button.builder(
            Component.literal("Done"),
            button -> this.minecraft.setScreen(this.parent)
        ).bounds(this.width / 2 - 100, this.height / 2 + 10, 200, 20).build());
    }

    @Override
    public void render(GuiGraphics graphics, int mouseX, int mouseY, float delta) {
        super.render(graphics, mouseX, mouseY, delta);
        graphics.drawCenteredString(this.font, this.title, this.width / 2, 20, 0xFFFFFF);
    }
}
