package com.rbs.slurpiesdongles.init;

import com.rbs.slurpiesdongles.SlurpiesDongles;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;

/**
 * Created by Consular on 7/21/2017.
 */
public class SDTab extends CreativeTabs {

    public SDTab() {
        super(SlurpiesDongles.modId);

        setBackgroundImageName("item_search.png");
        setBackgroundImageName("slurpiesdongles.png");
    }

    @Override
    public ItemStack getTabIconItem() {
        return new ItemStack(Items.ENCHANTED_BOOK);
    }

    @Override
    public boolean hasSearchBar() {
        return true;
    }

    @Override
    public String getTranslatedTabLabel() {
        return "SD";
    }
}

