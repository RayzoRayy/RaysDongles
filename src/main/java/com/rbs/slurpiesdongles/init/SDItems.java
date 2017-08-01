package com.rbs.slurpiesdongles.init;

import com.rbs.slurpiesdongles.SlurpiesDongles;
import com.rbs.slurpiesdongles.armor.ArmorRubyArmor;
import com.rbs.slurpiesdongles.armor.ArmorSapphireArmor;
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



    //armor
    /*
    public static Item rubyHelmet = new ArmorRubyArmor("ruby_helmet", ConfigPreInit.ruby_armor, "ruby", EntityEquipmentSlot.HEAD);
    public static Item rubyChestplate = new ArmorRubyArmor("ruby_chestplate", ConfigPreInit.ruby_armor, "ruby", EntityEquipmentSlot.CHEST);
    public static Item rubyLeggings = new ArmorRubyArmor("ruby_leggings", ConfigPreInit.ruby_armor, "ruby", EntityEquipmentSlot.LEGS);
    public static Item rubyBoots = new ArmorRubyArmor("ruby_boots", ConfigPreInit.ruby_armor, "ruby", EntityEquipmentSlot.FEET);
    */
    public static Item sapphireHelmet = new ArmorSapphireArmor("sapphire_helmet", ConfigPreInit.sapphireArmor, "sapphire",EntityEquipmentSlot.HEAD);
    public static Item sapphireChestplate = new ArmorSapphireArmor("sapphire_chestplate", ConfigPreInit.sapphireArmor, "sapphire", EntityEquipmentSlot.CHEST);
    public static Item sapphireLeggings = new ArmorSapphireArmor("sapphire_leggings", ConfigPreInit.sapphireArmor, "sapphire", EntityEquipmentSlot.LEGS);
    public static Item sapphireBoots = new ArmorSapphireArmor("sapphire_boots", ConfigPreInit.sapphireArmor, "sapphire", EntityEquipmentSlot.FEET);


    //Dust
    public static Item blueGlowstoneDust = new ItemBase("blue_glowstone_dust");
    public static Item grayGlowstoneDust = new ItemBase("gray_glowstone_dust");
    public static Item greenGlowstoneDust = new ItemBase("green_glowstone_dust");
    public static Item orangeGlowstoneDust = new ItemBase("orange_glowstone_dust");
    public static Item pinkGlowstoneDust = new ItemBase("pink_glowstone_dust");
    public static Item purpleGlowstoneDust = new ItemBase("purple_glowstone_dust");
    public static Item redGlowstoneDust = new ItemBase("red_glowstone_dust");

    //Items
    public static Item blender = new ItemBlender("blender");
    public static Item hammer = new ItemBase("hammer");
    public static Item hotWater = new ItemBase("hot_water");
    public static Item knife = new ItemBase("knife");
    public static Item popsSign = new ItemBase("pops_sign");
    public static Item timeStaff = new ItemTimeStaff("time_staff");
    public static Item topazHandle = new ItemBase("topaz_handle");
    public static Item VMPUpgrade = new ItemBase("vmp_upgrade");
    public static Item repairItem = new DaRepairItem("repair");


    //Gems
    public static Item lignite = new ItemLignite("lignite");
    public static Item ruby = new ItemBase("ruby");
    public static Item sapphire = new ItemBase("sapphire");
    public static Item hardenedTopaz = new ItemBase("hardened_topaz");
    public static Item topaz = new ItemBase("topaz");

    //Seeds
    public static ItemSeeds cornSeed = new ItemCornSeed("corn_seed", SDBlocks.cornCrop, Blocks.FARMLAND);


    public static void register(IForgeRegistry<Item> registry) {
        registry.registerAll(
                //armor
                //ruby_helmet,
                //ruby_chestplate,
                //ruby_leggings,
                //ruby_boots,
                sapphireHelmet,
                sapphireChestplate,
                sapphireLeggings,
                sapphireBoots,

                //Dust
                blueGlowstoneDust,
                grayGlowstoneDust,
                greenGlowstoneDust,
                orangeGlowstoneDust,
                pinkGlowstoneDust,
                purpleGlowstoneDust,
                redGlowstoneDust,

                //Items
                blender.setContainerItem(SDItems.blender),
                hammer,
                hotWater,
                knife.setContainerItem(SDItems.knife),
                popsSign,
                timeStaff,
                topazHandle,
                VMPUpgrade,
                repairItem,

                //Gems
                lignite,
                ruby,
                sapphire,
                hardenedTopaz,
                topaz,

                //Seeds
                cornSeed

        );

    }
    public static void registerModels() {
        //Armor
        //registerRender(ruby_helmet);
        //registerRender(ruby_chestplate);
       // registerRender(ruby_leggings);
       // registerRender(ruby_boots);
        registerRender(sapphireHelmet);
        registerRender(sapphireChestplate);
        registerRender(sapphireLeggings);
        registerRender(sapphireBoots);

        //Dust
        registerRender(blueGlowstoneDust);
        registerRender(grayGlowstoneDust);
        registerRender(greenGlowstoneDust);
        registerRender(orangeGlowstoneDust);
        registerRender(pinkGlowstoneDust);
        registerRender(purpleGlowstoneDust);
        registerRender(redGlowstoneDust);

        //Items
        registerRender(blender);
        registerRender(hammer);
        registerRender(hotWater);
        registerRender(knife);
        registerRender(popsSign);
        registerRender(timeStaff);
        registerRender(topazHandle);
        registerRender(VMPUpgrade);
        registerRender(repairItem);

        //Gems
        registerRender(lignite);
        registerRender(ruby);
        registerRender(sapphire);
        registerRender(hardenedTopaz);
        registerRender(topaz);

        //Seeds
        registerRender(cornSeed);


    }
    public static void registerRender(Item item) {
        Minecraft.getMinecraft().getRenderItem().getItemModelMesher().register(item, 0, new ModelResourceLocation(SlurpiesDongles.modId + ":" + item.getUnlocalizedName().substring(5), "inventory"));
    }
}
