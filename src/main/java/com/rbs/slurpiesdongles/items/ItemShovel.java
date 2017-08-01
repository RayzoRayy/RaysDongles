package com.rbs.slurpiesdongles.items;

import com.rbs.slurpiesdongles.SlurpiesDongles;
import net.minecraft.item.ItemSpade;

public class ItemShovel extends ItemSpade {

    protected String name;

    public ItemShovel(String name, ToolMaterial material) {
        super(material);
        setCreativeTab(SlurpiesDongles.creativeTab);
        this.setMaxStackSize(1);

        this.name = name;
        setUnlocalizedName(name);
        setRegistryName(name);
    }

}
