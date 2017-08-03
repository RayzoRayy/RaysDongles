package com.rbs.slurpiesdongles.items;

import com.rbs.slurpiesdongles.SlurpiesDongles;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

import javax.annotation.Nullable;
import java.util.List;

public class ItemLignite extends Item {

    public ItemLignite(String name)
    {
        setCreativeTab(SlurpiesDongles.creativeTab);

        setUnlocalizedName(name);
        setRegistryName(name);
    }
    @SideOnly(Side.CLIENT)
    public void addInformation(ItemStack stack, @Nullable World worldIn, List<String> tooltip, ITooltipFlag flagIn) {
        tooltip.add("Smelts double what Coal smelts, (16 items)");
    }
}
