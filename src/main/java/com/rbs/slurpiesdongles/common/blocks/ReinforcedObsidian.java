package com.rbs.slurpiesdongles.common.blocks;

import net.minecraft.client.gui.screens.Screen;
import net.minecraft.core.BlockPos;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.TextComponent;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.boss.wither.WitherBoss;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.projectile.WitherSkull;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Explosion;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.storage.loot.LootContext;

import javax.annotation.Nullable;
import java.util.Collections;
import java.util.List;

public class ReinforcedObsidian extends Block {

    public ReinforcedObsidian(Properties properties) {
        super(properties);
    }
    @Override
    public boolean canEntityDestroy(BlockState state, BlockGetter world, BlockPos pos, Entity entity) {
        return !(entity instanceof WitherBoss) && !(entity instanceof WitherSkull);
    }
    @Override
    public void playerDestroy(Level level, Player player, BlockPos pos, BlockState blockState, @Nullable BlockEntity blockEntity, ItemStack itemStack) {
        super.playerDestroy(level, player, pos, blockState, blockEntity, itemStack);
    }
    @Override
    public void onBlockExploded(BlockState state, Level world, BlockPos pos, Explosion explosion) {

    }
    @Override
    public boolean canDropFromExplosion(BlockState state, BlockGetter world, BlockPos pos, Explosion explosion) {
        return false;
    }
    @Override
    public void appendHoverText(ItemStack p_49816_, @Nullable BlockGetter p_49817_, List<Component> toolTip, TooltipFlag tooltipFlag) {
        if(Screen.hasShiftDown()) {
            toolTip.add(new TextComponent("Wither proof!"));
        }else
        if(!Screen.hasShiftDown()) {
            toolTip.add(new TextComponent("Hold \u00A7eSHIFT\u00A7f for more information"));
        }
    }
    @Override
    public List<ItemStack> getDrops(BlockState state, LootContext.Builder builder) {
        return (Collections.singletonList(new ItemStack(this)));
    }
}
