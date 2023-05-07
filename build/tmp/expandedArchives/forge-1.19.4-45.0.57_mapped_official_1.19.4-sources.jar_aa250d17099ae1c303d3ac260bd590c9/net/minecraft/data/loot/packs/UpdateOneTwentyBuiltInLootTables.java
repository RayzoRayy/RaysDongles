package net.minecraft.data.loot.packs;

import com.google.common.collect.Sets;
import java.util.Collections;
import java.util.Set;
import net.minecraft.resources.ResourceLocation;

public class UpdateOneTwentyBuiltInLootTables {
   private static final Set<ResourceLocation> LOCATIONS = Sets.newHashSet();
   private static final Set<ResourceLocation> IMMUTABLE_LOCATIONS = Collections.unmodifiableSet(LOCATIONS);
   public static final ResourceLocation DESERT_WELL_ARCHAEOLOGY = register("archaeology/desert_well");
   public static final ResourceLocation DESERT_PYRAMID_ARCHAEOLOGY = register("archaeology/desert_pyramid");

   private static ResourceLocation register(String p_272728_) {
      return register(new ResourceLocation(p_272728_));
   }

   private static ResourceLocation register(ResourceLocation p_273688_) {
      if (LOCATIONS.add(p_273688_)) {
         return p_273688_;
      } else {
         throw new IllegalArgumentException(p_273688_ + " is already a registered built-in loot table");
      }
   }

   public static Set<ResourceLocation> all() {
      return IMMUTABLE_LOCATIONS;
   }
}