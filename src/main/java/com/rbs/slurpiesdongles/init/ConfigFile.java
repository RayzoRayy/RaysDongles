package com.rbs.slurpiesdongles.init;

import com.rbs.slurpiesdongles.SlurpiesDongles;
import net.minecraft.init.SoundEvents;
import net.minecraftforge.common.util.EnumHelper;

public class ConfigFile {

    public static void SyncConfig() {

        //Armor Materials
        ConfigPreInit.rubyArmor = EnumHelper.addArmorMaterial("ruby_armor", "SlurpiesDongles:ruby",
                SlurpiesDongles.Armor.get("SlurpiesDongles.ruby_armor", "Durability", 26).getInt(),
                new int[]{SlurpiesDongles.Armor.get("SlurpiesDongles.ruby_armor", "Head", 4).getInt(),
                        SlurpiesDongles.Armor.get("SlurpiesDongles.ruby_armor", "Chest", 6).getInt(),
                        SlurpiesDongles.Armor.get("SlurpiesDongles.ruby_armor", "Legs", 7).getInt(),
                        SlurpiesDongles.Armor.get("SlurpiesDongles.ruby_armor", "Feet", 4).getInt()},
                SlurpiesDongles.Armor.get("SlurpiesDongles.ruby_armor", "Enchantability", 31).getInt(),
                SoundEvents.ITEM_ARMOR_EQUIP_DIAMOND,
                (float) SlurpiesDongles.Armor.get("SlurpiesDongles.ruby_armor", "Toughness", 2.0D).getDouble(0.0D));

        ConfigPreInit.sapphireArmor = EnumHelper.addArmorMaterial("sapphire_armor", "SlurpiesDongles:sapphire",
                SlurpiesDongles.Armor.get("SlurpiesDongles.sapphire_armor", "Durability", 20).getInt(),
                new int[]{SlurpiesDongles.Armor.get("SlurpiesDongles.sapphire_armor", "Head", 3).getInt(),
                        SlurpiesDongles.Armor.get("SlurpiesDongles.sapphire_armor", "Chest", 5).getInt(),
                        SlurpiesDongles.Armor.get("SlurpiesDongles.sapphire_armor", "Legs", 6).getInt(),
                        SlurpiesDongles.Armor.get("SlurpiesDongles.sapphire_armor", "Feet", 3).getInt()},
                SlurpiesDongles.Armor.get("SlurpiesDongles.sapphire_armor", "Enchantability", 27).getInt(),
                SoundEvents.ITEM_ARMOR_EQUIP_DIAMOND,
                (float) SlurpiesDongles.Armor.get("SlurpiesDongles.sapphire_armor", "Toughness", 1.0D).getDouble(0.0D));


        ConfigPreInit.topazArmor = EnumHelper.addArmorMaterial("topaz_armor", "SlurpiesDongles:topaz",
                SlurpiesDongles.Armor.get("SlurpiesDongles.topaz_armor", "Durability", 0).getInt(),
                new int[]{SlurpiesDongles.Armor.get("SlurpiesDongles.topaz_armor", "Head", 5).getInt(),
                        SlurpiesDongles.Armor.get("SlurpiesDongles.topaz_armor", "Chest", 8).getInt(),
                        SlurpiesDongles.Armor.get("SlurpiesDongles.topaz_armor", "Legs", 9).getInt(),
                        SlurpiesDongles.Armor.get("SlurpiesDongles.topaz_armor", "Feet", 4).getInt()},
                SlurpiesDongles.Armor.get("SlurpiesDongles.topaz_armor", "Enchantability", 45).getInt(),
                SoundEvents.ITEM_ARMOR_EQUIP_DIAMOND,
                (float) SlurpiesDongles.Armor.get("SlurpiesDongles.topaz_armor", "Toughness", 6.0D).getDouble(0.0D));

        if (SlurpiesDongles.Armor.hasChanged()) {
            SlurpiesDongles.Armor.save();
        }

        //Axe Materials DMG Vs Entity: 1=2, 2=3. 3=4, 4=5. 5=6. 6=7. 7=8. 8=9, 9=10, 10=11, 11=12, so on so forth
        ConfigPreInit.emeraldAxeMaterial = EnumHelper.addToolMaterial("emerald_axe_material",
                SlurpiesDongles.Config.get("SlurpiesDongles.emerald_axe_material", "Harvest Level", 0).getInt(),
                SlurpiesDongles.Config.get("SlurpiesDongles.emerald_axe_material", "Max Uses", 2149).getInt(),
                (float) SlurpiesDongles.Config.get("SlurpiesDongles.emerald_axe_material", "EfficiencyOnProperty", 10.0D).getDouble(0.0D),
                (float) SlurpiesDongles.Config.get("SlurpiesDongles.emerald_axe_material", "DamageVsEntity", 8).getInt(),
                SlurpiesDongles.Config.get("SlurpiesDongles.emerald_axe_material", "Enchantability", 17).getInt());

        ConfigPreInit.rubyAxeMaterial = EnumHelper.addToolMaterial("ruby_axe_material",
                SlurpiesDongles.Config.get("SlurpiesDongles.ruby_axe_material", "Harvest Level", 0).getInt(),
                SlurpiesDongles.Config.get("SlurpiesDongles.ruby_axe_material", "Max Uses", 721).getInt(),
                (float) SlurpiesDongles.Config.get("SlurpiesDongles.ruby_axe_material", "EfficiencyOnProperty", 7.0D).getDouble(0.0D),
                (float) SlurpiesDongles.Config.get("SlurpiesDongles.ruby_axe_material", "DamageVsEntity", 8).getInt(),
                SlurpiesDongles.Config.get("SlurpiesDongles.ruby_axe_material", "Enchantability", 31).getInt());

        ConfigPreInit.sapphireAxeMaterial = EnumHelper.addToolMaterial("sapphire_axe_material",
                SlurpiesDongles.Config.get("SlurpiesDongles.sapphire_axe_material", "Harvest Level", 0).getInt(),
                SlurpiesDongles.Config.get("SlurpiesDongles.sapphire_axe_material", "Max Uses", 492).getInt(),
                (float) SlurpiesDongles.Config.get("SlurpiesDongles.sapphire_axe_material", "EfficiencyOnProperty", 6.0D).getDouble(0.0D),
                (float) SlurpiesDongles.Config.get("SlurpiesDongles.sapphire_axe_material", "DamageVsEntity", 8).getInt(),
                SlurpiesDongles.Config.get("SlurpiesDongles.sapphire_axe_material", "Enchantability", 27).getInt());

        ConfigPreInit.topazAxeMaterial = EnumHelper.addToolMaterial("topaz_axe_material",
                SlurpiesDongles.Config.get("SlurpiesDongles.topaz_axe_material", "Harvest Level", 0).getInt(),
                SlurpiesDongles.Config.get("SlurpiesDongles.topaz_axe_material", "Max Uses", 0).getInt(),
                (float) SlurpiesDongles.Config.get("SlurpiesDongles.topaz_axe_material", "EfficiencyOnProperty", 14.0D).getDouble(0.0D),
                (float) SlurpiesDongles.Config.get("SlurpiesDongles.topaz_axe_material", "DamageVsEntity", 8).getInt(),
                SlurpiesDongles.Config.get("SlurpiesDongles.topaz_axe_material", "Enchantability", 20).getInt());


        //Battle Axe Materials DMG Vs Entity: 1=5, 2=6, 3=7, 4=8, 5=9, 6=10, 7=11, 8=12, 9=13, 10=14, so on so forth

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
                (float) SlurpiesDongles.Config.get("SlurpiesDongles.topaz_battleaxe_material", "DamageVsEntity", 10).getInt(),
                SlurpiesDongles.Config.get("SlurpiesDongles.topaz_battleaxe_material", "Enchantability", 45).getInt());

        ConfigPreInit.woodenBattleaxeMaterial = EnumHelper.addToolMaterial("wooden_battleaxe_material",
                SlurpiesDongles.Config.get("SlurpiesDongles.wooden_battleaxe_material", "Harvest Level", 0).getInt(),
                SlurpiesDongles.Config.get("SlurpiesDongles.wooden_battleaxe_material", "Max Uses", 59).getInt(),
                (float) SlurpiesDongles.Config.get("SlurpiesDongles.wooden_battleaxe_material", "EfficiencyOnProperty", 0.0D).getDouble(0.0D),
                (float) SlurpiesDongles.Config.get("SlurpiesDongles.wooden_battleaxe_material", "DamageVsEntity", 1).getInt(),
                SlurpiesDongles.Config.get("SlurpiesDongles.wooden_battleaxe_material", "Enchantability", 15).getInt());


        //Paxel Materials DMG Vs Entity: 1=3, 2=4, 3=5, 4=6, 5=7, 6=8, 7=9, 8=10, 9=11, 10=12, so on so forth
        ConfigPreInit.diamondPaxelMaterial = EnumHelper.addToolMaterial("diamond_paxel_material",
                SlurpiesDongles.Config.get("SlurpiesDongles.diamond_paxel_material", "Harvest Level", 3).getInt(),
                SlurpiesDongles.Config.get("SlurpiesDongles.diamond_paxel_material", "Max Uses", 4683).getInt(),
                (float) SlurpiesDongles.Config.get("SlurpiesDongles.diamond_paxel_material", "EfficiencyOnProperty", 8.0D).getDouble(0.0D),
                (float) SlurpiesDongles.Config.get("SlurpiesDongles.diamond_paxel_material", "DamageVsEntity", 7).getInt(),
                SlurpiesDongles.Config.get("SlurpiesDongles.diamond_paxel_material", "Enchantability", 10).getInt());

        ConfigPreInit.emeraldPaxelMaterial = EnumHelper.addToolMaterial("emerald_paxel_material",
                SlurpiesDongles.Config.get("SlurpiesDongles.emerald_paxel_material", "Harvest Level", 3).getInt(),
                SlurpiesDongles.Config.get("SlurpiesDongles.emerald_paxel_material", "Max Uses", 6447).getInt(),
                (float) SlurpiesDongles.Config.get("SlurpiesDongles.emerald_paxel_material", "EfficiencyOnProperty", 10.0D).getDouble(0.0D),
                (float) SlurpiesDongles.Config.get("SlurpiesDongles.emerald_paxel_material", "DamageVsEntity", 9).getInt(),
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
                (float) SlurpiesDongles.Config.get("SlurpiesDongles.iron_paxel_material", "DamageVsEntity", 4).getInt(),
                SlurpiesDongles.Config.get("SlurpiesDongles.iron_paxel_material", "Enchantability", 14).getInt());

        ConfigPreInit.stonePaxelMaterial = EnumHelper.addToolMaterial("stone_paxel_material",
                SlurpiesDongles.Config.get("SlurpiesDongles.stone_paxel_material", "Harvest Level", 1).getInt(),
                SlurpiesDongles.Config.get("SlurpiesDongles.stone_paxel_material", "Max Uses", 393).getInt(),
                (float) SlurpiesDongles.Config.get("SlurpiesDongles.stone_paxel_material", "EfficiencyOnProperty", 4.0D).getDouble(0.0D),
                (float) SlurpiesDongles.Config.get("SlurpiesDongles.stone_paxel_material", "DamageVsEntity", 3).getInt(),
                SlurpiesDongles.Config.get("SlurpiesDongles.stone_paxel_material", "Enchantability", 5).getInt());

        ConfigPreInit.rubyPaxelMaterial = EnumHelper.addToolMaterial("ruby_paxel_material",
                SlurpiesDongles.Config.get("SlurpiesDongles.ruby_paxel_material", "Harvest Level", 3).getInt(),
                SlurpiesDongles.Config.get("SlurpiesDongles.ruby_paxel_material", "Max Uses", 2163).getInt(),
                (float) SlurpiesDongles.Config.get("SlurpiesDongles.ruby_paxel_material", "EfficiencyOnProperty", 7.0D).getDouble(0.0D),
                (float) SlurpiesDongles.Config.get("SlurpiesDongles.ruby_paxel_material", "DamageVsEntity", 6).getInt(),
                SlurpiesDongles.Config.get("SlurpiesDongles.ruby_paxel_material", "Enchantability", 31).getInt());

        ConfigPreInit.sapphirePaxelMaterial = EnumHelper.addToolMaterial("sapphire_paxel_material",
                SlurpiesDongles.Config.get("SlurpiesDongles.sapphire_paxel_material", "Harvest Level", 3).getInt(),
                SlurpiesDongles.Config.get("SlurpiesDongles.sapphire_paxel_material", "Max Uses", 1476).getInt(),
                (float) SlurpiesDongles.Config.get("SlurpiesDongles.sapphire_paxel_material", "EfficiencyOnProperty", 6.0D).getDouble(0.0D),
                (float) SlurpiesDongles.Config.get("SlurpiesDongles.sapphire_paxel_material", "DamageVsEntity", 5).getInt(),
                SlurpiesDongles.Config.get("SlurpiesDongles.sapphire_paxel_material", "Enchantability", 27).getInt());

        ConfigPreInit.topazPaxelMaterial = EnumHelper.addToolMaterial("topaz_paxel_material",
                SlurpiesDongles.Config.get("SlurpiesDongles.topaz_paxel_material", "Harvest Level", 3).getInt(),
                SlurpiesDongles.Config.get("SlurpiesDongles.topaz_paxel_material", "Max Uses", 0).getInt(),
                (float) SlurpiesDongles.Config.get("SlurpiesDongles.topaz_paxel_material", "EfficiencyOnProperty", 14.0D).getDouble(0.0D),
                (float) SlurpiesDongles.Config.get("SlurpiesDongles.topaz_paxel_material", "DamageVsEntity", 11).getInt(),
                SlurpiesDongles.Config.get("SlurpiesDongles.topaz_paxel_material", "Enchantability", 20).getInt());

        ConfigPreInit.woodenPaxelMaterial = EnumHelper.addToolMaterial("wooden_paxel_material",
                SlurpiesDongles.Config.get("SlurpiesDongles.wooden_paxel_material", "Harvest Level", 0).getInt(),
                SlurpiesDongles.Config.get("SlurpiesDongles.wooden_paxel_material", "Max Uses", 177).getInt(),
                (float) SlurpiesDongles.Config.get("SlurpiesDongles.wooden_paxel_material", "EfficiencyOnProperty", 2.0D).getDouble(0.0D),
                (float) SlurpiesDongles.Config.get("SlurpiesDongles.wooden_paxel_material", "DamageVsEntity", 2).getInt(),
                SlurpiesDongles.Config.get("SlurpiesDongles.wooden_paxel_material", "Enchantability", 15).getInt());

        //Sword Materials DMG Vs Entity: 1=5, 2=6, 3=7, 4=8, 5=9, 6=10, 7=11, 8=12, 9=13, 10=14
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
                (float) SlurpiesDongles.Config.get("SlurpiesDongles.topaz_sword_material", "DamageVsEntity", 8).getInt(),
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
                (float) SlurpiesDongles.Config.get("SlurpiesDongles.ruby_material", "EfficiencyOnProperty", 7.0D).getDouble(0.0D),
                (float) SlurpiesDongles.Config.get("SlurpiesDongles.ruby_material", "DamageVsEntity", 1).getInt(),
                SlurpiesDongles.Config.get("SlurpiesDongles.ruby_material", "Enchantability", 31).getInt());

        ConfigPreInit.sapphireMaterial = EnumHelper.addToolMaterial("ruby_material",
                SlurpiesDongles.Config.get("SlurpiesDongles.sapphire_material", "Harvest Level", 3).getInt(),
                SlurpiesDongles.Config.get("SlurpiesDongles.sapphire_material", "Max Uses", 492).getInt(),
                (float) SlurpiesDongles.Config.get("SlurpiesDongles.sapphire_material", "EfficiencyOnProperty", 6.0D).getDouble(0.0D),
                (float) SlurpiesDongles.Config.get("SlurpiesDongles.sapphire_material", "DamageVsEntity", 1).getInt(),
                SlurpiesDongles.Config.get("SlurpiesDongles.sapphire_material", "Enchantability", 27).getInt());

        ConfigPreInit.topazMaterial = EnumHelper.addToolMaterial("topaz_material",
                SlurpiesDongles.Config.get("SlurpiesDongles.topaz_material", "Harvest Level", 4).getInt(),
                SlurpiesDongles.Config.get("SlurpiesDongles.topaz_material", "Max Uses", 0).getInt(),//0 = unbreakable
                (float) SlurpiesDongles.Config.get("SlurpiesDongles.topaz_material", "EfficiencyOnProperty", 14.0D).getDouble(0.0D),
                (float) SlurpiesDongles.Config.get("SlurpiesDongles.topaz_material", "DamageVsEntity", 1).getInt(),
                SlurpiesDongles.Config.get("SlurpiesDongles.topaz_material", "Enchantability", 20).getInt());

        //Pickaxe Materials
        ConfigPreInit.vmpickMaterial = EnumHelper.addToolMaterial("vmpick_material",
                SlurpiesDongles.Config.get("SlurpiesDongles.vmpick_material", "Harvest Level", 1).getInt(),
                SlurpiesDongles.Config.get("SlurpiesDongles.vmpick_material", "Max Uses", 9100).getInt(),
                (float) SlurpiesDongles.Config.get("SlurpiesDongles.vmpick_material", "EfficiencyOnProperty", 4.0D).getDouble(0.0D),
                (float) SlurpiesDongles.Config.get("SlurpiesDongles.vmpick_material", "DamageVsEntity", 1).getInt(),
                SlurpiesDongles.Config.get("SlurpiesDongles.vmpick_material", "Enchantability", 10).getInt());

        if (SlurpiesDongles.Config.hasChanged()) {
            SlurpiesDongles.Config.save();
        }

        //Charms
        ConfigPreInit.disableFireResistanceCharm = SlurpiesDongles.Charms.getBoolean("Disable Fire Resistance Charm", "SlurpiesDongles.Charms.fire resistance charm", ConfigPreInit.disableFireResistanceCharm, "Disable Fire Resistance Charm 'Set this to true to disable");
        ConfigPreInit.disableNightVisionCharm = SlurpiesDongles.Charms.getBoolean("Disable Night Vision Charm", "SlurpiesDongles.Charms.night vision charm", ConfigPreInit.disableNightVisionCharm, "Disable Night Vision Charm 'Set this to true to disable");
        ConfigPreInit.disableRegenCharm = SlurpiesDongles.Charms.getBoolean("Disable Regen Charm", "SlurpiesDongles.Charms.regen charm", ConfigPreInit.disableRegenCharm, "Disable Regen Charm 'Set this to true to disable");
        ConfigPreInit.disableSpeedCharm = SlurpiesDongles.Charms.getBoolean("Disable Speed Charm", "SlurpiesDongles.Charms.speed charm", ConfigPreInit.disableSpeedCharm, "Disable Speed Charm 'Set this to true to disable");
        ConfigPreInit.disableStrengthCharm = SlurpiesDongles.Charms.getBoolean("Disable Strength Charm", "SlurpiesDongles.Charms.strength charm", ConfigPreInit.disableStrengthCharm, "Disable Strength Charm 'Set this to true to disable");
        ConfigPreInit.disableWaterBreathingCharm = SlurpiesDongles.Charms.getBoolean("Disable Water Breathing Charm", "SlurpiesDongles.Charms.water breathing charm", ConfigPreInit.disableWaterBreathingCharm, "Disable Water Breathing Charm 'Set this to true to disable");


        if (SlurpiesDongles.Charms.hasChanged()) {
            SlurpiesDongles.Charms.save();
        }

        //Food
        ConfigPreInit.disableLemonBush = SlurpiesDongles.Worldgen.getBoolean("Disable Lemon Bush", "SlurpiesDongles.Food.lemon Bush", ConfigPreInit.disableLemonBush, "Disable Lemon Bush Worldgeneration 'Set this to true to disable");
        ConfigPreInit.disableOrangeBush = SlurpiesDongles.Worldgen.getBoolean("Disable Orange Bush", "SlurpiesDongles.Food.orange Bush", ConfigPreInit.disableOrangeBush, "Disable Orange Bush Worldgeneration 'Set this to true to disable");
        //Ore
        ConfigPreInit.disableLigniteOre = SlurpiesDongles.Worldgen.getBoolean("Disable Lignite Ore", "SlurpiesDongles.Ore.lignite ore", ConfigPreInit.disableLigniteOre, "Disable Lignite Ore Worldgeneration 'Set this to true to disable");
        ConfigPreInit.disableRubyOre = SlurpiesDongles.Worldgen.getBoolean("Disable Ruby Ore", "SlurpiesDongles.Ore.ruby ore", ConfigPreInit.disableRubyOre, "Disable Ruby Ore Worldgeneration 'Set this to true to disable");
        ConfigPreInit.disableSapphireOre = SlurpiesDongles.Worldgen.getBoolean("Disable Sapphire Ore", "SlurpiesDongles.Ore.sapphire ore", ConfigPreInit.disableSapphireOre, "Disable Sapphire Ore Worldgeneration 'Set this to true to disable");
        ConfigPreInit.disableTopazOre = SlurpiesDongles.Worldgen.getBoolean("Disable Topaz Ore", "SlurpiesDongles.Ore.topaz ore", ConfigPreInit.disableTopazOre, "Disable Topaz Ore Worldgeneration 'Set this to true to disable");

        //Nether Ores
        ConfigPreInit.disableNetherCoalOre = SlurpiesDongles.Worldgen.getBoolean("Disable Nether Coal Ore", "SlurpiesDongles.Nether Ore.nether coal ore", ConfigPreInit.disableNetherCoalOre, "Disable Nether Coal Ore Worldgeneration 'Set this to true to disable");
        ConfigPreInit.disableNetherDiamondOre = SlurpiesDongles.Worldgen.getBoolean("Disable Nether Diamond Ore", "SlurpiesDongles.Nether Ore.nether diamond ore", ConfigPreInit.disableNetherDiamondOre, "Disable Nether Diamond Ore Worldgeneration 'Set this to true to disable");
        ConfigPreInit.disableNetherEmeraldOre = SlurpiesDongles.Worldgen.getBoolean("Disable Nether Emerald Ore", "SlurpiesDongles.Nether Ore.nether emerald ore", ConfigPreInit.disableNetherEmeraldOre, "Disable Nether Emerald Ore Worldgeneration 'Set this to true to disable");
        ConfigPreInit.disableNetherGoldOre = SlurpiesDongles.Worldgen.getBoolean("Disable Nether Gold Ore", "SlurpiesDongles.Nether Ore.nether gold ore", ConfigPreInit.disableNetherGoldOre, "Disable Nether Gold Ore Worldgeneration 'Set this to true to disable");
        ConfigPreInit.disableNetherIronOre = SlurpiesDongles.Worldgen.getBoolean("Disable Nether Iron Ore", "SlurpiesDongles.Nether Ore.nether iron ore", ConfigPreInit.disableNetherIronOre, "Disable Nether Iron Ore Worldgeneration 'Set this to true to disable");
        ConfigPreInit.disableNetherLapisOre = SlurpiesDongles.Worldgen.getBoolean("Disable Nether Lapis Ore", "SlurpiesDongles.Nether Ore.nether lapis ore", ConfigPreInit.disableNetherLapisOre, "Disable Nether Lapis Ore Worldgeneration 'Set this to true to disable");
        ConfigPreInit.disableNetherRedstoneOre = SlurpiesDongles.Worldgen.getBoolean("Disable Nether Redstone Ore", "SlurpiesDongles.Nether Ore.nether redstone ore", ConfigPreInit.disableNetherRedstoneOre, "Disable Nether Redstone Ore Worldgeneration 'Set this to true to disable");

        if (SlurpiesDongles.Worldgen.hasChanged()) {
            SlurpiesDongles.Worldgen.save();
        }
        //World Generation
        //Food
        if (ConfigPreInit.disableLemonBush == false) {
            ConfigPreInit.lemonBlockCount = SlurpiesDongles.OreAndFood.get("SlurpiesDongles.Food.lemon bush", "How many lemon bushes spawn together", ConfigPreInit.lemonBlockCount).getInt();
            ConfigPreInit.lemonChancesToSpawn = SlurpiesDongles.OreAndFood.get("SlurpiesDongles.Food.lemon bush", "The chance the lemon bush can spawn per chunk", ConfigPreInit.lemonChancesToSpawn).getInt();
            ConfigPreInit.lemoneMinYLevel = SlurpiesDongles.OreAndFood.get("SlurpiesDongles.Food.lemon bush", "The minimum Y level the lemon bush will spawn at, recommended is 60 so it doesn't spawn below grass.", ConfigPreInit.lemoneMinYLevel).getInt();
            ConfigPreInit.lemonMaxYLevel = SlurpiesDongles.OreAndFood.get("SlurpiesDongles.Food.lemon bush", "The maximum Y level the lemon bush will spawn at, recommended is 90", ConfigPreInit.lemonMaxYLevel).getInt();

            if (ConfigPreInit.disableOrangeBush == false) {
                ConfigPreInit.orangeBlockCount = SlurpiesDongles.OreAndFood.get("SlurpiesDongles.Food.orange bush", "How many orange bushes spawn together", ConfigPreInit.orangeBlockCount).getInt();
                ConfigPreInit.orangeChancesToSpawn = SlurpiesDongles.OreAndFood.get("SlurpiesDongles.Food.orange bush", "The chance the orange bush can spawn per chunk", ConfigPreInit.orangeChancesToSpawn).getInt();
                ConfigPreInit.orangeMinYLevel = SlurpiesDongles.OreAndFood.get("SlurpiesDongles.Food.orange bush", "The minimum Y level the orange bush will spawn at, recommended is 60 so it doesn't spawn below grass.", ConfigPreInit.orangeMinYLevel).getInt();
                ConfigPreInit.orangeMaxYLevel = SlurpiesDongles.OreAndFood.get("SlurpiesDongles.Food.orange bush", "The maximum Y level the orange bush will spawn at, recommended is 90", ConfigPreInit.orangeMaxYLevel).getInt();
            }
            //Ores
            if (ConfigPreInit.disableLigniteOre == false) {
                ConfigPreInit.ligniteBlockCount = SlurpiesDongles.OreAndFood.get("SlurpiesDongles.Ore.lignite ore", "How many lignite ores spawn together", ConfigPreInit.ligniteBlockCount).getInt();
                ConfigPreInit.ligniteChancesToSpawn = SlurpiesDongles.OreAndFood.get("SlurpiesDongles.Ore.lignite ore", "The chance the lignite ore can spawn per chunk", ConfigPreInit.ligniteChancesToSpawn).getInt();
                ConfigPreInit.ligniteMinYLevel = SlurpiesDongles.OreAndFood.get("SlurpiesDongles.Ore.lignite ore", "The minimum Y level the lignite ore will spawn at", ConfigPreInit.ligniteMinYLevel).getInt();
                ConfigPreInit.ligniteMaxYLevel = SlurpiesDongles.OreAndFood.get("SlurpiesDongles.Ore.lignite ore", "The maximum Y level the lignite ore will spawn at", ConfigPreInit.ligniteMaxYLevel).getInt();

                if (ConfigPreInit.disableRubyOre == false) {
                    ConfigPreInit.rubyBlockCount = SlurpiesDongles.OreAndFood.get("SlurpiesDongles.Ore.ruby ore", "How many ruby ores spawn together", ConfigPreInit.rubyBlockCount).getInt();
                    ConfigPreInit.rubyChancesToSpawn = SlurpiesDongles.OreAndFood.get("SlurpiesDongles.Ore.ruby ore", "The chance the ruby ore can spawn per chunk", ConfigPreInit.rubyChancesToSpawn).getInt();
                    ConfigPreInit.rubyMinYLevel = SlurpiesDongles.OreAndFood.get("SlurpiesDongles.Ore.ruby ore", "The minimum Y level the ruby ore will spawn at", ConfigPreInit.rubyMinYLevel).getInt();
                    ConfigPreInit.rubyMaxYLevel = SlurpiesDongles.OreAndFood.get("SlurpiesDongles.Ore.ruby ore", "The maximum Y level the ruby ore will spawn at", ConfigPreInit.rubyMaxYLevel).getInt();

                    if (ConfigPreInit.disableSapphireOre == false) {
                        ConfigPreInit.sapphireBlockCount = SlurpiesDongles.OreAndFood.get("SlurpiesDongles.Ore.sapphire ore", "How many sapphire ores spawn together", ConfigPreInit.sapphireBlockCount).getInt();
                        ConfigPreInit.sapphireChanesToSpawn = SlurpiesDongles.OreAndFood.get("SlurpiesDongles.Ore.sapphire ore", "The chance the sapphire ore can spawn per chunk", ConfigPreInit.sapphireChanesToSpawn).getInt();
                        ConfigPreInit.sapphireMinYLevel = SlurpiesDongles.OreAndFood.get("SlurpiesDongles.Ore.sapphire ore", "The minimum Y level the sapphire ore will spawn at", ConfigPreInit.sapphireMinYLevel).getInt();
                        ConfigPreInit.sapphireMaxYLevel = SlurpiesDongles.OreAndFood.get("SlurpiesDongles.Ore.sapphire ore", "The maximum Y level the sapphire ore will spawn at", ConfigPreInit.sapphireMaxYLevel).getInt();

                        if (ConfigPreInit.disableTopazOre == false) {
                            ConfigPreInit.topazBlockCount = SlurpiesDongles.OreAndFood.get("SlurpiesDongles.Ore.topaz ore", "How many topaz ores spawn together", ConfigPreInit.topazBlockCount).getInt();
                            ConfigPreInit.topazChancesToSpawn = SlurpiesDongles.OreAndFood.get("SlurpiesDongles.Ore.topaz ore", "The chance the topaz ore can spawn per chunk", ConfigPreInit.topazChancesToSpawn).getInt();
                            ConfigPreInit.topazMinYLevel = SlurpiesDongles.OreAndFood.get("SlurpiesDongles.Ore.topaz ore", "The minimum Y level the topaz ore will spawn at", ConfigPreInit.topazMinYLevel).getInt();
                            ConfigPreInit.topazMaxYLevel = SlurpiesDongles.OreAndFood.get("SlurpiesDongles.Ore.topaz ore", "The maximum Y level the topaz ore will spawn at", ConfigPreInit.topazMaxYLevel).getInt();

                            //Nether Ores
                            if (ConfigPreInit.disableNetherCoalOre == false) {
                                ConfigPreInit.coalBlockCount = SlurpiesDongles.OreAndFood.get("SlurpiesDongles.Ore.nether coal ore", "How many topaz ores spawn together", ConfigPreInit.coalBlockCount).getInt();
                                ConfigPreInit.coalChancesToSpawn = SlurpiesDongles.OreAndFood.get("SlurpiesDongles.Ore.nether coal ore", "The chance the topaz ore can spawn per chunk", ConfigPreInit.coalChancesToSpawn).getInt();
                                ConfigPreInit.coalMinYLevel = SlurpiesDongles.OreAndFood.get("SlurpiesDongles.Ore.nether coal ore", "The minimum Y level the topaz ore will spawn at", ConfigPreInit.coalMinYLevel).getInt();
                                ConfigPreInit.coalMaxYLevel = SlurpiesDongles.OreAndFood.get("SlurpiesDongles.Ore.nether coal ore", "The maximum Y level the topaz ore will spawn at", ConfigPreInit.coalMaxYLevel).getInt();

                                if (ConfigPreInit.disableNetherDiamondOre == false) {
                                    ConfigPreInit.diamondBlockCount = SlurpiesDongles.OreAndFood.get("SlurpiesDongles.Ore.nether diamond ore", "How many topaz ores spawn together", ConfigPreInit.diamondBlockCount).getInt();
                                    ConfigPreInit.diamondChancesToSpawn = SlurpiesDongles.OreAndFood.get("SlurpiesDongles.Ore.nether diamond ore", "The chance the topaz ore can spawn per chunk", ConfigPreInit.diamondChancesToSpawn).getInt();
                                    ConfigPreInit.diamondMinYLevel = SlurpiesDongles.OreAndFood.get("SlurpiesDongles.Ore.nether diamond ore", "The minimum Y level the topaz ore will spawn at", ConfigPreInit.diamondMinYLevel).getInt();
                                    ConfigPreInit.diamondMaxYLevel = SlurpiesDongles.OreAndFood.get("SlurpiesDongles.Ore.nether diamond ore", "The maximum Y level the topaz ore will spawn at", ConfigPreInit.diamondMaxYLevel).getInt();

                                    if (ConfigPreInit.disableNetherEmeraldOre == false) {
                                        ConfigPreInit.emeraldBlockCount = SlurpiesDongles.OreAndFood.get("SlurpiesDongles.Ore.nether emerald ore", "How many topaz ores spawn together", ConfigPreInit.emeraldBlockCount).getInt();
                                        ConfigPreInit.emeraldChancesToSpawn = SlurpiesDongles.OreAndFood.get("SlurpiesDongles.Ore.nether emerald ore", "The chance the topaz ore can spawn per chunk", ConfigPreInit.emeraldChancesToSpawn).getInt();
                                        ConfigPreInit.emeraldMinYLevel = SlurpiesDongles.OreAndFood.get("SlurpiesDongles.Ore.nether emerald ore", "The minimum Y level the topaz ore will spawn at", ConfigPreInit.emeraldMinYLevel).getInt();
                                        ConfigPreInit.emeraldMaxYLevel = SlurpiesDongles.OreAndFood.get("SlurpiesDongles.Ore.nether emerald ore", "The maximum Y level the topaz ore will spawn at", ConfigPreInit.emeraldMaxYLevel).getInt();

                                        if (ConfigPreInit.disableNetherGoldOre == false) {
                                            ConfigPreInit.goldBlockCount = SlurpiesDongles.OreAndFood.get("SlurpiesDongles.Ore.nether gold ore", "How many topaz ores spawn together", ConfigPreInit.goldBlockCount).getInt();
                                            ConfigPreInit.goldChancesToSpawn = SlurpiesDongles.OreAndFood.get("SlurpiesDongles.Ore.nether gold ore", "The chance the topaz ore can spawn per chunk", ConfigPreInit.goldChancesToSpawn).getInt();
                                            ConfigPreInit.goldMinYLevel = SlurpiesDongles.OreAndFood.get("SlurpiesDongles.Ore.nether gold ore", "The minimum Y level the topaz ore will spawn at", ConfigPreInit.goldMinYLevel).getInt();
                                            ConfigPreInit.goldMaxYLevel = SlurpiesDongles.OreAndFood.get("SlurpiesDongles.Ore.nether gold ore", "The maximum Y level the topaz ore will spawn at", ConfigPreInit.goldMaxYLevel).getInt();

                                            if (ConfigPreInit.disableNetherIronOre == false) {
                                                ConfigPreInit.ironBlockCount = SlurpiesDongles.OreAndFood.get("SlurpiesDongles.Ore.nether iron ore", "How many topaz ores spawn together", ConfigPreInit.ironBlockCount).getInt();
                                                ConfigPreInit.ironChancesToSpawn = SlurpiesDongles.OreAndFood.get("SlurpiesDongles.Ore.nether iron ore", "The chance the topaz ore can spawn per chunk", ConfigPreInit.ironChancesToSpawn).getInt();
                                                ConfigPreInit.ironMinYLevel = SlurpiesDongles.OreAndFood.get("SlurpiesDongles.Ore.nether iron ore", "The minimum Y level the topaz ore will spawn at", ConfigPreInit.ironMinYLevel).getInt();
                                                ConfigPreInit.ironMaxYLevel = SlurpiesDongles.OreAndFood.get("SlurpiesDongles.Ore.nether iron ore", "The maximum Y level the topaz ore will spawn at", ConfigPreInit.ironMaxYLevel).getInt();

                                                if (ConfigPreInit.disableNetherLapisOre == false) {
                                                    ConfigPreInit.lapisBlockCount = SlurpiesDongles.OreAndFood.get("SlurpiesDongles.Ore.nether lapis ore", "How many topaz ores spawn together", ConfigPreInit.lapisBlockCount).getInt();
                                                    ConfigPreInit.lapisChancesToSpawn = SlurpiesDongles.OreAndFood.get("SlurpiesDongles.Ore.nether lapis ore", "The chance the topaz ore can spawn per chunk", ConfigPreInit.lapisChancesToSpawn).getInt();
                                                    ConfigPreInit.lapisMinYLevel = SlurpiesDongles.OreAndFood.get("SlurpiesDongles.Ore.nether lapis ore", "The minimum Y level the topaz ore will spawn at", ConfigPreInit.lapisMinYLevel).getInt();
                                                    ConfigPreInit.lapisMaxYLevel = SlurpiesDongles.OreAndFood.get("SlurpiesDongles.Ore.nether lapis ore", "The maximum Y level the topaz ore will spawn at", ConfigPreInit.lapisMaxYLevel).getInt();

                                                    if (ConfigPreInit.disableNetherRedstoneOre == false) {
                                                        ConfigPreInit.redstoneBlockCount = SlurpiesDongles.OreAndFood.get("SlurpiesDongles.Ore.nether redstone ore", "How many topaz ores spawn together", ConfigPreInit.redstoneBlockCount).getInt();
                                                        ConfigPreInit.redstoneChancesToSpawn = SlurpiesDongles.OreAndFood.get("SlurpiesDongles.Ore.nether redstone ore", "The chance the topaz ore can spawn per chunk", ConfigPreInit.redstoneChancesToSpawn).getInt();
                                                        ConfigPreInit.redstoneMinYLevel = SlurpiesDongles.OreAndFood.get("SlurpiesDongles.Ore.nether redstone ore", "The minimum Y level the topaz ore will spawn at", ConfigPreInit.redstoneMinYLevel).getInt();
                                                        ConfigPreInit.redstoneMaxYLevel = SlurpiesDongles.OreAndFood.get("SlurpiesDongles.Ore.nether redstone ore", "The maximum Y level the topaz ore will spawn at", ConfigPreInit.redstoneMaxYLevel).getInt();

                                                        if (SlurpiesDongles.OreAndFood.hasChanged()) {
                                                            SlurpiesDongles.OreAndFood.save();
                                                        }
                                                    }
                                                }
                                            }
                                        }
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }
    }
}
