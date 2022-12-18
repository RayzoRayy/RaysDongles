package com.rbs.slurpiesdongles.common.items.tools;

import net.minecraft.world.level.block.state.BlockState;

public interface IBreakValidator {
    boolean canBreak(BlockState state);

}
