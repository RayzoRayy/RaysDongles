package com.rbs.slurpiesdongles.common.items.charms;

import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.TextComponent;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Rarity;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.level.Level;
import org.jetbrains.annotations.Nullable;

import java.util.List;

public class RegenerationCharmTier2 extends CharmItem {

    public RegenerationCharmTier2(Properties p_41383_) {
        super(p_41383_);
    }

    @Override
    public boolean onTick(ItemStack stack, Player living, Level world) {
        if (living instanceof Player){
            if(world.getGameTime() % 40 == 0 && living.getHealth() < living.getMaxHealth())
                if(!this.canTick(stack)){
                    living.heal(1.0F);
                }
        }
        return false;
    }

    @Override
    public void appendHoverText(ItemStack stack, @Nullable Level worldIn, List<Component> tooltip, TooltipFlag flagIn) {
        tooltip.add(new TextComponent("Having this item in your inventory grants you Health Regeneration at a faster rate than the tier 1 charm"));
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
