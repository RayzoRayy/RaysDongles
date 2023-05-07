package net.minecraft.data.advancements.packs;

import java.util.function.Consumer;
import net.minecraft.advancements.Advancement;
import net.minecraft.core.HolderLookup;
import net.minecraft.data.advancements.AdvancementSubProvider;
import net.minecraft.world.level.biome.MultiNoiseBiomeSourceParameterList;

public class UpdateOneTwentyAdventureAdvancements implements AdvancementSubProvider {
   public void generate(HolderLookup.Provider p_272612_, Consumer<Advancement> p_273008_) {
      Advancement advancement = AdvancementSubProvider.createPlaceholder("adventure/sleep_in_bed");
      VanillaAdventureAdvancements.createAdventuringTime(p_273008_, advancement, MultiNoiseBiomeSourceParameterList.Preset.OVERWORLD_UPDATE_1_20);
   }
}