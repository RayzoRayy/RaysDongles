package com.ray.raysdongles.core.config;

import net.minecraftforge.common.ForgeConfigSpec;
import net.minecraftforge.fml.ModLoadingContext;
import net.minecraftforge.fml.config.ModConfig;
import org.apache.commons.lang3.tuple.Pair;

import static net.minecraftforge.common.ForgeConfigSpec.*;

public class ConfigGeneral {
    public static ConfigGeneral INSTANCE;
    public static Common COMMON;
    public static ForgeConfigSpec COMMON_SPEC;

    public static void init1() {
        Pair<Common, ForgeConfigSpec> commonPair = new Builder().configure(Common::new);

        COMMON_SPEC = commonPair.getRight();
        COMMON = commonPair.getLeft();
        ModLoadingContext.get().registerConfig(ModConfig.Type.COMMON, COMMON_SPEC, "Ray's Dongles-Common.toml");
    }

    public static class Common {

        public BooleanValue receiveUpdateMessages;

        private Common(Builder builder) {
            builder.push("General");
            receiveUpdateMessages = builder
                    .comment("Enable or disable the Update Checker [ true / false default: true")
                    .define("Receive update messages", true);
            builder.pop();
        }
    }
    //Actual Items
    public static BooleanValue disableBlender;
    public static BooleanValue disableKnife;
    public static BooleanValue disableNetherStarChunk;
    public static BooleanValue disablePopsSign;
    public static BooleanValue disableStoneRod;
    //Armor
    public static BooleanValue disableAmethystArmor;
    public static BooleanValue disableRubyArmor;
    public static BooleanValue disableTopazArmor;
    public static BooleanValue disableWitheredArmor;
    //Blocks
    public static BooleanValue disableBlenderBlock;
    public static BooleanValue disableReinforcedObsidian;
    //Blocks but Crops
    public static BooleanValue disableWildCrops;
    //Blocks but Ores
    public static BooleanValue disableDeepslateRubyOre;
    public static BooleanValue disableDeepslateTopazOre;
    public static BooleanValue disableRubyOre;
    public static BooleanValue disableTopazOre;

    //Charms
    public static BooleanValue disableAbsorptionCharm;
    public static BooleanValue disableAbsorptionCharmTier2;
    public static BooleanValue disableFireResistanceCharm;
    public static BooleanValue disableNightVisionCharm;
    public static BooleanValue disableRegenerationCharm;
    public static BooleanValue disableRegenerationCharmTier2;
    public static BooleanValue disableSpeedCharm;
    public static BooleanValue disableSpeedCharmTier2;
    public static BooleanValue disableStrengthCharm;
    public static BooleanValue disableStrengthCharmTier2;
    public static BooleanValue disableWaterbreathingCharm;
    //Drinks
    public static BooleanValue disableAppleJuice;
    public static BooleanValue disableAppleSlushie;
    public static BooleanValue disableCarrotJuice;
    public static BooleanValue disableLemonade;
    public static BooleanValue disableMelonJuice;
    public static BooleanValue disableMelonSlushie;
    public static BooleanValue disableOrangeJuice;
    public static BooleanValue disableOrangeLemonade;
    public static BooleanValue disableOrangeSlushie;
    public static BooleanValue disableStrawberryJuice;
    public static BooleanValue disableStrawberryLemonade;
    public static BooleanValue disableStrawberrySlushie;
    public static BooleanValue disableTomatoJuice;
    //Events
    public static IntValue rawBaconDropAmount;
    //Food
    public static BooleanValue disableAppleSlice;
    public static BooleanValue disableBacon;
    public static BooleanValue disableBaconEggSandwitch;
    public static BooleanValue disableBaconPotatoBeefStew;
    public static BooleanValue disableBaconPotatoChickenStew;
    public static BooleanValue disableBeefJerky;
    public static BooleanValue disableBeefChickenSandwich;
    public static BooleanValue disableBeefPorkSandwich;
    public static BooleanValue disableBeefSandwich;
    public static BooleanValue disableCookedCarrot;
    public static BooleanValue disableCarrotStick;
    public static BooleanValue disableChickenNugget;
    public static BooleanValue disableChickenSandwich;
    public static BooleanValue disableChickenPorkSandwich;
    public static BooleanValue disableCookedCarrotStick;
    public static BooleanValue disableCookedRabbitLeg;
    public static BooleanValue disableCorn;
    public static BooleanValue disableEggs;
    public static BooleanValue disableGoldenBacon;// 12 /1.0
    public static BooleanValue disableGoldenBakedPotato;// 10 /1.2
    public static BooleanValue disableGoldenMelonSlice;// 4 /0.6
    public static BooleanValue disableGoldenPotato;// 2 /0.6
    public static BooleanValue disableMelonSlice;
    public static BooleanValue disableMixedFruitBowl;
    public static BooleanValue disableMixedSeeds;
    public static BooleanValue disablePizza;
    public static BooleanValue disablePorkSandwich;
    public static BooleanValue disablePotatoWedge;
    public static BooleanValue disableRabbitLeg;
    public static BooleanValue disableRawBacon;
    public static BooleanValue disableRawBeefSlice;
    public static BooleanValue disableRawCorn;
    public static BooleanValue disableRawPotatoWedge;
    public static BooleanValue disableRoastedApple;
    public static BooleanValue disableRoastedBeetrootSeeds;
    public static BooleanValue disableRoastedMelonSeeds;
    public static BooleanValue disableRoastedMushroom;
    public static BooleanValue disableRoastedPumpkinSeeds;
    public static BooleanValue disableRoastedRedMushroom;
    public static BooleanValue disableRoastedSeeds;
    public static BooleanValue disableStrawberry;
    public static BooleanValue disableSugarCoatedApple;
    public static BooleanValue disableSugarCoatedLemon;
    public static BooleanValue disableSugarCoatedMelon;
    public static BooleanValue disableSugarCoatedOrange;
    public static BooleanValue disableSugarCoatedStrawberry;
    public static BooleanValue disableToast;
    public static BooleanValue disableToastedBaconEggSandwich;
    //Regen Stuffs
    public static BooleanValue disableDiamondApple;
    public static BooleanValue disableEnchantedDiamondApple;
    public static BooleanValue disableEmeraldApple;
    public static BooleanValue disableEnchantedEmeraldApple;
    public static BooleanValue disableIronApple;
    public static BooleanValue disableEnchantedIronApple;
    public static BooleanValue disableHolyBread;
    //Tools
    public static BooleanValue disableAmethystTools;
    public static BooleanValue disableRubyTools;
    public static BooleanValue disableTopazTools;
    public static BooleanValue disableVMTools;
    public static BooleanValue disableWitheredTools;
    //Tool Materials
    //Reg
    public static DoubleValue amethystAttackDamage;
    public static DoubleValue amethystEfficiency;
    public static IntValue amethystEnchantability;
    public static IntValue amethystHarvestLevel;
    public static IntValue amethystMaxUses;
    public static IntValue amethystPaxelMaxUses;
    public static DoubleValue rubyAttackDamage;
    public static DoubleValue rubyEfficiency;
    public static IntValue rubyEnchantability;
    public static IntValue rubyHarvestLevel;
    public static IntValue rubyMaxUses;
    public static IntValue rubyPaxelMaxUses;
    public static DoubleValue topazAttackDamage;
    public static DoubleValue topazEfficiency;
    public static IntValue topazEnchantability;
    public static IntValue topazHarvestLevel;
    public static IntValue topazMaxUses;
    public static IntValue topazPaxelMaxUses;
    public static DoubleValue vmAttackDamage;
    public static DoubleValue vmEfficiency;
    public static IntValue vmEnchantability;
    public static IntValue vmHarvestLevel;
    public static IntValue vmMaxUses;
    public static DoubleValue witheredAttackDamage;
    public static DoubleValue witheredEfficiency;
    public static IntValue witheredEnchantability;
    public static IntValue witheredHarvestLevel;
    public static IntValue witheredMaxUses;
    public static IntValue witheredPaxelMaxUses;

    //Paxels

    //Tools Vanilla Addons
    public static BooleanValue disableDiamondToolsExtras;
    public static BooleanValue disableEmeraldToolsExtras;
    public static BooleanValue disableGoldToolsExtras;
    public static BooleanValue disableIronToolsExtras;
    public static BooleanValue disableNetheriteToolsExtras;
    public static BooleanValue disableStoneToolsExtras;
    public static BooleanValue disableWoodenToolsExtras;
        public static void init(Builder builder) {
            //Actual Items
            builder.comment("Config section for Actual Item tweaks").push("Actual Items");
            disableBlender = builder
                    .comment("Enable or disable the Blender [ true / false default: true")
                    .define("Enable Blender", true);
            disableKnife = builder
                    .comment("Enable or disable the Knife [ true / false default: true")
                    .define("Enable Knife", true);
            disableNetherStarChunk = builder
                    .comment("Enable or disable Nether Star chunks [ true / false default: true")
                    .define("Enable Nether Star chunks", true);
            disablePopsSign = builder
                    .comment("Enable or disable Pops Sign [true / false default: false")
                    .define("Enable Pops Sign", false);
            disableStoneRod = builder
                    .comment("Enable or disable the Stone Rod [true / false default: true")
                    .define("Enable Stone Rod", true);
            builder.pop();
            //Armor
            builder.comment("Config section for Armor tweaks").push("Armor");

            disableAmethystArmor = builder
                    .comment("Setting this to false will disable Amethyst Armor [true / false default: true]")
                    .define("Enable Amethyst Armor", true);
            disableRubyArmor = builder
                    .comment("Setting this to false will disable Ruby Armor [true / false default: true]")
                    .define("Enable Ruby Armor", true);
            disableTopazArmor = builder
                    .comment("Setting this to false will disable Topaz Armor [true / false default: true]")
                    .define("Enable Topaz Armor", true);
            disableWitheredArmor = builder
                    .comment("Setting this to false will disable Withered Armor [true / false default: true]")
                    .define("Enable Withered Armor", true);
            builder.pop();
            //Blocks
            builder.comment("Config section for Block tweaks").push("Blocks");

            disableBlenderBlock = builder
                    .comment("Setting this to false will disable the placeable Blender [true / false default:true")
                    .define("Enable placeable Blender", true);
            disableReinforcedObsidian = builder
                    .comment("Setting this to false will disable the Reinforced Obsidian Block [true / false default:true")
                    .define("Enable Reinforced Obsidian Block", true);
            //Blocks but Crops
            disableWildCrops = builder
                    .comment("Setting this to false will disable the Wild Crops [true / false default:true")
                    .define("Enable Wild Crops", true);
            //Blocks but Ores
            disableDeepslateRubyOre = builder
                    .comment("Setting this to false will disable the Deepslate Ruby Ore [true / false default:true")
                    .define("Enable Deepslate Ruby Ore", true);
            disableDeepslateTopazOre = builder
                    .comment("Setting this to false will disable the Deepslate Topaz Ore [true / false default:true")
                    .define("Enable Deepslate Topaz Ore", true);
            disableRubyOre = builder
                    .comment("Setting this to false will disable the Ruby Ore [true / false default:true")
                    .define("Enable Ruby Ore", true);
            disableTopazOre = builder
                    .comment("Setting this to false will disable the Topaz Ore [true / false default:true")
                    .define("Enable Topaz Ore", true);
            builder.pop();
            //Charms
            builder.comment("Config section for Charm tweaks").push("Charms");

            disableAbsorptionCharm = builder
                    .comment("Setting this to false will disable the Absorption Charm [true / false default: true]")
                    .define("Enable Absorption Charm", true);
            disableAbsorptionCharmTier2 = builder
                    .comment("Setting this to false will disable the Absorption Charm Tier 2 [true / false default: true]")
                    .define("Enable Absorption Charm Tier 2", true);
            disableFireResistanceCharm = builder
                    .comment("Setting this to false will disable the Fire Resistance Charm [true / false default: true]")
                    .define("Enable Fire Resistance Charm", true);
            disableNightVisionCharm = builder
                    .comment("Setting this to false will disable the Night Vision Charm [true / false default: true]")
                    .define("Enable Night Vision Charm", true);
            disableRegenerationCharm = builder
                    .comment("Setting this to false will disable the Regeneration Charm [true / false default: true]")
                    .define("Enable Regeneration Charm", true);
            disableRegenerationCharmTier2 = builder
                    .comment("Setting this to false will disable the Regeneration Charm Tier 2 [true / false default: true]")
                    .define("Enable Regeneration Charm Tier 2", true);
            disableSpeedCharm = builder
                    .comment("Setting this to false will disable the Speed Charm [true / false default: true]")
                    .define("Enable Speed Charm", true);
            disableSpeedCharmTier2 = builder
                    .comment("Setting this to false will disable the Speed Charm Tier 2 [true / false default: true]")
                    .define("Enable Speed Charm Tier 2", true);
            disableStrengthCharm = builder
                    .comment("Setting this to false will disable the Strength Charm [true / false default: true]")
                    .define("Enable Strength Charm", true);
            disableStrengthCharmTier2 = builder
                    .comment("Setting this to false will disable the Strength Charm Tier 2 [true / false default: true]")
                    .define("Enable Strength Charm Tier 2", true);
            disableWaterbreathingCharm = builder
                    .comment("Setting this to false will disable the Waterbreathing Charm [true / false default: true]")
                    .define("Enable Waterbreathing Charm", true);
            builder.pop();
            //Drinks
            builder.comment("Config section for Drink tweaks").push("Drinks");
            disableAppleJuice = builder
                    .comment("Enable or disable the Apple Juice [true / false default: true")
                    .define("Enable Apple Juice", true);
            disableAppleSlushie = builder
                    .comment("Enable or disable the Apple Slushie [true / false default: true")
                    .define("Enable Apple Slushie", true);
            disableCarrotJuice = builder
                    .comment("Enable or disable the Carrot Juice [true / false default: true")
                    .define("Enable Carrot Juice", true);
            disableLemonade = builder
                    .comment("Enable or disable the Lemonade [true / false default: true")
                    .define("Enable Lemonade", true);
            disableMelonJuice = builder
                    .comment("Enable or disable the Melon Juice [true / false default: true")
                    .define("Enable Melon Juice", true);
            disableMelonSlushie = builder
                    .comment("Enable or disable the Melon Slushie [true / false default: true")
                    .define("Enable Melon Slushie", true);
            disableOrangeJuice = builder
                    .comment("Enable or disable the Orange Juice [true / false default: true")
                    .define("Enable Orange Juice", true);
            disableOrangeLemonade = builder
                    .comment("Enable or disable the Orange Lemonade [true / false default: true")
                    .define("Enable Orange Lemonade", true);
            disableOrangeSlushie = builder
                    .comment("Enable or disable the Orange Slushie [true / false default: true")
                    .define("Enable Orange Slushie", true);
            disableStrawberryJuice = builder
                    .comment("Enable or disable the Strawberry Juice [true / false default: true")
                    .define("Enable Strawberry Juice", true);
            disableStrawberryLemonade = builder
                    .comment("Enable or disable the Strawberry Lemonade [true / false default: true")
                    .define("Enable Strawberry Lemonade", true);
            disableStrawberrySlushie = builder
                    .comment("Enable or disable the Strawberry Slushie [true / false default: true")
                    .define("Enable Strawberry Slushie", true);
            disableTomatoJuice = builder
                    .comment("Enable or disable the Tomato Juice [true / false default: true")
                    .define("Enable Tomato Juice", true);
            builder.pop();
            //Food Stuffs
            builder.comment("Config section for Food Tweaks").push("Food");
            disableAppleSlice = builder
                    .comment("Enable or Disable Apple Slice [true / false default: true")
                    .define("Enable Apple Slice", true);
            disableBacon = builder
                    .comment("Enable or Disable Bacon [true / false default: true")
                    .define("Enable Bacon", true);
            disableBaconEggSandwitch = builder
                    .comment("Enable or Disable Bacon Egg Sandwich [true / false default: true")
                    .define("Enable Bacon Egg Sandwich", true);
            disableBaconPotatoBeefStew = builder
                    .comment("Enable or Disable Bacon Potato Beef Stew [true / false default: true")
                    .define("Enable Bacon Potato Beef Stew", true);
            disableBaconPotatoChickenStew = builder
                    .comment("Enable or Disable Bacon Potato Chicken Stew [true / false default: true")
                    .define("Enable Bacon Potato Chicken Stew", true);
            disableBeefJerky = builder
                    .comment("Enable or Disable Beef Jerky [true / false default: true")
                    .define("Enable Beef Jerky", true);
            disableBeefChickenSandwich = builder
                    .comment("Enable or Disable Beef Chicken Sandwich [true / false default: true")
                    .define("Enable Beef Chicken Sandwich", true);
            disableBeefPorkSandwich = builder
                    .comment("Enable or Disable Beef Pork Sandwich [true / false default: true")
                    .define("Enable Beef Pork Sandwich", true);
            disableBeefSandwich = builder
                    .comment("Enable or Disable Beef Sandwich [true / false default: true")
                    .define("Enable Beef Sandwich", true);
            disableCookedCarrot = builder
                    .comment("Enable or Disable Cooked Carrot [true / false default: true")
                    .define("Enable Cooked Carrot", true);
            disableCarrotStick = builder
                    .comment("Enable or Disable Carrot Stick [true / false default: true")
                    .define("Enable Carrot Stick", true);
            disableChickenNugget = builder
                    .comment("Enable or Disable Chicken Nugget [true / false default: true")
                    .define("Enable Chicken Nugget", true);
            disableChickenSandwich = builder
                    .comment("Enable or Disable Chicken Sandwich [true / false default: true")
                    .define("Enable Chicken Sandwich", true);
            disableChickenPorkSandwich = builder
                    .comment("Enable or Disable Chicken Pork Sandwich [true / false default: true")
                    .define("Enable Chicken Pork Sandwich", true);
            disableCookedCarrotStick = builder
                    .comment("Enable or Disable Cooked Carrot Stick [true / false default: true")
                    .define("Enable Cooked Carrot Stick", true);
            disableCookedRabbitLeg = builder
                    .comment("Enable or Disable Cooked Rabbit Leg [true / false default: true")
                    .define("Enable Cooked Rabbit Leg", true);
            disableCorn = builder
                    .comment("Enable or Disable Corn [true / false default: true")
                    .define("Enable Corn", true);
            disableEggs = builder
                    .comment("Enable or Disable Eggs [true / false default: true")
                    .define("Enable Eggs", true);
            disableGoldenBacon = builder
                    .comment("Enable or Disable Golden Bacon [true / false default: true")
                    .define("Enable Golden Bacon", true);
            disableGoldenBakedPotato = builder
                    .comment("Enable or Disable Golden Baked Potato [true / false default: true")
                    .define("Enable Golden Baked Potato", true);
            disableGoldenMelonSlice = builder
                    .comment("Enable or Disable Golden Melon Slice [true / false default: true")
                    .define("Enable Golden Melon Slice", true);
            disableGoldenPotato = builder
                    .comment("Enable or Disable Golden Potato [true / false default: true")
                    .define("Enable Golden Potato", true);
            disableMelonSlice = builder
                    .comment("Enable or Disable Melon Slice [true / false default: true")
                    .define("Enable Melon Slice", true);
            disableMixedFruitBowl = builder
                    .comment("Enable or Disable Mixed Fruit Bowl [true / false default: true")
                    .define("Enable Mixed Fruit Bowl", true);
            disableMixedSeeds = builder
                    .comment("Enable or Disable Mixed Seeds [true / false default: true")
                    .define("Enable Mixed Seeds", true);
            disablePizza = builder
                    .comment("Enable or Disable Pizza [true / false default: true")
                    .define("Enable Pizza", true);
            disablePorkSandwich = builder
                    .comment("Enable or Disable Pork Sandwich [true / false default: true")
                    .define("Enable Pork Sandwich", true);
            disablePotatoWedge = builder
                    .comment("Enable or Disable Potato Wedge [true / false default: true")
                    .define("Enable Potato Wedge", true);
            disableRabbitLeg = builder
                    .comment("Enable or Disable Rabbit Leg [true / false default: true")
                    .define("Enable Rabbit Leg", true);
            disableRawBacon = builder
                    .comment("Enable or Disable Raw Bacon [true / false default: true")
                    .define("Enable Raw Bacon", true);
            disableRawBeefSlice = builder
                    .comment("Enable or Disable Raw Beef Slice [true / false default: true")
                    .define("Enable Raw Beef Slice", true);
            disableRawCorn = builder
                    .comment("Enable or Disable Raw Corn [true / false default: true")
                    .define("Enable Raw Corn", true);
            disableRawPotatoWedge = builder
                    .comment("Enable or Disable Raw Potato Wedge [true / false default: true")
                    .define("Enable Raw Potato Wedge", true);
            disableRoastedApple = builder
                    .comment("Enable or Disable Roasted Apple [true / false default: true")
                    .define("Enable Roasted Apple", true);
            disableRoastedBeetrootSeeds = builder
                    .comment("Enable or Disable Roasted Beetroot Seeds [true / false default: true")
                    .define("Enable Roasted Beetroot Seeds", true);
            disableRoastedMelonSeeds = builder
                    .comment("Enable or Disable Roasted Melon Seeds [true / false default: true")
                    .define("Enable Roasted Melon Seeds", true);
            disableRoastedMushroom = builder
                    .comment("Enable or Disable Roasted Mushroom [true / false default: true")
                    .define("Enable Roasted Mushroom", true);
            disableRoastedPumpkinSeeds = builder
                    .comment("Enable or Disable Roasted Pumpkin Seeds [true / false default: true")
                    .define("Enable Roasted Pumpkin Seeds", true);
            disableRoastedRedMushroom = builder
                    .comment("Enable or Disable Roasted Red Mushroom [true / false default: true")
                    .define("Enable Roasted Red Mushroom", true);
            disableRoastedSeeds = builder
                    .comment("Enable or Disable Roasted Seeds [true / false default: true")
                    .define("Enable Roasted Seeds", true);
            disableStrawberry = builder
                    .comment("Enable or Disable Strawberry [true / false default: true")
                    .define("Enable Strawberry", true);
            disableSugarCoatedApple = builder
                    .comment("Enable or Disable Sugar Coated Apple [true / false default: true")
                    .define("Enable Sugar Coated Apple", true);
            disableSugarCoatedLemon = builder
                    .comment("Enable or Disable Sugar Coated Lemon [true / false default: true")
                    .define("Enable Sugar Coated Lemon", true);
            disableSugarCoatedMelon = builder
                    .comment("Enable or Disable Sugar Coated Melon [true / false default: true")
                    .define("Enable Sugar Coated Melon", true);
            disableSugarCoatedOrange = builder
                    .comment("Enable or Disable Sugar Coated Orange [true / false default: true")
                    .define("Enable Sugar Coated Orange", true);
            disableSugarCoatedStrawberry = builder
                    .comment("Enable or Disable Sugar Coated Strawberry [true / false default: true")
                    .define("Enable Sugar Coated Strawberry", true);
            disableToast = builder
                    .comment("Enable or Disable Toast [true / false default: true")
                    .define("Enable Toast", true);
            disableToastedBaconEggSandwich = builder
                    .comment("Enable or Disable Toasted Bacon Egg Sandwich [true / false default: true")
                    .define("Enable Toasted Bacon Egg Sandwich", true);
            builder.pop();
            //Regen Stuffs
            builder.comment("Config section for Defensive Food Tweaks").push("Defensive Food");
            disableDiamondApple = builder
                    .comment("Enable or disable the Diamond Apple [true / false default: true")
                    .define("Enable Diamond Apple", true);
            disableEnchantedDiamondApple = builder
                    .comment("Enable or disable the Enchanted Diamond Apple [true / false default: true")
                    .define("Enable Enchanted Diamond Apple", true);
            disableEmeraldApple = builder
                    .comment("Enable or disable the Emerald Apple [true / false default: true")
                    .define("Enable Emerald Apple", true);
            disableEnchantedEmeraldApple = builder
                    .comment("Enable or disable the Enchanted Emerald Apple [true / false default: true")
                    .define("Enable Enchanted Emerald Apple", true);
            disableIronApple = builder
                    .comment("Enable or disable the Iron Apple [true / false default: true")
                    .define("Enable Iron Apple", true);
            disableEnchantedIronApple = builder
                    .comment("Enable or disable the Enchanted Iron Apple [true / false default: true")
                    .define("Enable Enchanted Iron Apple", true);
            disableHolyBread = builder
                    .comment("Enable or disable Holy Bread [true / false default: true")
                    .define("Enable Holy Bread", true);
            builder.pop();
            //Mob drops
            builder.comment("Config section for mob drops tweaks").push("Mob Drops");
            rawBaconDropAmount = builder
                    .comment("Amount of Raw Bacon that Pigs drop : Default is 1 Raw Bacon drop, max is 5")
                    .comment("Set the value to 0 to disable Raw Bacon drops")
                    .defineInRange("Raw Bacon drop amount", 1, 0, 5);
            builder.pop();
            //Tools
            builder.comment("Config section for Amethyst tool tweaks").push("Amethyst");
            disableAmethystTools = builder
                    .comment("Setting this to false will disable the entire Amethyst tool set [true / false default: true")
                    .define("Enable Amethyst tools", true);
            amethystAttackDamage = builder
                    .comment("Defines the BASE attack damage for Amethyst tools / modifying this will change damage for ALL Amethyst tools")
                    .defineInRange("AttackDamage", 3.0,  0,40.0);
            amethystEfficiency = builder
                    .comment("Defines the efficiency for Amethyst tools / DOES NOT AFFECT Axes, Battleaxes, Lumberaxes, OR Swords")
                    .defineInRange("Efficiency", 8.0, 0, 40.0);
            amethystEnchantability = builder
                    .comment("Defines the enchantability for Amethyst tools [Default is 13)")
                    .defineInRange("Enchantability", 13, 0, Integer.MAX_VALUE);
            amethystHarvestLevel = builder
                    .comment("Defines the harvest level for Amethyst tools [Default is 3, min is 0, max is 10")
                    .defineInRange("HarvestLevel", 3, 0, 10);
            amethystMaxUses = builder
                    .comment("Defines the max uses for Amethyst tools [Default is 1739 uses")
                    .defineInRange("MaxUses", 1739, 0, Integer.MAX_VALUE);
            amethystPaxelMaxUses = builder
                    .comment("Defines the max uses for Amethyst paxel [Default is 5217 uses, Axe + Pickaxe + Shovel durability = 5217")
                    .defineInRange("MaxUses", 5217, 0, Integer.MAX_VALUE);
            builder.pop();
            builder.comment("Config section for Ruby tool tweaks").push("Ruby");
            disableRubyTools = builder
                    .comment("Setting this to false will disable the entire Ruby tool set [true / false default: true")
                    .define("Enable Ruby tools", true);
            rubyAttackDamage = builder
                    .comment("Defines the BASE attack damage for Ruby tools / modifying this will change damage for ALL Ruby tools")
                    .defineInRange("AttackDamage", 2.0,  0,40.0);
            rubyEfficiency = builder
                    .comment("Defines the efficiency for Ruby tools / DOES NOT AFFECT Axes, Battleaxes, Lumberaxes, OR Swords")
                    .defineInRange("Efficiency", 6.0, 0, 40.0);
            rubyEnchantability = builder
                    .comment("Defines the enchantability for Ruby tools [Default is 15)")
                    .defineInRange("Enchantability", 15, 0, Integer.MAX_VALUE);
            rubyHarvestLevel = builder
                    .comment("Defines the harvest level for Ruby tools [Default is 2, min is 0, max is 10")
                    .defineInRange("HarvestLevel", 2, 0, 10);
            rubyMaxUses = builder
                    .comment("Defines the max uses for Ruby tools [Default is 721 uses")
                    .defineInRange("MaxUses", 721, 0, Integer.MAX_VALUE);
            rubyPaxelMaxUses = builder
                    .comment("Defines the max uses for Ruby paxel [Default is 2163 uses, Axe + Pickaxe + Shovel durability = 2163")
                    .defineInRange("MaxUses", 2163, 0, Integer.MAX_VALUE);
            builder.pop();
            builder.comment("Config section for Topaz tool tweaks").push("Topaz");
            disableTopazTools = builder
                    .comment("Setting this to false will disable the entire Topaz tool set [true / false default: true")
                    .define("Enable Topaz tools", true);
            topazAttackDamage = builder
                    .comment("Defines the BASE attack damage for Topaz tools / modifying this will change damage for ALL Topaz tools")
                    .defineInRange("AttackDamage", 3.0,  0,40.0);
            topazEfficiency = builder
                    .comment("Defines the efficiency for Topaz tools / DOES NOT AFFECT Axes, Battleaxes, Lumberaxes, OR Swords")
                    .defineInRange("Efficiency", 8.0, 0, 40.0);
            topazEnchantability = builder
                    .comment("Defines the enchantability for Topaz tools [Default is 12)")
                    .defineInRange("Enchantability", 12, 0, Integer.MAX_VALUE);
            topazHarvestLevel = builder
                    .comment("Defines the harvest level for Topaz tools [Default is 3, min is 0, max is 10")
                    .defineInRange("HarvestLevel", 3, 0, 10);
            topazMaxUses = builder
                    .comment("Defines the max uses for Topaz tools [Default is 1893 uses")
                    .defineInRange("MaxUses", 1893, 0, Integer.MAX_VALUE);
            topazPaxelMaxUses = builder
                    .comment("Defines the max uses for Topaz paxel [Default is 5679 uses, Axe + Pickaxe + Shovel durability = 5679")
                    .defineInRange("MaxUses", 5679, 0, Integer.MAX_VALUE);
            builder.pop();
            builder.comment("Config section for Mass Mining tool tweaks").push("Mass Mining");
            disableVMTools = builder
                    .comment("Setting this to false will disable the entire Mass Mining tool set [true / false default: true")
                    .define("Enable VM tools", true);
            vmAttackDamage = builder
                    .comment("Defines the BASE attack damage for Mass Mining tools / modifying this will change damage for ALL Mass Mining tools")
                    .defineInRange("AttackDamage", 1.0,  0,40.0);
            vmEfficiency = builder
                    .comment("Defines the efficiency for Mass Mining tools / DOES NOT AFFECT Axes, Battleaxes, Lumberaxes, OR Swords")
                    .defineInRange("Efficiency", 4.0, 0, 40.0);
            vmEnchantability = builder
                    .comment("Defines the enchantability for Mass Mining tools [Default is 5)")
                    .defineInRange("Enchantability", 5, 0, Integer.MAX_VALUE);
            vmHarvestLevel = builder
                    .comment("Defines the harvest level for Mass Mining tools [Default is 1, min is 0, max is 10")
                    .defineInRange("HarvestLevel", 1, 0, 10);
            vmMaxUses = builder
                    .comment("Defines the max uses for Mass Mining tools [Default is 18200 uses")
                    .defineInRange("MaxUses", 18200, 0, Integer.MAX_VALUE);
            builder.pop();
            builder.comment("Config section for Withered tool tweaks").push("Withered");
            disableWitheredTools = builder
                    .comment("Setting this to false will disable the entire Withered tool set [true / false default: true")
                    .define("Enable Withered tools", true);
            witheredAttackDamage = builder
                    .comment("Defines the BASE attack damage for Withered tools / modifying this will change damage for ALL Withered tools")
                    .defineInRange("AttackDamage", 4.0,  0,40.0);
            witheredEfficiency = builder
                    .comment("Defines the efficiency for Withered tools / DOES NOT AFFECT Axes, Battleaxes, Lumberaxes, OR Swords")
                    .defineInRange("Efficiency", 11.0, 0, 40.0);
            witheredEnchantability = builder
                    .comment("Defines the enchantability for Withered tools [Default is 20)")
                    .defineInRange("Enchantability", 20, 0, Integer.MAX_VALUE);
            witheredHarvestLevel = builder
                    .comment("Defines the harvest level for Withered tools [Default is 4, min is 0, max is 10")
                    .defineInRange("HarvestLevel", 4, 0, 10);
            witheredMaxUses = builder
                    .comment("Defines the max uses for Withered tools [Default is 0 uses / This is what makes it un-breakable")
                    .defineInRange("MaxUses", 0, 0, Integer.MAX_VALUE);
            witheredPaxelMaxUses = builder
                    .comment("Defines the max uses for Withered paxel [Default is 0 uses, / This is what makes it un-breakable")
                    .defineInRange("MaxUses", 0, 0, Integer.MAX_VALUE);
            builder.pop();
            //Vanilla Tool Addons
            builder.comment("Config section for extra Vanilla tool set tweaks").push("Extra Vanilla Tools");
            disableDiamondToolsExtras = builder
                    .comment("Setting this to false will disable the extra Diamond tools [true / false default: true")
                    .define("Enable extra Diamond tools", true);
            disableEmeraldToolsExtras = builder
                    .comment("Setting this to false will disable the extra Emerald tools [true / false default: true")
                    .define("Enable extra Emerald tools", true);
            disableGoldToolsExtras = builder
                    .comment("Setting this to false will disable the extra Gold tools [true / false default: true")
                    .define("Enable extra Gold tools", true);
            disableIronToolsExtras = builder
                    .comment("Setting this to false will disable the extra Iron tools [true / false default: true")
                    .define("Enable extra Iron tools", true);
            disableNetheriteToolsExtras = builder
                    .comment("Setting this to false will disable the extra Netherite tools [true / false default: true")
                    .define("Enable extra Netherite tools", true);
            disableStoneToolsExtras = builder
                    .comment("Setting this to false will disable the extra Stone tools [true / false default: true")
                    .define("Enable extra Stone tools", true);
            disableWoodenToolsExtras = builder
                    .comment("Setting this to false will disable the extra Wooden tools [true / false default: true")
                    .define("Enable extra Wooden tools", true);
            builder.pop();
        }
    }

