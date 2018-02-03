package com.rbs.slurpiesdongles.items;

import com.google.common.collect.Lists;
import com.rbs.slurpiesdongles.SlurpiesDongles;
import com.rbs.slurpiesdongles.init.Names;
import com.rbs.slurpiesdongles.init.SDBlocks;
import net.minecraft.block.Block;
import net.minecraft.block.state.IBlockState;
import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.init.Blocks;
import net.minecraft.item.ItemSeeds;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockAccess;
import net.minecraftforge.common.EnumPlantType;
import net.minecraftforge.common.IPlantable;

import java.util.List;

public class ItemStrawBerrrySeed extends ItemSeeds implements IPlantable {


    private final Block crops;
    private final Block soilBlockID;

    @Override
    public EnumPlantType getPlantType(IBlockAccess world, BlockPos pos) {
        return EnumPlantType.Crop;
    }

    @Override
    public IBlockState getPlant (IBlockAccess world, BlockPos pos) {
        return SDBlocks.strawberryCrop.getDefaultState();
    }

    public ItemStrawBerrrySeed(String name, Block crops, Block soil) {
        super(SDBlocks.strawberryCrop, Blocks.FARMLAND);

        this.crops = crops;
        this.soilBlockID = soil;

        setCreativeTab(SlurpiesDongles.creativeTab);

        setUnlocalizedName(name);
        setRegistryName(name);

    }
    public String getName() {
        return Names.strawberryseeds;
    }

    public String getFullName() {
        return getModId() + ":" + getName();
    }

    public String getModId() {
        return SlurpiesDongles.modId.toLowerCase();
    }

    public List<ModelResourceLocation> getVariants() {
        return Lists.newArrayList(new ModelResourceLocation(getFullName(), "inventory"));
    }
}

