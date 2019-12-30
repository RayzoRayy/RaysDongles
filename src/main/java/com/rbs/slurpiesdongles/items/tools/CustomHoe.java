package com.rbs.slurpiesdongles.items.tools;

import com.rbs.slurpiesdongles.Reference;
import com.rbs.slurpiesdongles.init.ModTools;
import net.minecraft.item.HoeItem;
import net.minecraft.item.IItemTier;
import net.minecraft.item.Item;

public class CustomHoe extends HoeItem {

    public CustomHoe(IItemTier tier, int attackDamageIn, Item.Properties builder, String name) {
        super(tier, attackDamageIn, builder);

        this.setRegistryName(Reference.MODID, name);
    }
}
