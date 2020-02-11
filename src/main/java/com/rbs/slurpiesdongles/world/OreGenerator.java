package com.rbs.slurpiesdongles.world;

import com.rbs.slurpiesdongles.config.ConfigGeneral;
import com.rbs.slurpiesdongles.init.ModBlocks;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.gen.GenerationStage;
import net.minecraft.world.gen.blockplacer.SimpleBlockPlacer;
import net.minecraft.world.gen.blockstateprovider.WeightedBlockStateProvider;
import net.minecraft.world.gen.feature.*;
import net.minecraft.world.gen.placement.CountRangeConfig;
import net.minecraft.world.gen.placement.FrequencyConfig;
import net.minecraft.world.gen.placement.Placement;
import net.minecraftforge.registries.ForgeRegistries;


public class OreGenerator {

    //countIn, minHeightIn, minHeight, maxHeightIn
    private static final CountRangeConfig amethystOre = new CountRangeConfig(ConfigGeneral.amethystOreChance.get(), ConfigGeneral.amethystMinHeight.get(), 0,ConfigGeneral.amethystMaxHeight.get());
    public static final int amethystOreVeinSize = ConfigGeneral.amethystOreSize.get();;
    private static final CountRangeConfig rubyOre = new CountRangeConfig(ConfigGeneral.rubyOreChance.get(), ConfigGeneral.rubyMinHeight.get(), 0,ConfigGeneral.rubyMaxHeight.get());
    public static final int rubyOreVeinSize = ConfigGeneral.rubyOreSize.get();
    private static final CountRangeConfig topazOre = new CountRangeConfig(ConfigGeneral.topazOreChance.get(), ConfigGeneral.topazMinHeight.get(), 0,ConfigGeneral.topazMaxHeight.get());
    public static final int topazOreVeinSize = ConfigGeneral.topazOreSize.get();
    //Nether Ores
    private static final CountRangeConfig netherCoalOre = new CountRangeConfig(ConfigGeneral.netherCoalOreChance.get(), ConfigGeneral.netherCoalOreMinHeight.get(), 0,ConfigGeneral.netherCoalOreMaxHeight.get());
    public static final int netherCoalOreVeinSize = ConfigGeneral.netherCoalOreSize.get();
    private static final CountRangeConfig netherDiamondOre = new CountRangeConfig(ConfigGeneral.netherDiamondOreChance.get(), ConfigGeneral.netherDiamondOreMinHeight.get(), 0,ConfigGeneral.netherDiamondOreMaxHeight.get());
    public static final int netherDiamondOreVeinSize = ConfigGeneral.netherDiamondOreSize.get();
    private static final CountRangeConfig netherEmeraldOre = new CountRangeConfig(ConfigGeneral.netherEmeraldOreChance.get(), ConfigGeneral.netherEmeraldOreMinHeight.get(), 0,ConfigGeneral.netherEmeraldOreMaxHeight.get());
    public static final int netherEmeraldOreVeinSize = ConfigGeneral.netherEmeraldOreSize.get();
    private static final CountRangeConfig netherGoldOre = new CountRangeConfig(ConfigGeneral.netherGoldOreChance.get(), ConfigGeneral.netherGoldOreMinHeight.get(), 0,ConfigGeneral.netherGoldOreMaxHeight.get());
    public static final int netherGoldOreVeinSize = ConfigGeneral.netherGoldOreSize.get();
    private static final CountRangeConfig netherIronOre = new CountRangeConfig(ConfigGeneral.netherIronOreChance.get(), ConfigGeneral.netherIronOreMinHeight.get(), 0,ConfigGeneral.netherIronOreMaxHeight.get());
    public static final int netherIronOreVeinSize = ConfigGeneral.netherIronOreSize.get();
    private static final CountRangeConfig netherLapisOre = new CountRangeConfig(ConfigGeneral.netherlapisOreChance.get(), ConfigGeneral.netherLapisOreMinHeight.get(), 0,ConfigGeneral.netherLapisOreMaxHeight.get());
    public static final int netherLapisOreVeinSize = ConfigGeneral.netherLapisOreSize.get();
    private static final CountRangeConfig netherRedstoneOre = new CountRangeConfig(ConfigGeneral.netherRedstoneOreChance.get(), ConfigGeneral.netherRedstoneOreMinHeight.get(), 0,ConfigGeneral.netherRedstoneOreMaxHeight.get());
    public static final int netherRedstoneOreVeinSize = ConfigGeneral.netherRedstoneOreSize.get();

    public static void setupOreGen() {

        for (Biome biome : ForgeRegistries.BIOMES) {
            //Crops
            if (WildCropsWorldGen.wild_crops !=null) {
                BlockClusterFeatureConfig featureConfigGrass = (new BlockClusterFeatureConfig.Builder((new WeightedBlockStateProvider()).func_227407_a_(ModBlocks.wild_crops.getDefaultState(), 1), new SimpleBlockPlacer())).tries(ConfigGeneral.wildCropsChance.get()).build();
                biome.addFeature(GenerationStage.Decoration.VEGETAL_DECORATION, Feature.FLOWER.withConfiguration(featureConfigGrass).withPlacement(Placement.COUNT_HEIGHTMAP_DOUBLE.configure(new FrequencyConfig(2))));
            }

            //Overworld Ores
            if (ConfigGeneral.amethystOreGeneration.get())
                biome.addFeature(GenerationStage.Decoration.UNDERGROUND_ORES,Feature.ORE.withConfiguration(new OreFeatureConfig(OreFeatureConfig.FillerBlockType.NATURAL_STONE, ModBlocks.amethyst_ore.getDefaultState(), amethystOreVeinSize)).withPlacement( Placement.COUNT_RANGE.configure( amethystOre)));

            if (ConfigGeneral.rubyOreGeneration.get())
                biome.addFeature(GenerationStage.Decoration.UNDERGROUND_ORES,Feature.ORE.withConfiguration(new OreFeatureConfig(OreFeatureConfig.FillerBlockType.NATURAL_STONE, ModBlocks.ruby_ore.getDefaultState(), rubyOreVeinSize)).withPlacement( Placement.COUNT_RANGE.configure( rubyOre)));

            if (ConfigGeneral.topazOreGeneration.get())
                biome.addFeature(GenerationStage.Decoration.UNDERGROUND_ORES,Feature.ORE.withConfiguration(new OreFeatureConfig(OreFeatureConfig.FillerBlockType.NATURAL_STONE, ModBlocks.topaz_ore.getDefaultState(), topazOreVeinSize)).withPlacement( Placement.COUNT_RANGE.configure( topazOre)));
            //Nether Ores
            if (ConfigGeneral.netherCoalOreGeneration.get())
                biome.addFeature(GenerationStage.Decoration.UNDERGROUND_ORES,Feature.ORE.withConfiguration(new OreFeatureConfig(OreFeatureConfig.FillerBlockType.NETHERRACK, ModBlocks.nether_coal_ore.getDefaultState(), netherCoalOreVeinSize)).withPlacement( Placement.COUNT_RANGE.configure( netherCoalOre)));

            if (ConfigGeneral.netherDiamondOreGeneration.get())
                biome.addFeature(GenerationStage.Decoration.UNDERGROUND_ORES,Feature.ORE.withConfiguration(new OreFeatureConfig(OreFeatureConfig.FillerBlockType.NETHERRACK, ModBlocks.nether_diamond_ore.getDefaultState(), netherDiamondOreVeinSize)).withPlacement( Placement.COUNT_RANGE.configure( netherDiamondOre)));

            if (ConfigGeneral.netherEmeraldOreGeneration.get())
                biome.addFeature(GenerationStage.Decoration.UNDERGROUND_ORES,Feature.ORE.withConfiguration(new OreFeatureConfig(OreFeatureConfig.FillerBlockType.NETHERRACK, ModBlocks.nether_emerald_ore.getDefaultState(), netherEmeraldOreVeinSize)).withPlacement( Placement.COUNT_RANGE.configure( netherEmeraldOre)));

            if (ConfigGeneral.netherGoldOreGeneration.get())
                biome.addFeature(GenerationStage.Decoration.UNDERGROUND_ORES,Feature.ORE.withConfiguration(new OreFeatureConfig(OreFeatureConfig.FillerBlockType.NETHERRACK, ModBlocks.nether_gold_ore.getDefaultState(), netherGoldOreVeinSize)).withPlacement( Placement.COUNT_RANGE.configure( netherGoldOre)));

            if (ConfigGeneral.netherIronOreGeneration.get())
                biome.addFeature(GenerationStage.Decoration.UNDERGROUND_ORES,Feature.ORE.withConfiguration(new OreFeatureConfig(OreFeatureConfig.FillerBlockType.NETHERRACK, ModBlocks.nether_iron_ore.getDefaultState(), netherIronOreVeinSize)).withPlacement( Placement.COUNT_RANGE.configure( netherIronOre)));

            if (ConfigGeneral.netherLapisOreGeneration.get())
                biome.addFeature(GenerationStage.Decoration.UNDERGROUND_ORES,Feature.ORE.withConfiguration(new OreFeatureConfig(OreFeatureConfig.FillerBlockType.NETHERRACK, ModBlocks.nether_lapis_ore.getDefaultState(), netherLapisOreVeinSize)).withPlacement( Placement.COUNT_RANGE.configure( netherLapisOre)));

            if (ConfigGeneral.netherRedstoneOreGeneration.get())
                biome.addFeature(GenerationStage.Decoration.UNDERGROUND_ORES,Feature.ORE.withConfiguration(new OreFeatureConfig(OreFeatureConfig.FillerBlockType.NETHERRACK, ModBlocks.nether_redstone_ore.getDefaultState(), netherRedstoneOreVeinSize)).withPlacement( Placement.COUNT_RANGE.configure( netherRedstoneOre)));

        }
    }
}


