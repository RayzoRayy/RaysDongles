package com.rbs.slurpiesdongles.init;

import com.rbs.slurpiesdongles.Reference;
import com.rbs.slurpiesdongles.config.ConfigGeneral;
import com.rbs.slurpiesdongles.items.tools.materials.ToolMaterials;
import com.rbs.slurpiesdongles.items.tools.*;
import com.rbs.slurpiesdongles.items.tools.tiers.amethyst.*;
import com.rbs.slurpiesdongles.items.tools.tiers.diamond.*;
import com.rbs.slurpiesdongles.items.tools.tiers.gold.*;
import com.rbs.slurpiesdongles.items.tools.tiers.iron.*;
import com.rbs.slurpiesdongles.items.tools.tiers.ruby.*;
import com.rbs.slurpiesdongles.items.tools.tiers.stone.*;
import com.rbs.slurpiesdongles.items.tools.tiers.topaz.*;
import com.rbs.slurpiesdongles.items.tools.tiers.withered.*;
import com.rbs.slurpiesdongles.items.tools.tiers.wood.*;
import net.minecraft.item.Item;
import net.minecraft.item.ItemTier;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.registries.ObjectHolder;


@Mod.EventBusSubscriber(modid = Reference.MODID, bus = Mod.EventBusSubscriber.Bus.MOD) //This line tells forge this class has events we want to listen to, we also tell forge we want to listen to the Mod bus. (This is new in 1.13)
@ObjectHolder(Reference.MODID) //We use ObjectHolder to let forge inject the item into our variables, this to make sure when people replace our item we use the correct one.

public class ModTools {
    //Axes
    public static Item amethyst_axe = null;
    public static Item ruby_axe = null;
    public static Item topaz_axe = null;
    public static Item withered_axe = null;
    //Battleaxes
    public static Item amethyst_battleaxe = null;
    public static Item diamond_battleaxe = null;
    public static Item gold_battleaxe = null;
    public static Item iron_battleaxe = null;
    public static Item ruby_battleaxe = null;
    public static Item stone_battleaxe = null;
    public static Item topaz_battleaxe = null;
    public static Item withered_battleaxe = null;
    public static Item wooden_battleaxe = null;
    //Excavators
    public static Item amethyst_excavator = null;
    public static Item diamond_excavator = null;
    public static Item gold_excavator = null;
    public static Item iron_excavator = null;
    public static Item ruby_excavator = null;
    public static Item stone_excavator = null;
    public static Item topaz_excavator = null;
    public static Item withered_excavator = null;
    public static Item wooden_excavator = null;
    public static Item vm_excavator = null;
    //Hammers
    public static Item amethyst_hammer = null;
    public static Item diamond_hammer = null;
    public static Item gold_hammer = null;
    public static Item iron_hammer = null;
    public static Item ruby_hammer = null;
    public static Item stone_hammer = null;
    public static Item topaz_hammer = null;
    public static Item vm_hammer = null;
    public static Item withered_hammer = null;
    public static Item wooden_hammer = null;
    //Hoes
    public static Item amethyst_hoe = null;
    public static Item ruby_hoe = null;
    public static Item topaz_hoe = null;
    public static Item withered_hoe = null;
    //Lumber Axes
    public static Item amethyst_lumber_axe = null;
    public static Item diamond_lumber_axe = null;
    public static Item gold_lumber_axe = null;
    public static Item iron_lumber_axe = null;
    public static Item ruby_lumber_axe = null;
    public static Item stone_lumber_axe = null;
    public static Item topaz_lumber_axe = null;
    public static Item withered_lumber_axe = null;
    public static Item wooden_lumber_axe = null;
    //Paxels
    public static Item amethyst_paxel = null;
    public static Item diamond_paxel = null;
    public static Item gold_paxel = null;
    public static Item iron_paxel = null;
    public static Item ruby_paxel = null;
    public static Item stone_paxel = null;
    public static Item topaz_paxel = null;
    public static Item withered_paxel = null;
    public static Item wooden_paxel = null;
    //Pickaxes
    public static Item amethyst_pickaxe = null;
    public static Item ruby_pickaxe = null;
    public static Item topaz_pickaxe = null;
    public static Item withered_pickaxe = null;
    public static Item vmpick = null;
    //Shovels
    public static Item amethyst_shovel = null;
    public static Item ruby_shovel = null;
    public static Item topaz_shovel = null;
    public static Item withered_shovel = null;
    //Swords
    public static Item amethyst_sword = null;
    public static Item ruby_sword = null;
    public static Item topaz_sword = null;
    public static Item withered_sword = null;
    @SubscribeEvent
    public static void registerItems(RegistryEvent.Register<Item> event) {
        event.getRegistry().registerAll();
        if (ConfigGeneral.disableAmethystTools.get()) {
            event.getRegistry().register(amethyst_axe = new AmethystAxe(ToolMaterials.AMETHYST, 6, -3.1F, new Item.Properties().group(Reference.tabSlurpiesDongles), "amethyst_axe"));
            event.getRegistry().register(amethyst_battleaxe = new AmethystSword(ToolMaterials.AMETHYST_BATTLEAXE, 3, -3.1F, new Item.Properties().group(Reference.tabSlurpiesDongles), "amethyst_battleaxe"));
            event.getRegistry().register(amethyst_excavator = new AmethystExcavator(ToolMaterials.AMETHYST, 1, -2.6F, new Item.Properties().group(Reference.tabSlurpiesDongles), "amethyst_excavator"));
            event.getRegistry().register(amethyst_hammer = new AmethystHammer(ToolMaterials.AMETHYST, 1, -2.8F, new Item.Properties().group(Reference.tabSlurpiesDongles), "amethyst_hammer"));
            event.getRegistry().register(amethyst_hoe = new AmethystHoe(ToolMaterials.AMETHYST, 1, new Item.Properties().group(Reference.tabSlurpiesDongles), "amethyst_hoe"));
            event.getRegistry().register(amethyst_lumber_axe = new AmethystLumbarAxe(ToolMaterials.AMETHYST, 2, -3.1F, new Item.Properties().group(Reference.tabSlurpiesDongles), "amethyst_lumber_axe"));
            event.getRegistry().register(amethyst_paxel = new AmethystPaxel(ToolMaterials.AMETHYST_PAXEL, 3, -3.0F, null, new Item.Properties().group(Reference.tabSlurpiesDongles), "amethyst_paxel"));
            event.getRegistry().register(amethyst_pickaxe = new AmethystPickaxe(ToolMaterials.AMETHYST, 1, -2.8F, new Item.Properties().group(Reference.tabSlurpiesDongles), "amethyst_pickaxe"));
            event.getRegistry().register(amethyst_shovel = new AmethystShovel(ToolMaterials.AMETHYST, 1, -2.6F, new Item.Properties().group(Reference.tabSlurpiesDongles), "amethyst_shovel"));
            event.getRegistry().register(amethyst_sword = new AmethystSword(ToolMaterials.AMETHYST_SWORD, 3, -2.4F, new Item.Properties().group(Reference.tabSlurpiesDongles), "amethyst_sword"));
        }
        if (ConfigGeneral.disableRubyTools.get()) {
            event.getRegistry().register(ruby_axe = new RubyAxe(ToolMaterials.RUBY, 3, -3.1F, new Item.Properties().group(Reference.tabSlurpiesDongles), "ruby_axe"));
            event.getRegistry().register(ruby_battleaxe = new RubySword(ToolMaterials.RUBY_BATTLEAXE, 3, -3.1F, new Item.Properties().group(Reference.tabSlurpiesDongles), "ruby_battleaxe"));
            event.getRegistry().register(ruby_excavator = new RubyExcavator(ToolMaterials.RUBY, 1, -2.6F, new Item.Properties().group(Reference.tabSlurpiesDongles), "ruby_excavator"));
            event.getRegistry().register(ruby_hammer = new RubyHammer(ToolMaterials.RUBY, 1, -2.8F, new Item.Properties().group(Reference.tabSlurpiesDongles), "ruby_hammer"));
            event.getRegistry().register(ruby_hoe = new RubyHoe(ToolMaterials.RUBY, 1, new Item.Properties().group(Reference.tabSlurpiesDongles), "ruby_hoe"));
            event.getRegistry().register(ruby_lumber_axe = new RubyLumbarAxe(ToolMaterials.RUBY, 4, -3.1F, new Item.Properties().group(Reference.tabSlurpiesDongles), "ruby_lumber_axe"));
            event.getRegistry().register(ruby_paxel = new RubyPaxel(ToolMaterials.RUBY_PAXEL, 2, -3.0F,null, new Item.Properties().group(Reference.tabSlurpiesDongles), "ruby_paxel"));
            event.getRegistry().register(ruby_pickaxe = new RubyPickaxe(ToolMaterials.RUBY, 1, -2.8F, new Item.Properties().group(Reference.tabSlurpiesDongles), "ruby_pickaxe"));
            event.getRegistry().register(ruby_shovel = new RubyShovel(ToolMaterials.RUBY, 1, -2.6F, new Item.Properties().group(Reference.tabSlurpiesDongles), "ruby_shovel"));
            event.getRegistry().register(ruby_sword = new RubySword(ToolMaterials.RUBY_SWORD, 2, -2.4F, new Item.Properties().group(Reference.tabSlurpiesDongles), "ruby_sword"));
        }
        if (ConfigGeneral.disableTopazTools.get()) {
            event.getRegistry().register(topaz_axe = new TopazAxe(ToolMaterials.TOPAZ, 6, -3.1F, new Item.Properties().group(Reference.tabSlurpiesDongles), "topaz_axe"));
            event.getRegistry().register(topaz_battleaxe = new TopazSword(ToolMaterials.TOPAZ_BATTLEAXE, 2, -3.1F, new Item.Properties().group(Reference.tabSlurpiesDongles), "topaz_battleaxe"));
            event.getRegistry().register(topaz_excavator = new TopazExcavator(ToolMaterials.TOPAZ, 1, -2.6F, new Item.Properties().group(Reference.tabSlurpiesDongles), "topaz_excavator"));
            event.getRegistry().register(topaz_hammer = new TopazHammer(ToolMaterials.TOPAZ, 1, -2.8F, new Item.Properties().group(Reference.tabSlurpiesDongles), "topaz_hammer"));
            event.getRegistry().register(topaz_hoe = new TopazHoe(ToolMaterials.TOPAZ, 1, new Item.Properties().group(Reference.tabSlurpiesDongles), "topaz_hoe"));
            event.getRegistry().register(topaz_lumber_axe = new TopazLumbarAxe(ToolMaterials.TOPAZ, 4, -3.1F, new Item.Properties().group(Reference.tabSlurpiesDongles), "topaz_lumber_axe"));
            event.getRegistry().register(topaz_paxel = new TopazPaxel(ToolMaterials.TOPAZ_PAXEL, 2, -3.0F,null, new Item.Properties().group(Reference.tabSlurpiesDongles), "topaz_paxel"));
            event.getRegistry().register(topaz_pickaxe = new TopazPickaxe(ToolMaterials.TOPAZ, 1, -2.8F, new Item.Properties().group(Reference.tabSlurpiesDongles), "topaz_pickaxe"));
            event.getRegistry().register(topaz_shovel = new TopazShovel(ToolMaterials.TOPAZ, 1, -2.6F, new Item.Properties().group(Reference.tabSlurpiesDongles), "topaz_shovel"));
            event.getRegistry().register(topaz_sword = new TopazSword(ToolMaterials.TOPAZ_SWORD, 2, -2.4F, new Item.Properties().group(Reference.tabSlurpiesDongles), "topaz_sword"));
        }
        if (ConfigGeneral.disableWitheredTools.get()) {
            event.getRegistry().register(withered_axe = new WitheredAxe(ToolMaterials.WITHERED, 7, -3.1F, new Item.Properties().group(Reference.tabSlurpiesDongles), "withered_axe"));
            event.getRegistry().register(withered_battleaxe = new WitheredSword(ToolMaterials.WITHERED_BATTLEAXE, 2, -3.1F, new Item.Properties().group(Reference.tabSlurpiesDongles), "withered_battleaxe"));
            event.getRegistry().register(withered_excavator = new WitheredExcavator(ToolMaterials.WITHERED, 1, -2.6F, new Item.Properties().group(Reference.tabSlurpiesDongles), "withered_excavator"));
            event.getRegistry().register(withered_hammer = new WitheredHammer(ToolMaterials.WITHERED, 1, -2.8F, new Item.Properties().group(Reference.tabSlurpiesDongles), "withered_hammer"));
            event.getRegistry().register(withered_hoe = new WitheredHoe(ToolMaterials.WITHERED, 1, new Item.Properties().group(Reference.tabSlurpiesDongles), "withered_hoe"));
            event.getRegistry().register(withered_lumber_axe = new WitheredLumbarAxe(ToolMaterials.WITHERED, 8, -3.1F, new Item.Properties().group(Reference.tabSlurpiesDongles), "withered_lumber_axe"));
            event.getRegistry().register(withered_paxel = new WitheredPaxel(ToolMaterials.WITHERED_PAXEL, 2, -3.0F,null, new Item.Properties().group(Reference.tabSlurpiesDongles), "withered_paxel"));
            event.getRegistry().register(withered_pickaxe = new WitheredPickaxe(ToolMaterials.WITHERED, 1, -2.8F, new Item.Properties().group(Reference.tabSlurpiesDongles), "withered_pickaxe"));
            event.getRegistry().register(withered_shovel = new WitheredShovel(ToolMaterials.WITHERED, 1, -2.6F, new Item.Properties().group(Reference.tabSlurpiesDongles), "withered_shovel"));
            event.getRegistry().register(withered_sword = new WitheredSword(ToolMaterials.WITHERED_SWORD, 2, -2.4F, new Item.Properties().group(Reference.tabSlurpiesDongles), "withered_sword"));
        }
        //Vanilla Addons
        if (ConfigGeneral.disableDiamondToolsExtras.get()) {
            event.getRegistry().register(diamond_battleaxe = new DiamondBA(ItemTier.DIAMOND, 4, -3.1F, new Item.Properties().group(Reference.tabSlurpiesDongles), "diamond_battleaxe"));
            event.getRegistry().register(diamond_excavator = new DiamondExcavator(ItemTier.DIAMOND, 1, -2.6F, new Item.Properties().group(Reference.tabSlurpiesDongles), "diamond_excavator"));
            event.getRegistry().register(diamond_hammer = new DiamondHammer(ItemTier.DIAMOND, 1, -2.8F, new Item.Properties().group(Reference.tabSlurpiesDongles), "diamond_hammer"));
            event.getRegistry().register(diamond_lumber_axe = new DiamondLumbarAxe(ItemTier.DIAMOND, 3, -3.1F, new Item.Properties().group(Reference.tabSlurpiesDongles), "diamond_lumber_axe"));
            event.getRegistry().register(diamond_paxel = new DiamondPaxel(ToolMaterials.DIAMOND_PAXEL, 3, -3.0F,null, new Item.Properties().group(Reference.tabSlurpiesDongles),"diamond_paxel"));
        }
        if (ConfigGeneral.disableGoldToolsExtras.get()) {
            event.getRegistry().register(gold_battleaxe = new GoldenBA(ItemTier.GOLD, 4, -3.1F, new Item.Properties().group(Reference.tabSlurpiesDongles), "gold_battleaxe"));
            event.getRegistry().register(gold_excavator = new GoldenExcavator(ItemTier.GOLD, 1, -2.6F, new Item.Properties().group(Reference.tabSlurpiesDongles), "gold_excavator"));
            event.getRegistry().register(gold_hammer = new GoldenHammer(ItemTier.GOLD, 1, -2.8F, new Item.Properties().group(Reference.tabSlurpiesDongles), "gold_hammer"));
            event.getRegistry().register(gold_lumber_axe = new GoldenLumbarAxe(ItemTier.GOLD, 3, -3.1F, new Item.Properties().group(Reference.tabSlurpiesDongles), "gold_lumber_axe"));
            event.getRegistry().register(gold_paxel = new GoldenPaxel(ToolMaterials.GOLD_PAXEL, 3, -3.0F,null, new Item.Properties().group(Reference.tabSlurpiesDongles),"gold_paxel"));
        }
        if (ConfigGeneral.disableIronToolsExtras.get()) {
            event.getRegistry().register(iron_battleaxe = new IronBA(ItemTier.IRON, 4, -3.1F, new Item.Properties().group(Reference.tabSlurpiesDongles), "iron_battleaxe"));
            event.getRegistry().register(iron_excavator = new IronExcavator(ItemTier.IRON, 1, -2.6F, new Item.Properties().group(Reference.tabSlurpiesDongles), "iron_excavator"));
            event.getRegistry().register(iron_hammer = new IronHammer(ItemTier.IRON, 1, -2.8F, new Item.Properties().group(Reference.tabSlurpiesDongles), "iron_hammer"));
            event.getRegistry().register(iron_lumber_axe = new IronLumbarAxe(ItemTier.IRON, 3, -3.1F, new Item.Properties().group(Reference.tabSlurpiesDongles), "iron_lumber_axe"));
            event.getRegistry().register(iron_paxel = new IronPaxel(ToolMaterials.IRON_PAXEL, 3, -3.0F,null, new Item.Properties().group(Reference.tabSlurpiesDongles), "iron_paxel"));
        }
        if (ConfigGeneral.disableStoneToolsExtras.get()) {
            event.getRegistry().register(stone_battleaxe = new StoneBA(ItemTier.STONE, 4, -3.1F, new Item.Properties().group(Reference.tabSlurpiesDongles), "stone_battleaxe"));
            event.getRegistry().register(stone_excavator = new StoneExcavator(ItemTier.STONE, 1, -2.6F, new Item.Properties().group(Reference.tabSlurpiesDongles), "stone_excavator"));
            event.getRegistry().register(stone_hammer = new StoneHammer(ItemTier.STONE, 1, -2.8F, new Item.Properties().group(Reference.tabSlurpiesDongles), "stone_hammer"));
            event.getRegistry().register(stone_lumber_axe = new StoneLumbarAxe(ItemTier.STONE, 1, -3.1F, new Item.Properties().group(Reference.tabSlurpiesDongles), "stone_lumber_axe"));
            event.getRegistry().register(stone_paxel = new StonePaxel(ToolMaterials.STONE_PAXEL, 2, -3.0F,null, new Item.Properties().group(Reference.tabSlurpiesDongles), "stone_paxel"));
        }
        if (ConfigGeneral.disableWoodenToolsExtras.get()) {
            event.getRegistry().register(wooden_battleaxe = new WoodenBA(ItemTier.WOOD, 4, -3.1F, new Item.Properties().group(Reference.tabSlurpiesDongles), "wooden_battleaxe"));
            event.getRegistry().register(wooden_excavator = new WoodenExcavator(ItemTier.WOOD, 1, -2.6F, new Item.Properties().group(Reference.tabSlurpiesDongles), "wooden_excavator"));
            event.getRegistry().register(wooden_hammer = new WoodenHammer(ItemTier.WOOD, 1, -2.8F, new Item.Properties().group(Reference.tabSlurpiesDongles), "wooden_hammer"));
            event.getRegistry().register(wooden_lumber_axe = new WoodenLumbarAxe(ItemTier.WOOD, 1, -3.1F, new Item.Properties().group(Reference.tabSlurpiesDongles), "wooden_lumber_axe"));
            event.getRegistry().register(wooden_paxel = new WoodenPaxel(ToolMaterials.WOODEN_PAXEL, 3, -3.0F,null, new Item.Properties().group(Reference.tabSlurpiesDongles),"wooden_paxel"));
        }
        if (ConfigGeneral.disableVMTools.get()) {
            event.getRegistry().register(vm_excavator = new VMExcavator(ToolMaterials.VMPICK, 1, -2.6F, new Item.Properties().group(Reference.tabSlurpiesDongles), "vm_excavator"));
            event.getRegistry().register(vm_hammer = new VMHammer(ToolMaterials.VMPICK, 1, -2.8F, new Item.Properties().group(Reference.tabSlurpiesDongles), "vm_hammer"));
            event.getRegistry().register(vmpick = new VMPick(ToolMaterials.VMPICK, 1, -2.8F, new Item.Properties().group(Reference.tabSlurpiesDongles), "vmpick"));
        }
    }
}
