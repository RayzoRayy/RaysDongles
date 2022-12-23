package com.rbs.slurpiesdongles.core.init;

import com.rbs.slurpiesdongles.SlurpiesDongles;
import com.rbs.slurpiesdongles.common.items.foods.DrinkItem;
import com.rbs.slurpiesdongles.common.items.foods.*;
import com.rbs.slurpiesdongles.core.config.ConfigGeneral;
import com.rbs.slurpiesdongles.core.itemgroup.RDItemGroup;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemNameBlockItem;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

import static com.rbs.slurpiesdongles.core.config.ConfigGeneral.*;
import static com.rbs.slurpiesdongles.core.config.ConfigGeneral.disableRawBacon;

public class ModFood {

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

    public static final DeferredRegister<Item> FOODS = DeferredRegister.create(ForgeRegistries.ITEMS, SlurpiesDongles.MOD_ID);
    //Food but crops
    public static final RegistryObject<Item> CABBAGE = FOODS.register("cabbage",
            () -> new ItemNameBlockItem(ModBlocks.CABBAGE_CROP.get(), new Item.Properties().tab(RDItemGroup.tabSlurpiesDongles).food(FoodStats.CABBAGE)));
    public static final RegistryObject<Item> LEMON = FOODS.register("lemon",
            () -> new ItemNameBlockItem(ModBlocks.LEMON_CROP.get(), new Item.Properties().tab(RDItemGroup.tabSlurpiesDongles).food(FoodStats.LEMON)));
    public static final RegistryObject<Item> ORANGE = FOODS.register("orange",
            () -> new ItemNameBlockItem(ModBlocks.ORANGE_CROP.get(), new Item.Properties().tab(RDItemGroup.tabSlurpiesDongles).food(FoodStats.ORANGE)));
    public static final RegistryObject<Item> TOMATO = FOODS.register("tomato",
            () -> new ItemNameBlockItem(ModBlocks.TOMATO_CROP.get(), new Item.Properties().tab(RDItemGroup.tabSlurpiesDongles).food(FoodStats.TOMATO)));
    //Seeds
    public static final RegistryObject<Item> CORN_SEED = FOODS.register("corn_seed",
            () -> new ItemNameBlockItem(ModBlocks.CORN_CROP.get(), new Item.Properties().tab(RDItemGroup.tabSlurpiesDongles)));
    public static final RegistryObject<Item> STRAWBERRY_SEED = FOODS.register("strawberry_seed",
            () -> new ItemNameBlockItem(ModBlocks.STRAWBERRY_CROP.get(), new Item.Properties().tab(RDItemGroup.tabSlurpiesDongles)));


    public static void register(IEventBus eventBus) {
        FOODS.register(eventBus);
        //Drinks
        if (disableAppleJuice.get()) {
            APPLE_JUICE = FOODS.register("apple_juice",
                    () -> new DrinkItem(props().food(FoodStats.APPLE_JUICE)));
        }
        if (disableAppleSlushie.get()) {
            APPLE_SLUSHIE = FOODS.register("apple_slushie",
                    () -> new DrinkItem(props().food(FoodStats.APPLE_SLUSHIE)));
        }
        if (disableCarrotJuice.get()) {
            CARROT_JUICE = FOODS.register("carrot_juice",
                    () -> new DrinkItem(props().food(FoodStats.CARROT_JUICE)));
        }
        if (disableLemonade.get()) {
            LEMONADE = FOODS.register("lemonade",
                    () -> new DrinkItem(props().food(FoodStats.LEMONADE)));
        }
        if (disableMelonJuice.get()) {
            MELON_JUICE = FOODS.register("melon_juice",
                    () -> new DrinkItem(props().food(FoodStats.MELON_JUICE)));
        }
        if (disableMelonSlushie.get()) {
            MELON_SLUSHIE = FOODS.register("melon_slushie",
                    () -> new DrinkItem(props().food(FoodStats.MELON_SLUSHIE)));
        }
        if (disableOrangeJuice.get()) {
            ORANGE_JUICE = FOODS.register("orange_juice",
                    () -> new DrinkItem(props().food(FoodStats.ORANGE_JUICE)));
        }
        if (disableOrangeLemonade.get()) {
            ORANGE_LEMONADE = FOODS.register("orange_lemonade",
                    () -> new DrinkItem(props().food(FoodStats.ORANGE_LEMONADE)));

        }
        if (disableOrangeSlushie.get()) {
            ORANGE_SLUSHIE = FOODS.register("orange_slushie",
                    () -> new DrinkItem(props().food(FoodStats.ORANGE_SLUSHIE)));
        }
        if (disableStrawberryJuice.get()) {
            STRAWBERRY_JUICE = FOODS.register("strawberry_juice",
                    () -> new DrinkItem(props().food(FoodStats.STRAWBERRY_JUICE)));
        }
        if (disableStrawberryLemonade.get()) {
            STRAWBERRY_LEMONADE = FOODS.register("strawberry_lemonade",
                    () -> new DrinkItem(props().food(FoodStats.STRAWBERRY_LEMONADE)));
        }
        if (disableStrawberrySlushie.get()) {
            STRAWBERRY_SLUSHIE = FOODS.register("strawberry_slushie",
                    () -> new DrinkItem(props().food(FoodStats.STRAWBERRY_SLUSHIE)));
        }
        if (disableTomatoJuice.get()) {
            TOMATO_JUICE = FOODS.register("tomato_juice",
                    () -> new DrinkItem(props().food(FoodStats.TOMATO_JUICE)));
        }
        //Food
        if (disableAppleSlice.get()) {
            RegistryObject<Item> APPLE_SLICE = FOODS.register("apple_slice",
                    () -> new TinyFood(new Item.Properties().tab(RDItemGroup.tabSlurpiesDongles).food(FoodStats.APPLE_SLICE)));
        }
        if (disableBacon.get()) {
            RegistryObject<Item> BACON = FOODS.register("bacon",
                    () -> new Item(new Item.Properties().tab(RDItemGroup.tabSlurpiesDongles).food(FoodStats.BACON)));
        }
        if (disableBaconEggSandwitch.get()) {
            RegistryObject<Item> BACON_EGG_SANDWICH = FOODS.register("bacon_egg_sandwitch",
                    () -> new Item(new Item.Properties().tab(RDItemGroup.tabSlurpiesDongles).food(FoodStats.BACON_EGG_SANDWICH)));
        }
        if (disableBaconPotatoBeefStew.get()) {
            RegistryObject<Item> BACON_POTATO_BEEF_STEW = FOODS.register("bacon_potato_beef_stew",
                    () -> new Item(new Item.Properties().tab(RDItemGroup.tabSlurpiesDongles).food(FoodStats.BACON_POTATO_BEEF_STEW)));
        }
        if (disableBaconPotatoChickenStew.get()) {
            RegistryObject<Item> BACON_POTATO_CHICKEN_STEW = FOODS.register("bacon_potato_chicken_stew",
                    () -> new Item(new Item.Properties().tab(RDItemGroup.tabSlurpiesDongles).food(FoodStats.BACON_POTATO_CHICKEN_STEW)));
        }
        if (disableBeefJerky.get()) {
            RegistryObject<Item> BEEF_JERKY = FOODS.register("beef_jerky",
                    () -> new Item(new Item.Properties().tab(RDItemGroup.tabSlurpiesDongles).food(FoodStats.BEEF_JERKY)));
        }
        if (disableBeefChickenSandwich.get()) {
            RegistryObject<Item> BEEF_CHICKEN_SANDWICH = FOODS.register("beef_chicken_sandwich",
                    () -> new BigSandwitch(new Item.Properties().tab(RDItemGroup.tabSlurpiesDongles).food(FoodStats.BEEF_CHICKEN_SANDWICH)));
        }
        if (disableBeefPorkSandwich.get()) {
            RegistryObject<Item> BEEF_PORK_SANDWICH = FOODS.register("beef_pork_sandwich",
                    () -> new BigSandwitch(new Item.Properties().tab(RDItemGroup.tabSlurpiesDongles).food(FoodStats.BEEF_PORK_SANDWICH)));
        }
        if (disableBeefSandwich.get()) {
            RegistryObject<Item> BEEF_SANDWICH = FOODS.register("beef_sandwich",
                    () -> new Item(new Item.Properties().tab(RDItemGroup.tabSlurpiesDongles).food(FoodStats.BEEF_SANDWICH)));
        }
        if (disableCookedCarrot.get()) {
            RegistryObject<Item> COOKED_CARROT = FOODS.register("cooked_carrot",
                    () -> new Item(new Item.Properties().tab(RDItemGroup.tabSlurpiesDongles).food(FoodStats.COOKED_CARROT)));
        }
        if (disableCarrotStick.get()) {
            RegistryObject<Item> CARROT_STICK = FOODS.register("carrot_stick",
                    () -> new Item(new Item.Properties().tab(RDItemGroup.tabSlurpiesDongles).food(FoodStats.CARROT_STICK)));
        }
        if (disableChickenNugget.get()) {
            RegistryObject<Item> CHICKEN_NUGGET = FOODS.register("chicken_nugget",
                    () -> new TinyFood(new Item.Properties().tab(RDItemGroup.tabSlurpiesDongles).food(FoodStats.CHICKEN_NUGGET)));
        }
        if (disableChickenSandwich.get()) {
            RegistryObject<Item> CHICKEN_SANDWICH = FOODS.register("chicken_sandwich",
                    () -> new Item(new Item.Properties().tab(RDItemGroup.tabSlurpiesDongles).food(FoodStats.CHICKEN_SANDWICH)));
        }
        if (disableChickenPorkSandwich.get()) {
        RegistryObject<Item> CHICKEN_PORK_SANDWICH = FOODS.register("chicken_pork_sandwich",
                () -> new BigSandwitch(new Item.Properties().tab(RDItemGroup.tabSlurpiesDongles).food(FoodStats.CHICKEN_PORK_SANDWICH)));
        }
        if (disableCookedCarrotStick.get()) {
    RegistryObject<Item> COOKED_CARROT_STICK = FOODS.register("cooked_carrot_stick",
            () -> new Item(new Item.Properties().tab(RDItemGroup.tabSlurpiesDongles).food(FoodStats.COOKED_CARROT_STICK)));
        }
        if (disableCookedRabbitLeg.get()) {
        RegistryObject<Item> COOKED_RABBIT_LEG = FOODS.register("cooked_rabbit_leg",
                () -> new Item(new Item.Properties().tab(RDItemGroup.tabSlurpiesDongles).food(FoodStats.COOKED_RABBIT_LEG)));
        }
        if (disableCorn.get()) {
        RegistryObject<Item> CORN = FOODS.register("corn",
                () -> new Item(new Item.Properties().tab(RDItemGroup.tabSlurpiesDongles).food(FoodStats.CORN)));
        }
        if (disableEggs.get()) {
        RegistryObject<Item> EGGS = FOODS.register("eggs",
                () -> new Item(new Item.Properties().tab(RDItemGroup.tabSlurpiesDongles).food(FoodStats.EGGS)));
        }
        if (disableGoldenBacon.get()) {
            RegistryObject<Item> GOLDEN_BACON = FOODS.register("golden_bacon",
                    () -> new Item(new Item.Properties().tab(RDItemGroup.tabSlurpiesDongles).food(FoodStats.GOLDEN_BACON)));
        }
        if (disableGoldenBakedPotato.get()) {
            RegistryObject<Item> GOLDEN_BAKED_POTATO = FOODS.register("golden_baked_potato",
                    () -> new Item(new Item.Properties().tab(RDItemGroup.tabSlurpiesDongles).food(FoodStats.GOLDEN_BAKED_POTATO)));
        }
        if (disableGoldenMelonSlice.get()) {
            RegistryObject<Item> GOLDEN_MELON_SLICE = FOODS.register("golden_melon_slice",
                    () -> new Item(new Item.Properties().tab(RDItemGroup.tabSlurpiesDongles).food(FoodStats.GOLDEN_MELON_SLICE)));
        }
        if (disableGoldenPotato.get()) {
            RegistryObject<Item> GOLDEN_POTATO = FOODS.register("golden_potato",
                    () -> new Item(new Item.Properties().tab(RDItemGroup.tabSlurpiesDongles).food(FoodStats.GOLDEN_POTATO)));
        }
        if (disableMelonSlice.get()) {
        RegistryObject<Item> MELON_SLICE = FOODS.register("melon_slice",
                () -> new TinyFood(new Item.Properties().tab(RDItemGroup.tabSlurpiesDongles).food(FoodStats.MELON_SLICE)));
        }
        if (disableMixedFruitBowl.get()) {
        RegistryObject<Item> MIXED_FRUIT_BOWL = FOODS.register("mixed_fruit_bowl",
                () -> new Item(new Item.Properties().tab(RDItemGroup.tabSlurpiesDongles).food(FoodStats.MIXED_FRUIT_BOWL)));
        }
        if (disableMixedSeeds.get()) {
        RegistryObject<Item> MIXED_SEEDS = FOODS.register("mixed_seeds",
                () -> new Item(new Item.Properties().tab(RDItemGroup.tabSlurpiesDongles).food(FoodStats.MIXED_SEEDS)));
        }
        if (disablePizza.get()) {
        RegistryObject<Item> PIZZA = FOODS.register("pizza",
                () -> new zaa(new Item.Properties().tab(RDItemGroup.tabSlurpiesDongles).food(FoodStats.PIZZA)));
        }
        if (disablePorkSandwich.get()) {
        RegistryObject<Item> PORK_SANDWICH = FOODS.register("pork_sandwich",
                () -> new Item(new Item.Properties().tab(RDItemGroup.tabSlurpiesDongles).food(FoodStats.PORK_SANDWICH)));
        }
        if (disablePotatoWedge.get()) {
        RegistryObject<Item> POTATO_WEDGE = FOODS.register("potato_wedge",
                () -> new TinyFood(new Item.Properties().tab(RDItemGroup.tabSlurpiesDongles).food(FoodStats.POTATO_WEDGE)));
        }
        if (disableRabbitLeg.get()) {
        RegistryObject<Item> RABBIT_LEG = FOODS.register("rabbit_leg",
                () -> new Item(new Item.Properties().tab(RDItemGroup.tabSlurpiesDongles).food(FoodStats.RABBIT_LEG)));
        }
        if (disableRawBacon.get()) {
            RAW_BACON = FOODS.register("raw_bacon",
                    () -> new RawBaconItem(props().food(FoodStats.RAW_BACON)));
        }
        if (disableRawBeefSlice.get()) {
            RegistryObject<Item> RAW_BEEF_SLICE = FOODS.register("raw_beef_slice",
                    () -> new Item(new Item.Properties().tab(RDItemGroup.tabSlurpiesDongles).food(FoodStats.RAW_BEEF_SLICE)));
        }
        if (disableRawCorn.get()) {
            RegistryObject<Item> RAW_CORN = FOODS.register("raw_corn",
                    () -> new Item(new Item.Properties().tab(RDItemGroup.tabSlurpiesDongles)));
        }
        if (disableRawPotatoWedge.get()) {
            RegistryObject<Item> RAW_POTATO_WEDGE = FOODS.register("raw_potato_wedge",
                    () -> new TinyFood(new Item.Properties().tab(RDItemGroup.tabSlurpiesDongles).food(FoodStats.RAW_POTATO_WEDGE)));
        }
        if (disableRoastedApple.get()) {
            RegistryObject<Item> ROASTED_APPLE = FOODS.register("roasted_apple",
                    () -> new Item(new Item.Properties().tab(RDItemGroup.tabSlurpiesDongles).food(FoodStats.ROASTED_APPLE)));
        }
        if (disableRoastedBeetrootSeeds.get()) {
            RegistryObject<Item> ROASTED_BEETROOT_SEEDS = FOODS.register("roasted_beetroot_seeds",
                    () -> new Item(new Item.Properties().tab(RDItemGroup.tabSlurpiesDongles).food(FoodStats.ROASTED_BEETROOT_SEEDS)));
        }
        if (disableRoastedMelonSeeds.get()) {
            RegistryObject<Item> ROASTED_MELON_SEEDS = FOODS.register("roasted_melon_seeds",
                    () -> new Item(new Item.Properties().tab(RDItemGroup.tabSlurpiesDongles).food(FoodStats.ROASTED_MELON_SEEDS)));
        }
            if (disableRoastedMushroom.get()) {
                RegistryObject<Item> ROASTED_MUSHROOM = FOODS.register("roasted_mushroom",
                        () -> new Item(new Item.Properties().tab(RDItemGroup.tabSlurpiesDongles).food(FoodStats.ROASTED_MUSHROOM)));
            }
        if (disableRoastedPumpkinSeeds.get()) {
            RegistryObject<Item> ROASTED_PUMPKIN_SEEDS = FOODS.register("roasted_pumpkin_seeds",
                    () -> new Item(new Item.Properties().tab(RDItemGroup.tabSlurpiesDongles).food(FoodStats.ROASTED_PUMPKIN_SEEDS)));
        }
        if (disableRoastedRedMushroom.get()) {
            RegistryObject<Item> ROASTED_RED_MUSHROOM = FOODS.register("roasted_red_mushroom",
                    () -> new Item(new Item.Properties().tab(RDItemGroup.tabSlurpiesDongles).food(FoodStats.ROASTED_RED_MUSHROOM)));
        }
        if (disableRoastedSeeds.get()) {
            RegistryObject<Item> ROASTED_SEEDS = FOODS.register("roasted_seeds",
                    () -> new Item(new Item.Properties().tab(RDItemGroup.tabSlurpiesDongles).food(FoodStats.ROASTED_SEEDS)));
        }
        if (disableStrawberry.get()) {
            RegistryObject<Item> STRAWBERRY = FOODS.register("strawberry",
                    () -> new TinyFood(new Item.Properties().tab(RDItemGroup.tabSlurpiesDongles).food(FoodStats.STRAWBERRY)));
        }
        if (disableSugarCoatedApple.get()) {
            RegistryObject<Item> SUGARCOATED_APPLE = FOODS.register("sugarcoated_apple",
                    () -> new Item(new Item.Properties().tab(RDItemGroup.tabSlurpiesDongles).food(FoodStats.SUGAR_COATED_APPLE)));
        }
        if (disableSugarCoatedLemon.get()) {
            RegistryObject<Item> SUGARCOATED_LEMON = FOODS.register("sugarcoated_lemon",
                    () -> new Item(new Item.Properties().tab(RDItemGroup.tabSlurpiesDongles).food(FoodStats.SUGAR_COATED_LEMEON)));
        }
        if (disableSugarCoatedMelon.get()) {
            RegistryObject<Item> SUGARCOATED_MELON = FOODS.register("sugarcoated_melon",
                    () -> new Item(new Item.Properties().tab(RDItemGroup.tabSlurpiesDongles).food(FoodStats.SUGAR_COATED_MELON)));
        }
        if (disableSugarCoatedOrange.get()) {
            RegistryObject<Item> SUGARCOATED_ORANGE = FOODS.register("sugarcoated_orange",
                    () -> new Item(new Item.Properties().tab(RDItemGroup.tabSlurpiesDongles).food(FoodStats.SUGAR_COATED_ORANGE)));
        }
        if (disableSugarCoatedStrawberry.get()) {
            RegistryObject<Item> SUGARCOATED_STRAWBERRY = FOODS.register("sugarcoated_strawberry",
                    () -> new TinyFood(new Item.Properties().tab(RDItemGroup.tabSlurpiesDongles).food(FoodStats.SUGAR_COATED_STRAWBERRY)));
        }
        if (disableToast.get()) {
            RegistryObject<Item> TOAST = FOODS.register("toast",
                    () -> new Item(new Item.Properties().tab(RDItemGroup.tabSlurpiesDongles).food(FoodStats.TOAST)));
        }
        if (disableToastedBaconEggSandwich.get()) {
            RegistryObject<Item> TOASTED_BACON_EGG_SANDWICH = FOODS.register("toasted_bacon_egg_sandwitch",
                    () -> new Item(new Item.Properties().tab(RDItemGroup.tabSlurpiesDongles).food(FoodStats.TOASTED_BACON_EGG_SANDWICH)));
        }
        //Regen Stuff
        if (disableDiamondApple.get()) {
            RegistryObject<Item> DIAMOND_APPLE = FOODS.register("diamond_apple",
                    () -> new DiamondApple(new Item.Properties().tab(RDItemGroup.tabSlurpiesDongles).food(FoodStats.DIAMOND_APPLE)));
        }
        if (disableEnchantedDiamondApple.get()) {
            RegistryObject<Item> ENCHANTED_DIAMOND_APPLE = FOODS.register("enchanted_diamond_apple",
                    () -> new EnchantedDiamondApple(new Item.Properties().tab(RDItemGroup.tabSlurpiesDongles).food(FoodStats.ENCHANTED_DIAMOND_APPLE)));

        }
        if (disableEmeraldApple.get()) {
            RegistryObject<Item> EMERALD_APPLE = FOODS.register("emerald_apple",
                    () -> new EmeraldApple(new Item.Properties().tab(RDItemGroup.tabSlurpiesDongles).food(FoodStats.EMERALD_APPLE)));
        }
        if (disableEnchantedEmeraldApple.get()) {
            RegistryObject<Item> ENCHANTED_EMERALD_APPLE = FOODS.register("enchanted_emerald_apple",
                    () -> new EnchantedEmeraldApple(new Item.Properties().tab(RDItemGroup.tabSlurpiesDongles).food(FoodStats.ENCHANTED_EMERALD_APPLE)));
        }
        if (disableIronApple.get()) {
            RegistryObject<Item> IRON_APPLE = FOODS.register("iron_apple",
                    () -> new IronApple(new Item.Properties().tab(RDItemGroup.tabSlurpiesDongles).food(FoodStats.IRON_APPLE)));
        }
        if (disableEnchantedIronApple.get()) {
            RegistryObject<Item> ENCHANTED_IRON_APPLE = FOODS.register("enchanted_iron_apple",
                    () -> new EnchantedIronApple(new Item.Properties().tab(RDItemGroup.tabSlurpiesDongles).food(FoodStats.ENCHANTED_IRON_APPLE)));
        }
        if (disableHolyBread.get()) {
            RegistryObject<Item> HOLY_BREAD = FOODS.register("holy_bread",
                    () -> new HolyBreadItem(new Item.Properties().tab(RDItemGroup.tabSlurpiesDongles).food(FoodStats.HOLY_BREAD)));
        }
    }
    private static Item.Properties props () {
        return new Item.Properties().tab(RDItemGroup.tabSlurpiesDongles);
    }
}
