package com.rbs.slurpiesdongles.init;

import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraftforge.fml.common.registry.GameRegistry;

public class SmeltingRecipies {

    public static void registerSmeltingRecipes() {
        //Food
        GameRegistry.addSmelting(Items.APPLE, new ItemStack(SDFood.roastedApple), 0.0F);
        GameRegistry.addSmelting(Items.BEETROOT_SEEDS, new ItemStack(SDFood.roastedBeetrootSeeds), 0.0F);
        GameRegistry.addSmelting(Items.BREAD, new ItemStack(SDFood.toast), 0.0F);
        GameRegistry.addSmelting(Items.EGG, new ItemStack(SDFood.eggs), 0.0F);
        GameRegistry.addSmelting(Items.MELON_SEEDS, new ItemStack(SDFood.roastedMelonSeeds), 0.0F);
        GameRegistry.addSmelting(Items.PUMPKIN_SEEDS, new ItemStack(SDFood.roastedPumpkinSeeds), 0.0F);
        GameRegistry.addSmelting(SDFood.rawBacon, new ItemStack(SDFood.bacon), 0.0F);
        GameRegistry.addSmelting(SDFood.rawBeefSlice, new ItemStack(SDFood.beefJerky), 0.0F);
        GameRegistry.addSmelting(SDFood.rawCorn, new ItemStack(SDFood.corn), 0.0F);
        GameRegistry.addSmelting(Items.WHEAT_SEEDS, new ItemStack(SDFood.roastedSeeds), 0.0F);
        //Misc
        GameRegistry.addSmelting(SDItems.topaz, new ItemStack(SDItems.hardenedTopaz), 7.0F);
        GameRegistry.addSmelting(Items.WATER_BUCKET, new ItemStack(SDItems.hotWater), 0.0F);
        GameRegistry.addSmelting(Items.ROTTEN_FLESH, new ItemStack(Items.LEATHER), 0.2F);
        //Ores
        GameRegistry.addSmelting(SDBlocks.netherCoalOre, new ItemStack(Blocks.COAL_ORE, 2), 3.0F);
        GameRegistry.addSmelting(SDBlocks.netherDiamondOre, new ItemStack(Blocks.DIAMOND_ORE, 2), 10.0F);
        GameRegistry.addSmelting(SDBlocks.netherEmeraldOre, new ItemStack(Blocks.EMERALD_ORE, 2), 13.0F);
        GameRegistry.addSmelting(SDBlocks.netherIronOre, new ItemStack(Blocks.IRON_ORE, 2), 4.0F);

    }
}