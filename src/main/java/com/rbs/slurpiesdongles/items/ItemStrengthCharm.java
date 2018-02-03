package com.rbs.slurpiesdongles.items;

import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.MobEffects;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.PotionEffect;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

import javax.annotation.Nullable;
import java.util.List;

public class ItemStrengthCharm extends ItemBaseCharm {

    public ItemStrengthCharm(String name) {
        super(name);
    }

    @Override
    public void onTick(ItemStack stack, EntityPlayer living) {
        if (!this.canTick(stack)) {
            living.addPotionEffect(new PotionEffect(MobEffects.STRENGTH, 600, 0));

        }
    }
    @SideOnly(Side.CLIENT)
    public void addInformation(ItemStack stack, @Nullable World worldIn, List<String> tooltip, ITooltipFlag flagIn)
    {
        tooltip.add("Holding this item in your inventory gives you Strength");
    }
}
