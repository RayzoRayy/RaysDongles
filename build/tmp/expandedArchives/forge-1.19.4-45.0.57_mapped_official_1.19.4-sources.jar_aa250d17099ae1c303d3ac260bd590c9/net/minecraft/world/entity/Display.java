package net.minecraft.world.entity;

import com.mojang.datafixers.util.Pair;
import com.mojang.logging.LogUtils;
import com.mojang.math.Transformation;
import com.mojang.serialization.Codec;
import it.unimi.dsi.fastutil.ints.IntOpenHashSet;
import it.unimi.dsi.fastutil.ints.IntSet;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.function.IntFunction;
import javax.annotation.Nullable;
import net.minecraft.Util;
import net.minecraft.commands.CommandSourceStack;
import net.minecraft.core.registries.Registries;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.nbt.NbtOps;
import net.minecraft.nbt.NbtUtils;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.ComponentUtils;
import net.minecraft.network.protocol.Packet;
import net.minecraft.network.protocol.game.ClientGamePacketListener;
import net.minecraft.network.protocol.game.ClientboundAddEntityPacket;
import net.minecraft.network.syncher.EntityDataAccessor;
import net.minecraft.network.syncher.EntityDataSerializers;
import net.minecraft.network.syncher.SynchedEntityData;
import net.minecraft.util.Brightness;
import net.minecraft.util.ByIdMap;
import net.minecraft.util.FastColor;
import net.minecraft.util.FormattedCharSequence;
import net.minecraft.util.Mth;
import net.minecraft.util.StringRepresentable;
import net.minecraft.world.item.ItemDisplayContext;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.material.PushReaction;
import net.minecraft.world.phys.AABB;
import org.joml.Quaternionf;
import org.joml.Vector3f;
import org.slf4j.Logger;

public abstract class Display extends Entity {
   static final Logger LOGGER = LogUtils.getLogger();
   private static final float INITIAL_UPDATE_PROGRESS = Float.POSITIVE_INFINITY;
   public static final int NO_BRIGHTNESS_OVERRIDE = -1;
   private static final EntityDataAccessor<Integer> DATA_INTERPOLATION_START_DELTA_TICKS_ID = SynchedEntityData.defineId(Display.class, EntityDataSerializers.INT);
   private static final EntityDataAccessor<Integer> DATA_INTERPOLATION_DURATION_ID = SynchedEntityData.defineId(Display.class, EntityDataSerializers.INT);
   private static final EntityDataAccessor<Vector3f> DATA_TRANSLATION_ID = SynchedEntityData.defineId(Display.class, EntityDataSerializers.VECTOR3);
   private static final EntityDataAccessor<Vector3f> DATA_SCALE_ID = SynchedEntityData.defineId(Display.class, EntityDataSerializers.VECTOR3);
   private static final EntityDataAccessor<Quaternionf> DATA_LEFT_ROTATION_ID = SynchedEntityData.defineId(Display.class, EntityDataSerializers.QUATERNION);
   private static final EntityDataAccessor<Quaternionf> DATA_RIGHT_ROTATION_ID = SynchedEntityData.defineId(Display.class, EntityDataSerializers.QUATERNION);
   private static final EntityDataAccessor<Byte> DATA_BILLBOARD_RENDER_CONSTRAINTS_ID = SynchedEntityData.defineId(Display.class, EntityDataSerializers.BYTE);
   private static final EntityDataAccessor<Integer> DATA_BRIGHTNESS_OVERRIDE_ID = SynchedEntityData.defineId(Display.class, EntityDataSerializers.INT);
   private static final EntityDataAccessor<Float> DATA_VIEW_RANGE_ID = SynchedEntityData.defineId(Display.class, EntityDataSerializers.FLOAT);
   private static final EntityDataAccessor<Float> DATA_SHADOW_RADIUS_ID = SynchedEntityData.defineId(Display.class, EntityDataSerializers.FLOAT);
   private static final EntityDataAccessor<Float> DATA_SHADOW_STRENGTH_ID = SynchedEntityData.defineId(Display.class, EntityDataSerializers.FLOAT);
   private static final EntityDataAccessor<Float> DATA_WIDTH_ID = SynchedEntityData.defineId(Display.class, EntityDataSerializers.FLOAT);
   private static final EntityDataAccessor<Float> DATA_HEIGHT_ID = SynchedEntityData.defineId(Display.class, EntityDataSerializers.FLOAT);
   private static final EntityDataAccessor<Integer> DATA_GLOW_COLOR_OVERRIDE_ID = SynchedEntityData.defineId(Display.class, EntityDataSerializers.INT);
   private static final float INITIAL_SHADOW_RADIUS = 0.0F;
   private static final float INITIAL_SHADOW_STRENGTH = 1.0F;
   private static final int NO_GLOW_COLOR_OVERRIDE = -1;
   public static final String TAG_INTERPOLATION_DURATION = "interpolation_duration";
   public static final String TAG_START_INTERPOLATION = "start_interpolation";
   public static final String TAG_TRANSFORMATION = "transformation";
   public static final String TAG_BILLBOARD = "billboard";
   public static final String TAG_BRIGHTNESS = "brightness";
   public static final String TAG_VIEW_RANGE = "view_range";
   public static final String TAG_SHADOW_RADIUS = "shadow_radius";
   public static final String TAG_SHADOW_STRENGTH = "shadow_strength";
   public static final String TAG_WIDTH = "width";
   public static final String TAG_HEIGHT = "height";
   public static final String TAG_GLOW_COLOR_OVERRIDE = "glow_color_override";
   private final Display.GenericInterpolator<Transformation> transformation = new Display.GenericInterpolator<Transformation>(Transformation.identity()) {
      protected Transformation interpolate(float p_270184_, Transformation p_270389_, Transformation p_270084_) {
         return p_270389_.slerp(p_270084_, p_270184_);
      }
   };
   private final Display.FloatInterpolator shadowRadius = new Display.FloatInterpolator(0.0F);
   private final Display.FloatInterpolator shadowStrength = new Display.FloatInterpolator(1.0F);
   private final Quaternionf orientation = new Quaternionf();
   protected final Display.InterpolatorSet interpolators = new Display.InterpolatorSet();
   private long interpolationStartClientTick;
   private float lastProgress;
   private AABB cullingBoundingBox;
   private boolean updateInterpolators;
   private boolean updateTime;

   public Display(EntityType<?> p_270360_, Level p_270280_) {
      super(p_270360_, p_270280_);
      this.noPhysics = true;
      this.noCulling = true;
      this.cullingBoundingBox = this.getBoundingBox();
      this.interpolators.addEntry(Set.of(DATA_TRANSLATION_ID, DATA_LEFT_ROTATION_ID, DATA_SCALE_ID, DATA_RIGHT_ROTATION_ID), (p_276358_, p_276359_) -> {
         this.transformation.updateValue(p_276358_, createTransformation(p_276359_));
      });
      this.interpolators.addEntry(DATA_SHADOW_STRENGTH_ID, this.shadowStrength);
      this.interpolators.addEntry(DATA_SHADOW_RADIUS_ID, this.shadowRadius);
   }

   public void onSyncedDataUpdated(List<SynchedEntityData.DataValue<?>> p_270860_) {
      super.onSyncedDataUpdated(p_270860_);
      boolean flag = false;

      for(SynchedEntityData.DataValue<?> datavalue : p_270860_) {
         flag |= this.interpolators.shouldTriggerUpdate(datavalue.id());
      }

      if (flag) {
         boolean flag1 = this.tickCount <= 0;
         if (flag1) {
            this.interpolators.updateValues(Float.POSITIVE_INFINITY, this.entityData);
         } else {
            this.updateInterpolators = true;
         }
      }

   }

   public void onSyncedDataUpdated(EntityDataAccessor<?> p_270275_) {
      super.onSyncedDataUpdated(p_270275_);
      if (DATA_HEIGHT_ID.equals(p_270275_) || DATA_WIDTH_ID.equals(p_270275_)) {
         this.updateCulling();
      }

      if (DATA_INTERPOLATION_START_DELTA_TICKS_ID.equals(p_270275_)) {
         this.updateTime = true;
      }

   }

   private static Transformation createTransformation(SynchedEntityData p_270278_) {
      Vector3f vector3f = p_270278_.get(DATA_TRANSLATION_ID);
      Quaternionf quaternionf = p_270278_.get(DATA_LEFT_ROTATION_ID);
      Vector3f vector3f1 = p_270278_.get(DATA_SCALE_ID);
      Quaternionf quaternionf1 = p_270278_.get(DATA_RIGHT_ROTATION_ID);
      return new Transformation(vector3f, quaternionf, vector3f1, quaternionf1);
   }

   public void tick() {
      Entity entity = this.getVehicle();
      if (entity != null && entity.isRemoved()) {
         this.stopRiding();
      }

      if (this.level.isClientSide) {
         if (this.updateTime) {
            this.updateTime = false;
            int i = this.getInterpolationDelay();
            this.interpolationStartClientTick = (long)(this.tickCount + i);
         }

         if (this.updateInterpolators) {
            this.updateInterpolators = false;
            this.interpolators.updateValues(this.lastProgress, this.entityData);
         }
      }

   }

   protected void defineSynchedData() {
      this.entityData.define(DATA_INTERPOLATION_START_DELTA_TICKS_ID, 0);
      this.entityData.define(DATA_INTERPOLATION_DURATION_ID, 0);
      this.entityData.define(DATA_TRANSLATION_ID, new Vector3f());
      this.entityData.define(DATA_SCALE_ID, new Vector3f(1.0F, 1.0F, 1.0F));
      this.entityData.define(DATA_RIGHT_ROTATION_ID, new Quaternionf());
      this.entityData.define(DATA_LEFT_ROTATION_ID, new Quaternionf());
      this.entityData.define(DATA_BILLBOARD_RENDER_CONSTRAINTS_ID, Display.BillboardConstraints.FIXED.getId());
      this.entityData.define(DATA_BRIGHTNESS_OVERRIDE_ID, -1);
      this.entityData.define(DATA_VIEW_RANGE_ID, 1.0F);
      this.entityData.define(DATA_SHADOW_RADIUS_ID, 0.0F);
      this.entityData.define(DATA_SHADOW_STRENGTH_ID, 1.0F);
      this.entityData.define(DATA_WIDTH_ID, 0.0F);
      this.entityData.define(DATA_HEIGHT_ID, 0.0F);
      this.entityData.define(DATA_GLOW_COLOR_OVERRIDE_ID, -1);
   }

   protected void readAdditionalSaveData(CompoundTag p_270854_) {
      if (p_270854_.contains("transformation")) {
         Transformation.EXTENDED_CODEC.decode(NbtOps.INSTANCE, p_270854_.get("transformation")).resultOrPartial(Util.prefix("Display entity", LOGGER::error)).ifPresent((p_270952_) -> {
            this.setTransformation(p_270952_.getFirst());
         });
      }

      if (p_270854_.contains("interpolation_duration", 99)) {
         int i = p_270854_.getInt("interpolation_duration");
         this.setInterpolationDuration(i);
      }

      if (p_270854_.contains("start_interpolation", 99)) {
         int j = p_270854_.getInt("start_interpolation");
         this.setInterpolationDelay(j);
      }

      if (p_270854_.contains("billboard", 8)) {
         Display.BillboardConstraints.CODEC.decode(NbtOps.INSTANCE, p_270854_.get("billboard")).resultOrPartial(Util.prefix("Display entity", LOGGER::error)).ifPresent((p_270691_) -> {
            this.setBillboardConstraints(p_270691_.getFirst());
         });
      }

      if (p_270854_.contains("view_range", 99)) {
         this.setViewRange(p_270854_.getFloat("view_range"));
      }

      if (p_270854_.contains("shadow_radius", 99)) {
         this.setShadowRadius(p_270854_.getFloat("shadow_radius"));
      }

      if (p_270854_.contains("shadow_strength", 99)) {
         this.setShadowStrength(p_270854_.getFloat("shadow_strength"));
      }

      if (p_270854_.contains("width", 99)) {
         this.setWidth(p_270854_.getFloat("width"));
      }

      if (p_270854_.contains("height", 99)) {
         this.setHeight(p_270854_.getFloat("height"));
      }

      if (p_270854_.contains("glow_color_override", 99)) {
         this.setGlowColorOverride(p_270854_.getInt("glow_color_override"));
      }

      if (p_270854_.contains("brightness", 10)) {
         Brightness.CODEC.decode(NbtOps.INSTANCE, p_270854_.get("brightness")).resultOrPartial(Util.prefix("Display entity", LOGGER::error)).ifPresent((p_270247_) -> {
            this.setBrightnessOverride(p_270247_.getFirst());
         });
      } else {
         this.setBrightnessOverride((Brightness)null);
      }

   }

   private void setTransformation(Transformation p_270186_) {
      this.entityData.set(DATA_TRANSLATION_ID, p_270186_.getTranslation());
      this.entityData.set(DATA_LEFT_ROTATION_ID, p_270186_.getLeftRotation());
      this.entityData.set(DATA_SCALE_ID, p_270186_.getScale());
      this.entityData.set(DATA_RIGHT_ROTATION_ID, p_270186_.getRightRotation());
   }

   protected void addAdditionalSaveData(CompoundTag p_270779_) {
      Transformation.EXTENDED_CODEC.encodeStart(NbtOps.INSTANCE, createTransformation(this.entityData)).result().ifPresent((p_270528_) -> {
         p_270779_.put("transformation", p_270528_);
      });
      Display.BillboardConstraints.CODEC.encodeStart(NbtOps.INSTANCE, this.getBillboardConstraints()).result().ifPresent((p_270227_) -> {
         p_270779_.put("billboard", p_270227_);
      });
      p_270779_.putInt("interpolation_duration", this.getInterpolationDuration());
      p_270779_.putFloat("view_range", this.getViewRange());
      p_270779_.putFloat("shadow_radius", this.getShadowRadius());
      p_270779_.putFloat("shadow_strength", this.getShadowStrength());
      p_270779_.putFloat("width", this.getWidth());
      p_270779_.putFloat("height", this.getHeight());
      p_270779_.putInt("glow_color_override", this.getGlowColorOverride());
      Brightness brightness = this.getBrightnessOverride();
      if (brightness != null) {
         Brightness.CODEC.encodeStart(NbtOps.INSTANCE, brightness).result().ifPresent((p_270121_) -> {
            p_270779_.put("brightness", p_270121_);
         });
      }

   }

   public Packet<ClientGamePacketListener> getAddEntityPacket() {
      return new ClientboundAddEntityPacket(this);
   }

   public AABB getBoundingBoxForCulling() {
      return this.cullingBoundingBox;
   }

   public PushReaction getPistonPushReaction() {
      return PushReaction.IGNORE;
   }

   public Quaternionf orientation() {
      return this.orientation;
   }

   public Transformation transformation(float p_270349_) {
      return this.transformation.get(p_270349_);
   }

   private void setInterpolationDuration(int p_270803_) {
      this.entityData.set(DATA_INTERPOLATION_DURATION_ID, p_270803_);
   }

   private int getInterpolationDuration() {
      return this.entityData.get(DATA_INTERPOLATION_DURATION_ID);
   }

   private void setInterpolationDelay(int p_276366_) {
      this.entityData.set(DATA_INTERPOLATION_START_DELTA_TICKS_ID, p_276366_, true);
   }

   private int getInterpolationDelay() {
      return this.entityData.get(DATA_INTERPOLATION_START_DELTA_TICKS_ID);
   }

   private void setBillboardConstraints(Display.BillboardConstraints p_270345_) {
      this.entityData.set(DATA_BILLBOARD_RENDER_CONSTRAINTS_ID, p_270345_.getId());
   }

   public Display.BillboardConstraints getBillboardConstraints() {
      return Display.BillboardConstraints.BY_ID.apply(this.entityData.get(DATA_BILLBOARD_RENDER_CONSTRAINTS_ID));
   }

   private void setBrightnessOverride(@Nullable Brightness p_270461_) {
      this.entityData.set(DATA_BRIGHTNESS_OVERRIDE_ID, p_270461_ != null ? p_270461_.pack() : -1);
   }

   @Nullable
   private Brightness getBrightnessOverride() {
      int i = this.entityData.get(DATA_BRIGHTNESS_OVERRIDE_ID);
      return i != -1 ? Brightness.unpack(i) : null;
   }

   public int getPackedBrightnessOverride() {
      return this.entityData.get(DATA_BRIGHTNESS_OVERRIDE_ID);
   }

   private void setViewRange(float p_270907_) {
      this.entityData.set(DATA_VIEW_RANGE_ID, p_270907_);
   }

   private float getViewRange() {
      return this.entityData.get(DATA_VIEW_RANGE_ID);
   }

   private void setShadowRadius(float p_270122_) {
      this.entityData.set(DATA_SHADOW_RADIUS_ID, p_270122_);
   }

   private float getShadowRadius() {
      return this.entityData.get(DATA_SHADOW_RADIUS_ID);
   }

   public float getShadowRadius(float p_270525_) {
      return this.shadowRadius.get(p_270525_);
   }

   private void setShadowStrength(float p_270866_) {
      this.entityData.set(DATA_SHADOW_STRENGTH_ID, p_270866_);
   }

   private float getShadowStrength() {
      return this.entityData.get(DATA_SHADOW_STRENGTH_ID);
   }

   public float getShadowStrength(float p_270465_) {
      return this.shadowStrength.get(p_270465_);
   }

   private void setWidth(float p_270741_) {
      this.entityData.set(DATA_WIDTH_ID, p_270741_);
   }

   private float getWidth() {
      return this.entityData.get(DATA_WIDTH_ID);
   }

   private void setHeight(float p_270716_) {
      this.entityData.set(DATA_HEIGHT_ID, p_270716_);
   }

   private int getGlowColorOverride() {
      return this.entityData.get(DATA_GLOW_COLOR_OVERRIDE_ID);
   }

   private void setGlowColorOverride(int p_270784_) {
      this.entityData.set(DATA_GLOW_COLOR_OVERRIDE_ID, p_270784_);
   }

   public float calculateInterpolationProgress(float p_272675_) {
      int i = this.getInterpolationDuration();
      if (i <= 0) {
         return 1.0F;
      } else {
         float f = (float)((long)this.tickCount - this.interpolationStartClientTick);
         float f1 = f + p_272675_;
         float f2 = Mth.clamp(Mth.inverseLerp(f1, 0.0F, (float)i), 0.0F, 1.0F);
         this.lastProgress = f2;
         return f2;
      }
   }

   private float getHeight() {
      return this.entityData.get(DATA_HEIGHT_ID);
   }

   public void setPos(double p_270091_, double p_270983_, double p_270419_) {
      super.setPos(p_270091_, p_270983_, p_270419_);
      this.updateCulling();
   }

   private void updateCulling() {
      float f = this.getWidth();
      float f1 = this.getHeight();
      if (f != 0.0F && f1 != 0.0F) {
         this.noCulling = false;
         float f2 = f / 2.0F;
         double d0 = this.getX();
         double d1 = this.getY();
         double d2 = this.getZ();
         this.cullingBoundingBox = new AABB(d0 - (double)f2, d1, d2 - (double)f2, d0 + (double)f2, d1 + (double)f1, d2 + (double)f2);
      } else {
         this.noCulling = true;
      }

   }

   public void setXRot(float p_270257_) {
      super.setXRot(p_270257_);
      this.updateOrientation();
   }

   public void setYRot(float p_270921_) {
      super.setYRot(p_270921_);
      this.updateOrientation();
   }

   private void updateOrientation() {
      this.orientation.rotationYXZ(-0.017453292F * this.getYRot(), ((float)Math.PI / 180F) * this.getXRot(), 0.0F);
   }

   public boolean shouldRenderAtSqrDistance(double p_270991_) {
      return p_270991_ < Mth.square((double)this.getViewRange() * 64.0D * getViewScale());
   }

   public int getTeamColor() {
      int i = this.getGlowColorOverride();
      return i != -1 ? i : super.getTeamColor();
   }

   public static enum BillboardConstraints implements StringRepresentable {
      FIXED((byte)0, "fixed"),
      VERTICAL((byte)1, "vertical"),
      HORIZONTAL((byte)2, "horizontal"),
      CENTER((byte)3, "center");

      public static final Codec<Display.BillboardConstraints> CODEC = StringRepresentable.fromEnum(Display.BillboardConstraints::values);
      public static final IntFunction<Display.BillboardConstraints> BY_ID = ByIdMap.continuous(Display.BillboardConstraints::getId, values(), ByIdMap.OutOfBoundsStrategy.ZERO);
      private final byte id;
      private final String name;

      private BillboardConstraints(byte p_270785_, String p_270544_) {
         this.name = p_270544_;
         this.id = p_270785_;
      }

      public String getSerializedName() {
         return this.name;
      }

      byte getId() {
         return this.id;
      }
   }

   public static class BlockDisplay extends Display {
      public static final String TAG_BLOCK_STATE = "block_state";
      private static final EntityDataAccessor<BlockState> DATA_BLOCK_STATE_ID = SynchedEntityData.defineId(Display.BlockDisplay.class, EntityDataSerializers.BLOCK_STATE);

      public BlockDisplay(EntityType<?> p_271022_, Level p_270442_) {
         super(p_271022_, p_270442_);
      }

      protected void defineSynchedData() {
         super.defineSynchedData();
         this.entityData.define(DATA_BLOCK_STATE_ID, Blocks.AIR.defaultBlockState());
      }

      public BlockState getBlockState() {
         return this.entityData.get(DATA_BLOCK_STATE_ID);
      }

      public void setBlockState(BlockState p_270267_) {
         this.entityData.set(DATA_BLOCK_STATE_ID, p_270267_);
      }

      protected void readAdditionalSaveData(CompoundTag p_270139_) {
         super.readAdditionalSaveData(p_270139_);
         this.setBlockState(NbtUtils.readBlockState(this.level.holderLookup(Registries.BLOCK), p_270139_.getCompound("block_state")));
      }

      protected void addAdditionalSaveData(CompoundTag p_270469_) {
         super.addAdditionalSaveData(p_270469_);
         p_270469_.put("block_state", NbtUtils.writeBlockState(this.getBlockState()));
      }
   }

   static class ColorInterpolator extends Display.IntInterpolator {
      protected ColorInterpolator(int p_270450_) {
         super(p_270450_);
      }

      protected int interpolate(float p_270505_, int p_270271_, int p_270171_) {
         return FastColor.ARGB32.lerp(p_270505_, p_270271_, p_270171_);
      }
   }

   static class FloatInterpolator extends Display.Interpolator<Float> {
      protected FloatInterpolator(float p_270764_) {
         super(p_270764_);
      }

      protected float interpolate(float p_270533_, float p_270482_, float p_270980_) {
         return Mth.lerp(p_270533_, p_270482_, p_270980_);
      }

      public float get(float p_270330_) {
         return !((double)p_270330_ >= 1.0D) && this.lastValue != null ? this.interpolate(p_270330_, this.lastValue, this.currentValue) : this.currentValue;
      }

      protected Float getGeneric(float p_276367_) {
         return this.get(p_276367_);
      }
   }

   abstract static class GenericInterpolator<T> extends Display.Interpolator<T> {
      protected GenericInterpolator(T p_270744_) {
         super(p_270744_);
      }

      protected abstract T interpolate(float p_270830_, T p_270924_, T p_270755_);

      public T get(float p_270270_) {
         return (T)(!((double)p_270270_ >= 1.0D) && this.lastValue != null ? this.interpolate(p_270270_, this.lastValue, this.currentValue) : this.currentValue);
      }

      protected T getGeneric(float p_276362_) {
         return this.get(p_276362_);
      }
   }

   static class IntInterpolator extends Display.Interpolator<Integer> {
      protected IntInterpolator(int p_270128_) {
         super(p_270128_);
      }

      protected int interpolate(float p_270179_, int p_270484_, int p_270734_) {
         return Mth.lerpInt(p_270179_, p_270484_, p_270734_);
      }

      public int get(float p_270183_) {
         return !((double)p_270183_ >= 1.0D) && this.lastValue != null ? this.interpolate(p_270183_, this.lastValue, this.currentValue) : this.currentValue;
      }

      protected Integer getGeneric(float p_276372_) {
         return this.get(p_276372_);
      }
   }

   @FunctionalInterface
   interface IntepolatorUpdater {
      void update(float p_276309_, SynchedEntityData p_276315_);
   }

   abstract static class Interpolator<T> {
      @Nullable
      protected T lastValue;
      protected T currentValue;

      protected Interpolator(T p_270191_) {
         this.currentValue = p_270191_;
      }

      protected abstract T getGeneric(float p_276365_);

      public void updateValue(float p_276301_, T p_276291_) {
         if (p_276301_ != Float.POSITIVE_INFINITY) {
            this.lastValue = this.getGeneric(p_276301_);
         }

         this.currentValue = p_276291_;
      }
   }

   static class InterpolatorSet {
      private final IntSet interpolatedData = new IntOpenHashSet();
      private final List<Display.IntepolatorUpdater> updaters = new ArrayList<>();

      protected <T> void addEntry(EntityDataAccessor<T> p_270106_, Display.Interpolator<T> p_270238_) {
         this.interpolatedData.add(p_270106_.getId());
         this.updaters.add((p_276250_, p_276251_) -> {
            p_270238_.updateValue(p_276250_, p_276251_.get(p_270106_));
         });
      }

      protected void addEntry(Set<EntityDataAccessor<?>> p_270337_, Display.IntepolatorUpdater p_276300_) {
         for(EntityDataAccessor<?> entitydataaccessor : p_270337_) {
            this.interpolatedData.add(entitydataaccessor.getId());
         }

         this.updaters.add(p_276300_);
      }

      public boolean shouldTriggerUpdate(int p_270333_) {
         return this.interpolatedData.contains(p_270333_);
      }

      public void updateValues(float p_276304_, SynchedEntityData p_276273_) {
         for(Display.IntepolatorUpdater display$intepolatorupdater : this.updaters) {
            display$intepolatorupdater.update(p_276304_, p_276273_);
         }

      }
   }

   public static class ItemDisplay extends Display {
      private static final String TAG_ITEM = "item";
      private static final String TAG_ITEM_DISPLAY = "item_display";
      private static final EntityDataAccessor<ItemStack> DATA_ITEM_STACK_ID = SynchedEntityData.defineId(Display.ItemDisplay.class, EntityDataSerializers.ITEM_STACK);
      private static final EntityDataAccessor<Byte> DATA_ITEM_DISPLAY_ID = SynchedEntityData.defineId(Display.ItemDisplay.class, EntityDataSerializers.BYTE);
      private final SlotAccess slot = new SlotAccess() {
         public ItemStack get() {
            return ItemDisplay.this.getItemStack();
         }

         public boolean set(ItemStack p_270697_) {
            ItemDisplay.this.setItemStack(p_270697_);
            return true;
         }
      };

      public ItemDisplay(EntityType<?> p_270104_, Level p_270735_) {
         super(p_270104_, p_270735_);
      }

      protected void defineSynchedData() {
         super.defineSynchedData();
         this.entityData.define(DATA_ITEM_STACK_ID, ItemStack.EMPTY);
         this.entityData.define(DATA_ITEM_DISPLAY_ID, ItemDisplayContext.NONE.getId());
      }

      public ItemStack getItemStack() {
         return this.entityData.get(DATA_ITEM_STACK_ID);
      }

      void setItemStack(ItemStack p_270310_) {
         this.entityData.set(DATA_ITEM_STACK_ID, p_270310_);
      }

      private void setItemTransform(ItemDisplayContext p_270370_) {
         this.entityData.set(DATA_ITEM_DISPLAY_ID, p_270370_.getId());
      }

      public ItemDisplayContext getItemTransform() {
         return ItemDisplayContext.BY_ID.apply(this.entityData.get(DATA_ITEM_DISPLAY_ID));
      }

      protected void readAdditionalSaveData(CompoundTag p_270713_) {
         super.readAdditionalSaveData(p_270713_);
         this.setItemStack(ItemStack.of(p_270713_.getCompound("item")));
         if (p_270713_.contains("item_display", 8)) {
            ItemDisplayContext.CODEC.decode(NbtOps.INSTANCE, p_270713_.get("item_display")).resultOrPartial(Util.prefix("Display entity", Display.LOGGER::error)).ifPresent((p_270456_) -> {
               this.setItemTransform(p_270456_.getFirst());
            });
         }

      }

      protected void addAdditionalSaveData(CompoundTag p_270669_) {
         super.addAdditionalSaveData(p_270669_);
         p_270669_.put("item", this.getItemStack().save(new CompoundTag()));
         ItemDisplayContext.CODEC.encodeStart(NbtOps.INSTANCE, this.getItemTransform()).result().ifPresent((p_270615_) -> {
            p_270669_.put("item_display", p_270615_);
         });
      }

      public SlotAccess getSlot(int p_270599_) {
         return p_270599_ == 0 ? this.slot : SlotAccess.NULL;
      }
   }

   public static class TextDisplay extends Display {
      public static final String TAG_TEXT = "text";
      private static final String TAG_LINE_WIDTH = "line_width";
      private static final String TAG_TEXT_OPACITY = "text_opacity";
      private static final String TAG_BACKGROUND_COLOR = "background";
      private static final String TAG_SHADOW = "shadow";
      private static final String TAG_SEE_THROUGH = "see_through";
      private static final String TAG_USE_DEFAULT_BACKGROUND = "default_background";
      private static final String TAG_ALIGNMENT = "alignment";
      public static final byte FLAG_SHADOW = 1;
      public static final byte FLAG_SEE_THROUGH = 2;
      public static final byte FLAG_USE_DEFAULT_BACKGROUND = 4;
      public static final byte FLAG_ALIGN_LEFT = 8;
      public static final byte FLAG_ALIGN_RIGHT = 16;
      private static final byte INITIAL_TEXT_OPACITY = -1;
      public static final int INITIAL_BACKGROUND = 1073741824;
      private static final EntityDataAccessor<Component> DATA_TEXT_ID = SynchedEntityData.defineId(Display.TextDisplay.class, EntityDataSerializers.COMPONENT);
      private static final EntityDataAccessor<Integer> DATA_LINE_WIDTH_ID = SynchedEntityData.defineId(Display.TextDisplay.class, EntityDataSerializers.INT);
      private static final EntityDataAccessor<Integer> DATA_BACKGROUND_COLOR_ID = SynchedEntityData.defineId(Display.TextDisplay.class, EntityDataSerializers.INT);
      private static final EntityDataAccessor<Byte> DATA_TEXT_OPACITY_ID = SynchedEntityData.defineId(Display.TextDisplay.class, EntityDataSerializers.BYTE);
      private static final EntityDataAccessor<Byte> DATA_STYLE_FLAGS_ID = SynchedEntityData.defineId(Display.TextDisplay.class, EntityDataSerializers.BYTE);
      private final Display.IntInterpolator textOpacity = new Display.IntInterpolator(-1);
      private final Display.IntInterpolator backgroundColor = new Display.ColorInterpolator(1073741824);
      @Nullable
      private Display.TextDisplay.CachedInfo clientDisplayCache;

      public TextDisplay(EntityType<?> p_270708_, Level p_270736_) {
         super(p_270708_, p_270736_);
         this.interpolators.addEntry(DATA_BACKGROUND_COLOR_ID, this.backgroundColor);
         this.interpolators.addEntry(Set.of(DATA_TEXT_OPACITY_ID), (p_276360_, p_276361_) -> {
            this.textOpacity.updateValue(p_276360_, Integer.valueOf(p_276361_.get(DATA_TEXT_OPACITY_ID) & 255));
         });
      }

      protected void defineSynchedData() {
         super.defineSynchedData();
         this.entityData.define(DATA_TEXT_ID, Component.empty());
         this.entityData.define(DATA_LINE_WIDTH_ID, 200);
         this.entityData.define(DATA_BACKGROUND_COLOR_ID, 1073741824);
         this.entityData.define(DATA_TEXT_OPACITY_ID, (byte)-1);
         this.entityData.define(DATA_STYLE_FLAGS_ID, (byte)0);
      }

      public void onSyncedDataUpdated(EntityDataAccessor<?> p_270797_) {
         super.onSyncedDataUpdated(p_270797_);
         this.clientDisplayCache = null;
      }

      public Component getText() {
         return this.entityData.get(DATA_TEXT_ID);
      }

      private void setText(Component p_270902_) {
         this.entityData.set(DATA_TEXT_ID, p_270902_);
      }

      public int getLineWidth() {
         return this.entityData.get(DATA_LINE_WIDTH_ID);
      }

      private void setLineWidth(int p_270545_) {
         this.entityData.set(DATA_LINE_WIDTH_ID, p_270545_);
      }

      public byte getTextOpacity(float p_270287_) {
         return (byte)this.textOpacity.get(p_270287_);
      }

      private byte getTextOpacity() {
         return this.entityData.get(DATA_TEXT_OPACITY_ID);
      }

      private void setTextOpacity(byte p_270583_) {
         this.entityData.set(DATA_TEXT_OPACITY_ID, p_270583_);
      }

      public int getBackgroundColor(float p_270652_) {
         return this.backgroundColor.get(p_270652_);
      }

      private int getBackgroundColor() {
         return this.entityData.get(DATA_BACKGROUND_COLOR_ID);
      }

      private void setBackgroundColor(int p_270241_) {
         this.entityData.set(DATA_BACKGROUND_COLOR_ID, p_270241_);
      }

      public byte getFlags() {
         return this.entityData.get(DATA_STYLE_FLAGS_ID);
      }

      private void setFlags(byte p_270855_) {
         this.entityData.set(DATA_STYLE_FLAGS_ID, p_270855_);
      }

      private static byte loadFlag(byte p_270219_, CompoundTag p_270994_, String p_270958_, byte p_270701_) {
         return p_270994_.getBoolean(p_270958_) ? (byte)(p_270219_ | p_270701_) : p_270219_;
      }

      protected void readAdditionalSaveData(CompoundTag p_270714_) {
         super.readAdditionalSaveData(p_270714_);
         if (p_270714_.contains("line_width", 99)) {
            this.setLineWidth(p_270714_.getInt("line_width"));
         }

         if (p_270714_.contains("text_opacity", 99)) {
            this.setTextOpacity(p_270714_.getByte("text_opacity"));
         }

         if (p_270714_.contains("background", 99)) {
            this.setBackgroundColor(p_270714_.getInt("background"));
         }

         byte b0 = loadFlag((byte)0, p_270714_, "shadow", (byte)1);
         b0 = loadFlag(b0, p_270714_, "see_through", (byte)2);
         b0 = loadFlag(b0, p_270714_, "default_background", (byte)4);
         Optional<Display.TextDisplay.Align> optional = Display.TextDisplay.Align.CODEC.decode(NbtOps.INSTANCE, p_270714_.get("alignment")).resultOrPartial(Util.prefix("Display entity", Display.LOGGER::error)).map(Pair::getFirst);
         if (optional.isPresent()) {
            byte b1;
            switch ((Display.TextDisplay.Align)optional.get()) {
               case CENTER:
                  b1 = b0;
                  break;
               case LEFT:
                  b1 = (byte)(b0 | 8);
                  break;
               case RIGHT:
                  b1 = (byte)(b0 | 16);
                  break;
               default:
                  throw new IncompatibleClassChangeError();
            }

            b0 = b1;
         }

         this.setFlags(b0);
         if (p_270714_.contains("text", 8)) {
            String s = p_270714_.getString("text");

            try {
               Component component = Component.Serializer.fromJson(s);
               if (component != null) {
                  CommandSourceStack commandsourcestack = this.createCommandSourceStack().withPermission(2);
                  Component component1 = ComponentUtils.updateForEntity(commandsourcestack, component, this, 0);
                  this.setText(component1);
               } else {
                  this.setText(Component.empty());
               }
            } catch (Exception exception) {
               Display.LOGGER.warn("Failed to parse display entity text {}", s, exception);
            }
         }

      }

      private static void storeFlag(byte p_270879_, CompoundTag p_270177_, String p_270294_, byte p_270853_) {
         p_270177_.putBoolean(p_270294_, (p_270879_ & p_270853_) != 0);
      }

      protected void addAdditionalSaveData(CompoundTag p_270268_) {
         super.addAdditionalSaveData(p_270268_);
         p_270268_.putString("text", Component.Serializer.toJson(this.getText()));
         p_270268_.putInt("line_width", this.getLineWidth());
         p_270268_.putInt("background", this.getBackgroundColor());
         p_270268_.putByte("text_opacity", this.getTextOpacity());
         byte b0 = this.getFlags();
         storeFlag(b0, p_270268_, "shadow", (byte)1);
         storeFlag(b0, p_270268_, "see_through", (byte)2);
         storeFlag(b0, p_270268_, "default_background", (byte)4);
         Display.TextDisplay.Align.CODEC.encodeStart(NbtOps.INSTANCE, getAlign(b0)).result().ifPresent((p_271001_) -> {
            p_270268_.put("alignment", p_271001_);
         });
      }

      public Display.TextDisplay.CachedInfo cacheDisplay(Display.TextDisplay.LineSplitter p_270682_) {
         if (this.clientDisplayCache == null) {
            int i = this.getLineWidth();
            this.clientDisplayCache = p_270682_.split(this.getText(), i);
         }

         return this.clientDisplayCache;
      }

      public static Display.TextDisplay.Align getAlign(byte p_270911_) {
         if ((p_270911_ & 8) != 0) {
            return Display.TextDisplay.Align.LEFT;
         } else {
            return (p_270911_ & 16) != 0 ? Display.TextDisplay.Align.RIGHT : Display.TextDisplay.Align.CENTER;
         }
      }

      public static enum Align implements StringRepresentable {
         CENTER("center"),
         LEFT("left"),
         RIGHT("right");

         public static final Codec<Display.TextDisplay.Align> CODEC = StringRepresentable.fromEnum(Display.TextDisplay.Align::values);
         private final String name;

         private Align(String p_270554_) {
            this.name = p_270554_;
         }

         public String getSerializedName() {
            return this.name;
         }
      }

      public static record CachedInfo(List<Display.TextDisplay.CachedLine> lines, int width) {
      }

      public static record CachedLine(FormattedCharSequence contents, int width) {
      }

      @FunctionalInterface
      public interface LineSplitter {
         Display.TextDisplay.CachedInfo split(Component p_270086_, int p_270526_);
      }
   }
}