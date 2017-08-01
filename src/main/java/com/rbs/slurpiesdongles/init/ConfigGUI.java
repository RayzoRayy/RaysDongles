package com.rbs.slurpiesdongles.init;

import com.rbs.slurpiesdongles.SlurpiesDongles;
import net.minecraft.client.gui.GuiScreen;
import net.minecraftforge.common.config.ConfigElement;
import net.minecraftforge.fml.client.config.GuiConfig;

public class ConfigGUI extends GuiConfig {

    public ConfigGUI(GuiScreen screen) {
        super(screen, (new ConfigElement(SlurpiesDongles.Config.getCategory("general"))).getChildElements(), "slurpiesdongles", false, false, GuiConfig.getAbridgedConfigPath(SlurpiesDongles.Config.toString()));
    }
}
