package com.rbs.slurpiesdongles.core.init;

import com.rbs.slurpiesdongles.SlurpiesDongles;
import com.rbs.slurpiesdongles.common.items.foods.DrinkItem;
import com.rbs.slurpiesdongles.common.items.foods.*;
import com.rbs.slurpiesdongles.core.config.ConfigGeneral;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemNameBlockItem;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

import static com.rbs.slurpiesdongles.core.util.ModTab.tabSlurpiesDongles;

public class ModFood {

    public static final DeferredRegister<Item> FOODS = DeferredRegister.create(ForgeRegistries.ITEMS, SlurpiesDongles.MOD_ID);

    public static RegistryObject<Item> APPLE_JUICE = null;
    public static RegistryObject<Item> APPLE_SLUSHIE = null;
    public static RegistryObject<Item> CARROT_JUICE = null;
    public static RegistryObject<Item> LEMONADE = null;
    public static RegistryObject<Item> MELON_JUICE = null;
    public static RegistryObject<Item> MELON_SLUSHIE = null;
    public static RegistryObject<Item> ORANGE_JUICE = null;
    public static RegistryObject<Item> ORANGE_LEMONADE = null;
    public static RegistryObject<Item> ORANGE_SLUSHIE = null;
    public static RegistryObject<Item> STRAWBERRY = null;
    public static RegistryObject<Item> STRAWBERRY_JUICE = null;
    public static RegistryObject<Item> STRAWBERRY_LEMONADE = null;
    public static RegistryObject<Item> STRAWBERRY_SLUSHIE = null;
    public static RegistryObject<Item> SUGARCOATED_APPLE = null;
    public static RegistryObject<Item> SUGARCOATED_LEMON = null;
    public static RegistryObject<Item> SUGARCOATED_MELON = null;
    public static RegistryObject<Item> SUGARCOATED_ORANGE = null;
    public static RegistryObject<Item> SUGARCOATED_STRAWBERRY = null;
    public static RegistryObject<Item> TOMATO_JUICE = null;
    public static RegistryObject<Item> RAW_BACON = null;
    //Food but crops
    public static final RegistryObject<Item> CABBAGE = FOODS.register("cabbage",
            () -> new ItemNameBlockItem(ModBlocks.CABBAGE_CROP.get(), props().food(FoodStats.CABBAGE)));
    public static final RegistryObject<Item> LEMON = FOODS.register("lemon",
            () -> new ItemNameBlockItem(ModBlocks.LEMON_CROP.get(), props().food(FoodStats.LEMON)));
    public static final RegistryObject<Item> ORANGE = FOODS.register("orange",
            () -> new ItemNameBlockItem(ModBlocks.ORANGE_CROP.get(), props().food(FoodStats.ORANGE)));
    public static final RegistryObject<Item> TOMATO = FOODS.register("tomato",
            () -> new ItemNameBlockItem(ModBlocks.TOMATO_CROP.get(), props().food(FoodStats.TOMATO)));
    //Seeds
    public static final RegistryObject<Item> CORN_SEED = FOODS.register("corn_seed",
            () -> new ItemNameBlockItem(ModBlocks.CORN_CROP.get(), props()));
    public static final RegistryObject<Item> STRAWBERRY_SEED = FOODS.register("strawberry_seed",
            () -> new ItemNameBlockItem(ModBlocks.STRAWBERRY_CROP.get(), props()));


    public static void register(IEventBus eventBus) {
        FOODS.register(eventBus);
        //Drinks
        if (ConfigGeneral.disableAppleJuice.get()) {
            APPLE_JUICE = FOODS.register("apple_juice",
                    () -> new DrinkItem(props().food(FoodStats.APPLE_JUICE)));
        }
        if (ConfigGeneral.disableAppleSlushie.get()) {
           APPLE_SLUSHIE = FOODS.register("apple_slushie",
                    () -> new DrinkItem(props().food(FoodStats.APPLE_SLUSHIE)));
        }
        if (ConfigGeneral.disableCarrotJuice.get()) {
            CARROT_JUICE = FOODS.register("carrot_juice",
                    () -> new DrinkItem(props().food(FoodStats.CARROT_JUICE)));
        }
        if (ConfigGeneral.disableLemonade.get()) {
            LEMONADE = FOODS.register("lemonade",
                    () -> new DrinkItem(props().food(FoodStats.LEMONADE)));
        }
        if (ConfigGeneral.disableMelonJuice.get()) {
           MELON_JUICE = FOODS.register("melon_juice",
                    () -> new DrinkItem(props().food(FoodStats.MELON_JUICE)));
        }
        if (ConfigGeneral.disableMelonSlushie.get()) {
            MELON_SLUSHIE = FOODS.register("melon_slushie",
                    () -> new DrinkItem(props().food(FoodStats.MELON_SLUSHIE)));
        }
        if (ConfigGeneral.disableOrangeJuice.get()) {
            ORANGE_JUICE = FOODS.register("orange_juice",
                    () -> new DrinkItem(props().food(FoodStats.ORANGE_JUICE)));
        }
        if (ConfigGeneral.disableOrangeLemonade.get()) {
           ORANGE_LEMONADE = FOODS.register("orange_lemonade",
                    () -> new DrinkItem(props().food(FoodStats.ORANGE_LEMONADE)));

        }
        if (ConfigGeneral.disableOrangeSlushie.get()) {
           ORANGE_SLUSHIE = FOODS.register("orange_slushie",
                    () -> new DrinkItem(props().food(FoodStats.ORANGE_SLUSHIE)));
        }
        if (ConfigGeneral.disableStrawberryJuice.get()) {
           STRAWBERRY_JUICE = FOODS.register("strawberry_juice",
                    () -> new DrinkItem(props().food(FoodStats.STRAWBERRY_JUICE)));
        }
        if (ConfigGeneral.disableStrawberryLemonade.get()) {
           STRAWBERRY_LEMONADE = FOODS.register("strawberry_lemonade",
                    () -> new DrinkItem(props().food(FoodStats.STRAWBERRY_LEMONADE)));
        }
        if (ConfigGeneral.disableStrawberrySlushie.get()) {
            STRAWBERRY_SLUSHIE = FOODS.register("strawberry_slushie",
                    () -> new DrinkItem(props().food(FoodStats.STRAWBERRY_SLUSHIE)));
        }
        if (ConfigGeneral.disableTomatoJuice.get()) {
           TOMATO_JUICE = FOODS.register("tomato_juice",
                    () -> new DrinkItem(props().food(FoodStats.TOMATO_JUICE)));
        }
        //Food
        if (ConfigGeneral.disableAppleSlice.get()) {
            RegistryObject<Item> APPLE_SLICE = FOODS.register("apple_slice",
                    () -> new TinyFood(props().food(FoodStats.APPLE_SLICE)));
        }
        if (ConfigGeneral.disableBacon.get()) {
            RegistryObject<Item> BACON = FOODS.register("bacon",
                    () -> new Item(props().food(FoodStats.BACON)));
        }
        if (ConfigGeneral.disableBaconEggSandwitch.get()) {
            RegistryObject<Item> BACON_EGG_SANDWICH = FOODS.register("bacon_egg_sandwitch",
                    () -> new Item(props().food(FoodStats.BACON_EGG_SANDWICH)));
        }
        if (ConfigGeneral.disableBaconPotatoBeefStew.get()) {
            RegistryObject<Item> BACON_POTATO_BEEF_STEW = FOODS.register("bacon_potato_beef_stew",
                    () -> new Item(props().food(FoodStats.BACON_POTATO_BEEF_STEW)));
        }
        if (ConfigGeneral.disableBaconPotatoChickenStew.get()) {
            RegistryObject<Item> BACON_POTATO_CHICKEN_STEW = FOODS.register("bacon_potato_chicken_stew",
                    () -> new Item(props().food(FoodStats.BACON_POTATO_CHICKEN_STEW)));
        }
        if (ConfigGeneral.disableBeefJerky.get()) {
            RegistryObject<Item> BEEF_JERKY = FOODS.register("beef_jerky",
                    () -> new Item(props().food(FoodStats.BEEF_JERKY)));
        }
        if (ConfigGeneral.disableBeefChickenSandwich.get()) {
            RegistryObject<Item> BEEF_CHICKEN_SANDWICH = FOODS.register("beef_chicken_sandwich",
                    () -> new BigSandwitch(props().food(FoodStats.BEEF_CHICKEN_SANDWICH)));
        }
        if (ConfigGeneral.disableBeefPorkSandwich.get()) {
            RegistryObject<Item> BEEF_PORK_SANDWICH = FOODS.register("beef_pork_sandwich",
                    () -> new BigSandwitch(props().food(FoodStats.BEEF_PORK_SANDWICH)));
        }
        if (ConfigGeneral.disableBeefSandwich.get()) {
            RegistryObject<Item> BEEF_SANDWICH = FOODS.register("beef_sandwich",
                    () -> new Item(props().food(FoodStats.BEEF_SANDWICH)));
        }
        if (ConfigGeneral.disableCookedCarrot.get()) {
            RegistryObject<Item> COOKED_CARROT = FOODS.register("cooked_carrot",
                    () -> new Item(props().food(FoodStats.COOKED_CARROT)));
        }
        if (ConfigGeneral.disableCarrotStick.get()) {
            RegistryObject<Item> CARROT_STICK = FOODS.register("carrot_stick",
                    () -> new Item(props().food(FoodStats.CARROT_STICK)));
        }
        if (ConfigGeneral.disableChickenNugget.get()) {
            RegistryObject<Item> CHICKEN_NUGGET = FOODS.register("chicken_nugget",
                    () -> new TinyFood(props().food(FoodStats.CHICKEN_NUGGET)));
        }
        if (ConfigGeneral.disableChickenSandwich.get()) {
            RegistryObject<Item> CHICKEN_SANDWICH = FOODS.register("chicken_sandwich",
                    () -> new Item(props().food(FoodStats.CHICKEN_SANDWICH)));
        }
        if (ConfigGeneral.disableChickenPorkSandwich.get()) {
        RegistryObject<Item> CHICKEN_PORK_SANDWICH = FOODS.register("chicken_pork_sandwich",
                () -> new BigSandwitch(props().food(FoodStats.CHICKEN_PORK_SANDWICH)));
        }
        if (ConfigGeneral.disableCookedCarrotStick.get()) {
    RegistryObject<Item> COOKED_CARROT_STICK = FOODS.register("cooked_carrot_stick",
            () -> new Item(props().food(FoodStats.COOKED_CARROT_STICK)));
        }
        if (ConfigGeneral.disableCookedRabbitLeg.get()) {
        RegistryObject<Item> COOKED_RABBIT_LEG = FOODS.register("cooked_rabbit_leg",
                () -> new Item(props().food(FoodStats.COOKED_RABBIT_LEG)));
        }
        if (ConfigGeneral.disableCorn.get()) {
        RegistryObject<Item> CORN = FOODS.register("corn",
                () -> new Item(props().food(FoodStats.CORN)));
        }
        if (ConfigGeneral.disableEggs.get()) {
        RegistryObject<Item> EGGS = FOODS.register("eggs",
                () -> new Item(props().food(FoodStats.EGGS)));
        }
        if (ConfigGeneral.disableGoldenBacon.get()) {
            RegistryObject<Item> GOLDEN_BACON = FOODS.register("golden_bacon",
                    () -> new Item(props().food(FoodStats.GOLDEN_BACON)));
        }
        if (ConfigGeneral.disableGoldenBakedPotato.get()) {
            RegistryObject<Item> GOLDEN_BAKED_POTATO = FOODS.register("golden_baked_potato",
                    () -> new Item(props().food(FoodStats.GOLDEN_BAKED_POTATO)));
        }
        if (ConfigGeneral.disableGoldenMelonSlice.get()) {
            RegistryObject<Item> GOLDEN_MELON_SLICE = FOODS.register("golden_melon_slice",
                    () -> new Item(props().food(FoodStats.GOLDEN_MELON_SLICE)));
        }
        if (ConfigGeneral.disableGoldenPotato.get()) {
            RegistryObject<Item> GOLDEN_POTATO = FOODS.register("golden_potato",
                    () -> new Item(props().food(FoodStats.GOLDEN_POTATO)));
        }
        if (ConfigGeneral.disableMelonSlice.get()) {
        RegistryObject<Item> MELON_SLICE = FOODS.register("melon_slice",
                () -> new TinyFood(props().food(FoodStats.MELON_SLICE)));
        }
        if (ConfigGeneral.disableMixedFruitBowl.get()) {
        RegistryObject<Item> MIXED_FRUIT_BOWL = FOODS.register("mixed_fruit_bowl",
                () -> new Item(props().food(FoodStats.MIXED_FRUIT_BOWL)));
        }
        if (ConfigGeneral.disableMixedSeeds.get()) {
        RegistryObject<Item> MIXED_SEEDS = FOODS.register("mixed_seeds",
                () -> new Item(props().food(FoodStats.MIXED_SEEDS)));
        }
        if (ConfigGeneral.disablePizza.get()) {
        RegistryObject<Item> PIZZA = FOODS.register("pizza",
                () -> new zaa(props().food(FoodStats.PIZZA)));
        }
        if (ConfigGeneral.disablePorkSandwich.get()) {
        RegistryObject<Item> PORK_SANDWICH = FOODS.register("pork_sandwich",
                () -> new Item(props().food(FoodStats.PORK_SANDWICH)));
        }
        if (ConfigGeneral.disablePotatoWedge.get()) {
        RegistryObject<Item> POTATO_WEDGE = FOODS.register("potato_wedge",
                () -> new TinyFood(props().food(FoodStats.POTATO_WEDGE)));
        }
        if (ConfigGeneral.disableRabbitLeg.get()) {
        RegistryObject<Item> RABBIT_LEG = FOODS.register("rabbit_leg",
                () -> new Item(props().food(FoodStats.RABBIT_LEG)));
        }
        if (ConfigGeneral.disableRawBeefSlice.get()) {
            RegistryObject<Item> RAW_BEEF_SLICE = FOODS.register("raw_beef_slice",
                    () -> new Item(props().food(FoodStats.RAW_BEEF_SLICE)));
        }
        if (ConfigGeneral.disableRawBacon.get()) {
            RAW_BACON = FOODS.register("raw_bacon",
                    () -> new RawBaconItem(props().food(FoodStats.RAW_BACON)));
        }
        if (ConfigGeneral.disableRawCorn.get()) {
            RegistryObject<Item> RAW_CORN = FOODS.register("raw_corn",
                    () -> new Item(props()));
        }

        if (ConfigGeneral.disableRawPotatoWedge.get()) {
            RegistryObject<Item> RAW_POTATO_WEDGE = FOODS.register("raw_potato_wedge",
                    () -> new TinyFood(props().food(FoodStats.RAW_POTATO_WEDGE)));
        }
        if (ConfigGeneral.disableRoastedApple.get()) {
            RegistryObject<Item> ROASTED_APPLE = FOODS.register("roasted_apple",
                    () -> new Item(props().food(FoodStats.ROASTED_APPLE)));
        }
        if (ConfigGeneral.disableRoastedBeetrootSeeds.get()) {
            RegistryObject<Item> ROASTED_BEETROOT_SEEDS = FOODS.register("roasted_beetroot_seeds",
                    () -> new Item(props().food(FoodStats.ROASTED_BEETROOT_SEEDS)));
        }
        if (ConfigGeneral.disableRoastedMelonSeeds.get()) {
            RegistryObject<Item> ROASTED_MELON_SEEDS = FOODS.register("roasted_melon_seeds",
                    () -> new Item(props().food(FoodStats.ROASTED_MELON_SEEDS)));
        }
            if (ConfigGeneral.disableRoastedMushroom.get()) {
                RegistryObject<Item> ROASTED_MUSHROOM = FOODS.register("roasted_mushroom",
                        () -> new Item(props().food(FoodStats.ROASTED_MUSHROOM)));
            }
        if (ConfigGeneral.disableRoastedPumpkinSeeds.get()) {
            RegistryObject<Item> ROASTED_PUMPKIN_SEEDS = FOODS.register("roasted_pumpkin_seeds",
                    () -> new Item(props().food(FoodStats.ROASTED_PUMPKIN_SEEDS)));
        }
        if (ConfigGeneral.disableRoastedRedMushroom.get()) {
            RegistryObject<Item> ROASTED_RED_MUSHROOM = FOODS.register("roasted_red_mushroom",
                    () -> new Item(props().food(FoodStats.ROASTED_RED_MUSHROOM)));
        }
        if (ConfigGeneral.disableRoastedSeeds.get()) {
            RegistryObject<Item> ROASTED_SEEDS = FOODS.register("roasted_seeds",
                    () -> new Item(props().food(FoodStats.ROASTED_SEEDS)));
        }
        if (ConfigGeneral.disableStrawberry.get()) {
            RegistryObject<Item> STRAWBERRY = FOODS.register("strawberry",
                    () -> new TinyFood(props().food(FoodStats.STRAWBERRY)));
        }
        if (ConfigGeneral.disableSugarCoatedApple.get()) {
            RegistryObject<Item> SUGARCOATED_APPLE = FOODS.register("sugarcoated_apple",
                    () -> new Item(props().food(FoodStats.SUGAR_COATED_APPLE)));
        }
        if (ConfigGeneral.disableSugarCoatedLemon.get()) {
            RegistryObject<Item> SUGARCOATED_LEMON = FOODS.register("sugarcoated_lemon",
                    () -> new Item(props().food(FoodStats.SUGAR_COATED_LEMEON)));
        }
        if (ConfigGeneral.disableSugarCoatedMelon.get()) {
            RegistryObject<Item> SUGARCOATED_MELON = FOODS.register("sugarcoated_melon",
                    () -> new Item(props().food(FoodStats.SUGAR_COATED_MELON)));
        }
        if (ConfigGeneral.disableSugarCoatedOrange.get()) {
            RegistryObject<Item> SUGARCOATED_ORANGE = FOODS.register("sugarcoated_orange",
                    () -> new Item(props().food(FoodStats.SUGAR_COATED_ORANGE)));
        }
        if (ConfigGeneral.disableSugarCoatedStrawberry.get()) {
            RegistryObject<Item> SUGARCOATED_STRAWBERRY = FOODS.register("sugarcoated_strawberry",
                    () -> new TinyFood(props().food(FoodStats.SUGAR_COATED_STRAWBERRY)));
        }
        if (ConfigGeneral.disableToast.get()) {
            RegistryObject<Item> TOAST = FOODS.register("toast",
                    () -> new Item(props().food(FoodStats.TOAST)));
        }
        if (ConfigGeneral.disableToastedBaconEggSandwich.get()) {
            RegistryObject<Item> TOASTED_BACON_EGG_SANDWICH = FOODS.register("toasted_bacon_egg_sandwitch",
                    () -> new Item(props().food(FoodStats.TOASTED_BACON_EGG_SANDWICH)));
        }
        //Regen Stuff
        if (ConfigGeneral.disableDiamondApple.get()) {
            RegistryObject<Item> DIAMOND_APPLE = FOODS.register("diamond_apple",
                    () -> new DiamondApple(props().food(FoodStats.DIAMOND_APPLE)));
        }
        if (ConfigGeneral.disableEnchantedDiamondApple.get()) {
            RegistryObject<Item> ENCHANTED_DIAMOND_APPLE = FOODS.register("enchanted_diamond_apple",
                    () -> new EnchantedDiamondApple(props().food(FoodStats.ENCHANTED_DIAMOND_APPLE)));

        }
        if (ConfigGeneral.disableEmeraldApple.get()) {
            RegistryObject<Item> EMERALD_APPLE = FOODS.register("emerald_apple",
                    () -> new EmeraldApple(props().food(FoodStats.EMERALD_APPLE)));
        }
        if (ConfigGeneral.disableEnchantedEmeraldApple.get()) {
            RegistryObject<Item> ENCHANTED_EMERALD_APPLE = FOODS.register("enchanted_emerald_apple",
                    () -> new EnchantedEmeraldApple(props().food(FoodStats.ENCHANTED_EMERALD_APPLE)));
        }
        if (ConfigGeneral.disableIronApple.get()) {
            RegistryObject<Item> IRON_APPLE = FOODS.register("iron_apple",
                    () -> new IronApple(props().food(FoodStats.IRON_APPLE)));
        }
        if (ConfigGeneral.disableEnchantedIronApple.get()) {
            RegistryObject<Item> ENCHANTED_IRON_APPLE = FOODS.register("enchanted_iron_apple",
                    () -> new EnchantedIronApple(props().food(FoodStats.ENCHANTED_IRON_APPLE)));
        }
        if (ConfigGeneral.disableHolyBread.get()) {
            RegistryObject<Item> HOLY_BREAD = FOODS.register("holy_bread",
                    () -> new HolyBreadItem(props().food(FoodStats.HOLY_BREAD)));
        }
    }
    public static final Item.Properties props() {
        return new Item.Properties().tab(tabSlurpiesDongles);
    }
}
