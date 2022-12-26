package com.rbs.slurpiesdongles.core.world;

import com.google.common.base.Suppliers;
import com.rbs.slurpiesdongles.SlurpiesDongles;
import com.rbs.slurpiesdongles.core.config.ConfigGeneral;
import com.rbs.slurpiesdongles.core.init.ModBlocks;
import com.rbs.slurpiesdongles.core.world.feature.ModOrePlacement;
import net.minecraft.core.Holder;
import net.minecraft.core.Registry;
import net.minecraft.data.worldgen.features.FeatureUtils;
import net.minecraft.data.worldgen.features.OreFeatures;
import net.minecraft.data.worldgen.placement.PlacementUtils;
import net.minecraft.world.level.levelgen.VerticalAnchor;
import net.minecraft.world.level.levelgen.feature.ConfiguredFeature;
import net.minecraft.world.level.levelgen.feature.Feature;
import net.minecraft.world.level.levelgen.feature.configurations.OreConfiguration;
import net.minecraft.world.level.levelgen.feature.configurations.RandomPatchConfiguration;
import net.minecraft.world.level.levelgen.feature.configurations.SimpleBlockConfiguration;
import net.minecraft.world.level.levelgen.feature.stateproviders.BlockStateProvider;
import net.minecraft.world.level.levelgen.placement.*;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.RegistryObject;

import java.util.List;
import java.util.function.Supplier;

public class ModOreFeatures {

    public static final List<OreConfiguration.TargetBlockState> OVERWORLD_ENDERDUST_ORES = List.of(
            OreConfiguration.target(OreFeatures.STONE_ORE_REPLACEABLES, ModBlocks.ENDERDUST_ORE.get().defaultBlockState()),
            OreConfiguration.target(OreFeatures.DEEPSLATE_ORE_REPLACEABLES, ModBlocks.DEEPSLATE_ENDERDUST_ORE.get().defaultBlockState()));
    public static final List<OreConfiguration.TargetBlockState> OVERWORLD_RUBY_ORES = List.of(
            OreConfiguration.target(OreFeatures.STONE_ORE_REPLACEABLES, ModBlocks.RUBY_ORE.get().defaultBlockState()),
            OreConfiguration.target(OreFeatures.DEEPSLATE_ORE_REPLACEABLES, ModBlocks.DEEPSLATE_RUBY_ORE.get().defaultBlockState()));
    public static final List<OreConfiguration.TargetBlockState> OVERWORLD_TOPAZ_ORES = List.of(
            OreConfiguration.target(OreFeatures.STONE_ORE_REPLACEABLES, ModBlocks.TOPAZ_ORE.get().defaultBlockState()),
            OreConfiguration.target(OreFeatures.DEEPSLATE_ORE_REPLACEABLES, ModBlocks.DEEPSLATE_TOPAZ_ORE.get().defaultBlockState()));

    public static final Holder<ConfiguredFeature<RandomPatchConfiguration, ?>> WILD_CROPS =
            FeatureUtils.register("wild_crops", Feature.FLOWER,
                    new RandomPatchConfiguration(ConfigGeneral.wildCropsChance.get(), ConfigGeneral.wildCropsXZSpread.get(), ConfigGeneral.wildCropsYSpread.get(), PlacementUtils.onlyWhenEmpty(Feature.SIMPLE_BLOCK,
                            new SimpleBlockConfiguration(BlockStateProvider.simple(ModBlocks.WILD_CROPS.get())))));

    public static final Holder<ConfiguredFeature<OreConfiguration, ?>> ENDERDUST_ORE = FeatureUtils.register("enderdust_ore",
            Feature.ORE, new OreConfiguration(OVERWORLD_ENDERDUST_ORES, ConfigGeneral.enderdustVeinSize.get()));
    public static final Holder<PlacedFeature> ENDERDUST_ORE_PLACED = PlacementUtils.register("enderdust_ore_placed",
            ModOreFeatures.ENDERDUST_ORE, ModOrePlacement.commonOrePlacement(ConfigGeneral.enderdustOreVeinsPerChunk.get(),
                    HeightRangePlacement.triangle(VerticalAnchor.aboveBottom(ConfigGeneral.enderdustLowY.get()), VerticalAnchor.aboveBottom(ConfigGeneral.enderdustHighY.get()))));
    public static final Holder<ConfiguredFeature<OreConfiguration, ?>> RUBY_ORE = FeatureUtils.register("ruby_ore",
            Feature.ORE, new OreConfiguration(OVERWORLD_RUBY_ORES, ConfigGeneral.rubyVeinSize.get()));
    public static final Holder<PlacedFeature> RUBY_ORE_PLACED = PlacementUtils.register("ruby_ore_placed",
            ModOreFeatures.RUBY_ORE, ModOrePlacement.commonOrePlacement(ConfigGeneral.rubyOreVeinsPerChunk.get(),
                    HeightRangePlacement.triangle(VerticalAnchor.aboveBottom(ConfigGeneral.rubyLowY.get()), VerticalAnchor.aboveBottom(ConfigGeneral.rubyHighY.get()))));
    public static final Holder<ConfiguredFeature<OreConfiguration, ?>> TOPAZ_ORE = FeatureUtils.register("topaz_ore",
            Feature.ORE, new OreConfiguration(OVERWORLD_TOPAZ_ORES, ConfigGeneral.topazVeinSize.get()));
    public static final Holder<PlacedFeature> TOPAZ_ORE_PLACED = PlacementUtils.register("topaz_ore_placed",
            ModOreFeatures.TOPAZ_ORE, ModOrePlacement.commonOrePlacement(ConfigGeneral.topazOreVeinsPerChunk.get(),
                    HeightRangePlacement.triangle(VerticalAnchor.aboveBottom(ConfigGeneral.topazLowY.get()), VerticalAnchor.aboveBottom(ConfigGeneral.topazHighY.get()))));

    public static final Holder<PlacedFeature> WILD_CROPS_PLACED = PlacementUtils.register("wild_crops_placed",
            ModOreFeatures.WILD_CROPS, RarityFilter.onAverageOnceEvery(ConfigGeneral.wildCropsChunkGeneration.get()),
            InSquarePlacement.spread(), PlacementUtils.HEIGHTMAP, BiomeFilter.biome());

}

