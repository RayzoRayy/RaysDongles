package com.rbs.slurpiesdongles.core.events;

import com.rbs.slurpiesdongles.SlurpiesDongles;
import com.rbs.slurpiesdongles.core.config.ConfigGeneral;
import com.rbs.slurpiesdongles.core.init.ModBlocks;
import com.rbs.slurpiesdongles.core.init.ModFood;
import com.rbs.slurpiesdongles.core.init.ModItems;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.event.CreativeModeTabEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.registries.RegistryObject;

import java.util.ArrayList;
import java.util.List;

public class CreativeTabEvent {

    public static CreativeModeTab TAB_RAYSDONGLES;

    @SubscribeEvent
    public static void onCreativeModeTabRegister(CreativeModeTabEvent.Register event) {
        TAB_RAYSDONGLES = event.registerCreativeModeTab(new ResourceLocation(SlurpiesDongles.MOD_ID, "slurpiesdongles"), builder -> {
            builder
                    .icon(() -> new ItemStack(ModBlocks.RAINBOW_BRICKS.get()))
                    .displayItems((flagSet, entries, flag) -> {
                        ModBlocks.BLOCKS.getEntries().stream().map(RegistryObject::get).forEach(entries::accept);
                        ModFood.FOODS.getEntries().stream().map(RegistryObject::get).forEach(entries::accept);
                        ModItems.ITEMS.getEntries().stream().map(RegistryObject::get).forEach(entries::accept);
                    })
                    .title(Component.translatable("itemGroup.slurpiesdongles"))
                    .build();
        });
    }

}
