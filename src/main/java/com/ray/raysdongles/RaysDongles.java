package com.ray.raysdongles;

import com.ray.raysdongles.core.config.CoreConfig;
import com.ray.raysdongles.core.events.CreativeTabEvent;
import com.ray.raysdongles.core.events.ModMobDrops;
import com.ray.raysdongles.core.init.ModBlocks;
import com.ray.raysdongles.core.init.ModFood;
import com.ray.raysdongles.core.init.ModItems;
import net.minecraft.resources.ResourceLocation;
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

@Mod(RaysDongles.MOD_ID)
public class RaysDongles
{
    public static final String MOD_ID = "raysdongles";
    public static final Lazy<ModContainer> MOD_CONTAINER = Lazy.of(() -> ModList.get().getModContainerById(MOD_ID).orElse(null));
    public static final Logger LOGGER = LogManager.getLogger(MOD_ID);
    public static ResourceLocation rsl(String path) {
        return new ResourceLocation(RaysDongles.MOD_ID, path);
    }

    public RaysDongles() {

        ModLoadingContext.get().registerConfig(ModConfig.Type.SERVER, CoreConfig.SPEC, "Ray's Dongles-General.toml");
        CoreConfig.loadConfig(CoreConfig.SPEC, FMLPaths.CONFIGDIR.get().resolve("Ray's Dongles-General.toml".toString()));

        IEventBus bus = FMLJavaModLoadingContext.get().getModEventBus();

        ModBlocks.BLOCKS.register(bus);
        ModFood.register(bus);
        ModItems.register(bus);

        MinecraftForge.EVENT_BUS.register(ModMobDrops.class);
        bus.addListener(this::commonSetup);
        bus.addListener(CreativeTabEvent::onCreativeModeTabRegister);

        MinecraftForge.EVENT_BUS.register(this);
    }

    private void commonSetup(final FMLCommonSetupEvent event) {
    }
    @Mod.EventBusSubscriber(modid = MOD_ID, bus = Mod.EventBusSubscriber.Bus.MOD)
    public static class ClientModEvents {
        }
    }


