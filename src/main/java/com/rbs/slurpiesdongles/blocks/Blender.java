package com.rbs.slurpiesdongles.blocks;

import com.rbs.slurpiesdongles.Reference;
import com.rbs.slurpiesdongles.init.ModBlocks;
import com.rbs.slurpiesdongles.init.ModItems;
import net.minecraft.block.Block;
import net.minecraft.item.Item;

/*public class Blender extends Block {
    public Blender(Block.Properties builder, String name) {
        super(builder);

        this.setRegistryName(Reference.MODID, name);

        ModBlocks.BLOCKS.add(this);
        ModItems.ITEMS.add(new ItemBlock(this, new Item.Properties().group(Reference.tabSlurpiesDongles)).setRegistryName(this.getRegistryName()));
    }
    public void onBlockAdded(World worldIn, BlockPos pos, IBlockState state)
    {
        this.setDefaultFacing(worldIn, pos, state);
    }

    private void setDefaultFacing(World worldIn, BlockPos pos, IBlockState state)
    {
        if (!worldIn.isRemote)
        {/*
            IBlockState iblockstate = worldIn.getBlockState(pos.north());
            IBlockState iblockstate1 = worldIn.getBlockState(pos.south());
            IBlockState iblockstate2 = worldIn.getBlockState(pos.west());
            IBlockState iblockstate3 = worldIn.getBlockState(pos.east());
            EnumFacing enumfacing = (EnumFacing)state.get(FACING);

            if (enumfacing == EnumFacing.NORTH && iblockstate.isFullCube() && !iblockstate1.isFullCube())
            {
                enumfacing = EnumFacing.SOUTH;
            }
            else if (enumfacing == EnumFacing.SOUTH && iblockstate1.isFullCube() && !iblockstate.isFullCube())
            {
                enumfacing = EnumFacing.NORTH;
            }
            else if (enumfacing == EnumFacing.WEST && iblockstate2.isFullCube() && !iblockstate3.isFullCube())
            {
                enumfacing = EnumFacing.EAST;
            }
            else if (enumfacing == EnumFacing.EAST && iblockstate3.isFullCube() && !iblockstate2.isFullCube())
            {
                enumfacing = EnumFacing.WEST;
            }

            worldIn.setBlockState(pos, state.with(FACING, enumfacing), 2);
        }
    }

    public IBlockState getStateForPlacement(World worldIn, BlockPos pos, EnumFacing facing, float hitX, float hitY, float hitZ, int meta, EntityLivingBase placer)
    {
        return this.getDefaultState().with(FACING, placer.getHorizontalFacing().getOpposite());
    }

    public EnumBlockRenderType getRenderType(IBlockState state)
    {
        return EnumBlockRenderType.MODEL;
    }

    public IBlockState getStateFromMeta(int meta)
    {
        EnumFacing enumfacing = EnumFacing.fromAngle(meta);

        if (enumfacing.getAxis() == EnumFacing.Axis.Y)
        {
            enumfacing = EnumFacing.NORTH;
        }

        return this.getDefaultState().with(FACING, enumfacing);
    }

    /**
     * Convert the BlockState into the correct metadata value
     */
    /*public int getMetaFromState(IBlockState state)
    {
        return ((EnumFacing)state.get(FACING)).getIndex();
    }

    /**
     * Returns the blockstate with the given rotation from the passed blockstate. If inapplicable, returns the passed
     * blockstate.
     */
   /* public IBlockState withRotation(IBlockState state, Rotation rot)
    {
        return state.with(FACING, rot.rotate((EnumFacing)state.get(FACING)));
    }

    /**
     * Returns the blockstate with the given mirror of the passed blockstate. If inapplicable, returns the passed
     * blockstate.
     */
    /*public IBlockState mirror(Mirror mirrorIn) {
        return this.getBlock().mirror((IBlockState) this, mirrorIn);
    }
    //public IBlockState withMirror(IBlockState state, Mirror mirrorIn)
    {
        //return state.with(mirrorIn.toRotation((EnumFacing)state.get(FACING)));
    }

    //protected BlockStateContainer createBlockState()
    //{
       // return new BlockStateContainer(this, new IProperty[] {FACING});
    //}

    @Override
    public boolean isFullCube(IBlockState state) {
        return false;
    }

    @Override
    public boolean isOpaqueCube(IBlockReader worldIn, BlockPos pos) {

        return false;
    }

    @Override
    public BlockRenderLayer getBlockLayer() {
        return BlockRenderLayer.TRANSLUCENT;
    }

    @Override
    public boolean onBlockEventReceived(World worldIn, BlockPos pos, IBlockState state, EntityPlayer playerIn, EnumHand hand, EnumFacing facing, float hitX, float hitY, float hitZ) {
        if (!worldIn.isRemote){


            if (playerIn.getHeldItem(hand).getItem() == Items.CARROT) { //Input of the item

                ItemStack CARROT_JUICE = new ItemStack(ModFood.CARROT_JUICE);//Output of the item

                playerIn.getHeldItem(hand).split(1);
                playerIn.addItemStackToInventory(CARROT_JUICE);
                return true;
            }

            if (playerIn.getHeldItem(hand).getItem() == Items.MELON_SLICE) {

                ItemStack MELON_JUICE = new ItemStack(ModFood.MELON_JUICE);

                playerIn.getHeldItem(hand).split(1);
                playerIn.addItemStackToInventory(MELON_JUICE);
                return true;
            }

            if (playerIn.getHeldItem(hand).getItem() == Items.APPLE) {

                ItemStack APPLE_JUICE = new ItemStack(ModFood.APPLE_JUICE);

                playerIn.getHeldItem(hand).split(1);
                playerIn.addItemStackToInventory(APPLE_JUICE);
                return true;
            }

            if (playerIn.getHeldItem(hand).getItem() == ModFood.SUGAR_COATED_LEMON) {

                ItemStack LEMONADE = new ItemStack(ModFood.LEMONADE);

                playerIn.getHeldItem(hand).split(1);
                playerIn.addItemStackToInventory(LEMONADE);
                return true;
            }

            if (playerIn.getHeldItem(hand).getItem() == ModFood.SUGAR_COATED_MELON) {

                ItemStack MELON_SLUSHIE = new ItemStack(ModFood.MELON_SLUSHIE);

                playerIn.getHeldItem(hand).split(1);
                playerIn.addItemStackToInventory(MELON_SLUSHIE);
                return true;
            }

            if (playerIn.getHeldItem(hand).getItem() == ModFood.ORANGE) {

                ItemStack ORANGE_JUICE = new ItemStack(ModFood.ORANGE_JUICE);

                playerIn.getHeldItem(hand).split(1);
                playerIn.addItemStackToInventory(ORANGE_JUICE);
                return true;
            }

            if (playerIn.getHeldItem(hand).getItem() == ModFood.SUGAR_COATED_APPLE) {

                ItemStack ORANGE_JUICE = new ItemStack(ModFood.APPLE_SLUSHIE);

                playerIn.getHeldItem(hand).split(1);
                playerIn.addItemStackToInventory(ORANGE_JUICE);
                return true;
            }

        }

        if (playerIn.getHeldItem(hand).getItem() == ModFood.SUGAR_COATED_ORANGE) {

            ItemStack ORANGE_JUICE = new ItemStack(ModFood.ORANGE_SLUSHIE);

            playerIn.getHeldItem(hand).split(1);
            playerIn.addItemStackToInventory(ORANGE_JUICE);
            return true;

        }

        if (playerIn.getHeldItem(hand).getItem() == ModFood.SUGAR_COATED_STRAWBERRY) {

            ItemStack ORANGE_JUICE = new ItemStack(ModFood.STRAWBERRY_SLUSHIE);

            playerIn.getHeldItem(hand).split(1);
            playerIn.addItemStackToInventory(ORANGE_JUICE);
            return true;

        }

        if (playerIn.getHeldItem(hand).getItem() == ModFood.STRAWBERRY) {

            ItemStack ORANGE_JUICE = new ItemStack(ModFood.STRAWBERRY_JUICE);

            playerIn.getHeldItem(hand).split(1);
            playerIn.addItemStackToInventory(ORANGE_JUICE);
            return true;

        }

        if (playerIn.getHeldItem(hand).getItem() == ModFood.TOMATO) {

            ItemStack TOMATO_JUICE = new ItemStack(ModFood.TOMATO_JUICE);

            playerIn.getHeldItem(hand).split(1);
            playerIn.addItemStackToInventory(TOMATO_JUICE);
            return true;
        }



        return false;
    }
}*/
