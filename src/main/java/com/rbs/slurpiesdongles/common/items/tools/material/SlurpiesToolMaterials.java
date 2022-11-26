package com.rbs.slurpiesdongles.common.items.tools.material;

import com.rbs.slurpiesdongles.core.init.ModItems;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.Tier;
import net.minecraft.world.item.crafting.Ingredient;

import java.util.function.Supplier;

public enum SlurpiesToolMaterials implements Tier {
    //Battleaxe Materials
    AMETHYST_BATTLEAXE(0, 1739, 0.0F, 4, 10, () -> Ingredient.of(Items.AMETHYST_SHARD)),

    RUBY_BATTLEAXE(0, 721, 0.0F, 3, 14, () -> Ingredient.of(ModItems.RUBY.get())),

    TOPAZ_BATTLEAXE(0, 1893, 0.0F, 6, 20, () -> Ingredient.of(ModItems.TOPAZ.get())),

    WITHERED_BATTLEAXE(0, 0, 0.0F, 7, 25, () -> Ingredient.of(Items.COAL)),

    //General Tool Materials
    AMETHYST(3, 1739, 8.0F, 1, 10, () -> Ingredient.of(Items.AMETHYST_SHARD)),

    RUBY(2, 721, 6.0F, 1, 14, () -> Ingredient.of(ModItems.RUBY.get())),

    TOPAZ(4, 1893, 9.0F, 1, 20, () -> Ingredient.of(ModItems.TOPAZ.get())),

    WITHERED(4, 0, 10.0F, 1, 25, () -> Ingredient.of(Items.COAL)),

    //Paxel Materials

    AMETHYST_PAXEL(3, 5217, 8.0F, 3, 10, () -> Ingredient.of(Items.AMETHYST_SHARD)),

    DIAMOND_PAXEL(3, 4683, 8.0F, 3, 10, () -> Ingredient.of(Items.DIAMOND)),

    GOLD_PAXEL(0, 96, 12.0F, 0, 22, () -> Ingredient.of(Items.GOLD_INGOT)),

    IRON_PAXEL(2, 750, 6.0F, 2, 14, () -> Ingredient.of(Items.IRON_INGOT)),

    STONE_PAXEL(1, 393, 4.0F, 1, 5, () -> Ingredient.of(Items.COBBLESTONE)),

    RUBY_PAXEL(3, 2163, 6.0F, 3, 14, () -> Ingredient.of(ModItems.RUBY.get())),

    TOPAZ_PAXEL(4, 5679, 9.0F, 5, 20, () -> Ingredient.of(ModItems.TOPAZ.get())),

    WITHERED_PAXEL(4, 0, 10.0F, 6, 25, () -> Ingredient.of(Items.COAL)),

    WOODEN_PAXEL(0, 177, 2.0F, 0, 15, () -> Ingredient.of(Items.OAK_PLANKS)),

    //Pickaxe Materials
    VMPICK(1, 18200, 4.0F, 1, 5, () -> Ingredient.of(Items.COBBLESTONE)),

    //Sword Materials
    AMETHYST_SWORD(0, 1739, 0.0F, 3, 10, () -> Ingredient.of(Items.AMETHYST_SHARD)),

    RUBY_SWORD(0, 721, 0.0F, 3, 14, () -> Ingredient.of(ModItems.RUBY.get())),

    TOPAZ_SWORD(0, 1893, 0.0F, 5, 20, () -> Ingredient.of(ModItems.TOPAZ.get())),

    WITHERED_SWORD(0, 0, 0.0F, 6, 25, () -> Ingredient.of(Items.COAL));

    public final int harvestLevel;
    private final int maxUses;
    private final float efficiency;
    private final float attackDanage;
    private final int enchantability;
    private final Ingredient repairMaterial;

    SlurpiesToolMaterials(int harvestLevel, int maxUses, float efficiency, float attackDamage, int enchantability, Supplier<Ingredient> repairMaterial){
        this.harvestLevel = harvestLevel;
        this.maxUses = maxUses;
        this.efficiency = efficiency;
        this.attackDanage = attackDamage;
        this.enchantability = enchantability;
        this.repairMaterial = repairMaterial.get();
    }


    @Override
    public int getUses() {
        return this.maxUses;
    }

    @Override
    public float getSpeed() {
        return this.efficiency;
    }

    @Override
    public float getAttackDamageBonus() {
        return this.attackDanage;
    }

    @Override
    public int getLevel() {
        return this.harvestLevel;
    }

    @Override
    public int getEnchantmentValue() {
        return this.enchantability;
    }

    @Override
    public Ingredient getRepairIngredient() {
        return this.repairMaterial;
    }
}
