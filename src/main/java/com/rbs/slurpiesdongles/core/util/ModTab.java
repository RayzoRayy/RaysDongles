package com.rbs.slurpiesdongles.core.util;

import com.rbs.slurpiesdongles.core.init.ModFood;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.ItemStack;

public class ModTab {
    public static final CreativeModeTab tabSlurpiesDongles = new CreativeModeTab("RaysDongles") {
        @Override
        public ItemStack makeIcon() {
            return new ItemStack(ModFood.TOMATO.get());
        }
    };
}
