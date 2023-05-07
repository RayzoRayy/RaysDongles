package net.minecraft.client.gui.font.providers;

import com.google.common.collect.Lists;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.google.gson.JsonParseException;
import com.mojang.blaze3d.font.GlyphInfo;
import com.mojang.blaze3d.font.GlyphProvider;
import com.mojang.blaze3d.font.SheetGlyphInfo;
import com.mojang.blaze3d.platform.NativeImage;
import com.mojang.logging.LogUtils;
import it.unimi.dsi.fastutil.ints.Int2ObjectMap;
import it.unimi.dsi.fastutil.ints.Int2ObjectOpenHashMap;
import it.unimi.dsi.fastutil.ints.IntSet;
import it.unimi.dsi.fastutil.ints.IntSets;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;
import java.util.function.Function;
import javax.annotation.Nullable;
import net.minecraft.client.gui.font.glyphs.BakedGlyph;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.packs.resources.ResourceManager;
import net.minecraft.util.GsonHelper;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import org.slf4j.Logger;

@OnlyIn(Dist.CLIENT)
public class BitmapProvider implements GlyphProvider {
   static final Logger LOGGER = LogUtils.getLogger();
   private final NativeImage image;
   private final Int2ObjectMap<BitmapProvider.Glyph> glyphs;

   BitmapProvider(NativeImage p_95333_, Int2ObjectMap<BitmapProvider.Glyph> p_95334_) {
      this.image = p_95333_;
      this.glyphs = p_95334_;
   }

   public void close() {
      this.image.close();
   }

   @Nullable
   public GlyphInfo getGlyph(int p_232638_) {
      return this.glyphs.get(p_232638_);
   }

   public IntSet getSupportedGlyphs() {
      return IntSets.unmodifiable(this.glyphs.keySet());
   }

   @OnlyIn(Dist.CLIENT)
   public static class Builder implements GlyphProviderBuilder {
      private final ResourceLocation texture;
      private final List<int[]> chars;
      private final int height;
      private final int ascent;

      public Builder(ResourceLocation p_95349_, int p_95350_, int p_95351_, List<int[]> p_95352_) {
         this.texture = p_95349_.withPrefix("textures/");
         this.chars = p_95352_;
         this.height = p_95350_;
         this.ascent = p_95351_;
      }

      public static BitmapProvider.Builder fromJson(JsonObject p_95356_) {
         int i = GsonHelper.getAsInt(p_95356_, "height", 8);
         int j = GsonHelper.getAsInt(p_95356_, "ascent");
         if (j > i) {
            throw new JsonParseException("Ascent " + j + " higher than height " + i);
         } else {
            List<int[]> list = Lists.newArrayList();
            JsonArray jsonarray = GsonHelper.getAsJsonArray(p_95356_, "chars");

            for(int k = 0; k < jsonarray.size(); ++k) {
               String s = GsonHelper.convertToString(jsonarray.get(k), "chars[" + k + "]");
               int[] aint = s.codePoints().toArray();
               if (k > 0) {
                  int l = ((int[])list.get(0)).length;
                  if (aint.length != l) {
                     throw new JsonParseException("Elements of chars have to be the same length (found: " + aint.length + ", expected: " + l + "), pad with space or \\u0000");
                  }
               }

               list.add(aint);
            }

            if (!list.isEmpty() && ((int[])list.get(0)).length != 0) {
               return new BitmapProvider.Builder(new ResourceLocation(GsonHelper.getAsString(p_95356_, "file")), i, j, list);
            } else {
               throw new JsonParseException("Expected to find data in chars, found none.");
            }
         }
      }

      @Nullable
      public GlyphProvider create(ResourceManager p_95354_) {
         try (InputStream inputstream = p_95354_.open(this.texture)) {
            NativeImage nativeimage = NativeImage.read(NativeImage.Format.RGBA, inputstream);
            int i = nativeimage.getWidth();
            int j = nativeimage.getHeight();
            int k = i / ((int[])this.chars.get(0)).length;
            int l = j / this.chars.size();
            float f = (float)this.height / (float)l;
            Int2ObjectMap<BitmapProvider.Glyph> int2objectmap = new Int2ObjectOpenHashMap<>();

            for(int i1 = 0; i1 < this.chars.size(); ++i1) {
               int j1 = 0;

               for(int k1 : this.chars.get(i1)) {
                  int l1 = j1++;
                  if (k1 != 0) {
                     int i2 = this.getActualGlyphWidth(nativeimage, k, l, l1, i1);
                     BitmapProvider.Glyph bitmapprovider$glyph = int2objectmap.put(k1, new BitmapProvider.Glyph(f, nativeimage, l1 * k, i1 * l, k, l, (int)(0.5D + (double)((float)i2 * f)) + 1, this.ascent));
                     if (bitmapprovider$glyph != null) {
                        BitmapProvider.LOGGER.warn("Codepoint '{}' declared multiple times in {}", Integer.toHexString(k1), this.texture);
                     }
                  }
               }
            }

            return new BitmapProvider(nativeimage, int2objectmap);
         } catch (IOException ioexception) {
            throw new RuntimeException(ioexception.getMessage());
         }
      }

      private int getActualGlyphWidth(NativeImage p_95358_, int p_95359_, int p_95360_, int p_95361_, int p_95362_) {
         int i;
         for(i = p_95359_ - 1; i >= 0; --i) {
            int j = p_95361_ * p_95359_ + i;

            for(int k = 0; k < p_95360_; ++k) {
               int l = p_95362_ * p_95360_ + k;
               if (p_95358_.getLuminanceOrAlpha(j, l) != 0) {
                  return i + 1;
               }
            }
         }

         return i + 1;
      }
   }

   @OnlyIn(Dist.CLIENT)
   static record Glyph(float scale, NativeImage image, int offsetX, int offsetY, int width, int height, int advance, int ascent) implements GlyphInfo {
      public float getAdvance() {
         return (float)this.advance;
      }

      public BakedGlyph bake(Function<SheetGlyphInfo, BakedGlyph> p_232640_) {
         return p_232640_.apply(new SheetGlyphInfo() {
            public float getOversample() {
               return 1.0F / Glyph.this.scale;
            }

            public int getPixelWidth() {
               return Glyph.this.width;
            }

            public int getPixelHeight() {
               return Glyph.this.height;
            }

            public float getBearingY() {
               return SheetGlyphInfo.super.getBearingY() + 7.0F - (float)Glyph.this.ascent;
            }

            public void upload(int p_232658_, int p_232659_) {
               Glyph.this.image.upload(0, p_232658_, p_232659_, Glyph.this.offsetX, Glyph.this.offsetY, Glyph.this.width, Glyph.this.height, false, false);
            }

            public boolean isColored() {
               return Glyph.this.image.format().components() > 1;
            }
         });
      }
   }
}