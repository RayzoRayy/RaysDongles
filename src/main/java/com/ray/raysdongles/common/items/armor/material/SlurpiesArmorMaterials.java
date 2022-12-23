package com.ray.raysdongles.common.items.armor.material;

import com.ray.raysdongles.core.init.ModItems;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.util.LazyLoadedValue;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.item.ArmorMaterial;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.crafting.Ingredient;

import java.util.function.Supplier;

public enum SlurpiesArmorMaterials implements ArmorMaterial {
    AMETHYST ("raysdongles:amethyst", 35, new int[]{3, 6, 8, 3}, 13, SoundEvents.ARMOR_EQUIP_DIAMOND,2.5F, 0F, () -> Ingredient.of(Items.AMETHYST_SHARD)),
    RUBY("raysdongles:ruby", 17, new int[]{2, 6, 6, 3}, 10, SoundEvents.ARMOR_EQUIP_IRON, 0.0F, 0F, () -> Ingredient.of(ModItems.RUBY.get())),
    TOPAZ("raysdongles:topaz", 33, new int[]{3, 6, 8, 3}, 11, SoundEvents.ARMOR_EQUIP_DIAMOND, 2.0F, 0F, () -> Ingredient.of(ModItems.TOPAZ.get())),
    WITHERED("raysdongles:withered", 0, new int[]{4, 7, 9, 4}, 15, SoundEvents.ARMOR_EQUIP_NETHERITE, 3.0F, 0.1F, () -> Ingredient.of());

    private static final int[] HEALTH_PER_SLOT = new int[]{13, 15, 16, 11};
    private final String name;
    private final int durabilityMultiplier;
    private final int[] slotProtections;
    private final int enchantmentValue;
    private final SoundEvent sound;
    private final float toughness;
    private final float knockbackResistance;
    private final LazyLoadedValue<Ingredient> repairIngredient;

    SlurpiesArmorMaterials(String pName, int pDurabilityMultiplier, int[] pSlotProtections, int pEnchantmentValue, SoundEvent pSound, float pToughness, float pKnockbackResistance, Supplier<Ingredient> pRepairIngredient) {
        this.name = pName;
        this.durabilityMultiplier = pDurabilityMultiplier;
        this.slotProtections = pSlotProtections;
        this.enchantmentValue = pEnchantmentValue;
        this.sound = pSound;
        this.toughness = pToughness;
        this.knockbackResistance = pKnockbackResistance;
        this.repairIngredient = new LazyLoadedValue<>(pRepairIngredient);
    }

    public int getDurabilityForSlot(EquipmentSlot pSlot) {
        return HEALTH_PER_SLOT[pSlot.getIndex()] * this.durabilityMultiplier;
    }

    public int getDefenseForSlot(EquipmentSlot pSlot) {
        return this.slotProtections[pSlot.getIndex()];
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
