package com.rbs.slurpiesdongles.init;

import net.minecraft.block.Block;
import net.minecraft.item.Item;
import net.minecraftforge.client.event.ModelRegistryEvent;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

/**
 * Created by Consular on 7/20/2017.
 */
@Mod.EventBusSubscriber
public class RegistrationHandler {
    @SubscribeEvent
    public static void registerItems(RegistryEvent.Register<Item> event) {
        SDBlocks.registerItemBlocks(event.getRegistry());
        SDFood.register(event.getRegistry());
        SDItems.register(event.getRegistry());
    }
    @SubscribeEvent
    public static void registerBlocks(RegistryEvent.Register<Block> event) {
        SDBlocks.registerBlocks(event.getRegistry());
    }
}
