package com.rbs.slurpiesdongles.common.items.foods;

import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.TextComponent;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.level.Level;
import org.jetbrains.annotations.Nullable;

import java.util.List;


public class EnchantedDiamondApple extends Item {

    public EnchantedDiamondApple(Properties p_41383_) {
        super(p_41383_);
    }
    @Override
    public void appendHoverText(ItemStack stack, @Nullable Level worldIn, List<Component> tooltip, TooltipFlag flagIn) {
        tooltip.add(new TextComponent("Grants Fire Resistance 2, Speed 2, & Resistance 2"));
    }

    @Override
    public boolean isFoil(ItemStack p_41453_) {
        return true;
    }

}
