package com.rbs.slurpiesdongles.blocks;

import com.rbs.slurpiesdongles.SlurpiesDongles;
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

public class BlockNetherIronOre extends Block{
    public BlockNetherIronOre(String name) {
        super(Material.ROCK);
        this.setHarvestLevel("pickaxe", 1);
        this.setHardness(3.0F);
        this.setResistance(5.0F);
        this.setCreativeTab(SlurpiesDongles.creativeTab);
        this.setSoundType(SoundType.STONE);

        setUnlocalizedName(name);
        setRegistryName(name);
    }
    public Item getItemDropped(IBlockState state, Random random, int fortune){

        return this == Blocks.IRON_ORE ? Items.IRON_INGOT : Item.getItemFromBlock(this);
    }

    public void dropBlockAsItemWithChance(World world, BlockPos pos, IBlockState state, float chance, int fortune){

        super.dropBlockAsItemWithChance(world, pos, state, chance, fortune);
    }

    public int getExpDrop(IBlockAccess world, BlockPos pos, int fortune){

        IBlockState state = world.getBlockState(pos);
        Random random = world instanceof World ? ((World)world).rand : new Random();

        if (this.getItemDropped(state, random, fortune) != Item.getItemFromBlock(this)){

            int i = 0;

            if (this == Blocks.IRON_ORE)
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
    public Item createItemBlock() {
        return new ItemBlock(this).setRegistryName(getRegistryName());
    }
}
