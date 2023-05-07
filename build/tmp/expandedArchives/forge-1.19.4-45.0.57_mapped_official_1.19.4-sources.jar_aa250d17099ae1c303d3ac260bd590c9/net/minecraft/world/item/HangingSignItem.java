package net.minecraft.world.item;

import javax.annotation.Nullable;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelReader;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.WallHangingSignBlock;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.entity.SignBlockEntity;
import net.minecraft.world.level.block.state.BlockState;

public class HangingSignItem extends StandingAndWallBlockItem {
   public HangingSignItem(Block p_251582_, Block p_250734_, Item.Properties p_250266_) {
      super(p_251582_, p_250734_, p_250266_, Direction.UP);
   }

   protected boolean canPlace(LevelReader p_252032_, BlockState p_252230_, BlockPos p_252075_) {
      Block block = p_252230_.getBlock();
      if (block instanceof WallHangingSignBlock wallhangingsignblock) {
         if (!wallhangingsignblock.canPlace(p_252230_, p_252032_, p_252075_)) {
            return false;
         }
      }

      return super.canPlace(p_252032_, p_252230_, p_252075_);
   }

   protected boolean updateCustomBlockEntityTag(BlockPos p_251351_, Level p_251684_, @Nullable Player p_248736_, ItemStack p_250117_, BlockState p_251334_) {
      boolean flag = super.updateCustomBlockEntityTag(p_251351_, p_251684_, p_248736_, p_250117_, p_251334_);
      if (!p_251684_.isClientSide && !flag && p_248736_ != null) {
         BlockEntity blockentity = p_251684_.getBlockEntity(p_251351_);
         if (blockentity instanceof SignBlockEntity) {
            SignBlockEntity signblockentity = (SignBlockEntity)blockentity;
            p_248736_.openTextEdit(signblockentity);
         }
      }

      return flag;
   }
}