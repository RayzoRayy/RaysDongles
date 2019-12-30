package com.rbs.slurpiesdongles.food;

import net.minecraft.item.Food;
import net.minecraft.item.Food.Builder;
import net.minecraft.potion.EffectInstance;
import net.minecraft.potion.Effects;

public class FoodStats {

    //Defensive Stuff
    public static final Food DIAMOND_APPLE = (new Builder().hunger(6).saturation(1.3F).effect(new EffectInstance(Effects.RESISTANCE, 1200, 0), 1.0F).effect(new EffectInstance(Effects.SPEED, 1200, 0), 1.0F).effect(new EffectInstance(Effects.FIRE_RESISTANCE, 1200, 0), 1.0F).setAlwaysEdible().build());
    public static final Food ENCHANTED_DIAMOND_APPLE = (new Builder().hunger(8).saturation(1.5F).effect(new EffectInstance(Effects.RESISTANCE, 2400, 1), 1.0F).effect(new EffectInstance(Effects.SPEED, 4800, 1), 1.0F).effect(new EffectInstance(Effects.FIRE_RESISTANCE, 2400, 1), 1.0F).setAlwaysEdible().build());
    public static final Food EMERALD_APPLE = (new Builder().hunger(7).saturation(1.4F).effect(new EffectInstance(Effects.STRENGTH, 1200, 1), 1.0F).effect(new EffectInstance(Effects.INSTANT_HEALTH, 20, 0), 1.0F).effect(new EffectInstance(Effects.RESISTANCE, 1800, 1), 1.0F).effect(new EffectInstance(Effects.ABSORPTION, 2400, 2), 1.0F).effect(new EffectInstance(Effects.SPEED, 2400, 0), 1.0F).setAlwaysEdible().build());
    public static final Food ENCHANTED_EMERALD_APPLE = (new Builder().hunger(9).saturation(1.6F).effect(new EffectInstance(Effects.STRENGTH, 2400, 2), 1.0F).effect(new EffectInstance(Effects.INSTANT_HEALTH, 40, 1), 1.0F).effect(new EffectInstance(Effects.RESISTANCE, 3600, 2), 1.0F).effect(new EffectInstance(Effects.ABSORPTION, 4800, 3), 1.0F).effect(new EffectInstance(Effects.SPEED, 4800, 3), 1.0F).setAlwaysEdible().build());
    public static final Food IRON_APPLE = (new Builder().hunger(3).saturation(1.2F).effect(new EffectInstance(Effects.STRENGTH, 600, 0), 1.0F).effect(new EffectInstance(Effects.ABSORPTION, 200, 0), 1.0F).setAlwaysEdible().build());
    public static final Food ENCHANTED_IRON_APPLE = (new Builder().hunger(4).saturation(1.1F).effect(new EffectInstance(Effects.STRENGTH, 1200, 1), 1.0F).effect(new EffectInstance(Effects.ABSORPTION, 400, 1), 1.0F).setAlwaysEdible().build());
    public static final Food HOLY_BREAD = (new Builder().hunger(8).saturation(1.2F).effect(new EffectInstance(Effects.REGENERATION, 1200, 1), 1.0F).effect(new EffectInstance(Effects.RESISTANCE, 6000, 0), 1.0F).effect(new EffectInstance(Effects.FIRE_RESISTANCE, 6000, 0), 1.0F).effect(new EffectInstance(Effects.ABSORPTION, 2400, 3), 1.0F).setAlwaysEdible().build());

    //Drinks
    public static final Food APPLE_JUICE = (new Builder().hunger(5).saturation(0.5F)).build();
    public static final Food APPLE_SLUSHIE = (new Builder().hunger(6).saturation(0.7F)).build();
    public static final Food CARROT_JUICE = (new Builder().hunger(4).saturation(0.6F)).build();
    public static final Food LEMONADE = (new Builder().hunger(5).saturation(0.6F)).build();
    public static final Food MELON_JUICE = (new Builder().hunger(4).saturation(0.6F)).build();
    public static final Food MELON_SLUSHIE = (new Builder().hunger(5).saturation(0.8F)).build();
    public static final Food ORANGE_JUICE = (new Builder().hunger(5).saturation(0.6F)).build();
    public static final Food ORANGE_SLUSHIE = (new Builder().hunger(6).saturation(0.8F)).build();
    public static final Food STRAWBERRY_JUICE = (new Builder().hunger(4).saturation(0.6F)).build();
    public static final Food STRAWBERRY_SLUSHIE = (new Builder().hunger(5).saturation(0.8F)).build();
    public static final Food TOMATO_JUICE = (new Builder().hunger(4).saturation(0.4F)).build();

    //Food
    public static final Food APPLE_SLICE = (new Builder().hunger(2).saturation(0.2F).build());
    public static final Food BACON = (new Builder().hunger(6).saturation(0.5F).build());
    public static final Food BACON_EGG_SANDWICH = (new Builder().hunger(20).saturation(1.0F).build());
    public static final Food BACON_POTATO_BEEF_STEW = (new Builder().hunger(12).saturation(0.9F).build());
    public static final Food BACON_POTATO_CHICKEN_STEW = (new Builder().hunger(12).saturation(0.9F).build());
    public static final Food BEEF_JERKY = (new Builder().hunger(3).saturation(0.5F).build());
    public static final Food BEEF_CHICKEN_SANDWICH = (new Builder().hunger(20).saturation(1.0F).build());
    public static final Food BEEF_PORK_SANDWICH = (new Builder().hunger(20).saturation(1.0F).build());
    public static final Food BEEF_SANDWICH = (new Builder().hunger(10).saturation(0.5F).build());
    public static final Food CABBAGE = (new Builder().hunger(3).saturation(0.4F).build());
    public static final Food CARROT_STICK = (new Builder().hunger(2).saturation(0.3F).build());
    public static final Food CHICKEN_NUGGET = (new Builder().hunger(1).saturation(0.3F).build());
    public static final Food CHICKEN_SANDWICH = (new Builder().hunger(10).saturation(0.6F).build());
    public static final Food CHICKEN_PORK_SANDWICH = (new Builder().hunger(20).saturation(1.0F).build());
    public static final Food COOKED_CARROT = (new Builder().hunger(4).saturation(0.3F).build());
    public static final Food COOKED_CARROT_STICK = (new Builder().hunger(3).saturation(0.3F).build());
    public static final Food COOKED_RABBIT_LEG = (new Builder().hunger(3).saturation(0.3F).build());
    public static final Food CORN = (new Builder().hunger(6).saturation(1.0F).build());
    public static final Food EGGS = (new Builder().hunger(4).saturation(0.4F).build());
    public static final Food LEMON = (new Builder().hunger(2).saturation(0.2F).build());
    public static final Food MELON_SLICE = (new Builder().hunger(1).saturation(0.2F).build());
    public static final Food MIXED_FRUIT_BOWL = (new Builder().hunger(11).saturation(0.8F).build());
    public static final Food MIXED_SEEDS = (new Builder().hunger(6).saturation(0.6F).build());
    public static final Food ORANGE = (new Builder().hunger(4).saturation(0.3F).build());
    public static final Food PIZZA = (new Builder().hunger(10).saturation(1.0F).build());
    public static final Food PORK_SANDWICH = (new Builder().hunger(10).saturation(0.6F).build());
    public static final Food POTATO_WEDGE = (new Builder().hunger(3).saturation(0.3F).build());
    public static final Food RABBIT_LEG = (new Builder().hunger(1).saturation(0.1F).build());
    public static final Food RAW_BACON = (new Builder().hunger(2).saturation(0.2F).effect(new EffectInstance(Effects.HUNGER, 200, 1), 1.0F).build());
    public static final Food RAW_BEEF_SLICE = (new Builder().hunger(1).saturation(0.2F).effect(new EffectInstance(Effects.HUNGER, 200, 1), 1.0F).build());
    public static final Food RAW_POTATO_WEDGE = (new Builder().hunger(1).saturation(0.2F).build());
    public static final Food ROASTED_APPLE = (new Builder().hunger(6).saturation(0.7F).build());
    public static final Food ROASTED_BEETROOT_SEEDS = (new Builder().hunger(2).saturation(0.3F).build());
    public static final Food ROASTED_MELON_SEEDS = (new Builder().hunger(2).saturation(0.3F).build());
    public static final Food ROASTED_MUSHROOM = (new Builder().hunger(2).saturation(0.2F).build());
    public static final Food ROASTED_PUMPKIN_SEEDS = (new Builder().hunger(2).saturation(0.3F).build());
    public static final Food ROASTED_RED_MUSHROOM = (new Builder().hunger(2).saturation(0.2F).build());
    public static final Food ROASTED_SEEDS = (new Builder().hunger(2).saturation(0.3F).build());
    public static final Food STRAWBERRY = (new Builder().hunger(2).saturation(0.3F).build());
    public static final Food SUGAR_COATED_APPLE = (new Builder().hunger(4).saturation(0.3F).build());
    public static final Food SUGAR_COATED_LEMEON = (new Builder().hunger(2).saturation(0.2F).build());
    public static final Food SUGAR_COATED_MELON = (new Builder().hunger(2).saturation(0.4F).build());
    public static final Food SUGAR_COATED_ORANGE = (new Builder().hunger(4).saturation(0.3F).build());
    public static final Food SUGAR_COATED_STRAWBERRY = (new Builder().hunger(2).saturation(0.3F).build());
    public static final Food TOAST = (new Builder().hunger(6).saturation(1.0F).build());
    public static final Food TOASTED_BACON_EGG_SANDWICH = (new Builder().hunger(20).saturation(1.0F).build());
    public static final Food TOMATO = (new Builder().hunger(3).saturation(0.4F).build());

}


