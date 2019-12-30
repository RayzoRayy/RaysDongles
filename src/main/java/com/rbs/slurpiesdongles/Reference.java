package com.rbs.slurpiesdongles;

import com.rbs.slurpiesdongles.init.ModFood;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.util.IItemProvider;

public class Reference {

    public static final String MODID = "slurpiesdongles";

    public static ItemGroup tabSlurpiesDongles = new ItemGroup(ItemGroup.getGroupCountSafe(), "RaysDongles") {
        @Override
        public ItemStack createIcon() {
            return new ItemStack( ModFood.enchanted_diamond_apple);
        }
    };

}
