package com.rbs.slurpiesdongles.items;

import com.rbs.slurpiesdongles.SlurpiesDongles;

public class ItemHoe extends net.minecraft.item.ItemHoe {

    protected String name;

    public ItemHoe(String name, ToolMaterial material) {
        super(material);
        setCreativeTab(SlurpiesDongles.creativeTab);
        this.setMaxStackSize(1);

        this.name = name;
        setUnlocalizedName(name);
        setRegistryName(name);
    }
}
