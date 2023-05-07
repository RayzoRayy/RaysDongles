package net.minecraft.client.gui.font.providers;

import com.google.gson.JsonObject;
import com.google.gson.JsonParseException;
import com.mojang.blaze3d.font.GlyphInfo;
import com.mojang.blaze3d.font.GlyphProvider;
import com.mojang.blaze3d.font.SheetGlyphInfo;
import com.mojang.blaze3d.platform.NativeImage;
import com.mojang.logging.LogUtils;
import it.unimi.dsi.fastutil.ints.IntOpenHashSet;
import it.unimi.dsi.fastutil.ints.IntSet;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.IllegalFormatException;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.CompletableFuture;
import java.util.function.Function;
import javax.annotation.Nullable;
import net.minecraft.Util;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.font.glyphs.BakedGlyph;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.packs.resources.ResourceManager;
import net.minecraft.util.GsonHelper;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;

@OnlyIn(Dist.CLIENT)
public class LegacyUnicodeBitmapsProvider implements GlyphProvider {
   static final Logger LOGGER = LogUtils.getLogger();
   private static final int UNICODE_SHEETS = 256;
   private static final int CODEPOINTS_PER_SHEET = 256;
   private static final int TEXTURE_SIZE = 256;
   private static final byte NO_GLYPH = 0;
   private static final int TOTAL_CODEPOINTS = 65536;
   private final byte[] sizes;
   private final LegacyUnicodeBitmapsProvider.Sheet[] sheets = new LegacyUnicodeBitmapsProvider.Sheet[256];

   public LegacyUnicodeBitmapsProvider(ResourceManager p_95429_, byte[] p_95430_, String p_95431_) {
      this.sizes = p_95430_;
      Set<ResourceLocation> set = new HashSet<>();

      for(int i = 0; i < 256; ++i) {
         int j = i * 256;
         set.add(getSheetLocation(p_95431_, j));
      }

      String s = getCommonSearchPrefix(set);
      Map<ResourceLocation, CompletableFuture<NativeImage>> map = new HashMap<>();
      p_95429_.listResources(s, set::contains).forEach((p_248801_, p_248818_) -> {
         map.put(p_248801_, CompletableFuture.supplyAsync(() -> {
            try (InputStream inputstream = p_248818_.open()) {
               return NativeImage.read(NativeImage.Format.RGBA, inputstream);
            } catch (IOException ioexception) {
               LOGGER.error("Failed to read resource {} from pack {}", p_248801_, p_248818_.sourcePackId());
               return null;
            }
         }, Util.backgroundExecutor()));
      });
      List<CompletableFuture<?>> list = new ArrayList<>(256);

      for(int k = 0; k < 256; ++k) {
         int l = k * 256;
         int i1 = k;
         ResourceLocation resourcelocation = getSheetLocation(p_95431_, l);
         CompletableFuture<NativeImage> completablefuture = map.get(resourcelocation);
         if (completablefuture != null) {
            list.add(completablefuture.thenAcceptAsync((p_249925_) -> {
               if (p_249925_ != null) {
                  if (p_249925_.getWidth() == 256 && p_249925_.getHeight() == 256) {
                     for(int j1 = 0; j1 < 256; ++j1) {
                        byte b0 = p_95430_[l + j1];
                        if (b0 != 0 && getLeft(b0) > getRight(b0)) {
                           p_95430_[l + j1] = 0;
                        }
                     }

                     this.sheets[i1] = new LegacyUnicodeBitmapsProvider.Sheet(p_95430_, p_249925_);
                  } else {
                     p_249925_.close();
                     Arrays.fill(p_95430_, l, l + 256, (byte)0);
                  }

               }
            }, Util.backgroundExecutor()));
         }
      }

      CompletableFuture.allOf(list.toArray((p_249378_) -> {
         return new CompletableFuture[p_249378_];
      })).join();
   }

   private static String getCommonSearchPrefix(Set<ResourceLocation> p_250091_) {
      String s = StringUtils.getCommonPrefix(p_250091_.stream().map(ResourceLocation::getPath).toArray((p_248925_) -> {
         return new String[p_248925_];
      }));
      int i = s.lastIndexOf("/");
      return i == -1 ? "" : s.substring(0, i);
   }

   public void close() {
      for(LegacyUnicodeBitmapsProvider.Sheet legacyunicodebitmapsprovider$sheet : this.sheets) {
         if (legacyunicodebitmapsprovider$sheet != null) {
            legacyunicodebitmapsprovider$sheet.close();
         }
      }

   }

   private static ResourceLocation getSheetLocation(String p_250928_, int p_249330_) {
      String s = String.format(Locale.ROOT, "%02x", p_249330_ / 256);
      ResourceLocation resourcelocation = new ResourceLocation(String.format(Locale.ROOT, p_250928_, s));
      return resourcelocation.withPrefix("textures/");
   }

   @Nullable
   public GlyphInfo getGlyph(int p_232668_) {
      if (p_232668_ >= 0 && p_232668_ < this.sizes.length) {
         int i = p_232668_ / 256;
         LegacyUnicodeBitmapsProvider.Sheet legacyunicodebitmapsprovider$sheet = this.sheets[i];
         return legacyunicodebitmapsprovider$sheet != null ? legacyunicodebitmapsprovider$sheet.getGlyph(p_232668_) : null;
      } else {
         return null;
      }
   }

   public IntSet getSupportedGlyphs() {
      IntSet intset = new IntOpenHashSet();

      for(int i = 0; i < this.sizes.length; ++i) {
         if (this.sizes[i] != 0) {
            intset.add(i);
         }
      }

      return intset;
   }

   static int getLeft(byte p_95434_) {
      return p_95434_ >> 4 & 15;
   }

   static int getRight(byte p_95441_) {
      return (p_95441_ & 15) + 1;
   }

   @OnlyIn(Dist.CLIENT)
   public static class Builder implements GlyphProviderBuilder {
      private final ResourceLocation metadata;
      private final String texturePattern;

      public Builder(ResourceLocation p_95448_, String p_95449_) {
         this.metadata = p_95448_;
         this.texturePattern = p_95449_;
      }

      public static GlyphProviderBuilder fromJson(JsonObject p_95453_) {
         return new LegacyUnicodeBitmapsProvider.Builder(new ResourceLocation(GsonHelper.getAsString(p_95453_, "sizes")), getTemplate(p_95453_));
      }

      private static String getTemplate(JsonObject p_182570_) {
         String s = GsonHelper.getAsString(p_182570_, "template");

         try {
            String.format(Locale.ROOT, s, "");
            return s;
         } catch (IllegalFormatException illegalformatexception) {
            throw new JsonParseException("Invalid legacy unicode template supplied, expected single '%s': " + s);
         }
      }

      @Nullable
      public GlyphProvider create(ResourceManager p_95451_) {
         try (InputStream inputstream = Minecraft.getInstance().getResourceManager().open(this.metadata)) {
            byte[] abyte = inputstream.readNBytes(65536);
            return new LegacyUnicodeBitmapsProvider(p_95451_, abyte, this.texturePattern);
         } catch (IOException ioexception) {
            LegacyUnicodeBitmapsProvider.LOGGER.error("Cannot load {}, unicode glyphs will not render correctly", (Object)this.metadata);
            return null;
         }
      }
   }

   @OnlyIn(Dist.CLIENT)
   static record Glyph(int sourceX, int sourceY, int width, int height, NativeImage source) implements GlyphInfo {
      public float getAdvance() {
         return (float)(this.width / 2 + 1);
      }

      public float getShadowOffset() {
         return 0.5F;
      }

      public float getBoldOffset() {
         return 0.5F;
      }

      public BakedGlyph bake(Function<SheetGlyphInfo, BakedGlyph> p_232670_) {
         return p_232670_.apply(new SheetGlyphInfo() {
            public float getOversample() {
               return 2.0F;
            }

            public int getPixelWidth() {
               return Glyph.this.width;
            }

            public int getPixelHeight() {
               return Glyph.this.height;
            }

            public void upload(int p_232685_, int p_232686_) {
               Glyph.this.source.upload(0, p_232685_, p_232686_, Glyph.this.sourceX, Glyph.this.sourceY, Glyph.this.width, Glyph.this.height, false, false);
            }

            public boolean isColored() {
               return Glyph.this.source.format().components() > 1;
            }
         });
      }
   }

   @OnlyIn(Dist.CLIENT)
   static class Sheet implements AutoCloseable {
      private final byte[] sizes;
      private final NativeImage source;

      Sheet(byte[] p_248858_, NativeImage p_249074_) {
         this.sizes = p_248858_;
         this.source = p_249074_;
      }

      public void close() {
         this.source.close();
      }

      @Nullable
      public GlyphInfo getGlyph(int p_249902_) {
         byte b0 = this.sizes[p_249902_];
         if (b0 != 0) {
            int i = LegacyUnicodeBitmapsProvider.getLeft(b0);
            return new LegacyUnicodeBitmapsProvider.Glyph(p_249902_ % 16 * 16 + i, (p_249902_ & 255) / 16 * 16, LegacyUnicodeBitmapsProvider.getRight(b0) - i, 16, this.source);
         } else {
            return null;
         }
      }
   }
}