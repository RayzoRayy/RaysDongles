package com.rbs.slurpiesdongles.blocks;

import com.rbs.slurpiesdongles.SlurpiesDongles;
import net.minecraft.block.Block;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.item.Item;
import net.minecraft.item.ItemBlock;

public class BlockLignite extends Block {

    public BlockLignite(String name) {
        super(Material.ROCK);
        setCreativeTab(SlurpiesDongles.creativeTab);
        this.setHarvestLevel("pickaxe", 2);
        this.setSoundType(SoundType.STONE);
        this.setHardness(5.0F);
        this.setResistance(10.0F);

        setUnlocalizedName(name);
        setRegistryName(name);
    }

    public Item createItemBlock() {
        return new ItemBlock(this).setRegistryName(getRegistryName());
    }
}
