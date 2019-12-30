package com.rbs.slurpiesdongles.items.tools;

import com.rbs.slurpiesdongles.Reference;
import com.rbs.slurpiesdongles.helpers.HarvestLevelHelper;
import com.rbs.slurpiesdongles.init.ModTools;
import net.minecraft.item.IItemTier;
import net.minecraft.item.Item;
import net.minecraft.item.ShovelItem;

public class CustomShovel extends ShovelItem {
    public CustomShovel(IItemTier tier, int attackDamageIn, float attackSpeedIn, Item.Properties builder , String name) {
        super(tier, attackDamageIn, attackSpeedIn, builder);

        this.setRegistryName(Reference.MODID, name);
    }
}
