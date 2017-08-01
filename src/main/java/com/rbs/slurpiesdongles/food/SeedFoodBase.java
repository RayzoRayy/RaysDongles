package com.rbs.slurpiesdongles.food;

import com.rbs.slurpiesdongles.SlurpiesDongles;
import net.minecraft.block.Block;
import net.minecraft.item.ItemSeedFood;

public class SeedFoodBase extends ItemSeedFood {

    protected String name;
    public SeedFoodBase(int healAmount, float saturation, Block crops, Block soil, String name) {
        super(healAmount, saturation, crops, soil);
        setCreativeTab(SlurpiesDongles.creativeTab);

        this.name = name;
        setUnlocalizedName(name);
        setRegistryName(name);
    }
}
