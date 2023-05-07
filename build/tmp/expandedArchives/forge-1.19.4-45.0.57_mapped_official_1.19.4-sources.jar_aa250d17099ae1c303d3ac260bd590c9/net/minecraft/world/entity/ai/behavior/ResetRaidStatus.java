package net.minecraft.world.entity.ai.behavior;

import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.ai.Brain;
import net.minecraft.world.entity.ai.behavior.declarative.BehaviorBuilder;
import net.minecraft.world.entity.raid.Raid;
import net.minecraft.world.entity.schedule.Activity;

public class ResetRaidStatus {
   public static BehaviorControl<LivingEntity> create() {
      return BehaviorBuilder.create((p_259870_) -> {
         return p_259870_.point((p_260098_, p_259208_, p_259778_) -> {
            if (p_260098_.random.nextInt(20) != 0) {
               return false;
            } else {
               Brain<?> brain = p_259208_.getBrain();
               Raid raid = p_260098_.getRaidAt(p_259208_.blockPosition());
               if (raid == null || raid.isStopped() || raid.isLoss()) {
                  brain.setDefaultActivity(Activity.IDLE);
                  brain.updateActivityFromSchedule(p_260098_.getDayTime(), p_260098_.getGameTime());
               }

               return true;
            }
         });
      });
   }
}