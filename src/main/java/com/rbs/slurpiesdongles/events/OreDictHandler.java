package com.rbs.slurpiesdongles.events;

import com.rbs.slurpiesdongles.init.SDBlocks;
import com.rbs.slurpiesdongles.init.SDItems;
import net.minecraft.init.Items;
import net.minecraftforge.oredict.OreDictionary;

public class OreDictHandler {

    public static void registerOreDict() {


        //Ores
        OreDictionary.registerOre("oreLignite", SDBlocks.oreLignite);
        OreDictionary.registerOre("gemLignite", SDItems.lignite);
        OreDictionary.registerOre("oreRuby", SDBlocks.oreRuby);
        OreDictionary.registerOre("gemRuby", SDItems.ruby);
        OreDictionary.registerOre("oreSapphire", SDBlocks.oreSapphire);
        OreDictionary.registerOre("gemSapphire", SDItems.sapphire);
        OreDictionary.registerOre("oreTopaz", SDBlocks.oreTopaz);
        OreDictionary.registerOre("gemTopaz", SDItems.topaz);


    }
}

