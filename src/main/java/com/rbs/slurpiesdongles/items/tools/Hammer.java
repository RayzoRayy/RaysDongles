package com.rbs.slurpiesdongles.items.tools;

import com.rbs.slurpiesdongles.Reference;
import com.rbs.slurpiesdongles.handlers.SDTool;
import com.rbs.slurpiesdongles.helpers.ToolHelper;
import com.rbs.slurpiesdongles.init.ModTools;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.IItemTier;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.PickaxeItem;
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

/*
Source code for this class originally belongs too Neurodr0me, and can be found here: https://github.com/astradamus/PracticalTools
Source code ALSO belongs to SilentChaos512, his code can be found here: https://github.com/SilentChaos512/Silent-Gear
Fixing up the class credit goes too: TwiistsGaming, his page can be found here: https://www.curseforge.com/members/twiistsgaming/projects
Credit can also be found on the Contributors page of my wiki which is here: https://github.com/RedBullSlurpie/SlurpiesDongles/wiki/Contributors
 */

public class Hammer extends PickaxeItem implements SDTool {

    public Hammer(IItemTier tier, int attackDamageIn, float attackSpeedIn, Item.Properties builder, String name) {
        super(tier, attackDamageIn, attackSpeedIn, builder);

        this.setRegistryName(Reference.MODID, name);
    }
    @Nonnull
    @Override
    public ToolType getSDToolClass() {
        return ToolType.PICKAXE;
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
        tooltip.add(new StringTextComponent(TextFormatting.YELLOW  + "Hammer that mines in a 3x3x1 from the block you mine"));
    }
}
