package net.minecraft.world.level.chunk;

import com.google.common.collect.ImmutableList;
import com.google.common.collect.Lists;
import com.mojang.datafixers.util.Either;
import it.unimi.dsi.fastutil.ints.IntArrayList;
import it.unimi.dsi.fastutil.ints.IntList;
import java.util.Collections;
import java.util.EnumSet;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.Executor;
import java.util.function.Function;
import javax.annotation.Nullable;
import net.minecraft.Util;
import net.minecraft.core.Registry;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.level.ChunkHolder;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.server.level.ThreadedLevelLightEngine;
import net.minecraft.server.level.WorldGenRegion;
import net.minecraft.util.profiling.jfr.JvmProfiler;
import net.minecraft.util.profiling.jfr.callback.ProfiledDuration;
import net.minecraft.world.level.levelgen.BelowZeroRetrogen;
import net.minecraft.world.level.levelgen.GenerationStep;
import net.minecraft.world.level.levelgen.Heightmap;
import net.minecraft.world.level.levelgen.blending.Blender;
import net.minecraft.world.level.levelgen.structure.templatesystem.StructureTemplateManager;

public class ChunkStatus {
   public static final int MAX_STRUCTURE_DISTANCE = 8;
   private static final EnumSet<Heightmap.Types> PRE_FEATURES = EnumSet.of(Heightmap.Types.OCEAN_FLOOR_WG, Heightmap.Types.WORLD_SURFACE_WG);
   public static final EnumSet<Heightmap.Types> POST_FEATURES = EnumSet.of(Heightmap.Types.OCEAN_FLOOR, Heightmap.Types.WORLD_SURFACE, Heightmap.Types.MOTION_BLOCKING, Heightmap.Types.MOTION_BLOCKING_NO_LEAVES);
   private static final ChunkStatus.LoadingTask PASSTHROUGH_LOAD_TASK = (p_223343_, p_223344_, p_223345_, p_223346_, p_223347_, p_223348_) -> {
      if (p_223348_ instanceof ProtoChunk protochunk) {
         if (!p_223348_.getStatus().isOrAfter(p_223343_)) {
            protochunk.setStatus(p_223343_);
         }
      }

      return CompletableFuture.completedFuture(Either.left(p_223348_));
   };
   public static final ChunkStatus EMPTY = registerSimple("empty", (ChunkStatus)null, -1, PRE_FEATURES, ChunkStatus.ChunkType.PROTOCHUNK, (p_156307_, p_156308_, p_156309_, p_156310_, p_156311_) -> {
   });
   public static final ChunkStatus STRUCTURE_STARTS = register("structure_starts", EMPTY, 0, PRE_FEATURES, ChunkStatus.ChunkType.PROTOCHUNK, (p_223361_, p_223362_, p_223363_, p_223364_, p_223365_, p_223366_, p_223367_, p_223368_, p_223369_, p_223370_) -> {
      if (!p_223369_.getStatus().isOrAfter(p_223361_)) {
         if (p_223363_.getServer().getWorldData().worldGenOptions().generateStructures()) {
            p_223364_.createStructures(p_223363_.registryAccess(), p_223363_.getChunkSource().getGeneratorState(), p_223363_.structureManager(), p_223369_, p_223365_);
         }

         if (p_223369_ instanceof ProtoChunk) {
            ProtoChunk protochunk = (ProtoChunk)p_223369_;
            protochunk.setStatus(p_223361_);
         }

         p_223363_.onStructureStartsAvailable(p_223369_);
      }

      return CompletableFuture.completedFuture(Either.left(p_223369_));
   }, (p_223325_, p_223326_, p_223327_, p_223328_, p_223329_, p_223330_) -> {
      if (!p_223330_.getStatus().isOrAfter(p_223325_)) {
         if (p_223330_ instanceof ProtoChunk) {
            ProtoChunk protochunk = (ProtoChunk)p_223330_;
            protochunk.setStatus(p_223325_);
         }

         p_223326_.onStructureStartsAvailable(p_223330_);
      }

      return CompletableFuture.completedFuture(Either.left(p_223330_));
   });
   public static final ChunkStatus STRUCTURE_REFERENCES = registerSimple("structure_references", STRUCTURE_STARTS, 8, PRE_FEATURES, ChunkStatus.ChunkType.PROTOCHUNK, (p_196843_, p_196844_, p_196845_, p_196846_, p_196847_) -> {
      WorldGenRegion worldgenregion = new WorldGenRegion(p_196844_, p_196846_, p_196843_, -1);
      p_196845_.createReferences(worldgenregion, p_196844_.structureManager().forWorldGenRegion(worldgenregion), p_196847_);
   });
   public static final ChunkStatus BIOMES = register("biomes", STRUCTURE_REFERENCES, 8, PRE_FEATURES, ChunkStatus.ChunkType.PROTOCHUNK, (p_255565_, p_255566_, p_255567_, p_255568_, p_255569_, p_255570_, p_255571_, p_255572_, p_255573_, p_255574_) -> {
      if (!p_255574_ && p_255573_.getStatus().isOrAfter(p_255565_)) {
         return CompletableFuture.completedFuture(Either.left(p_255573_));
      } else {
         WorldGenRegion worldgenregion = new WorldGenRegion(p_255567_, p_255572_, p_255565_, -1);
         return p_255568_.createBiomes(p_255566_, p_255567_.getChunkSource().randomState(), Blender.of(worldgenregion), p_255567_.structureManager().forWorldGenRegion(worldgenregion), p_255573_).thenApply((p_196819_) -> {
            if (p_196819_ instanceof ProtoChunk) {
               ((ProtoChunk)p_196819_).setStatus(p_255565_);
            }

            return Either.left(p_196819_);
         });
      }
   });
   public static final ChunkStatus NOISE = register("noise", BIOMES, 8, PRE_FEATURES, ChunkStatus.ChunkType.PROTOCHUNK, (p_223332_, p_223333_, p_223334_, p_223335_, p_223336_, p_223337_, p_223338_, p_223339_, p_223340_, p_223341_) -> {
      if (!p_223341_ && p_223340_.getStatus().isOrAfter(p_223332_)) {
         return CompletableFuture.completedFuture(Either.left(p_223340_));
      } else {
         WorldGenRegion worldgenregion = new WorldGenRegion(p_223334_, p_223339_, p_223332_, 0);
         return p_223335_.fillFromNoise(p_223333_, Blender.of(worldgenregion), p_223334_.getChunkSource().randomState(), p_223334_.structureManager().forWorldGenRegion(worldgenregion), p_223340_).thenApply((p_196792_) -> {
            if (p_196792_ instanceof ProtoChunk protochunk) {
               BelowZeroRetrogen belowzeroretrogen = protochunk.getBelowZeroRetrogen();
               if (belowzeroretrogen != null) {
                  BelowZeroRetrogen.replaceOldBedrock(protochunk);
                  if (belowzeroretrogen.hasBedrockHoles()) {
                     belowzeroretrogen.applyBedrockMask(protochunk);
                  }
               }

               protochunk.setStatus(p_223332_);
            }

            return Either.left(p_196792_);
         });
      }
   });
   public static final ChunkStatus SURFACE = registerSimple("surface", NOISE, 8, PRE_FEATURES, ChunkStatus.ChunkType.PROTOCHUNK, (p_156247_, p_156248_, p_156249_, p_156250_, p_156251_) -> {
      WorldGenRegion worldgenregion = new WorldGenRegion(p_156248_, p_156250_, p_156247_, 0);
      p_156249_.buildSurface(worldgenregion, p_156248_.structureManager().forWorldGenRegion(worldgenregion), p_156248_.getChunkSource().randomState(), p_156251_);
   });
   public static final ChunkStatus CARVERS = registerSimple("carvers", SURFACE, 8, PRE_FEATURES, ChunkStatus.ChunkType.PROTOCHUNK, (p_270056_, p_270057_, p_270058_, p_270059_, p_270060_) -> {
      WorldGenRegion worldgenregion = new WorldGenRegion(p_270057_, p_270059_, p_270056_, 0);
      if (p_270060_ instanceof ProtoChunk protochunk) {
         Blender.addAroundOldChunksCarvingMaskFilter(worldgenregion, protochunk);
      }

      p_270058_.applyCarvers(worldgenregion, p_270057_.getSeed(), p_270057_.getChunkSource().randomState(), p_270057_.getBiomeManager(), p_270057_.structureManager().forWorldGenRegion(worldgenregion), p_270060_, GenerationStep.Carving.AIR);
   });
   public static final ChunkStatus LIQUID_CARVERS = registerSimple("liquid_carvers", CARVERS, 8, POST_FEATURES, ChunkStatus.ChunkType.PROTOCHUNK, (p_196805_, p_196806_, p_196807_, p_196808_, p_196809_) -> {
   });
   public static final ChunkStatus FEATURES = register("features", LIQUID_CARVERS, 8, POST_FEATURES, ChunkStatus.ChunkType.PROTOCHUNK, (p_223314_, p_223315_, p_223316_, p_223317_, p_223318_, p_223319_, p_223320_, p_223321_, p_223322_, p_223323_) -> {
      ProtoChunk protochunk = (ProtoChunk)p_223322_;
      protochunk.setLightEngine(p_223319_);
      if (p_223323_ || !p_223322_.getStatus().isOrAfter(p_223314_)) {
         Heightmap.primeHeightmaps(p_223322_, EnumSet.of(Heightmap.Types.MOTION_BLOCKING, Heightmap.Types.MOTION_BLOCKING_NO_LEAVES, Heightmap.Types.OCEAN_FLOOR, Heightmap.Types.WORLD_SURFACE));
         WorldGenRegion worldgenregion = new WorldGenRegion(p_223316_, p_223321_, p_223314_, 1);
         p_223317_.applyBiomeDecoration(worldgenregion, p_223322_, p_223316_.structureManager().forWorldGenRegion(worldgenregion));
         Blender.generateBorderTicks(worldgenregion, p_223322_);
         protochunk.setStatus(p_223314_);
      }

      return p_223319_.retainData(p_223322_).thenApply(Either::left);
   }, (p_223307_, p_223308_, p_223309_, p_223310_, p_223311_, p_223312_) -> {
      return p_223310_.retainData(p_223312_).thenApply(Either::left);
   });
   public static final ChunkStatus LIGHT = register("light", FEATURES, 1, POST_FEATURES, ChunkStatus.ChunkType.PROTOCHUNK, (p_223296_, p_223297_, p_223298_, p_223299_, p_223300_, p_223301_, p_223302_, p_223303_, p_223304_, p_223305_) -> {
      return lightChunk(p_223296_, p_223301_, p_223304_);
   }, (p_223289_, p_223290_, p_223291_, p_223292_, p_223293_, p_223294_) -> {
      return lightChunk(p_223289_, p_223292_, p_223294_);
   });
   public static final ChunkStatus SPAWN = registerSimple("spawn", LIGHT, 0, POST_FEATURES, ChunkStatus.ChunkType.PROTOCHUNK, (p_196758_, p_196759_, p_196760_, p_196761_, p_196762_) -> {
      if (!p_196762_.isUpgrading()) {
         p_196760_.spawnOriginalMobs(new WorldGenRegion(p_196759_, p_196761_, p_196758_, -1));
      }

   });
   public static final ChunkStatus HEIGHTMAPS = registerSimple("heightmaps", SPAWN, 0, POST_FEATURES, ChunkStatus.ChunkType.PROTOCHUNK, (p_223254_, p_223255_, p_223256_, p_223257_, p_223258_) -> {
   });
   public static final ChunkStatus FULL = register("full", HEIGHTMAPS, 0, POST_FEATURES, ChunkStatus.ChunkType.LEVELCHUNK, (p_223267_, p_223268_, p_223269_, p_223270_, p_223271_, p_223272_, p_223273_, p_223274_, p_223275_, p_223276_) -> {
      return p_223273_.apply(p_223275_);
   }, (p_223260_, p_223261_, p_223262_, p_223263_, p_223264_, p_223265_) -> {
      return p_223264_.apply(p_223265_);
   });
   private static final List<ChunkStatus> STATUS_BY_RANGE = ImmutableList.of(FULL, FEATURES, LIQUID_CARVERS, BIOMES, STRUCTURE_STARTS, STRUCTURE_STARTS, STRUCTURE_STARTS, STRUCTURE_STARTS, STRUCTURE_STARTS, STRUCTURE_STARTS, STRUCTURE_STARTS, STRUCTURE_STARTS);
   private static final IntList RANGE_BY_STATUS = Util.make(new IntArrayList(getStatusList().size()), (p_223278_) -> {
      int i = 0;

      for(int j = getStatusList().size() - 1; j >= 0; --j) {
         while(i + 1 < STATUS_BY_RANGE.size() && j <= STATUS_BY_RANGE.get(i + 1).getIndex()) {
            ++i;
         }

         p_223278_.add(0, i);
      }

   });
   private final String name;
   private final int index;
   private final ChunkStatus parent;
   private final ChunkStatus.GenerationTask generationTask;
   private final ChunkStatus.LoadingTask loadingTask;
   private final int range;
   private final ChunkStatus.ChunkType chunkType;
   private final EnumSet<Heightmap.Types> heightmapsAfter;

   private static CompletableFuture<Either<ChunkAccess, ChunkHolder.ChunkLoadingFailure>> lightChunk(ChunkStatus p_62389_, ThreadedLevelLightEngine p_62390_, ChunkAccess p_62391_) {
      boolean flag = isLighted(p_62389_, p_62391_);
      if (!p_62391_.getStatus().isOrAfter(p_62389_)) {
         ((ProtoChunk)p_62391_).setStatus(p_62389_);
      }

      return p_62390_.lightChunk(p_62391_, flag).thenApply(Either::left);
   }

   private static ChunkStatus registerSimple(String p_62415_, @Nullable ChunkStatus p_62416_, int p_62417_, EnumSet<Heightmap.Types> p_62418_, ChunkStatus.ChunkType p_62419_, ChunkStatus.SimpleGenerationTask p_62420_) {
      return register(p_62415_, p_62416_, p_62417_, p_62418_, p_62419_, p_62420_);
   }

   private static ChunkStatus register(String p_62400_, @Nullable ChunkStatus p_62401_, int p_62402_, EnumSet<Heightmap.Types> p_62403_, ChunkStatus.ChunkType p_62404_, ChunkStatus.GenerationTask p_62405_) {
      return register(p_62400_, p_62401_, p_62402_, p_62403_, p_62404_, p_62405_, PASSTHROUGH_LOAD_TASK);
   }

   private static ChunkStatus register(String p_62407_, @Nullable ChunkStatus p_62408_, int p_62409_, EnumSet<Heightmap.Types> p_62410_, ChunkStatus.ChunkType p_62411_, ChunkStatus.GenerationTask p_62412_, ChunkStatus.LoadingTask p_62413_) {
      return Registry.register(BuiltInRegistries.CHUNK_STATUS, p_62407_, new ChunkStatus(p_62407_, p_62408_, p_62409_, p_62410_, p_62411_, p_62412_, p_62413_));
   }

   public static List<ChunkStatus> getStatusList() {
      List<ChunkStatus> list = Lists.newArrayList();

      ChunkStatus chunkstatus;
      for(chunkstatus = FULL; chunkstatus.getParent() != chunkstatus; chunkstatus = chunkstatus.getParent()) {
         list.add(chunkstatus);
      }

      list.add(chunkstatus);
      Collections.reverse(list);
      return list;
   }

   private static boolean isLighted(ChunkStatus p_62393_, ChunkAccess p_62394_) {
      return p_62394_.getStatus().isOrAfter(p_62393_) && p_62394_.isLightCorrect();
   }

   public static ChunkStatus getStatusAroundFullChunk(int p_156186_) {
      if (p_156186_ >= STATUS_BY_RANGE.size()) {
         return EMPTY;
      } else {
         return p_156186_ < 0 ? FULL : STATUS_BY_RANGE.get(p_156186_);
      }
   }

   public static int maxDistance() {
      return STATUS_BY_RANGE.size();
   }

   public static int getDistance(ChunkStatus p_62371_) {
      return RANGE_BY_STATUS.getInt(p_62371_.getIndex());
   }

   public ChunkStatus(String p_62342_, @Nullable ChunkStatus p_62343_, int p_62344_, EnumSet<Heightmap.Types> p_62345_, ChunkStatus.ChunkType p_62346_, ChunkStatus.GenerationTask p_62347_, ChunkStatus.LoadingTask p_62348_) {
      this.name = p_62342_;
      this.parent = p_62343_ == null ? this : p_62343_;
      this.generationTask = p_62347_;
      this.loadingTask = p_62348_;
      this.range = p_62344_;
      this.chunkType = p_62346_;
      this.heightmapsAfter = p_62345_;
      this.index = p_62343_ == null ? 0 : p_62343_.getIndex() + 1;
   }

   public int getIndex() {
      return this.index;
   }

   public String getName() {
      return this.name;
   }

   public ChunkStatus getParent() {
      return this.parent;
   }

   public CompletableFuture<Either<ChunkAccess, ChunkHolder.ChunkLoadingFailure>> generate(Executor p_223280_, ServerLevel p_223281_, ChunkGenerator p_223282_, StructureTemplateManager p_223283_, ThreadedLevelLightEngine p_223284_, Function<ChunkAccess, CompletableFuture<Either<ChunkAccess, ChunkHolder.ChunkLoadingFailure>>> p_223285_, List<ChunkAccess> p_223286_, boolean p_223287_) {
      ChunkAccess chunkaccess = p_223286_.get(p_223286_.size() / 2);
      ProfiledDuration profiledduration = JvmProfiler.INSTANCE.onChunkGenerate(chunkaccess.getPos(), p_223281_.dimension(), this.name);
      CompletableFuture<Either<ChunkAccess, ChunkHolder.ChunkLoadingFailure>> completablefuture = this.generationTask.doWork(this, p_223280_, p_223281_, p_223282_, p_223283_, p_223284_, p_223285_, p_223286_, chunkaccess, p_223287_);
      return profiledduration != null ? completablefuture.thenApply((p_223252_) -> {
         profiledduration.finish();
         return p_223252_;
      }) : completablefuture;
   }

   public CompletableFuture<Either<ChunkAccess, ChunkHolder.ChunkLoadingFailure>> load(ServerLevel p_223245_, StructureTemplateManager p_223246_, ThreadedLevelLightEngine p_223247_, Function<ChunkAccess, CompletableFuture<Either<ChunkAccess, ChunkHolder.ChunkLoadingFailure>>> p_223248_, ChunkAccess p_223249_) {
      return this.loadingTask.doWork(this, p_223245_, p_223246_, p_223247_, p_223248_, p_223249_);
   }

   public int getRange() {
      return this.range;
   }

   public ChunkStatus.ChunkType getChunkType() {
      return this.chunkType;
   }

   public static ChunkStatus byName(String p_62398_) {
      return BuiltInRegistries.CHUNK_STATUS.get(ResourceLocation.tryParse(p_62398_));
   }

   public EnumSet<Heightmap.Types> heightmapsAfter() {
      return this.heightmapsAfter;
   }

   public boolean isOrAfter(ChunkStatus p_62428_) {
      return this.getIndex() >= p_62428_.getIndex();
   }

   public String toString() {
      return BuiltInRegistries.CHUNK_STATUS.getKey(this).toString();
   }

   public static enum ChunkType {
      PROTOCHUNK,
      LEVELCHUNK;
   }

   interface GenerationTask {
      CompletableFuture<Either<ChunkAccess, ChunkHolder.ChunkLoadingFailure>> doWork(ChunkStatus p_223371_, Executor p_223372_, ServerLevel p_223373_, ChunkGenerator p_223374_, StructureTemplateManager p_223375_, ThreadedLevelLightEngine p_223376_, Function<ChunkAccess, CompletableFuture<Either<ChunkAccess, ChunkHolder.ChunkLoadingFailure>>> p_223377_, List<ChunkAccess> p_223378_, ChunkAccess p_223379_, boolean p_223380_);
   }

   interface LoadingTask {
      CompletableFuture<Either<ChunkAccess, ChunkHolder.ChunkLoadingFailure>> doWork(ChunkStatus p_223382_, ServerLevel p_223383_, StructureTemplateManager p_223384_, ThreadedLevelLightEngine p_223385_, Function<ChunkAccess, CompletableFuture<Either<ChunkAccess, ChunkHolder.ChunkLoadingFailure>>> p_223386_, ChunkAccess p_223387_);
   }

   interface SimpleGenerationTask extends ChunkStatus.GenerationTask {
      default CompletableFuture<Either<ChunkAccess, ChunkHolder.ChunkLoadingFailure>> doWork(ChunkStatus p_223389_, Executor p_223390_, ServerLevel p_223391_, ChunkGenerator p_223392_, StructureTemplateManager p_223393_, ThreadedLevelLightEngine p_223394_, Function<ChunkAccess, CompletableFuture<Either<ChunkAccess, ChunkHolder.ChunkLoadingFailure>>> p_223395_, List<ChunkAccess> p_223396_, ChunkAccess p_223397_, boolean p_223398_) {
         if (p_223398_ || !p_223397_.getStatus().isOrAfter(p_223389_)) {
            this.doWork(p_223389_, p_223391_, p_223392_, p_223396_, p_223397_);
            if (p_223397_ instanceof ProtoChunk) {
               ProtoChunk protochunk = (ProtoChunk)p_223397_;
               protochunk.setStatus(p_223389_);
            }
         }

         return CompletableFuture.completedFuture(Either.left(p_223397_));
      }

      void doWork(ChunkStatus p_156323_, ServerLevel p_156324_, ChunkGenerator p_156325_, List<ChunkAccess> p_156326_, ChunkAccess p_156327_);
   }
}