package net.minecraft.client.gui;

import com.mojang.blaze3d.platform.GlStateManager;
import com.mojang.blaze3d.platform.Window;
import com.mojang.blaze3d.systems.RenderSystem;
import com.mojang.blaze3d.vertex.BufferBuilder;
import com.mojang.blaze3d.vertex.BufferUploader;
import com.mojang.blaze3d.vertex.DefaultVertexFormat;
import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.Tesselator;
import com.mojang.blaze3d.vertex.VertexFormat;
import com.mojang.math.Divisor;
import it.unimi.dsi.fastutil.ints.IntIterator;
import java.util.ArrayDeque;
import java.util.Deque;
import java.util.Objects;
import java.util.function.BiConsumer;
import javax.annotation.Nullable;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.navigation.ScreenRectangle;
import net.minecraft.client.renderer.GameRenderer;
import net.minecraft.client.renderer.texture.TextureAtlasSprite;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.FastColor;
import net.minecraft.util.FormattedCharSequence;
import net.minecraft.util.Mth;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import org.joml.Matrix4f;

@OnlyIn(Dist.CLIENT)
public abstract class GuiComponent {
   public static final ResourceLocation BACKGROUND_LOCATION = new ResourceLocation("textures/gui/options_background.png");
   public static final ResourceLocation STATS_ICON_LOCATION = new ResourceLocation("textures/gui/container/stats_icons.png");
   public static final ResourceLocation GUI_ICONS_LOCATION = new ResourceLocation("textures/gui/icons.png");
   public static final ResourceLocation LIGHT_DIRT_BACKGROUND = new ResourceLocation("textures/gui/light_dirt_background.png");
   private static final GuiComponent.ScissorStack SCISSOR_STACK = new GuiComponent.ScissorStack();

   protected static void hLine(PoseStack p_93155_, int p_93156_, int p_93157_, int p_93158_, int p_93159_) {
      if (p_93157_ < p_93156_) {
         int i = p_93156_;
         p_93156_ = p_93157_;
         p_93157_ = i;
      }

      fill(p_93155_, p_93156_, p_93158_, p_93157_ + 1, p_93158_ + 1, p_93159_);
   }

   protected static void vLine(PoseStack p_93223_, int p_93224_, int p_93225_, int p_93226_, int p_93227_) {
      if (p_93226_ < p_93225_) {
         int i = p_93225_;
         p_93225_ = p_93226_;
         p_93226_ = i;
      }

      fill(p_93223_, p_93224_, p_93225_ + 1, p_93224_ + 1, p_93226_, p_93227_);
   }

   public static void enableScissor(int p_239261_, int p_239262_, int p_239263_, int p_239264_) {
      applyScissor(SCISSOR_STACK.push(new ScreenRectangle(p_239261_, p_239262_, p_239263_ - p_239261_, p_239264_ - p_239262_)));
   }

   public static void disableScissor() {
      applyScissor(SCISSOR_STACK.pop());
   }

   private static void applyScissor(@Nullable ScreenRectangle p_276021_) {
      if (p_276021_ != null) {
         Window window = Minecraft.getInstance().getWindow();
         int i = window.getHeight();
         double d0 = window.getGuiScale();
         double d1 = (double)p_276021_.left() * d0;
         double d2 = (double)i - (double)p_276021_.bottom() * d0;
         double d3 = (double)p_276021_.width() * d0;
         double d4 = (double)p_276021_.height() * d0;
         RenderSystem.enableScissor((int)d1, (int)d2, Math.max(0, (int)d3), Math.max(0, (int)d4));
      } else {
         RenderSystem.disableScissor();
      }

   }

   public static void fill(PoseStack p_93173_, int p_93174_, int p_93175_, int p_93176_, int p_93177_, int p_93178_) {
      fill(p_93173_, p_93174_, p_93175_, p_93176_, p_93177_, 0, p_93178_);
   }

   public static void fill(PoseStack p_265170_, int p_265299_, int p_265262_, int p_265737_, int p_265091_, int p_265558_, int p_265191_) {
      Matrix4f matrix4f = p_265170_.last().pose();
      if (p_265299_ < p_265737_) {
         int i = p_265299_;
         p_265299_ = p_265737_;
         p_265737_ = i;
      }

      if (p_265262_ < p_265091_) {
         int j = p_265262_;
         p_265262_ = p_265091_;
         p_265091_ = j;
      }

      float f3 = (float)FastColor.ARGB32.alpha(p_265191_) / 255.0F;
      float f = (float)FastColor.ARGB32.red(p_265191_) / 255.0F;
      float f1 = (float)FastColor.ARGB32.green(p_265191_) / 255.0F;
      float f2 = (float)FastColor.ARGB32.blue(p_265191_) / 255.0F;
      BufferBuilder bufferbuilder = Tesselator.getInstance().getBuilder();
      RenderSystem.enableBlend();
      RenderSystem.setShader(GameRenderer::getPositionColorShader);
      bufferbuilder.begin(VertexFormat.Mode.QUADS, DefaultVertexFormat.POSITION_COLOR);
      bufferbuilder.vertex(matrix4f, (float)p_265299_, (float)p_265262_, (float)p_265558_).color(f, f1, f2, f3).endVertex();
      bufferbuilder.vertex(matrix4f, (float)p_265299_, (float)p_265091_, (float)p_265558_).color(f, f1, f2, f3).endVertex();
      bufferbuilder.vertex(matrix4f, (float)p_265737_, (float)p_265091_, (float)p_265558_).color(f, f1, f2, f3).endVertex();
      bufferbuilder.vertex(matrix4f, (float)p_265737_, (float)p_265262_, (float)p_265558_).color(f, f1, f2, f3).endVertex();
      BufferUploader.drawWithShader(bufferbuilder.end());
      RenderSystem.disableBlend();
   }

   protected static void fillGradient(PoseStack p_93180_, int p_93181_, int p_93182_, int p_93183_, int p_93184_, int p_93185_, int p_93186_) {
      fillGradient(p_93180_, p_93181_, p_93182_, p_93183_, p_93184_, p_93185_, p_93186_, 0);
   }

   protected static void fillGradient(PoseStack p_168741_, int p_168742_, int p_168743_, int p_168744_, int p_168745_, int p_168746_, int p_168747_, int p_168748_) {
      RenderSystem.enableBlend();
      RenderSystem.setShader(GameRenderer::getPositionColorShader);
      Tesselator tesselator = Tesselator.getInstance();
      BufferBuilder bufferbuilder = tesselator.getBuilder();
      bufferbuilder.begin(VertexFormat.Mode.QUADS, DefaultVertexFormat.POSITION_COLOR);
      fillGradient(p_168741_.last().pose(), bufferbuilder, p_168742_, p_168743_, p_168744_, p_168745_, p_168748_, p_168746_, p_168747_);
      tesselator.end();
      RenderSystem.disableBlend();
   }

   protected static void fillGradient(Matrix4f p_254526_, BufferBuilder p_93125_, int p_93126_, int p_93127_, int p_93128_, int p_93129_, int p_93130_, int p_93131_, int p_93132_) {
      float f = (float)FastColor.ARGB32.alpha(p_93131_) / 255.0F;
      float f1 = (float)FastColor.ARGB32.red(p_93131_) / 255.0F;
      float f2 = (float)FastColor.ARGB32.green(p_93131_) / 255.0F;
      float f3 = (float)FastColor.ARGB32.blue(p_93131_) / 255.0F;
      float f4 = (float)FastColor.ARGB32.alpha(p_93132_) / 255.0F;
      float f5 = (float)FastColor.ARGB32.red(p_93132_) / 255.0F;
      float f6 = (float)FastColor.ARGB32.green(p_93132_) / 255.0F;
      float f7 = (float)FastColor.ARGB32.blue(p_93132_) / 255.0F;
      p_93125_.vertex(p_254526_, (float)p_93126_, (float)p_93127_, (float)p_93130_).color(f1, f2, f3, f).endVertex();
      p_93125_.vertex(p_254526_, (float)p_93126_, (float)p_93129_, (float)p_93130_).color(f5, f6, f7, f4).endVertex();
      p_93125_.vertex(p_254526_, (float)p_93128_, (float)p_93129_, (float)p_93130_).color(f5, f6, f7, f4).endVertex();
      p_93125_.vertex(p_254526_, (float)p_93128_, (float)p_93127_, (float)p_93130_).color(f1, f2, f3, f).endVertex();
   }

   public static void drawCenteredString(PoseStack p_93209_, Font p_93210_, String p_93211_, int p_93212_, int p_93213_, int p_93214_) {
      p_93210_.drawShadow(p_93209_, p_93211_, (float)(p_93212_ - p_93210_.width(p_93211_) / 2), (float)p_93213_, p_93214_);
   }

   public static void drawCenteredString(PoseStack p_93216_, Font p_93217_, Component p_93218_, int p_93219_, int p_93220_, int p_93221_) {
      FormattedCharSequence formattedcharsequence = p_93218_.getVisualOrderText();
      p_93217_.drawShadow(p_93216_, formattedcharsequence, (float)(p_93219_ - p_93217_.width(formattedcharsequence) / 2), (float)p_93220_, p_93221_);
   }

   public static void drawCenteredString(PoseStack p_168750_, Font p_168751_, FormattedCharSequence p_168752_, int p_168753_, int p_168754_, int p_168755_) {
      p_168751_.drawShadow(p_168750_, p_168752_, (float)(p_168753_ - p_168751_.width(p_168752_) / 2), (float)p_168754_, p_168755_);
   }

   public static void drawString(PoseStack p_93237_, Font p_93238_, String p_93239_, int p_93240_, int p_93241_, int p_93242_) {
      p_93238_.drawShadow(p_93237_, p_93239_, (float)p_93240_, (float)p_93241_, p_93242_);
   }

   public static void drawString(PoseStack p_168757_, Font p_168758_, FormattedCharSequence p_168759_, int p_168760_, int p_168761_, int p_168762_) {
      p_168758_.drawShadow(p_168757_, p_168759_, (float)p_168760_, (float)p_168761_, p_168762_);
   }

   public static void drawString(PoseStack p_93244_, Font p_93245_, Component p_93246_, int p_93247_, int p_93248_, int p_93249_) {
      p_93245_.drawShadow(p_93244_, p_93246_, (float)p_93247_, (float)p_93248_, p_93249_);
   }

   public static void blitOutlineBlack(int p_93102_, int p_93103_, BiConsumer<Integer, Integer> p_93104_) {
      RenderSystem.blendFuncSeparate(GlStateManager.SourceFactor.ZERO, GlStateManager.DestFactor.ONE_MINUS_SRC_ALPHA, GlStateManager.SourceFactor.SRC_ALPHA, GlStateManager.DestFactor.ONE_MINUS_SRC_ALPHA);
      p_93104_.accept(p_93102_ + 1, p_93103_);
      p_93104_.accept(p_93102_ - 1, p_93103_);
      p_93104_.accept(p_93102_, p_93103_ + 1);
      p_93104_.accept(p_93102_, p_93103_ - 1);
      RenderSystem.defaultBlendFunc();
      p_93104_.accept(p_93102_, p_93103_);
   }

   public static void blit(PoseStack p_93201_, int p_93202_, int p_93203_, int p_93204_, int p_93205_, int p_93206_, TextureAtlasSprite p_93207_) {
      innerBlit(p_93201_.last().pose(), p_93202_, p_93202_ + p_93205_, p_93203_, p_93203_ + p_93206_, p_93204_, p_93207_.getU0(), p_93207_.getU1(), p_93207_.getV0(), p_93207_.getV1());
   }

   public static void blit(PoseStack p_267237_, int p_266847_, int p_266730_, int p_266944_, int p_266929_, int p_266735_, TextureAtlasSprite p_266828_, float p_267032_, float p_267019_, float p_267126_, float p_266756_) {
      innerBlit(p_267237_.last().pose(), p_266847_, p_266847_ + p_266929_, p_266730_, p_266730_ + p_266735_, p_266944_, p_266828_.getU0(), p_266828_.getU1(), p_266828_.getV0(), p_266828_.getV1(), p_267032_, p_267019_, p_267126_, p_266756_);
   }

   public static void renderOutline(PoseStack p_275510_, int p_275680_, int p_275707_, int p_275647_, int p_275238_, int p_275643_) {
      fill(p_275510_, p_275680_, p_275707_, p_275680_ + p_275647_, p_275707_ + 1, p_275643_);
      fill(p_275510_, p_275680_, p_275707_ + p_275238_ - 1, p_275680_ + p_275647_, p_275707_ + p_275238_, p_275643_);
      fill(p_275510_, p_275680_, p_275707_ + 1, p_275680_ + 1, p_275707_ + p_275238_ - 1, p_275643_);
      fill(p_275510_, p_275680_ + p_275647_ - 1, p_275707_ + 1, p_275680_ + p_275647_, p_275707_ + p_275238_ - 1, p_275643_);
   }

   public static void blit(PoseStack p_93229_, int p_93230_, int p_93231_, int p_93232_, int p_93233_, int p_93234_, int p_93235_) {
      blit(p_93229_, p_93230_, p_93231_, 0, (float)p_93232_, (float)p_93233_, p_93234_, p_93235_, 256, 256);
   }

   public static void blit(PoseStack p_93144_, int p_93145_, int p_93146_, int p_93147_, float p_93148_, float p_93149_, int p_93150_, int p_93151_, int p_93152_, int p_93153_) {
      blit(p_93144_, p_93145_, p_93145_ + p_93150_, p_93146_, p_93146_ + p_93151_, p_93147_, p_93150_, p_93151_, p_93148_, p_93149_, p_93152_, p_93153_);
   }

   public static void blit(PoseStack p_93161_, int p_93162_, int p_93163_, int p_93164_, int p_93165_, float p_93166_, float p_93167_, int p_93168_, int p_93169_, int p_93170_, int p_93171_) {
      blit(p_93161_, p_93162_, p_93162_ + p_93164_, p_93163_, p_93163_ + p_93165_, 0, p_93168_, p_93169_, p_93166_, p_93167_, p_93170_, p_93171_);
   }

   public static void blit(PoseStack p_93134_, int p_93135_, int p_93136_, float p_93137_, float p_93138_, int p_93139_, int p_93140_, int p_93141_, int p_93142_) {
      blit(p_93134_, p_93135_, p_93136_, p_93139_, p_93140_, p_93137_, p_93138_, p_93139_, p_93140_, p_93141_, p_93142_);
   }

   private static void blit(PoseStack p_93188_, int p_93189_, int p_93190_, int p_93191_, int p_93192_, int p_93193_, int p_93194_, int p_93195_, float p_93196_, float p_93197_, int p_93198_, int p_93199_) {
      innerBlit(p_93188_.last().pose(), p_93189_, p_93190_, p_93191_, p_93192_, p_93193_, (p_93196_ + 0.0F) / (float)p_93198_, (p_93196_ + (float)p_93194_) / (float)p_93198_, (p_93197_ + 0.0F) / (float)p_93199_, (p_93197_ + (float)p_93195_) / (float)p_93199_);
   }

   private static void innerBlit(Matrix4f p_254452_, int p_93114_, int p_93115_, int p_93116_, int p_93117_, int p_93118_, float p_93119_, float p_93120_, float p_93121_, float p_93122_) {
      RenderSystem.setShader(GameRenderer::getPositionTexShader);
      BufferBuilder bufferbuilder = Tesselator.getInstance().getBuilder();
      bufferbuilder.begin(VertexFormat.Mode.QUADS, DefaultVertexFormat.POSITION_TEX);
      bufferbuilder.vertex(p_254452_, (float)p_93114_, (float)p_93116_, (float)p_93118_).uv(p_93119_, p_93121_).endVertex();
      bufferbuilder.vertex(p_254452_, (float)p_93114_, (float)p_93117_, (float)p_93118_).uv(p_93119_, p_93122_).endVertex();
      bufferbuilder.vertex(p_254452_, (float)p_93115_, (float)p_93117_, (float)p_93118_).uv(p_93120_, p_93122_).endVertex();
      bufferbuilder.vertex(p_254452_, (float)p_93115_, (float)p_93116_, (float)p_93118_).uv(p_93120_, p_93121_).endVertex();
      BufferUploader.drawWithShader(bufferbuilder.end());
   }

   private static void innerBlit(Matrix4f p_267291_, int p_266998_, int p_266799_, int p_267254_, int p_267187_, int p_267149_, float p_266788_, float p_266950_, float p_267255_, float p_267102_, float p_267305_, float p_267134_, float p_266747_, float p_266801_) {
      RenderSystem.setShader(GameRenderer::getPositionColorTexShader);
      RenderSystem.enableBlend();
      BufferBuilder bufferbuilder = Tesselator.getInstance().getBuilder();
      bufferbuilder.begin(VertexFormat.Mode.QUADS, DefaultVertexFormat.POSITION_COLOR_TEX);
      bufferbuilder.vertex(p_267291_, (float)p_266998_, (float)p_267254_, (float)p_267149_).color(p_267305_, p_267134_, p_266747_, p_266801_).uv(p_266788_, p_267255_).endVertex();
      bufferbuilder.vertex(p_267291_, (float)p_266998_, (float)p_267187_, (float)p_267149_).color(p_267305_, p_267134_, p_266747_, p_266801_).uv(p_266788_, p_267102_).endVertex();
      bufferbuilder.vertex(p_267291_, (float)p_266799_, (float)p_267187_, (float)p_267149_).color(p_267305_, p_267134_, p_266747_, p_266801_).uv(p_266950_, p_267102_).endVertex();
      bufferbuilder.vertex(p_267291_, (float)p_266799_, (float)p_267254_, (float)p_267149_).color(p_267305_, p_267134_, p_266747_, p_266801_).uv(p_266950_, p_267255_).endVertex();
      BufferUploader.drawWithShader(bufferbuilder.end());
      RenderSystem.disableBlend();
   }

   public static void blitNineSliced(PoseStack p_268074_, int p_267951_, int p_267990_, int p_267940_, int p_268153_, int p_268039_, int p_268077_, int p_268104_, int p_268286_, int p_268150_) {
      blitNineSliced(p_268074_, p_267951_, p_267990_, p_267940_, p_268153_, p_268039_, p_268039_, p_268039_, p_268039_, p_268077_, p_268104_, p_268286_, p_268150_);
   }

   public static void blitNineSliced(PoseStack p_276051_, int p_276037_, int p_276039_, int p_276052_, int p_276041_, int p_276045_, int p_276035_, int p_276064_, int p_276043_, int p_276026_, int p_276036_) {
      blitNineSliced(p_276051_, p_276037_, p_276039_, p_276052_, p_276041_, p_276045_, p_276035_, p_276045_, p_276035_, p_276064_, p_276043_, p_276026_, p_276036_);
   }

   public static void blitNineSliced(PoseStack p_268130_, int p_268227_, int p_268163_, int p_267980_, int p_268198_, int p_268060_, int p_268055_, int p_268157_, int p_268238_, int p_268215_, int p_268205_, int p_268248_, int p_267943_) {
      p_268060_ = Math.min(p_268060_, p_267980_ / 2);
      p_268157_ = Math.min(p_268157_, p_267980_ / 2);
      p_268055_ = Math.min(p_268055_, p_268198_ / 2);
      p_268238_ = Math.min(p_268238_, p_268198_ / 2);
      if (p_267980_ == p_268215_ && p_268198_ == p_268205_) {
         blit(p_268130_, p_268227_, p_268163_, p_268248_, p_267943_, p_267980_, p_268198_);
      } else if (p_268198_ == p_268205_) {
         blit(p_268130_, p_268227_, p_268163_, p_268248_, p_267943_, p_268060_, p_268198_);
         blitRepeating(p_268130_, p_268227_ + p_268060_, p_268163_, p_267980_ - p_268157_ - p_268060_, p_268198_, p_268248_ + p_268060_, p_267943_, p_268215_ - p_268157_ - p_268060_, p_268205_);
         blit(p_268130_, p_268227_ + p_267980_ - p_268157_, p_268163_, p_268248_ + p_268215_ - p_268157_, p_267943_, p_268157_, p_268198_);
      } else if (p_267980_ == p_268215_) {
         blit(p_268130_, p_268227_, p_268163_, p_268248_, p_267943_, p_267980_, p_268055_);
         blitRepeating(p_268130_, p_268227_, p_268163_ + p_268055_, p_267980_, p_268198_ - p_268238_ - p_268055_, p_268248_, p_267943_ + p_268055_, p_268215_, p_268205_ - p_268238_ - p_268055_);
         blit(p_268130_, p_268227_, p_268163_ + p_268198_ - p_268238_, p_268248_, p_267943_ + p_268205_ - p_268238_, p_267980_, p_268238_);
      } else {
         blit(p_268130_, p_268227_, p_268163_, p_268248_, p_267943_, p_268060_, p_268055_);
         blitRepeating(p_268130_, p_268227_ + p_268060_, p_268163_, p_267980_ - p_268157_ - p_268060_, p_268055_, p_268248_ + p_268060_, p_267943_, p_268215_ - p_268157_ - p_268060_, p_268055_);
         blit(p_268130_, p_268227_ + p_267980_ - p_268157_, p_268163_, p_268248_ + p_268215_ - p_268157_, p_267943_, p_268157_, p_268055_);
         blit(p_268130_, p_268227_, p_268163_ + p_268198_ - p_268238_, p_268248_, p_267943_ + p_268205_ - p_268238_, p_268060_, p_268238_);
         blitRepeating(p_268130_, p_268227_ + p_268060_, p_268163_ + p_268198_ - p_268238_, p_267980_ - p_268157_ - p_268060_, p_268238_, p_268248_ + p_268060_, p_267943_ + p_268205_ - p_268238_, p_268215_ - p_268157_ - p_268060_, p_268238_);
         blit(p_268130_, p_268227_ + p_267980_ - p_268157_, p_268163_ + p_268198_ - p_268238_, p_268248_ + p_268215_ - p_268157_, p_267943_ + p_268205_ - p_268238_, p_268157_, p_268238_);
         blitRepeating(p_268130_, p_268227_, p_268163_ + p_268055_, p_268060_, p_268198_ - p_268238_ - p_268055_, p_268248_, p_267943_ + p_268055_, p_268060_, p_268205_ - p_268238_ - p_268055_);
         blitRepeating(p_268130_, p_268227_ + p_268060_, p_268163_ + p_268055_, p_267980_ - p_268157_ - p_268060_, p_268198_ - p_268238_ - p_268055_, p_268248_ + p_268060_, p_267943_ + p_268055_, p_268215_ - p_268157_ - p_268060_, p_268205_ - p_268238_ - p_268055_);
         blitRepeating(p_268130_, p_268227_ + p_267980_ - p_268157_, p_268163_ + p_268055_, p_268060_, p_268198_ - p_268238_ - p_268055_, p_268248_ + p_268215_ - p_268157_, p_267943_ + p_268055_, p_268157_, p_268205_ - p_268238_ - p_268055_);
      }
   }

   public static void blitRepeating(PoseStack p_268006_, int p_268243_, int p_268142_, int p_267983_, int p_268249_, int p_268110_, int p_268012_, int p_268290_, int p_268226_) {
      int i = p_268243_;

      int j;
      for(IntIterator intiterator = slices(p_267983_, p_268290_); intiterator.hasNext(); i += j) {
         j = intiterator.nextInt();
         int k = (p_268290_ - j) / 2;
         int l = p_268142_;

         int i1;
         for(IntIterator intiterator1 = slices(p_268249_, p_268226_); intiterator1.hasNext(); l += i1) {
            i1 = intiterator1.nextInt();
            int j1 = (p_268226_ - i1) / 2;
            blit(p_268006_, i, l, p_268110_ + k, p_268012_ + j1, j, i1);
         }
      }

   }

   private static IntIterator slices(int p_276023_, int p_276059_) {
      int i = Mth.positiveCeilDiv(p_276023_, p_276059_);
      return new Divisor(p_276023_, i);
   }

   @OnlyIn(Dist.CLIENT)
   static class ScissorStack {
      private final Deque<ScreenRectangle> stack = new ArrayDeque<>();

      public ScreenRectangle push(ScreenRectangle p_276054_) {
         ScreenRectangle screenrectangle = this.stack.peekLast();
         if (screenrectangle != null) {
            ScreenRectangle screenrectangle1 = Objects.requireNonNullElse(p_276054_.intersection(screenrectangle), ScreenRectangle.empty());
            this.stack.addLast(screenrectangle1);
            return screenrectangle1;
         } else {
            this.stack.addLast(p_276054_);
            return p_276054_;
         }
      }

      @Nullable
      public ScreenRectangle pop() {
         if (this.stack.isEmpty()) {
            throw new IllegalStateException("Scissor stack underflow");
         } else {
            this.stack.removeLast();
            return this.stack.peekLast();
         }
      }
   }
}