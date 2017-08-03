package com.rbs.slurpiesdongles.food;

import com.rbs.slurpiesdongles.SlurpiesDongles;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.MobEffects;
import net.minecraft.item.EnumAction;
import net.minecraft.item.ItemFood;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.PotionEffect;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

import javax.annotation.Nullable;
import java.util.List;

public class DrinkBlackCoffee extends ItemFood {
    public DrinkBlackCoffee(int amount, float saturation, boolean isWolfFood, String name) {
        super(amount, saturation, isWolfFood);

        this.setAlwaysEdible();
        this.setCreativeTab(SlurpiesDongles.creativeTab);

        setUnlocalizedName(name);
        setRegistryName(name);

    }

    public EnumAction getItemUseAction(ItemStack Stack) {

        return EnumAction.DRINK;
    }

    public void onFoodEaten(ItemStack stack, World worldIn, EntityPlayer player) {
        if (!worldIn.isRemote) {
            if (stack.getMetadata() > 0) {
                player.addPotionEffect(new PotionEffect(MobEffects.SPEED, 1200, 0));
            }
            else
            {
                player.addPotionEffect(new PotionEffect(MobEffects.SPEED, 1200, 0));

            }

        }
    }
    @SideOnly(Side.CLIENT)
    public void addInformation(ItemStack stack, @Nullable World worldIn, List<String> tooltip, ITooltipFlag flagIn) {
        tooltip.add("Gives you speed for a minute!");
    }

}
