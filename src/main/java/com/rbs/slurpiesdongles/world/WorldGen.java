package com.rbs.slurpiesdongles.world;

import com.rbs.slurpiesdongles.init.ConfigPreInit;
import com.rbs.slurpiesdongles.init.SDBlocks;
import net.minecraft.block.state.pattern.BlockMatcher;
import net.minecraft.init.Blocks;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraft.world.chunk.IChunkProvider;
import net.minecraft.world.gen.IChunkGenerator;
import net.minecraft.world.gen.feature.WorldGenMinable;
import net.minecraft.world.gen.feature.WorldGenerator;
import net.minecraftforge.fml.common.IWorldGenerator;

import java.util.Random;

public class WorldGen implements IWorldGenerator {

    //@formatter:off

    private WorldGenerator genLigniteOre;
    private WorldGenerator genSapphireOre;
    private WorldGenerator genRubyOre;
    private WorldGenerator genTopazOre;
    private WorldGenerator genLemonBush;
    private WorldGenerator genOrangeBush;
    //Nether
    private WorldGenerator genNetherCoalOre;
    private WorldGenerator genNetherDiamondOre;
    private WorldGenerator genNetherEmeraldOre;
    private WorldGenerator genNetherGoldOre;
    private WorldGenerator genNetherIronOre;
    private WorldGenerator genNetherLapisOre;
    private WorldGenerator genNetherRedstoneOre;

    //@formatter:on

    public WorldGen() {

        this.genLigniteOre = new WorldGenMinable(SDBlocks.oreLignite.getDefaultState(), ConfigPreInit.ligniteBlockCount, BlockMatcher.forBlock(Blocks.STONE));
        this.genSapphireOre = new WorldGenMinable(SDBlocks.oreSapphire.getDefaultState(), ConfigPreInit.sapphireBlockCount, BlockMatcher.forBlock(Blocks.STONE));
        this.genRubyOre = new WorldGenMinable(SDBlocks.oreRuby.getDefaultState(), ConfigPreInit.rubyBlockCount, BlockMatcher.forBlock(Blocks.STONE));
        this.genTopazOre = new WorldGenMinable(SDBlocks.oreTopaz.getDefaultState(), ConfigPreInit.topazBlockCount, BlockMatcher.forBlock(Blocks.STONE));
        this.genLemonBush = new WorldGenMinable(SDBlocks.lemonBush.getDefaultState(), ConfigPreInit.lemonBlockCount, BlockMatcher.forBlock(Blocks.GRASS));
        this.genOrangeBush = new WorldGenMinable(SDBlocks.orangeBush.getDefaultState(), ConfigPreInit.orangeBlockCount, BlockMatcher.forBlock(Blocks.GRASS));
        //Nether
        this.genNetherCoalOre = new WorldGenMinable(SDBlocks.netherCoalOre.getDefaultState(), ConfigPreInit.coalBlockCount, BlockMatcher.forBlock(Blocks.NETHERRACK));
        this.genNetherDiamondOre = new WorldGenMinable(SDBlocks.netherDiamondOre.getDefaultState(), ConfigPreInit.diamondBlockCount, BlockMatcher.forBlock(Blocks.NETHERRACK));
        this.genNetherEmeraldOre= new WorldGenMinable(SDBlocks.netherEmeraldOre.getDefaultState(), ConfigPreInit.emeraldBlockCount, BlockMatcher.forBlock(Blocks.NETHERRACK));
        this.genNetherGoldOre = new WorldGenMinable(SDBlocks.netherGoldOre.getDefaultState(), ConfigPreInit.goldBlockCount, BlockMatcher.forBlock(Blocks.NETHERRACK));
        this.genNetherIronOre = new WorldGenMinable(SDBlocks.netherIronOre.getDefaultState(), ConfigPreInit.ironBlockCount, BlockMatcher.forBlock(Blocks.NETHERRACK));
        this.genNetherLapisOre = new WorldGenMinable(SDBlocks.netherLapisOre.getDefaultState(), ConfigPreInit.lapisBlockCount, BlockMatcher.forBlock(Blocks.NETHERRACK));
        this.genNetherRedstoneOre = new WorldGenMinable(SDBlocks.netherRedstoneOre.getDefaultState(), ConfigPreInit.redstoneBlockCount, BlockMatcher.forBlock(Blocks.NETHERRACK));

    }

    @Override
    public void generate(Random random, int chunkX, int chunkZ, World world, IChunkGenerator chunkGenerator, IChunkProvider chunkProvider) {

        switch (world.provider.getDimension()) {
            case 0: //OverWorld
                this.runGenerator(this.genLigniteOre, world, random, chunkX, chunkZ, ConfigPreInit.ligniteChancesToSpawn, ConfigPreInit.ligniteMinYLevel, ConfigPreInit.ligniteMaxYLevel);
                this.runGenerator(this.genSapphireOre, world, random, chunkX, chunkZ, ConfigPreInit.sapphireChanesToSpawn, ConfigPreInit.sapphireMinYLevel, ConfigPreInit.sapphireMaxYLevel);
                this.runGenerator(this.genRubyOre, world, random, chunkX, chunkZ, ConfigPreInit.rubyChancesToSpawn, ConfigPreInit.rubyMinYLevel, ConfigPreInit.rubyMaxYLevel);
                this.runGenerator(this.genTopazOre, world, random, chunkX, chunkZ, ConfigPreInit.topazChancesToSpawn, ConfigPreInit.topazMinYLevel, ConfigPreInit.topazMaxYLevel);
                this.runGenerator(this.genLemonBush, world, random, chunkX, chunkZ, ConfigPreInit.lemonChancesToSpawn, ConfigPreInit.lemoneMinYLevel, ConfigPreInit.lemonMaxYLevel);
                this.runGenerator(this.genOrangeBush, world, random, chunkX, chunkZ, ConfigPreInit.orangeChancesToSpawn, ConfigPreInit.orangeMinYLevel, ConfigPreInit.orangeMaxYLevel);


                break;
            case -1: //Nether
                this.runGenerator(this.genNetherCoalOre, world, random, chunkX, chunkZ, ConfigPreInit.coalChancesToSpawn, ConfigPreInit.coalMinYLevel, ConfigPreInit.coalMaxYLevel);
                this.runGenerator(this.genNetherDiamondOre, world, random, chunkX, chunkZ, ConfigPreInit.diamondChancesToSpawn, ConfigPreInit.diamondMinYLevel, ConfigPreInit.diamondMaxYLevel);
                this.runGenerator(this.genNetherEmeraldOre, world, random, chunkX, chunkZ, ConfigPreInit.emeraldChancesToSpawn, ConfigPreInit.emeraldMinYLevel, ConfigPreInit.emeraldMaxYLevel);
                this.runGenerator(this.genNetherGoldOre, world, random, chunkX, chunkZ, ConfigPreInit.goldChancesToSpawn, ConfigPreInit.goldMinYLevel, ConfigPreInit.goldMaxYLevel);
                this.runGenerator(this.genNetherIronOre, world, random, chunkX, chunkZ, ConfigPreInit.ironChancesToSpawn, ConfigPreInit.ironMinYLevel, ConfigPreInit.ironMaxYLevel);
                this.runGenerator(this.genNetherLapisOre, world, random, chunkX, chunkZ, ConfigPreInit.lapisChancesToSpawn, ConfigPreInit.lapisMinYLevel, ConfigPreInit.lapisMaxYLevel);
                this.runGenerator(this.genNetherRedstoneOre, world, random, chunkX, chunkZ, ConfigPreInit.redstoneChancesToSpawn, ConfigPreInit.redstoneMinYLevel, ConfigPreInit.redstoneMaxYLevel);

                break;
            case 1: //End

                break;
        }

    }

    private void runGenerator(WorldGenerator generator, World world, Random rand, int chunk_X, int chunk_Z, int chancesToSpawn, int minHeight, int maxHeight) {
        if (minHeight < 0 || maxHeight > 256 || minHeight > maxHeight)
            throw new IllegalArgumentException("Illegal Height Arguments for WorldGenerator");

        int heightDiff = maxHeight - minHeight + 1;
        for (int i = 0; i < chancesToSpawn; i++) {
            int x = chunk_X * 16 + rand.nextInt(16);
            int y = minHeight + rand.nextInt(heightDiff);
            int z = chunk_Z * 16 + rand.nextInt(16);
            generator.generate(world, rand, new BlockPos(x, y, z));
        }

    }

}
