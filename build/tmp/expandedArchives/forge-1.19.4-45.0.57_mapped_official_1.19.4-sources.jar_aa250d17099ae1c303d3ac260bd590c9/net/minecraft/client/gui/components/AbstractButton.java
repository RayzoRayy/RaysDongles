package net.minecraft.client.gui.components;

import com.mojang.blaze3d.systems.RenderSystem;
import com.mojang.blaze3d.vertex.PoseStack;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.Font;
import net.minecraft.network.chat.Component;
import net.minecraft.util.Mth;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

@OnlyIn(Dist.CLIENT)
public abstract class AbstractButton extends AbstractWidget {
   protected static final int TEXTURE_Y_OFFSET = 46;
   protected static final int TEXTURE_WIDTH = 200;
   protected static final int TEXTURE_HEIGHT = 20;
   protected static final int TEXTURE_BORDER_X = 20;
   protected static final int TEXTURE_BORDER_Y = 4;
   protected static final int TEXT_MARGIN = 2;

   public AbstractButton(int p_93365_, int p_93366_, int p_93367_, int p_93368_, Component p_93369_) {
      super(p_93365_, p_93366_, p_93367_, p_93368_, p_93369_);
   }

   public abstract void onPress();

   public void renderWidget(PoseStack p_275468_, int p_275505_, int p_275674_, float p_275696_) {
      Minecraft minecraft = Minecraft.getInstance();
      RenderSystem.setShaderTexture(0, WIDGETS_LOCATION);
      RenderSystem.setShaderColor(1.0F, 1.0F, 1.0F, this.alpha);
      RenderSystem.enableBlend();
      RenderSystem.enableDepthTest();
      blitNineSliced(p_275468_, this.getX(), this.getY(), this.getWidth(), this.getHeight(), 20, 4, 200, 20, 0, this.getTextureY());
      RenderSystem.setShaderColor(1.0F, 1.0F, 1.0F, 1.0F);
      int i = getFGColor();
      this.renderString(p_275468_, minecraft.font, i | Mth.ceil(this.alpha * 255.0F) << 24);
   }

   public void renderString(PoseStack p_275258_, Font p_275290_, int p_275734_) {
      this.renderScrollingString(p_275258_, p_275290_, 2, p_275734_);
   }

   private int getTextureY() {
      int i = 1;
      if (!this.active) {
         i = 0;
      } else if (this.isHoveredOrFocused()) {
         i = 2;
      }

      return 46 + i * 20;
   }

   public void onClick(double p_93371_, double p_93372_) {
      this.onPress();
   }

   public boolean keyPressed(int p_93374_, int p_93375_, int p_93376_) {
      if (this.active && this.visible) {
         if (p_93374_ != 257 && p_93374_ != 32 && p_93374_ != 335) {
            return false;
         } else {
            this.playDownSound(Minecraft.getInstance().getSoundManager());
            this.onPress();
            return true;
         }
      } else {
         return false;
      }
   }
}
