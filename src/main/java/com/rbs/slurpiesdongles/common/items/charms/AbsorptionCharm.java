package com.rbs.slurpiesdongles.common.items.charms;

import net.minecraft.network.chat.Component;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Rarity;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.level.Level;
import org.jetbrains.annotations.Nullable;

import java.util.List;

public class AbsorptionCharm extends CharmItem {

    public AbsorptionCharm(Properties p_41383_) {
        super(p_41383_);
    }

    @Override
    public boolean onTick(ItemStack stack, Player living, Level world) {
        if (living instanceof Player){
            if(world.getGameTime() % 200 == 0 && living.getAbsorptionAmount() < living.getMaxHealth())
                if(!this.canTick(stack)){
                    living.setAbsorptionAmount(4.0F);
                }
        }
        return false;
    }

    @Override
    public void appendHoverText(ItemStack stack, @Nullable Level level, List<Component> toolTip, TooltipFlag flag) {
        toolTip.add(Component.translatable("Having this item in your inventory grants you 2 Absorbtion hearts every 10 seconds. Please allow 10 seconds to apply your hearts when you first craft the item"));
    }

    @Override
    public Rarity getRarity(ItemStack p_41461_) {
        return p_41461_.getCount() == 0 ? Rarity.RARE : Rarity.RARE;
    }
}
