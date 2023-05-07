package net.minecraft.world.damagesource;

import com.google.common.collect.Lists;
import java.util.List;
import java.util.Optional;
import javax.annotation.Nullable;
import net.minecraft.core.BlockPos;
import net.minecraft.network.chat.ClickEvent;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.ComponentUtils;
import net.minecraft.network.chat.HoverEvent;
import net.minecraft.network.chat.Style;
import net.minecraft.tags.BlockTags;
import net.minecraft.tags.DamageTypeTags;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockState;

public class CombatTracker {
   public static final int RESET_DAMAGE_STATUS_TIME = 100;
   public static final int RESET_COMBAT_STATUS_TIME = 300;
   private static final Style INTENTIONAL_GAME_DESIGN_STYLE = Style.EMPTY.withClickEvent(new ClickEvent(ClickEvent.Action.OPEN_URL, "https://bugs.mojang.com/browse/MCPE-28723")).withHoverEvent(new HoverEvent(HoverEvent.Action.SHOW_TEXT, Component.literal("MCPE-28723")));
   private final List<CombatEntry> entries = Lists.newArrayList();
   private final LivingEntity mob;
   private int lastDamageTime;
   private int combatStartTime;
   private int combatEndTime;
   private boolean inCombat;
   private boolean takingDamage;
   @Nullable
   private String nextLocation;

   public CombatTracker(LivingEntity p_19285_) {
      this.mob = p_19285_;
   }

   public void prepareForDamage() {
      this.resetPreparedStatus();
      Optional<BlockPos> optional = this.mob.getLastClimbablePos();
      if (optional.isPresent()) {
         BlockState blockstate = this.mob.level.getBlockState(optional.get());
         if (!blockstate.is(Blocks.LADDER) && !blockstate.is(BlockTags.TRAPDOORS)) {
            if (blockstate.is(Blocks.VINE)) {
               this.nextLocation = "vines";
            } else if (!blockstate.is(Blocks.WEEPING_VINES) && !blockstate.is(Blocks.WEEPING_VINES_PLANT)) {
               if (!blockstate.is(Blocks.TWISTING_VINES) && !blockstate.is(Blocks.TWISTING_VINES_PLANT)) {
                  if (blockstate.is(Blocks.SCAFFOLDING)) {
                     this.nextLocation = "scaffolding";
                  } else {
                     this.nextLocation = "other_climbable";
                  }
               } else {
                  this.nextLocation = "twisting_vines";
               }
            } else {
               this.nextLocation = "weeping_vines";
            }
         } else {
            this.nextLocation = "ladder";
         }
      } else if (this.mob.isInWater()) {
         this.nextLocation = "water";
      }

   }

   public void recordDamage(DamageSource p_19290_, float p_19291_, float p_19292_) {
      this.recheckStatus();
      this.prepareForDamage();
      CombatEntry combatentry = new CombatEntry(p_19290_, this.mob.tickCount, p_19291_, p_19292_, this.nextLocation, this.mob.fallDistance);
      this.entries.add(combatentry);
      this.lastDamageTime = this.mob.tickCount;
      this.takingDamage = true;
      if (combatentry.isCombatRelated() && !this.inCombat && this.mob.isAlive()) {
         this.inCombat = true;
         this.combatStartTime = this.mob.tickCount;
         this.combatEndTime = this.combatStartTime;
         this.mob.onEnterCombat();
      }

   }

   public Component getDeathMessage() {
      if (this.entries.isEmpty()) {
         return Component.translatable("death.attack.generic", this.mob.getDisplayName());
      } else {
         CombatEntry combatentry = this.getMostSignificantFall();
         CombatEntry combatentry1 = this.entries.get(this.entries.size() - 1);
         Component component1 = combatentry1.getAttackerName();
         DamageSource damagesource = combatentry1.getSource();
         Entity entity = damagesource.getEntity();
         DeathMessageType deathmessagetype = damagesource.type().deathMessageType();
         Component component;
         if (combatentry != null && deathmessagetype == DeathMessageType.FALL_VARIANTS) {
            Component component3 = combatentry.getAttackerName();
            DamageSource damagesource1 = combatentry.getSource();
            if (!damagesource1.is(DamageTypeTags.IS_FALL) && !damagesource1.is(DamageTypeTags.ALWAYS_MOST_SIGNIFICANT_FALL)) {
               if (component3 != null && !component3.equals(component1)) {
                  Entity entity1 = damagesource1.getEntity();
                  ItemStack itemstack2;
                  if (entity1 instanceof LivingEntity) {
                     LivingEntity livingentity1 = (LivingEntity)entity1;
                     itemstack2 = livingentity1.getMainHandItem();
                  } else {
                     itemstack2 = ItemStack.EMPTY;
                  }

                  ItemStack itemstack1 = itemstack2;
                  if (!itemstack1.isEmpty() && itemstack1.hasCustomHoverName()) {
                     component = Component.translatable("death.fell.assist.item", this.mob.getDisplayName(), component3, itemstack1.getDisplayName());
                  } else {
                     component = Component.translatable("death.fell.assist", this.mob.getDisplayName(), component3);
                  }
               } else if (component1 != null) {
                  ItemStack itemstack3;
                  if (entity instanceof LivingEntity) {
                     LivingEntity livingentity = (LivingEntity)entity;
                     itemstack3 = livingentity.getMainHandItem();
                  } else {
                     itemstack3 = ItemStack.EMPTY;
                  }

                  ItemStack itemstack = itemstack3;
                  if (!itemstack.isEmpty() && itemstack.hasCustomHoverName()) {
                     component = Component.translatable("death.fell.finish.item", this.mob.getDisplayName(), component1, itemstack.getDisplayName());
                  } else {
                     component = Component.translatable("death.fell.finish", this.mob.getDisplayName(), component1);
                  }
               } else {
                  component = Component.translatable("death.fell.killer", this.mob.getDisplayName());
               }
            } else {
               component = Component.translatable("death.fell.accident." + this.getFallLocation(combatentry), this.mob.getDisplayName());
            }
         } else {
            if (deathmessagetype == DeathMessageType.INTENTIONAL_GAME_DESIGN) {
               String s = "death.attack." + damagesource.getMsgId();
               Component component2 = ComponentUtils.wrapInSquareBrackets(Component.translatable(s + ".link")).withStyle(INTENTIONAL_GAME_DESIGN_STYLE);
               return Component.translatable(s + ".message", this.mob.getDisplayName(), component2);
            }

            component = damagesource.getLocalizedDeathMessage(this.mob);
         }

         return component;
      }
   }

   @Nullable
   public LivingEntity getKiller() {
      LivingEntity livingentity = null;
      Player player = null;
      float f = 0.0F;
      float f1 = 0.0F;

      for(CombatEntry combatentry : this.entries) {
         Entity entity = combatentry.getSource().getEntity();
         if (entity instanceof Player player1) {
            if (player == null || combatentry.getDamage() > f1) {
               f1 = combatentry.getDamage();
               player = player1;
            }
         }

         entity = combatentry.getSource().getEntity();
         if (entity instanceof LivingEntity livingentity1) {
            if (livingentity == null || combatentry.getDamage() > f) {
               f = combatentry.getDamage();
               livingentity = livingentity1;
            }
         }
      }

      return (LivingEntity)(player != null && f1 >= f / 3.0F ? player : livingentity);
   }

   @Nullable
   private CombatEntry getMostSignificantFall() {
      CombatEntry combatentry = null;
      CombatEntry combatentry1 = null;
      float f = 0.0F;
      float f1 = 0.0F;

      for(int i = 0; i < this.entries.size(); ++i) {
         CombatEntry combatentry2 = this.entries.get(i);
         CombatEntry combatentry3 = i > 0 ? this.entries.get(i - 1) : null;
         DamageSource damagesource = combatentry2.getSource();
         boolean flag = damagesource.is(DamageTypeTags.ALWAYS_MOST_SIGNIFICANT_FALL);
         float f2 = flag ? Float.MAX_VALUE : combatentry2.getFallDistance();
         if ((damagesource.is(DamageTypeTags.IS_FALL) || flag) && f2 > 0.0F && (combatentry == null || f2 > f1)) {
            if (i > 0) {
               combatentry = combatentry3;
            } else {
               combatentry = combatentry2;
            }

            f1 = f2;
         }

         if (combatentry2.getLocation() != null && (combatentry1 == null || combatentry2.getDamage() > f)) {
            combatentry1 = combatentry2;
            f = combatentry2.getDamage();
         }
      }

      if (f1 > 5.0F && combatentry != null) {
         return combatentry;
      } else {
         return f > 5.0F && combatentry1 != null ? combatentry1 : null;
      }
   }

   private String getFallLocation(CombatEntry p_19288_) {
      return p_19288_.getLocation() == null ? "generic" : p_19288_.getLocation();
   }

   public boolean isTakingDamage() {
      this.recheckStatus();
      return this.takingDamage;
   }

   public boolean isInCombat() {
      this.recheckStatus();
      return this.inCombat;
   }

   public int getCombatDuration() {
      return this.inCombat ? this.mob.tickCount - this.combatStartTime : this.combatEndTime - this.combatStartTime;
   }

   private void resetPreparedStatus() {
      this.nextLocation = null;
   }

   public void recheckStatus() {
      int i = this.inCombat ? 300 : 100;
      if (this.takingDamage && (!this.mob.isAlive() || this.mob.tickCount - this.lastDamageTime > i)) {
         boolean flag = this.inCombat;
         this.takingDamage = false;
         this.inCombat = false;
         this.combatEndTime = this.mob.tickCount;
         if (flag) {
            this.mob.onLeaveCombat();
         }

         this.entries.clear();
      }

   }

   public LivingEntity getMob() {
      return this.mob;
   }

   @Nullable
   public CombatEntry getLastEntry() {
      return this.entries.isEmpty() ? null : this.entries.get(this.entries.size() - 1);
   }

   public int getKillerId() {
      LivingEntity livingentity = this.getKiller();
      return livingentity == null ? -1 : livingentity.getId();
   }
}