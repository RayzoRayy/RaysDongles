package com.ray.raysdongles.common.items.charms;

import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;

public abstract class CharmItem extends Item {
    public CharmItem(Properties p_41383_) {
        super(p_41383_);
    }

    @Override
    public void inventoryTick(ItemStack stack, Level level, Entity entity, int slot, boolean isSelected) {
        if(entity instanceof Player){
            onTick(stack, (Player) entity, level);
        }
    }

    public boolean canTick(ItemStack stack) {
        return Boolean.parseBoolean(null);
    }

    public abstract boolean onTick(ItemStack stack, Player living, Level world);
}
