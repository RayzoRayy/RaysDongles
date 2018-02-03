package com.rbs.slurpiesdongles.items;

import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public abstract class ItemBaseCharm extends ItemBase {

    public ItemBaseCharm(String name) {
        super(name);

        this.setMaxStackSize(1);
    }

    @Override
    public void onUpdate(ItemStack stack, World worldIn, Entity entityIn, int itemSlot, boolean isSelected) {
        if (entityIn instanceof EntityPlayer) {
            onTick(stack, (EntityPlayer) entityIn);
        }
    }

    public boolean canTick(ItemStack stack) {
        return Boolean.parseBoolean(null);
    }


    public abstract void onTick(ItemStack arg0, EntityPlayer arg1);

    }

