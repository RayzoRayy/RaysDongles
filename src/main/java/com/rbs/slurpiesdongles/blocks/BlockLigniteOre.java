package com.rbs.slurpiesdongles.blocks;

import com.rbs.slurpiesdongles.SlurpiesDongles;
import com.rbs.slurpiesdongles.init.SDBlocks;
import com.rbs.slurpiesdongles.init.SDItems;
import net.minecraft.block.Block;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.item.Item;
import net.minecraft.item.ItemBlock;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;

import java.util.Random;

public class BlockLigniteOre extends Block {
    public BlockLigniteOre(String name) {
        super(Material.ROCK);
        this.setHarvestLevel("pickaxe", 2);
        this.setHardness(5.0F);
        this.setResistance(10.0F);
        this.setCreativeTab(SlurpiesDongles.creativeTab);
        this.setSoundType(SoundType.STONE);

        setUnlocalizedName(name);
        setRegistryName(name);
    }

    public Item getItemDropped(IBlockState state, Random random, int fortune) {

        return this == SDBlocks.oreLignite ? SDItems.lignite : Item.getItemFromBlock(this);
    }

    public int quantityDroppedWithBonus(int fortune, Random random) {

        if (fortune > 0 && Item.getItemFromBlock(this) != this.getItemDropped((IBlockState) this.getBlockState().getValidStates().iterator().next(), random, fortune)) {

            int i = random.nextInt(fortune + 2) - 1;

            if (i < 0) {
                i = 0;
            }

            return this.quantityDropped(random) * (i + 1);
        } else {

            return this.quantityDropped(random);
        }
    }

    public void dropBlockAsItemWithChance(World world, BlockPos pos, IBlockState state, float chance, int fortune) {

        super.dropBlockAsItemWithChance(world, pos, state, chance, fortune);
    }

    public int getExpDrop(IBlockAccess world, BlockPos pos, int fortune) {

        IBlockState state = world.getBlockState(pos);
        Random random = world instanceof World ? ((World) world).rand : new Random();

        if (this.getItemDropped(state, random, fortune) != Item.getItemFromBlock(this)) {

            int i = 0;

            if (this == SDBlocks.oreLignite) {
                i = MathHelper.getInt(random, 3, 7);
            }

            return i;
        }
        return 0;
    }

    public int getDamageValue(World world, BlockPos pos) {

        return 0;
    }

    public Item createItemBlock() {
        return new ItemBlock(this).setRegistryName(getRegistryName());
    }
}
