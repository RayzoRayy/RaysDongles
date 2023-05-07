package net.minecraft.world.entity.animal.sniffer;

import com.mojang.serialization.Dynamic;
import java.util.Collection;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;
import net.minecraft.core.BlockPos;
import net.minecraft.core.particles.BlockParticleOption;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.network.protocol.game.DebugPackets;
import net.minecraft.network.syncher.EntityDataAccessor;
import net.minecraft.network.syncher.EntityDataSerializers;
import net.minecraft.network.syncher.SynchedEntityData;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.tags.BlockTags;
import net.minecraft.tags.ItemTags;
import net.minecraft.util.Mth;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.AgeableMob;
import net.minecraft.world.entity.AnimationState;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityDimensions;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.Mob;
import net.minecraft.world.entity.Pose;
import net.minecraft.world.entity.ai.Brain;
import net.minecraft.world.entity.ai.attributes.AttributeSupplier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.ai.memory.MemoryModuleType;
import net.minecraft.world.entity.ai.util.LandRandomPos;
import net.minecraft.world.entity.animal.Animal;
import net.minecraft.world.entity.item.ItemEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.RenderShape;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.pathfinder.BlockPathTypes;
import net.minecraft.world.phys.AABB;
import net.minecraft.world.phys.Vec3;

public class Sniffer extends Animal {
   private static final int DIGGING_PARTICLES_DELAY_TICKS = 1700;
   private static final int DIGGING_PARTICLES_DURATION_TICKS = 6000;
   private static final int DIGGING_PARTICLES_AMOUNT = 30;
   private static final int DIGGING_DROP_SEED_OFFSET_TICKS = 120;
   private static final int SNIFFING_PROXIMITY_DISTANCE = 10;
   private static final int SNIFFER_BABY_AGE_TICKS = 48000;
   private static final EntityDataAccessor<Sniffer.State> DATA_STATE = SynchedEntityData.defineId(Sniffer.class, EntityDataSerializers.SNIFFER_STATE);
   private static final EntityDataAccessor<Integer> DATA_DROP_SEED_AT_TICK = SynchedEntityData.defineId(Sniffer.class, EntityDataSerializers.INT);
   public final AnimationState walkingAnimationState = new AnimationState();
   public final AnimationState panicAnimationState = new AnimationState();
   public final AnimationState feelingHappyAnimationState = new AnimationState();
   public final AnimationState scentingAnimationState = new AnimationState();
   public final AnimationState sniffingAnimationState = new AnimationState();
   public final AnimationState searchingAnimationState = new AnimationState();
   public final AnimationState diggingAnimationState = new AnimationState();
   public final AnimationState risingAnimationState = new AnimationState();

   public static AttributeSupplier.Builder createAttributes() {
      return Mob.createMobAttributes().add(Attributes.MOVEMENT_SPEED, (double)0.1F).add(Attributes.MAX_HEALTH, 14.0D);
   }

   public Sniffer(EntityType<? extends Animal> p_273717_, Level p_273562_) {
      super(p_273717_, p_273562_);
      this.entityData.define(DATA_STATE, Sniffer.State.IDLING);
      this.entityData.define(DATA_DROP_SEED_AT_TICK, 0);
      this.getNavigation().setCanFloat(true);
      this.setPathfindingMalus(BlockPathTypes.WATER, -2.0F);
   }

   protected float getStandingEyeHeight(Pose p_272721_, EntityDimensions p_273353_) {
      return this.getDimensions(p_272721_).height * 0.6F;
   }

   private boolean isMoving() {
      boolean flag = this.onGround || this.isInWaterOrBubble();
      return flag && this.getDeltaMovement().horizontalDistanceSqr() > 1.0E-6D;
   }

   private boolean isMovingInWater() {
      return this.isMoving() && this.isInWater() && !this.isUnderWater() && this.getDeltaMovement().horizontalDistanceSqr() > 9.999999999999999E-6D;
   }

   private boolean isMovingOnLand() {
      return this.isMoving() && !this.isUnderWater() && !this.isInWater();
   }

   public boolean isPanicking() {
      return this.brain.getMemory(MemoryModuleType.IS_PANICKING).isPresent();
   }

   public boolean canPlayDiggingSound() {
      return this.getState() == Sniffer.State.DIGGING || this.getState() == Sniffer.State.SEARCHING;
   }

   private BlockPos getHeadPosition() {
      Vec3 vec3 = this.position().add(this.getForward().scale(2.25D));
      return BlockPos.containing(vec3.x(), this.getY(), vec3.z());
   }

   private Sniffer.State getState() {
      return this.entityData.get(DATA_STATE);
   }

   private Sniffer setState(Sniffer.State p_273359_) {
      this.entityData.set(DATA_STATE, p_273359_);
      return this;
   }

   public void onSyncedDataUpdated(EntityDataAccessor<?> p_272936_) {
      if (DATA_STATE.equals(p_272936_)) {
         Sniffer.State sniffer$state = this.getState();
         this.resetAnimations();
         switch (sniffer$state) {
            case SCENTING:
               this.scentingAnimationState.startIfStopped(this.tickCount);
               break;
            case SNIFFING:
               this.sniffingAnimationState.startIfStopped(this.tickCount);
               break;
            case SEARCHING:
               this.searchingAnimationState.startIfStopped(this.tickCount);
               break;
            case DIGGING:
               this.diggingAnimationState.startIfStopped(this.tickCount);
               break;
            case RISING:
               this.risingAnimationState.startIfStopped(this.tickCount);
               break;
            case FEELING_HAPPY:
               this.feelingHappyAnimationState.startIfStopped(this.tickCount);
         }
      }

      super.onSyncedDataUpdated(p_272936_);
   }

   private void resetAnimations() {
      this.searchingAnimationState.stop();
      this.diggingAnimationState.stop();
      this.sniffingAnimationState.stop();
      this.risingAnimationState.stop();
      this.feelingHappyAnimationState.stop();
      this.scentingAnimationState.stop();
   }

   public Sniffer transitionTo(Sniffer.State p_273096_) {
      switch (p_273096_) {
         case SCENTING:
            this.playSound(SoundEvents.SNIFFER_SCENTING, 1.0F, 1.0F);
            this.setState(Sniffer.State.SCENTING);
            break;
         case SNIFFING:
            this.playSound(SoundEvents.SNIFFER_SNIFFING, 1.0F, 1.0F);
            this.setState(Sniffer.State.SNIFFING);
            break;
         case SEARCHING:
            this.setState(Sniffer.State.SEARCHING);
            break;
         case DIGGING:
            this.setState(Sniffer.State.DIGGING).onDiggingStart();
            break;
         case RISING:
            this.playSound(SoundEvents.SNIFFER_DIGGING_STOP, 1.0F, 1.0F);
            this.setState(Sniffer.State.RISING);
            break;
         case FEELING_HAPPY:
            this.playSound(SoundEvents.SNIFFER_HAPPY, 1.0F, 1.0F);
            this.setState(Sniffer.State.FEELING_HAPPY);
            break;
         case IDLING:
            this.setState(Sniffer.State.IDLING);
      }

      return this;
   }

   private Sniffer onDiggingStart() {
      this.entityData.set(DATA_DROP_SEED_AT_TICK, this.tickCount + 120);
      this.level.broadcastEntityEvent(this, (byte)63);
      return this;
   }

   public Sniffer onDiggingComplete(boolean p_272677_) {
      if (p_272677_) {
         this.storeExploredPosition(this.getOnPos());
      }

      return this;
   }

   Optional<BlockPos> calculateDigPosition() {
      return IntStream.range(0, 5).mapToObj((p_273771_) -> {
         return LandRandomPos.getPos(this, 10 + 2 * p_273771_, 3);
      }).filter(Objects::nonNull).map(BlockPos::containing).map(BlockPos::below).filter(this::canDig).findFirst();
   }

   protected boolean canRide(Entity p_273768_) {
      return false;
   }

   boolean canDig() {
      return !this.isPanicking() && !this.isBaby() && !this.isInWater() && this.canDig(this.getHeadPosition().below());
   }

   private boolean canDig(BlockPos p_272757_) {
      return this.level.getBlockState(p_272757_).is(BlockTags.SNIFFER_DIGGABLE_BLOCK) && this.level.getBlockState(p_272757_.above()).isAir() && this.getExploredPositions().noneMatch(p_272757_::equals);
   }

   private void dropSeed() {
      if (!this.level.isClientSide() && this.entityData.get(DATA_DROP_SEED_AT_TICK) == this.tickCount) {
         ItemStack itemstack = new ItemStack(Items.TORCHFLOWER_SEEDS);
         BlockPos blockpos = this.getHeadPosition();
         ItemEntity itementity = new ItemEntity(this.level, (double)blockpos.getX(), (double)blockpos.getY(), (double)blockpos.getZ(), itemstack);
         itementity.setDefaultPickUpDelay();
         this.level.addFreshEntity(itementity);
         this.playSound(SoundEvents.SNIFFER_DROP_SEED, 1.0F, 1.0F);
      }
   }

   private Sniffer emitDiggingParticles(AnimationState p_273528_) {
      boolean flag = p_273528_.getAccumulatedTime() > 1700L && p_273528_.getAccumulatedTime() < 6000L;
      if (flag) {
         BlockState blockstate = this.getBlockStateOn();
         BlockPos blockpos = this.getHeadPosition();
         if (blockstate.getRenderShape() != RenderShape.INVISIBLE) {
            for(int i = 0; i < 30; ++i) {
               Vec3 vec3 = Vec3.atCenterOf(blockpos);
               this.level.addParticle(new BlockParticleOption(ParticleTypes.BLOCK, blockstate), vec3.x, vec3.y, vec3.z, 0.0D, 0.0D, 0.0D);
            }

            if (this.tickCount % 10 == 0) {
               this.level.playLocalSound(this.getX(), this.getY(), this.getZ(), blockstate.getSoundType().getHitSound(), this.getSoundSource(), 0.5F, 0.5F, false);
            }
         }
      }

      return this;
   }

   private Sniffer storeExploredPosition(BlockPos p_273015_) {
      List<BlockPos> list = this.getExploredPositions().limit(20L).collect(Collectors.toList());
      list.add(0, p_273015_);
      this.getBrain().setMemory(MemoryModuleType.SNIFFER_EXPLORED_POSITIONS, list);
      return this;
   }

   private Stream<BlockPos> getExploredPositions() {
      return this.getBrain().getMemory(MemoryModuleType.SNIFFER_EXPLORED_POSITIONS).stream().flatMap(Collection::stream);
   }

   protected void jumpFromGround() {
      super.jumpFromGround();
      double d0 = this.moveControl.getSpeedModifier();
      if (d0 > 0.0D) {
         double d1 = this.getDeltaMovement().horizontalDistanceSqr();
         if (d1 < 0.01D) {
            this.moveRelative(0.1F, new Vec3(0.0D, 0.0D, 1.0D));
         }
      }

   }

   public void tick() {
      boolean flag = this.isInWater() && !this.isUnderWater();
      this.getAttribute(Attributes.MOVEMENT_SPEED).setBaseValue(flag ? (double)0.2F : (double)0.1F);
      if (!this.isMovingOnLand() && !this.isMovingInWater()) {
         this.panicAnimationState.stop();
         this.walkingAnimationState.stop();
      } else if (this.isPanicking()) {
         this.walkingAnimationState.stop();
         this.panicAnimationState.startIfStopped(this.tickCount);
      } else {
         this.panicAnimationState.stop();
         this.walkingAnimationState.startIfStopped(this.tickCount);
      }

      switch (this.getState()) {
         case SEARCHING:
            this.playSearchingSound();
            break;
         case DIGGING:
            this.emitDiggingParticles(this.diggingAnimationState).dropSeed();
      }

      super.tick();
   }

   public InteractionResult mobInteract(Player p_273046_, InteractionHand p_272687_) {
      ItemStack itemstack = p_273046_.getItemInHand(p_272687_);
      InteractionResult interactionresult = super.mobInteract(p_273046_, p_272687_);
      if (interactionresult.consumesAction() && this.isFood(itemstack)) {
         this.level.playSound((Player)null, this, this.getEatingSound(itemstack), SoundSource.NEUTRAL, 1.0F, Mth.randomBetween(this.level.random, 0.8F, 1.2F));
      }

      return interactionresult;
   }

   private void playSearchingSound() {
      if (this.level.isClientSide() && this.tickCount % 20 == 0) {
         this.level.playLocalSound(this.getX(), this.getY(), this.getZ(), SoundEvents.SNIFFER_SEARCHING, this.getSoundSource(), 1.0F, 1.0F, false);
      }

   }

   protected void playStepSound(BlockPos p_272953_, BlockState p_273729_) {
      this.playSound(SoundEvents.SNIFFER_STEP, 0.15F, 1.0F);
   }

   public SoundEvent getEatingSound(ItemStack p_272747_) {
      return SoundEvents.SNIFFER_EAT;
   }

   protected SoundEvent getAmbientSound() {
      return Set.of(Sniffer.State.DIGGING, Sniffer.State.SEARCHING).contains(this.getState()) ? null : SoundEvents.SNIFFER_IDLE;
   }

   protected SoundEvent getHurtSound(DamageSource p_273718_) {
      return SoundEvents.SNIFFER_HURT;
   }

   protected SoundEvent getDeathSound() {
      return SoundEvents.SNIFFER_DEATH;
   }

   public int getMaxHeadYRot() {
      return 50;
   }

   public void setBaby(boolean p_272995_) {
      this.setAge(p_272995_ ? -48000 : 0);
   }

   public AgeableMob getBreedOffspring(ServerLevel p_273401_, AgeableMob p_273310_) {
      return EntityType.SNIFFER.create(p_273401_);
   }

   public boolean canMate(Animal p_272966_) {
      if (!(p_272966_ instanceof Sniffer sniffer)) {
         return false;
      } else {
         Set<Sniffer.State> set = Set.of(Sniffer.State.IDLING, Sniffer.State.SCENTING, Sniffer.State.FEELING_HAPPY);
         return set.contains(this.getState()) && set.contains(sniffer.getState()) && super.canMate(p_272966_);
      }
   }

   public AABB getBoundingBoxForCulling() {
      return super.getBoundingBoxForCulling().inflate((double)0.6F);
   }

   public boolean isFood(ItemStack p_273659_) {
      return p_273659_.is(ItemTags.SNIFFER_FOOD);
   }

   protected Brain<?> makeBrain(Dynamic<?> p_273174_) {
      return SnifferAi.makeBrain(this.brainProvider().makeBrain(p_273174_));
   }

   public Brain<Sniffer> getBrain() {
      return (Brain<Sniffer>)super.getBrain();
   }

   protected Brain.Provider<Sniffer> brainProvider() {
      return Brain.provider(SnifferAi.MEMORY_TYPES, SnifferAi.SENSOR_TYPES);
   }

   protected void customServerAiStep() {
      this.level.getProfiler().push("snifferBrain");
      this.getBrain().tick((ServerLevel)this.level, this);
      this.level.getProfiler().popPush("snifferActivityUpdate");
      SnifferAi.updateActivity(this);
      this.level.getProfiler().pop();
      super.customServerAiStep();
   }

   protected void sendDebugPackets() {
      super.sendDebugPackets();
      DebugPackets.sendEntityBrain(this);
   }

   public static enum State {
      IDLING,
      FEELING_HAPPY,
      SCENTING,
      SNIFFING,
      SEARCHING,
      DIGGING,
      RISING;
   }
}