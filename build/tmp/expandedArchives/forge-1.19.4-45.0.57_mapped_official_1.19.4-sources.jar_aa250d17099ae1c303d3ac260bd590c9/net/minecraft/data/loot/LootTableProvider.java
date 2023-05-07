package net.minecraft.data.loot;

import com.google.common.collect.Maps;
import com.google.common.collect.Multimap;
import com.google.common.collect.Sets;
import com.mojang.logging.LogUtils;
import java.nio.file.Path;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.CompletableFuture;
import java.util.function.Supplier;
import net.minecraft.data.CachedOutput;
import net.minecraft.data.DataProvider;
import net.minecraft.data.PackOutput;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.storage.loot.LootTable;
import net.minecraft.world.level.storage.loot.LootTables;
import net.minecraft.world.level.storage.loot.ValidationContext;
import net.minecraft.world.level.storage.loot.parameters.LootContextParamSet;
import net.minecraft.world.level.storage.loot.parameters.LootContextParamSets;
import org.slf4j.Logger;

public class LootTableProvider implements DataProvider {
   private static final Logger LOGGER = LogUtils.getLogger();
   private final PackOutput.PathProvider pathProvider;
   private final Set<ResourceLocation> requiredTables;
   private final List<LootTableProvider.SubProviderEntry> subProviders;

   public LootTableProvider(PackOutput p_254123_, Set<ResourceLocation> p_254481_, List<LootTableProvider.SubProviderEntry> p_253798_) {
      this.pathProvider = p_254123_.createPathProvider(PackOutput.Target.DATA_PACK, "loot_tables");
      this.subProviders = p_253798_;
      this.requiredTables = p_254481_;
   }

   public CompletableFuture<?> run(CachedOutput p_254060_) {
      Map<ResourceLocation, LootTable> map = Maps.newHashMap();
      this.getTables().forEach((p_248012_) -> {
         p_248012_.provider().get().generate((p_248006_, p_248007_) -> {
            if (map.put(p_248006_, p_248007_.setParamSet(p_248012_.paramSet).build()) != null) {
               throw new IllegalStateException("Duplicate loot table " + p_248006_);
            }
         });
      });
      ValidationContext validationcontext = new ValidationContext(LootContextParamSets.ALL_PARAMS, (p_124465_) -> {
         return null;
      }, map::get);

      validate(map, validationcontext);

      Multimap<String, String> multimap = validationcontext.getProblems();
      if (!multimap.isEmpty()) {
         multimap.forEach((p_124446_, p_124447_) -> {
            LOGGER.warn("Found validation problem in {}: {}", p_124446_, p_124447_);
         });
         throw new IllegalStateException("Failed to validate loot tables, see logs");
      } else {
         return CompletableFuture.allOf(map.entrySet().stream().map((p_253405_) -> {
            ResourceLocation resourcelocation1 = p_253405_.getKey();
            LootTable loottable = p_253405_.getValue();
            Path path = this.pathProvider.json(resourcelocation1);
            return DataProvider.saveStable(p_254060_, LootTables.serialize(loottable), path);
         }).toArray((p_253403_) -> {
            return new CompletableFuture[p_253403_];
         }));
      }
   }

   public List<LootTableProvider.SubProviderEntry> getTables() {
      return this.subProviders;
   }

   protected void validate(Map<ResourceLocation, LootTable> map, ValidationContext validationcontext) {
      for(ResourceLocation resourcelocation : Sets.difference(this.requiredTables, map.keySet())) {
         validationcontext.reportProblem("Missing built-in table: " + resourcelocation);
      }

      map.forEach((p_124441_, p_124442_) -> {
         LootTables.validate(validationcontext, p_124441_, p_124442_);
      });
   }

   public final String getName() {
      return "Loot Tables";
   }

   public static record SubProviderEntry(Supplier<LootTableSubProvider> provider, LootContextParamSet paramSet) {
   }
}
