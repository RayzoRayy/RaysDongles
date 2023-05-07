package net.minecraft.client.gui.components;

import com.mojang.blaze3d.systems.RenderSystem;
import com.mojang.blaze3d.vertex.PoseStack;
import net.minecraft.client.gui.GuiComponent;
import net.minecraft.client.gui.narration.NarrationElementOutput;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

@OnlyIn(Dist.CLIENT)
public class ImageWidget extends AbstractWidget {
   private final ResourceLocation imageLocation;

   public ImageWidget(int p_275550_, int p_275723_, ResourceLocation p_275649_) {
      this(0, 0, p_275550_, p_275723_, p_275649_);
   }

   public ImageWidget(int p_275421_, int p_275294_, int p_275403_, int p_275631_, ResourceLocation p_275648_) {
      super(p_275421_, p_275294_, p_275403_, p_275631_, Component.empty());
      this.imageLocation = p_275648_;
   }

   protected void updateWidgetNarration(NarrationElementOutput p_275454_) {
   }

   public void renderWidget(PoseStack p_275715_, int p_275730_, int p_275439_, float p_275378_) {
      RenderSystem.setShaderTexture(0, this.imageLocation);
      int i = this.getWidth();
      int j = this.getHeight();
      GuiComponent.blit(p_275715_, this.getX(), this.getY(), 0.0F, 0.0F, i, j, i, j);
   }
}