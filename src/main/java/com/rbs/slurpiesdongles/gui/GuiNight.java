package com.rbs.slurpiesdongles.gui;

import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.client.resources.I18n;

import java.io.IOException;

public class GuiNight extends GuiScreen {

    GuiButton sixPM;
    GuiButton sevenPM;
    GuiButton eightPM;
    GuiButton ninePM;
    GuiButton tenPM;
    GuiButton elevenPM;
    GuiButton twelveAM;
    GuiButton oneAM;
    GuiButton twoAM;
    GuiButton threeAM;
    GuiButton fourAM;
    GuiButton fiveAM;

    GuiButton exit;


    @Override
    public void initGui() {

        buttonList.clear();
        buttonList.add(sixPM = new GuiButton(0, this.width / 2 - 100, this.height / 6 + 24 - 6, 95, 20, I18n.format("12000(18:00PM)", new Object[0])));
        buttonList.add(sevenPM = new GuiButton(1, this.width / 2 + 6, this.height / 6 + 24 - 6, 95, 20, I18n.format("13000(19:00PM)", new Object[0])));

        buttonList.add(eightPM = new GuiButton(2, this.width / 2 - 100, this.height / 6 + 48 - 6, 95, 20, I18n.format("14000(20:00PM)", new Object[0])));
        buttonList.add(ninePM = new GuiButton(3, this.width / 2 + 6, this.height / 6 + 48 - 6, 95, 20, I18n.format("15000(21:00PM)", new Object[0])));

        buttonList.add(tenPM = new GuiButton(4, this.width / 2 - 100, this.height / 6 + 72 - 6, 95, 20, I18n.format("16000(22:00PM)", new Object[0])));
        buttonList.add(elevenPM = new GuiButton(5, this.width / 2 + 6, this.height / 6 + 72 - 6, 95, 20, I18n.format("17000(23:00PM)", new Object[0])));

        buttonList.add(twelveAM = new GuiButton(6, this.width / 2 - 100, this.height / 6 + 96 - 6, 95, 20, I18n.format("18000(00:00AM)", new Object[0])));
        buttonList.add(oneAM = new GuiButton(7, this.width / 2 + 6, this.height / 6 + 96 - 6, 95, 20, I18n.format("19000(01:00AM)", new Object[0])));

        buttonList.add(twoAM = new GuiButton(8, this.width / 2 - 100, this.height / 6 + 120 - 6, 95, 20, I18n.format("20000(02:00AM)", new Object[0])));
        buttonList.add(threeAM = new GuiButton(9, this.width / 2 + 6, this.height / 6 + 120 - 6, 95, 20, I18n.format("21000(03:00AM)", new Object[0])));

        buttonList.add(fourAM = new GuiButton(10, this.width / 2 - 100, this.height / 6 + 144 - 6, 95, 20, I18n.format("22000(04:00AM)", new Object[0])));
        buttonList.add(fiveAM = new GuiButton(11, this.width / 2 + 6, this.height / 6 + 144 - 6, 95, 20, I18n.format("23000(05:00AM)", new Object[0])));

        buttonList.add(exit = new GuiButton(12, this.width / 2 - 100, this.height / 6 + 168, I18n.format("Return to Wand Options", new Object[0])));

        super.initGui();
    }

    @Override
    protected void actionPerformed(GuiButton button) throws IOException {
        switch (button.id) {
            case 0:
                mc.player.sendChatMessage("/time set 12000");
                break;
            case 1:
                mc.player.sendChatMessage("/time set 13000");
                break;
            case 2:
                mc.player.sendChatMessage("/time set 14000");
                break;
            case 3:
                mc.player.sendChatMessage("/time set 15000");
                break;
            case 4:
                mc.player.sendChatMessage("/time set 16000");
                break;
            case 5:
                mc.player.sendChatMessage("/time set 17000");
                break;
            case 6:
                mc.player.sendChatMessage("/time set 18000");
                break;
            case 7:
                mc.player.sendChatMessage("/time set 19000");
                break;
            case 8:
                mc.player.sendChatMessage("/time set 20000");
                break;
            case 9:
                mc.player.sendChatMessage("/time set 21000");
                break;
            case 10:
                mc.player.sendChatMessage("/time set 22000");
                break;
            case 11:
                mc.player.sendChatMessage("/time set 23000");
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
