package com.rbs.slurpiesdongles.items.charms;

import com.rbs.slurpiesdongles.items.ItemBase;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;

public abstract class CharmBase extends ItemBase {
    public CharmBase(Properties properties, String name) {

        super(properties, name);
    }
    public abstract void onTick(ItemStack stack, PlayerEntity living);

    @Override
    public void inventoryTick(ItemStack stack, World worldIn, Entity entityIn, int itemSlot, boolean isSelected) {
        if (entityIn instanceof PlayerEntity) {
            onTick(stack, (PlayerEntity) entityIn, worldIn);
        }
    }

    public boolean canTick(ItemStack stack) {
        return Boolean.parseBoolean(null);
    }



    public abstract boolean onTick(ItemStack stack, PlayerEntity living, World world);

}
