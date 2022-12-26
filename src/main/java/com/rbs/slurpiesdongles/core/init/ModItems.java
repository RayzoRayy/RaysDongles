package com.rbs.slurpiesdongles.core.init;

import com.rbs.slurpiesdongles.SlurpiesDongles;
import com.rbs.slurpiesdongles.common.items.ContainerItem;
import com.rbs.slurpiesdongles.common.items.armor.WitheredArmor;
import com.rbs.slurpiesdongles.common.items.armor.material.SlurpiesArmorMaterials;
import com.rbs.slurpiesdongles.common.items.charms.*;
import com.rbs.slurpiesdongles.common.items.tools.*;
import com.rbs.slurpiesdongles.common.items.tools.material.TMTiers;
import com.rbs.slurpiesdongles.core.config.ConfigGeneral;
import com.rbs.slurpiesdongles.core.itemgroup.RDItemGroup;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.item.*;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.RegistryObject;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

public class ModItems {

    public static final DeferredRegister<Item> ITEMS = DeferredRegister.create(ForgeRegistries.ITEMS, SlurpiesDongles.MOD_ID);

    public static RegistryObject<Item> WITHERED_HELMET = null;
    public static RegistryObject<Item> WITHERED_CHEST = null;
    public static RegistryObject<Item> WITHERED_LEGS = null;
    public static RegistryObject<Item> WITHERED_BOOTS = null;

    //Dusts
    public static final RegistryObject<Item> ENDER_DUST = ITEMS.register("ender_dust",
            () -> new Item(new Item.Properties().tab(RDItemGroup.tabSlurpiesDongles)));

    //Glowstone Dusts
    public static final RegistryObject<Item> BLUE_GLOWSTONE_DUST = ITEMS.register("blue_glowstone_dust",
            () -> new Item(new Item.Properties().tab(RDItemGroup.tabSlurpiesDongles)));
    public static final RegistryObject<Item> GRAY_GLOWSTONE_DUST = ITEMS.register("gray_glowstone_dust",
            () -> new Item(new Item.Properties().tab(RDItemGroup.tabSlurpiesDongles)));
    public static final RegistryObject<Item> GREEN_GLOWSTONE_DUST = ITEMS.register("green_glowstone_dust",
            () -> new Item(new Item.Properties().tab(RDItemGroup.tabSlurpiesDongles)));
    public static final RegistryObject<Item> ORANGE_GLOWSTONE_DUST = ITEMS.register("orange_glowstone_dust",
            () -> new Item(new Item.Properties().tab(RDItemGroup.tabSlurpiesDongles)));
    public static final RegistryObject<Item> PINK_GLOWSTONE_DUST = ITEMS.register("pink_glowstone_dust",
            () -> new Item(new Item.Properties().tab(RDItemGroup.tabSlurpiesDongles)));
    public static final RegistryObject<Item> PURPLE_GLOWSTONE_DUST = ITEMS.register("purple_glowstone_dust",
            () -> new Item(new Item.Properties().tab(RDItemGroup.tabSlurpiesDongles)));
    public static final RegistryObject<Item> RED_GLOWSTONE_DUST = ITEMS.register("red_glowstone_dust",
            () -> new Item(new Item.Properties().tab(RDItemGroup.tabSlurpiesDongles)));
    //Gems
    public static final RegistryObject<Item> RUBY = ITEMS.register("ruby",
            () -> new Item(new Item.Properties().tab(RDItemGroup.tabSlurpiesDongles)));
    public static final RegistryObject<Item> RAW_TOPAZ = ITEMS.register("raw_topaz",
            () -> new Item(new Item.Properties().tab(RDItemGroup.tabSlurpiesDongles)));
    public static final RegistryObject<Item> TOPAZ = ITEMS.register("topaz",
            () -> new Item(new Item.Properties().tab(RDItemGroup.tabSlurpiesDongles)));
    //Items

    public static final RegistryObject<Item> VME_UPGRADE = ITEMS.register("vme_upgrade",
            () -> new Item(new Item.Properties().tab(RDItemGroup.tabSlurpiesDongles)));
    public static final RegistryObject<Item> VMH_UPGRADE = ITEMS.register("vmh_upgrade",
            () -> new Item(new Item.Properties().tab(RDItemGroup.tabSlurpiesDongles)));
    public static final RegistryObject<Item> VMP_UPGRADE = ITEMS.register("vmp_upgrade",
            () -> new Item(new Item.Properties().tab(RDItemGroup.tabSlurpiesDongles)));


    //Config Registries
    public static void register(IEventBus eventBus) {
        ITEMS.register(eventBus);
        //Charms
        if (ConfigGeneral.disableAbsorptionCharm.get()) {
            RegistryObject<Item> ABSORPTION_CHARM = ITEMS.register("absorption_charm",
                    () -> new AbsorptionCharm(new Item.Properties().tab(RDItemGroup.tabSlurpiesDongles)));
        }
        if (ConfigGeneral.disableAbsorptionCharmTier2.get()) {
            RegistryObject<Item> ABSORPTION_CHARM_TIER_2 = ITEMS.register("absorption_charm_tier_2",
                    () -> new AbsorptionCharmTier2(new Item.Properties().tab(RDItemGroup.tabSlurpiesDongles)));
        }
        if (ConfigGeneral.disableFireResistanceCharm.get()) {
            RegistryObject<Item> FIRE_RESISTANCE_CHARM = ITEMS.register("fire_resistance_charm",
                    () -> new FireCharm(new Item.Properties().tab(RDItemGroup.tabSlurpiesDongles)));
        }
        if (ConfigGeneral.disableNightVisionCharm.get()) {
            RegistryObject<Item> NIGHT_VISION_CHARM = ITEMS.register("night_vision_charm",
                    () -> new NightVisionCharm(new Item.Properties().tab(RDItemGroup.tabSlurpiesDongles)));
        }
        if (ConfigGeneral.disableRegenerationCharm.get()) {
            RegistryObject<Item> REGEN_CHARM = ITEMS.register("regen_charm",
                    () -> new RegenerationCharm(new Item.Properties().tab(RDItemGroup.tabSlurpiesDongles)));
        }
        if (ConfigGeneral.disableRegenerationCharmTier2.get()) {
            RegistryObject<Item> REGEN_CHARM_TIER_2 = ITEMS.register("regen_charm_tier_2",
                    () -> new RegenerationCharmTier2(new Item.Properties().tab(RDItemGroup.tabSlurpiesDongles)));
        }
        if (ConfigGeneral.disableSpeedCharm.get()) {
            RegistryObject<Item> SPEED_CHARM = ITEMS.register("speed_charm",
                    () -> new SpeedCharm(new Item.Properties().tab(RDItemGroup.tabSlurpiesDongles)));
        }
        if (ConfigGeneral.disableSpeedCharmTier2.get()) {
            RegistryObject<Item> SPEED_CHARM_TIER_2 = ITEMS.register("speed_charm_tier_2",
                    () -> new SpeedCharmTier2(new Item.Properties().tab(RDItemGroup.tabSlurpiesDongles)));
        }
        if (ConfigGeneral.disableStrengthCharm.get()) {
            RegistryObject<Item> STRENGTH_CHARM = ITEMS.register("strength_charm",
                    () -> new StrengthCharm(new Item.Properties().tab(RDItemGroup.tabSlurpiesDongles)));
        }
        if (ConfigGeneral.disableStrengthCharmTier2.get()) {
            RegistryObject<Item> STRENGTH_CHARM_TIER_2 = ITEMS.register("strength_charm_tier_2",
                    () -> new StrengthCharmTier2(new Item.Properties().tab(RDItemGroup.tabSlurpiesDongles)));
        }
        if (ConfigGeneral.disableWaterbreathingCharm.get()) {
            RegistryObject<Item> WATER_BREATHING_CHARM = ITEMS.register("water_breathing_charm",
                    () -> new WaterBreathingCharm(new Item.Properties().tab(RDItemGroup.tabSlurpiesDongles)));
        }
        //Actual Items
        if (ConfigGeneral.disableBlender.get()) {
            RegistryObject<Item> BLENDER = ITEMS.register("blender",
                    () -> new ContainerItem(new Item.Properties().tab(RDItemGroup.tabSlurpiesDongles)));
        }
        if (ConfigGeneral.disableKnife.get()) {
           RegistryObject<Item> KNIFE = ITEMS.register("knife",
                    () -> new ContainerItem(new Item.Properties().tab(RDItemGroup.tabSlurpiesDongles)));
        }
        if (ConfigGeneral.disableNetherStarChunk.get()) {
            RegistryObject<Item> NETHER_STAR_CHUNK = ITEMS.register("nether_star_chunk",
                    () -> new Item(new Item.Properties().tab(RDItemGroup.tabSlurpiesDongles)));
        }
        if (ConfigGeneral.disablePopsSign.get()) {
            RegistryObject<Item> POPS_SIGN = ITEMS.register("pops_sign",
                    () -> new Item(new Item.Properties().tab(RDItemGroup.tabSlurpiesDongles)));
        }
        if (ConfigGeneral.disableStoneRod.get()) {
            RegistryObject<Item> STONE_ROD = ITEMS.register("stone_rod",
                    () -> new Item(new Item.Properties().tab(RDItemGroup.tabSlurpiesDongles)));
        }

        //Amethyst
        if (ConfigGeneral.disableAmethystArmor.get()) {
            RegistryObject<Item> AMETHYST_HELMET = ITEMS.register("amethyst_helmet",
                    () -> new ArmorItem(SlurpiesArmorMaterials.AMETHYST, EquipmentSlot.HEAD, new Item.Properties().tab(RDItemGroup.tabSlurpiesDongles)));
            RegistryObject<Item> AMETHYST_CHEST = ITEMS.register("amethyst_chestplate",
                    () -> new ArmorItem(SlurpiesArmorMaterials.AMETHYST, EquipmentSlot.CHEST, new Item.Properties().tab(RDItemGroup.tabSlurpiesDongles)));
            RegistryObject<Item> AMETHYST_LEGS = ITEMS.register("amethyst_leggings",
                    () -> new ArmorItem(SlurpiesArmorMaterials.AMETHYST, EquipmentSlot.LEGS, new Item.Properties().tab(RDItemGroup.tabSlurpiesDongles)));
            RegistryObject<Item> AMETHYST_BOOTS = ITEMS.register("amethyst_boots",
                    () -> new ArmorItem(SlurpiesArmorMaterials.AMETHYST, EquipmentSlot.FEET, new Item.Properties().tab(RDItemGroup.tabSlurpiesDongles)));
        }
        if (ConfigGeneral.disableAmethystTools.get()) {
            RegistryObject<Item> AMETHYST_AXE = ITEMS.register("amethyst_axe",
                    () -> new AxeItem(TMTiers.AMETHYST, 6.0F, -3.0F, props()));
            RegistryObject<Item> AMETHYST_BATTLE_AXE = ITEMS.register("amethyst_battleaxe",
                    () -> new BattleAxeItem(TMTiers.AMETHYST_BATTLEAXE, 7.0F, -2.9F, props()));
            RegistryObject<Item> AMETHYST_EXCAVATOR = ITEMS.register("amethyst_excavator",
                    () -> new ExcavatorItem(TMTiers.AMETHYST, 1, -3.0F, props()));
            RegistryObject<Item> AMETHYST_HAMMER = ITEMS.register("amethyst_hammer",
                    () -> new HammerItem(1, -2.8F, TMTiers.AMETHYST, props()));
            RegistryObject<Item> AMETHYST_HOE = ITEMS.register("amethyst_hoe",
                    () -> new HoeItem(TMTiers.AMETHYST, -3, 0.0F, props()));
            RegistryObject<Item> AMETHYST_LUMBER_AXE = ITEMS.register("amethyst_lumber_axe",
                    () -> new LumberAxeItem(TMTiers.AMETHYST, 6.0F, -3.0F, props()));
            RegistryObject<Item> AMETHYST_PAXEL = ITEMS.register("amethyst_paxel",
                    () -> new PaxelItem(1, -2.8F, TMTiers.AMETHYST_PAXEL, props()));
            RegistryObject<Item> AMETHYST_PICKAXE = ITEMS.register("amethyst_pickaxe",
                    () -> new PickaxeItem(TMTiers.AMETHYST, 1, -2.8F, props()));
            RegistryObject<Item> AMETHYST_SHOVEL = ITEMS.register("amethyst_shovel",
                    () -> new ShovelItem(TMTiers.AMETHYST, 1.5F, -3.0F, props()));
            RegistryObject<Item> AMETHYST_SWORD = ITEMS.register("amethyst_sword",
                    () -> new SwordItem(TMTiers.AMETHYST_SWORD, 4, -2.4F, props()));
        }
        //Ruby
        if (ConfigGeneral.disableRubyArmor.get()) {
            RegistryObject<Item> RUBY_HELMET = ITEMS.register("ruby_helmet",
                    () -> new ArmorItem(SlurpiesArmorMaterials.RUBY, EquipmentSlot.HEAD, new Item.Properties().tab(RDItemGroup.tabSlurpiesDongles)));
            RegistryObject<Item> RUBY_CHEST = ITEMS.register("ruby_chestplate",
                    () -> new ArmorItem(SlurpiesArmorMaterials.RUBY, EquipmentSlot.CHEST, new Item.Properties().tab(RDItemGroup.tabSlurpiesDongles)));
            RegistryObject<Item> RUBY_LEGS = ITEMS.register("ruby_leggings",
                    () -> new ArmorItem(SlurpiesArmorMaterials.RUBY, EquipmentSlot.LEGS, new Item.Properties().tab(RDItemGroup.tabSlurpiesDongles)));
            RegistryObject<Item> RUBY_BOOTS = ITEMS.register("ruby_boots",
                    () -> new ArmorItem(SlurpiesArmorMaterials.RUBY, EquipmentSlot.FEET, new Item.Properties().tab(RDItemGroup.tabSlurpiesDongles)));
        }
        if (ConfigGeneral.disableRubyTools.get()) {
            RegistryObject<Item> RUBY_AXE = ITEMS.register("ruby_axe",
                    () -> new AxeItem(TMTiers.RUBY, 7.0F, -3.1F, props()));
            RegistryObject<Item> RUBY_BATTLE_AXE = ITEMS.register("ruby_battleaxe",
                    () -> new BattleAxeItem(TMTiers.RUBY_BATTLEAXE, 8.0F, -3.0F, props()));
            RegistryObject<Item> RUBY_EXCAVATOR = ITEMS.register("ruby_excavator",
                    () -> new ExcavatorItem(TMTiers.RUBY, 1, -3.0F, props()));
            RegistryObject<Item> RUBY_HAMMER = ITEMS.register("ruby_hammer",
                    () -> new HammerItem(1, -2.8F, TMTiers.RUBY, props()));
            RegistryObject<Item> RUBY_HOE = ITEMS.register("ruby_hoe",
                    () -> new HoeItem(TMTiers.RUBY, -2, 0.0F, props()));
            RegistryObject<Item> RUBY_LUMBER_AXE = ITEMS.register("ruby_lumber_axe",
                    () -> new LumberAxeItem(TMTiers.RUBY, 7.0F, -3.1F, props()));
            RegistryObject<Item> RUBY_PAXEL = ITEMS.register("ruby_paxel",
                    () -> new PaxelItem(1, -2.8F, TMTiers.RUBY_PAXEL, props()));
            RegistryObject<Item> RUBY_PICKAXE = ITEMS.register("ruby_pickaxe",
                    () -> new PickaxeItem(TMTiers.RUBY, 1, -2.8F, props()));
            RegistryObject<Item> RUBY_SHOVEL = ITEMS.register("ruby_shovel",
                    () -> new ShovelItem(TMTiers.RUBY, 1.5F, -3.0F, props()));
            RegistryObject<Item> RUBY_SWORD = ITEMS.register("ruby_sword",
                    () -> new SwordItem(TMTiers.RUBY_SWORD, 4, -2.4F, props()));
        }
        //Topaz
        if (ConfigGeneral.disableTopazArmor.get()) {
            RegistryObject<Item> TOPAZ_HELMET = ITEMS.register("topaz_helmet",
                    () -> new ArmorItem(SlurpiesArmorMaterials.TOPAZ, EquipmentSlot.HEAD, new Item.Properties().tab(RDItemGroup.tabSlurpiesDongles)));
            RegistryObject<Item> TOPAZ_CHEST = ITEMS.register("topaz_chestplate",
                    () -> new ArmorItem(SlurpiesArmorMaterials.TOPAZ, EquipmentSlot.CHEST, new Item.Properties().tab(RDItemGroup.tabSlurpiesDongles)));
            RegistryObject<Item> TOPAZ_LEGS = ITEMS.register("topaz_leggings",
                    () -> new ArmorItem(SlurpiesArmorMaterials.TOPAZ, EquipmentSlot.LEGS, new Item.Properties().tab(RDItemGroup.tabSlurpiesDongles)));
            RegistryObject<Item> TOPAZ_BOOTS = ITEMS.register("topaz_boots",
                    () -> new ArmorItem(SlurpiesArmorMaterials.TOPAZ, EquipmentSlot.FEET, new Item.Properties().tab(RDItemGroup.tabSlurpiesDongles)));
        }
        if (ConfigGeneral.disableTopazTools.get()) {
            RegistryObject<Item> TOPAZ_AXE = ITEMS.register("topaz_axe",
                    () -> new AxeItem(TMTiers.TOPAZ, 5.0F, -3.0F, props()));
            RegistryObject<Item> TOPAZ_BATTLE_AXE = ITEMS.register("topaz_battleaxe",
                    () -> new BattleAxeItem(TMTiers.TOPAZ_BATTLEAXE, 6.0F, -2.9F, props()));
            RegistryObject<Item> TOPAZ_EXCAVATOR = ITEMS.register("topaz_excavator",
                    () -> new ExcavatorItem(TMTiers.TOPAZ, 1, -3.0F, props()));
            RegistryObject<Item> TOPAZ_HAMMER = ITEMS.register("topaz_hammer",
                    () -> new HammerItem(1, -2.8F, TMTiers.TOPAZ, props()));
            RegistryObject<Item> TOPAZ_HOE = ITEMS.register("topaz_hoe",
                    () -> new HoeItem(TMTiers.TOPAZ, -3, 0.0F, props()));
            RegistryObject<Item> TOPAZ_LUMBER_AXE = ITEMS.register("topaz_lumber_axe",
                    () -> new LumberAxeItem(TMTiers.TOPAZ, 5.0F, -3.0F, props()));
            RegistryObject<Item> TOPAZ_PAXEL = ITEMS.register("topaz_paxel",
                    () -> new PaxelItem(1, -2.8F, TMTiers.TOPAZ_PAXEL, props()));
            RegistryObject<Item> TOPAZ_PICKAXE = ITEMS.register("topaz_pickaxe",
                    () -> new PickaxeItem(TMTiers.TOPAZ, 1, -2.8F, props()));
            RegistryObject<Item> TOPAZ_SHOVEL = ITEMS.register("topaz_shovel",
                    () -> new ShovelItem(TMTiers.TOPAZ, 1.5F, -3.0F, props()));
            RegistryObject<Item> TOPAZ_SWORD = ITEMS.register("topaz_sword",
                    () -> new SwordItem(TMTiers.TOPAZ_SWORD, 3, -2.4F, props()));
        }
        //Withered
        if (ConfigGeneral.disableWitheredArmor.get()) {
            WITHERED_HELMET = ITEMS.register("withered_helmet",
                    () -> new WitheredArmor(SlurpiesArmorMaterials.WITHERED, EquipmentSlot.HEAD, props().tab(RDItemGroup.tabSlurpiesDongles)));
            WITHERED_CHEST = ITEMS.register("withered_chestplate",
                    () -> new WitheredArmor(SlurpiesArmorMaterials.WITHERED, EquipmentSlot.CHEST, props().tab(RDItemGroup.tabSlurpiesDongles)));
            WITHERED_LEGS = ITEMS.register("withered_leggings",
                    () -> new WitheredArmor(SlurpiesArmorMaterials.WITHERED, EquipmentSlot.LEGS, props().tab(RDItemGroup.tabSlurpiesDongles)));
            WITHERED_BOOTS = ITEMS.register("withered_boots",
                    () -> new WitheredArmor(SlurpiesArmorMaterials.WITHERED, EquipmentSlot.FEET, props().tab(RDItemGroup.tabSlurpiesDongles)));
        }
        if (ConfigGeneral.disableWitheredTools.get()) {
            RegistryObject<Item> WITHERED_AXE = ITEMS.register("withered_axe",
                    () -> new AxeItem(TMTiers.WITHERED, 6.0F, -3.0F, props()));
            RegistryObject<Item> WITHERED_BATTLE_AXE = ITEMS.register("withered_battleaxe",
                    () -> new BattleAxeItem(TMTiers.WITHERED_BATTLEAXE, 7.0F, -2.9F, props()));
            RegistryObject<Item> WITHERED_EXCAVATOR = ITEMS.register("withered_excavator",
                    () -> new ExcavatorItem(TMTiers.WITHERED, 1, -3.0F, props()));
            RegistryObject<Item> WITHERED_HAMMER = ITEMS.register("withered_hammer",
                    () -> new HammerItem(1, -2.8F, TMTiers.WITHERED, props()));
            RegistryObject<Item> WITHERED_HOE = ITEMS.register("withered_hoe",
                    () -> new HoeItem(TMTiers.WITHERED, -4, 0.0F, props()));
            RegistryObject<Item> WITHERED_LUMBER_AXE = ITEMS.register("withered_lumber_axe",
                    () -> new LumberAxeItem(TMTiers.WITHERED, 6.0F, -3.0F, props()));
            RegistryObject<Item> WITHERED_PAXEL = ITEMS.register("withered_paxel",
                    () -> new PaxelItem(1, -2.8F, TMTiers.WITHERED_PAXEL, props()));
            RegistryObject<Item> WITHERED_PICKAXE = ITEMS.register("withered_pickaxe",
                    () -> new PickaxeItem(TMTiers.WITHERED, 1, -2.8F, props()));
            RegistryObject<Item> WITHERED_SHOVEL = ITEMS.register("withered_shovel",
                    () -> new ShovelItem(TMTiers.WITHERED, 1.5F, -3.0F, props()));
            RegistryObject<Item> WITHERED_SWORD = ITEMS.register("withered_sword",
                    () -> new SwordItem(TMTiers.WITHERED_SWORD, 4, -2.4F, props()));
        }
        //Vanilla Extras
        if (ConfigGeneral.disableDiamondToolsExtras.get()) {
            RegistryObject<Item> DIAMOND_BATTLE_AXE = ITEMS.register("diamond_battleaxe",
                    () -> new BattleAxeItem(Tiers.DIAMOND, 6.0F, -2.9F, props()));
            final RegistryObject<Item> DIAMOND_PAXEL = ITEMS.register("diamond_paxel",
                    () -> new PaxelItem(1, -2.8F, TMTiers.DIAMOND_PAXEL, props()));
            RegistryObject<Item> DIAMOND_LUMBER_AXE = ITEMS.register("diamond_lumber_axe",
                    () -> new LumberAxeItem(Tiers.DIAMOND, 5.0F, -3.0F, props()));
            RegistryObject<Item> DIAMOND_HAMMER = ITEMS.register("diamond_hammer",
                    () -> new HammerItem(1, -2.8F, Tiers.DIAMOND, props()));
            RegistryObject<Item> DIAMOND_EXCAVATOR = ITEMS.register("diamond_excavator",
                    () -> new ExcavatorItem(Tiers.DIAMOND, 1, -3.0F, props()));
        }
        if (ConfigGeneral.disableGoldToolsExtras.get()) {
            RegistryObject<Item> GOLD_BATTLE_AXE = ITEMS.register("gold_battleaxe",
                    () -> new BattleAxeItem(Tiers.GOLD, 7.0F, -2.9F, props()));
            RegistryObject<Item> GOLD_PAXEL = ITEMS.register("gold_paxel",
                    () -> new PaxelItem(1, -2.8F, TMTiers.GOLD_PAXEL, props()));
            RegistryObject<Item> GOLD_LUMBER_AXE = ITEMS.register("gold_lumber_axe",
                    () -> new LumberAxeItem(Tiers.GOLD, 6.0F, -3.0F, props()));
            RegistryObject<Item> GOLD_HAMMER = ITEMS.register("gold_hammer",
                    () -> new HammerItem(1, -2.8F, Tiers.GOLD, props()));
            RegistryObject<Item> GOLD_EXCAVATOR = ITEMS.register("gold_excavator",
                    () -> new ExcavatorItem(Tiers.GOLD, 1, -2.8F, props()));
        }
        if (ConfigGeneral.disableIronToolsExtras.get()) {
            RegistryObject<Item> IRON_BATTLE_AXE = ITEMS.register("iron_battleaxe",
                    () -> new BattleAxeItem(Tiers.IRON, 7.0F, -3.0F, props()));
            RegistryObject<Item> IRON_PAXEL = ITEMS.register("iron_paxel",
                    () -> new PaxelItem(1, -3.0F, TMTiers.IRON_PAXEL, props()));
            RegistryObject<Item> IRON_LUMBER_AXE = ITEMS.register("iron_lumber_axe",
                    () -> new LumberAxeItem(Tiers.IRON, 6.0F, -3.1F, props()));
            RegistryObject<Item> IRON_HAMMER = ITEMS.register("iron_hammer",
                    () -> new HammerItem(1, -2.8F, Tiers.IRON, props()));
            RegistryObject<Item> IRON_EXCAVATOR = ITEMS.register("iron_excavator",
                    () -> new ExcavatorItem(Tiers.IRON, 1, -3.0F, props()));
        }
        if (ConfigGeneral.disableNetheriteToolsExtras.get()) {
            RegistryObject<Item> NETHERITE_BATTLE_AXE = ITEMS.register("netherite_battleaxe",
                    () -> new BattleAxeItem(Tiers.NETHERITE, 6.0F, -2.9F, props()));
            RegistryObject<Item> NETHERITE_PAXEL = ITEMS.register("netherite_paxel",
                    () -> new PaxelItem(1, -2.8F, TMTiers.NETHERITE_PAXEL, props()));
            RegistryObject<Item> NETHERITE_LUMBAR_AXE = ITEMS.register("netherite_lumber_axe",
                    () -> new LumberAxeItem(Tiers.NETHERITE, 5.0F, -3.0F, props()));
            RegistryObject<Item> NETHERITE_HAMMER = ITEMS.register("netherite_hammer",
                    () -> new HammerItem(1, -2.8F, Tiers.NETHERITE, props()));
            RegistryObject<Item> NETHERITE_EXCAVATOR = ITEMS.register("netherite_excavator",
                    () -> new ExcavatorItem(Tiers.NETHERITE, 1, -3.0F, props()));
        }
        if (ConfigGeneral.disableStoneToolsExtras.get()) {
            RegistryObject<Item> STONE_BATTLE_AXE = ITEMS.register("stone_battleaxe",
                    () -> new BattleAxeItem(Tiers.STONE, 8.0F, -3.1F, props()));
            RegistryObject<Item> STONE_PAXEL = ITEMS.register("stone_paxel",
                    () -> new PaxelItem(1, -2.8F, TMTiers.STONE_PAXEL, props()));
            RegistryObject<Item> STONE_LUMBER_AXE = ITEMS.register("stone_lumber_axe",
                    () -> new LumberAxeItem(Tiers.STONE, 7.0F, -3.2F, props()));
            RegistryObject<Item> STONE_HAMMER = ITEMS.register("stone_hammer",
                    () -> new HammerItem(1, -2.8F, Tiers.STONE, props()));
            RegistryObject<Item> STONE_EXCAVATOR = ITEMS.register("stone_excavator",
                    () -> new ExcavatorItem(Tiers.STONE, 1, -3.0F, props()));
        }
        if (ConfigGeneral.disableWoodenToolsExtras.get()) {
            RegistryObject<Item> WOODEN_BATTLE_AXE = ITEMS.register("wooden_battleaxe",
                    () -> new BattleAxeItem(Tiers.WOOD, 7.0F, -3.1F, props()));
            RegistryObject<Item> WOODEN_PAXEL = ITEMS.register("wooden_paxel",
                    () -> new PaxelItem(1, -2.8F, TMTiers.WOODEN_PAXEL, props()));
            RegistryObject<Item> WOODEN_LUMBER_AXE = ITEMS.register("wooden_lumber_axe",
                    () -> new LumberAxeItem(Tiers.WOOD, 6.0F, -3.2F, props()));
            RegistryObject<Item> WOODEN_HAMMER = ITEMS.register("wooden_hammer",
                    () -> new HammerItem(1, -2.8F, Tiers.WOOD, props()));
            RegistryObject<Item> WOODEN_EXCAVATOR = ITEMS.register("wooden_excavator",
                    () -> new ExcavatorItem(Tiers.WOOD, 1, -3.0F, props()));
        }
        if (ConfigGeneral.disableVMTools.get()) {
            RegistryObject<Item> VM_EXCAVATOR = ITEMS.register("vm_excavator",
                    () -> new ExcavatorItem(TMTiers.VM, 1, -3.0F, props()));
            RegistryObject<Item> VM_HAMMER = ITEMS.register("vm_hammer",
                    () -> new HammerItem(1, -2.8F, TMTiers.VM, props()));
            RegistryObject<Item> VM_PICKAXE = ITEMS.register("vmpick",
                    () -> new PickaxeItem(TMTiers.VM, 1, -2.8F, props()));
        }
    }

    private static Item.Properties props() {
        return new Item.Properties().tab(RDItemGroup.tabSlurpiesDongles);
    }
}
