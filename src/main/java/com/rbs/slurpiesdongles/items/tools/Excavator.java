package com.rbs.slurpiesdongles.items.tools;

import com.rbs.slurpiesdongles.Reference;
import com.rbs.slurpiesdongles.handlers.SDTool;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.IItemTier;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ShovelItem;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.RayTraceContext;
import net.minecraft.util.math.RayTraceResult;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.StringTextComponent;
import net.minecraft.util.text.TextFormatting;
import net.minecraft.world.World;
import net.minecraftforge.common.ToolType;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import java.util.List;

public class Excavator extends ShovelItem implements SDTool {

    public Excavator(IItemTier tier, int attackDamageIn, float attackSpeedIn, Item.Properties builder, String name) {
        super(tier, attackDamageIn, attackSpeedIn, builder);

        this.setRegistryName(Reference.MODID, name);
    }
    @Nonnull
    @Override
    public ToolType getSDToolClass() {
        return ToolType.SHOVEL;
    }

    @Nullable
    @Override
    public RayTraceResult rayTraceBlocks(World world, PlayerEntity player) {
        return rayTrace(world, player, RayTraceContext.FluidMode.NONE);
    }


    @Override
    public boolean onBlockStartBreak(ItemStack itemstack, BlockPos pos, PlayerEntity player) {
        //itemstack.attemptDamageItem(0, ToolHelper.random, null);
        return SDTool.BreakHandler.onBlockStartBreak(itemstack, pos, player);
    }

    @Override
    public void addInformation(ItemStack stack, @Nullable World worldIn, List<ITextComponent> tooltip, ITooltipFlag flagIn) {
        tooltip.add(new StringTextComponent(TextFormatting.YELLOW  + "Excavator that mines in a 3x3x1 from the block you mine"));
    }
}
