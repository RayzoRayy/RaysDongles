package com.rbs.slurpiesdongles;

import com.rbs.slurpiesdongles.core.config.CoreConfig;
import com.rbs.slurpiesdongles.core.events.ModMobDrops;
import com.rbs.slurpiesdongles.core.init.ModBlocks;
import com.rbs.slurpiesdongles.core.init.ModFood;
import com.rbs.slurpiesdongles.core.init.ModItems;
import com.rbs.slurpiesdongles.core.world.OreGeneration;
import com.rbs.slurpiesdongles.core.world.feature.ModOrePlacement;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.common.util.Lazy;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.ModContainer;
import net.minecraftforge.fml.ModList;
import net.minecraftforge.fml.ModLoadingContext;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.config.ModConfig;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import net.minecraftforge.fml.loading.FMLPaths;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

@Mod(SlurpiesDongles.MOD_ID)
public class SlurpiesDongles
{

    public static final String MOD_ID = "slurpiesdongles";
    public static final Lazy<ModContainer> MOD_CONTAINER = Lazy.of(() -> ModList.get().getModContainerById(MOD_ID).orElse(null));
    public static final Logger LOGGER = LogManager.getLogger(MOD_ID);

    public SlurpiesDongles() {

        ModLoadingContext.get().registerConfig(ModConfig.Type.SERVER, CoreConfig.SPEC, "Ray's Dongles-General.toml");
        CoreConfig.loadConfig(CoreConfig.SPEC, FMLPaths.CONFIGDIR.get().resolve("Ray's Dongles-General.toml".toString()));

        IEventBus bus = FMLJavaModLoadingContext.get().getModEventBus();

        ModBlocks.BLOCKS.register(bus);
        ModFood.register(bus);
        ModItems.register(bus);

        ModOrePlacement.register(bus);
        OreGeneration.register(bus);

        MinecraftForge.EVENT_BUS.register(ModMobDrops.class);
        bus.addListener(this::commonSetup);

        MinecraftForge.EVENT_BUS.register(this);



    }

    private void commonSetup(final FMLCommonSetupEvent event) {
    }
    @Mod.EventBusSubscriber(modid = MOD_ID, bus = Mod.EventBusSubscriber.Bus.MOD)
    public static class ClientModEvents {
        }

    }


