package com.rbs.slurpiesdongles.world;

import com.rbs.slurpiesdongles.Reference;
import com.rbs.slurpiesdongles.SlurpiesDongles;
import com.rbs.slurpiesdongles.config.ConfigGeneral;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.gen.feature.Feature;
import net.minecraft.world.gen.feature.NoFeatureConfig;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.IForgeRegistry;
import net.minecraftforge.registries.IForgeRegistryEntry;

@Mod.EventBusSubscriber(modid = Reference.MODID)
public class WildCropsWorldGen {
    public static Feature<NoFeatureConfig> wild_crops;

    public static void registerAll(RegistryEvent.Register<Feature<?>> event) {
        if (!event.getName().equals(ForgeRegistries.FEATURES.getRegistryName())) return;
        IForgeRegistry<Feature<?>> r = event.getRegistry();

        if (ConfigGeneral.disableWildCrops.get()) {
            wild_crops = register(r, new WildCropsGenFeature(NoFeatureConfig::deserialize), "wild_crops");
        }

    }
    private static <V extends R, R extends IForgeRegistryEntry<R>> V register(IForgeRegistry<R> registry, V value, String name)
    {
        ResourceLocation id = SlurpiesDongles.getId(name);
        value.setRegistryName(id);
        registry.register(value);
        return value;
    }
}
