package com.rbs.slurpiesdongles.common.items.foods;

import net.minecraft.network.chat.Component;
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
    public void appendHoverText(ItemStack p_41421_, @Nullable Level p_41422_, List<Component> p_41423_, TooltipFlag p_41424_) {
        p_41423_.add(Component.translatable("Grants Fire Resistance 2, Speed 2, & Resistance 2"));
    }

    @Override
    public boolean isFoil(ItemStack p_41453_) {
        return true;
    }

}
