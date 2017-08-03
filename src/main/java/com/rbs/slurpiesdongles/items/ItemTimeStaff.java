package com.rbs.slurpiesdongles.items;

import com.rbs.slurpiesdongles.SlurpiesDongles;
import com.rbs.slurpiesdongles.gui.GuiHandler;
import com.rbs.slurpiesdongles.gui.GuiTimeWand;
import net.minecraft.client.Minecraft;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.EnumRarity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ActionResult;
import net.minecraft.util.EnumHand;
import net.minecraft.world.World;
import net.minecraftforge.fml.common.network.internal.OpenGuiHandler;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class ItemTimeStaff extends Item {
    Minecraft mc;


    public ItemTimeStaff(String name) {
        this.setMaxStackSize(1);
        this.setCreativeTab(SlurpiesDongles.creativeTab);

        this.setUnlocalizedName(name);
        this.setRegistryName(name);

    }

    @Override
    @SideOnly(Side.CLIENT)
    public ActionResult<ItemStack> onItemRightClick(World worldIn, EntityPlayer playerIn, EnumHand handIn) {
        if(worldIn.isRemote) {

            playerIn.openGui(SlurpiesDongles.instance, GuiHandler.WAND, worldIn, (int) playerIn.posX, (int) playerIn.posY, (int) playerIn.posZ);
        }

        return super.onItemRightClick(worldIn, playerIn, handIn);
    }

    @Override
    public boolean isRepairable() {
        return false;
    }

    public EnumRarity getRarity(ItemStack stack) {
        return stack.getMetadata() == 0 ? EnumRarity.RARE : EnumRarity.EPIC;

    }
}
