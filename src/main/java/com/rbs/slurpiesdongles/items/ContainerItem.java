package com.rbs.slurpiesdongles.items;

import com.rbs.slurpiesdongles.Reference;
import com.rbs.slurpiesdongles.init.ModItems;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;

public class ContainerItem extends ItemBase {
    public ContainerItem(Properties properties, String name) {
        super(properties, name);
    }



    @Override
    public boolean hasContainerItem(ItemStack stack)
    {
        return true;
    }

    @Override
    public ItemStack getContainerItem(ItemStack stack)
    {
        return stack.copy();
    }

}
