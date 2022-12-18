package com.rbs.slurpiesdongles.common.items.tools;

import net.minecraft.core.BlockPos;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ShovelItem;
import net.minecraft.world.item.Tier;
import net.minecraft.world.level.ClipContext;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.HitResult;

import javax.annotation.Nullable;

public class ExcavatorItem extends ShovelItem {

    public ExcavatorItem(Tier tier, float atkDmg, float atkSpd, Properties props) {
        super(tier, atkDmg, atkSpd, props);
    }
    @Override
    public boolean canAttackBlock(BlockState p_41441_, Level level, BlockPos pos, Player player) {
        int radius = 1;
        if (player.isShiftKeyDown()) {
            radius = 0;
        }
        float originHardness = level.getBlockState(pos).getDestroySpeed(level, pos);

        // only do a 3x3 break if the player's tool is effective on the block they are breaking
        // this makes it so breaking gravel doesn't break nearby stone
        if (player.getMainHandItem().isCorrectToolForDrops(level.getBlockState(pos))) {
            BlockBreaker.breakInRadius(level, player, radius, pos, (breakState) -> {
                double hardness = breakState.getDestroySpeed(level, pos);
                boolean isEffective = player.getMainHandItem().isCorrectToolForDrops(breakState);
                boolean verifyHardness = hardness < originHardness * 5 && hardness > 0;
                return isEffective && verifyHardness;
            });
        }
        return true;
    }
}
