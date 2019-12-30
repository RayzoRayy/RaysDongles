package com.rbs.slurpiesdongles.blocks;

import com.rbs.slurpiesdongles.Reference;
import com.rbs.slurpiesdongles.init.ModBlocks;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.BushBlock;
import net.minecraft.block.TallGrassBlock;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.shapes.ISelectionContext;
import net.minecraft.util.math.shapes.VoxelShape;
import net.minecraft.world.IBlockReader;
import net.minecraft.world.IWorld;

import java.util.Random;

public class WildCrops extends BushBlock {

    //protected static final VoxelShape SHAPE = Block.makeCuboidShape(2.0D, 0.0D, 2.0D, 14.0D, 13.0D, 14.0D);

    public WildCrops(Properties builder, String name) {
        super(builder);

        this.setRegistryName(Reference.MODID, name);
    }

    public Block.OffsetType getOffsetType() {
        return Block.OffsetType.XZ;
    }


    public static void generateBush(IWorld world, BlockPos pos, Random random)
    {
        world.setBlockState(pos, ModBlocks.wild_crops.getDefaultState(), 3);
    }

}

