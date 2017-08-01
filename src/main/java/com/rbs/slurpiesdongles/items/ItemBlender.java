package com.rbs.slurpiesdongles.items;

import com.rbs.slurpiesdongles.SlurpiesDongles;
import com.rbs.slurpiesdongles.init.SDItems;
import net.minecraft.item.Item;

public class ItemBlender extends Item {

    protected String name;

    public ItemBlender(String name) {

        setCreativeTab(SlurpiesDongles.creativeTab);

        this.name = name;
        setUnlocalizedName(name);
        setRegistryName(name);
    }
}
