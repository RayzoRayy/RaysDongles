package com.rbs.slurpiesdongles;

import com.rbs.slurpiesdongles.events.EventPigDrops;
import com.rbs.slurpiesdongles.events.FuelHandler;
import com.rbs.slurpiesdongles.events.SeedsDropFromGrass;
import com.rbs.slurpiesdongles.init.*;
import com.rbs.slurpiesdongles.proxy.CommonProxy;
import com.rbs.slurpiesdongles.world.WorldGen;
import com.sun.org.glassfish.gmbal.IncludeSubclass;
import net.minecraft.block.Block;
import net.minecraft.item.Item;
import net.minecraftforge.client.event.ModelRegistryEvent;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.common.config.Configuration;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.fml.client.event.ConfigChangedEvent;
import net.minecraftforge.fml.common.FMLCommonHandler;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.SidedProxy;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.fml.common.event.FMLServerStartingEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.registry.GameRegistry;

import java.io.File;
import java.util.Random;

import static com.rbs.slurpiesdongles.init.Configuration.*;

/**
 * Created by Consular on 7/19/2017.
 */
@Mod(modid = SlurpiesDongles.modId, name = SlurpiesDongles.name, version = SlurpiesDongles.version)
public class SlurpiesDongles {



    public static Random random = new Random();
    public static final SDTab creativeTab = new SDTab();

    public static Configuration Config;
    public static Configuration Armor;

        public static final String modId = "slurpiesdongles";
        public static final String name = "Slurpies Dongles";
        public static final String version = "1.0.0";

        @SidedProxy(serverSide = "com.rbs.slurpiesdongles.proxy.CommonProxy", clientSide = "com.rbs.slurpiesdongles.proxy.ClientProxy")
        public static CommonProxy proxy;

        @Mod.Instance(modId)
    public static SlurpiesDongles instance;

        @Mod.EventHandler
    public void preInit(FMLPreInitializationEvent event) {
            MinecraftForge.EVENT_BUS.register(new EventPigDrops());
            MinecraftForge.EVENT_BUS.register(new RegistrationHandler());
            MinecraftForge.EVENT_BUS.register(new FuelHandler());
            MinecraftForge.EVENT_BUS.register(new SmeltingRecipies());
            SeedsDropFromGrass.getSeedDrops();

            syncConfig();
            Config = new Configuration(new File("config/SlurpiesDongles/ToolMaterials.cfg"));
            Armor = new Configuration(new File("config/SlurpiesDongles/ArmorMaterials.cfg"));
            //ConfigFile.SyncConfig();

        }

        @Mod.EventHandler
    public void init(FMLInitializationEvent event) {
            MinecraftForge.EVENT_BUS.register(instance);
            proxy.registerRenders();
            SmeltingRecipies.registerSmeltingRecipes();
            GameRegistry.registerWorldGenerator(new WorldGen(), 0);
            GameRegistry.registerFuelHandler(new FuelHandler());
        }

        @Mod.EventHandler
    public void postInit(FMLPostInitializationEvent event) {

        }

    @Mod.EventHandler
    public void serverLoad(FMLServerStartingEvent event) {

    }
    @SubscribeEvent
    public void onConfigChanged (ConfigChangedEvent.OnConfigChangedEvent event) {
        if (event.getModID().equals(SlurpiesDongles.modId)) {
            //ConfigFile.SyncConfig();
            syncConfig();
        }
    }

}
