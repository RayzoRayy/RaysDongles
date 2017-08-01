package com.rbs.slurpiesdongles.blocks;

import com.rbs.slurpiesdongles.SlurpiesDongles;
import com.rbs.slurpiesdongles.init.SDBlocks;
import com.rbs.slurpiesdongles.init.SDItems;
import net.minecraft.block.Block;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.MapColor;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.item.Item;
import net.minecraft.item.ItemBlock;
import net.minecraft.util.math.MathHelper;

import java.util.Random;

public class BlockCustomGlowstone extends Block {
    public BlockCustomGlowstone( String name) {
        super(Material.GLASS);
        this.setSoundType(SoundType.GLASS);

        this.setHardness(0.3F);
        this.setResistance(2.0F);
        this.setLightOpacity(16);
        this.setLightLevel(1.0F);
        this.setCreativeTab(SlurpiesDongles.creativeTab);

        setUnlocalizedName(name);
        setRegistryName(name);
    }
    public int quantityDroppedWithBonus(int fortune, Random random)
    {
        return MathHelper.clamp(this.quantityDropped(random) + random.nextInt(fortune + 1), 1, 4);
    }

    public int quantityDropped(Random random)
    {
        return 2 + random.nextInt(3);
    }

    {


    }

    public Item getItemDropped(IBlockState state, Random rand, int fortune) {
        return this == SDBlocks.blueGlowstone ? SDItems.blueGlowstoneDust : (this ==
                SDBlocks.grayGlowstone ? SDItems.grayGlowstoneDust : (this ==
                SDBlocks.greenGlowstone ? SDItems.greenGlowstoneDust : (this ==
                SDBlocks.orangeGlowstone ? SDItems.orangeGlowstoneDust : (this ==
                SDBlocks.pinkGlowstone ? SDItems.pinkGlowstoneDust: (this ==
                SDBlocks.purpleGlowstone ? SDItems.purpleGlowstoneDust : (this ==
                SDBlocks.redGlowstone ? SDItems.redGlowstoneDust : Item.getItemFromBlock(this)))))));
    }

    public MapColor getMapColor(IBlockState state)
    {
        return MapColor.SAND;
    }

    public Item createItemBlock() {
        return new ItemBlock(this).setRegistryName(getRegistryName());
    }

}
