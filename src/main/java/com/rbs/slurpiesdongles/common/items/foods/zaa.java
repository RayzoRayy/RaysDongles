package com.rbs.slurpiesdongles.common.items.foods;

import net.minecraft.network.chat.Component;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.level.Level;
import org.jetbrains.annotations.Nullable;

import javax.annotation.Nonnull;
import java.util.List;

public class zaa extends Item {
    public zaa(Properties pProperties) {
        super(pProperties);
    }

    @Override
    public void appendHoverText(ItemStack p_41421_, @Nullable Level p_41422_, List<Component> p_41423_, TooltipFlag p_41424_) {
        p_41423_.add(Component.translatable("This is a hearty slice, longer to eat, but will fill you up!"));
    }
    @Override
    public int getUseDuration(ItemStack par1ItemStack) {//How long it takes to consume the food
        return 64;
    }

    /**
     * returns the action that specifies what animation to play when the items
     * is being used
     */
    @Nonnull
    @Override
    public ItemStack finishUsingItem(ItemStack pStack, Level pLevel, LivingEntity pLivingEntity) {
        if (!(pLivingEntity instanceof Player))
            return pStack;

        Player player = (Player) pLivingEntity;

        pStack.shrink(1);
        player.getFoodData().eat(20, 1.0F);
        pLevel.playSound((Player) pLivingEntity, player.getX(), player.getY(), player.getZ(), SoundEvents.PLAYER_BURP, SoundSource.PLAYERS, 1.0F, 0.1F + (pLevel.random.nextFloat() - pLevel.random.nextFloat()) * 0.9F);

        return pStack;
    }
}
