package com.rbs.slurpiesdongles.items.charms;

import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Rarity;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.StringTextComponent;
import net.minecraft.util.text.TextFormatting;
import net.minecraft.world.World;

import javax.annotation.Nullable;
import java.util.List;

public class RegenerationCharm extends CharmBase {
    public RegenerationCharm (Properties properties, String name) {
        super(properties, name);
    }

    @Override
    public void onTick(ItemStack stack, PlayerEntity living) {

    }

    @Override
    public boolean onTick(ItemStack stack, PlayerEntity living, World world) {
        if (living instanceof PlayerEntity) {
            if (world.getGameTime() % 45 == 0 && ((PlayerEntity) living).getHealth() < ((PlayerEntity) living).getMaxHealth())//% number is how often the charm heals, 20 = 1 second

                if (!this.canTick(stack)) {
                    living.heal(0.5F);
                }
        }
        return false;
    }
    @Override
    public void addInformation(ItemStack stack, @Nullable World worldIn, List<ITextComponent> tooltip, ITooltipFlag flagIn) {
        tooltip.add(new StringTextComponent( TextFormatting.YELLOW + "Having this item in your inventory grants you Health Regeneration"));
    }
    public Rarity getRarity(ItemStack stack) {
        return stack.getCount() == 0 ? Rarity.RARE : Rarity.RARE;

    }
}