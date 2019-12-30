package com.rbs.slurpiesdongles.food;

import com.rbs.slurpiesdongles.Reference;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.util.*;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ActionResultType;
import net.minecraft.util.Hand;
import net.minecraft.util.SoundEvents;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.StringTextComponent;
import net.minecraft.util.text.TextFormatting;
import net.minecraft.world.World;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import java.util.List;

public class BigSandwich extends Item {

    public BigSandwich(Properties properties, String name) {
        super(properties);

    this.setRegistryName(Reference.MODID, name);
    }

    /**
     * returns the action that specifies what animation to play when the items
     * is being used
     */
    @Nonnull
    @Override
    public ItemStack onItemUseFinish(@Nonnull ItemStack stack, @Nonnull World world, LivingEntity entityLiving) {
        if (!(entityLiving instanceof PlayerEntity))
            return stack;

        PlayerEntity player = (PlayerEntity) entityLiving;

        stack.shrink(1);
        player.getFoodStats().addStats(20, 1.0F);
        world.playSound((PlayerEntity) entityLiving, player.getPosition(), SoundEvents.ENTITY_PLAYER_BURP, SoundCategory.PLAYERS, 1.0F, world.rand.nextFloat() * 0.1F + 0.9F);
        return stack;
    }






    /**
     * Called whenever this item is equipped and the right mouse button is
     * pressed. Args: itemStack, world, entityPlayer
     */
    @Nonnull
    @Override
    public ActionResult<ItemStack> onItemRightClick(World world, PlayerEntity player, @Nonnull Hand hand) {
        ItemStack stack = player.getHeldItem(hand);

        if (player.canEat(false)) {
            player.setActiveHand(hand);
            return new ActionResult<>(ActionResultType.SUCCESS, stack);
        }

        return new ActionResult<>(ActionResultType.FAIL, stack);
    }

    @Override
    public void addInformation(ItemStack stack, @Nullable World worldIn, List<ITextComponent> tooltip, ITooltipFlag flagIn) {
        tooltip.add(new StringTextComponent(TextFormatting.YELLOW + "This is a big sandwich, heals alot, but takes twice as long to eat"));
    }

    /**
     * How long it takes to use or consume an item
     */
    @Override
    public int getUseDuration(ItemStack par1ItemStack) {
        return 64;
    }
}
