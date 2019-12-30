package com.rbs.slurpiesdongles.food;

import com.rbs.slurpiesdongles.Reference;
import com.rbs.slurpiesdongles.init.ModFood;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ActionResult;
import net.minecraft.util.ActionResultType;
import net.minecraft.util.Hand;
import net.minecraft.world.World;

import javax.annotation.Nonnull;

public class FoodBaseTiny extends Item {
    public FoodBaseTiny(Item.Properties p_i50045_1_, String name) {
        super(p_i50045_1_);

        this.setRegistryName(Reference.MODID, name);

    }

    @Override
    public int getUseDuration(ItemStack par1ItemStack) {
        return 12;
    }

    @Nonnull
    @Override
    public ActionResult<ItemStack> onItemRightClick(World world, PlayerEntity player, @Nonnull Hand hand) {
        ItemStack stack = player.getHeldItem(hand);

        if(player.canEat(false)) {
            player.setActiveHand(hand);
            return new ActionResult<>(ActionResultType.SUCCESS, stack);
        }

        return new ActionResult<>(ActionResultType.FAIL, stack);
    }
}
