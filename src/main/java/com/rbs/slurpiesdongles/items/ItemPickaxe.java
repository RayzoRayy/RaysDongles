package com.rbs.slurpiesdongles.items;

import com.rbs.slurpiesdongles.SlurpiesDongles;

public class ItemPickaxe extends net.minecraft.item.ItemPickaxe {

    protected String name;

    public ItemPickaxe(ToolMaterial material) {
        super(material);
        setCreativeTab(SlurpiesDongles.creativeTab);
        this.setMaxStackSize(1);

        this.name = name;
        setUnlocalizedName(name);
        setRegistryName(name);
    }
}
