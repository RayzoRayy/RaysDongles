package com.rbs.slurpiesdongles.events;

import com.rbs.slurpiesdongles.init.SDFood;
import net.minecraft.entity.passive.EntityPig;
import net.minecraftforge.event.entity.living.LivingDropsEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

/**
 * Created by Consular on 7/21/2017.
 */
public class EventPigDrops {

    public static double rand;

    @SubscribeEvent
    public void onEntityDrop(LivingDropsEvent event){

        if (event.getSource().getDamageType().equals("player")) {

            rand = Math.random();
            if ((event.getEntityLiving() instanceof EntityPig)) {

                if (rand < 0.5D) {
                    event.getEntityLiving().dropItem(SDFood.rawBacon, 1);

                }
                if (rand < 0.25D) {
                    event.getEntityLiving().dropItem(SDFood.rawBacon, 1);
                }
            }
        }
    }
}
