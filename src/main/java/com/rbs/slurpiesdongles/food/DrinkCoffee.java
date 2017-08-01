package com.rbs.slurpiesdongles.food;

import com.rbs.slurpiesdongles.SlurpiesDongles;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.MobEffects;
import net.minecraft.item.EnumAction;
import net.minecraft.item.ItemFood;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.PotionEffect;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

import java.util.List;

public class DrinkCoffee extends ItemFood {
    public DrinkCoffee(int amount, float saturation, boolean isWolfFood, String name) {
        super(amount, saturation, isWolfFood);
        this.setCreativeTab(SlurpiesDongles.creativeTab);
        this.setAlwaysEdible();

        setUnlocalizedName(name);
        setRegistryName(name);


    }

    public EnumAction getItemUseAction(ItemStack Stack) {

        return EnumAction.DRINK;
    }

    public void onFoodEaten(ItemStack stack, World worldIn, EntityPlayer player) {
        if (!worldIn.isRemote) {
            if (stack.getMetadata() > 0) {
                player.addPotionEffect(new PotionEffect(MobEffects.HASTE, 1200, 1));

            } else {

            }
        }
    }
    @SideOnly(Side.CLIENT)
    public void addInformation(ItemStack stack, EntityPlayer player, List list, boolean par4)
    {
        list.add("Drinking this gives you Haste!");
    }
}
