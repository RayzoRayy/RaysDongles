package com.rbs.slurpiesdongles.common.items.tools.material;

import com.rbs.slurpiesdongles.core.config.ConfigGeneral;
import com.rbs.slurpiesdongles.core.init.ModItems;
import net.minecraft.tags.BlockTags;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.Tier;
import net.minecraft.world.item.Tiers;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.level.block.Blocks;
import net.minecraftforge.common.ForgeTier;

public class TMTiers {
    //Materials that do not need Harvest or Speed levels
    private static double noEfficiency = 0;
    private static int noHarvest = 0;
    //Reg
    private static double amethystAttackDamage = ConfigGeneral.amethystAttackDamage.get();
    private static double amethystEfficiency = ConfigGeneral.amethystEfficiency.get();
    private static int amethystEnchantability = ConfigGeneral.amethystEnchantability.get();
    private static int amethystHarvestLevel = ConfigGeneral.amethystHarvestLevel.get();
    private static int amethystMaxUses = ConfigGeneral.amethystMaxUses.get();
    private static int amethystPaxelMaxUses = ConfigGeneral.amethystPaxelMaxUses.get();
    private static double rubyAttackDamage = ConfigGeneral.rubyAttackDamage.get();
    private static double rubyEfficiency = ConfigGeneral.rubyEfficiency.get();
    private static int rubyEnchantability = ConfigGeneral.rubyEnchantability.get();
    private static int rubyHarvestLevel = ConfigGeneral.rubyHarvestLevel.get();
    private static int rubyMaxUses = ConfigGeneral.rubyMaxUses.get();
    private static int rubyPaxelMaxUses = ConfigGeneral.rubyPaxelMaxUses.get();
    private static double topazAttackDamage = ConfigGeneral.topazAttackDamage.get();
    private static double topazEfficiency = ConfigGeneral.topazEfficiency.get();
    private static int topazEnchantability = ConfigGeneral.topazEnchantability.get();
    private static int topazHarvestLevel = ConfigGeneral.topazHarvestLevel.get();
    private static int topazMaxUses = ConfigGeneral.topazMaxUses.get();
    private static int topazPaxelMaxUses = ConfigGeneral.topazPaxelMaxUses.get();
    private static double vmAttackDamage = ConfigGeneral.vmAttackDamage.get();
    private static double vmEfficiency = ConfigGeneral.vmEfficiency.get();
    private static int vmEnchantability = ConfigGeneral.vmEnchantability.get();
    private static int vmHarvestLevel = ConfigGeneral.vmHarvestLevel.get();
    private static int vmMaxUses = ConfigGeneral.vmMaxUses.get();
    private static double witheredAttackDamage = ConfigGeneral.witheredAttackDamage.get();
    private static double witheredEfficiency = ConfigGeneral.witheredEfficiency.get();
    private static int witheredEnchantability = ConfigGeneral.witheredEnchantability.get();
    private static int witheredHarvestLevel = ConfigGeneral.witheredHarvestLevel.get();
    private static int witheredMaxUses = ConfigGeneral.witheredMaxUses.get();
    private static int witheredPaxelMaxUses = ConfigGeneral.witheredPaxelMaxUses.get();
    //Vanilla
    private static int diamondPaxelMaxUses = 4683;
    private static int goldPaxelMaxUses = 96;
    private static int ironPaxelMaxUses = 750;
    private static int netheritePaxelMaxUses = 6093;
    private static int stonePaxelMaxUses = 393;
    private static int woodenPaxelMaxUses = 177;

    //Tiers Creation
    //Reg
    public static final Tier AMETHYST = new ForgeTier(amethystHarvestLevel,
            amethystMaxUses,
            (float) amethystEfficiency,
            (float) amethystAttackDamage,
            amethystEnchantability,
            BlockTags.NEEDS_DIAMOND_TOOL, () -> Ingredient.of(Items.AMETHYST_SHARD));
    public static final Tier AMETHYST_BATTLEAXE = new ForgeTier(noHarvest,
            amethystMaxUses,
            (float) noEfficiency,
            (float) amethystAttackDamage,
            amethystEnchantability,
            null, () -> Ingredient.of(Items.AMETHYST_SHARD));
    public static final Tier AMETHYST_PAXEL = new ForgeTier(amethystHarvestLevel,
            amethystPaxelMaxUses,
            (float) amethystEfficiency,
            (float) amethystAttackDamage,
            amethystEnchantability,
            BlockTags.NEEDS_DIAMOND_TOOL, () -> Ingredient.of(Items.AMETHYST_SHARD));
    public static final Tier AMETHYST_SWORD = new ForgeTier(noHarvest,
            amethystMaxUses,
            (float) noEfficiency,
            (float) amethystAttackDamage,
            amethystEnchantability,
            null, () -> Ingredient.of(Items.AMETHYST_SHARD));
    public static final Tier RUBY = new ForgeTier(rubyHarvestLevel,
            rubyMaxUses,
            (float) rubyEfficiency,
            (float) rubyAttackDamage,
            rubyEnchantability,
            BlockTags.NEEDS_IRON_TOOL, () -> Ingredient.of(ModItems.RUBY.get()));
    public static final Tier RUBY_BATTLEAXE = new ForgeTier(noHarvest,
            rubyMaxUses,
            (float) noEfficiency,
            (float) rubyAttackDamage,
            rubyEnchantability,
            null, () -> Ingredient.of(ModItems.RUBY.get()));
    public static final Tier RUBY_PAXEL = new ForgeTier(rubyHarvestLevel,
            rubyPaxelMaxUses,
            (float) rubyEfficiency,
            (float) rubyAttackDamage,
            rubyEnchantability,
            BlockTags.NEEDS_IRON_TOOL, () -> Ingredient.of(ModItems.RUBY.get()));
    public static final Tier RUBY_SWORD = new ForgeTier(noHarvest,
            rubyMaxUses,
            (float) noEfficiency,
            (float) rubyAttackDamage,
            rubyEnchantability,
            null, () -> Ingredient.of(ModItems.RUBY.get()));
    public static final Tier TOPAZ = new ForgeTier(topazHarvestLevel,
            topazMaxUses,
            (float) topazEfficiency,
            (float) topazAttackDamage,
            topazEnchantability,
            BlockTags.NEEDS_DIAMOND_TOOL, () -> Ingredient.of(ModItems.TOPAZ.get()));
    public static final Tier TOPAZ_BATTLEAXE = new ForgeTier(noHarvest,
            topazMaxUses,
            (float) noEfficiency,
            (float) topazAttackDamage,
            topazEnchantability,
            null, () -> Ingredient.of(ModItems.TOPAZ.get()));
    public static final Tier TOPAZ_PAXEL = new ForgeTier(topazHarvestLevel,
            topazPaxelMaxUses,
            (float) topazEfficiency,
            (float) topazAttackDamage,
            topazEnchantability,
            BlockTags.NEEDS_DIAMOND_TOOL, () -> Ingredient.of(ModItems.TOPAZ.get()));
    public static final Tier TOPAZ_SWORD = new ForgeTier(noHarvest,
            topazMaxUses,
            (float) noEfficiency,
            (float) topazAttackDamage,
            topazEnchantability,
            null, () -> Ingredient.of(ModItems.TOPAZ.get()));
    public static final Tier WITHERED = new ForgeTier(witheredHarvestLevel,
            witheredMaxUses,
            (float) witheredEfficiency,
            (float) witheredAttackDamage,
            witheredEnchantability,
            BlockTags.NEEDS_DIAMOND_TOOL, () -> Ingredient.of());
    public static final Tier WITHERED_BATTLEAXE = new ForgeTier(noHarvest,
            witheredMaxUses,
            (float) noEfficiency,
            (float) witheredAttackDamage,
            witheredEnchantability,
            null, () -> Ingredient.of());
    public static final Tier WITHERED_PAXEL = new ForgeTier(witheredHarvestLevel,
            witheredPaxelMaxUses,
            (float) witheredEfficiency,
            (float) witheredAttackDamage,
            witheredEnchantability,
            BlockTags.NEEDS_DIAMOND_TOOL, () -> Ingredient.of());
    public static final Tier WITHERED_SWORD = new ForgeTier(noHarvest,
            witheredMaxUses,
            (float) noEfficiency,
            (float) witheredAttackDamage,
            witheredEnchantability,
            null, () -> Ingredient.of());
    public static final Tier VM = new ForgeTier(vmHarvestLevel,
            vmMaxUses,
            (float) vmEfficiency,
            (float) vmAttackDamage,
            vmEnchantability,
            BlockTags.NEEDS_STONE_TOOL, () -> Ingredient.of(Blocks.COBBLESTONE));
    //Vanilla Extras
    public static final Tier DIAMOND_PAXEL = new ForgeTier(Tiers.DIAMOND.getLevel(),
            diamondPaxelMaxUses,
            (float) Tiers.DIAMOND.getSpeed(),
            (float) Tiers.DIAMOND.getAttackDamageBonus(),
            Tiers.DIAMOND.getEnchantmentValue(),
            BlockTags.NEEDS_DIAMOND_TOOL, () -> Ingredient.of(Items.DIAMOND));
    public static final Tier GOLD_PAXEL = new ForgeTier(Tiers.GOLD.getLevel(),
            goldPaxelMaxUses,
            (float) Tiers.GOLD.getSpeed(),
            (float) Tiers.GOLD.getAttackDamageBonus(),
            Tiers.GOLD.getEnchantmentValue(),
            null, () -> Ingredient.of(Items.GOLD_INGOT));
    public static final Tier IRON_PAXEL = new ForgeTier(Tiers.IRON.getLevel(),
            ironPaxelMaxUses,
            (float) Tiers.IRON.getSpeed(),
            (float) Tiers.IRON.getAttackDamageBonus(),
            Tiers.IRON.getEnchantmentValue(),
            BlockTags.NEEDS_IRON_TOOL, () -> Ingredient.of(Items.IRON_INGOT));
    public static final Tier NETHERITE_PAXEL = new ForgeTier(Tiers.NETHERITE.getLevel(),
            netheritePaxelMaxUses,
            (float) Tiers.NETHERITE.getSpeed(),
            (float) Tiers.NETHERITE.getAttackDamageBonus(),
            Tiers.NETHERITE.getEnchantmentValue(),
            BlockTags.NEEDS_DIAMOND_TOOL, () -> Ingredient.of(Items.NETHERITE_INGOT));
    public static final Tier STONE_PAXEL = new ForgeTier(Tiers.STONE.getLevel(),
            stonePaxelMaxUses,
            (float) Tiers.STONE.getSpeed(),
            (float) Tiers.STONE.getAttackDamageBonus(),
            Tiers.STONE.getEnchantmentValue(),
            BlockTags.NEEDS_DIAMOND_TOOL, () -> Ingredient.of(Items.STONE));
    public static final Tier WOODEN_PAXEL = new ForgeTier(Tiers.WOOD.getLevel(),
            stonePaxelMaxUses,
            (float) Tiers.WOOD.getSpeed(),
            (float) Tiers.WOOD.getAttackDamageBonus(),
            Tiers.WOOD.getEnchantmentValue(),
            null, () -> Ingredient.of(Items.STONE));
}
