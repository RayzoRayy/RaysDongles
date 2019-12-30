package com.rbs.slurpiesdongles.food;

import com.rbs.slurpiesdongles.Reference;
import com.rbs.slurpiesdongles.init.ModFood;
import net.minecraft.block.Block;
import net.minecraft.command.impl.SeedCommand;
import net.minecraft.item.*;
import net.minecraft.util.FoodStats;
import net.minecraft.util.IItemProvider;

public class FoodBaseSeed extends BlockItem {

    public FoodBaseSeed(Properties p_i50041_2_, Block p_i50041_1_ , String name) {
        super(p_i50041_1_, p_i50041_2_);

        this.setRegistryName(Reference.MODID, name);
    }
    @Override
    public String getTranslationKey() {
        return this.getDefaultTranslationKey();
    }

   }
