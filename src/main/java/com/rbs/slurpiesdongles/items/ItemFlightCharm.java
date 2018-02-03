package com.rbs.slurpiesdongles.items;

import com.rbs.slurpiesdongles.init.SDItems;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.MobEffects;
import net.minecraft.item.EnumRarity;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.PotionEffect;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

import javax.annotation.Nullable;
import java.util.List;

public class ItemFlightCharm extends ItemBaseCharm {
    public ItemFlightCharm(String name) {
        super(name);
    }

    @Override
    public void onTick(ItemStack stack, EntityPlayer living) {
        if (!this.canTick(stack)) {
           // return;
        //}
            if (!living.capabilities.isCreativeMode) {
                if (!living.capabilities.allowFlying) {
                    living.capabilities.allowFlying = true;
                }
            }

            } else {

                if (!living.capabilities.isCreativeMode) {
                    living.capabilities.isFlying = false;
                    living.capabilities.allowFlying = false;
                }

            }


    }

        @SideOnly(Side.CLIENT)
        public void addInformation (ItemStack stack, @Nullable World worldIn, List < String > tooltip, ITooltipFlag
        flagIn)
        {
            tooltip.add("Holding this item in your inventory gives you Creative Flight!");
        }
    }



