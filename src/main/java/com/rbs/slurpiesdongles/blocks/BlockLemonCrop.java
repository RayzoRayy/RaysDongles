package com.rbs.slurpiesdongles.blocks;

import com.rbs.slurpiesdongles.init.SDFood;
import net.minecraft.block.Block;
import net.minecraft.block.BlockCrops;
import net.minecraft.block.state.IBlockState;
import net.minecraft.item.Item;
import net.minecraft.item.ItemBlock;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockAccess;

import static net.minecraft.block.BlockCrops.AGE;

public class BlockLemonCrop extends BlockCrops {

    public BlockLemonCrop(String name) {
        this.setDefaultState(this.blockState.getBaseState().withProperty(AGE, Integer.valueOf(0)));
        this.setTickRandomly(true);

        setUnlocalizedName(name);
        setRegistryName(name);
    }

    private static final AxisAlignedBB[] LEMON_AABB = new AxisAlignedBB[]{new AxisAlignedBB(0.0D, 0.0D, 0.0D, 1.0D, 0.125D, 1.0D), new AxisAlignedBB(0.0D, 0.0D, 0.0D, 1.0D, 0.1875D, 1.0D), new AxisAlignedBB(0.0D, 0.0D, 0.0D, 1.0D, 0.25D, 1.0D), new AxisAlignedBB(0.0D, 0.0D, 0.0D, 1.0D, 0.3125D, 1.0D), new AxisAlignedBB(0.0D, 0.0D, 0.0D, 1.0D, 0.375D, 1.0D), new AxisAlignedBB(0.0D, 0.0D, 0.0D, 1.0D, 0.4375D, 1.0D), new AxisAlignedBB(0.0D, 0.0D, 0.0D, 1.0D, 0.5D, 1.0D), new AxisAlignedBB(0.0D, 0.0D, 0.0D, 1.0D, 0.5625D, 1.0D)};

    @Override
    protected Item getSeed() {
        return SDFood.lemon;
    }

    @Override
    protected Item getCrop() {
        return SDFood.lemon;


    }

    public AxisAlignedBB getBoundingBox(IBlockState state, IBlockAccess source, BlockPos pos) {
        return LEMON_AABB[((Integer) state.getValue(this.getAgeProperty())).intValue()];
    }

    public Item createItemBlock() {
        return new ItemBlock(this).setRegistryName(getRegistryName());

    }
}
