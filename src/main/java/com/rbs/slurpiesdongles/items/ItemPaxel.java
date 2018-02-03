package com.rbs.slurpiesdongles.items;

import com.google.common.collect.Multimap;
import com.google.common.collect.Sets;
import com.rbs.slurpiesdongles.SlurpiesDongles;
import net.minecraft.block.Block;
import net.minecraft.block.BlockDirt;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.ai.attributes.AttributeModifier;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.init.SoundEvents;
import net.minecraft.inventory.EntityEquipmentSlot;
import net.minecraft.item.*;
import net.minecraft.util.EnumActionResult;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

import java.util.Set;

public class ItemPaxel extends net.minecraft.item.ItemPickaxe {

    private float speed;
    public ToolMaterial material;

    public ItemPaxel(String name, ToolMaterial materialIn) {
        super(materialIn);
        this.material = materialIn;
        this.setMaxDamage(material.getMaxUses());
        this.speed = 4.0F + material.getDamageVsEntity();
        this.setUnlocalizedName(name);
        this.setRegistryName(name);
        this.setCreativeTab(SlurpiesDongles.creativeTab);

    }

    @Override
    public float getStrVsBlock(ItemStack stack, IBlockState blockState)
    {
        return blockState.getBlock() != Blocks.BEDROCK ? efficiencyOnProperMaterial : 1.0F;
    }

    @Override
    public boolean canHarvestBlock(IBlockState state, ItemStack stack)
    {
        Block block = state.getBlock();

        if(block == Blocks.OBSIDIAN)
        {
            return toolMaterial.getHarvestLevel() == 3;
        }

        if(block == Blocks.DIAMOND_BLOCK || block == Blocks.DIAMOND_ORE)
        {
            return toolMaterial.getHarvestLevel() >= 2;
        }

        if(block == Blocks.GOLD_BLOCK || block == Blocks.GOLD_ORE)
        {
            return toolMaterial.getHarvestLevel() >= 2;
        }

        if(block == Blocks.IRON_BLOCK || block == Blocks.IRON_ORE)
        {
            return toolMaterial.getHarvestLevel() >= 1;
        }

        if(block == Blocks.LAPIS_BLOCK || block == Blocks.LAPIS_ORE)
        {
            return toolMaterial.getHarvestLevel() >= 1;
        }

        if(block == Blocks.REDSTONE_ORE || block == Blocks.LIT_REDSTONE_ORE)
        {
            return toolMaterial.getHarvestLevel() >= 2;
        }

        if(block == Blocks.ANVIL)
        {
            return toolMaterial.getHarvestLevel() >= 0;
        }

        if(block == Blocks.SNOW || block == Blocks.SNOW_LAYER)
        {
            return true;
        }

        if(state.getMaterial() == Material.ROCK)
        {
            return true;
        }

        return state.getMaterial() == Material.IRON;
    }

    public boolean hitEntity(ItemStack stack, EntityLivingBase target, EntityLivingBase attacker)
    {
        stack.damageItem(1, attacker);
        return true;
    }

    public boolean onBlockDestroyed(ItemStack stack, World worldIn, IBlockState state, BlockPos pos, EntityLivingBase entityLiving)
    {
        if ((double)state.getBlockHardness(worldIn, pos) != 0.0D)
        {
            stack.damageItem(1, entityLiving);
        }

        return true;
    }

    @SideOnly(Side.CLIENT)
    public boolean isFull3D()
    {
        return true;
    }

    public Multimap<String, AttributeModifier> getItemAttributeModifiers(EntityEquipmentSlot equipmentSlot)
    {
        Multimap<String, AttributeModifier> multimap = super.getItemAttributeModifiers(equipmentSlot);

        if (equipmentSlot == EntityEquipmentSlot.MAINHAND)
        {
            multimap.put(SharedMonsterAttributes.ATTACK_DAMAGE.getName(), new AttributeModifier(ATTACK_DAMAGE_MODIFIER, "Weapon modifier", 0.0D, 0));
            multimap.put(SharedMonsterAttributes.ATTACK_SPEED.getName(), new AttributeModifier(ATTACK_SPEED_MODIFIER, "Weapon modifier", (double)(this.speed - 4.0F), 0));
        }

        return multimap;
    }

    public EnumActionResult onItemUse(EntityPlayer player, World worldIn, BlockPos pos, EnumHand hand, EnumFacing facing, float hitX, float hitY, float hitZ)
    {
        ItemStack itemstack = player.getHeldItem(hand);

        if (!player.canPlayerEdit(pos.offset(facing), facing, itemstack))
        {
            return EnumActionResult.FAIL;
        }
        else
        {
            int hook = net.minecraftforge.event.ForgeEventFactory.onHoeUse(itemstack, player, worldIn, pos);
            if (hook != 0) return hook > 0 ? EnumActionResult.SUCCESS : EnumActionResult.FAIL;

            IBlockState iblockstate = worldIn.getBlockState(pos);
            Block block = iblockstate.getBlock();

            if (facing != EnumFacing.DOWN && worldIn.isAirBlock(pos.up()))
            {
                if (block == Blocks.GRASS || block == Blocks.GRASS_PATH)
                {
                    this.setBlock(itemstack, player, worldIn, pos, Blocks.FARMLAND.getDefaultState());
                    return EnumActionResult.SUCCESS;
                }

                if (block == Blocks.DIRT)
                {
                    switch ((BlockDirt.DirtType)iblockstate.getValue(BlockDirt.VARIANT))
                    {
                        case DIRT:
                            this.setBlock(itemstack, player, worldIn, pos, Blocks.FARMLAND.getDefaultState());
                            return EnumActionResult.SUCCESS;
                        case COARSE_DIRT:
                            this.setBlock(itemstack, player, worldIn, pos, Blocks.DIRT.getDefaultState().withProperty(BlockDirt.VARIANT, BlockDirt.DirtType.DIRT));
                            return EnumActionResult.SUCCESS;
                    }
                }
            }

            return EnumActionResult.PASS;
        }
    }

    protected void setBlock(ItemStack stack, EntityPlayer player, World worldIn, BlockPos pos, IBlockState state)
    {
        worldIn.playSound(player, pos, SoundEvents.ITEM_HOE_TILL, SoundCategory.BLOCKS, 1.0F, 1.0F);

        if (!worldIn.isRemote)
        {
            worldIn.setBlockState(pos, state, 11);
            stack.damageItem(1, player);
        }
    }
}
