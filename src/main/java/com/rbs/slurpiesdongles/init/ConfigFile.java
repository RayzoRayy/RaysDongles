package com.rbs.slurpiesdongles.init;

import com.rbs.slurpiesdongles.SlurpiesDongles;
import net.minecraft.init.SoundEvents;
import net.minecraftforge.common.util.EnumHelper;

public class ConfigFile {

    public static void SyncConfig() {

        //Armor Materials
       /* ConfigPreInit.ruby_armor = EnumHelper.addArmorMaterial("ruby_armor", "SlurpiesDongles:ruby",
                SlurpiesDongles.Armor.get("SlurpiesDongles.ruby_armor", "Durability", 65).getInt(),
                new int[]{SlurpiesDongles.Armor.get("SlurpiesDongles.ruby_armor", "Head", 3).getInt(),
                        SlurpiesDongles.Armor.get("SlurpiesDongles.ruby_armor", "Legs", 7).getInt(),
                        SlurpiesDongles.Armor.get("SlurpiesDongles.ruby_armor", "Chest", 5).getInt(),
                        SlurpiesDongles.Armor.get("SlurpiesDongles.ruby_armor", "Feet", 3).getInt()},
                SlurpiesDongles.Armor.get("SlurpiesDongles.ruby_armor", "Enchantability", 31).getInt(),
                SoundEvents.ITEM_ARMOR_EQUIP_DIAMOND,
                (float) SlurpiesDongles.Armor.get("SlurpiesDongles.ruby_armor", "Toughness", 2.0D).getDouble(2.0D));

        //ConfigPreInit.sapphire_armor = EnumHelper.addArmorMaterial("sapphire_armor", "SlurpiesDongles:sapphire", 42, new int[5], 27, SoundEvents.ITEM_ARMOR_EQUIP_DIAMOND, 1.0F);
        ConfigPreInit.sapphire_armor = EnumHelper.addArmorMaterial("sapphire_armor", "SlurpiesDongles:sapphire",
                SlurpiesDongles.Armor.get("SlurpiesDongles.sapphire_armor", "Durability", 42).getInt(),
                new int[]{SlurpiesDongles.Armor.get("SlurpiesDongles.sapphire_armor", "Head", 2).getInt(),
                        SlurpiesDongles.Armor.get("SlurpiesDongles.sapphire_armor", "Legs", 6).getInt(),
                        SlurpiesDongles.Armor.get("SlurpiesDongles.sapphire_armor", "Chest", 5).getInt(),
                        SlurpiesDongles.Armor.get("SlurpiesDongles.sapphire_armor", "Feet", 2).getInt()},
                SlurpiesDongles.Armor.get("SlurpiesDongles.sapphire_armor", "Enchantability", 27).getInt(),
                SoundEvents.ITEM_ARMOR_EQUIP_DIAMOND,
                (float) SlurpiesDongles.Armor.get("SlurpiesDongles.sapphire_armor", "Toughness", 1.0D).getDouble(1.0D));

/*
        ConfigPreInit.topaz_armor = EnumHelper.addArmorMaterial("topaz_armor", "SlurpiesDongles:topaz",
                SlurpiesDongles.Armor.get("SlurpiesDongles.topaz_armor", "Durability", 0).getInt(),
                new int[]{SlurpiesDongles.Armor.get("SlurpiesDongles.topaz_armor", "Head", 4).getInt(),
                        SlurpiesDongles.Armor.get("SlurpiesDongles.topaz_armor", "Legs", 5).getInt(),
                        SlurpiesDongles.Armor.get("SlurpiesDongles.topaz_armor", "Chest", 8).getInt(),
                        SlurpiesDongles.Armor.get("SlurpiesDongles.topaz_armor", "Feet", 4).getInt()},
                SlurpiesDongles.Armor.get("SlurpiesDongles.topaz_armor", "Enchantability", 45).getInt(),
                SoundEvents.ITEM_ARMOR_EQUIP_DIAMOND,
                (float) SlurpiesDongles.Armor.get("SlurpiesDongles.topaz_armor", "Toughness", 4.0D).getDouble(4.0D));

        if (SlurpiesDongles.Armor.hasChanged()) {
            SlurpiesDongles.Armor.save();
        }

        //Battle Axe Materials

        ConfigPreInit.diamond_battleaxe_material = EnumHelper.addToolMaterial("diamond_battleaxe_material",
                SlurpiesDongles.Config.get("SlurpiesDongles.diamond_battleaxe_material", "Harvest Level", 0).getInt(),
                SlurpiesDongles.Config.get("SlurpiesDongles.diamond_battleaxe_material", "Max Uses", 1561).getInt(),
                (float) SlurpiesDongles.Config.get("SlurpiesDongles.diamond_battleaxe_material", "EfficiencyOnProperty", 0.0D).getDouble(0.0D),
                (float) SlurpiesDongles.Config.get("SlurpiesDongles.diamond_battleaxe_material", "DamageVsEntity", 6).getInt(),
                SlurpiesDongles.Config.get("SlurpiesDongles.diamond_battleaxe_material", "Enchantability", 17).getInt());

        ConfigPreInit.emerald_battleaxe_material = EnumHelper.addToolMaterial("emerald_battleaxe_material",
                SlurpiesDongles.Config.get("SlurpiesDongles.emerald_battleaxe_material", "Harvest Level", 0).getInt(),
                SlurpiesDongles.Config.get("SlurpiesDongles.emerald_battleaxe_material", "Max Uses", 1024).getInt(),
                (float) SlurpiesDongles.Config.get("SlurpiesDongles.emerald_battleaxe_material", "EfficiencyOnProperty", 0.0D).getDouble(0.0D),
                (float) SlurpiesDongles.Config.get("SlurpiesDongles.emerald_battleaxe_material", "DamageVsEntity", 8).getInt(),
                SlurpiesDongles.Config.get("SlurpiesDongles.emerald_battleaxe_material", "Enchantability", 42).getInt());

        ConfigPreInit.ruby_battleaxe_material = EnumHelper.addToolMaterial("ruby_battleaxe_material",
                SlurpiesDongles.Config.get("SlurpiesDongles.ruby_battleaxe_material", "Harvest Level", 0).getInt(),
                SlurpiesDongles.Config.get("SlurpiesDongles.ruby_battleaxe_material", "Max Uses", 721).getInt(),
                (float) SlurpiesDongles.Config.get("SlurpiesDongles.ruby_battleaxe_material", "EfficiencyOnProperty", 0.0D).getDouble(0.0D),
                (float) SlurpiesDongles.Config.get("SlurpiesDongles.ruby_battleaxe_material", "DamageVsEntity", 5).getInt(),
                SlurpiesDongles.Config.get("SlurpiesDongles.ruby_battleaxe_material", "Enchantability", 31).getInt());

        ConfigPreInit.sapphire_battleaxe_material = EnumHelper.addToolMaterial("sapphire_battleaxe_material",
                SlurpiesDongles.Config.get("SlurpiesDongles.sapphire_battleaxe_material", "Harvest Level", 0).getInt(),
                SlurpiesDongles.Config.get("SlurpiesDongles.sapphire_battleaxe_material", "Max Uses", 492).getInt(),
                (float) SlurpiesDongles.Config.get("SlurpiesDongles.sapphire_battleaxe_material", "EfficiencyOnProperty", 0.0D).getDouble(0.0D),
                (float) SlurpiesDongles.Config.get("SlurpiesDongles.sapphire_battleaxe_material", "DamageVsEntity", 3).getInt(),
                SlurpiesDongles.Config.get("SlurpiesDongles.sapphire_battleaxe_material", "Enchantability", 27).getInt());

        ConfigPreInit.topaz_battleaxe_material = EnumHelper.addToolMaterial("topaz_battleaxe_material",
                SlurpiesDongles.Config.get("SlurpiesDongles.topaz_battleaxe_material", "Harvest Level", 0).getInt(),
                SlurpiesDongles.Config.get("SlurpiesDongles.topaz_battleaxe_material", "Max Uses", 0).getInt(),
                (float) SlurpiesDongles.Config.get("SlurpiesDongles.topaz_battleaxe_material", "EfficiencyOnProperty", 0.0D).getDouble(0.0D),
                (float) SlurpiesDongles.Config.get("SlurpiesDongles.topaz_battleaxe_material", "DamageVsEntity", 11).getInt(),
                SlurpiesDongles.Config.get("SlurpiesDongles.topaz_battleaxe_material", "Enchantability", 45).getInt());

        //Paxel Materials
        ConfigPreInit.diamond_paxel_material = EnumHelper.addToolMaterial("diamond_paxel_material",
                SlurpiesDongles.Config.get("SlurpiesDongles.diamond_paxel_material", "Harvest Level", 3).getInt(),
                SlurpiesDongles.Config.get("SlurpiesDongles.diamond_paxel_material", "Max Uses", 4683).getInt(),
                (float) SlurpiesDongles.Config.get("SlurpiesDongles.diamond_paxel_material", "EfficiencyOnProperty", 8.0D).getDouble(0.0D),
                (float) SlurpiesDongles.Config.get("SlurpiesDongles.diamond_paxel_material", "DamageVsEntity", 3).getInt(),
                SlurpiesDongles.Config.get("SlurpiesDongles.diamond_paxel_material", "Enchantability", 10).getInt());

        ConfigPreInit.gold_paxel_material = EnumHelper.addToolMaterial("gold_paxel_material",
                SlurpiesDongles.Config.get("SlurpiesDongles.gold_paxel_material", "Harvest Level", 0).getInt(),
                SlurpiesDongles.Config.get("SlurpiesDongles.gold_paxel_material", "Max Uses", 96).getInt(),
                (float) SlurpiesDongles.Config.get("SlurpiesDongles.gold_paxel_material", "EfficiencyOnProperty", 12.0D).getDouble(0.0D),
                (float) SlurpiesDongles.Config.get("SlurpiesDongles.gold_paxel_material", "DamageVsEntity", 0).getInt(),
                SlurpiesDongles.Config.get("SlurpiesDongles.gold_paxel_material", "Enchantability", 22).getInt());

        ConfigPreInit.iron_paxel_material = EnumHelper.addToolMaterial("iron_paxel_material",
                SlurpiesDongles.Config.get("SlurpiesDongles.iron_paxel_material", "Harvest Level", 2).getInt(),
                SlurpiesDongles.Config.get("SlurpiesDongles.iron_paxel_material", "Max Uses", 750).getInt(),
                (float) SlurpiesDongles.Config.get("SlurpiesDongles.iron_paxel_material", "EfficiencyOnProperty", 6.0D).getDouble(0.0D),
                (float) SlurpiesDongles.Config.get("SlurpiesDongles.iron_paxel_material", "DamageVsEntity", 2).getInt(),
                SlurpiesDongles.Config.get("SlurpiesDongles.iron_paxel_material", "Enchantability", 14).getInt());

        ConfigPreInit.stone_paxel_material = EnumHelper.addToolMaterial("stone_paxel_material",
                SlurpiesDongles.Config.get("SlurpiesDongles.stone_paxel_material", "Harvest Level", 1).getInt(),
                SlurpiesDongles.Config.get("SlurpiesDongles.stone_paxel_material", "Max Uses", 393).getInt(),
                (float) SlurpiesDongles.Config.get("SlurpiesDongles.stone_paxel_material", "EfficiencyOnProperty", 4.0D).getDouble(0.0D),
                (float) SlurpiesDongles.Config.get("SlurpiesDongles.stone_paxel_material", "DamageVsEntity", 1).getInt(),
                SlurpiesDongles.Config.get("SlurpiesDongles.stone_paxel_material", "Enchantability", 5).getInt());

        ConfigPreInit.ruby_paxel_material = EnumHelper.addToolMaterial("ruby_paxel_material",
                SlurpiesDongles.Config.get("SlurpiesDongles.ruby_paxel_material", "Harvest Level", 3).getInt(),
                SlurpiesDongles.Config.get("SlurpiesDongles.ruby_paxel_material", "Max Uses", 2163).getInt(),
                (float) SlurpiesDongles.Config.get("SlurpiesDongles.ruby_paxel_material", "EfficiencyOnProperty", 12.0D).getDouble(0.0D),
                (float) SlurpiesDongles.Config.get("SlurpiesDongles.ruby_paxel_material", "DamageVsEntity", 1).getInt(),
                SlurpiesDongles.Config.get("SlurpiesDongles.ruby_paxel_material", "Enchantability", 31).getInt());

        ConfigPreInit.sapphire_paxel_material = EnumHelper.addToolMaterial("sapphire_paxel_material",
                SlurpiesDongles.Config.get("SlurpiesDongles.sapphire_paxel_material", "Harvest Level", 3).getInt(),
                SlurpiesDongles.Config.get("SlurpiesDongles.sapphire_paxel_material", "Max Uses", 1476).getInt(),
                (float) SlurpiesDongles.Config.get("SlurpiesDongles.sapphire_paxel_material", "EfficiencyOnProperty", 8.0D).getDouble(0.0D),
                (float) SlurpiesDongles.Config.get("SlurpiesDongles.sapphire_paxel_material", "DamageVsEntity", 1).getInt(),
                SlurpiesDongles.Config.get("SlurpiesDongles.sapphire_paxel_material", "Enchantability", 27).getInt());

        ConfigPreInit.topaz_paxel_material = EnumHelper.addToolMaterial("topaz_paxel_material",
                SlurpiesDongles.Config.get("SlurpiesDongles.topaz_paxel_material", "Harvest Level", 3).getInt(),
                SlurpiesDongles.Config.get("SlurpiesDongles.topaz_paxel_material", "Max Uses", 0).getInt(),
                (float) SlurpiesDongles.Config.get("SlurpiesDongles.topaz_paxel_material", "EfficiencyOnProperty", 8.0D).getDouble(0.0D),
                (float) SlurpiesDongles.Config.get("SlurpiesDongles.topaz_paxel_material", "DamageVsEntity", 1).getInt(),
                SlurpiesDongles.Config.get("SlurpiesDongles.topaz_paxel_material", "Enchantability", 20).getInt());

        //Sword Materials
        ConfigPreInit.ruby_sword_material = EnumHelper.addToolMaterial("ruby_sword_material",
                SlurpiesDongles.Config.get("SlurpiesDongles.ruby_sword_material", "Harvest Level", 0).getInt(),
                SlurpiesDongles.Config.get("SlurpiesDongles.ruby_sword_material", "Max Uses", 721).getInt(),
                (float) SlurpiesDongles.Config.get("SlurpiesDongles.ruby_sword_material", "EfficiencyOnProperty", 0.0D).getDouble(0.0D),
                (float) SlurpiesDongles.Config.get("SlurpiesDongles.ruby_sword_material", "DamageVsEntity", 3).getInt(),
                SlurpiesDongles.Config.get("SlurpiesDongles.ruby_sword_material", "Enchantability", 31).getInt());

        ConfigPreInit.sapphire_sword_material = EnumHelper.addToolMaterial("sapphire_sword_material",
                SlurpiesDongles.Config.get("SlurpiesDongles.sapphire_sword_material", "Harvest Level", 0).getInt(),
                SlurpiesDongles.Config.get("SlurpiesDongles.sapphire_sword_material", "Max Uses", 492).getInt(),
                (float) SlurpiesDongles.Config.get("SlurpiesDongles.sapphire_sword_material", "EfficiencyOnProperty", 0.0D).getDouble(0.0D),
                (float) SlurpiesDongles.Config.get("SlurpiesDongles.sapphire_sword_material", "DamageVsEntity", 2).getInt(),
                SlurpiesDongles.Config.get("SlurpiesDongles.sapphire_sword_material", "Enchantability", 27).getInt());

        ConfigPreInit.topaz_sword_material = EnumHelper.addToolMaterial("topaz_sword_material",
                SlurpiesDongles.Config.get("SlurpiesDongles.topaz_sword_material", "Harvest Level", 0).getInt(),
                SlurpiesDongles.Config.get("SlurpiesDongles.topaz_sword_material", "Max Uses", 0).getInt(),
                (float) SlurpiesDongles.Config.get("SlurpiesDongles.topaz_sword_material", "EfficiencyOnProperty", 0.0D).getDouble(0.0D),
                (float) SlurpiesDongles.Config.get("SlurpiesDongles.topaz_sword_material", "DamageVsEntity", 10).getInt(),
                SlurpiesDongles.Config.get("SlurpiesDongles.topaz_sword_material", "Enchantability", 20).getInt());

        //Tool Materials
        ConfigPreInit.ruby_material = EnumHelper.addToolMaterial("ruby_material",
                SlurpiesDongles.Config.get("SlurpiesDongles.ruby_material", "Harvest Level", 3).getInt(),
                SlurpiesDongles.Config.get("SlurpiesDongles.ruby_material", "Max Uses", 721).getInt(),
                (float) SlurpiesDongles.Config.get("SlurpiesDongles.ruby_material", "EfficiencyOnProperty", 12.0D).getDouble(0.0D),
                (float) SlurpiesDongles.Config.get("SlurpiesDongles.ruby_material", "DamageVsEntity", 1).getInt(),
                SlurpiesDongles.Config.get("SlurpiesDongles.ruby_material", "Enchantability", 31).getInt());

        ConfigPreInit.sapphire_material = EnumHelper.addToolMaterial("ruby_material",
                SlurpiesDongles.Config.get("SlurpiesDongles.sapphire_material", "Harvest Level", 3).getInt(),
                SlurpiesDongles.Config.get("SlurpiesDongles.sapphire_material", "Max Uses", 492).getInt(),
                (float) SlurpiesDongles.Config.get("SlurpiesDongles.sapphire_material", "EfficiencyOnProperty", 8.0D).getDouble(0.0D),
                (float) SlurpiesDongles.Config.get("SlurpiesDongles.sapphire_material", "DamageVsEntity", 1).getInt(),
                SlurpiesDongles.Config.get("SlurpiesDongles.sapphire_material", "Enchantability", 27).getInt());

        ConfigPreInit.topaz_material = EnumHelper.addToolMaterial("topaz_material",
                SlurpiesDongles.Config.get("SlurpiesDongles.topaz_material", "Harvest Level", 4).getInt(),
                SlurpiesDongles.Config.get("SlurpiesDongles.topaz_material", "Max Uses", 0).getInt(),//0 = unbreakable
                (float) SlurpiesDongles.Config.get("SlurpiesDongles.topaz_material", "EfficiencyOnProperty", 15.0D).getDouble(0.0D),
                (float) SlurpiesDongles.Config.get("SlurpiesDongles.topaz_material", "DamageVsEntity", 1).getInt(),
                SlurpiesDongles.Config.get("SlurpiesDongles.topaz_material", "Enchantability", 20).getInt());

        ConfigPreInit.vmpick_material = EnumHelper.addToolMaterial("vmpick_material",
                SlurpiesDongles.Config.get("SlurpiesDongles.vmpick_material", "Harvest Level", 1).getInt(),
                SlurpiesDongles.Config.get("SlurpiesDongles.vmpick_material", "Max Uses", 22000).getInt(),
                (float) SlurpiesDongles.Config.get("SlurpiesDongles.vmpick_material", "EfficiencyOnProperty", 4.0D).getDouble(0.0D),
                (float) SlurpiesDongles.Config.get("SlurpiesDongles.vmpick_material", "DamageVsEntity", 1).getInt(),
                SlurpiesDongles.Config.get("SlurpiesDongles.vmpick_material", "Enchantability", 10).getInt());

        if (SlurpiesDongles.Config.hasChanged()) {
            SlurpiesDongles.Config.save();
        }*/



    }
}
