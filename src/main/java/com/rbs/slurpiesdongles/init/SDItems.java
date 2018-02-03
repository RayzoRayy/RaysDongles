package com.rbs.slurpiesdongles.init;

import com.rbs.slurpiesdongles.SlurpiesDongles;
import com.rbs.slurpiesdongles.armor.ArmorRubyArmor;
import com.rbs.slurpiesdongles.armor.ArmorSapphireArmor;
import com.rbs.slurpiesdongles.armor.ArmorTopazArmor;
import com.rbs.slurpiesdongles.items.*;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.init.Blocks;
import net.minecraft.inventory.EntityEquipmentSlot;
import net.minecraft.item.Item;
import net.minecraft.item.ItemSeeds;
import net.minecraftforge.registries.IForgeRegistry;

/**
 * Created by Consular on 7/19/2017.
 */
public class SDItems {



    //Armor
    public static Item rubyHelmet = new ArmorRubyArmor("ruby_helmet", ConfigPreInit.rubyArmor, "ruby", EntityEquipmentSlot.HEAD);
    public static Item rubyChestplate = new ArmorRubyArmor("ruby_chestplate", ConfigPreInit.rubyArmor, "ruby", EntityEquipmentSlot.CHEST);
    public static Item rubyLeggings = new ArmorRubyArmor("ruby_leggings", ConfigPreInit.rubyArmor, "ruby", EntityEquipmentSlot.LEGS);
    public static Item rubyBoots = new ArmorRubyArmor("ruby_boots", ConfigPreInit.rubyArmor, "ruby", EntityEquipmentSlot.FEET);
    public static Item sapphireHelmet = new ArmorSapphireArmor("sapphire_helmet", ConfigPreInit.sapphireArmor, "sapphire",EntityEquipmentSlot.HEAD);
    public static Item sapphireChestplate = new ArmorSapphireArmor("sapphire_chestplate", ConfigPreInit.sapphireArmor, "sapphire", EntityEquipmentSlot.CHEST);
    public static Item sapphireLeggings = new ArmorSapphireArmor("sapphire_leggings", ConfigPreInit.sapphireArmor, "sapphire", EntityEquipmentSlot.LEGS);
    public static Item sapphireBoots = new ArmorSapphireArmor("sapphire_boots", ConfigPreInit.sapphireArmor, "sapphire", EntityEquipmentSlot.FEET);
    public static Item topazHelmet = new ArmorTopazArmor("topaz_helmet", ConfigPreInit.topazArmor, "topaz", EntityEquipmentSlot.HEAD);
    public static Item topazChestplate = new ArmorTopazArmor("topaz_chestplate", ConfigPreInit.topazArmor, "topaz", EntityEquipmentSlot.CHEST);
    public static Item topazLeggings = new ArmorTopazArmor("topaz_leggings", ConfigPreInit.topazArmor, "topaz", EntityEquipmentSlot.LEGS);
    public static Item topazBoots = new ArmorTopazArmor("topaz_boots", ConfigPreInit.topazArmor, "topaz", EntityEquipmentSlot.FEET);

    //Axes
    public static Item emeraldAxe = new ItemAx("emerald_axe", ConfigPreInit.emeraldAxeMaterial);
    public static Item rubyAxe = new ItemAx("ruby_axe", ConfigPreInit.rubyAxeMaterial);
    public static Item sapphireAxe = new ItemAx("sapphire_axe", ConfigPreInit.sapphireAxeMaterial);
    public static Item topazAxe = new ItemAx("topaz_axe", ConfigPreInit.topazAxeMaterial);

    //Battle Axes
    public static Item diamondBattleAxe = new ItemSword("diamond_battleaxe", ConfigPreInit.diamondBattleaxeMaterial);
    public static Item emeraldBattleAxe = new ItemSword("emerald_battleaxe", ConfigPreInit.emeraldBattleaxeMaterial);
    public static Item goldBattleAxe = new ItemSword("gold_battleaxe", ConfigPreInit.goldBattleaxeMaterial);
    public static Item ironBattleAxe = new ItemSword("iron_battleaxe", ConfigPreInit.ironBattleaxeMaterial);
    public static Item rubyBattleAxe = new ItemSword("ruby_battleaxe", ConfigPreInit.rubyBattleaxeMaterial);
    public static Item sapphireBattleAxe = new ItemSword("sapphire_battleaxe", ConfigPreInit.sapphireBattleaxeMaterial);
    public static Item stoneBattleAxe = new ItemSword("stone_battleaxe", ConfigPreInit.stoneBattleaxeMaterial);
    public static Item topazBattleAxe = new ItemSword("topaz_battleaxe", ConfigPreInit.topazBattleaxeMaterial);
    public static Item woodenBattleAxe = new ItemSword("wooden_battleaxe", ConfigPreInit.woodenBattleaxeMaterial);

    //Charms
    public static Item fireResistanceCharm = new ItemFireCharm("fire_resistance_charm");
    public static Item flightCharm = new ItemFlightCharm("flight_charm");
    public static Item nightVisionCharm = new ItemNightVisionCharm("night_vision_charm");
    public static Item regenCharm = new ItemRegenCharm("regen_charm");
    public static Item speedCharm = new ItemSpeedCharm("speed_charm");
    public static Item strengthCharm = new ItemStrengthCharm("strength_charm");
    public static Item waterBreathingCharm = new ItemWaterBreathingCharm("water_breathing_charm");

    //Dust
    public static Item blueGlowstoneDust = new ItemBase("blue_glowstone_dust");
    public static Item grayGlowstoneDust = new ItemBase("gray_glowstone_dust");
    public static Item greenGlowstoneDust = new ItemBase("green_glowstone_dust");
    public static Item orangeGlowstoneDust = new ItemBase("orange_glowstone_dust");
    public static Item pinkGlowstoneDust = new ItemBase("pink_glowstone_dust");
    public static Item purpleGlowstoneDust = new ItemBase("purple_glowstone_dust");
    public static Item redGlowstoneDust = new ItemBase("red_glowstone_dust");

    //Gems
    public static Item lignite = new ItemLignite("lignite");
    public static Item ruby = new ItemBase("ruby");
    public static Item sapphire = new ItemBase("sapphire");
    public static Item hardenedTopaz = new ItemBase("hardened_topaz");
    public static Item topaz = new ItemBase("topaz");

    //Hoes
    public static Item emeraldHoe = new ItemHoe("emerald_hoe", ConfigPreInit.emeraldMaterial);
    public static Item rubyHoe = new ItemHoe("ruby_hoe", ConfigPreInit.rubyMaterial);
    public static Item sapphireHoe = new ItemHoe("sapphire_hoe", ConfigPreInit.sapphireMaterial);
    public static Item topazHoe = new ItemHoe("topaz_hoe", ConfigPreInit.topazMaterial);

    //Items
    public static Item hammer = new ItemBase("hammer");
    public static Item hotWater = new ItemBase("hot_water");
    public static Item knife = new ItemBase("knife");
    public static Item popsSign = new ItemBase("pops_sign");
    public static Item timeStaff = new ItemTimeStaff("time_staff");
    public static Item topazHandle = new ItemBase("topaz_handle");
    public static Item VMPUpgrade = new ItemBase("vmp_upgrade");
    public static Item repairItem = new DaRepairItem("repair");

    //Paxels
    public static Item diamondPaxel = new ItemPaxel("diamond_paxel", ConfigPreInit.diamondPaxelMaterial);
    public static Item emeraldPaxel = new ItemPaxel("emerald_paxel", ConfigPreInit.emeraldPaxelMaterial);
    public static Item goldPaxel = new ItemPaxel("gold_paxel", ConfigPreInit.goldPaxelMaterial);
    public static Item ironPaxel = new ItemPaxel("iron_paxel", ConfigPreInit.ironPaxelMaterial);
    public static Item stonePaxel = new ItemPaxel("stone_paxel", ConfigPreInit.stonePaxelMaterial);
    public static Item rubyPaxel = new ItemPaxel("ruby_paxel", ConfigPreInit.rubyPaxelMaterial);
    public static Item sapphirePaxel = new ItemPaxel("sapphire_paxel", ConfigPreInit.sapphirePaxelMaterial);
    public static Item topazPaxel = new ItemPaxel("topaz_paxel", ConfigPreInit.topazPaxelMaterial);
    public static Item woodenPaxel = new ItemPaxel("wooden_paxel", ConfigPreInit.woodenPaxelMaterial);

    //Pickaxes
    public static Item emeraldPickaxe = new ItemPickaxe("emerald_pickaxe", ConfigPreInit.emeraldMaterial);
    public static Item rubyPickaxe = new ItemPickaxe("ruby_pickaxe", ConfigPreInit.rubyMaterial);
    public static Item sapphirePickaxe = new ItemPickaxe("sapphire_pickaxe", ConfigPreInit.sapphireMaterial);
    public static Item topazPickaxe = new ItemPickaxe("topaz_pickaxe", ConfigPreInit.topazMaterial);
    public static Item VMPick = new VMPick("vmpick", ConfigPreInit.vmpickMaterial);

    //Seeds
    public static ItemSeeds cornSeed = new ItemCornSeed("corn_seed", SDBlocks.cornCrop, Blocks.FARMLAND);
    public static ItemSeeds strawberrySeed = new ItemStrawBerrrySeed ("strawberry_seed", SDBlocks.strawberryCrop, Blocks.FARMLAND);

    //Shovels
    public static Item emeraldShovel = new ItemShovel("emerald_shovel", ConfigPreInit.emeraldMaterial);
    public static Item rubyShovel = new ItemShovel("ruby_shovel", ConfigPreInit.rubyMaterial);
    public static Item sapphireShovel = new ItemShovel("sapphire_shovel", ConfigPreInit.sapphireMaterial);
    public static Item topazShovel = new ItemShovel("topaz_shovel", ConfigPreInit.topazMaterial);

    //Swords
    public static Item emeraldSword = new ItemSword("emerald_sword", ConfigPreInit.emeraldSwordMaterial);
    public static Item rubySword = new ItemSword("ruby_sword", ConfigPreInit.rubySwordMaterial);
    public static Item sapphireSword = new ItemSword("sapphire_sword", ConfigPreInit.sapphireSwordMaterial);
    public static Item topazSword = new ItemSword("topaz_sword", ConfigPreInit.topazSwordMaterial);


    public static void register(IForgeRegistry<Item> registry) {
        registry.registerAll(
                //armor
                rubyHelmet,
                rubyChestplate,
                rubyLeggings,
                rubyBoots,
                sapphireHelmet,
                sapphireChestplate,
                sapphireLeggings,
                sapphireBoots,
                topazHelmet,
                topazChestplate,
                topazLeggings,
                topazBoots,

                //Axes
                emeraldAxe,
                rubyAxe,
                sapphireAxe,
                topazAxe,

                //Battle Axes
                diamondBattleAxe,
                emeraldBattleAxe,
                goldBattleAxe,
                ironBattleAxe,
                rubyBattleAxe,
                sapphireBattleAxe,
                stoneBattleAxe,
                topazBattleAxe,
                woodenBattleAxe,

                //Dust
                blueGlowstoneDust,
                grayGlowstoneDust,
                greenGlowstoneDust,
                orangeGlowstoneDust,
                pinkGlowstoneDust,
                purpleGlowstoneDust,
                redGlowstoneDust,

                //Gems
                lignite,
                ruby,
                sapphire,
                hardenedTopaz,
                topaz,

                //Hoes
                emeraldHoe,
                rubyHoe,
                sapphireHoe,
                topazHoe,

                //Items
                hammer,
                hotWater,
                knife.setContainerItem(SDItems.knife),
                popsSign,
                timeStaff,
                topazHandle,
                VMPUpgrade,
                repairItem,

                //Paxels
                diamondPaxel,
                emeraldPaxel,
                goldPaxel,
                ironPaxel,
                stonePaxel,
                rubyPaxel,
                sapphirePaxel,
                topazPaxel,
                woodenPaxel,

                //Pickaxes
                emeraldPickaxe,
                rubyPickaxe,
                sapphirePickaxe,
                topazPickaxe,
                VMPick,

                //Seeds
                cornSeed,
                strawberrySeed,

                //Shovels
                emeraldShovel,
                rubyShovel,
                sapphireShovel,
                topazShovel,

                //Swords
                emeraldSword,
                rubySword,
                sapphireSword,
                topazSword


        );
        registry.registerAll();
        if (ConfigPreInit.disableFireResistanceCharm == false) {
            registry.register(fireResistanceCharm);

            if (ConfigPreInit.disableFlightCharm == false) {
                registry.register(flightCharm);
            }
        }
        if (ConfigPreInit.disableNightVisionCharm == false) {
            registry.register(nightVisionCharm);
        }
        if (ConfigPreInit.disableRegenCharm == false) {
            registry.register(regenCharm);
        }
        if (ConfigPreInit.disableSpeedCharm == false) {
            registry.register(speedCharm);
        }
        if (ConfigPreInit.disableStrengthCharm == false) {
            registry.register(strengthCharm);
        }
        if (ConfigPreInit.disableWaterBreathingCharm == false) {
            registry.register(waterBreathingCharm);
        }

    }
    public static void registerModels() {
        //Armor
        registerRender(rubyHelmet);
        registerRender(rubyChestplate);
        registerRender(rubyLeggings);
        registerRender(rubyBoots);
        registerRender(sapphireHelmet);
        registerRender(sapphireChestplate);
        registerRender(sapphireLeggings);
        registerRender(sapphireBoots);
        registerRender(topazHelmet);
        registerRender(topazChestplate);
        registerRender(topazLeggings);
        registerRender(topazBoots);

        //Axes
        registerRender(emeraldAxe);
        registerRender(rubyAxe);
        registerRender(sapphireAxe);
        registerRender(topazAxe);

        //Battle Axes
        registerRender(diamondBattleAxe);
        registerRender(emeraldBattleAxe);
        registerRender(ironBattleAxe);
        registerRender(goldBattleAxe);
        registerRender(rubyBattleAxe);
        registerRender(sapphireBattleAxe);
        registerRender(stoneBattleAxe);
        registerRender(topazBattleAxe);
        registerRender(woodenBattleAxe);

        //Dust
        registerRender(blueGlowstoneDust);
        registerRender(grayGlowstoneDust);
        registerRender(greenGlowstoneDust);
        registerRender(orangeGlowstoneDust);
        registerRender(pinkGlowstoneDust);
        registerRender(purpleGlowstoneDust);
        registerRender(redGlowstoneDust);

        //Gems
        registerRender(lignite);
        registerRender(ruby);
        registerRender(sapphire);
        registerRender(hardenedTopaz);
        registerRender(topaz);

        //Hoes
        registerRender(emeraldHoe);
        registerRender(rubyHoe);
        registerRender(sapphireHoe);
        registerRender(topazHoe);

        //Items
        registerRender(hammer);
        registerRender(hotWater);
        registerRender(knife);
        registerRender(popsSign);
        registerRender(timeStaff);
        registerRender(topazHandle);
        registerRender(VMPUpgrade);
        registerRender(repairItem);

        //Paxels
        registerRender(diamondPaxel);
        registerRender(emeraldPaxel);
        registerRender(goldPaxel);
        registerRender(ironPaxel);
        registerRender(stonePaxel);
        registerRender(rubyPaxel);
        registerRender(sapphirePaxel);
        registerRender(topazPaxel);
        registerRender(woodenPaxel);

        //Pickaxes
        registerRender(emeraldPickaxe);
        registerRender(rubyPickaxe);
        registerRender(sapphirePickaxe);
        registerRender(topazPickaxe);
        registerRender(VMPick);

        //Seeds
        registerRender(cornSeed);
        registerRender(strawberrySeed);

        //Shovels
        registerRender(emeraldShovel);
        registerRender(rubyShovel);
        registerRender(sapphireShovel);
        registerRender(topazShovel);

        //Swords
        registerRender(emeraldSword);
        registerRender(rubySword);
        registerRender(sapphireSword);
        registerRender(topazSword);

        if (ConfigPreInit.disableFireResistanceCharm == false) {
            registerRender(fireResistanceCharm);

            if (ConfigPreInit.disableFlightCharm == false) {
                registerRender(flightCharm);
            }

        }
        if (ConfigPreInit.disableNightVisionCharm == false) {
            registerRender(nightVisionCharm);

        }
        if (ConfigPreInit.disableRegenCharm == false) {
            registerRender(regenCharm);

        }
        if (ConfigPreInit.disableSpeedCharm == false) {
            registerRender(speedCharm);

        }
        if (ConfigPreInit.disableStrengthCharm == false) {
            registerRender(strengthCharm);

        }
        if (ConfigPreInit.disableWaterBreathingCharm == false) {
            registerRender(waterBreathingCharm);

        }

    }
    public static void registerRender(Item item) {
        Minecraft.getMinecraft().getRenderItem().getItemModelMesher().register(item, 0, new ModelResourceLocation(SlurpiesDongles.modId + ":" + item.getUnlocalizedName().substring(5), "inventory"));
    }
}
