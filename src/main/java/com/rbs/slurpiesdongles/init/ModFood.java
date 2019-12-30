package com.rbs.slurpiesdongles.init;

import com.rbs.slurpiesdongles.Reference;
import com.rbs.slurpiesdongles.config.ConfigGeneral;
import com.rbs.slurpiesdongles.food.*;
//import com.rbs.slurpiesdongles.food.seeds.CornSeeds;
//import com.rbs.slurpiesdongles.food.seeds.StrawberrySeeds;
import com.rbs.slurpiesdongles.food.seeds.CornSeeds;
import com.rbs.slurpiesdongles.food.seeds.StrawberrySeeds;
import com.rbs.slurpiesdongles.items.ItemBase;
import net.minecraft.item.BlockNamedItem;
import net.minecraft.item.Item;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.registries.ObjectHolder;

import java.sql.Ref;
import java.util.ArrayList;
import java.util.List;

@Mod.EventBusSubscriber(modid = Reference.MODID, bus = Mod.EventBusSubscriber.Bus.MOD) //This line tells forge this class has events we want to listen to, we also tell forge we want to listen to the Mod bus. (This is new in 1.13)
@ObjectHolder(Reference.MODID) //We use ObjectHolder to let forge inject the item into our variables, this to make sure when people replace our item we use the correct one.

public class ModFood {
    //Drinks
    public static final Item apple_juice = null;
    public static final Item apple_slushie = null;
    public static final Item carrot_juice = null;
    public static final Item lemonade = null;
    public static final Item melon_juice = null;
    public static final Item melon_slushie = null;
    public static final Item orange_juice = null;
    public static final Item orange_slushie = null;
    public static final Item strawberry_juice = null;
    public static final Item strawberry_slushie = null;
    public static final Item tomato_juice = null;
    //Food
    public static final Item apple_slice = null;
    public static final Item bacon = null;
    public static final Item bacon_egg_sandwitch = null;
    public static final Item bacon_potato_beef_stew = null;
    public static final Item bacon_potato_chicken_stew = null;
    public static final Item beef_jerky = null;
    public static final Item beef_chicken_sandwich = null;
    public static final Item beef_pork_sandwich = null;
    public static final Item beef_sandwich = null;
    public static final Item carrot_stick = null;
    public static final Item chicken_nugget = null;
    public static final Item chicken_sandwich = null;
    public static final Item chicken_pork_sandwich = null;
    public static final Item cooked_carrot = null;
    public static final Item cooked_carrot_stick = null;
    public static final Item cooked_rabbit_leg = null;
    public static final Item corn = null;
    public static final Item eggs = null;
    public static final Item melon_slice = null;
    public static final Item mixed_fruit_bowl = null;
    public static final Item mixed_seeds = null;
    public static final Item pizza = null;
    public static final Item pork_sandwich = null;
    public static final Item potato_wedge = null;
    public static final Item rabbit_leg = null;
    public static final Item raw_bacon = null;
    public static final Item raw_beef_slice = null;
    public static final Item raw_corn = null;
    public static final Item roasted_apple = null;
    public static final Item roasted_beetroot_seeds = null;
    public static final Item roasted_melon_seeds = null;
    public static final Item roasted_mushroom = null;
    public static final Item roasted_pumpkin_seeds = null;
    public static final Item roasted_red_mushroom = null;
    public static final Item roasted_seeds = null;
    public static final Item strawberry = null;
    public static final Item sugarcoated_apple = null;
    public static final Item sugarcoated_lemon = null;
    public static final Item sugarcoated_melon = null;
    public static final Item sugarcoated_orange = null;
    public static final Item sugarcoated_strawberry = null;
    public static final Item toast = null;
    public static final Item toasted_bacon_egg_sandwitch = null;
    //Food but Crops
    public static final Item cabbage = null;
    public static final Item lemon = null;
    public static final Item orange = null;
    public static final Item tomato = null;
    //Regen Stuff
    public static Item diamond_apple = null;
    public static Item enchanted_diamond_apple = null;
    public static Item emerald_apple = null;
    public static Item enchanted_emerald_apple = null;
    public static Item iron_apple = null;
    public static Item enchanted_iron_apple = null;
    public static Item holy_bread = null;
    //Seeds
    public static final Item corn_seed = null;
    public static final Item strawberry_seed = null;

    @SubscribeEvent
    public static void registerItems(RegistryEvent.Register<Item> event) {
        event.getRegistry().registerAll(
                //Drinks
                new FoodBaseDrink(new Item.Properties().group(Reference.tabSlurpiesDongles).food(FoodStats.APPLE_JUICE), "apple_juice"),
                new FoodBaseDrink(new Item.Properties().group(Reference.tabSlurpiesDongles).food(FoodStats.APPLE_SLUSHIE), "apple_slushie"),
                new FoodBaseDrink(new Item.Properties().group(Reference.tabSlurpiesDongles).food(FoodStats.CARROT_JUICE), "carrot_juice"),
                new FoodBaseDrink(new Item.Properties().group(Reference.tabSlurpiesDongles).food(FoodStats.LEMONADE), "lemonade"),
                new FoodBaseDrink(new Item.Properties().group(Reference.tabSlurpiesDongles).food(FoodStats.MELON_JUICE), "melon_juice"),
                new FoodBaseDrink(new Item.Properties().group(Reference.tabSlurpiesDongles).food(FoodStats.MELON_SLUSHIE), "melon_slushie"),
                new FoodBaseDrink(new Item.Properties().group(Reference.tabSlurpiesDongles).food(FoodStats.ORANGE_JUICE), "orange_juice"),
                new FoodBaseDrink(new Item.Properties().group(Reference.tabSlurpiesDongles).food(FoodStats.ORANGE_SLUSHIE), "orange_slushie"),
                new FoodBaseDrink(new Item.Properties().group(Reference.tabSlurpiesDongles).food(FoodStats.STRAWBERRY_JUICE), "strawberry_juice"),
                new FoodBaseDrink(new Item.Properties().group(Reference.tabSlurpiesDongles).food(FoodStats.STRAWBERRY_SLUSHIE), "strawberry_slushie"),
                new FoodBaseDrink(new Item.Properties().group(Reference.tabSlurpiesDongles).food(FoodStats.TOMATO_JUICE), "tomato_juice"),
                //Food
                new FoodBaseTiny(new Item.Properties().group(Reference.tabSlurpiesDongles).food(FoodStats.APPLE_SLICE), "apple_slice"),
                new FoodBase(new Item.Properties().group(Reference.tabSlurpiesDongles).food(FoodStats.BACON), "bacon"),
                new FoodBase(new Item.Properties().group(Reference.tabSlurpiesDongles).food(FoodStats.BACON_EGG_SANDWICH), "bacon_egg_sandwitch"),
                new FoodBase(new Item.Properties().group(Reference.tabSlurpiesDongles).food(FoodStats.BACON_POTATO_BEEF_STEW), "bacon_potato_beef_stew"),
                new FoodBase(new Item.Properties().group(Reference.tabSlurpiesDongles).food(FoodStats.BACON_POTATO_CHICKEN_STEW), "bacon_potato_chicken_stew"),
                new FoodBase(new Item.Properties().group(Reference.tabSlurpiesDongles).food(FoodStats.BEEF_JERKY), "beef_jerky"),
                new BigSandwich(new Item.Properties().group(Reference.tabSlurpiesDongles).food(FoodStats.BEEF_CHICKEN_SANDWICH), "beef_chicken_sandwich"),
                new BigSandwich(new Item.Properties().group(Reference.tabSlurpiesDongles).food(FoodStats.BEEF_PORK_SANDWICH), "beef_pork_sandwich"),
                new FoodBase(new Item.Properties().group(Reference.tabSlurpiesDongles).food(FoodStats.BEEF_SANDWICH), "beef_sandwich"),
                new FoodBase(new Item.Properties().group(Reference.tabSlurpiesDongles).food(FoodStats.CARROT_STICK), "carrot_stick"),
                new FoodBase(new Item.Properties().group(Reference.tabSlurpiesDongles).food(FoodStats.CHICKEN_NUGGET), "chicken_nugget"),
                new FoodBase(new Item.Properties().group(Reference.tabSlurpiesDongles).food(FoodStats.CHICKEN_SANDWICH), "chicken_sandwich"),
                new BigSandwich(new Item.Properties().group(Reference.tabSlurpiesDongles).food(FoodStats.CHICKEN_PORK_SANDWICH), "chicken_pork_sandwich"),
                new FoodBase(new Item.Properties().group(Reference.tabSlurpiesDongles).food(FoodStats.COOKED_CARROT), "cooked_carrot"),
                new FoodBase(new Item.Properties().group(Reference.tabSlurpiesDongles).food(FoodStats.COOKED_CARROT_STICK), "cooked_carrot_stick"),
                new FoodBase(new Item.Properties().group(Reference.tabSlurpiesDongles).food(FoodStats.COOKED_RABBIT_LEG), "cooked_rabbit_leg"),
                new FoodBase(new Item.Properties().group(Reference.tabSlurpiesDongles).food(FoodStats.CORN), "corn"),
                new FoodBase(new Item.Properties().group(Reference.tabSlurpiesDongles).food(FoodStats.EGGS), "eggs"),
                new FoodBaseTiny(new Item.Properties().group(Reference.tabSlurpiesDongles).food(FoodStats.MELON_SLICE), "melon_slice"),
                new FoodBase(new Item.Properties().group(Reference.tabSlurpiesDongles).food(FoodStats.MIXED_FRUIT_BOWL), "mixed_fruit_bowl"),
                new FoodBase(new Item.Properties().group(Reference.tabSlurpiesDongles).food(FoodStats.MIXED_SEEDS), "mixed_seeds"),
                new FoodBase(new Item.Properties().group(Reference.tabSlurpiesDongles).food(FoodStats.PIZZA), "pizza"),
                new FoodBase(new Item.Properties().group(Reference.tabSlurpiesDongles).food(FoodStats.PORK_SANDWICH), "pork_sandwich"),
                new FoodBase(new Item.Properties().group(Reference.tabSlurpiesDongles).food(FoodStats.POTATO_WEDGE), "potato_wedge"),
                new FoodBase(new Item.Properties().group(Reference.tabSlurpiesDongles).food(FoodStats.RABBIT_LEG), "rabbit_leg"),
                new FoodBase(new Item.Properties().group(Reference.tabSlurpiesDongles).food(FoodStats.RAW_BACON), "raw_bacon"),
                new FoodBase(new Item.Properties().group(Reference.tabSlurpiesDongles).food(FoodStats.RAW_BEEF_SLICE), "raw_beef_slice"),
                new ItemBase(new Item.Properties().group(Reference.tabSlurpiesDongles), "raw_corn"),
                new FoodBase(new Item.Properties().group(Reference.tabSlurpiesDongles).food(FoodStats.ROASTED_APPLE), "roasted_apple"),
                new FoodBase(new Item.Properties().group(Reference.tabSlurpiesDongles).food(FoodStats.ROASTED_BEETROOT_SEEDS), "roasted_beetroot_seeds"),
                new FoodBase(new Item.Properties().group(Reference.tabSlurpiesDongles).food(FoodStats.ROASTED_MELON_SEEDS), "roasted_melon_seeds"),
                new FoodBase(new Item.Properties().group(Reference.tabSlurpiesDongles).food(FoodStats.ROASTED_MUSHROOM), "roasted_mushroom"),
                new FoodBase(new Item.Properties().group(Reference.tabSlurpiesDongles).food(FoodStats.ROASTED_PUMPKIN_SEEDS), "roasted_pumpkin_seeds"),
                new FoodBase(new Item.Properties().group(Reference.tabSlurpiesDongles).food(FoodStats.ROASTED_RED_MUSHROOM), "roasted_red_mushroom"),
                new FoodBase(new Item.Properties().group(Reference.tabSlurpiesDongles).food(FoodStats.ROASTED_SEEDS), "roasted_seeds"),
                new FoodBaseTiny(new Item.Properties().group(Reference.tabSlurpiesDongles).food(FoodStats.STRAWBERRY), "strawberry"),
                new FoodBase(new Item.Properties().group(Reference.tabSlurpiesDongles).food(FoodStats.SUGAR_COATED_APPLE), "sugarcoated_apple"),
                new FoodBase(new Item.Properties().group(Reference.tabSlurpiesDongles).food(FoodStats.SUGAR_COATED_LEMEON), "sugarcoated_lemon"),
                new FoodBase(new Item.Properties().group(Reference.tabSlurpiesDongles).food(FoodStats.SUGAR_COATED_MELON), "sugarcoated_melon"),
                new FoodBase(new Item.Properties().group(Reference.tabSlurpiesDongles).food(FoodStats.SUGAR_COATED_ORANGE), "sugarcoated_orange"),
                new FoodBase(new Item.Properties().group(Reference.tabSlurpiesDongles).food(FoodStats.SUGAR_COATED_STRAWBERRY), "sugarcoated_strawberry"),
                new FoodBase(new Item.Properties().group(Reference.tabSlurpiesDongles).food(FoodStats.TOAST), "toast"),
                new FoodBase(new Item.Properties().group(Reference.tabSlurpiesDongles).food(FoodStats.TOASTED_BACON_EGG_SANDWICH), "toasted_bacon_egg_sandwitch"),
                //Food but Crops
                new FoodBaseSeed(new Item.Properties().group(Reference.tabSlurpiesDongles).food(FoodStats.CABBAGE), ModBlocks.cabbage_crop, "cabbage"),
                new FoodBaseSeed(new Item.Properties().group(Reference.tabSlurpiesDongles).food(FoodStats.LEMON), ModBlocks.lemon_crop, "lemon"),
                new FoodBaseSeed(new Item.Properties().group(Reference.tabSlurpiesDongles).food(FoodStats.ORANGE), ModBlocks.orange_crop, "orange"),
                new FoodBaseSeed(new Item.Properties().group(Reference.tabSlurpiesDongles).food(FoodStats.TOMATO), ModBlocks.tomato_crop, "tomato"),

                //Seeds
                new CornSeeds(new Item.Properties().group(Reference.tabSlurpiesDongles), ModBlocks.corn_crop, "corn_seed"),
                new StrawberrySeeds(new Item.Properties().group(Reference.tabSlurpiesDongles), ModBlocks.strawberry_crop, "strawberry_seed")
        );
        event.getRegistry().registerAll();
        {
            //Defensive Stuff
            if (ConfigGeneral.disableDiamondApple.get()) {
                event.getRegistry().register(diamond_apple = new AppleDiamond(new Item.Properties().group(Reference.tabSlurpiesDongles).food(FoodStats.DIAMOND_APPLE), "diamond_apple"));
            }
            if (ConfigGeneral.disableEnchantedDiamondApple.get()) {
                event.getRegistry().register(enchanted_diamond_apple = new AppleDiamondEnchanted(new Item.Properties().group(Reference.tabSlurpiesDongles).food(FoodStats.ENCHANTED_DIAMOND_APPLE), "enchanted_diamond_apple"));
            }
            if (ConfigGeneral.disableEmeraldApple.get()) {
                event.getRegistry().register(emerald_apple = new AppleEmerald(new Item.Properties().group(Reference.tabSlurpiesDongles).food(FoodStats.EMERALD_APPLE), "emerald_apple"));
            }
            if (ConfigGeneral.disableEnchantedEmeraldApple.get()) {
                event.getRegistry().register(enchanted_emerald_apple = new AppleEmeraldEnchanted(new Item.Properties().group(Reference.tabSlurpiesDongles).food(FoodStats.ENCHANTED_EMERALD_APPLE), "enchanted_emerald_apple"));
            }
            if (ConfigGeneral.disableIronApple.get()) {
                event.getRegistry().register(iron_apple = new AppleIron(new Item.Properties().group(Reference.tabSlurpiesDongles).food(FoodStats.IRON_APPLE), "iron_apple"));
            }
            if (ConfigGeneral.disableEnchantedIronApple.get()) {
                event.getRegistry().register(enchanted_iron_apple = new AppleIronEnchanted(new Item.Properties().group(Reference.tabSlurpiesDongles).food(FoodStats.ENCHANTED_IRON_APPLE), "enchanted_iron_apple"));
            }
            if (ConfigGeneral.disableHolyBread.get()) {
                event.getRegistry().register(holy_bread = new HolyBread(new Item.Properties().group(Reference.tabSlurpiesDongles).food(FoodStats.HOLY_BREAD), "holy_bread"));
            }
        }
    }
}
