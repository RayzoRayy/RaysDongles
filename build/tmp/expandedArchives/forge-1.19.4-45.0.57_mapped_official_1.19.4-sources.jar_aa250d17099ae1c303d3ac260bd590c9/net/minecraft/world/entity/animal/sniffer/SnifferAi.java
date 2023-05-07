package net.minecraft.world.entity.animal.sniffer;

import com.google.common.collect.ImmutableList;
import com.mojang.datafixers.util.Pair;
import com.mojang.logging.LogUtils;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Set;
import net.minecraft.core.BlockPos;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.util.Unit;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.PathfinderMob;
import net.minecraft.world.entity.ai.Brain;
import net.minecraft.world.entity.ai.behavior.AnimalMakeLove;
import net.minecraft.world.entity.ai.behavior.AnimalPanic;
import net.minecraft.world.entity.ai.behavior.Behavior;
import net.minecraft.world.entity.ai.behavior.DoNothing;
import net.minecraft.world.entity.ai.behavior.LookAtTargetSink;
import net.minecraft.world.entity.ai.behavior.MoveToTargetSink;
import net.minecraft.world.entity.ai.behavior.PositionTracker;
import net.minecraft.world.entity.ai.behavior.RandomStroll;
import net.minecraft.world.entity.ai.behavior.RunOne;
import net.minecraft.world.entity.ai.behavior.SetEntityLookTarget;
import net.minecraft.world.entity.ai.behavior.SetWalkTargetFromLookTarget;
import net.minecraft.world.entity.ai.behavior.Swim;
import net.minecraft.world.entity.ai.memory.MemoryModuleType;
import net.minecraft.world.entity.ai.memory.MemoryStatus;
import net.minecraft.world.entity.ai.memory.WalkTarget;
import net.minecraft.world.entity.ai.sensing.Sensor;
import net.minecraft.world.entity.ai.sensing.SensorType;
import net.minecraft.world.entity.schedule.Activity;
import org.slf4j.Logger;

public class SnifferAi {
   private static final Logger LOGGER = LogUtils.getLogger();
   private static final int MAX_LOOK_DISTANCE = 6;
   static final List<SensorType<? extends Sensor<? super Sniffer>>> SENSOR_TYPES = ImmutableList.of(SensorType.NEAREST_LIVING_ENTITIES, SensorType.HURT_BY, SensorType.NEAREST_PLAYERS);
   static final List<MemoryModuleType<?>> MEMORY_TYPES = ImmutableList.of(MemoryModuleType.LOOK_TARGET, MemoryModuleType.WALK_TARGET, MemoryModuleType.CANT_REACH_WALK_TARGET_SINCE, MemoryModuleType.PATH, MemoryModuleType.IS_PANICKING, MemoryModuleType.SNIFFER_SNIFFING_TARGET, MemoryModuleType.SNIFFER_DIGGING, MemoryModuleType.SNIFFER_HAPPY, MemoryModuleType.SNIFF_COOLDOWN, MemoryModuleType.SNIFFER_EXPLORED_POSITIONS, MemoryModuleType.NEAREST_VISIBLE_LIVING_ENTITIES, MemoryModuleType.BREED_TARGET);
   private static final int SNIFFING_COOLDOWN_TICKS = 9600;
   private static final float SPEED_MULTIPLIER_WHEN_IDLING = 1.0F;
   private static final float SPEED_MULTIPLIER_WHEN_PANICKING = 2.0F;
   private static final float SPEED_MULTIPLIER_WHEN_SNIFFING = 1.25F;

   protected static Brain<?> makeBrain(Brain<Sniffer> p_273175_) {
      initCoreActivity(p_273175_);
      initIdleActivity(p_273175_);
      initSniffingActivity(p_273175_);
      initDigActivity(p_273175_);
      p_273175_.setCoreActivities(Set.of(Activity.CORE));
      p_273175_.setDefaultActivity(Activity.IDLE);
      p_273175_.useDefaultActivity();
      return p_273175_;
   }

   private static void initCoreActivity(Brain<Sniffer> p_273185_) {
      p_273185_.addActivity(Activity.CORE, 0, ImmutableList.of(new Swim(0.8F), new AnimalPanic(2.0F) {
         protected void start(ServerLevel p_272973_, PathfinderMob p_273233_, long p_273492_) {
            p_273233_.getBrain().eraseMemory(MemoryModuleType.SNIFFER_DIGGING);
            p_273233_.getBrain().eraseMemory(MemoryModuleType.SNIFFER_SNIFFING_TARGET);
            ((Sniffer)p_273233_).transitionTo(Sniffer.State.IDLING);
            super.start(p_272973_, p_273233_, p_273492_);
         }
      }, new MoveToTargetSink(10000, 15000)));
   }

   private static void initSniffingActivity(Brain<Sniffer> p_273183_) {
      p_273183_.addActivityWithConditions(Activity.SNIFF, ImmutableList.of(Pair.of(0, new SnifferAi.Searching())), Set.of(Pair.of(MemoryModuleType.IS_PANICKING, MemoryStatus.VALUE_ABSENT), Pair.of(MemoryModuleType.SNIFFER_SNIFFING_TARGET, MemoryStatus.VALUE_PRESENT), Pair.of(MemoryModuleType.WALK_TARGET, MemoryStatus.VALUE_PRESENT)));
   }

   private static void initDigActivity(Brain<Sniffer> p_273677_) {
      p_273677_.addActivityWithConditions(Activity.DIG, ImmutableList.of(Pair.of(0, new SnifferAi.Digging(160, 180)), Pair.of(0, new SnifferAi.FinishedDigging(40))), Set.of(Pair.of(MemoryModuleType.IS_PANICKING, MemoryStatus.VALUE_ABSENT), Pair.of(MemoryModuleType.WALK_TARGET, MemoryStatus.VALUE_ABSENT), Pair.of(MemoryModuleType.SNIFFER_DIGGING, MemoryStatus.VALUE_PRESENT)));
   }

   private static void initIdleActivity(Brain<Sniffer> p_273750_) {
      p_273750_.addActivityWithConditions(Activity.IDLE, ImmutableList.of(Pair.of(0, new LookAtTargetSink(45, 90)), Pair.of(0, new SnifferAi.FeelingHappy(40, 100)), Pair.of(0, new RunOne<>(ImmutableList.of(Pair.of(SetWalkTargetFromLookTarget.create(1.0F, 3), 2), Pair.of(new SnifferAi.Scenting(40, 80), 1), Pair.of(new SnifferAi.Sniffing(40, 80), 1), Pair.of(new AnimalMakeLove(EntityType.SNIFFER, 1.0F), 1), Pair.of(SetEntityLookTarget.create(EntityType.PLAYER, 6.0F), 1), Pair.of(RandomStroll.stroll(1.0F), 1), Pair.of(new DoNothing(5, 20), 2))))), Set.of(Pair.of(MemoryModuleType.SNIFFER_DIGGING, MemoryStatus.VALUE_ABSENT)));
   }

   static void updateActivity(Sniffer p_273301_) {
      p_273301_.getBrain().setActiveActivityToFirstValid(ImmutableList.of(Activity.DIG, Activity.SNIFF, Activity.IDLE));
   }

   static class Digging extends Behavior<Sniffer> {
      Digging(int p_272666_, int p_273420_) {
         super(Map.of(MemoryModuleType.IS_PANICKING, MemoryStatus.VALUE_ABSENT, MemoryModuleType.WALK_TARGET, MemoryStatus.VALUE_ABSENT, MemoryModuleType.SNIFFER_DIGGING, MemoryStatus.VALUE_PRESENT, MemoryModuleType.SNIFF_COOLDOWN, MemoryStatus.VALUE_ABSENT), p_272666_, p_273420_);
      }

      protected boolean checkExtraStartConditions(ServerLevel p_273442_, Sniffer p_273370_) {
         return !p_273370_.isPanicking() && !p_273370_.isInWater();
      }

      protected boolean canStillUse(ServerLevel p_272686_, Sniffer p_273617_, long p_273124_) {
         return p_273617_.getBrain().getMemory(MemoryModuleType.SNIFFER_DIGGING).isPresent() && !p_273617_.isPanicking();
      }

      protected void start(ServerLevel p_272951_, Sniffer p_272688_, long p_272979_) {
         p_272688_.transitionTo(Sniffer.State.DIGGING);
      }

      protected void stop(ServerLevel p_273656_, Sniffer p_273063_, long p_272844_) {
         p_273063_.getBrain().setMemoryWithExpiry(MemoryModuleType.SNIFF_COOLDOWN, Unit.INSTANCE, 9600L);
      }
   }

   static class FeelingHappy extends Behavior<Sniffer> {
      FeelingHappy(int p_273286_, int p_272777_) {
         super(Map.of(MemoryModuleType.SNIFFER_HAPPY, MemoryStatus.VALUE_PRESENT), p_273286_, p_272777_);
      }

      protected boolean canStillUse(ServerLevel p_272660_, Sniffer p_273250_, long p_273180_) {
         return true;
      }

      protected void start(ServerLevel p_273624_, Sniffer p_273470_, long p_273501_) {
         p_273470_.transitionTo(Sniffer.State.FEELING_HAPPY);
      }

      protected void stop(ServerLevel p_273216_, Sniffer p_273271_, long p_273738_) {
         p_273271_.transitionTo(Sniffer.State.IDLING);
         p_273271_.getBrain().eraseMemory(MemoryModuleType.SNIFFER_HAPPY);
      }
   }

   static class FinishedDigging extends Behavior<Sniffer> {
      FinishedDigging(int p_272941_) {
         super(Map.of(MemoryModuleType.IS_PANICKING, MemoryStatus.VALUE_ABSENT, MemoryModuleType.WALK_TARGET, MemoryStatus.VALUE_ABSENT, MemoryModuleType.SNIFFER_DIGGING, MemoryStatus.VALUE_PRESENT, MemoryModuleType.SNIFF_COOLDOWN, MemoryStatus.VALUE_PRESENT), p_272941_, p_272941_);
      }

      protected boolean checkExtraStartConditions(ServerLevel p_273692_, Sniffer p_272856_) {
         return true;
      }

      protected boolean canStillUse(ServerLevel p_273775_, Sniffer p_273131_, long p_273569_) {
         return p_273131_.getBrain().getMemory(MemoryModuleType.SNIFFER_DIGGING).isPresent();
      }

      protected void start(ServerLevel p_272708_, Sniffer p_273502_, long p_272739_) {
         p_273502_.transitionTo(Sniffer.State.RISING);
      }

      protected void stop(ServerLevel p_273210_, Sniffer p_273648_, long p_272804_) {
         boolean flag = this.timedOut(p_272804_);
         p_273648_.transitionTo(Sniffer.State.IDLING).onDiggingComplete(flag);
         p_273648_.getBrain().eraseMemory(MemoryModuleType.SNIFFER_DIGGING);
         p_273648_.getBrain().setMemory(MemoryModuleType.SNIFFER_HAPPY, true);
      }
   }

   static class Scenting extends Behavior<Sniffer> {
      Scenting(int p_272680_, int p_273445_) {
         super(Map.of(MemoryModuleType.SNIFFER_DIGGING, MemoryStatus.VALUE_ABSENT, MemoryModuleType.SNIFFER_SNIFFING_TARGET, MemoryStatus.VALUE_ABSENT, MemoryModuleType.SNIFFER_HAPPY, MemoryStatus.VALUE_ABSENT), p_272680_, p_273445_);
      }

      protected boolean canStillUse(ServerLevel p_273482_, Sniffer p_273724_, long p_273191_) {
         return true;
      }

      protected void start(ServerLevel p_272795_, Sniffer p_272788_, long p_273611_) {
         p_272788_.transitionTo(Sniffer.State.SCENTING);
      }

      protected void stop(ServerLevel p_272816_, Sniffer p_273426_, long p_272832_) {
         p_273426_.transitionTo(Sniffer.State.IDLING);
      }
   }

   static class Searching extends Behavior<Sniffer> {
      Searching() {
         super(Map.of(MemoryModuleType.WALK_TARGET, MemoryStatus.VALUE_PRESENT, MemoryModuleType.IS_PANICKING, MemoryStatus.VALUE_ABSENT, MemoryModuleType.SNIFFER_SNIFFING_TARGET, MemoryStatus.VALUE_PRESENT), 600);
      }

      protected boolean checkExtraStartConditions(ServerLevel p_273493_, Sniffer p_272857_) {
         return !p_272857_.isPanicking() && !p_272857_.isInWater();
      }

      protected boolean canStillUse(ServerLevel p_273196_, Sniffer p_273769_, long p_273602_) {
         if (p_273769_.isPanicking() && !p_273769_.isInWater()) {
            return false;
         } else {
            Optional<BlockPos> optional = p_273769_.getBrain().getMemory(MemoryModuleType.WALK_TARGET).map(WalkTarget::getTarget).map(PositionTracker::currentBlockPosition);
            Optional<BlockPos> optional1 = p_273769_.getBrain().getMemory(MemoryModuleType.SNIFFER_SNIFFING_TARGET);
            return !optional.isEmpty() && !optional1.isEmpty() ? optional1.get().equals(optional.get()) : false;
         }
      }

      protected void start(ServerLevel p_273563_, Sniffer p_273394_, long p_273358_) {
         p_273394_.transitionTo(Sniffer.State.SEARCHING);
      }

      protected void stop(ServerLevel p_273705_, Sniffer p_273135_, long p_272667_) {
         if (p_273135_.canDig()) {
            p_273135_.getBrain().setMemory(MemoryModuleType.SNIFFER_DIGGING, true);
         }

         p_273135_.getBrain().eraseMemory(MemoryModuleType.WALK_TARGET);
         p_273135_.getBrain().eraseMemory(MemoryModuleType.SNIFFER_SNIFFING_TARGET);
      }
   }

   static class Sniffing extends Behavior<Sniffer> {
      Sniffing(int p_272703_, int p_272735_) {
         super(Map.of(MemoryModuleType.WALK_TARGET, MemoryStatus.VALUE_ABSENT, MemoryModuleType.SNIFFER_SNIFFING_TARGET, MemoryStatus.VALUE_ABSENT, MemoryModuleType.SNIFF_COOLDOWN, MemoryStatus.VALUE_ABSENT), p_272703_, p_272735_);
      }

      protected boolean checkExtraStartConditions(ServerLevel p_272972_, Sniffer p_273676_) {
         return !p_273676_.isBaby() && !p_273676_.isInWater();
      }

      protected boolean canStillUse(ServerLevel p_273156_, Sniffer p_273448_, long p_272841_) {
         return !p_273448_.isPanicking();
      }

      protected void start(ServerLevel p_272950_, Sniffer p_272614_, long p_273573_) {
         p_272614_.transitionTo(Sniffer.State.SNIFFING);
      }

      protected void stop(ServerLevel p_272617_, Sniffer p_273181_, long p_272635_) {
         boolean flag = this.timedOut(p_272635_);
         p_273181_.transitionTo(Sniffer.State.IDLING);
         if (flag) {
            p_273181_.calculateDigPosition().ifPresent((p_273341_) -> {
               p_273181_.getBrain().setMemory(MemoryModuleType.SNIFFER_SNIFFING_TARGET, p_273341_);
               p_273181_.getBrain().setMemory(MemoryModuleType.WALK_TARGET, new WalkTarget(p_273341_, 1.25F, 0));
            });
         }

      }
   }
}