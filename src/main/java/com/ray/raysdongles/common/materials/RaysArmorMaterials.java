package com.ray.raysdongles.common.materials;

import com.ray.raysdongles.core.init.ModItems;
import net.minecraft.Util;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.util.LazyLoadedValue;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.item.ArmorItem;
import net.minecraft.world.item.ArmorMaterial;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.crafting.Ingredient;

import java.util.EnumMap;
import java.util.function.Supplier;

public enum RaysArmorMaterials implements ArmorMaterial {

    AMETHYST ("raysdongles:amethyst", 35, Util.make(new EnumMap<>(ArmorItem.Type.class), (ammy) -> {
        ammy.put(ArmorItem.Type.BOOTS, 3);
        ammy.put(ArmorItem.Type.LEGGINGS, 6);
        ammy.put(ArmorItem.Type.CHESTPLATE, 8);
        ammy.put(ArmorItem.Type.HELMET, 3);
    }), 13, SoundEvents.ARMOR_EQUIP_DIAMOND, 2.5F, 0.0F, () -> {
        return Ingredient.of(Items.AMETHYST_SHARD);
    }),
    RUBY ("raysdongles:ruby", 17, Util.make(new EnumMap<>(ArmorItem.Type.class), (ruby) -> {
        ruby.put(ArmorItem.Type.BOOTS, 2);
        ruby.put(ArmorItem.Type.LEGGINGS, 6);
        ruby.put(ArmorItem.Type.CHESTPLATE, 6);
        ruby.put(ArmorItem.Type.HELMET, 3);
    }), 10, SoundEvents.ARMOR_EQUIP_IRON, 0.0F, 0.0F, () -> {
        return Ingredient.of(ModItems.RUBY.get());
    }),
    TOPAZ ("raysdongles:topaz", 33, Util.make(new EnumMap<>(ArmorItem.Type.class), (topaz) -> {
        topaz.put(ArmorItem.Type.BOOTS, 3);
        topaz.put(ArmorItem.Type.LEGGINGS, 6);
        topaz.put(ArmorItem.Type.CHESTPLATE, 8);
        topaz.put(ArmorItem.Type.HELMET, 3);
    }), 11, SoundEvents.ARMOR_EQUIP_DIAMOND, 2.0F, 0.0F, () -> {
        return Ingredient.of(ModItems.TOPAZ.get());
    }),
    WITHERED ("raysdongles:withered", 0, Util.make(new EnumMap<>(ArmorItem.Type.class), (topaz) -> {
        topaz.put(ArmorItem.Type.BOOTS, 4);
        topaz.put(ArmorItem.Type.LEGGINGS, 7);
        topaz.put(ArmorItem.Type.CHESTPLATE, 9);
        topaz.put(ArmorItem.Type.HELMET, 4);
    }), 15, SoundEvents.ARMOR_EQUIP_NETHERITE, 3.0F, 0.1F, () -> {
        return Ingredient.of();
    });

    private static final EnumMap<ArmorItem.Type, Integer> HEALTH_FUNCTION_FOR_TYPE = Util.make(new EnumMap<>(ArmorItem.Type.class), (hp) -> {
        hp.put(ArmorItem.Type.BOOTS, 13);
        hp.put(ArmorItem.Type.LEGGINGS, 15);
        hp.put(ArmorItem.Type.CHESTPLATE, 16);
        hp.put(ArmorItem.Type.HELMET, 11);
    });
    private final String name;
    private final int durabilityMultiplier;
    private final  EnumMap<ArmorItem.Type, Integer> protectionFunctionForType;
    private final int enchantmentValue;
    private final SoundEvent sound;
    private final float toughness;
    private final float knockbackResistance;
    private final LazyLoadedValue<Ingredient> repairIngredient;

    RaysArmorMaterials(String pName, int pDurabilityMultiplier, EnumMap<ArmorItem.Type, Integer> pfft, int pEnchantmentValue, SoundEvent pSound, float pToughness, float pKnockbackResistance, Supplier<Ingredient> pRepairIngredient) {
        this.name = pName;
        this.durabilityMultiplier = pDurabilityMultiplier;
        this.enchantmentValue = pEnchantmentValue;
        this.sound = pSound;
        this.toughness = pToughness;
        this.knockbackResistance = pKnockbackResistance;
        this.repairIngredient = new LazyLoadedValue<>(pRepairIngredient);
        this.protectionFunctionForType = pfft;
    }

    @Override
    public int getDurabilityForType(ArmorItem.Type type) {
        return HEALTH_FUNCTION_FOR_TYPE.get(type) * this.durabilityMultiplier;
    }

    @Override
    public int getDefenseForType(ArmorItem.Type type) {
        return protectionFunctionForType.get(type);
    }

    public int getEnchantmentValue() {
        return this.enchantmentValue;
    }

    public SoundEvent getEquipSound() {
        return this.sound;
    }

    public Ingredient getRepairIngredient() {
        return this.repairIngredient.get();
    }

    public String getName() {
        return this.name;
    }

    public float getToughness() {
        return this.toughness;
    }

    /**
     * Gets the percentage of knockback resistance provided by armor of the material.
     */
    public float getKnockbackResistance() {
        return this.knockbackResistance;
    }
}
