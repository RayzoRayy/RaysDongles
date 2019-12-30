package com.rbs.slurpiesdongles.food;

import com.rbs.slurpiesdongles.Reference;
import com.rbs.slurpiesdongles.init.ModFood;
import net.minecraft.item.Item;

public class FoodBase extends Item {
    public FoodBase(Item.Properties p_i50045_1_, String name) {
        super(p_i50045_1_);

        this.setRegistryName(Reference.MODID, name);

    }
}
