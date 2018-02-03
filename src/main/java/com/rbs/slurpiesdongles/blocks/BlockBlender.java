package com.rbs.slurpiesdongles.blocks;

import com.rbs.slurpiesdongles.SlurpiesDongles;
import com.rbs.slurpiesdongles.init.SDFood;
import net.minecraft.block.Block;
import net.minecraft.block.BlockHorizontal;
import net.minecraft.block.material.Material;
import net.minecraft.block.properties.IProperty;
import net.minecraft.block.properties.PropertyDirection;
import net.minecraft.block.state.BlockStateContainer;
import net.minecraft.block.state.IBlockState;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemStack;
import net.minecraft.util.*;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

import javax.annotation.Nullable;
import java.util.List;

/**
 * Created by patrick on 30/07/2017.
 */
public class BlockBlender extends Block {

    public static final PropertyDirection FACING = BlockHorizontal.FACING;

    public BlockBlender(String name) {
        super(Material.GLASS);
        this.setUnlocalizedName(name);
        this.setRegistryName(name);
        this.setCreativeTab(SlurpiesDongles.creativeTab);
    }

    public void onBlockAdded(World worldIn, BlockPos pos, IBlockState state)
    {
        this.setDefaultFacing(worldIn, pos, state);
    }

    private void setDefaultFacing(World worldIn, BlockPos pos, IBlockState state)
    {
        if (!worldIn.isRemote)
        {
            IBlockState iblockstate = worldIn.getBlockState(pos.north());
            IBlockState iblockstate1 = worldIn.getBlockState(pos.south());
            IBlockState iblockstate2 = worldIn.getBlockState(pos.west());
            IBlockState iblockstate3 = worldIn.getBlockState(pos.east());
            EnumFacing enumfacing = (EnumFacing)state.getValue(FACING);

            if (enumfacing == EnumFacing.NORTH && iblockstate.isFullBlock() && !iblockstate1.isFullBlock())
            {
                enumfacing = EnumFacing.SOUTH;
            }
            else if (enumfacing == EnumFacing.SOUTH && iblockstate1.isFullBlock() && !iblockstate.isFullBlock())
            {
                enumfacing = EnumFacing.NORTH;
            }
            else if (enumfacing == EnumFacing.WEST && iblockstate2.isFullBlock() && !iblockstate3.isFullBlock())
            {
                enumfacing = EnumFacing.EAST;
            }
            else if (enumfacing == EnumFacing.EAST && iblockstate3.isFullBlock() && !iblockstate2.isFullBlock())
            {
                enumfacing = EnumFacing.WEST;
            }

            worldIn.setBlockState(pos, state.withProperty(FACING, enumfacing), 2);
        }
    }

    public IBlockState getStateForPlacement(World worldIn, BlockPos pos, EnumFacing facing, float hitX, float hitY, float hitZ, int meta, EntityLivingBase placer)
    {
        return this.getDefaultState().withProperty(FACING, placer.getHorizontalFacing().getOpposite());
    }

    public EnumBlockRenderType getRenderType(IBlockState state)
    {
        return EnumBlockRenderType.MODEL;
    }

    public IBlockState getStateFromMeta(int meta)
    {
        EnumFacing enumfacing = EnumFacing.getFront(meta);

        if (enumfacing.getAxis() == EnumFacing.Axis.Y)
        {
            enumfacing = EnumFacing.NORTH;
        }

        return this.getDefaultState().withProperty(FACING, enumfacing);
    }

    /**
     * Convert the BlockState into the correct metadata value
     */
    public int getMetaFromState(IBlockState state)
    {
        return ((EnumFacing)state.getValue(FACING)).getIndex();
    }

    /**
     * Returns the blockstate with the given rotation from the passed blockstate. If inapplicable, returns the passed
     * blockstate.
     */
    public IBlockState withRotation(IBlockState state, Rotation rot)
    {
        return state.withProperty(FACING, rot.rotate((EnumFacing)state.getValue(FACING)));
    }

    /**
     * Returns the blockstate with the given mirror of the passed blockstate. If inapplicable, returns the passed
     * blockstate.
     */
    public IBlockState withMirror(IBlockState state, Mirror mirrorIn)
    {
        return state.withRotation(mirrorIn.toRotation((EnumFacing)state.getValue(FACING)));
    }

    protected BlockStateContainer createBlockState()
    {
        return new BlockStateContainer(this, new IProperty[] {FACING});
    }

    @Override
    public boolean isFullCube(IBlockState state) {
        return false;
    }

    @Override
    public boolean isOpaqueCube(IBlockState state) {
        return false;
    }

    @Override
    public BlockRenderLayer getBlockLayer() {
        return BlockRenderLayer.TRANSLUCENT;
    }

    @Override
    public boolean onBlockActivated(World worldIn, BlockPos pos, IBlockState state, EntityPlayer playerIn, EnumHand hand, EnumFacing facing, float hitX, float hitY, float hitZ) {
        if (!worldIn.isRemote){


        if (playerIn.getHeldItem(hand).getItem() == Items.CARROT) { //Input of the item

            ItemStack CARROT_JUICE = new ItemStack(SDFood.carrotJuice);//Output of the item

            playerIn.getHeldItem(hand).splitStack(1);
            playerIn.addItemStackToInventory(CARROT_JUICE);
            return true;
        }

        if (playerIn.getHeldItem(hand).getItem() == Items.MELON) {

            ItemStack MELON_JUICE = new ItemStack(SDFood.melonJuice);

            playerIn.getHeldItem(hand).splitStack(1);
            playerIn.addItemStackToInventory(MELON_JUICE);
            return true;
        }

        if (playerIn.getHeldItem(hand).getItem() == Items.APPLE) {

            ItemStack APPLE_JUICE = new ItemStack(SDFood.appleJuice);

            playerIn.getHeldItem(hand).splitStack(1);
            playerIn.addItemStackToInventory(APPLE_JUICE);
            return true;
        }

            if (playerIn.getHeldItem(hand).getItem() == SDFood.sugarcoatedLemon) {

                ItemStack LEMONADE = new ItemStack(SDFood.lemonade);

                playerIn.getHeldItem(hand).splitStack(1);
                playerIn.addItemStackToInventory(LEMONADE);
                return true;
            }

            if (playerIn.getHeldItem(hand).getItem() == SDFood.sugarcoatedMelon) {

                ItemStack MELON_SLUSHIE = new ItemStack(SDFood.melonSlushie);

                playerIn.getHeldItem(hand).splitStack(1);
                playerIn.addItemStackToInventory(MELON_SLUSHIE);
                return true;
            }

            if (playerIn.getHeldItem(hand).getItem() == SDFood.orange) {

                ItemStack ORANGE_JUICE = new ItemStack(SDFood.orangeJuice);

                playerIn.getHeldItem(hand).splitStack(1);
                playerIn.addItemStackToInventory(ORANGE_JUICE);
                return true;
            }

            if (playerIn.getHeldItem(hand).getItem() == SDFood.sugarcoatedApple) {

                ItemStack ORANGE_JUICE = new ItemStack(SDFood.appleSlushie);

                playerIn.getHeldItem(hand).splitStack(1);
                playerIn.addItemStackToInventory(ORANGE_JUICE);
                return true;
            }

        }

        if (playerIn.getHeldItem(hand).getItem() == SDFood.sugarcoatedOrange) {

            ItemStack ORANGE_JUICE = new ItemStack(SDFood.orangeSlushie);

            playerIn.getHeldItem(hand).splitStack(1);
            playerIn.addItemStackToInventory(ORANGE_JUICE);
            return true;

        }

        if (playerIn.getHeldItem(hand).getItem() == SDFood.sugarcoatedStrawberry) {

            ItemStack ORANGE_JUICE = new ItemStack(SDFood.strawberrySlushie);

            playerIn.getHeldItem(hand).splitStack(1);
            playerIn.addItemStackToInventory(ORANGE_JUICE);
            return true;

        }

        if (playerIn.getHeldItem(hand).getItem() == SDFood.strawBerry) {

            ItemStack ORANGE_JUICE = new ItemStack(SDFood.strawberryJuice);

            playerIn.getHeldItem(hand).splitStack(1);
            playerIn.addItemStackToInventory(ORANGE_JUICE);
            return true;

        }



        return false;
    }

    @SideOnly(Side.CLIENT)
    public void addInformation(ItemStack stack, @Nullable World worldIn, List<String> tooltip, ITooltipFlag flagIn) {
        tooltip.add("Place blender anywhere you want, & right click with the food in your hand to blend it");
    }

    public Item createItemBlock() {
        return new ItemBlock(this).setRegistryName(getRegistryName());
    }

}
