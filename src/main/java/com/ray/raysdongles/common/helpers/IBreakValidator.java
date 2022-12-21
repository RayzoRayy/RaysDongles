package com.ray.raysdongles.common.helpers;

import net.minecraft.world.level.block.state.BlockState;

public interface IBreakValidator {
    boolean canBreak(BlockState state);

}
