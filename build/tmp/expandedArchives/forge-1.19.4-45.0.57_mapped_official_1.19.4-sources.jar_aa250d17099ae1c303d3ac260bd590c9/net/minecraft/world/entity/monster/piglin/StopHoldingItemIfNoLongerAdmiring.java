package net.minecraft.world.entity.monster.piglin;

import net.minecraft.world.entity.ai.behavior.BehaviorControl;
import net.minecraft.world.entity.ai.behavior.declarative.BehaviorBuilder;
import net.minecraft.world.entity.ai.memory.MemoryModuleType;
import net.minecraft.world.item.Items;

public class StopHoldingItemIfNoLongerAdmiring {
   public static BehaviorControl<Piglin> create() {
      return BehaviorBuilder.create((p_259197_) -> {
         return p_259197_.group(p_259197_.absent(MemoryModuleType.ADMIRING_ITEM)).apply(p_259197_, (p_259512_) -> {
            return (p_275996_, p_275997_, p_275998_) -> {
               if (!p_275997_.getOffhandItem().isEmpty() && !p_275997_.getOffhandItem().canPerformAction(net.minecraftforge.common.ToolActions.SHIELD_BLOCK)) {
                  PiglinAi.stopHoldingOffHandItem(p_275997_, true);
                  return true;
               } else {
                  return false;
               }
            };
         });
      });
   }
}
