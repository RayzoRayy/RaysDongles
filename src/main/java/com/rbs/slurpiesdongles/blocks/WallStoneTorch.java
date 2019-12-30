package com.rbs.slurpiesdongles.blocks;

import com.rbs.slurpiesdongles.Reference;
import com.rbs.slurpiesdongles.init.ModBlocks;
import com.rbs.slurpiesdongles.init.ModItems;
import net.minecraft.block.SoundType;
import net.minecraft.block.WallTorchBlock;
import net.minecraft.item.BlockItem;
import net.minecraft.item.Item;

public class WallStoneTorch extends WallTorchBlock {
    public WallStoneTorch(Properties p_i48298_1_, SoundType type, String name) {
        super(p_i48298_1_);

        this.setRegistryName(Reference.MODID, name);
    }
}
