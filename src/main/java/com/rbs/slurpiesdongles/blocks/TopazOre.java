package com.rbs.slurpiesdongles.blocks;

import com.rbs.slurpiesdongles.Reference;
import com.rbs.slurpiesdongles.helpers.HarvestLevelHelper;
import com.rbs.slurpiesdongles.init.ModBlocks;
import com.rbs.slurpiesdongles.init.ModItems;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.item.BlockItem;
import net.minecraft.item.Item;
import net.minecraft.util.IItemProvider;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;
import net.minecraft.world.World;
import net.minecraftforge.common.ToolType;

import java.util.Random;

public class TopazOre extends Block {

    public final HarvestLevelHelper resource;

    public TopazOre(Block.Properties builder, HarvestLevelHelper resource, String name) {
        super(builder);

        this.setRegistryName(Reference.MODID, name);
        this.resource = resource;
    }
    @Override
    public ToolType getHarvestTool(BlockState state) {
        return ToolType.PICKAXE;
    }

    @Override
    public int getHarvestLevel(BlockState state) {
        return resource.harvestLevel;
    }
}