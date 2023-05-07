package net.minecraft.world.level.block;

import java.util.List;
import net.minecraft.core.BlockPos;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.BlockSetType;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.minecraft.world.level.block.state.properties.BooleanProperty;
import net.minecraft.world.phys.AABB;

public class PressurePlateBlock extends BasePressurePlateBlock {
   public static final BooleanProperty POWERED = BlockStateProperties.POWERED;
   private final PressurePlateBlock.Sensitivity sensitivity;

   public PressurePlateBlock(PressurePlateBlock.Sensitivity p_273523_, BlockBehaviour.Properties p_273571_, BlockSetType p_273284_) {
      super(p_273571_, p_273284_);
      this.registerDefaultState(this.stateDefinition.any().setValue(POWERED, Boolean.valueOf(false)));
      this.sensitivity = p_273523_;
   }

   protected int getSignalForState(BlockState p_55270_) {
      return p_55270_.getValue(POWERED) ? 15 : 0;
   }

   protected BlockState setSignalForState(BlockState p_55259_, int p_55260_) {
      return p_55259_.setValue(POWERED, Boolean.valueOf(p_55260_ > 0));
   }

   protected int getSignalStrength(Level p_55264_, BlockPos p_55265_) {
      AABB aabb = TOUCH_AABB.move(p_55265_);
      List<? extends Entity> list;
      switch (this.sensitivity) {
         case EVERYTHING:
            list = p_55264_.getEntities((Entity)null, aabb);
            break;
         case MOBS:
            list = p_55264_.getEntitiesOfClass(LivingEntity.class, aabb);
            break;
         default:
            return 0;
      }

      if (!list.isEmpty()) {
         for(Entity entity : list) {
            if (!entity.isIgnoringBlockTriggers()) {
               return 15;
            }
         }
      }

      return 0;
   }

   protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> p_55262_) {
      p_55262_.add(POWERED);
   }

   public static enum Sensitivity {
      EVERYTHING,
      MOBS;
   }
}