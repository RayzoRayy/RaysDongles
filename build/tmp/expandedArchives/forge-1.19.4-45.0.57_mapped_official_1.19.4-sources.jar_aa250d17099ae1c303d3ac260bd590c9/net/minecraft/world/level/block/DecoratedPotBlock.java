package net.minecraft.world.level.block;

import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.Containers;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.entity.DecoratedPotBlockEntity;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.minecraft.world.level.block.state.properties.BooleanProperty;
import net.minecraft.world.level.block.state.properties.DirectionProperty;
import net.minecraft.world.level.material.FluidState;
import net.minecraft.world.level.material.Fluids;
import net.minecraft.world.level.pathfinder.PathComputationType;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.VoxelShape;
import org.jetbrains.annotations.Nullable;

public class DecoratedPotBlock extends BaseEntityBlock {
   private static final VoxelShape BOUNDING_BOX = Block.box(1.0D, 0.0D, 1.0D, 15.0D, 16.0D, 15.0D);
   private static final DirectionProperty HORIZONTAL_FACING = BlockStateProperties.HORIZONTAL_FACING;
   private static final BooleanProperty WATERLOGGED = BlockStateProperties.WATERLOGGED;

   public DecoratedPotBlock(BlockBehaviour.Properties p_273064_) {
      super(p_273064_);
      this.registerDefaultState(this.stateDefinition.any().setValue(HORIZONTAL_FACING, Direction.NORTH).setValue(WATERLOGGED, Boolean.valueOf(false)));
   }

   public BlockState updateShape(BlockState p_276307_, Direction p_276322_, BlockState p_276280_, LevelAccessor p_276320_, BlockPos p_276270_, BlockPos p_276312_) {
      if (p_276307_.getValue(WATERLOGGED)) {
         p_276320_.scheduleTick(p_276270_, Fluids.WATER, Fluids.WATER.getTickDelay(p_276320_));
      }

      return super.updateShape(p_276307_, p_276322_, p_276280_, p_276320_, p_276270_, p_276312_);
   }

   public BlockState getStateForPlacement(BlockPlaceContext p_272711_) {
      FluidState fluidstate = p_272711_.getLevel().getFluidState(p_272711_.getClickedPos());
      return this.defaultBlockState().setValue(HORIZONTAL_FACING, p_272711_.getHorizontalDirection()).setValue(WATERLOGGED, Boolean.valueOf(fluidstate.getType() == Fluids.WATER));
   }

   public boolean isPathfindable(BlockState p_276295_, BlockGetter p_276308_, BlockPos p_276313_, PathComputationType p_276303_) {
      return false;
   }

   public VoxelShape getShape(BlockState p_273112_, BlockGetter p_273055_, BlockPos p_273137_, CollisionContext p_273151_) {
      return BOUNDING_BOX;
   }

   protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> p_273169_) {
      p_273169_.add(HORIZONTAL_FACING, WATERLOGGED);
   }

   public @Nullable BlockEntity newBlockEntity(BlockPos p_273396_, BlockState p_272674_) {
      return new DecoratedPotBlockEntity(p_273396_, p_272674_);
   }

   public void playerWillDestroy(Level p_273590_, BlockPos p_273343_, BlockState p_272869_, Player p_273002_) {
      if (!p_273590_.isClientSide) {
         BlockEntity blockentity = p_273590_.getBlockEntity(p_273343_);
         if (blockentity instanceof DecoratedPotBlockEntity) {
            DecoratedPotBlockEntity decoratedpotblockentity = (DecoratedPotBlockEntity)blockentity;
            decoratedpotblockentity.playerDestroy(p_273590_, p_273343_, p_273002_.getMainHandItem(), p_273002_);
         }
      }

      super.playerWillDestroy(p_273590_, p_273343_, p_272869_, p_273002_);
   }

   public void onRemove(BlockState p_272815_, Level p_273192_, BlockPos p_273629_, BlockState p_272976_, boolean p_272923_) {
      if (!p_273192_.isClientSide) {
         BlockEntity blockentity = p_273192_.getBlockEntity(p_273629_);
         if (blockentity instanceof DecoratedPotBlockEntity) {
            DecoratedPotBlockEntity decoratedpotblockentity = (DecoratedPotBlockEntity)blockentity;
            if (!decoratedpotblockentity.isBroken()) {
               Containers.dropItemStack(p_273192_, (double)p_273629_.getX(), (double)p_273629_.getY(), (double)p_273629_.getZ(), decoratedpotblockentity.getItem());
               p_273192_.playSound((Player)null, p_273629_, SoundEvents.DECORATED_POT_BREAK, SoundSource.PLAYERS, 1.0F, 1.0F);
            }
         }
      }

      super.onRemove(p_272815_, p_273192_, p_273629_, p_272976_, p_272923_);
   }

   public FluidState getFluidState(BlockState p_272593_) {
      return p_272593_.getValue(WATERLOGGED) ? Fluids.WATER.getSource(false) : super.getFluidState(p_272593_);
   }
}