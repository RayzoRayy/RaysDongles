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
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

import javax.annotation.Nullable;
import java.util.List;

public class StrengthCharmTier2 extends CharmBase {
    public StrengthCharmTier2 (Properties properties, String name) {
        super(properties, name);
    }

    @Override
    public void onTick(ItemStack stack, PlayerEntity living) {

    }

    @Override
    public boolean onTick(ItemStack stack, PlayerEntity living, World world) {
        if (!this.canTick(stack)) {
            living.addPotionEffect(new EffectInstance(Effects.STRENGTH, 600, 1, false, false));
        }
        return false;
    }
    @Override
    public void addInformation(ItemStack stack, @Nullable World worldIn, List<ITextComponent> tooltip, ITooltipFlag flagIn) {
        tooltip.add(new StringTextComponent(TextFormatting.YELLOW + "Having this item in your inventory grants you Strength tier 2"));

    }
    public Rarity getRarity(ItemStack stack) {
        return stack.getCount() == 0 ? Rarity.EPIC : Rarity.EPIC;

    }
    @OnlyIn(Dist.CLIENT)
    public boolean hasEffect(ItemStack stack) {
        return true;
    }
}
