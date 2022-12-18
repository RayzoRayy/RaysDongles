package com.rbs.slurpiesdongles.common.items.charms;

import net.minecraft.network.chat.Component;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Rarity;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.level.Level;
import org.jetbrains.annotations.Nullable;

import java.util.List;

public class RegenerationCharm extends CharmItem {

    public RegenerationCharm(Properties p_41383_) {
        super(p_41383_);
    }

    @Override
    public boolean onTick(ItemStack stack, Player living, Level world) {
        if (living instanceof Player){
            if(world.getGameTime() % 45 == 0 && living.getHealth() < living.getMaxHealth())
                if(!this.canTick(stack)){
                    living.heal(0.5F);
                }
        }
        return false;
    }

    @Override
    public void appendHoverText(ItemStack stack, @Nullable Level level, List<Component> toolTip, TooltipFlag flag) {
        toolTip.add(Component.translatable("Having this item in your inventory grants you Health Regeneration"));
    }

    @Override
    public Rarity getRarity(ItemStack p_41461_) {
        return p_41461_.getCount() == 0 ? Rarity.RARE : Rarity.RARE;
    }
}
