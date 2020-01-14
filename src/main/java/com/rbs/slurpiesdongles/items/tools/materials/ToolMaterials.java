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
    AMAZONITE_BATTLEAXE(0, 1949, 0.0F, 4, 37, ModItems.amazonite),

    AMETHYST_BATTLEAXE(0, 873, 0.0F, 4, 31, ModItems.amethyst),

    EMERALD_BATTLEAXE(0, 2149, 0.0F, 3, 17, Items.EMERALD),

    PERIDOT_BATTLEAXE(0, 492, 0.0F, 2, 27, ModItems.peridot),

    RUBY_BATTLEAXE(0, 721, 0.0F, 3, 24, ModItems.ruby),

    SAPPHIRE_BATTLEAXE(0, 561, 0.0F, 3, 26, ModItems.sapphire),

    TOPAZ_BATTLEAXE(0, 0, 0.0F, 6, 20, ModItems.topaz),

    WITHERED_BATTLEAXE(0, 0, 0.0F, 7, 17, Items.NETHER_STAR),

    //General Tool Materials
    AMAZONITE(3, 1949, 9.0F, 1, 37, ModItems.amazonite),

    AMETHYST(3, 873, 7.0F, 1, 31, ModItems.amethyst),

    EMERALD(4, 2149, 10.0F, 1, 17, Items.EMERALD),

    PERIDOT(2, 492, 6.0F, 1, 27, ModItems.peridot),

    RUBY(2, 721, 7.0F, 1, 24, ModItems.ruby),

    SAPPHIRE(2, 561, 6.0F, 1, 26, ModItems.sapphire),

    TOPAZ(4, 0, 14.0F, 1, 20, ModItems.topaz),


    WITHERED(4, 0, 20.0F, 1, 17, Items.NETHER_STAR),

    //Paxel Materials

    AMAZONITE_PAXEL(3, 5847, 9.0F, 3, 37, ModItems.amazonite),

    AMETHYST_PAXEL(3, 2619, 7.0F, 3, 31, ModItems.amethyst),

    DIAMOND_PAXEL(3, 4683, 8.0F, 3, 31, Items.DIAMOND),


    EMERALD_PAXEL(3, 6447, 10.0F, 3, 17, Items.EMERALD),

    GOLD_PAXEL(0, 96, 12.0F, 0, 22, Items.GOLD_INGOT),

    IRON_PAXEL(2, 750, 6.0F, 2, 14, Items.IRON_INGOT),

    STONE_PAXEL(1, 393, 4.0F, 1, 5, Items.COBBLESTONE),

    PERIDOT_PAXEL(3, 1476, 6.0F, 2, 27, ModItems.peridot),

    RUBY_PAXEL(3, 2163, 7.0F, 3, 24, ModItems.ruby),

    SAPPHIRE_PAXEL(2, 1683, 6.0F, 3, 26, ModItems.sapphire),

    TOPAZ_PAXEL(4, 0, 14.0F, 5, 20, ModItems.topaz),

    WITHERED_PAXEL(4, 0, 20.0F, 6, 17, Items.NETHER_STAR),

    WOODEN_PAXEL(0, 177, 2.0F, 0, 15, Items.OAK_PLANKS),

    //Pickaxe Materials
    VMPICK(1, 18200, 4.0F, 1, 10, Items.IRON_INGOT),

    //Sword Materials
    AMAZONITE_SWORD(0, 1949, 0.0F, 3, 37, ModItems.amazonite),

    AMETHYST_SWORD(0, 873, 0.0F, 3, 31, ModItems.amethyst),

    EMERALD_SWORD(0, 2149, 0.0F, 3, 17, Items.EMERALD),

    PERIDOT_SWORD(0, 492, 0.0F, 2, 27, ModItems.peridot),

    RUBY_SWORD(0, 721, 0.0F, 3, 24, ModItems.ruby),

    SAPPHIRE_SWORD(0, 561, 0.0F, 3, 26, ModItems.sapphire),

    TOPAZ_SWORD(0, 0, 0.0F, 5, 20, ModItems.topaz),

    WITHERED_SWORD(0, 0, 0.0F, 6, 17, Items.NETHER_STAR);

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
