package net.minecraft.world.level.block.entity;

import com.google.common.annotations.VisibleForTesting;
import net.minecraft.advancements.CriteriaTriggers;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.SculkCatalystBlock;
import net.minecraft.world.level.block.SculkSpreader;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.gameevent.BlockPositionSource;
import net.minecraft.world.level.gameevent.GameEvent;
import net.minecraft.world.level.gameevent.GameEventListener;
import net.minecraft.world.level.gameevent.PositionSource;
import net.minecraft.world.phys.Vec3;

public class SculkCatalystBlockEntity extends BlockEntity implements GameEventListener {
   private final BlockPositionSource blockPosSource = new BlockPositionSource(this.worldPosition);
   private final SculkSpreader sculkSpreader = SculkSpreader.createLevelSpreader();

   public SculkCatalystBlockEntity(BlockPos p_222774_, BlockState p_222775_) {
      super(BlockEntityType.SCULK_CATALYST, p_222774_, p_222775_);
   }

   public PositionSource getListenerSource() {
      return this.blockPosSource;
   }

   public int getListenerRadius() {
      return 8;
   }

   public GameEventListener.DeliveryMode getDeliveryMode() {
      return GameEventListener.DeliveryMode.BY_DISTANCE;
   }

   public boolean handleGameEvent(ServerLevel p_249401_, GameEvent p_251680_, GameEvent.Context p_248816_, Vec3 p_248534_) {
      if (p_251680_ == GameEvent.ENTITY_DIE) {
         Entity $$5 = p_248816_.sourceEntity();
         if ($$5 instanceof LivingEntity) {
            LivingEntity livingentity = (LivingEntity)$$5;
            if (!livingentity.wasExperienceConsumed()) {
               int i = livingentity.getExperienceReward();
               if (livingentity.shouldDropExperience() && i > 0) {
                  this.sculkSpreader.addCursors(BlockPos.containing(p_248534_.relative(Direction.UP, 0.5D)), i);
                  this.tryAwardItSpreadsAdvancement(livingentity);
               }

               livingentity.skipDropExperience();
               SculkCatalystBlock.bloom(p_249401_, this.worldPosition, this.getBlockState(), p_249401_.getRandom());
            }

            return true;
         }
      }

      return false;
   }

   private void tryAwardItSpreadsAdvancement(LivingEntity p_249197_) {
      LivingEntity livingentity = p_249197_.getLastHurtByMob();
      if (livingentity instanceof ServerPlayer serverplayer) {
         DamageSource damagesource = p_249197_.getLastDamageSource() == null ? this.level.damageSources().playerAttack(serverplayer) : p_249197_.getLastDamageSource();
         CriteriaTriggers.KILL_MOB_NEAR_SCULK_CATALYST.trigger(serverplayer, p_249197_, damagesource);
      }

   }

   public static void serverTick(Level p_222780_, BlockPos p_222781_, BlockState p_222782_, SculkCatalystBlockEntity p_222783_) {
      p_222783_.sculkSpreader.updateCursors(p_222780_, p_222781_, p_222780_.getRandom(), true);
   }

   public void load(CompoundTag p_222787_) {
      super.load(p_222787_);
      this.sculkSpreader.load(p_222787_);
   }

   protected void saveAdditional(CompoundTag p_222789_) {
      this.sculkSpreader.save(p_222789_);
      super.saveAdditional(p_222789_);
   }

   @VisibleForTesting
   public SculkSpreader getSculkSpreader() {
      return this.sculkSpreader;
   }
}