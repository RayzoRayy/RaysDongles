package com.rbs.slurpiesdongles.gui;

import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.client.resources.I18n;

import java.io.IOException;

public class GuiDay extends GuiScreen {

    GuiButton sixAM;
    GuiButton sevenAM;
    GuiButton eightAM;
    GuiButton nineAM;
    GuiButton tenAM;
    GuiButton elevenAM;
    GuiButton twelvePM;
    GuiButton onePM;
    GuiButton twoPM;
    GuiButton threePM;
    GuiButton fourPM;
    GuiButton fivePM;

    GuiButton exit;


    @Override
    public void initGui() {

        buttonList.clear();


        buttonList.add(sixAM = new GuiButton(0, this.width / 2 - 100, this.height / 6 + 24 - 6, 95, 20, I18n.format("0(06:00AM)", new Object[0])));
        buttonList.add(sevenAM = new GuiButton(1, this.width / 2 + 6, this.height / 6 + 24 - 6, 95, 20, I18n.format("1000(07:00AM)", new Object[0])));

        buttonList.add(eightAM = new GuiButton(2, this.width / 2 - 100, this.height / 6 + 48 - 6, 95, 20, I18n.format("2000(08:00AM)", new Object[0])));
        buttonList.add(nineAM = new GuiButton(3, this.width / 2 + 6, this.height / 6 + 48 - 6, 95, 20, I18n.format("3000(09:00AM)", new Object[0])));

        buttonList.add(tenAM = new GuiButton(4, this.width / 2 - 100, this.height / 6 + 72 - 6, 95, 20, I18n.format("4000(10:00AM)", new Object[0])));
        buttonList.add(elevenAM = new GuiButton(5, this.width / 2 + 6, this.height / 6 + 72 - 6, 95, 20, I18n.format("5000(11:00AM)", new Object[0])));

        buttonList.add(twelvePM = new GuiButton(6, this.width / 2 - 100, this.height / 6 + 96 - 6, 95, 20, I18n.format("6000(12:00PM)", new Object[0])));
        buttonList.add(onePM = new GuiButton(7, this.width / 2 + 6, this.height / 6 + 96 - 6, 95, 20, I18n.format("7000(13:00PM)", new Object[0])));

        buttonList.add(twoPM = new GuiButton(8, this.width / 2 - 100, this.height / 6 + 120 - 6, 95, 20, I18n.format("8000(14:00PM)", new Object[0])));
        buttonList.add(threePM = new GuiButton(9, this.width / 2 + 6, this.height / 6 + 120 - 6, 95, 20, I18n.format("9000(15:00PM)", new Object[0])));

        buttonList.add(fourPM = new GuiButton(10, this.width / 2 - 100, this.height / 6 + 144 - 6, 95, 20, I18n.format("10000(16:00PM)", new Object[0])));
        buttonList.add(fivePM = new GuiButton(11, this.width / 2 + 6, this.height / 6 + 144 - 6, 95, 20, I18n.format("11000(17:00PM)", new Object[0])));

        buttonList.add(exit = new GuiButton(12, this.width / 2 - 100, this.height / 6 + 168, I18n.format("Return to Wand Options", new Object[0])));

        super.initGui();
    }

    @Override
    protected void actionPerformed(GuiButton button) throws IOException {
        switch (button.id) {
            case 0:
                mc.player.sendChatMessage("/time set 0");
                break;
            case 1:
                mc.player.sendChatMessage("/time set 1000");
                break;
            case 2:
                mc.player.sendChatMessage("/time set 2000");
                break;
            case 3:
                mc.player.sendChatMessage("/time set 3000");
                break;
            case 4:
                mc.player.sendChatMessage("/time set 4000");
                break;
            case 5:
                mc.player.sendChatMessage("/time set 5000");
                break;
            case 6:
                mc.player.sendChatMessage("/time set 6000");
                break;
            case 7:
                mc.player.sendChatMessage("/time set 7000");
                break;
            case 8:
                mc.player.sendChatMessage("/time set 8000");
                break;
            case 9:
                mc.player.sendChatMessage("/time set 9000");
                break;
            case 10:
                mc.player.sendChatMessage("/time set 10000");
                break;
            case 11:
                mc.player.sendChatMessage("/time set 11000");
                break;
            case 12:
                this.mc.displayGuiScreen(new GuiTimeWand());
                break;
        }

        super.actionPerformed(button);
    }

    @Override
    public void drawScreen(int x, int y, float partialTicks) {

        this.drawDefaultBackground();
        super.drawScreen(x, y, partialTicks);
    }
}
