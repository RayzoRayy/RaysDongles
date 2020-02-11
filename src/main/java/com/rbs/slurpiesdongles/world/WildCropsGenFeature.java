package com.rbs.slurpiesdongles.world;

import com.mojang.datafixers.Dynamic;
import com.rbs.slurpiesdongles.blocks.WildCrops;
import com.rbs.slurpiesdongles.config.ConfigGeneral;
import com.rbs.slurpiesdongles.init.ModBlocks;
import net.minecraft.tags.BlockTags;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IWorld;
import net.minecraft.world.gen.ChunkGenerator;
import net.minecraft.world.gen.GenerationSettings;
import net.minecraft.world.gen.feature.Feature;
import net.minecraft.world.gen.feature.NoFeatureConfig;

import java.util.Random;
import java.util.function.Function;

public class WildCropsGenFeature extends Feature<NoFeatureConfig> {
    public WildCropsGenFeature(Function<Dynamic<?>, ? extends NoFeatureConfig> configFactory) {
        super(configFactory);
    }


    @Override
    public boolean place(IWorld world, ChunkGenerator<? extends GenerationSettings> generator, Random random, BlockPos pos, NoFeatureConfig config) {

        if (random.nextInt(ConfigGeneral.wildCropsChance.get()) != 0)
            return false;


        int place0 = (int) ((Math.random() * 4) + 1);
        int place1 = (int) ((Math.random() * 4) + 1);


        if (world.getBlockState(pos.north(place0).down()).getBlock().isIn(BlockTags.CROPS) && world.getBlockState(pos.north(place0)).getMaterial().isReplaceable())
            WildCrops.generateBush(world, pos.north(place0), random);
        if (world.getBlockState(pos.south(place1).down()).getBlock().isIn(BlockTags.CROPS) && world.getBlockState(pos.south(place1)).getMaterial().isReplaceable())
            WildCrops.generateBush(world, pos.south(place1), random);


        return true;
    }
}
