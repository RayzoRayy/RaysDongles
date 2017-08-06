package com.rbs.slurpiesdongles.init;

import com.rbs.slurpiesdongles.SlurpiesDongles;
import net.minecraft.init.SoundEvents;
import net.minecraftforge.common.util.EnumHelper;

public class ConfigFile {

    public static void SyncConfig() {

        //Armor Materials
        ConfigPreInit.rubyArmor = EnumHelper.addArmorMaterial("ruby_armor", "SlurpiesDongles:ruby",
                SlurpiesDongles.Armor.get("SlurpiesDongles.ruby_armor", "Durability", 65).getInt(),
                new int[]{SlurpiesDongles.Armor.get("SlurpiesDongles.ruby_armor", "Head", 3).getInt(),
                        SlurpiesDongles.Armor.get("SlurpiesDongles.ruby_armor", "Legs", 7).getInt(),
                        SlurpiesDongles.Armor.get("SlurpiesDongles.ruby_armor", "Chest", 5).getInt(),
                        SlurpiesDongles.Armor.get("SlurpiesDongles.ruby_armor", "Feet", 3).getInt()},
                SlurpiesDongles.Armor.get("SlurpiesDongles.ruby_armor", "Enchantability", 31).getInt(),
                SoundEvents.ITEM_ARMOR_EQUIP_DIAMOND,
                (float) SlurpiesDongles.Armor.get("SlurpiesDongles.ruby_armor", "Toughness", 2.0D).getDouble(2.0D));

        ConfigPreInit.sapphireArmor = EnumHelper.addArmorMaterial("sapphire_armor", "SlurpiesDongles:sapphire",
                SlurpiesDongles.Armor.get("SlurpiesDongles.sapphire_armor", "Durability", 42).getInt(),
                new int[]{SlurpiesDongles.Armor.get("SlurpiesDongles.sapphire_armor", "Head", 2).getInt(),
                        SlurpiesDongles.Armor.get("SlurpiesDongles.sapphire_armor", "Legs", 6).getInt(),
                        SlurpiesDongles.Armor.get("SlurpiesDongles.sapphire_armor", "Chest", 5).getInt(),
                        SlurpiesDongles.Armor.get("SlurpiesDongles.sapphire_armor", "Feet", 2).getInt()},
                SlurpiesDongles.Armor.get("SlurpiesDongles.sapphire_armor", "Enchantability", 27).getInt(),
                SoundEvents.ITEM_ARMOR_EQUIP_DIAMOND,
                (float) SlurpiesDongles.Armor.get("SlurpiesDongles.sapphire_armor", "Toughness", 1.0D).getDouble(1.0D));


        ConfigPreInit.topazArmor = EnumHelper.addArmorMaterial("topaz_armor", "SlurpiesDongles:topaz",
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

        ConfigPreInit.diamondBattleaxeMaterial = EnumHelper.addToolMaterial("diamond_battleaxe_material",
                SlurpiesDongles.Config.get("SlurpiesDongles.diamond_battleaxe_material", "Harvest Level", 0).getInt(),
                SlurpiesDongles.Config.get("SlurpiesDongles.diamond_battleaxe_material", "Max Uses", 1561).getInt(),
                (float) SlurpiesDongles.Config.get("SlurpiesDongles.diamond_battleaxe_material", "EfficiencyOnProperty", 0.0D).getDouble(0.0D),
                (float) SlurpiesDongles.Config.get("SlurpiesDongles.diamond_battleaxe_material", "DamageVsEntity", 6).getInt(),
                SlurpiesDongles.Config.get("SlurpiesDongles.diamond_battleaxe_material", "Enchantability", 17).getInt());

        ConfigPreInit.emeraldBattleaxeMaterial = EnumHelper.addToolMaterial("emerald_battleaxe_material",
                SlurpiesDongles.Config.get("SlurpiesDongles.emerald_battleaxe_material", "Harvest Level", 0).getInt(),
                SlurpiesDongles.Config.get("SlurpiesDongles.emerald_battleaxe_material", "Max Uses", 2149).getInt(),
                (float) SlurpiesDongles.Config.get("SlurpiesDongles.emerald_battleaxe_material", "EfficiencyOnProperty", 0.0D).getDouble(0.0D),
                (float) SlurpiesDongles.Config.get("SlurpiesDongles.emerald_battleaxe_material", "DamageVsEntity", 8).getInt(),
                SlurpiesDongles.Config.get("SlurpiesDongles.emerald_battleaxe_material", "Enchantability", 42).getInt());

        ConfigPreInit.goldBattleaxeMaterial = EnumHelper.addToolMaterial("gold_battleaxe_material",
                SlurpiesDongles.Config.get("SlurpiesDongles.gold_battleaxe_material", "Harvest Level", 0).getInt(),
                SlurpiesDongles.Config.get("SlurpiesDongles.gold_battleaxe_material", "Max Uses", 32).getInt(),
                (float) SlurpiesDongles.Config.get("SlurpiesDongles.gold_battleaxe_material", "EfficiencyOnProperty", 0.0D).getDouble(0.0D),
                (float) SlurpiesDongles.Config.get("SlurpiesDongles.gold_battleaxe_material", "DamageVsEntity", 1).getInt(),
                SlurpiesDongles.Config.get("SlurpiesDongles.gold_battleaxe_material", "Enchantability", 22).getInt());

        ConfigPreInit.ironBattleaxeMaterial = EnumHelper.addToolMaterial("iron_battleaxe_material",
                SlurpiesDongles.Config.get("SlurpiesDongles.iron_battleaxe_material", "Harvest Level", 0).getInt(),
                SlurpiesDongles.Config.get("SlurpiesDongles.iron_battleaxe_material", "Max Uses", 250).getInt(),
                (float) SlurpiesDongles.Config.get("SlurpiesDongles.iron_battleaxe_material", "EfficiencyOnProperty", 0.0D).getDouble(0.0D),
                (float) SlurpiesDongles.Config.get("SlurpiesDongles.iron_battleaxe_material", "DamageVsEntity", 3).getInt(),
                SlurpiesDongles.Config.get("SlurpiesDongles.iron_battleaxe_material", "Enchantability", 14).getInt());

        ConfigPreInit.rubyBattleaxeMaterial = EnumHelper.addToolMaterial("ruby_battleaxe_material",
                SlurpiesDongles.Config.get("SlurpiesDongles.ruby_battleaxe_material", "Harvest Level", 0).getInt(),
                SlurpiesDongles.Config.get("SlurpiesDongles.ruby_battleaxe_material", "Max Uses", 721).getInt(),
                (float) SlurpiesDongles.Config.get("SlurpiesDongles.ruby_battleaxe_material", "EfficiencyOnProperty", 0.0D).getDouble(0.0D),
                (float) SlurpiesDongles.Config.get("SlurpiesDongles.ruby_battleaxe_material", "DamageVsEntity", 5).getInt(),
                SlurpiesDongles.Config.get("SlurpiesDongles.ruby_battleaxe_material", "Enchantability", 31).getInt());

        ConfigPreInit.sapphireBattleaxeMaterial = EnumHelper.addToolMaterial("sapphire_battleaxe_material",
                SlurpiesDongles.Config.get("SlurpiesDongles.sapphire_battleaxe_material", "Harvest Level", 0).getInt(),
                SlurpiesDongles.Config.get("SlurpiesDongles.sapphire_battleaxe_material", "Max Uses", 492).getInt(),
                (float) SlurpiesDongles.Config.get("SlurpiesDongles.sapphire_battleaxe_material", "EfficiencyOnProperty", 0.0D).getDouble(0.0D),
                (float) SlurpiesDongles.Config.get("SlurpiesDongles.sapphire_battleaxe_material", "DamageVsEntity", 4).getInt(),
                SlurpiesDongles.Config.get("SlurpiesDongles.sapphire_battleaxe_material", "Enchantability", 27).getInt());

        ConfigPreInit.stoneBattleaxeMaterial = EnumHelper.addToolMaterial("stone_battleaxe_material",
                SlurpiesDongles.Config.get("SlurpiesDongles.stone_battleaxe_material", "Harvest Level", 0).getInt(),
                SlurpiesDongles.Config.get("SlurpiesDongles.stone_battleaxe_material", "Max Uses", 131).getInt(),
                (float) SlurpiesDongles.Config.get("SlurpiesDongles.stone_battleaxe_material", "EfficiencyOnProperty", 0.0D).getDouble(0.0D),
                (float) SlurpiesDongles.Config.get("SlurpiesDongles.stone_battleaxe_material", "DamageVsEntity", 2).getInt(),
                SlurpiesDongles.Config.get("SlurpiesDongles.stone_battleaxe_material", "Enchantability", 5).getInt());

        ConfigPreInit.topazBattleaxeMaterial = EnumHelper.addToolMaterial("topaz_battleaxe_material",
                SlurpiesDongles.Config.get("SlurpiesDongles.topaz_battleaxe_material", "Harvest Level", 0).getInt(),
                SlurpiesDongles.Config.get("SlurpiesDongles.topaz_battleaxe_material", "Max Uses", 0).getInt(),
                (float) SlurpiesDongles.Config.get("SlurpiesDongles.topaz_battleaxe_material", "EfficiencyOnProperty", 0.0D).getDouble(0.0D),
                (float) SlurpiesDongles.Config.get("SlurpiesDongles.topaz_battleaxe_material", "DamageVsEntity", 11).getInt(),
                SlurpiesDongles.Config.get("SlurpiesDongles.topaz_battleaxe_material", "Enchantability", 45).getInt());

        ConfigPreInit.woodenBattleaxeMaterial = EnumHelper.addToolMaterial("wooden_battleaxe_material",
                SlurpiesDongles.Config.get("SlurpiesDongles.wooden_battleaxe_material", "Harvest Level", 0).getInt(),
                SlurpiesDongles.Config.get("SlurpiesDongles.wooden_battleaxe_material", "Max Uses", 59).getInt(),
                (float) SlurpiesDongles.Config.get("SlurpiesDongles.wooden_battleaxe_material", "EfficiencyOnProperty", 0.0D).getDouble(0.0D),
                (float) SlurpiesDongles.Config.get("SlurpiesDongles.wooden_battleaxe_material", "DamageVsEntity", 1).getInt(),
                SlurpiesDongles.Config.get("SlurpiesDongles.wooden_battleaxe_material", "Enchantability", 15).getInt());


        //Paxel Materials
        ConfigPreInit.diamondPaxelMaterial = EnumHelper.addToolMaterial("diamond_paxel_material",
                SlurpiesDongles.Config.get("SlurpiesDongles.diamond_paxel_material", "Harvest Level", 3).getInt(),
                SlurpiesDongles.Config.get("SlurpiesDongles.diamond_paxel_material", "Max Uses", 4683).getInt(),
                (float) SlurpiesDongles.Config.get("SlurpiesDongles.diamond_paxel_material", "EfficiencyOnProperty", 9.0D).getDouble(0.0D),
                (float) SlurpiesDongles.Config.get("SlurpiesDongles.diamond_paxel_material", "DamageVsEntity", 3).getInt(),
                SlurpiesDongles.Config.get("SlurpiesDongles.diamond_paxel_material", "Enchantability", 10).getInt());

        ConfigPreInit.emeraldPaxelMaterial = EnumHelper.addToolMaterial("emerald_paxel_material",
                SlurpiesDongles.Config.get("SlurpiesDongles.emerald_paxel_material", "Harvest Level", 3).getInt(),
                SlurpiesDongles.Config.get("SlurpiesDongles.emerald_paxel_material", "Max Uses", 6447).getInt(),
                (float) SlurpiesDongles.Config.get("SlurpiesDongles.emerald_paxel_material", "EfficiencyOnProperty", 10.0D).getDouble(0.0D),
                (float) SlurpiesDongles.Config.get("SlurpiesDongles.emerald_paxel_material", "DamageVsEntity", 3).getInt(),
                SlurpiesDongles.Config.get("SlurpiesDongles.emerald_paxel_material", "Enchantability", 17).getInt());

        ConfigPreInit.goldPaxelMaterial = EnumHelper.addToolMaterial("gold_paxel_material",
                SlurpiesDongles.Config.get("SlurpiesDongles.gold_paxel_material", "Harvest Level", 0).getInt(),
                SlurpiesDongles.Config.get("SlurpiesDongles.gold_paxel_material", "Max Uses", 96).getInt(),
                (float) SlurpiesDongles.Config.get("SlurpiesDongles.gold_paxel_material", "EfficiencyOnProperty", 12.0D).getDouble(0.0D),
                (float) SlurpiesDongles.Config.get("SlurpiesDongles.gold_paxel_material", "DamageVsEntity", 2).getInt(),
                SlurpiesDongles.Config.get("SlurpiesDongles.gold_paxel_material", "Enchantability", 22).getInt());

        ConfigPreInit.ironPaxelMaterial = EnumHelper.addToolMaterial("iron_paxel_material",
                SlurpiesDongles.Config.get("SlurpiesDongles.iron_paxel_material", "Harvest Level", 2).getInt(),
                SlurpiesDongles.Config.get("SlurpiesDongles.iron_paxel_material", "Max Uses", 750).getInt(),
                (float) SlurpiesDongles.Config.get("SlurpiesDongles.iron_paxel_material", "EfficiencyOnProperty", 6.0D).getDouble(0.0D),
                (float) SlurpiesDongles.Config.get("SlurpiesDongles.iron_paxel_material", "DamageVsEntity", 1).getInt(),
                SlurpiesDongles.Config.get("SlurpiesDongles.iron_paxel_material", "Enchantability", 14).getInt());

        ConfigPreInit.stonePaxelMaterial = EnumHelper.addToolMaterial("stone_paxel_material",
                SlurpiesDongles.Config.get("SlurpiesDongles.stone_paxel_material", "Harvest Level", 1).getInt(),
                SlurpiesDongles.Config.get("SlurpiesDongles.stone_paxel_material", "Max Uses", 393).getInt(),
                (float) SlurpiesDongles.Config.get("SlurpiesDongles.stone_paxel_material", "EfficiencyOnProperty", 4.0D).getDouble(0.0D),
                (float) SlurpiesDongles.Config.get("SlurpiesDongles.stone_paxel_material", "DamageVsEntity", 1).getInt(),
                SlurpiesDongles.Config.get("SlurpiesDongles.stone_paxel_material", "Enchantability", 5).getInt());

        ConfigPreInit.rubyPaxelMaterial = EnumHelper.addToolMaterial("ruby_paxel_material",
                SlurpiesDongles.Config.get("SlurpiesDongles.ruby_paxel_material", "Harvest Level", 3).getInt(),
                SlurpiesDongles.Config.get("SlurpiesDongles.ruby_paxel_material", "Max Uses", 2163).getInt(),
                (float) SlurpiesDongles.Config.get("SlurpiesDongles.ruby_paxel_material", "EfficiencyOnProperty", 12.0D).getDouble(0.0D),
                (float) SlurpiesDongles.Config.get("SlurpiesDongles.ruby_paxel_material", "DamageVsEntity", 1).getInt(),
                SlurpiesDongles.Config.get("SlurpiesDongles.ruby_paxel_material", "Enchantability", 31).getInt());

        ConfigPreInit.sapphirePaxelMaterial = EnumHelper.addToolMaterial("sapphire_paxel_material",
                SlurpiesDongles.Config.get("SlurpiesDongles.sapphire_paxel_material", "Harvest Level", 3).getInt(),
                SlurpiesDongles.Config.get("SlurpiesDongles.sapphire_paxel_material", "Max Uses", 1476).getInt(),
                (float) SlurpiesDongles.Config.get("SlurpiesDongles.sapphire_paxel_material", "EfficiencyOnProperty", 8.0D).getDouble(0.0D),
                (float) SlurpiesDongles.Config.get("SlurpiesDongles.sapphire_paxel_material", "DamageVsEntity", 1).getInt(),
                SlurpiesDongles.Config.get("SlurpiesDongles.sapphire_paxel_material", "Enchantability", 27).getInt());

        ConfigPreInit.topazPaxelMaterial = EnumHelper.addToolMaterial("topaz_paxel_material",
                SlurpiesDongles.Config.get("SlurpiesDongles.topaz_paxel_material", "Harvest Level", 3).getInt(),
                SlurpiesDongles.Config.get("SlurpiesDongles.topaz_paxel_material", "Max Uses", 0).getInt(),
                (float) SlurpiesDongles.Config.get("SlurpiesDongles.topaz_paxel_material", "EfficiencyOnProperty", 14.0D).getDouble(0.0D),
                (float) SlurpiesDongles.Config.get("SlurpiesDongles.topaz_paxel_material", "DamageVsEntity", 1).getInt(),
                SlurpiesDongles.Config.get("SlurpiesDongles.topaz_paxel_material", "Enchantability", 20).getInt());

        ConfigPreInit.woodenPaxelMaterial = EnumHelper.addToolMaterial("wooden_paxel_material",
                SlurpiesDongles.Config.get("SlurpiesDongles.wooden_paxel_material", "Harvest Level", 0).getInt(),
                SlurpiesDongles.Config.get("SlurpiesDongles.wooden_paxel_material", "Max Uses", 177).getInt(),
                (float) SlurpiesDongles.Config.get("SlurpiesDongles.wooden_paxel_material", "EfficiencyOnProperty", 2.0D).getDouble(0.0D),
                (float) SlurpiesDongles.Config.get("SlurpiesDongles.wooden_paxel_material", "DamageVsEntity", 1).getInt(),
                SlurpiesDongles.Config.get("SlurpiesDongles.wooden_paxel_material", "Enchantability", 15).getInt());

        //Sword Materials
        ConfigPreInit.emeraldSwordMaterial = EnumHelper.addToolMaterial("emerald_sword_material",
                SlurpiesDongles.Config.get("SlurpiesDongles.emerald_sword_material", "Harvest Level", 0).getInt(),
                SlurpiesDongles.Config.get("SlurpiesDongles.emerald_sword_material", "Max Uses", 2149).getInt(),
                (float) SlurpiesDongles.Config.get("SlurpiesDongles.emerald_sword_material", "EfficiencyOnProperty", 0.0D).getDouble(0.0D),
                (float) SlurpiesDongles.Config.get("SlurpiesDongles.emerald_sword_material", "DamageVsEntity", 5).getInt(),
                SlurpiesDongles.Config.get("SlurpiesDongles.emerald_sword_material", "Enchantability", 17).getInt());

        ConfigPreInit.rubySwordMaterial = EnumHelper.addToolMaterial("ruby_sword_material",
                SlurpiesDongles.Config.get("SlurpiesDongles.ruby_sword_material", "Harvest Level", 0).getInt(),
                SlurpiesDongles.Config.get("SlurpiesDongles.ruby_sword_material", "Max Uses", 721).getInt(),
                (float) SlurpiesDongles.Config.get("SlurpiesDongles.ruby_sword_material", "EfficiencyOnProperty", 0.0D).getDouble(0.0D),
                (float) SlurpiesDongles.Config.get("SlurpiesDongles.ruby_sword_material", "DamageVsEntity", 3).getInt(),
                SlurpiesDongles.Config.get("SlurpiesDongles.ruby_sword_material", "Enchantability", 31).getInt());

        ConfigPreInit.sapphireSwordMaterial = EnumHelper.addToolMaterial("sapphire_sword_material",
                SlurpiesDongles.Config.get("SlurpiesDongles.sapphire_sword_material", "Harvest Level", 0).getInt(),
                SlurpiesDongles.Config.get("SlurpiesDongles.sapphire_sword_material", "Max Uses", 492).getInt(),
                (float) SlurpiesDongles.Config.get("SlurpiesDongles.sapphire_sword_material", "EfficiencyOnProperty", 0.0D).getDouble(0.0D),
                (float) SlurpiesDongles.Config.get("SlurpiesDongles.sapphire_sword_material", "DamageVsEntity", 2).getInt(),
                SlurpiesDongles.Config.get("SlurpiesDongles.sapphire_sword_material", "Enchantability", 27).getInt());

        ConfigPreInit.topazSwordMaterial = EnumHelper.addToolMaterial("topaz_sword_material",
                SlurpiesDongles.Config.get("SlurpiesDongles.topaz_sword_material", "Harvest Level", 0).getInt(),
                SlurpiesDongles.Config.get("SlurpiesDongles.topaz_sword_material", "Max Uses", 0).getInt(),
                (float) SlurpiesDongles.Config.get("SlurpiesDongles.topaz_sword_material", "EfficiencyOnProperty", 0.0D).getDouble(0.0D),
                (float) SlurpiesDongles.Config.get("SlurpiesDongles.topaz_sword_material", "DamageVsEntity", 10).getInt(),
                SlurpiesDongles.Config.get("SlurpiesDongles.topaz_sword_material", "Enchantability", 20).getInt());

        //Tool Materials
        ConfigPreInit.emeraldMaterial = EnumHelper.addToolMaterial("emerald_material",
                SlurpiesDongles.Config.get("SlurpiesDongles.emerald_material", "Harvest Level", 3).getInt(),
                SlurpiesDongles.Config.get("SlurpiesDongles.emerald_material", "Max Uses", 2149).getInt(),
                (float) SlurpiesDongles.Config.get("SlurpiesDongles.emerald_material", "EfficiencyOnProperty", 10.0D).getDouble(0.0D),
                (float) SlurpiesDongles.Config.get("SlurpiesDongles.emerald_material", "DamageVsEntity", 1).getInt(),
                SlurpiesDongles.Config.get("SlurpiesDongles.emerald_material", "Enchantability", 17).getInt());


        ConfigPreInit.rubyMaterial = EnumHelper.addToolMaterial("ruby_material",
                SlurpiesDongles.Config.get("SlurpiesDongles.ruby_material", "Harvest Level", 3).getInt(),
                SlurpiesDongles.Config.get("SlurpiesDongles.ruby_material", "Max Uses", 721).getInt(),
                (float) SlurpiesDongles.Config.get("SlurpiesDongles.ruby_material", "EfficiencyOnProperty", 12.0D).getDouble(0.0D),
                (float) SlurpiesDongles.Config.get("SlurpiesDongles.ruby_material", "DamageVsEntity", 1).getInt(),
                SlurpiesDongles.Config.get("SlurpiesDongles.ruby_material", "Enchantability", 31).getInt());

        ConfigPreInit.sapphireMaterial = EnumHelper.addToolMaterial("ruby_material",
                SlurpiesDongles.Config.get("SlurpiesDongles.sapphire_material", "Harvest Level", 3).getInt(),
                SlurpiesDongles.Config.get("SlurpiesDongles.sapphire_material", "Max Uses", 492).getInt(),
                (float) SlurpiesDongles.Config.get("SlurpiesDongles.sapphire_material", "EfficiencyOnProperty", 8.0D).getDouble(0.0D),
                (float) SlurpiesDongles.Config.get("SlurpiesDongles.sapphire_material", "DamageVsEntity", 1).getInt(),
                SlurpiesDongles.Config.get("SlurpiesDongles.sapphire_material", "Enchantability", 27).getInt());

        ConfigPreInit.topazMaterial = EnumHelper.addToolMaterial("topaz_material",
                SlurpiesDongles.Config.get("SlurpiesDongles.topaz_material", "Harvest Level", 4).getInt(),
                SlurpiesDongles.Config.get("SlurpiesDongles.topaz_material", "Max Uses", 0).getInt(),//0 = unbreakable
                (float) SlurpiesDongles.Config.get("SlurpiesDongles.topaz_material", "EfficiencyOnProperty", 14.0D).getDouble(0.0D),
                (float) SlurpiesDongles.Config.get("SlurpiesDongles.topaz_material", "DamageVsEntity", 1).getInt(),
                SlurpiesDongles.Config.get("SlurpiesDongles.topaz_material", "Enchantability", 20).getInt());

                ConfigPreInit.vmpickMaterial = EnumHelper.addToolMaterial("vmpick_material",
                SlurpiesDongles.Config.get("SlurpiesDongles.vmpick_material", "Harvest Level", 1).getInt(),
                SlurpiesDongles.Config.get("SlurpiesDongles.vmpick_material", "Max Uses", 6200).getInt(),
                (float) SlurpiesDongles.Config.get("SlurpiesDongles.vmpick_material", "EfficiencyOnProperty", 4.0D).getDouble(0.0D),
                (float) SlurpiesDongles.Config.get("SlurpiesDongles.vmpick_material", "DamageVsEntity", 1).getInt(),
                SlurpiesDongles.Config.get("SlurpiesDongles.vmpick_material", "Enchantability", 10).getInt());

        if (SlurpiesDongles.Config.hasChanged()) {
            SlurpiesDongles.Config.save();
        }



    }
}
