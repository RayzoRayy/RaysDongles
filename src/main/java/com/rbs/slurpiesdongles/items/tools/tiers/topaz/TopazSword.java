package com.rbs.slurpiesdongles.items.tools.tiers.topaz;

import com.rbs.slurpiesdongles.Reference;
import com.rbs.slurpiesdongles.init.ModItems;
import net.minecraft.item.IItemTier;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.SwordItem;

public class TopazSword extends SwordItem {
    public TopazSword(IItemTier tier, int attackDamageIn, float attackSpeedIn, Item.Properties builder, String name) {
        super(tier, attackDamageIn, attackSpeedIn, builder);

        this.setRegistryName(Reference.MODID, name);

    }
    @Override
    public boolean getIsRepairable(ItemStack toRepair, ItemStack repair) {
        return repair.getItem() == ModItems.topaz;
    }
}
