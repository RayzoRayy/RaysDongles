package com.rbs.slurpiesdongles.blocks;

import com.google.common.collect.Lists;
import com.rbs.slurpiesdongles.SlurpiesDongles;
import com.rbs.slurpiesdongles.init.Names;
import com.rbs.slurpiesdongles.init.SDFood;
import com.rbs.slurpiesdongles.init.SDItems;
import net.minecraft.block.BlockCrops;
import net.minecraft.block.state.IBlockState;
import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.item.Item;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemStack;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockAccess;
import net.minecraftforge.common.EnumPlantType;

import java.util.List;
import java.util.Random;

/**
 * Created by Consular on 7/20/2017.
 */
public class BlockCornCrop extends BlockCrops {

    public BlockCornCrop(String name) {

        setUnlocalizedName(name);
        setRegistryName(name);
    }

    public Item createItemBlock() {
        return new ItemBlock(this).setRegistryName(getRegistryName());
    }

    @Override
    public List<ItemStack> getDrops(IBlockAccess world, BlockPos pos, IBlockState state, int fortune) {

        List<ItemStack> list = Lists.newArrayList(new ItemStack(getSeed()));

        int age = getAge(state);
        Random rand = SlurpiesDongles.instance.random;

        if (age >= 7) {
            //Seeds
            for (int i = 0; i < 1 + fortune; ++i) {
                if (rand.nextInt(15) <= age) {
                    list.add(new ItemStack(getSeed()));
                }
            }
            //Corn
            for (int i = 0; i < 2 + fortune + rand.nextInt(3); ++i) {
                list.add(new ItemStack(getCrop()));
            }
        }
        return list;
    }

    @Override
    protected Item getSeed() {
        return SDItems.cornSeed;
    }

    @Override
    protected Item getCrop() {
        return SDFood.corn;
    }

    @Override
    public EnumPlantType getPlantType(IBlockAccess world, BlockPos pos) {

        return EnumPlantType.Crop;

    }

    public List<ModelResourceLocation> getVariants() {

        List<ModelResourceLocation> list = Lists.newArrayList();
        for (int i = 0; i < 4; ++i) {
            list.add(new ModelResourceLocation(getFullName() + i, "inventory"));
        }
        return list;
    }

    public String getFullName() {
        return getModID() + ":" + getName();
    }

    public String getName() {
        return Names.corncrop;
    }

    public String getModID() {
        return SlurpiesDongles.modId.toLowerCase();

    }
}
