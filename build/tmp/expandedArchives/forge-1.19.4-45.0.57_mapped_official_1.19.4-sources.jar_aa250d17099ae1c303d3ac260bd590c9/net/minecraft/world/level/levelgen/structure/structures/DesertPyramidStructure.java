package net.minecraft.world.level.levelgen.structure.structures;

import com.mojang.serialization.Codec;
import it.unimi.dsi.fastutil.objects.ObjectArrayList;
import java.util.Set;
import net.minecraft.Util;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Vec3i;
import net.minecraft.data.loot.packs.UpdateOneTwentyBuiltInLootTables;
import net.minecraft.util.RandomSource;
import net.minecraft.util.SortedArraySet;
import net.minecraft.world.flag.FeatureFlags;
import net.minecraft.world.level.ChunkPos;
import net.minecraft.world.level.StructureManager;
import net.minecraft.world.level.WorldGenLevel;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.chunk.ChunkGenerator;
import net.minecraft.world.level.levelgen.structure.BoundingBox;
import net.minecraft.world.level.levelgen.structure.SinglePieceStructure;
import net.minecraft.world.level.levelgen.structure.Structure;
import net.minecraft.world.level.levelgen.structure.StructurePiece;
import net.minecraft.world.level.levelgen.structure.StructureType;
import net.minecraft.world.level.levelgen.structure.pieces.PiecesContainer;

public class DesertPyramidStructure extends SinglePieceStructure {
   public static final Codec<DesertPyramidStructure> CODEC = simpleCodec(DesertPyramidStructure::new);

   public DesertPyramidStructure(Structure.StructureSettings p_227418_) {
      super(DesertPyramidPiece::new, 21, 21, p_227418_);
   }

   public void afterPlace(WorldGenLevel p_273644_, StructureManager p_272615_, ChunkGenerator p_273655_, RandomSource p_272939_, BoundingBox p_273179_, ChunkPos p_273334_, PiecesContainer p_273575_) {
      if (p_273644_.enabledFeatures().contains(FeatureFlags.UPDATE_1_20)) {
         Set<BlockPos> set = SortedArraySet.create(Vec3i::compareTo);

         for(StructurePiece structurepiece : p_273575_.pieces()) {
            if (structurepiece instanceof DesertPyramidPiece) {
               DesertPyramidPiece desertpyramidpiece = (DesertPyramidPiece)structurepiece;
               set.addAll(desertpyramidpiece.getPotentialSuspiciousSandWorldPositions());
            }
         }

         ObjectArrayList<BlockPos> objectarraylist = new ObjectArrayList<>(set.stream().toList());
         Util.shuffle(objectarraylist, p_272939_);
         int i = Math.min(set.size(), p_272939_.nextInt(5, 8));

         for(BlockPos blockpos : objectarraylist) {
            if (i > 0) {
               --i;
               p_273644_.setBlock(blockpos, Blocks.SUSPICIOUS_SAND.defaultBlockState(), 2);
               p_273644_.getBlockEntity(blockpos, BlockEntityType.SUSPICIOUS_SAND).ifPresent((p_273083_) -> {
                  p_273083_.setLootTable(UpdateOneTwentyBuiltInLootTables.DESERT_PYRAMID_ARCHAEOLOGY, blockpos.asLong());
               });
            } else {
               p_273644_.setBlock(blockpos, Blocks.SAND.defaultBlockState(), 2);
            }
         }

      }
   }

   public StructureType<?> type() {
      return StructureType.DESERT_PYRAMID;
   }
}