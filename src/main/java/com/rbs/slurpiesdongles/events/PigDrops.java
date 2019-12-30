package com.rbs.slurpiesdongles.events;

import com.rbs.slurpiesdongles.init.ModFood;
import net.minecraft.entity.passive.PigEntity;
import net.minecraftforge.event.entity.living.LivingDropsEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;

public class PigDrops {

    public static double rand;

    @SubscribeEvent
    public void onMobDrop(LivingDropsEvent event) {

        if (event.getSource().getDamageType().equals("player")) {

            rand = Math.random();
            if ((event.getEntityLiving() instanceof PigEntity)) {

                if (rand < 0.5D) {
                    event.getEntityLiving().entityDropItem(ModFood.raw_bacon, 1);

                }
                if (rand < 0.25D) {
                    event.getEntityLiving().entityDropItem(ModFood.raw_bacon, 1);
                }
            }
        }
    }
}
