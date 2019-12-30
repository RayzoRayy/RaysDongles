package com.rbs.slurpiesdongles.items.charms;

import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Rarity;
import net.minecraft.potion.EffectInstance;
import net.minecraft.potion.Effects;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.StringTextComponent;
import net.minecraft.util.text.TextFormatting;
import net.minecraft.world.World;

import javax.annotation.Nullable;
import java.util.List;

public class StrengthCharm extends CharmBase {
    public StrengthCharm (Properties properties, String name) {
        super(properties, name);
    }

    @Override
    public void onTick(ItemStack stack, PlayerEntity living) {

    }

    @Override
    public boolean onTick(ItemStack stack, PlayerEntity living, World world) {
        if (!this.canTick(stack)) {
            living.addPotionEffect(new EffectInstance(Effects.STRENGTH, 600, 0, false, false));
        }
        return false;
    }
    @Override
    public void addInformation(ItemStack stack, @Nullable World worldIn, List<ITextComponent> tooltip, ITooltipFlag flagIn) {
        tooltip.add(new StringTextComponent(TextFormatting.YELLOW + "Having this item in your inventory grants you Strength"));
    }
    public Rarity getRarity(ItemStack stack) {
        return stack.getCount() == 0 ? Rarity.RARE : Rarity.RARE;

    }
}
