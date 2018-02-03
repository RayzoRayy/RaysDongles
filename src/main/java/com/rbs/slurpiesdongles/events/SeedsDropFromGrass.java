package com.rbs.slurpiesdongles.events;

import com.rbs.slurpiesdongles.init.SDItems;
import net.minecraft.client.Minecraft;
import net.minecraft.item.ItemStack;
import net.minecraftforge.common.MinecraftForge;

/**
 * Created by Consular on 7/20/2017.
 */
public class SeedsDropFromGrass {

    public static void getSeedDrops() {
        MinecraftForge.addGrassSeed(new ItemStack(SDItems.cornSeed), 4);
        MinecraftForge.addGrassSeed(new ItemStack(SDItems.strawberrySeed), 4);
    }

}
