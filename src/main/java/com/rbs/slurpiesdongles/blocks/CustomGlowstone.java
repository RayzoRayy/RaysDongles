package com.rbs.slurpiesdongles.blocks;

import com.rbs.slurpiesdongles.Reference;
import com.rbs.slurpiesdongles.helpers.HarvestLevelHelper;
import com.rbs.slurpiesdongles.init.ModBlocks;
import com.rbs.slurpiesdongles.init.ModItems;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.MaterialColor;
import net.minecraft.item.BlockItem;
import net.minecraft.item.Item;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;
import net.minecraft.world.World;

import java.util.Random;

public class CustomGlowstone extends Block {

    public final HarvestLevelHelper resource;

    public CustomGlowstone(Properties builder, HarvestLevelHelper resource, String name) {
        super(builder);

        this.resource = resource;

        this.setRegistryName(Reference.MODID, name);
    }

    @Override
    public int getHarvestLevel(BlockState state) {
        return resource.harvestLevel;
    }
}
