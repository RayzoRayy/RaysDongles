package net.minecraft.world.entity.ai.behavior;

import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.ai.behavior.declarative.BehaviorBuilder;

public class UpdateActivityFromSchedule {
   public static BehaviorControl<LivingEntity> create() {
      return BehaviorBuilder.create((p_259429_) -> {
         return p_259429_.point((p_269884_, p_269885_, p_269886_) -> {
            p_269885_.getBrain().updateActivityFromSchedule(p_269884_.getDayTime(), p_269884_.getGameTime());
            return true;
         });
      });
   }
}