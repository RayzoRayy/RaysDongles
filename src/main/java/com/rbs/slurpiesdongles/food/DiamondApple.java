package com.rbs.slurpiesdongles.food;

import com.rbs.slurpiesdongles.SlurpiesDongles;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.MobEffects;
import net.minecraft.item.ItemFood;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.PotionEffect;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

import javax.annotation.Nullable;
import java.util.List;

public class DiamondApple extends ItemFood {

    public DiamondApple(int amount, float saturation, boolean isWolfFood, String name) {

        super(amount, saturation, isWolfFood);
        this.setHasSubtypes(true);
        this.setAlwaysEdible();
        this.setMaxStackSize(16);
        setCreativeTab(SlurpiesDongles.creativeTab);

        setUnlocalizedName(name);
        setRegistryName(name);
    }

    public void onFoodEaten(ItemStack stack, World worldIn, EntityPlayer player) {
        if (!worldIn.isRemote) {
            if (stack.getMetadata() > 0) {
                player.addPotionEffect(new PotionEffect(MobEffects.RESISTANCE, 1200, 0));//200 = 10 seconds in game
                player.addPotionEffect(new PotionEffect(MobEffects.SPEED, 1200, 0));
                player.addPotionEffect(new PotionEffect(MobEffects.FIRE_RESISTANCE, 1200, 0));

            } else {
                player.addPotionEffect(new PotionEffect(MobEffects.RESISTANCE, 1200, 0));//200 = 10 seconds in game
                player.addPotionEffect(new PotionEffect(MobEffects.SPEED, 1200, 0));
                player.addPotionEffect(new PotionEffect(MobEffects.FIRE_RESISTANCE, 1200, 0));
            }
        }
    }
    @SideOnly(Side.CLIENT)
    public void addInformation(ItemStack stack, @Nullable World worldIn, List<String> tooltip, ITooltipFlag flagIn)
    {
        tooltip.add("Grants Resistance, Speed, & Fire Resistance for 60 seconds. Effects are doubled on the enchanted version");
    }
}
