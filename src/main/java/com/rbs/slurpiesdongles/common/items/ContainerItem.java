package com.rbs.slurpiesdongles.common.items;

import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;

public class ContainerItem extends Item {

    public ContainerItem(Properties pProperties) {
        super(pProperties);
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
