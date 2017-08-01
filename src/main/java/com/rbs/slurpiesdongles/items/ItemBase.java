package com.rbs.slurpiesdongles.items;

import com.rbs.slurpiesdongles.SlurpiesDongles;
import com.rbs.slurpiesdongles.init.SDTab;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import sun.font.CreatedFontTracker;

/**
 * Created by Consular on 7/19/2017.
 */
public class ItemBase extends Item {

    protected String name;

    public ItemBase(String name) {

        setCreativeTab(SlurpiesDongles.creativeTab);

        this.name = name;
        setUnlocalizedName(name);
        setRegistryName(name);
    }
}
