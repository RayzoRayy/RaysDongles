package com.rbs.slurpiesdongles.gui;

import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.client.resources.I18n;
import org.lwjgl.input.Mouse;

import java.io.IOException;

public class GuiTimeWand extends GuiScreen {

    protected String title = "Time Wand Options";

    GuiButton day;
    GuiButton night;
    GuiButton exit;

    @Override
    public void initGui() {

        this.title = I18n.format("Time Wand Options", new Object[0]);
        buttonList.clear();
        buttonList.add(exit = new GuiButton(0, this.width / 2 - 100, this.height / 6 + 18, I18n.format("menu.returnToGame", new Object[0])));
        buttonList.add(day = new GuiButton(1, this.width / 2 - 100, this.height / 6 + 48 - 6, 95, 20, I18n.format("Day", new Object[0])));
        buttonList.add(night = new GuiButton(2, this.width / 2 + 6, this.height / 6 + 48 - 6, 95, 20, I18n.format("Night", new Object[0])));

        super.initGui();
    }

    @Override
    public void drawScreen(int x, int y, float partialTicks) {
        this.drawDefaultBackground();
        this.drawCenteredString(this.fontRenderer, this.title, this.width / 2, 15, 16777215);
        super.drawScreen(x, y, partialTicks);

    }

    @Override
    protected void actionPerformed(GuiButton button) throws IOException {

        switch (button.id) {
            case 0:
                this.mc.displayGuiScreen((GuiScreen)null);
                this.mc.setIngameFocus();
                break;
            case 1:
                mc.displayGuiScreen(new GuiDay());
                break;
            case 2:
                mc.displayGuiScreen(new GuiNight());
                break;
        }
    }
}
