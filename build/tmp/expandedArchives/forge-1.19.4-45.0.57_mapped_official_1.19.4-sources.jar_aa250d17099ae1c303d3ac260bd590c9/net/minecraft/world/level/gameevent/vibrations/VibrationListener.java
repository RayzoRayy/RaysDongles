package net.minecraft.world.level.gameevent.vibrations;

import com.google.common.annotations.VisibleForTesting;
import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import it.unimi.dsi.fastutil.objects.Object2IntMap;
import it.unimi.dsi.fastutil.objects.Object2IntMaps;
import it.unimi.dsi.fastutil.objects.Object2IntOpenHashMap;
import java.util.Optional;
import javax.annotation.Nullable;
import net.minecraft.Util;
import net.minecraft.advancements.CriteriaTriggers;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.core.particles.VibrationParticleOption;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.tags.BlockTags;
import net.minecraft.tags.GameEventTags;
import net.minecraft.tags.TagKey;
import net.minecraft.util.ExtraCodecs;
import net.minecraft.util.Mth;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.level.ClipBlockStateContext;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.gameevent.GameEvent;
import net.minecraft.world.level.gameevent.GameEventListener;
import net.minecraft.world.level.gameevent.PositionSource;
import net.minecraft.world.phys.HitResult;
import net.minecraft.world.phys.Vec3;

public class VibrationListener implements GameEventListener {
   @VisibleForTesting
   public static final Object2IntMap<GameEvent> VIBRATION_FREQUENCY_FOR_EVENT = Object2IntMaps.unmodifiable(Util.make(new Object2IntOpenHashMap<>(), (p_275179_) -> {
      p_275179_.put(GameEvent.STEP, 1);
      p_275179_.put(GameEvent.ITEM_INTERACT_FINISH, 2);
      p_275179_.put(GameEvent.FLAP, 2);
      p_275179_.put(GameEvent.SWIM, 3);
      p_275179_.put(GameEvent.ELYTRA_GLIDE, 4);
      p_275179_.put(GameEvent.HIT_GROUND, 5);
      p_275179_.put(GameEvent.TELEPORT, 5);
      p_275179_.put(GameEvent.SPLASH, 6);
      p_275179_.put(GameEvent.ENTITY_SHAKE, 6);
      p_275179_.put(GameEvent.BLOCK_CHANGE, 6);
      p_275179_.put(GameEvent.NOTE_BLOCK_PLAY, 6);
      p_275179_.put(GameEvent.ENTITY_DISMOUNT, 6);
      p_275179_.put(GameEvent.PROJECTILE_SHOOT, 7);
      p_275179_.put(GameEvent.DRINK, 7);
      p_275179_.put(GameEvent.PRIME_FUSE, 7);
      p_275179_.put(GameEvent.ENTITY_MOUNT, 7);
      p_275179_.put(GameEvent.PROJECTILE_LAND, 8);
      p_275179_.put(GameEvent.EAT, 8);
      p_275179_.put(GameEvent.ENTITY_INTERACT, 8);
      p_275179_.put(GameEvent.ENTITY_DAMAGE, 8);
      p_275179_.put(GameEvent.EQUIP, 9);
      p_275179_.put(GameEvent.SHEAR, 9);
      p_275179_.put(GameEvent.ENTITY_ROAR, 9);
      p_275179_.put(GameEvent.BLOCK_CLOSE, 10);
      p_275179_.put(GameEvent.BLOCK_DEACTIVATE, 10);
      p_275179_.put(GameEvent.BLOCK_DETACH, 10);
      p_275179_.put(GameEvent.DISPENSE_FAIL, 10);
      p_275179_.put(GameEvent.BLOCK_OPEN, 11);
      p_275179_.put(GameEvent.BLOCK_ACTIVATE, 11);
      p_275179_.put(GameEvent.BLOCK_ATTACH, 11);
      p_275179_.put(GameEvent.ENTITY_PLACE, 12);
      p_275179_.put(GameEvent.BLOCK_PLACE, 12);
      p_275179_.put(GameEvent.FLUID_PLACE, 12);
      p_275179_.put(GameEvent.ENTITY_DIE, 13);
      p_275179_.put(GameEvent.BLOCK_DESTROY, 13);
      p_275179_.put(GameEvent.FLUID_PICKUP, 13);
      p_275179_.put(GameEvent.CONTAINER_CLOSE, 14);
      p_275179_.put(GameEvent.PISTON_CONTRACT, 14);
      p_275179_.put(GameEvent.PISTON_EXTEND, 15);
      p_275179_.put(GameEvent.CONTAINER_OPEN, 15);
      p_275179_.put(GameEvent.EXPLODE, 15);
      p_275179_.put(GameEvent.LIGHTNING_STRIKE, 15);
      p_275179_.put(GameEvent.INSTRUMENT_PLAY, 15);
   }));
   protected final PositionSource listenerSource;
   protected final int listenerRange;
   protected final VibrationListener.VibrationListenerConfig config;
   @Nullable
   protected VibrationInfo currentVibration;
   protected int travelTimeInTicks;
   private final VibrationSelector selectionStrategy;

   public static Codec<VibrationListener> codec(VibrationListener.VibrationListenerConfig p_223782_) {
      return RecordCodecBuilder.create((p_248464_) -> {
         return p_248464_.group(PositionSource.CODEC.fieldOf("source").forGetter((p_223802_) -> {
            return p_223802_.listenerSource;
         }), ExtraCodecs.NON_NEGATIVE_INT.fieldOf("range").forGetter((p_223800_) -> {
            return p_223800_.listenerRange;
         }), VibrationInfo.CODEC.optionalFieldOf("event").forGetter((p_248473_) -> {
            return Optional.ofNullable(p_248473_.currentVibration);
         }), VibrationSelector.CODEC.fieldOf("selector").forGetter((p_248461_) -> {
            return p_248461_.selectionStrategy;
         }), ExtraCodecs.NON_NEGATIVE_INT.fieldOf("event_delay").orElse(0).forGetter((p_223794_) -> {
            return p_223794_.travelTimeInTicks;
         })).apply(p_248464_, (p_248468_, p_248469_, p_248470_, p_248471_, p_248472_) -> {
            return new VibrationListener(p_248468_, p_248469_, p_223782_, p_248470_.orElse((VibrationInfo)null), p_248471_, p_248472_);
         });
      });
   }

   private VibrationListener(PositionSource p_252286_, int p_249404_, VibrationListener.VibrationListenerConfig p_251761_, @Nullable VibrationInfo p_250360_, VibrationSelector p_250234_, int p_248651_) {
      this.listenerSource = p_252286_;
      this.listenerRange = p_249404_;
      this.config = p_251761_;
      this.currentVibration = p_250360_;
      this.travelTimeInTicks = p_248651_;
      this.selectionStrategy = p_250234_;
   }

   public VibrationListener(PositionSource p_248645_, int p_250290_, VibrationListener.VibrationListenerConfig p_249414_) {
      this(p_248645_, p_250290_, p_249414_, (VibrationInfo)null, new VibrationSelector(), 0);
   }

   public static int getGameEventFrequency(GameEvent p_252168_) {
      return VIBRATION_FREQUENCY_FOR_EVENT.getOrDefault(p_252168_, 0);
   }

   public void tick(Level p_157899_) {
      if (p_157899_ instanceof ServerLevel serverlevel) {
         if (this.currentVibration == null) {
            this.selectionStrategy.chosenCandidate(serverlevel.getGameTime()).ifPresent((p_248466_) -> {
               this.currentVibration = p_248466_;
               Vec3 vec3 = this.currentVibration.pos();
               this.travelTimeInTicks = Mth.floor(this.currentVibration.distance());
               serverlevel.sendParticles(new VibrationParticleOption(this.listenerSource, this.travelTimeInTicks), vec3.x, vec3.y, vec3.z, 1, 0.0D, 0.0D, 0.0D, 0.0D);
               this.config.onSignalSchedule();
               this.selectionStrategy.startOver();
            });
         }

         if (this.currentVibration != null) {
            --this.travelTimeInTicks;
            if (this.travelTimeInTicks <= 0) {
               this.travelTimeInTicks = 0;
               this.config.onSignalReceive(serverlevel, this, BlockPos.containing(this.currentVibration.pos()), this.currentVibration.gameEvent(), this.currentVibration.getEntity(serverlevel).orElse((Entity)null), this.currentVibration.getProjectileOwner(serverlevel).orElse((Entity)null), this.currentVibration.distance());
               this.currentVibration = null;
            }
         }
      }

   }

   public PositionSource getListenerSource() {
      return this.listenerSource;
   }

   public int getListenerRadius() {
      return this.listenerRange;
   }

   public boolean handleGameEvent(ServerLevel p_251930_, GameEvent p_250161_, GameEvent.Context p_251443_, Vec3 p_250045_) {
      if (this.currentVibration != null) {
         return false;
      } else if (!this.config.isValidVibration(p_250161_, p_251443_)) {
         return false;
      } else {
         Optional<Vec3> optional = this.listenerSource.getPosition(p_251930_);
         if (optional.isEmpty()) {
            return false;
         } else {
            Vec3 vec3 = optional.get();
            if (!this.config.shouldListen(p_251930_, this, BlockPos.containing(p_250045_), p_250161_, p_251443_)) {
               return false;
            } else if (isOccluded(p_251930_, p_250045_, vec3)) {
               return false;
            } else {
               this.scheduleVibration(p_251930_, p_250161_, p_251443_, p_250045_, vec3);
               return true;
            }
         }
      }
   }

   public void forceGameEvent(ServerLevel p_250726_, GameEvent p_251544_, GameEvent.Context p_250365_, Vec3 p_249607_) {
      this.listenerSource.getPosition(p_250726_).ifPresent((p_248460_) -> {
         this.scheduleVibration(p_250726_, p_251544_, p_250365_, p_249607_, p_248460_);
      });
   }

   public void scheduleVibration(ServerLevel p_250210_, GameEvent p_251063_, GameEvent.Context p_249354_, Vec3 p_250310_, Vec3 p_249553_) {
      this.selectionStrategy.addCandidate(new VibrationInfo(p_251063_, (float)p_250310_.distanceTo(p_249553_), p_250310_, p_249354_.sourceEntity()), p_250210_.getGameTime());
   }

   private static boolean isOccluded(Level p_223776_, Vec3 p_223777_, Vec3 p_223778_) {
      Vec3 vec3 = new Vec3((double)Mth.floor(p_223777_.x) + 0.5D, (double)Mth.floor(p_223777_.y) + 0.5D, (double)Mth.floor(p_223777_.z) + 0.5D);
      Vec3 vec31 = new Vec3((double)Mth.floor(p_223778_.x) + 0.5D, (double)Mth.floor(p_223778_.y) + 0.5D, (double)Mth.floor(p_223778_.z) + 0.5D);

      for(Direction direction : Direction.values()) {
         Vec3 vec32 = vec3.relative(direction, (double)1.0E-5F);
         if (p_223776_.isBlockInLine(new ClipBlockStateContext(vec32, vec31, (p_223780_) -> {
            return p_223780_.is(BlockTags.OCCLUDES_VIBRATION_SIGNALS);
         })).getType() != HitResult.Type.BLOCK) {
            return false;
         }
      }

      return true;
   }

   public interface VibrationListenerConfig {
      default TagKey<GameEvent> getListenableEvents() {
         return GameEventTags.VIBRATIONS;
      }

      default boolean canTriggerAvoidVibration() {
         return false;
      }

      default boolean isValidVibration(GameEvent p_223878_, GameEvent.Context p_223879_) {
         if (!p_223878_.is(this.getListenableEvents())) {
            return false;
         } else {
            Entity entity = p_223879_.sourceEntity();
            if (entity != null) {
               if (entity.isSpectator()) {
                  return false;
               }

               if (entity.isSteppingCarefully() && p_223878_.is(GameEventTags.IGNORE_VIBRATIONS_SNEAKING)) {
                  if (this.canTriggerAvoidVibration() && entity instanceof ServerPlayer) {
                     ServerPlayer serverplayer = (ServerPlayer)entity;
                     CriteriaTriggers.AVOID_VIBRATION.trigger(serverplayer);
                  }

                  return false;
               }

               if (entity.dampensVibrations()) {
                  return false;
               }
            }

            if (p_223879_.affectedState() != null) {
               return !p_223879_.affectedState().is(BlockTags.DAMPENS_VIBRATIONS);
            } else {
               return true;
            }
         }
      }

      boolean shouldListen(ServerLevel p_223872_, GameEventListener p_223873_, BlockPos p_223874_, GameEvent p_223875_, GameEvent.Context p_223876_);

      void onSignalReceive(ServerLevel p_223865_, GameEventListener p_223866_, BlockPos p_223867_, GameEvent p_223868_, @Nullable Entity p_223869_, @Nullable Entity p_223870_, float p_223871_);

      default void onSignalSchedule() {
      }
   }
}