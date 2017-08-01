package com.rbs.slurpiesdongles.world;

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
        //Number after .getDefaultState = veinsize? I think...

        this.genLigniteOre = new WorldGenMinable(SDBlocks.oreLignite.getDefaultState(), 5, BlockMatcher.forBlock(Blocks.STONE));
        this.genSapphireOre = new WorldGenMinable(SDBlocks.oreSapphire.getDefaultState(), 5, BlockMatcher.forBlock(Blocks.STONE));
        this.genRubyOre = new WorldGenMinable(SDBlocks.oreRuby.getDefaultState(), 4, BlockMatcher.forBlock(Blocks.STONE));
        this.genTopazOre = new WorldGenMinable(SDBlocks.oreTopaz.getDefaultState(), 3, BlockMatcher.forBlock(Blocks.STONE));
        this.genLemonBush = new WorldGenMinable(SDBlocks.lemonBush.getDefaultState(), 7, BlockMatcher.forBlock(Blocks.GRASS));
        this.genOrangeBush = new WorldGenMinable(SDBlocks.orangeBush.getDefaultState(), 7, BlockMatcher.forBlock(Blocks.GRASS));
        //Nether
        this.genNetherCoalOre = new WorldGenMinable(SDBlocks.netherCoalOre.getDefaultState(), 10, BlockMatcher.forBlock(Blocks.NETHERRACK));
        this.genNetherDiamondOre = new WorldGenMinable(SDBlocks.netherDiamondOre.getDefaultState(), 4, BlockMatcher.forBlock(Blocks.NETHERRACK));
        this.genNetherEmeraldOre= new WorldGenMinable(SDBlocks.netherEmeraldOre.getDefaultState(), 3, BlockMatcher.forBlock(Blocks.NETHERRACK));
        this.genNetherGoldOre = new WorldGenMinable(SDBlocks.netherGoldOre.getDefaultState(), 6, BlockMatcher.forBlock(Blocks.NETHERRACK));
        this.genNetherIronOre = new WorldGenMinable(SDBlocks.netherIronOre.getDefaultState(), 8, BlockMatcher.forBlock(Blocks.NETHERRACK));
        this.genNetherLapisOre = new WorldGenMinable(SDBlocks.netherLapisOre.getDefaultState(), 4, BlockMatcher.forBlock(Blocks.NETHERRACK));
        this.genNetherRedstoneOre = new WorldGenMinable(SDBlocks.netherRedstoneOre.getDefaultState(), 5, BlockMatcher.forBlock(Blocks.NETHERRACK));

    }

    @Override
    public void generate(Random random, int chunkX, int chunkZ, World world, IChunkGenerator chunkGenerator, IChunkProvider chunkProvider) {
       //Numbers 1st number = chance to spawn per chunk, 2nd number = min Y level, 3rd number = max Y level

        switch (world.provider.getDimension()) {
            case 0: //OverWorld
                this.runGenerator(this.genLigniteOre, world, random, chunkX, chunkZ, 6, 1, 32);
                this.runGenerator(this.genSapphireOre, world, random, chunkX, chunkZ, 5, 1, 48);
                this.runGenerator(this.genRubyOre, world, random, chunkX, chunkZ, 3, 1, 32);
                this.runGenerator(this.genTopazOre, world, random, chunkX, chunkZ, 4, 1, 16);
                this.runGenerator(this.genLemonBush, world, random, chunkX, chunkZ, 1, 60, 90);
                this.runGenerator(this.genOrangeBush, world, random, chunkX, chunkZ, 1, 60, 90);


                break;
            case -1: //Nether
                this.runGenerator(this.genNetherCoalOre, world, random, chunkX, chunkZ, 10, 30, 145);
                this.runGenerator(this.genNetherDiamondOre, world, random, chunkX, chunkZ, 1, 30, 75);
                this.runGenerator(this.genNetherEmeraldOre, world, random, chunkX, chunkZ, 1, 30, 45);
                this.runGenerator(this.genNetherGoldOre, world, random, chunkX, chunkZ, 7, 30, 85);
                this.runGenerator(this.genNetherIronOre, world, random, chunkX, chunkZ, 10, 30, 135);
                this.runGenerator(this.genNetherLapisOre, world, random, chunkX, chunkZ, 5, 30, 77);
                this.runGenerator(this.genNetherRedstoneOre, world, random, chunkX, chunkZ, 6, 30, 62);

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
