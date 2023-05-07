package net.minecraft.world.item;

import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.core.particles.BlockParticleOption;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.context.UseOnContext;
import net.minecraft.world.level.ClipContext;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.entity.SuspiciousSandBlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.HitResult;
import net.minecraft.world.phys.Vec3;

public class BrushItem extends Item {
   public static final int TICKS_BETWEEN_SWEEPS = 10;
   private static final int USE_DURATION = 225;

   public BrushItem(Item.Properties p_272907_) {
      super(p_272907_);
   }

   public InteractionResult useOn(UseOnContext p_272607_) {
      Player player = p_272607_.getPlayer();
      if (player != null) {
         player.startUsingItem(p_272607_.getHand());
      }

      return InteractionResult.CONSUME;
   }

   public UseAnim getUseAnimation(ItemStack p_273490_) {
      return UseAnim.BRUSH;
   }

   public int getUseDuration(ItemStack p_272765_) {
      return 225;
   }

   public void onUseTick(Level p_273467_, LivingEntity p_273619_, ItemStack p_273316_, int p_273101_) {
      if (p_273101_ >= 0 && p_273619_ instanceof Player player) {
         BlockHitResult blockhitresult = Item.getPlayerPOVHitResult(p_273467_, player, ClipContext.Fluid.NONE);
         BlockPos $$7 = blockhitresult.getBlockPos();
         if (blockhitresult.getType() == HitResult.Type.MISS) {
            p_273619_.releaseUsingItem();
         } else {
            int i = this.getUseDuration(p_273316_) - p_273101_ + 1;
            if (i == 1 || i % 10 == 0) {
               BlockState blockstate = p_273467_.getBlockState($$7);
               this.spawnDustParticles(p_273467_, blockhitresult, blockstate, p_273619_.getViewVector(0.0F));
               p_273467_.playSound(player, $$7, SoundEvents.BRUSH_BRUSHING, SoundSource.PLAYERS);
               if (!p_273467_.isClientSide() && blockstate.is(Blocks.SUSPICIOUS_SAND)) {
                  BlockEntity $$11 = p_273467_.getBlockEntity($$7);
                  if ($$11 instanceof SuspiciousSandBlockEntity) {
                     SuspiciousSandBlockEntity suspicioussandblockentity = (SuspiciousSandBlockEntity)$$11;
                     boolean flag = suspicioussandblockentity.brush(p_273467_.getGameTime(), player, blockhitresult.getDirection());
                     if (flag) {
                        p_273316_.hurtAndBreak(1, p_273619_, (p_272600_) -> {
                           p_272600_.broadcastBreakEvent(EquipmentSlot.MAINHAND);
                        });
                     }
                  }
               }
            }

         }
      } else {
         p_273619_.releaseUsingItem();
      }
      super.onUseTick(p_273467_, p_273619_, p_273316_, p_273101_); //TODO: Remove in 1.20, this is only here to trigger Forge's old onUseingTick method
   }

   public void spawnDustParticles(Level p_272701_, BlockHitResult p_273758_, BlockState p_272753_, Vec3 p_272912_) {
      double d0 = 3.0D;
      int i = p_272701_.getRandom().nextInt(7, 12);
      BlockParticleOption blockparticleoption = new BlockParticleOption(ParticleTypes.BLOCK, p_272753_);
      Direction direction = p_273758_.getDirection();
      BrushItem.DustParticlesDelta brushitem$dustparticlesdelta = BrushItem.DustParticlesDelta.fromDirection(p_272912_, direction);
      Vec3 vec3 = p_273758_.getLocation();

      for(int j = 0; j < i; ++j) {
         p_272701_.addParticle(blockparticleoption, vec3.x - (double)(direction == Direction.WEST ? 1.0E-6F : 0.0F), vec3.y, vec3.z - (double)(direction == Direction.NORTH ? 1.0E-6F : 0.0F), brushitem$dustparticlesdelta.xd() * 3.0D * p_272701_.getRandom().nextDouble(), 0.0D, brushitem$dustparticlesdelta.zd() * 3.0D * p_272701_.getRandom().nextDouble());
      }

   }

   static record DustParticlesDelta(double xd, double yd, double zd) {
      private static final double ALONG_SIDE_DELTA = 1.0D;
      private static final double OUT_FROM_SIDE_DELTA = 0.1D;

      public static BrushItem.DustParticlesDelta fromDirection(Vec3 p_273421_, Direction p_272987_) {
         double d0 = 0.0D;
         BrushItem.DustParticlesDelta brushitem$dustparticlesdelta;
         switch (p_272987_) {
            case DOWN:
               brushitem$dustparticlesdelta = new BrushItem.DustParticlesDelta(-p_273421_.x(), 0.0D, p_273421_.z());
               break;
            case UP:
               brushitem$dustparticlesdelta = new BrushItem.DustParticlesDelta(p_273421_.z(), 0.0D, -p_273421_.x());
               break;
            case NORTH:
               brushitem$dustparticlesdelta = new BrushItem.DustParticlesDelta(1.0D, 0.0D, -0.1D);
               break;
            case SOUTH:
               brushitem$dustparticlesdelta = new BrushItem.DustParticlesDelta(-1.0D, 0.0D, 0.1D);
               break;
            case WEST:
               brushitem$dustparticlesdelta = new BrushItem.DustParticlesDelta(-0.1D, 0.0D, -1.0D);
               break;
            case EAST:
               brushitem$dustparticlesdelta = new BrushItem.DustParticlesDelta(0.1D, 0.0D, 1.0D);
               break;
            default:
               throw new IncompatibleClassChangeError();
         }

         return brushitem$dustparticlesdelta;
      }
   }
}
