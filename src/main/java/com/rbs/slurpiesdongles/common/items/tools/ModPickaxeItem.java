package com.rbs.slurpiesdongles.common.items.tools;

import net.minecraft.tags.BlockTags;
import net.minecraft.tags.TagKey;
import net.minecraft.world.item.DiggerItem;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Tier;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockState;

import static net.minecraftforge.common.TierSortingRegistry.isCorrectTierForDrops;

public class ModPickaxeItem extends DiggerItem {

    public ModPickaxeItem(Tier p_204110_, float p_204108_, float p_204109_, Properties p_204112_) {
        super(p_204108_, p_204109_, p_204110_, BlockTags.MINEABLE_WITH_PICKAXE, p_204112_);
    }
    @Override
    public float getDestroySpeed(ItemStack itemStack, BlockState blockState) {
        return getTier().getSpeed();

    }

    @Override
    public boolean isCorrectToolForDrops(ItemStack stack, BlockState state) {
        return isCorrectTierForDrops(getTier(), state);
    }
    @Override
    public boolean canPerformAction(ItemStack stack, net.minecraftforge.common.ToolAction toolAction) {
        return net.minecraftforge.common.ToolActions.DEFAULT_PICKAXE_ACTIONS.contains(toolAction);
    }
}
