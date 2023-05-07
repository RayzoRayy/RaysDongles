package net.minecraft.client.gui.components;

import com.mojang.blaze3d.systems.RenderSystem;
import com.mojang.blaze3d.vertex.PoseStack;
import net.minecraft.client.gui.GuiComponent;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.RandomSource;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

@OnlyIn(Dist.CLIENT)
public class LogoRenderer extends GuiComponent {
   public static final ResourceLocation MINECRAFT_LOGO = new ResourceLocation("textures/gui/title/minecraft.png");
   public static final ResourceLocation MINECRAFT_EDITION = new ResourceLocation("textures/gui/title/edition.png");
   public static final int LOGO_WIDTH = 274;
   public static final int LOGO_HEIGHT = 44;
   public static final int DEFAULT_HEIGHT_OFFSET = 30;
   private final boolean showEasterEgg = (double)RandomSource.create().nextFloat() < 1.0E-4D;
   private final boolean keepLogoThroughFade;

   public LogoRenderer(boolean p_265300_) {
      this.keepLogoThroughFade = p_265300_;
   }

   public void renderLogo(PoseStack p_265113_, int p_265062_, float p_265180_) {
      this.renderLogo(p_265113_, p_265062_, p_265180_, 30);
   }

   public void renderLogo(PoseStack p_265239_, int p_265393_, float p_265718_, int p_265506_) {
      RenderSystem.setShaderTexture(0, MINECRAFT_LOGO);
      RenderSystem.setShaderColor(1.0F, 1.0F, 1.0F, this.keepLogoThroughFade ? 1.0F : p_265718_);
      int i = p_265393_ / 2 - 137;
      if (this.showEasterEgg) {
         blitOutlineBlack(i, p_265506_, (p_274660_, p_274661_) -> {
            blit(p_265239_, p_274660_, p_274661_, 0, 0, 99, 44);
            blit(p_265239_, p_274660_ + 99, p_274661_, 129, 0, 27, 44);
            blit(p_265239_, p_274660_ + 99 + 26, p_274661_, 126, 0, 3, 44);
            blit(p_265239_, p_274660_ + 99 + 26 + 3, p_274661_, 99, 0, 26, 44);
            blit(p_265239_, p_274660_ + 155, p_274661_, 0, 45, 155, 44);
         });
      } else {
         blitOutlineBlack(i, p_265506_, (p_274657_, p_274658_) -> {
            blit(p_265239_, p_274657_, p_274658_, 0, 0, 155, 44);
            blit(p_265239_, p_274657_ + 155, p_274658_, 0, 45, 155, 44);
         });
      }

      RenderSystem.setShaderTexture(0, MINECRAFT_EDITION);
      blit(p_265239_, i + 88, p_265506_ + 37, 0.0F, 0.0F, 98, 14, 128, 16);
      RenderSystem.setShaderColor(1.0F, 1.0F, 1.0F, 1.0F);
   }
}