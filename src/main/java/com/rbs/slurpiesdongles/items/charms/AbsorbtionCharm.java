package com.rbs.slurpiesdongles.items.charms;

import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Rarity;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.StringTextComponent;
import net.minecraft.world.World;

import javax.annotation.Nullable;
import java.util.List;

public class AbsorbtionCharm extends CharmBase {
    public AbsorbtionCharm (String name, Item.Properties properties) {
        super(properties, name);
    }

    @Override
    public void onTick(ItemStack stack, PlayerEntity living) {

    }

    @Override
    public boolean onTick(ItemStack stack, PlayerEntity living, World world) {
        if (living instanceof PlayerEntity) {
            if (world.getGameTime() % 200 == 0 && ((PlayerEntity) living).getAbsorptionAmount() < ((PlayerEntity) living).getMaxHealth())//% number is how often the charm applies absorbtion, 20 = 1 second

                if (!this.canTick(stack)) {
                    living.setAbsorptionAmount(4.0F);
                }
        }
        return false;
    }
    @Override
    public void addInformation(ItemStack stack, @Nullable World worldIn, List<ITextComponent> tooltip, ITooltipFlag flagIn) {
        tooltip.add(new StringTextComponent("Having this item in your inventory grants you 2 Absorbtion hearts every 10 seconds. Please allow 10 seconds to apply your hearts when you first craft the item"));
    }
    public Rarity getRarity(ItemStack stack) {
        return stack.getCount() == 0 ? Rarity.RARE : Rarity.RARE;

    }
}
