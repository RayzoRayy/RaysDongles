package com.rbs.slurpiesdongles.blocks;

import com.rbs.slurpiesdongles.Reference;
import com.rbs.slurpiesdongles.helpers.HarvestLevelHelper;
import com.rbs.slurpiesdongles.init.ModBlocks;
import com.rbs.slurpiesdongles.init.ModItems;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.item.BlockItem;
import net.minecraft.item.Item;

public class CustomBricks extends Block {

    public final HarvestLevelHelper resource;

    public CustomBricks(Properties builder, HarvestLevelHelper resource, String name) {
        super(builder);

        this.resource = resource;

        this.setRegistryName(Reference.MODID, name);
    }

    @Override
    public int getHarvestLevel(BlockState state) {
        return resource.harvestLevel;
    }
}
