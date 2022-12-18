package com.rbs.slurpiesdongles.common.items.tools;

import com.rbs.slurpiesdongles.SlurpiesDongles;
import net.minecraft.core.Registry;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.BlockTags;
import net.minecraft.tags.TagKey;
import net.minecraft.world.level.block.Block;

public class SDBlockTags {
    public static void init() {
        Blocks.init();
    }

    private SDBlockTags() {
    }

    public static class Blocks {

        private static void init() {
        }

        private Blocks() {
        }
        public static final TagKey<Block> MINEABLE_WITH_PAXEL = forgeTag("mineable/paxel");

        private static TagKey<Block> tag(String name) {
            return BlockTags.create(new ResourceLocation(SlurpiesDongles.MOD_ID, name));
        }

        private static TagKey<Block> forgeTag(String name) {
            return BlockTags.create(new ResourceLocation("forge", name));
        }
    }
}

