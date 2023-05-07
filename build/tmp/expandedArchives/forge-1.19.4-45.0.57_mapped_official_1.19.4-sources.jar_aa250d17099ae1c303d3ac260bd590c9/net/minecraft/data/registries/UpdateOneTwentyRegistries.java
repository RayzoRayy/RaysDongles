package net.minecraft.data.registries;

import java.util.concurrent.CompletableFuture;
import net.minecraft.core.HolderLookup;
import net.minecraft.core.RegistryAccess;
import net.minecraft.core.RegistrySetBuilder;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.worldgen.biome.BiomeData;
import net.minecraft.world.item.armortrim.TrimMaterials;
import net.minecraft.world.item.armortrim.TrimPatterns;
import net.minecraft.world.level.biome.MultiNoiseBiomeSourceParameterLists;

public class UpdateOneTwentyRegistries {
   private static final RegistrySetBuilder BUILDER = (new RegistrySetBuilder()).add(Registries.TRIM_MATERIAL, TrimMaterials::nextUpdate).add(Registries.TRIM_PATTERN, TrimPatterns::nextUpdate).add(Registries.BIOME, BiomeData::nextUpdate).add(Registries.MULTI_NOISE_BIOME_SOURCE_PARAMETER_LIST, MultiNoiseBiomeSourceParameterLists::nextUpdate);

   public static CompletableFuture<HolderLookup.Provider> createLookup(CompletableFuture<HolderLookup.Provider> p_273075_) {
      return p_273075_.thenApply((p_272828_) -> {
         RegistryAccess.Frozen registryaccess$frozen = RegistryAccess.fromRegistryOfRegistries(BuiltInRegistries.REGISTRY);
         HolderLookup.Provider holderlookup$provider = BUILDER.buildPatch(registryaccess$frozen, p_272828_);
         VanillaRegistries.validateThatAllBiomeFeaturesHaveBiomeFilter(p_272828_.lookupOrThrow(Registries.PLACED_FEATURE), holderlookup$provider.lookupOrThrow(Registries.BIOME));
         return holderlookup$provider;
      });
   }
}