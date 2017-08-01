package com.rbs.slurpiesdongles.food;

import com.rbs.slurpiesdongles.SlurpiesDongles;
import net.minecraft.item.EnumAction;
import net.minecraft.item.ItemFood;
import net.minecraft.item.ItemStack;

/**
 * Created by Consular on 7/21/2017.
 */
public class FoodDrinkBase extends ItemFood {

    protected String name;

    public FoodDrinkBase(int amount, float saturation, boolean isWolfFood, String name) {
        super(amount, saturation, isWolfFood);

        setCreativeTab(SlurpiesDongles.creativeTab);

        this.name = name;
        setUnlocalizedName(name);
        setRegistryName(name);

    }
    public EnumAction getItemUseAction(ItemStack Stack) {

        return EnumAction.DRINK;
    }
}
