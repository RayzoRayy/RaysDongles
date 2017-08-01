package com.rbs.slurpiesdongles.init;

import com.rbs.slurpiesdongles.SlurpiesDongles;
import net.minecraft.init.SoundEvents;
import net.minecraftforge.common.util.EnumHelper;

/**
 * Created by Consular on 8/1/2017.
 */
public class Configuration {

    public static void syncConfig() {
        ConfigPreInit.sapphireArmor = EnumHelper.addArmorMaterial("sapphire_armor", "SlurpiesDongles:sapphire",
                SlurpiesDongles.Config.get("SlurpiesDongles.sapphire_armor", "Durability", 5).getInt(),
                new int[]{SlurpiesDongles.Config.get("SlurpiesDongles.sapphire_armor", "Head", 1).getInt(),
                        SlurpiesDongles.Config.get("SlurpiesDongles.sapphire_armor", "Chest", 2).getInt(),
                        SlurpiesDongles.Config.get("SlurpiesDongles.sapphire_armor", "Legs", 3).getInt(),
                        SlurpiesDongles.Config.get("SlurpiesDongles.sapphire_armor", "Feet", 1).getInt()},
                SlurpiesDongles.Config.get("SlurpiesDongles.sapphire_armor", "Enchantability", 12).getInt(),
                SoundEvents.ITEM_ARMOR_EQUIP_DIAMOND,
                (float) SlurpiesDongles.Config.get("SlurpiesDongles.sapphire_armor", "Toughness", 0.0D).getDouble(0.0D));

        if (SlurpiesDongles.Config.hasChanged()) {
            SlurpiesDongles.Config.save();
        }
    }
}
