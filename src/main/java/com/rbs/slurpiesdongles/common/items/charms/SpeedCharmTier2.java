package com.rbs.slurpiesdongles.common.items.charms;

import net.minecraft.network.chat.Component;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Rarity;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.level.Level;
import org.jetbrains.annotations.Nullable;

import java.util.List;

public class SpeedCharmTier2 extends CharmItem{

    public SpeedCharmTier2(Properties p_41383_) {
        super(p_41383_);
    }

    @Override
    public boolean onTick(ItemStack stack, Player living, Level world) {
        if(!this.canTick(stack)){
            living.addEffect(new MobEffectInstance(MobEffects.MOVEMENT_SPEED, 600, 1, false, false));
        }
        return false;
    }

    @Override
    public void appendHoverText(ItemStack stack, @Nullable Level level, List<Component> toolTip, TooltipFlag flag) {
        toolTip.add(Component.translatable("Having this item in your inventory grants you Speed Tier 2"));
    }

    @Override
    public Rarity getRarity(ItemStack p_41461_) {
        return p_41461_.getCount() == 0 ? Rarity.EPIC : Rarity.EPIC;
    }

    @Override
    public boolean isFoil(ItemStack p_41453_) {
        return true;
    }
}
