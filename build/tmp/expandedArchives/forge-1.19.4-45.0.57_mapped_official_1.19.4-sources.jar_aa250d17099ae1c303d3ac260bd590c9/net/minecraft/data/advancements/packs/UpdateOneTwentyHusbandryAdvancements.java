package net.minecraft.data.advancements.packs;

import java.util.function.Consumer;
import java.util.stream.Stream;
import net.minecraft.advancements.Advancement;
import net.minecraft.core.HolderLookup;
import net.minecraft.data.advancements.AdvancementSubProvider;
import net.minecraft.world.entity.EntityType;

public class UpdateOneTwentyHusbandryAdvancements implements AdvancementSubProvider {
   public void generate(HolderLookup.Provider p_267121_, Consumer<Advancement> p_266675_) {
      Advancement advancement = AdvancementSubProvider.createPlaceholder("husbandry/breed_an_animal");
      Stream<EntityType<?>> stream = Stream.concat(VanillaHusbandryAdvancements.BREEDABLE_ANIMALS.stream(), Stream.of(EntityType.CAMEL, EntityType.SNIFFER));
      VanillaHusbandryAdvancements.createBreedAllAnimalsAdvancement(advancement, p_266675_, stream, VanillaHusbandryAdvancements.INDIRECTLY_BREEDABLE_ANIMALS.stream());
   }
}