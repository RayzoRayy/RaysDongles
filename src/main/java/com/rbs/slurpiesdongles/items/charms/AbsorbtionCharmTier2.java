package com.rbs.slurpiesdongles.items.charms;

import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Rarity;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.StringTextComponent;
import net.minecraft.world.World;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

import javax.annotation.Nullable;
import java.util.List;

public class AbsorbtionCharmTier2 extends CharmBase {
    public AbsorbtionCharmTier2 (String name, Item.Properties properties) {
        super(properties, name);
    }

    @Override
    public void onTick(ItemStack stack, PlayerEntity living) {

    }

    @Override
    public boolean onTick(ItemStack stack, PlayerEntity living, World world) {
        if (living instanceof PlayerEntity) {
            if (world.getGameTime() % 150 == 0 && ((PlayerEntity) living).getAbsorptionAmount() < ((PlayerEntity) living).getMaxHealth())//% number is how often the charm applies absorbtion, 20 = 1 second

                if (!this.canTick(stack)) {
                    living.setAbsorptionAmount(8.0F);
                }
        }
        return false;
    }
    @Override
    public void addInformation(ItemStack stack, @Nullable World worldIn, List<ITextComponent> tooltip, ITooltipFlag flagIn) {
        tooltip.add(new StringTextComponent("Having this item in your inventory grants you 4 Absorbtion hearts every 6 seconds. Please allow 6 seconds to apply your hearts when you first craft the item"));
    }
    public Rarity getRarity(ItemStack stack) {
        return stack.getCount() == 0 ? Rarity.EPIC : Rarity.EPIC;

    }
    @OnlyIn(Dist.CLIENT)
    public boolean hasEffect(ItemStack stack) {
        return true;
    }
}

