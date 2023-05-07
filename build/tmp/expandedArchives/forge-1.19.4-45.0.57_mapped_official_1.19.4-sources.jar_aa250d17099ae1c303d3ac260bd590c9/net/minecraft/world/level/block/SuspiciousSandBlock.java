package net.minecraft.world.level.block;

import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.core.particles.BlockParticleOption;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.util.RandomSource;
import net.minecraft.world.entity.item.FallingBlockEntity;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.entity.SuspiciousSandBlockEntity;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.minecraft.world.level.block.state.properties.IntegerProperty;
import net.minecraft.world.level.gameevent.GameEvent;
import net.minecraft.world.level.material.PushReaction;
import net.minecraft.world.phys.Vec3;

public class SuspiciousSandBlock extends BaseEntityBlock implements Fallable {
   private static final IntegerProperty DUSTED = BlockStateProperties.DUSTED;
   public static final int TICK_DELAY = 2;

   public SuspiciousSandBlock(BlockBehaviour.Properties p_273062_) {
      super(p_273062_);
      this.registerDefaultState(this.stateDefinition.any().setValue(DUSTED, Integer.valueOf(0)));
   }

   protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> p_273642_) {
      p_273642_.add(DUSTED);
   }

   public RenderShape getRenderShape(BlockState p_272835_) {
      return RenderShape.MODEL;
   }

   public BlockEntity newBlockEntity(BlockPos p_272913_, BlockState p_273465_) {
      return new SuspiciousSandBlockEntity(p_272913_, p_273465_);
   }

   public PushReaction getPistonPushReaction(BlockState p_272640_) {
      return PushReaction.DESTROY;
   }

   public void onPlace(BlockState p_273229_, Level p_272807_, BlockPos p_273283_, BlockState p_273127_, boolean p_273128_) {
      p_272807_.scheduleTick(p_273283_, this, 2);
   }

   public BlockState updateShape(BlockState p_273424_, Direction p_273292_, BlockState p_273494_, LevelAccessor p_273388_, BlockPos p_272752_, BlockPos p_272808_) {
      p_273388_.scheduleTick(p_272752_, this, 2);
      return super.updateShape(p_273424_, p_273292_, p_273494_, p_273388_, p_272752_, p_272808_);
   }

   public void tick(BlockState p_273332_, ServerLevel p_272998_, BlockPos p_273141_, RandomSource p_272775_) {
      BlockEntity blockentity = p_272998_.getBlockEntity(p_273141_);
      if (blockentity instanceof SuspiciousSandBlockEntity suspicioussandblockentity) {
         suspicioussandblockentity.checkReset();
      }

      if (FallingBlock.isFree(p_272998_.getBlockState(p_273141_.below())) && p_273141_.getY() >= p_272998_.getMinBuildHeight()) {
         FallingBlockEntity fallingblockentity = FallingBlockEntity.fall(p_272998_, p_273141_, p_273332_);
         fallingblockentity.disableDrop();
      }
   }

   public void onBrokenAfterFall(Level p_273078_, BlockPos p_272800_, FallingBlockEntity p_272894_) {
      Vec3 vec3 = p_272894_.getBoundingBox().getCenter();
      p_273078_.levelEvent(2001, BlockPos.containing(vec3), Block.getId(p_272894_.getBlockState()));
      p_273078_.gameEvent(p_272894_, GameEvent.BLOCK_DESTROY, vec3);
   }

   public void animateTick(BlockState p_273592_, Level p_272671_, BlockPos p_273115_, RandomSource p_272715_) {
      if (p_272715_.nextInt(16) == 0) {
         BlockPos blockpos = p_273115_.below();
         if (FallingBlock.isFree(p_272671_.getBlockState(blockpos))) {
            double d0 = (double)p_273115_.getX() + p_272715_.nextDouble();
            double d1 = (double)p_273115_.getY() - 0.05D;
            double d2 = (double)p_273115_.getZ() + p_272715_.nextDouble();
            p_272671_.addParticle(new BlockParticleOption(ParticleTypes.FALLING_DUST, p_273592_), d0, d1, d2, 0.0D, 0.0D, 0.0D);
         }
      }

   }
}