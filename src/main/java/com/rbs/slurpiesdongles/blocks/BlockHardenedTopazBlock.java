package com.rbs.slurpiesdongles.blocks;

import com.rbs.slurpiesdongles.SlurpiesDongles;
import net.minecraft.block.Block;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.item.Item;
import net.minecraft.item.ItemBlock;

public class BlockHardenedTopazBlock extends Block {

    public BlockHardenedTopazBlock(String name) {

        super(Material.ROCK);
        this.setHarvestLevel("pickaxe", 2);
        this.setHardness(10.0F);
        this.setResistance(25.0F);
        this.setCreativeTab(SlurpiesDongles.creativeTab);
        this.setSoundType(SoundType.STONE);

        setUnlocalizedName(name);
        setRegistryName(name);
    }
    public Item createItemBlock() {
        return new ItemBlock(this).setRegistryName(getRegistryName());
    }
}
