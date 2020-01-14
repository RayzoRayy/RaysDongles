package com.rbs.slurpiesdongles;

import com.rbs.slurpiesdongles.config.CoreConfig;
import com.rbs.slurpiesdongles.events.PigDrops;
import com.rbs.slurpiesdongles.init.ModBlocks;
import com.rbs.slurpiesdongles.update.Configuration;
import com.rbs.slurpiesdongles.world.OreGenerator;
import com.rbs.slurpiesdongles.world.WildCropsWorldGen;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.RenderTypeLookup;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.ModLoadingContext;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.config.ModConfig;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.event.lifecycle.InterModEnqueueEvent;
import net.minecraftforge.fml.event.lifecycle.InterModProcessEvent;
import net.minecraftforge.fml.event.server.FMLServerStartingEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import net.minecraftforge.fml.loading.FMLPaths;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.annotation.Nonnull;
import java.util.Random;

// The value here should match an entry in the META-INF/mods.toml file
@Mod(value = Reference.MODID)
public class SlurpiesDongles {

    public static Random random = new Random();


    public static SlurpiesDongles instance;

    // Directly reference a log4j logger.
    private static final Logger LOGGER = LogManager.getLogger();

    public SlurpiesDongles() {
        //Config Stuff
        ModLoadingContext.get().registerConfig(ModConfig.Type.SERVER, CoreConfig.SPEC, "Ray's Dongles-General.toml");
        CoreConfig.loadConfig(CoreConfig.SPEC, FMLPaths.CONFIGDIR.get().resolve("Ray's Dongles-General.toml".toString()));
        FMLJavaModLoadingContext.get().getModEventBus().addListener(WildCropsWorldGen::registerAll);
        // Register the setup method for modloading
        FMLJavaModLoadingContext.get().getModEventBus().addListener(this::setup);
        // Register the enqueueIMC method for modloading
        FMLJavaModLoadingContext.get().getModEventBus().addListener(this::enqueueIMC);
        // Register the processIMC method for modloading
        FMLJavaModLoadingContext.get().getModEventBus().addListener(this::processIMC);
        // Register the doClientStuff method for modloading
        FMLJavaModLoadingContext.get().getModEventBus().addListener(this::doClientStuff);
        // Register ourselves for server, registry and other game events we are interested in
        MinecraftForge.EVENT_BUS.register(this);
        Configuration.init();


    }

    private void setup(final FMLCommonSetupEvent event) {
        // some preinit code
        MinecraftForge.EVENT_BUS.register(new PigDrops());
        OreGenerator.setupOreGen();

    }

    private void doClientStuff(final FMLClientSetupEvent event) {
        // do something that can only be done on the client
        //This renders items when placed so that the textures can load properly
        //Crops
        RenderTypeLookup.setRenderLayer(ModBlocks.cabbage_crop, RenderType.func_228643_e_());
        RenderTypeLookup.setRenderLayer(ModBlocks.corn_crop, RenderType.func_228643_e_());
        RenderTypeLookup.setRenderLayer(ModBlocks.lemon_crop, RenderType.func_228643_e_());
        RenderTypeLookup.setRenderLayer(ModBlocks.orange_crop, RenderType.func_228643_e_());
        RenderTypeLookup.setRenderLayer(ModBlocks.strawberry_crop, RenderType.func_228643_e_());
        RenderTypeLookup.setRenderLayer(ModBlocks.tomato_crop, RenderType.func_228643_e_());
        RenderTypeLookup.setRenderLayer(ModBlocks.wild_crops, RenderType.func_228643_e_());
        //Items
        RenderTypeLookup.setRenderLayer(ModBlocks.stone_torch, RenderType.func_228643_e_());
        RenderTypeLookup.setRenderLayer(ModBlocks.wall_stone_torch, RenderType.func_228643_e_());
    }

    private void enqueueIMC(final InterModEnqueueEvent event) {
        // some example code to dispatch IMC to another mod
    }

    private void processIMC(final InterModProcessEvent event) {
        // some example code to receive and process InterModComms from other mods

    }

    // You can use SubscribeEvent and let the Event Bus discover methods to call
    @SubscribeEvent
    public static void onServerStarting(FMLServerStartingEvent event) {
        // do something when the server starts

    }

    @Nonnull
    public static ResourceLocation getId(String path)
    {
        return new ResourceLocation(Reference.MODID, path);
    }
}


