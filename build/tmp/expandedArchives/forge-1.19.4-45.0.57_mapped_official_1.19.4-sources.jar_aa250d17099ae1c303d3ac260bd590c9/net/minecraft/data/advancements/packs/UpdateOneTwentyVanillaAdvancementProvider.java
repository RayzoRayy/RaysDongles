package net.minecraft.data.advancements.packs;

import java.util.List;
import java.util.concurrent.CompletableFuture;
import net.minecraft.core.HolderLookup;
import net.minecraft.data.PackOutput;
import net.minecraft.data.advancements.AdvancementProvider;

public class UpdateOneTwentyVanillaAdvancementProvider {
   public static AdvancementProvider create(PackOutput p_261716_, CompletableFuture<HolderLookup.Provider> p_262164_) {
      return new AdvancementProvider(p_261716_, p_262164_, List.of(new UpdateOneTwentyAdventureAdvancements(), new UpdateOneTwentyHusbandryAdvancements()));
   }
}