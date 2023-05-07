package com.ray.raysdongles.common.items.tools;

import com.ray.raysdongles.common.helpers.BlockBreaker;
import net.minecraft.core.BlockPos;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.*;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.state.BlockState;

public class HammerItem extends DiggerItem {

    public HammerItem(float atkDmg, float atkSpd, Tier tier, Properties properties) {
        super(atkDmg, atkSpd, tier, SDBlockTags.Blocks.MINEABLE_WITH_HAMMER, properties);
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
    @Override
    public boolean canPerformAction(ItemStack stack, net.minecraftforge.common.ToolAction toolAction) {
        return net.minecraftforge.common.ToolActions.DEFAULT_PICKAXE_ACTIONS.contains(toolAction);
    }
}
