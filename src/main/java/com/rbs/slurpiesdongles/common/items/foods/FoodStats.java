package com.rbs.slurpiesdongles.common.items.foods;

import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.food.FoodProperties;

public class FoodStats {

    //Defensive Stuff
    public static final FoodProperties DIAMOND_APPLE = (new FoodProperties.Builder().nutrition(6).saturationMod(1.3F).effect(new MobEffectInstance(MobEffects.DAMAGE_RESISTANCE, 1200, 0), 1.0F).effect(new MobEffectInstance(MobEffects.MOVEMENT_SPEED, 1200, 0), 1.0F).effect(new MobEffectInstance(MobEffects.FIRE_RESISTANCE, 1200, 0), 1.0F).alwaysEat().build());
    public static final FoodProperties ENCHANTED_DIAMOND_APPLE = (new FoodProperties.Builder().nutrition(8).saturationMod(1.5F).effect(new MobEffectInstance(MobEffects.DAMAGE_RESISTANCE, 2400, 1), 1.0F).effect(new MobEffectInstance(MobEffects.MOVEMENT_SPEED, 4800, 1), 1.0F).effect(new MobEffectInstance(MobEffects.FIRE_RESISTANCE, 2400, 1), 1.0F).alwaysEat().build());
    public static final FoodProperties EMERALD_APPLE = (new FoodProperties.Builder().nutrition(7).saturationMod(1.4F).effect(new MobEffectInstance(MobEffects.DAMAGE_BOOST, 1200, 1), 1.0F).effect(new MobEffectInstance(MobEffects.HEAL, 20, 0), 1.0F).effect(new MobEffectInstance(MobEffects.DAMAGE_RESISTANCE, 1800, 1), 1.0F).effect(new MobEffectInstance(MobEffects.ABSORPTION, 2400, 2), 1.0F).effect(new MobEffectInstance(MobEffects.MOVEMENT_SPEED, 2400, 0), 1.0F).alwaysEat().build());
    public static final FoodProperties ENCHANTED_EMERALD_APPLE = (new FoodProperties.Builder().nutrition(9).saturationMod(1.6F).effect(new MobEffectInstance(MobEffects.DAMAGE_BOOST, 2400, 2), 1.0F).effect(new MobEffectInstance(MobEffects.HEAL, 40, 1), 1.0F).effect(new MobEffectInstance(MobEffects.DAMAGE_RESISTANCE, 3600, 2), 1.0F).effect(new MobEffectInstance(MobEffects.ABSORPTION, 4800, 3), 1.0F).effect(new MobEffectInstance(MobEffects.MOVEMENT_SPEED, 4800, 3), 1.0F).alwaysEat().build());
    public static final FoodProperties IRON_APPLE = (new FoodProperties.Builder().nutrition(3).saturationMod(1.2F).effect(new MobEffectInstance(MobEffects.DAMAGE_BOOST, 600, 0), 1.0F).effect(new MobEffectInstance(MobEffects.ABSORPTION, 200, 0), 1.0F).alwaysEat().build());
    public static final FoodProperties ENCHANTED_IRON_APPLE = (new FoodProperties.Builder().nutrition(4).saturationMod(1.1F).effect(new MobEffectInstance(MobEffects.DAMAGE_BOOST, 1200, 1), 1.0F).effect(new MobEffectInstance(MobEffects.ABSORPTION, 400, 1), 1.0F).alwaysEat().build());
    public static final FoodProperties HOLY_BREAD = (new FoodProperties.Builder().nutrition(8).saturationMod(1.2F).effect(new MobEffectInstance(MobEffects.REGENERATION, 1200, 1), 1.0F).effect(new MobEffectInstance(MobEffects.DAMAGE_RESISTANCE, 6000, 0), 1.0F).effect(new MobEffectInstance(MobEffects.FIRE_RESISTANCE, 6000, 0), 1.0F).effect(new MobEffectInstance(MobEffects.ABSORPTION, 2400, 3), 1.0F).alwaysEat().build());

    //Drinks
    public static final FoodProperties APPLE_JUICE = (new FoodProperties.Builder().nutrition(5).saturationMod(0.5F)).build();
    public static final FoodProperties APPLE_SLUSHIE = (new FoodProperties.Builder().nutrition(6).saturationMod(0.7F)).build();
    public static final FoodProperties CARROT_JUICE = (new FoodProperties.Builder().nutrition(4).saturationMod(0.6F)).build();
    public static final FoodProperties LEMONADE = (new FoodProperties.Builder().nutrition(5).saturationMod(0.6F)).build();
    public static final FoodProperties MELON_JUICE = (new FoodProperties.Builder().nutrition(4).saturationMod(0.6F)).build();
    public static final FoodProperties MELON_SLUSHIE = (new FoodProperties.Builder().nutrition(5).saturationMod(0.8F)).build();
    public static final FoodProperties ORANGE_JUICE = (new FoodProperties.Builder().nutrition(5).saturationMod(0.6F)).build();
    public static final FoodProperties ORANGE_LEMONADE = (new FoodProperties.Builder().nutrition(6).saturationMod(0.8F).build());
    public static final FoodProperties ORANGE_SLUSHIE = (new FoodProperties.Builder().nutrition(6).saturationMod(0.8F)).build();
    public static final FoodProperties STRAWBERRY_JUICE = (new FoodProperties.Builder().nutrition(4).saturationMod(0.6F)).build();
    public static final FoodProperties STRAWBERRY_LEMONADE = (new FoodProperties.Builder().nutrition(6).saturationMod(0.8F).build());
    public static final FoodProperties STRAWBERRY_SLUSHIE = (new FoodProperties.Builder().nutrition(5).saturationMod(0.8F)).build();
    public static final FoodProperties TOMATO_JUICE = (new FoodProperties.Builder().nutrition(4).saturationMod(0.4F)).build();

    //FoodProperties
    public static final FoodProperties APPLE_SLICE = (new FoodProperties.Builder().nutrition(2).saturationMod(0.2F).build());
    public static final FoodProperties BACON = (new FoodProperties.Builder().nutrition(6).saturationMod(0.5F).build());
    public static final FoodProperties BACON_EGG_SANDWICH = (new FoodProperties.Builder().nutrition(20).saturationMod(1.0F).build());
    public static final FoodProperties BACON_POTATO_BEEF_STEW = (new FoodProperties.Builder().nutrition(12).saturationMod(0.9F).build());
    public static final FoodProperties BACON_POTATO_CHICKEN_STEW = (new FoodProperties.Builder().nutrition(12).saturationMod(0.9F).build());
    public static final FoodProperties BEEF_JERKY = (new FoodProperties.Builder().nutrition(3).saturationMod(0.5F).build());
    public static final FoodProperties BEEF_CHICKEN_SANDWICH = (new FoodProperties.Builder().nutrition(20).saturationMod(1.0F).build());
    public static final FoodProperties BEEF_PORK_SANDWICH = (new FoodProperties.Builder().nutrition(20).saturationMod(1.0F).build());
    public static final FoodProperties BEEF_SANDWICH = (new FoodProperties.Builder().nutrition(10).saturationMod(0.5F).build());
    public static final FoodProperties CABBAGE = (new FoodProperties.Builder().nutrition(3).saturationMod(0.4F).build());
    public static final FoodProperties CARROT_STICK = (new FoodProperties.Builder().nutrition(2).saturationMod(0.3F).build());
    public static final FoodProperties CHICKEN_NUGGET = (new FoodProperties.Builder().nutrition(1).saturationMod(0.3F).build());
    public static final FoodProperties CHICKEN_SANDWICH = (new FoodProperties.Builder().nutrition(10).saturationMod(0.6F).build());
    public static final FoodProperties CHICKEN_PORK_SANDWICH = (new FoodProperties.Builder().nutrition(20).saturationMod(1.0F).build());
    public static final FoodProperties COOKED_CARROT = (new FoodProperties.Builder().nutrition(4).saturationMod(0.3F).build());
    public static final FoodProperties COOKED_CARROT_STICK = (new FoodProperties.Builder().nutrition(3).saturationMod(0.3F).build());
    public static final FoodProperties COOKED_RABBIT_LEG = (new FoodProperties.Builder().nutrition(3).saturationMod(0.3F).build());
    public static final FoodProperties CORN = (new FoodProperties.Builder().nutrition(6).saturationMod(1.0F).build());
    public static final FoodProperties EGGS = (new FoodProperties.Builder().nutrition(4).saturationMod(0.4F).build());
    public static final FoodProperties GOLDEN_BACON = (new FoodProperties.Builder().nutrition(12).saturationMod(1.0F).build());
    public static final FoodProperties GOLDEN_BAKED_POTATO = (new FoodProperties.Builder().nutrition(10).saturationMod(1.2F).build());
    public static final FoodProperties GOLDEN_MELON_SLICE = (new FoodProperties.Builder().nutrition(4).saturationMod(0.6F).build());
    public static final FoodProperties GOLDEN_POTATO = (new FoodProperties.Builder().nutrition(2).saturationMod(0.6F).build());
    public static final FoodProperties LEMON = (new FoodProperties.Builder().nutrition(2).saturationMod(0.2F).build());
    public static final FoodProperties MELON_SLICE = (new FoodProperties.Builder().nutrition(1).saturationMod(0.2F).build());
    public static final FoodProperties MIXED_FRUIT_BOWL = (new FoodProperties.Builder().nutrition(11).saturationMod(0.8F).build());
    public static final FoodProperties MIXED_SEEDS = (new FoodProperties.Builder().nutrition(6).saturationMod(0.6F).build());
    public static final FoodProperties ORANGE = (new FoodProperties.Builder().nutrition(4).saturationMod(0.3F).build());
    public static final FoodProperties PIZZA = (new FoodProperties.Builder().nutrition(10).saturationMod(1.0F).build());
    public static final FoodProperties PORK_SANDWICH = (new FoodProperties.Builder().nutrition(10).saturationMod(0.6F).build());
    public static final FoodProperties POTATO_WEDGE = (new FoodProperties.Builder().nutrition(3).saturationMod(0.3F).build());
    public static final FoodProperties RABBIT_LEG = (new FoodProperties.Builder().nutrition(1).saturationMod(0.1F).build());
    public static final FoodProperties RAW_BACON = (new FoodProperties.Builder().nutrition(2).saturationMod(0.2F).effect(new MobEffectInstance(MobEffects.POISON, 200, 1), 1.0F).build());
    public static final FoodProperties RAW_BEEF_SLICE = (new FoodProperties.Builder().nutrition(1).saturationMod(0.2F).effect(new MobEffectInstance(MobEffects.POISON, 200, 1), 1.0F).build());
    public static final FoodProperties RAW_POTATO_WEDGE = (new FoodProperties.Builder().nutrition(1).saturationMod(0.2F).build());
    public static final FoodProperties ROASTED_APPLE = (new FoodProperties.Builder().nutrition(6).saturationMod(0.7F).build());
    public static final FoodProperties ROASTED_BEETROOT_SEEDS = (new FoodProperties.Builder().nutrition(2).saturationMod(0.3F).build());
    public static final FoodProperties ROASTED_MELON_SEEDS = (new FoodProperties.Builder().nutrition(2).saturationMod(0.3F).build());
    public static final FoodProperties ROASTED_MUSHROOM = (new FoodProperties.Builder().nutrition(2).saturationMod(0.2F).build());
    public static final FoodProperties ROASTED_PUMPKIN_SEEDS = (new FoodProperties.Builder().nutrition(2).saturationMod(0.3F).build());
    public static final FoodProperties ROASTED_RED_MUSHROOM = (new FoodProperties.Builder().nutrition(2).saturationMod(0.2F).build());
    public static final FoodProperties ROASTED_SEEDS = (new FoodProperties.Builder().nutrition(2).saturationMod(0.3F).build());
    public static final FoodProperties STRAWBERRY = (new FoodProperties.Builder().nutrition(2).saturationMod(0.3F).build());
    public static final FoodProperties SUGAR_COATED_APPLE = (new FoodProperties.Builder().nutrition(4).saturationMod(0.3F).build());
    public static final FoodProperties SUGAR_COATED_LEMEON = (new FoodProperties.Builder().nutrition(2).saturationMod(0.2F).build());
    public static final FoodProperties SUGAR_COATED_MELON = (new FoodProperties.Builder().nutrition(2).saturationMod(0.4F).build());
    public static final FoodProperties SUGAR_COATED_ORANGE = (new FoodProperties.Builder().nutrition(4).saturationMod(0.3F).build());
    public static final FoodProperties SUGAR_COATED_STRAWBERRY = (new FoodProperties.Builder().nutrition(2).saturationMod(0.3F).build());
    public static final FoodProperties TOAST = (new FoodProperties.Builder().nutrition(6).saturationMod(1.0F).build());
    public static final FoodProperties TOASTED_BACON_EGG_SANDWICH = (new FoodProperties.Builder().nutrition(20).saturationMod(1.0F).build());
    public static final FoodProperties TOMATO = (new FoodProperties.Builder().nutrition(3).saturationMod(0.4F).build());
}
