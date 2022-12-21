package com.ray.raysdongles.core.events;

import com.ray.raysdongles.RaysDongles;
import com.ray.raysdongles.core.init.ModBlocks;
import com.ray.raysdongles.core.init.ModFood;
import com.ray.raysdongles.core.init.ModItems;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.event.CreativeModeTabEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.registries.RegistryObject;

public class CreativeTabEvent {

    public static CreativeModeTab TAB_RAYSDONGLES;

    @SubscribeEvent
    public static void onCreativeModeTabRegister(CreativeModeTabEvent.Register event) {
        TAB_RAYSDONGLES = event.registerCreativeModeTab(new ResourceLocation(RaysDongles.MOD_ID, "raysdongles"), builder -> {
            builder
                    .icon(() -> new ItemStack(ModBlocks.RAINBOW_BRICKS.get()))
                    .displayItems((flagSet, entries, flag) -> {
                        ModBlocks.BLOCKS.getEntries().stream().map(RegistryObject::get).forEach(entries::accept);
                        ModFood.FOODS.getEntries().stream().map(RegistryObject::get).forEach(entries::accept);
                        ModItems.ITEMS.getEntries().stream().map(RegistryObject::get).forEach(entries::accept);
                    })
                    .title(Component.translatable("itemGroup.raysdongles"))
                    .build();
        });
    }

}
