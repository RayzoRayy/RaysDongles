package com.rbs.slurpiesdongles.blocks;

import com.rbs.slurpiesdongles.Reference;
import com.rbs.slurpiesdongles.helpers.HarvestLevelHelper;
import com.rbs.slurpiesdongles.init.ModBlocks;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.entity.Entity;
import net.minecraft.entity.boss.WitherEntity;
import net.minecraft.entity.monster.WitherSkeletonEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.projectile.WitherSkullEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.state.IStateHolder;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.StringTextComponent;
import net.minecraft.util.text.TextFormatting;
import net.minecraft.world.*;
import net.minecraftforge.common.ToolType;
import net.minecraftforge.common.extensions.IForgeBlockState;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import java.util.List;

public class ReinforcedObsidian extends Block {

    public final HarvestLevelHelper resource;

    public ReinforcedObsidian(Properties builder, HarvestLevelHelper resource, String name) {
        super(builder);

        this.resource = resource;
        this.setRegistryName(Reference.MODID, name);


    }
    @Override
    public void addInformation(ItemStack stack, @Nullable IBlockReader worldIn, List<ITextComponent> tooltip, ITooltipFlag flagIn) {
        tooltip.add(new StringTextComponent(TextFormatting.RED + "Wither proof"));
        tooltip.add(new StringTextComponent(TextFormatting.AQUA +"Can be broken by players"));
    }

    @Override
    public boolean canEntityDestroy(BlockState state, IBlockReader world, BlockPos pos, Entity entity) {
        if(entity instanceof PlayerEntity) {
            return true;
        }else
            return false;
    }

    @Override
    public void onBlockExploded(BlockState state, World world, BlockPos pos, Explosion explosion) {
        if(world.getBlockState(pos).getBlock() == ModBlocks.reinforced_obsidian) {
            explosion.clearAffectedBlockPositions();
        }
    }

    @Override
    public boolean canDropFromExplosion(BlockState state, IBlockReader world, BlockPos pos, Explosion explosion) {
        return false;
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


