package com.rbs.slurpiesdongles.food;

import com.rbs.slurpiesdongles.SlurpiesDongles;
import net.minecraft.item.ItemFood;

/**
 * Created by Consular on 7/21/2017.
 */
public class FoodBase extends ItemFood {

    protected String name;

    public FoodBase(int amount, float saturation, boolean isWolfFood, String name) {
        super(amount, saturation, isWolfFood);

        setCreativeTab(SlurpiesDongles.creativeTab);

        this.name = name;
        setUnlocalizedName(name);
        setRegistryName(name);
    }
}
