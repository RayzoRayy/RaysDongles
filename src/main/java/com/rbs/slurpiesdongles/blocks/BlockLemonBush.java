package com.rbs.slurpiesdongles.blocks;

import com.rbs.slurpiesdongles.SlurpiesDongles;
import com.rbs.slurpiesdongles.init.SDFood;
import net.minecraft.block.Block;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.MapColor;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.item.Item;
import net.minecraft.item.ItemBlock;
import net.minecraft.util.math.MathHelper;

import java.util.Random;

public class BlockLemonBush extends Block {
    public BlockLemonBush(String name) {
        super(Material.LEAVES);
        this.setHardness(0.3F);
        this.setCreativeTab(SlurpiesDongles.creativeTab);
        this.setSoundType(SoundType.PLANT);

        setUnlocalizedName(name);
        setRegistryName(name);
    }

    public int quantityDroppedWithBonus(int fortune, Random random) {
        return MathHelper.clamp(this.quantityDropped(random) + random.nextInt(fortune + 1), 1, 4);
    }

    public int quantityDropped(Random random) {
        return 2 + random.nextInt(3);
    }

    {


    }

    public Item getItemDropped(IBlockState state, Random rand, int fortune) {
        return SDFood.lemon;
    }

    protected Item getCrop() {
        return SDFood.lemon;
    }

    public MapColor getMapColor(IBlockState state) {
        return MapColor.SAND;
    }

    public Item createItemBlock() {
        return new ItemBlock(this).setRegistryName(getRegistryName());

    }
}
