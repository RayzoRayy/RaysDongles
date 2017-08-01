package com.rbs.slurpiesdongles.blocks;

import com.rbs.slurpiesdongles.SlurpiesDongles;
import com.rbs.slurpiesdongles.init.SDBlocks;
import net.minecraft.block.Block;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemBlock;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;

import java.util.Random;

public class BlockNetherRedstoneOre extends Block {
    public BlockNetherRedstoneOre(String name) {
        super(Material.ROCK);
        this.setHardness(3.0F);
        this.setResistance(5.0F);
        this.setCreativeTab(SlurpiesDongles.creativeTab);
        this.setHarvestLevel("pickaxe", 2);
        this.setSoundType(SoundType.STONE);

        setUnlocalizedName(name);
        setRegistryName(name);
    }
    public Item getItemDropped(IBlockState state, Random random, int fortune){

        return this == SDBlocks.netherRedstoneOre ? Items.REDSTONE : Item.getItemFromBlock(this);
    }

    public void dropBlockAsItemWithChance(World world, BlockPos pos, IBlockState state, float chance, int fortune){

        super.dropBlockAsItemWithChance(world, pos, state, chance, fortune);
    }

    public int getExpDrop(IBlockAccess world, BlockPos pos, int fortune){

        IBlockState state = world.getBlockState(pos);
        Random random = world instanceof World ? ((World)world).rand : new Random();

        if (this.getItemDropped(state, random, fortune) != Item.getItemFromBlock(this)){

            int i = 0;

            if (this == SDBlocks.netherRedstoneOre)
            {
                i = MathHelper.getInt(random, 3, 7);
            }

            return i;
        }
        return 0;
    }

    public int getDamageValue(World world, BlockPos pos){

        return 0;


    }
    public int quantityDropped(Random random)
    {
        return this == SDBlocks.netherRedstoneOre ? 7 + random.nextInt(5) : 1;
    }

    public Item createItemBlock() {
        return new ItemBlock(this).setRegistryName(getRegistryName());
    }
}
