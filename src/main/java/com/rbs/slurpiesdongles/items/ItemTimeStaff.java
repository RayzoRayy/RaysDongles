package com.rbs.slurpiesdongles.items;

import com.rbs.slurpiesdongles.SlurpiesDongles;
import com.rbs.slurpiesdongles.gui.GuiTimeWand;
import net.minecraft.client.Minecraft;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ActionResult;
import net.minecraft.util.EnumHand;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class ItemTimeStaff extends Item {
    Minecraft mc;


    public ItemTimeStaff(String name) {

        this.setUnlocalizedName(name);
        this.setRegistryName(name);
        this.setMaxStackSize(1);
        this.setCreativeTab(SlurpiesDongles.creativeTab);

    }

    /*@Override
    public ActionResult<ItemStack> onItemRightClick(World worldIn, EntityPlayer playerIn, EnumHand handIn) {
        if (worldIn.isDaytime()) {
            worldIn.setWorldTime(13000);
            if(!playerIn.capabilities.isCreativeMode) {
                playerIn.getHeldItem(handIn).damageItem(1, playerIn);
            }
        } else if (!worldIn.isDaytime()) {
            worldIn.setWorldTime(1000);
            if(!playerIn.capabilities.isCreativeMode) {
                playerIn.getHeldItem(handIn).damageItem(1, playerIn);
            }
        }
        return super.onItemRightClick(worldIn, playerIn, handIn);
    }*/

    @Override
    @SideOnly(Side.CLIENT)
    public ActionResult<ItemStack> onItemRightClick(World worldIn, EntityPlayer playerIn, EnumHand handIn) {
        if(!worldIn.isRemote) {
            Minecraft.getMinecraft().displayGuiScreen(new GuiTimeWand());
        }

        return super.onItemRightClick(worldIn, playerIn, handIn);
    }

    @Override
    public boolean isRepairable() {
        return false;
    }
}
