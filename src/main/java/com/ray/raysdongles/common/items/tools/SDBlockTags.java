package com.ray.raysdongles.common.items.tools;

import com.ray.raysdongles.RaysDongles;
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
        public static final TagKey<Block> MINEABLE_WITH_HAMMER = forgeTag("mineable/hammer");
        public static final TagKey<Block> MINEABLE_WITH_PAXEL = forgeTag("mineable/paxel");


        private static TagKey<Block> tag(String name) {
            return BlockTags.create(new ResourceLocation(RaysDongles.MOD_ID, name));
        }

        private static TagKey<Block> forgeTag(String name) {
            return BlockTags.create(new ResourceLocation("forge", name));
        }
    }
}

