package net.minecraft.data.loot.packs;

import java.util.function.BiConsumer;
import net.minecraft.data.loot.LootTableSubProvider;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.storage.loot.BuiltInLootTables;
import net.minecraft.world.level.storage.loot.LootTable;

public class UpdateOneTwentyFishingLoot implements LootTableSubProvider {
   public void generate(BiConsumer<ResourceLocation, LootTable.Builder> p_266672_) {
      p_266672_.accept(BuiltInLootTables.FISHING_FISH, VanillaFishingLoot.fishingFishLootTable());
   }
}