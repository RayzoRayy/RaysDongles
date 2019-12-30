package com.rbs.slurpiesdongles.items.tools;

import com.rbs.slurpiesdongles.Reference;
import com.rbs.slurpiesdongles.init.ModTools;
import net.minecraft.item.IItemTier;
import net.minecraft.item.Item;
import net.minecraft.item.SwordItem;

public class CustomSword extends SwordItem {
    public CustomSword(IItemTier tier, int attackDamageIn, float attackSpeedIn, Item.Properties builder, String name) {
        super(tier, attackDamageIn, attackSpeedIn, builder);

        this.setRegistryName(Reference.MODID, name);

    }
}
