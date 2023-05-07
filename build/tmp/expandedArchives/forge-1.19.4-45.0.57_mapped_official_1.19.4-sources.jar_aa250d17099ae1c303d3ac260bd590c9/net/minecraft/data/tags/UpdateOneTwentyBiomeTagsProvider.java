package net.minecraft.data.tags;

import java.util.concurrent.CompletableFuture;
import net.minecraft.core.HolderLookup;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.PackOutput;
import net.minecraft.tags.BiomeTags;
import net.minecraft.world.level.biome.Biome;
import net.minecraft.world.level.biome.Biomes;

public class UpdateOneTwentyBiomeTagsProvider extends TagsProvider<Biome> {
   public UpdateOneTwentyBiomeTagsProvider(PackOutput p_273510_, CompletableFuture<HolderLookup.Provider> p_272994_) {
      super(p_273510_, Registries.BIOME, p_272994_);
   }

   protected void addTags(HolderLookup.Provider p_273703_) {
      this.tag(BiomeTags.IS_MOUNTAIN).add(Biomes.CHERRY_GROVE);
   }
}