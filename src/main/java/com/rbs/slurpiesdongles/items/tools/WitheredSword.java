package com.rbs.slurpiesdongles.items.tools;

import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.entity.LivingEntity;
import net.minecraft.inventory.EquipmentSlotType;
import net.minecraft.item.IItemTier;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.EffectInstance;
import net.minecraft.potion.Effects;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.StringTextComponent;
import net.minecraft.util.text.TextFormatting;
import net.minecraft.world.World;

import javax.annotation.Nullable;
import java.util.List;

public class WitheredSword extends CustomSword {
    public WitheredSword(IItemTier tier, int attackDamageIn, float attackSpeedIn, Properties builder, String name) {
        super(tier, attackDamageIn, attackSpeedIn, builder, name);
    }

    //This is to add a potion effect to a mob upon hitting them
    @Override
    public boolean hitEntity(ItemStack stack, LivingEntity target, LivingEntity attacker) {
        stack.damageItem(1, target, (p_220045_0_) -> {
            p_220045_0_.sendBreakAnimation(EquipmentSlotType.MAINHAND);
        });
        target.addPotionEffect(new EffectInstance(Effects.WITHER, 100, 1));//100 = 5 seconds of Wither

        return true;
    }
    @Override
    public void addInformation(ItemStack stack, @Nullable World worldIn, List<ITextComponent> tooltip, ITooltipFlag flagIn) {
        tooltip.add(new StringTextComponent(TextFormatting.YELLOW + "Upon hitting an enemy, a 5 second Wither debuff is applied to them"));
    }
}
