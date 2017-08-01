package com.rbs.slurpiesdongles.items;

import com.rbs.slurpiesdongles.SlurpiesDongles;

public class ItemAxe extends net.minecraft.item.ItemAxe {

    protected String name;

    protected ItemAxe(String name, ToolMaterial material) {
        super(material);

        setCreativeTab(SlurpiesDongles.creativeTab);

        this.name = name;
        setRegistryName(name);
        setUnlocalizedName(name);
    }
}
