package com.rbs.slurpiesdongles.common.items.foods;

import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.TextComponent;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.level.Level;
import org.jetbrains.annotations.Nullable;

import java.util.List;

public class EnchantedEmeraldApple extends Item {

    public EnchantedEmeraldApple(Properties p_41383_) {
        super(p_41383_);
    }

    @Override
    public void appendHoverText(ItemStack stack, @Nullable Level worldIn, List<Component> tooltip, TooltipFlag flagIn) {
        tooltip.add(new TextComponent("Grants Absorbtion, Speed, Strength, Resistance & instant full health"));
    }

    @Override
    public boolean isFoil(ItemStack p_41453_) {
        return true;
    }
}
