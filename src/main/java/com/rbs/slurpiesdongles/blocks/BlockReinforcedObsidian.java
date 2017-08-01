package com.rbs.slurpiesdongles.blocks;

import com.rbs.slurpiesdongles.SlurpiesDongles;
import jdk.nashorn.internal.ir.Block;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.item.Item;
import net.minecraft.item.ItemBlock;

public class BlockReinforcedObsidian extends net.minecraft.block.Block {
    public BlockReinforcedObsidian(String name) {
        super(Material.ROCK);
        this.setCreativeTab(SlurpiesDongles.creativeTab);
        this.setHarvestLevel("pickaxe", 3);
        this.setSoundType(SoundType.STONE);
        this.setHardness(100.0F);
        this.setResistance(4000.0F);

        setUnlocalizedName(name);
        setRegistryName(name);
    }
    public Item createItemBlock() {
        return new ItemBlock(this).setRegistryName(getRegistryName());
    }
}
