package com.rbs.slurpiesdongles.items.tools.materials;

import com.rbs.slurpiesdongles.init.ModItems;
import net.minecraft.block.Blocks;
import net.minecraft.item.IItemTier;
import net.minecraft.item.Item;
import net.minecraft.item.Items;
import net.minecraft.item.crafting.Ingredient;
import net.minecraftforge.common.util.Lazy;

import java.util.function.Supplier;

public enum ToolMaterials implements IItemTier{

    //Battleaxe Materials
    AMETHYST_BATTLEAXE(0, 1739, 0.0F, 4, 10, ModItems.amethyst),

    RUBY_BATTLEAXE(0, 721, 0.0F, 3, 14, ModItems.ruby),

    TOPAZ_BATTLEAXE(0, 1893, 0.0F, 6, 20, ModItems.topaz),

    WITHERED_BATTLEAXE(0, 0, 0.0F, 7, 25, Items.NETHER_STAR),

    //General Tool Materials
    AMETHYST(3, 1739, 8.0F, 1, 10, ModItems.amethyst),

    RUBY(2, 721, 6.0F, 1, 14, ModItems.ruby),

    TOPAZ(4, 1893, 9.0F, 1, 20, ModItems.topaz),

    WITHERED(4, 0, 10.0F, 1, 25, Items.NETHER_STAR),

    //Paxel Materials

    AMETHYST_PAXEL(3, 5217, 8.0F, 3, 10, ModItems.amethyst),

    DIAMOND_PAXEL(3, 4683, 8.0F, 3, 10, Items.DIAMOND),

    GOLD_PAXEL(0, 96, 12.0F, 0, 22, Items.GOLD_INGOT),

    IRON_PAXEL(2, 750, 6.0F, 2, 14, Items.IRON_INGOT),

    STONE_PAXEL(1, 393, 4.0F, 1, 5, Items.COBBLESTONE),

    RUBY_PAXEL(3, 2163, 6.0F, 3, 14, ModItems.ruby),

    TOPAZ_PAXEL(4, 5679, 9.0F, 5, 20, ModItems.topaz),

    WITHERED_PAXEL(4, 0, 10.0F, 6, 25, Items.NETHER_STAR),

    WOODEN_PAXEL(0, 177, 2.0F, 0, 15, Items.OAK_PLANKS),

    //Pickaxe Materials
    VMPICK(1, 18200, 4.0F, 1, 5, Items.IRON_INGOT),

    //Sword Materials
    AMETHYST_SWORD(0, 1739, 0.0F, 3, 10, ModItems.amethyst),

    RUBY_SWORD(0, 721, 0.0F, 3, 14, ModItems.ruby),

    TOPAZ_SWORD(0, 1893, 0.0F, 5, 20, ModItems.topaz),

    WITHERED_SWORD(0, 0, 0.0F, 6, 25, Items.NETHER_STAR);

    public final int harvestLevel;
    public final int maxUses;
    public final float efficiency;
    public final float attackDamage;
    public final int enchantability;
    private Item repairItem;

    ToolMaterials(int harvestLevel, int maxUses, float efficiency, float attackDamage, int enchantability, Item repairItem) {
        this.harvestLevel = harvestLevel;
        this.maxUses = maxUses;
        this.efficiency = efficiency;
        this.attackDamage = attackDamage;
        this.enchantability = enchantability;
        this.repairItem = repairItem;
    }
    @Override
    public int getMaxUses() {
        return this.maxUses;
    }
    @Override
    public float getEfficiency() {
        return this.efficiency;
    }
    @Override
    public float getAttackDamage() {
        return this.attackDamage;
    }
    @Override
    public int getHarvestLevel() {
        return this.harvestLevel;
    }
    @Override
    public int getEnchantability() {
        return this.enchantability;
    }

    @Override
    public Ingredient getRepairMaterial() {
        return Ingredient.fromItems(this.repairItem);
    }
}
