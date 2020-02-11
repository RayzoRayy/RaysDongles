package com.rbs.slurpiesdongles.blocks;

import com.rbs.slurpiesdongles.Reference;
import com.rbs.slurpiesdongles.init.ModBlocks;
import com.rbs.slurpiesdongles.init.ModFood;
import com.rbs.slurpiesdongles.init.ModItems;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.block.CropsBlock;
import net.minecraft.item.BlockItem;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.state.IProperty;
import net.minecraft.state.IntegerProperty;
import net.minecraft.state.StateContainer;
import net.minecraft.state.properties.BlockStateProperties;
import net.minecraft.util.IItemProvider;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.shapes.ISelectionContext;
import net.minecraft.util.math.shapes.VoxelShape;
import net.minecraft.world.IBlockReader;
import net.minecraft.world.World;
import net.minecraft.world.server.ServerWorld;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.event.world.BlockEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;

import java.util.Random;

public class StrawberryCrop extends CropsBlock {
    public static final IntegerProperty STRAWBERRY_AGE;
    private static final VoxelShape[] SHAPE;

    public StrawberryCrop(Properties p_i48441_1_, String name) {
        super(p_i48441_1_);

        this.setRegistryName(Reference.MODID, name);
        this.setDefaultState(this.stateContainer.getBaseState().with(this.getAgeProperty(), Integer.valueOf(0)));

    }
    public IntegerProperty getAgeProperty() {
        return STRAWBERRY_AGE;
    }

    public int getMaxAge() {
        return 3;
    }
    @OnlyIn(Dist.CLIENT)
    protected IItemProvider getSeedsItem() {
        return ModFood.strawberry_seed;
    }

    public void func_225534_a_(BlockState p_225534_1_, ServerWorld p_225534_2_, BlockPos p_225534_3_, Random p_225534_4_) {
        super.tick(p_225534_1_, p_225534_2_, p_225534_3_, p_225534_4_);
        if (!p_225534_2_.isAreaLoaded(p_225534_3_, 1)) return; // Forge: prevent loading unloaded chunks when checking neighbor's light
        if (p_225534_2_.getLightSubtracted(p_225534_3_, 0) >= 9) {
            int i = this.getAge(p_225534_1_);
            if (i < this.getMaxAge()) {
                float f = getGrowthChance(this, p_225534_2_, p_225534_3_);
                if (net.minecraftforge.common.ForgeHooks.onCropsGrowPre(p_225534_2_, p_225534_3_, p_225534_1_, p_225534_4_.nextInt((int)(25.0F / f) + 1) == 0)) {
                    p_225534_2_.setBlockState(p_225534_3_, this.withAge(i + 1), 2);
                    net.minecraftforge.common.ForgeHooks.onCropsGrowPost(p_225534_2_, p_225534_3_, p_225534_1_);
                }
            }
        }

    }





    protected int getBonemealAgeIncrease(World p_185529_1_) {
        return super.getBonemealAgeIncrease(p_185529_1_) / 3;
    }

    protected void fillStateContainer(StateContainer.Builder<Block, BlockState> p_206840_1_) {
        p_206840_1_.add(new IProperty[]{STRAWBERRY_AGE});
    }

    public VoxelShape getShape(BlockState p_220053_1_, IBlockReader p_220053_2_, BlockPos p_220053_3_, ISelectionContext p_220053_4_) {
        return SHAPE[(Integer)p_220053_1_.get(this.getAgeProperty())];
    }

    static {
        STRAWBERRY_AGE = BlockStateProperties.AGE_0_3;
        SHAPE = new VoxelShape[]{Block.makeCuboidShape(0.0D, 0.0D, 0.0D, 16.0D, 2.0D, 16.0D), Block.makeCuboidShape(0.0D, 0.0D, 0.0D, 16.0D, 4.0D, 16.0D), Block.makeCuboidShape(0.0D, 0.0D, 0.0D, 16.0D, 6.0D, 16.0D), Block.makeCuboidShape(0.0D, 0.0D, 0.0D, 16.0D, 8.0D, 16.0D)};
    }

}
