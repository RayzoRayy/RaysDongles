package net.minecraft.tags;

import com.google.common.collect.HashMultimap;
import com.google.common.collect.ImmutableSet;
import com.google.common.collect.Maps;
import com.google.common.collect.Multimap;
import com.google.common.collect.Sets;
import com.google.gson.JsonElement;
import com.google.gson.JsonParser;
import com.mojang.datafixers.util.Either;
import com.mojang.logging.LogUtils;
import com.mojang.serialization.Dynamic;
import com.mojang.serialization.JsonOps;
import java.io.Reader;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Optional;
import java.util.Set;
import java.util.function.BiConsumer;
import java.util.function.Function;
import java.util.stream.Collectors;
import javax.annotation.Nullable;
import net.minecraft.resources.FileToIdConverter;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.packs.resources.Resource;
import net.minecraft.server.packs.resources.ResourceManager;
import org.slf4j.Logger;

public class TagLoader<T> {
   private static final Logger LOGGER = LogUtils.getLogger();
   final Function<ResourceLocation, Optional<? extends T>> idToValue;
   private final String directory;

   public TagLoader(Function<ResourceLocation, Optional<? extends T>> p_144493_, String p_144494_) {
      this.idToValue = p_144493_;
      this.directory = p_144494_;
   }

   public Map<ResourceLocation, List<TagLoader.EntryWithSource>> load(ResourceManager p_144496_) {
      Map<ResourceLocation, List<TagLoader.EntryWithSource>> map = Maps.newHashMap();
      FileToIdConverter filetoidconverter = FileToIdConverter.json(this.directory);

      for(Map.Entry<ResourceLocation, List<Resource>> entry : filetoidconverter.listMatchingResourceStacks(p_144496_).entrySet()) {
         ResourceLocation resourcelocation = entry.getKey();
         ResourceLocation resourcelocation1 = filetoidconverter.fileToId(resourcelocation);

         for(Resource resource : entry.getValue()) {
            try (Reader reader = resource.openAsReader()) {
               JsonElement jsonelement = JsonParser.parseReader(reader);
               List<TagLoader.EntryWithSource> list = map.computeIfAbsent(resourcelocation1, (p_215974_) -> {
                  return new ArrayList();
               });
               TagFile tagfile = TagFile.CODEC.parse(new Dynamic<>(JsonOps.INSTANCE, jsonelement)).getOrThrow(false, LOGGER::error);
               if (tagfile.replace()) {
                  list.clear();
               }

               String s = resource.sourcePackId();
               tagfile.entries().forEach((p_215997_) -> {
                  list.add(new TagLoader.EntryWithSource(p_215997_, s));
               });
            } catch (Exception exception) {
               LOGGER.error("Couldn't read tag list {} from {} in data pack {}", resourcelocation1, resourcelocation, resource.sourcePackId(), exception);
            }
         }
      }

      return map;
   }

   private static void visitDependenciesAndElement(Map<ResourceLocation, List<TagLoader.EntryWithSource>> p_144524_, Multimap<ResourceLocation, ResourceLocation> p_144525_, Set<ResourceLocation> p_144526_, ResourceLocation p_144527_, BiConsumer<ResourceLocation, List<TagLoader.EntryWithSource>> p_144528_) {
      if (p_144526_.add(p_144527_)) {
         p_144525_.get(p_144527_).forEach((p_216014_) -> {
            visitDependenciesAndElement(p_144524_, p_144525_, p_144526_, p_216014_, p_144528_);
         });
         List<TagLoader.EntryWithSource> list = p_144524_.get(p_144527_);
         if (list != null) {
            p_144528_.accept(p_144527_, list);
         }

      }
   }

   private static boolean isCyclic(Multimap<ResourceLocation, ResourceLocation> p_144502_, ResourceLocation p_144503_, ResourceLocation p_144504_) {
      Collection<ResourceLocation> collection = p_144502_.get(p_144504_);
      return collection.contains(p_144503_) ? true : collection.stream().anyMatch((p_216032_) -> {
         return isCyclic(p_144502_, p_144503_, p_216032_);
      });
   }

   private static void addDependencyIfNotCyclic(Multimap<ResourceLocation, ResourceLocation> p_144551_, ResourceLocation p_144552_, ResourceLocation p_144553_) {
      if (!isCyclic(p_144551_, p_144552_, p_144553_)) {
         p_144551_.put(p_144552_, p_144553_);
      }

   }

   private Either<Collection<TagLoader.EntryWithSource>, Collection<T>> build(TagEntry.Lookup<T> p_215979_, List<TagLoader.EntryWithSource> p_215980_) {
      ImmutableSet.Builder<T> builder = ImmutableSet.builder();
      List<TagLoader.EntryWithSource> list = new ArrayList<>();

      for(TagLoader.EntryWithSource tagloader$entrywithsource : p_215980_) {
         if (!tagloader$entrywithsource.entry().build(p_215979_, builder::add)) {
            list.add(tagloader$entrywithsource);
         }
      }

      return list.isEmpty() ? Either.right(builder.build()) : Either.left(list);
   }

   public Map<ResourceLocation, Collection<T>> build(Map<ResourceLocation, List<TagLoader.EntryWithSource>> p_203899_) {
      final Map<ResourceLocation, Collection<T>> map = Maps.newHashMap();
      TagEntry.Lookup<T> lookup = new TagEntry.Lookup<T>() {
         @Nullable
         public T element(ResourceLocation p_216039_) {
            return TagLoader.this.idToValue.apply(p_216039_).orElse(null);
         }

         @Nullable
         public Collection<T> tag(ResourceLocation p_216041_) {
            return map.get(p_216041_);
         }
      };
      Multimap<ResourceLocation, ResourceLocation> multimap = HashMultimap.create();
      p_203899_.forEach((p_215992_, p_215993_) -> {
         p_215993_.forEach((p_216020_) -> {
            p_216020_.entry.visitRequiredDependencies((p_144563_) -> {
               addDependencyIfNotCyclic(multimap, p_215992_, p_144563_);
            });
         });
      });
      p_203899_.forEach((p_216023_, p_216024_) -> {
         p_216024_.forEach((p_215989_) -> {
            p_215989_.entry.visitOptionalDependencies((p_216028_) -> {
               addDependencyIfNotCyclic(multimap, p_216023_, p_216028_);
            });
         });
      });
      Set<ResourceLocation> set = Sets.newHashSet();
      p_203899_.keySet().forEach((p_216008_) -> {
         visitDependenciesAndElement(p_203899_, multimap, set, p_216008_, (p_215984_, p_215985_) -> {
            this.build(lookup, p_215985_).ifLeft((p_215977_) -> {
               LOGGER.error("Couldn't load tag {} as it is missing following references: {}", p_215977_, p_215977_.stream().map(Objects::toString).collect(Collectors.joining(", \n\t")));
            }).ifRight((p_216001_) -> {
               map.put(p_215984_, p_216001_);
            });
         });
      });
      return map;
   }

   public Map<ResourceLocation, Collection<T>> loadAndBuild(ResourceManager p_203901_) {
      return this.build(this.load(p_203901_));
   }

   public static record EntryWithSource(TagEntry entry, String source) {
      public String toString() {
         return this.entry + " (from " + this.source + ")";
      }
   }
}
