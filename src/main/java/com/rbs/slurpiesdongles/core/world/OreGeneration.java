package com.rbs.slurpiesdongles.core.world;

import com.google.common.base.Suppliers;
import com.rbs.slurpiesdongles.SlurpiesDongles;
import com.rbs.slurpiesdongles.core.config.ConfigGeneral;
import com.rbs.slurpiesdongles.core.init.ModBlocks;
import net.minecraft.core.Registry;
import net.minecraft.data.worldgen.placement.PlacementUtils;
import net.minecraft.tags.BlockTags;
import net.minecraft.world.level.levelgen.feature.ConfiguredFeature;
import net.minecraft.world.level.levelgen.feature.Feature;
import net.minecraft.world.level.levelgen.feature.configurations.OreConfiguration;
import net.minecraft.world.level.levelgen.feature.configurations.RandomPatchConfiguration;
import net.minecraft.world.level.levelgen.feature.configurations.SimpleBlockConfiguration;
import net.minecraft.world.level.levelgen.feature.stateproviders.BlockStateProvider;
import net.minecraft.world.level.levelgen.structure.templatesystem.RuleTest;
import net.minecraft.world.level.levelgen.structure.templatesystem.TagMatchTest;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.RegistryObject;

import java.util.List;
import java.util.function.Supplier;

public class OreGeneration {


    public static final DeferredRegister<ConfiguredFeature<?, ?>> CONFIGURED_FEATURE =
            DeferredRegister.create(Registry.CONFIGURED_FEATURE_REGISTRY, SlurpiesDongles.MOD_ID);

    private static RuleTest ruletest1 = new TagMatchTest(BlockTags.STONE_ORE_REPLACEABLES);
    private static RuleTest ruletest2 = new TagMatchTest(BlockTags.DEEPSLATE_ORE_REPLACEABLES);
    public static final Supplier<List<OreConfiguration.TargetBlockState>> OVERWORLD_RUBY_ORE = Suppliers.memoize(() -> List.of(
            OreConfiguration.target(ruletest1, ModBlocks.RUBY_ORE.get().defaultBlockState()),
            OreConfiguration.target(ruletest2, ModBlocks.DEEPSLATE_RUBY_ORE.get().defaultBlockState())));

    public static final Supplier<List<OreConfiguration.TargetBlockState>> OVERWORLD_TOPAZ_ORE = Suppliers.memoize(() -> List.of(
            OreConfiguration.target(ruletest1, ModBlocks.TOPAZ_ORE.get().defaultBlockState()),
            OreConfiguration.target(ruletest2, ModBlocks.DEEPSLATE_TOPAZ_ORE.get().defaultBlockState())));

    public static final RegistryObject<ConfiguredFeature<?, ?>> RUBY_ORE = CONFIGURED_FEATURE.register("ruby_ore",
            ()-> new ConfiguredFeature<>(Feature.ORE, new OreConfiguration(OVERWORLD_RUBY_ORE.get(), ConfigGeneral.rubyVeinSize.get()))); //Vein Size

    public static final RegistryObject<ConfiguredFeature<?, ?>> TOPAZ_ORE = CONFIGURED_FEATURE.register("topaz_ore",
            ()-> new ConfiguredFeature<>(Feature.ORE, new OreConfiguration(OVERWORLD_TOPAZ_ORE.get(), ConfigGeneral.topazVeinSize.get()))); //Vein Size

    public static final RegistryObject<ConfiguredFeature<?, ?>> WILD_CROPS = CONFIGURED_FEATURE.register("wild_crops",
            () -> new ConfiguredFeature<>(Feature.FLOWER,
                    new RandomPatchConfiguration(ConfigGeneral.wildCropsChance.get(), ConfigGeneral.wildCropsXZSpread.get(), ConfigGeneral.wildCropsYSpread.get(), PlacementUtils.onlyWhenEmpty(Feature.SIMPLE_BLOCK,
                            new SimpleBlockConfiguration(BlockStateProvider.simple(ModBlocks.WILD_CROPS.get()))))));




}

