package com.rbs.slurpiesdongles.food;

import com.rbs.slurpiesdongles.Reference;
import com.rbs.slurpiesdongles.init.ModFood;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.potion.Effects;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.EffectInstance;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.StringTextComponent;
import net.minecraft.util.text.TextFormatting;
import net.minecraft.world.World;

import javax.annotation.Nullable;
import java.util.List;

public class RawBeefSlice extends Item {
    public RawBeefSlice(Item.Properties p_i50045_1_, String name) {
        super(p_i50045_1_);

        this.setRegistryName(Reference.MODID, name);
    }
    @Override
    public void addInformation(ItemStack stack, @Nullable World worldIn, List<ITextComponent> tooltip, ITooltipFlag flagIn) {
        tooltip.add(new StringTextComponent(TextFormatting.YELLOW + "Perhaps you should cook this first...or don't"));
    }
}
