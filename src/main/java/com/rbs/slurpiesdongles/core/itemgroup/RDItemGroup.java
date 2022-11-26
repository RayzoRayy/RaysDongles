package com.rbs.slurpiesdongles.core.itemgroup;

import com.rbs.slurpiesdongles.core.init.ModFood;
import com.rbs.slurpiesdongles.core.init.ModItems;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.ItemStack;

public class RDItemGroup {

    public static final CreativeModeTab tabSlurpiesDongles = new CreativeModeTab("RaysDongles") {
        @Override
        public ItemStack makeIcon() {
            return new ItemStack(ModFood.TOMATO.get());
        }

        private ResourceLocation backgroundLocation;

        /*@Override
        public CreativeModeTab setBackgroundImage(ResourceLocation texture) {
            this.backgroundLocation = texture;
            return this;
        }

        public ResourceLocation getBackgroundImage() {
            if (backgroundLocation != null) return backgroundLocation; //FORGE: allow custom namespace
            return new net.minecraft.resources.ResourceLocation("textures/gui/container/creative_inventory/tab_slurpiesdongles.png");
        }*/

    };


}
