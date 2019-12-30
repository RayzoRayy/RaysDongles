package com.rbs.slurpiesdongles.items.tools;

import com.rbs.slurpiesdongles.Reference;
import com.rbs.slurpiesdongles.helpers.HarvestLevelHelper;
import com.rbs.slurpiesdongles.init.ModTools;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.item.IItemTier;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.PickaxeItem;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.StringTextComponent;
import net.minecraft.util.text.TextFormatting;
import net.minecraft.world.World;

import javax.annotation.Nullable;
import java.util.List;

public class VMPick extends PickaxeItem {
    public VMPick(String name, IItemTier tier, int attackDamageIn, float attackSpeedIn, Item.Properties builder, HarvestLevelHelper resource) {
        super(tier, attackDamageIn, attackSpeedIn, builder);

        this.setRegistryName(Reference.MODID, name);
    }
    @Override
    public void addInformation(ItemStack stack, @Nullable World worldIn, List<ITextComponent> tooltip, ITooltipFlag flagIn) {
        tooltip.add(new StringTextComponent(TextFormatting.YELLOW + "This pick was made for use with the mod VeinMiner, so you can veinmine for a while before needing another pick"));
    }
}
