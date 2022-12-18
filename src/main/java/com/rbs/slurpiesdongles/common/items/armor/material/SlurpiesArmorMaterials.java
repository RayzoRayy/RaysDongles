package com.rbs.slurpiesdongles.common.items.armor.material;

import com.rbs.slurpiesdongles.core.init.ModItems;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.item.ArmorMaterial;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.crafting.Ingredient;

import java.util.function.Supplier;

public enum SlurpiesArmorMaterials implements ArmorMaterial {
    AMETHYST ("slurpiesdongles:amethyst", 34, new int[]{3, 6, 7, 4}, 10, SoundEvents.ARMOR_EQUIP_DIAMOND,2.0F, 0F, () -> Ingredient.of(Items.AMETHYST_SHARD)),
    RUBY("slurpiesdongles:ruby", 16, new int[]{2, 6, 6, 2}, 9, SoundEvents.ARMOR_EQUIP_DIAMOND, 0.0F, 0F, () -> Ingredient.of(ModItems.RUBY.get())),
    TOPAZ("slurpiesdongles:topaz", 35, new int[]{4, 6, 8, 4}, 20, SoundEvents.ARMOR_EQUIP_DIAMOND, 2.0F, 0F, () -> Ingredient.of(ModItems.TOPAZ.get())),
    WITHERED("slurpiesdongles:withered", 0, new int[]{4, 6, 7, 4}, 25, SoundEvents.ARMOR_EQUIP_DIAMOND, 2.0F, 0F, () -> Ingredient.of(Items.COAL));

    private static final int[] HEALTH_PER_SLOT = { 13, 15, 16, 11 };
    private final String name;
    private final int durabilityMultiplier;
    private final int[] armorVal;
    private final int enchantability;
    private final SoundEvent equipSound;
    private final float toughness;
    private final float knockbackResistance;
    private final Ingredient repairIngredient;

    SlurpiesArmorMaterials(String name, int durabilityMultiplier, int[] armorVal, int enchantability, SoundEvent equipSound, float toughness, float knockbackResistance, Supplier<Ingredient> repairIngredient) {
        this.name = name;
        this.durabilityMultiplier = durabilityMultiplier;
        this.armorVal = armorVal;
        this.enchantability = enchantability;
        this.equipSound = equipSound;
        this.toughness = toughness;
        this.knockbackResistance = knockbackResistance;
        this.repairIngredient = repairIngredient.get();

    }
    @Override
    public int getDurabilityForSlot(EquipmentSlot slot) {
        return HEALTH_PER_SLOT[slot.getIndex()] * this.durabilityMultiplier;
    }

    @Override
    public int getDefenseForSlot(EquipmentSlot slot) {
        return this.armorVal[slot.getIndex()];
    }

    @Override
    public int getEnchantmentValue() {
        return enchantability;
    }

    @Override
    public SoundEvent getEquipSound() {
        return equipSound;
    }

    @Override
    public Ingredient getRepairIngredient() {
        return repairIngredient;
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public float getToughness() {
        return toughness;
    }

    @Override
    public float getKnockbackResistance() {
        return knockbackResistance;
    }
}
