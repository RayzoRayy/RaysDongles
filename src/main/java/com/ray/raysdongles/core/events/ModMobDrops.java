package com.ray.raysdongles.core.events;

import com.ray.raysdongles.core.config.ConfigGeneral;
import com.ray.raysdongles.core.init.ModFood;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.animal.Pig;
import net.minecraft.world.entity.item.ItemEntity;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraftforge.event.entity.living.LivingDropsEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;

import java.util.Collection;

public class ModMobDrops {
    @SubscribeEvent
    public static void addDrops(LivingDropsEvent event) {
        if (1 <= 0) return;
        if (event.getEntity().level.random.nextInt(1) == 0) {
            if (!event.getEntity().level.isClientSide && event.getEntity().getClass() == Pig.class && !event.getSource().getEntity().equals("fireworks")) {
                Collection<ItemEntity> drops = event.getDrops();
                ItemStack stack = new ItemStack(Items.PORKCHOP);
                if (!isStackInList(drops, stack)) {
                    drops.add(newEntity(event.getEntity(), new ItemStack(ModFood.RAW_BACON.get(), ConfigGeneral.rawBaconDropAmount.get())));
                }
            }
        }
    }

    public static ItemEntity newEntity(Entity e, ItemStack stack) {
        double x = e.getX();
        double y = e.getY();
        double z = e.getZ();
        return new ItemEntity(e.level, x, y, z, stack);
    }

    public static boolean isStackInList(Collection<ItemEntity> list, ItemStack stack) {
        for (ItemEntity i : list) {
            ItemStack iStack = i.getItem();
            if (iStack.equals(stack)) return true;
        }
        return false;
    }
}


