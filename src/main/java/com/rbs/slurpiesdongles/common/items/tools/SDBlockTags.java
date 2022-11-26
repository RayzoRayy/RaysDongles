package com.rbs.slurpiesdongles.common.items.tools;

import com.rbs.slurpiesdongles.SlurpiesDongles;
import net.minecraft.core.Registry;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.TagKey;
import net.minecraft.world.level.block.Block;

public class SDBlockTags {
    public static final TagKey<Block> PAXEL_MINEABLE = TagKey.create(Registry.BLOCK_REGISTRY, new ResourceLocation(SlurpiesDongles.MOD_ID,"mineable/paxel"));

}
