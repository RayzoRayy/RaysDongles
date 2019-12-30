package com.rbs.slurpiesdongles.init;

import com.rbs.slurpiesdongles.Reference;
import com.rbs.slurpiesdongles.armor.*;
import com.rbs.slurpiesdongles.armor.materials.ArmorMaterials;
import com.rbs.slurpiesdongles.config.ConfigGeneral;
import net.minecraft.inventory.EquipmentSlotType;
import net.minecraft.item.Item;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.registries.ObjectHolder;

import java.util.ArrayList;
import java.util.List;

@Mod.EventBusSubscriber(modid = Reference.MODID, bus = Mod.EventBusSubscriber.Bus.MOD) //This line tells forge this class has events we want to listen to, we also tell forge we want to listen to the Mod bus. (This is new in 1.13)
@ObjectHolder(Reference.MODID) //We use ObjectHolder to let forge inject the item into our variables, this to make sure when people replace our item we use the correct one.

public class ModArmor {
    //Armor
    public static Item amazonite_helmet = null;
    public static Item amazonite_chestplate = null;
    public static Item amazonite_leggings = null;
    public static Item amazonite_boots = null;
    public static Item amethyst_helmet = null;
    public static Item amethyst_chestplate = null;
    public static Item amethyst_leggings = null;
    public static Item amethyst_boots = null;
    public static Item peridot_helmet = null;
    public static Item peridot_chestplate = null;
    public static Item peridot_leggings = null;
    public static Item peridot_boots = null;
    public static Item ruby_helmet = null;
    public static Item ruby_chestplate = null;
    public static Item ruby_leggings = null;
    public static Item ruby_boots = null;
    public static Item sapphire_helmet = null;
    public static Item sapphire_chestplate = null;
    public static Item sapphire_leggings = null;
    public static Item sapphire_boots = null;
    public static Item topaz_helmet = null;
    public static Item topaz_chestplate = null;
    public static Item topaz_leggings = null;
    public static Item topaz_boots = null;
    public static Item withered_helmet = null;
    public static Item withered_chestplate = null;
    public static Item withered_leggings = null;
    public static Item withered_boots = null;

    @SubscribeEvent
    public static void registerItems(RegistryEvent.Register<Item> event) {
        event.getRegistry().registerAll();
        if (ConfigGeneral.disableAmazoniteArmor.get()) {
            event.getRegistry().register(amazonite_helmet = new AmazoniteArmor(ArmorMaterials.AMAZONITE, EquipmentSlotType.HEAD, new Item.Properties().group(Reference.tabSlurpiesDongles), "amazonite_helmet"));
            event.getRegistry().register(amazonite_chestplate = new AmazoniteArmor(ArmorMaterials.AMAZONITE, EquipmentSlotType.CHEST, new Item.Properties().group(Reference.tabSlurpiesDongles), "amazonite_chestplate"));
            event.getRegistry().register(amazonite_leggings = new AmazoniteArmor(ArmorMaterials.AMAZONITE, EquipmentSlotType.LEGS, new Item.Properties().group(Reference.tabSlurpiesDongles), "amazonite_leggings"));
            event.getRegistry().register(amazonite_boots = new AmazoniteArmor(ArmorMaterials.AMAZONITE, EquipmentSlotType.FEET, new Item.Properties().group(Reference.tabSlurpiesDongles), "amazonite_boots"));
        }
        if (ConfigGeneral.disableAmethystArmor.get()) {
            event.getRegistry().register(amethyst_helmet = new AmethystArmor(ArmorMaterials.AMETHYST, EquipmentSlotType.HEAD, new Item.Properties().group(Reference.tabSlurpiesDongles), "amethyst_helmet"));
            event.getRegistry().register(amethyst_chestplate = new AmethystArmor(ArmorMaterials.AMETHYST, EquipmentSlotType.CHEST, new Item.Properties().group(Reference.tabSlurpiesDongles), "amethyst_chestplate"));
            event.getRegistry().register(amethyst_leggings = new AmethystArmor(ArmorMaterials.AMETHYST, EquipmentSlotType.LEGS, new Item.Properties().group(Reference.tabSlurpiesDongles), "amethyst_leggings"));
            event.getRegistry().register(amethyst_boots = new AmethystArmor(ArmorMaterials.AMETHYST, EquipmentSlotType.FEET, new Item.Properties().group(Reference.tabSlurpiesDongles), "amethyst_boots"));
        }
        if (ConfigGeneral.disablePeridotArmor.get()) {
            event.getRegistry().register(peridot_helmet = new PeridotArmor(ArmorMaterials.PERIDOT, EquipmentSlotType.HEAD, new Item.Properties().group(Reference.tabSlurpiesDongles), "peridot_helmet"));
            event.getRegistry().register(peridot_chestplate = new PeridotArmor(ArmorMaterials.PERIDOT, EquipmentSlotType.CHEST, new Item.Properties().group(Reference.tabSlurpiesDongles), "peridot_chestplate"));
            event.getRegistry().register(peridot_leggings = new PeridotArmor(ArmorMaterials.PERIDOT, EquipmentSlotType.LEGS, new Item.Properties().group(Reference.tabSlurpiesDongles), "peridot_leggings"));
            event.getRegistry().register(peridot_boots = new PeridotArmor(ArmorMaterials.PERIDOT, EquipmentSlotType.FEET, new Item.Properties().group(Reference.tabSlurpiesDongles), "peridot_boots"));
        }
        if (ConfigGeneral.disableRubyArmor.get()) {
            event.getRegistry().register(ruby_helmet = new RubyArmor(ArmorMaterials.RUBY, EquipmentSlotType.HEAD, new Item.Properties().group(Reference.tabSlurpiesDongles), "ruby_helmet"));
            event.getRegistry().register(ruby_chestplate = new RubyArmor(ArmorMaterials.RUBY, EquipmentSlotType.CHEST, new Item.Properties().group(Reference.tabSlurpiesDongles), "ruby_chestplate"));
            event.getRegistry().register(ruby_leggings = new RubyArmor(ArmorMaterials.RUBY, EquipmentSlotType.LEGS, new Item.Properties().group(Reference.tabSlurpiesDongles), "ruby_leggings"));
            event.getRegistry().register(ruby_boots = new RubyArmor(ArmorMaterials.RUBY, EquipmentSlotType.FEET, new Item.Properties().group(Reference.tabSlurpiesDongles), "ruby_boots"));
        }
        if (ConfigGeneral.disableSapphireArmor.get()) {
            event.getRegistry().register(sapphire_helmet = new SapphireArmor(ArmorMaterials.SAPPHIRE, EquipmentSlotType.HEAD, new Item.Properties().group(Reference.tabSlurpiesDongles), "sapphire_helmet"));
            event.getRegistry().register(sapphire_chestplate = new SapphireArmor(ArmorMaterials.SAPPHIRE, EquipmentSlotType.CHEST, new Item.Properties().group(Reference.tabSlurpiesDongles), "sapphire_chestplate"));
            event.getRegistry().register(sapphire_leggings = new SapphireArmor(ArmorMaterials.SAPPHIRE, EquipmentSlotType.LEGS, new Item.Properties().group(Reference.tabSlurpiesDongles), "sapphire_leggings"));
            event.getRegistry().register(sapphire_boots = new SapphireArmor(ArmorMaterials.SAPPHIRE, EquipmentSlotType.FEET, new Item.Properties().group(Reference.tabSlurpiesDongles), "sapphire_boots"));
        }
        if (ConfigGeneral.disableTopazArmor.get()) {
            event.getRegistry().register(topaz_helmet = new TopazArmor(ArmorMaterials.TOPAZ, EquipmentSlotType.HEAD, new Item.Properties().group(Reference.tabSlurpiesDongles), "topaz_helmet"));
            event.getRegistry().register(topaz_chestplate = new TopazArmor(ArmorMaterials.TOPAZ, EquipmentSlotType.CHEST, new Item.Properties().group(Reference.tabSlurpiesDongles), "topaz_chestplate"));
            event.getRegistry().register(topaz_leggings = new TopazArmor(ArmorMaterials.TOPAZ, EquipmentSlotType.LEGS, new Item.Properties().group(Reference.tabSlurpiesDongles), "topaz_leggings"));
            event.getRegistry().register(topaz_boots = new TopazArmor(ArmorMaterials.TOPAZ, EquipmentSlotType.FEET, new Item.Properties().group(Reference.tabSlurpiesDongles), "topaz_boots"));
        }
        if (ConfigGeneral.disableWitheredArmor.get()) {
            event.getRegistry().register(withered_helmet = new WitheredArmor(ArmorMaterials.WITHERED, EquipmentSlotType.HEAD, new Item.Properties().group(Reference.tabSlurpiesDongles), "withered_helmet"));
            event.getRegistry().register(withered_chestplate = new WitheredArmor(ArmorMaterials.WITHERED, EquipmentSlotType.CHEST, new Item.Properties().group(Reference.tabSlurpiesDongles), "withered_chestplate"));
            event.getRegistry().register(withered_leggings = new WitheredArmor(ArmorMaterials.WITHERED, EquipmentSlotType.LEGS, new Item.Properties().group(Reference.tabSlurpiesDongles), "withered_leggings"));
            event.getRegistry().register(withered_boots = new WitheredArmor(ArmorMaterials.WITHERED, EquipmentSlotType.FEET, new Item.Properties().group(Reference.tabSlurpiesDongles), "withered_boots"));
        }
    }
}
