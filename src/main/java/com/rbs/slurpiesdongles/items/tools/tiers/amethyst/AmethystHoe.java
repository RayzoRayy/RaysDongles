package com.rbs.slurpiesdongles.items.tools.tiers.amethyst;

import com.rbs.slurpiesdongles.Reference;
import com.rbs.slurpiesdongles.init.ModItems;
import net.minecraft.item.HoeItem;
import net.minecraft.item.IItemTier;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;

public class AmethystHoe extends HoeItem {
    public AmethystHoe(IItemTier tier, int attackDamageIn, Item.Properties builder, String name) {
        super(tier, attackDamageIn, builder);

        this.setRegistryName(Reference.MODID, name);
    }
    @Override
    public boolean getIsRepairable(ItemStack toRepair, ItemStack repair) {
        return repair.getItem() == ModItems.amethyst;
    }
}
