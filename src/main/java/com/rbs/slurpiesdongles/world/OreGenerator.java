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
import net.minecraft.world.gen.placement.IPlacementConfig;
import net.minecraft.world.gen.placement.Placement;
import net.minecraftforge.registries.ForgeRegistries;
import sun.security.krb5.Config;

import static net.minecraft.world.biome.DefaultBiomeFeatures.field_226826_u_;
import static net.minecraft.world.gen.feature.OreFeatureConfig.FillerBlockType.NATURAL_STONE;
import static net.minecraft.world.gen.feature.OreFeatureConfig.FillerBlockType.NETHERRACK;
import static net.minecraft.world.gen.placement.Placement.COUNT_RANGE;

public class OreGenerator {

    //countIn, minHeightIn, minHeight, maxHeightIn
    private static final CountRangeConfig amazoniteOre = new CountRangeConfig(ConfigGeneral.amazoniteOreChance.get(), ConfigGeneral.amazoniteMinHeight.get(), 0,ConfigGeneral.amazoniteMaxHeight.get());
    public static final int amazoniteOreVeinSize = ConfigGeneral.amazoniteOreSize.get();
    private static final CountRangeConfig amethystOre = new CountRangeConfig(ConfigGeneral.amethystOreChance.get(), ConfigGeneral.amethystMinHeight.get(), 0,ConfigGeneral.amethystMaxHeight.get());
    public static final int amethystOreVeinSize = ConfigGeneral.amethystOreSize.get();
    private static final CountRangeConfig peridotOre = new CountRangeConfig(ConfigGeneral.peridotOreChance.get(), ConfigGeneral.peridotMinHeight.get(), 0,ConfigGeneral.peridotMaxHeight.get());
    public static final int peridotOreVeinSize = ConfigGeneral.peridotOreSize.get();
    private static final CountRangeConfig rubyOre = new CountRangeConfig(ConfigGeneral.rubyOreChance.get(), ConfigGeneral.rubyMinHeight.get(), 0,ConfigGeneral.rubyMaxHeight.get());
    public static final int rubyOreVeinSize = ConfigGeneral.rubyOreSize.get();
    private static final CountRangeConfig sapphireOre = new CountRangeConfig(ConfigGeneral.sapphireOreChance.get(), ConfigGeneral.sapphireMinHeight.get(), 0,ConfigGeneral.sapphireMaxHeight.get());
    public static final int sapphireOreVeinSize = ConfigGeneral.sapphireOreSize.get();
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
                BlockClusterFeatureConfig featureConfigGrass = (new BlockClusterFeatureConfig.Builder((new WeightedBlockStateProvider()).func_227407_a_(ModBlocks.wild_crops.getDefaultState(), 1), new SimpleBlockPlacer())).func_227315_a_(ConfigGeneral.wildCropsChance.get()).func_227322_d_();
                biome.addFeature(GenerationStage.Decoration.VEGETAL_DECORATION, Feature.field_227247_y_.func_225566_b_(featureConfigGrass).func_227228_a_(Placement.COUNT_HEIGHTMAP_DOUBLE.func_227446_a_(new FrequencyConfig(2))));
            }

            //Overworld Ores
            if (ConfigGeneral.amazoniteOreGeneration.get())
                biome.addFeature(GenerationStage.Decoration.UNDERGROUND_ORES,Feature.ORE.func_225566_b_(new OreFeatureConfig(OreFeatureConfig.FillerBlockType.NATURAL_STONE, ModBlocks.amazonite_ore.getDefaultState(), amazoniteOreVeinSize)).func_227228_a_( Placement.COUNT_RANGE.func_227446_a_( amazoniteOre)));

            if (ConfigGeneral.amethystOreGeneration.get())
                biome.addFeature(GenerationStage.Decoration.UNDERGROUND_ORES,Feature.ORE.func_225566_b_(new OreFeatureConfig(OreFeatureConfig.FillerBlockType.NATURAL_STONE, ModBlocks.amethyst_ore.getDefaultState(), amethystOreVeinSize)).func_227228_a_( Placement.COUNT_RANGE.func_227446_a_( amethystOre)));

            if (ConfigGeneral.peridotOreGeneration.get())
                biome.addFeature(GenerationStage.Decoration.UNDERGROUND_ORES,Feature.ORE.func_225566_b_(new OreFeatureConfig(OreFeatureConfig.FillerBlockType.NATURAL_STONE, ModBlocks.peridot_ore.getDefaultState(), peridotOreVeinSize)).func_227228_a_( Placement.COUNT_RANGE.func_227446_a_( peridotOre)));

            if (ConfigGeneral.rubyOreGeneration.get())
                biome.addFeature(GenerationStage.Decoration.UNDERGROUND_ORES,Feature.ORE.func_225566_b_(new OreFeatureConfig(OreFeatureConfig.FillerBlockType.NATURAL_STONE, ModBlocks.ruby_ore.getDefaultState(), rubyOreVeinSize)).func_227228_a_( Placement.COUNT_RANGE.func_227446_a_( rubyOre)));

            if (ConfigGeneral.sapphireOreGeneration.get())
                biome.addFeature(GenerationStage.Decoration.UNDERGROUND_ORES,Feature.ORE.func_225566_b_(new OreFeatureConfig(OreFeatureConfig.FillerBlockType.NATURAL_STONE, ModBlocks.sapphire_ore.getDefaultState(), sapphireOreVeinSize)).func_227228_a_( Placement.COUNT_RANGE.func_227446_a_( sapphireOre)));

            if (ConfigGeneral.topazOreGeneration.get())
                biome.addFeature(GenerationStage.Decoration.UNDERGROUND_ORES,Feature.ORE.func_225566_b_(new OreFeatureConfig(OreFeatureConfig.FillerBlockType.NATURAL_STONE, ModBlocks.topaz_ore.getDefaultState(), topazOreVeinSize)).func_227228_a_( Placement.COUNT_RANGE.func_227446_a_( topazOre)));
            //Nether Ores
            if (ConfigGeneral.netherCoalOreGeneration.get())
                biome.addFeature(GenerationStage.Decoration.UNDERGROUND_ORES,Feature.ORE.func_225566_b_(new OreFeatureConfig(OreFeatureConfig.FillerBlockType.NETHERRACK, ModBlocks.nether_coal_ore.getDefaultState(), netherCoalOreVeinSize)).func_227228_a_( Placement.COUNT_RANGE.func_227446_a_( netherCoalOre)));

            if (ConfigGeneral.netherDiamondOreGeneration.get())
                biome.addFeature(GenerationStage.Decoration.UNDERGROUND_ORES,Feature.ORE.func_225566_b_(new OreFeatureConfig(OreFeatureConfig.FillerBlockType.NETHERRACK, ModBlocks.nether_diamond_ore.getDefaultState(), netherDiamondOreVeinSize)).func_227228_a_( Placement.COUNT_RANGE.func_227446_a_( netherDiamondOre)));

            if (ConfigGeneral.netherEmeraldOreGeneration.get())
                biome.addFeature(GenerationStage.Decoration.UNDERGROUND_ORES,Feature.ORE.func_225566_b_(new OreFeatureConfig(OreFeatureConfig.FillerBlockType.NETHERRACK, ModBlocks.nether_emerald_ore.getDefaultState(), netherEmeraldOreVeinSize)).func_227228_a_( Placement.COUNT_RANGE.func_227446_a_( netherEmeraldOre)));

            if (ConfigGeneral.netherGoldOreGeneration.get())
                biome.addFeature(GenerationStage.Decoration.UNDERGROUND_ORES,Feature.ORE.func_225566_b_(new OreFeatureConfig(OreFeatureConfig.FillerBlockType.NETHERRACK, ModBlocks.nether_gold_ore.getDefaultState(), netherGoldOreVeinSize)).func_227228_a_( Placement.COUNT_RANGE.func_227446_a_( netherGoldOre)));

            if (ConfigGeneral.netherIronOreGeneration.get())
                biome.addFeature(GenerationStage.Decoration.UNDERGROUND_ORES,Feature.ORE.func_225566_b_(new OreFeatureConfig(OreFeatureConfig.FillerBlockType.NETHERRACK, ModBlocks.nether_iron_ore.getDefaultState(), netherIronOreVeinSize)).func_227228_a_( Placement.COUNT_RANGE.func_227446_a_( netherIronOre)));

            if (ConfigGeneral.netherLapisOreGeneration.get())
                biome.addFeature(GenerationStage.Decoration.UNDERGROUND_ORES,Feature.ORE.func_225566_b_(new OreFeatureConfig(OreFeatureConfig.FillerBlockType.NETHERRACK, ModBlocks.nether_lapis_ore.getDefaultState(), netherLapisOreVeinSize)).func_227228_a_( Placement.COUNT_RANGE.func_227446_a_( netherLapisOre)));

            if (ConfigGeneral.netherRedstoneOreGeneration.get())
                biome.addFeature(GenerationStage.Decoration.UNDERGROUND_ORES,Feature.ORE.func_225566_b_(new OreFeatureConfig(OreFeatureConfig.FillerBlockType.NETHERRACK, ModBlocks.nether_redstone_ore.getDefaultState(), netherRedstoneOreVeinSize)).func_227228_a_( Placement.COUNT_RANGE.func_227446_a_( netherRedstoneOre)));

        }
    }
}


