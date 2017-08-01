package com.rbs.slurpiesdongles.proxy;

import com.rbs.slurpiesdongles.SlurpiesDongles;
import com.rbs.slurpiesdongles.init.SDBlocks;
import com.rbs.slurpiesdongles.init.SDFood;
import com.rbs.slurpiesdongles.init.SDItems;
import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.item.Item;
import net.minecraftforge.client.model.ModelLoader;

/**
 * Created by Consular on 7/19/2017.
 */
public class ClientProxy extends CommonProxy {

    public void registerRenders() {
        SDBlocks.registerModels();
        SDFood.registerModels();
        SDItems.registerModels();

    }
}
