package com.rbs.slurpiesdongles.core.world.feature;

import com.rbs.slurpiesdongles.SlurpiesDongles;
import com.rbs.slurpiesdongles.core.config.ConfigGeneral;
import com.rbs.slurpiesdongles.core.world.OreGeneration;
import net.minecraft.core.Registry;
import net.minecraft.data.worldgen.placement.PlacementUtils;
import net.minecraft.world.level.levelgen.VerticalAnchor;
import net.minecraft.world.level.levelgen.placement.*;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.RegistryObject;

import java.util.List;

public class ModOrePlacement {

    public static final DeferredRegister<PlacedFeature> PLACED_FEATURES = DeferredRegister.create(Registry.PLACED_FEATURE_REGISTRY, SlurpiesDongles.MOD_ID);

    public static final RegistryObject<PlacedFeature> RUBY_ORE = PLACED_FEATURES.register("ruby_ore_placed.json",
            ()-> new PlacedFeature(OreGeneration.RUBY_ORE.getHolder().get(), commonOrePlacement(ConfigGeneral.rubyOreVeinsPerChunk.get(), //Veins Per Chunk
                    HeightRangePlacement.triangle(VerticalAnchor.aboveBottom(-ConfigGeneral.rubyLowY.get()), VerticalAnchor.aboveBottom(ConfigGeneral.rubyHighY.get())))));

    public static final RegistryObject<PlacedFeature> TOPAZ_ORE = PLACED_FEATURES.register("topaz_ore_placed",
            ()-> new PlacedFeature(OreGeneration.TOPAZ_ORE.getHolder().get(), commonOrePlacement(ConfigGeneral.topazOreVeinsPerChunk.get(), //Veins Per Chunk
                    HeightRangePlacement.triangle(VerticalAnchor.aboveBottom(-ConfigGeneral.topazLowY.get()), VerticalAnchor.aboveBottom(ConfigGeneral.topazHighY.get())))));

    public static final RegistryObject<PlacedFeature> WILD_CROPS_PLACED = PLACED_FEATURES.register("wild_crops_placed",
            () -> new PlacedFeature(OreGeneration.WILD_CROPS.getHolder().get(), List.of(RarityFilter.onAverageOnceEvery(ConfigGeneral.wildCropsChunkGeneration.get()),// Average every x chunks
                    InSquarePlacement.spread(), PlacementUtils.HEIGHTMAP, BiomeFilter.biome())));


    public static List<PlacementModifier> orePlacement(PlacementModifier p_195347_, PlacementModifier p_195348_) {
        return List.of(p_195347_, InSquarePlacement.spread(), p_195348_, BiomeFilter.biome());
    }

    public static List<PlacementModifier> commonOrePlacement(int p_195344_, PlacementModifier p_195345_) {
        return orePlacement(CountPlacement.of(p_195344_), p_195345_);
    }

    public static List<PlacementModifier> rareOrePlacement(int p_195350_, PlacementModifier p_195351_) {
        return orePlacement(RarityFilter.onAverageOnceEvery(p_195350_), p_195351_);
    }



}
