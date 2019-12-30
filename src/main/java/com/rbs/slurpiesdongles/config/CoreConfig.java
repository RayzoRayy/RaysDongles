package com.rbs.slurpiesdongles.config;

import com.electronwill.nightconfig.core.file.CommentedFileConfig;
import com.electronwill.nightconfig.core.io.WritingMode;
import net.minecraftforge.common.ForgeConfigSpec;

import java.nio.file.Path;

public class CoreConfig {

    public static final ForgeConfigSpec.Builder BUILDER = new ForgeConfigSpec.Builder();

    public static final ForgeConfigSpec SPEC;

    static {
        BUILDER.push("Configs");
        ConfigGeneral.init(BUILDER);
        BUILDER.pop();

        SPEC = BUILDER.build();
    }

    public static void loadConfig(ForgeConfigSpec config, Path path)
    {
        CommentedFileConfig file = CommentedFileConfig.builder(path).sync().autosave().writingMode(WritingMode.REPLACE).build();
        file.load();
        config.setConfig(file);
    }
}
