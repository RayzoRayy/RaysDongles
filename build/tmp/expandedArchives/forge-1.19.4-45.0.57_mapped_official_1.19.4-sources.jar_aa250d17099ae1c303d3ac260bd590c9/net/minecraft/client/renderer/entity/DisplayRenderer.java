package net.minecraft.client.renderer.entity;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import com.mojang.math.Transformation;
import java.util.ArrayList;
import java.util.List;
import net.minecraft.client.Camera;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.Font;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.block.BlockRenderDispatcher;
import net.minecraft.client.renderer.texture.OverlayTexture;
import net.minecraft.client.renderer.texture.TextureAtlas;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.FormattedCharSequence;
import net.minecraft.world.entity.Display;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import org.joml.Matrix4f;
import org.joml.Quaternionf;

@OnlyIn(Dist.CLIENT)
public abstract class DisplayRenderer<T extends Display> extends EntityRenderer<T> {
   private static final float MAX_SHADOW_RADIUS = 64.0F;
   private final EntityRenderDispatcher entityRenderDispatcher;

   protected DisplayRenderer(EntityRendererProvider.Context p_270168_) {
      super(p_270168_);
      this.entityRenderDispatcher = p_270168_.getEntityRenderDispatcher();
   }

   public ResourceLocation getTextureLocation(T p_270675_) {
      return TextureAtlas.LOCATION_BLOCKS;
   }

   public void render(T p_270405_, float p_270225_, float p_270279_, PoseStack p_270728_, MultiBufferSource p_270209_, int p_270298_) {
      float f = p_270405_.calculateInterpolationProgress(p_270279_);
      this.shadowRadius = Math.min(p_270405_.getShadowRadius(f), 64.0F);
      this.shadowStrength = p_270405_.getShadowStrength(f);
      int i = p_270405_.getPackedBrightnessOverride();
      int j = i != -1 ? i : p_270298_;
      super.render(p_270405_, p_270225_, p_270279_, p_270728_, p_270209_, j);
      p_270728_.pushPose();
      p_270728_.mulPose(this.calculateOrientation(p_270405_));
      Transformation transformation = p_270405_.transformation(f);
      p_270728_.mulPoseMatrix(transformation.getMatrix());
      p_270728_.last().normal().rotate(transformation.getLeftRotation()).rotate(transformation.getRightRotation());
      this.renderInner(p_270405_, p_270728_, p_270209_, j, f);
      p_270728_.popPose();
   }

   private Quaternionf calculateOrientation(T p_271013_) {
      Camera camera = this.entityRenderDispatcher.camera;
      Quaternionf quaternionf;
      switch (p_271013_.getBillboardConstraints()) {
         case FIXED:
            quaternionf = p_271013_.orientation();
            break;
         case HORIZONTAL:
            quaternionf = (new Quaternionf()).rotationYXZ(-0.017453292F * p_271013_.getYRot(), -0.017453292F * camera.getXRot(), 0.0F);
            break;
         case VERTICAL:
            quaternionf = (new Quaternionf()).rotationYXZ((float)Math.PI - ((float)Math.PI / 180F) * camera.getYRot(), ((float)Math.PI / 180F) * p_271013_.getXRot(), 0.0F);
            break;
         case CENTER:
            quaternionf = (new Quaternionf()).rotationYXZ((float)Math.PI - ((float)Math.PI / 180F) * camera.getYRot(), -0.017453292F * camera.getXRot(), 0.0F);
            break;
         default:
            throw new IncompatibleClassChangeError();
      }

      return quaternionf;
   }

   protected abstract void renderInner(T p_270246_, PoseStack p_270944_, MultiBufferSource p_270706_, int p_270263_, float p_270377_);

   @OnlyIn(Dist.CLIENT)
   public static class BlockDisplayRenderer extends DisplayRenderer<Display.BlockDisplay> {
      private final BlockRenderDispatcher blockRenderer;

      protected BlockDisplayRenderer(EntityRendererProvider.Context p_270283_) {
         super(p_270283_);
         this.blockRenderer = p_270283_.getBlockRenderDispatcher();
      }

      public void renderInner(Display.BlockDisplay p_270998_, PoseStack p_270530_, MultiBufferSource p_270995_, int p_270553_, float p_270920_) {
         this.blockRenderer.renderSingleBlock(p_270998_.getBlockState(), p_270530_, p_270995_, p_270553_, OverlayTexture.NO_OVERLAY);
      }
   }

   @OnlyIn(Dist.CLIENT)
   public static class ItemDisplayRenderer extends DisplayRenderer<Display.ItemDisplay> {
      private final ItemRenderer itemRenderer;

      protected ItemDisplayRenderer(EntityRendererProvider.Context p_270110_) {
         super(p_270110_);
         this.itemRenderer = p_270110_.getItemRenderer();
      }

      public void renderInner(Display.ItemDisplay p_270490_, PoseStack p_270693_, MultiBufferSource p_271002_, int p_270588_, float p_270590_) {
         this.itemRenderer.renderStatic(p_270490_.getItemStack(), p_270490_.getItemTransform(), p_270588_, OverlayTexture.NO_OVERLAY, p_270693_, p_271002_, p_270490_.getLevel(), p_270490_.getId());
      }
   }

   @OnlyIn(Dist.CLIENT)
   public static class TextDisplayRenderer extends DisplayRenderer<Display.TextDisplay> {
      private final Font font;

      protected TextDisplayRenderer(EntityRendererProvider.Context p_271012_) {
         super(p_271012_);
         this.font = p_271012_.getFont();
      }

      private Display.TextDisplay.CachedInfo splitLines(Component p_270823_, int p_270893_) {
         List<FormattedCharSequence> list = this.font.split(p_270823_, p_270893_);
         List<Display.TextDisplay.CachedLine> list1 = new ArrayList<>(list.size());
         int i = 0;

         for(FormattedCharSequence formattedcharsequence : list) {
            int j = this.font.width(formattedcharsequence);
            i = Math.max(i, j);
            list1.add(new Display.TextDisplay.CachedLine(formattedcharsequence, j));
         }

         return new Display.TextDisplay.CachedInfo(list1, i);
      }

      public void renderInner(Display.TextDisplay p_270749_, PoseStack p_270232_, MultiBufferSource p_270522_, int p_270936_, float p_270759_) {
         byte b0 = p_270749_.getFlags();
         boolean flag = (b0 & 2) != 0;
         boolean flag1 = (b0 & 4) != 0;
         boolean flag2 = (b0 & 1) != 0;
         Display.TextDisplay.Align display$textdisplay$align = Display.TextDisplay.getAlign(b0);
         byte b1 = p_270749_.getTextOpacity(p_270759_);
         int i;
         if (flag1) {
            float f = Minecraft.getInstance().options.getBackgroundOpacity(0.25F);
            i = (int)(f * 255.0F) << 24;
         } else {
            i = p_270749_.getBackgroundColor(p_270759_);
         }

         float f2 = 0.0F;
         Matrix4f matrix4f = p_270232_.last().pose();
         matrix4f.rotate((float)Math.PI, 0.0F, 1.0F, 0.0F);
         matrix4f.scale(-0.025F, -0.025F, -0.025F);
         Display.TextDisplay.CachedInfo display$textdisplay$cachedinfo = p_270749_.cacheDisplay(this::splitLines);
         int j = 9 + 1;
         int k = display$textdisplay$cachedinfo.width();
         int l = display$textdisplay$cachedinfo.lines().size() * j;
         matrix4f.translate(1.0F - (float)k / 2.0F, (float)(-l), 0.0F);
         if (i != 0) {
            VertexConsumer vertexconsumer = p_270522_.getBuffer(flag ? RenderType.textBackgroundSeeThrough() : RenderType.textBackground());
            vertexconsumer.vertex(matrix4f, -1.0F, -1.0F, 0.0F).color(i).uv2(p_270936_).endVertex();
            vertexconsumer.vertex(matrix4f, -1.0F, (float)l, 0.0F).color(i).uv2(p_270936_).endVertex();
            vertexconsumer.vertex(matrix4f, (float)k, (float)l, 0.0F).color(i).uv2(p_270936_).endVertex();
            vertexconsumer.vertex(matrix4f, (float)k, -1.0F, 0.0F).color(i).uv2(p_270936_).endVertex();
         }

         for(Display.TextDisplay.CachedLine display$textdisplay$cachedline : display$textdisplay$cachedinfo.lines()) {
            float f3;
            switch (display$textdisplay$align) {
               case LEFT:
                  f3 = 0.0F;
                  break;
               case RIGHT:
                  f3 = (float)(k - display$textdisplay$cachedline.width());
                  break;
               case CENTER:
                  f3 = (float)k / 2.0F - (float)display$textdisplay$cachedline.width() / 2.0F;
                  break;
               default:
                  throw new IncompatibleClassChangeError();
            }

            float f1 = f3;
            this.font.drawInBatch(display$textdisplay$cachedline.contents(), f1, f2, b1 << 24 | 16777215, flag2, matrix4f, p_270522_, flag ? Font.DisplayMode.SEE_THROUGH : Font.DisplayMode.POLYGON_OFFSET, 0, p_270936_);
            f2 += (float)j;
         }

      }
   }
}