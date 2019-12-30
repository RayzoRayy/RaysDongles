package com.rbs.slurpiesdongles.items;

import com.rbs.slurpiesdongles.Reference;
import com.rbs.slurpiesdongles.init.ModItems;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;

public class ItemBase extends Item {
    public ItemBase(Properties properties, String name) {
        super(properties);

        this.setRegistryName(Reference.MODID, name);
    }

}
