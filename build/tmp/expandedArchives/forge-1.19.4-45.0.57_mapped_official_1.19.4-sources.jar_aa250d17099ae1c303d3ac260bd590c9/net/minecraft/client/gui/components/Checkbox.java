package net.minecraft.client.gui.components;

import com.mojang.blaze3d.systems.RenderSystem;
import com.mojang.blaze3d.vertex.PoseStack;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.Font;
import net.minecraft.client.gui.narration.NarratedElementType;
import net.minecraft.client.gui.narration.NarrationElementOutput;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.Mth;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

@OnlyIn(Dist.CLIENT)
public class Checkbox extends AbstractButton {
   private static final ResourceLocation TEXTURE = new ResourceLocation("textures/gui/checkbox.png");
   private static final int TEXT_COLOR = 14737632;
   private boolean selected;
   private final boolean showLabel;

   public Checkbox(int p_93826_, int p_93827_, int p_93828_, int p_93829_, Component p_93830_, boolean p_93831_) {
      this(p_93826_, p_93827_, p_93828_, p_93829_, p_93830_, p_93831_, true);
   }

   public Checkbox(int p_93833_, int p_93834_, int p_93835_, int p_93836_, Component p_93837_, boolean p_93838_, boolean p_93839_) {
      super(p_93833_, p_93834_, p_93835_, p_93836_, p_93837_);
      this.selected = p_93838_;
      this.showLabel = p_93839_;
   }

   public void onPress() {
      this.selected = !this.selected;
   }

   public boolean selected() {
      return this.selected;
   }

   public void updateWidgetNarration(NarrationElementOutput p_260253_) {
      p_260253_.add(NarratedElementType.TITLE, this.createNarrationMessage());
      if (this.active) {
         if (this.isFocused()) {
            p_260253_.add(NarratedElementType.USAGE, Component.translatable("narration.checkbox.usage.focused"));
         } else {
            p_260253_.add(NarratedElementType.USAGE, Component.translatable("narration.checkbox.usage.hovered"));
         }
      }

   }

   public void renderWidget(PoseStack p_93843_, int p_93844_, int p_93845_, float p_93846_) {
      Minecraft minecraft = Minecraft.getInstance();
      RenderSystem.setShaderTexture(0, TEXTURE);
      RenderSystem.enableDepthTest();
      Font font = minecraft.font;
      RenderSystem.setShaderColor(1.0F, 1.0F, 1.0F, this.alpha);
      RenderSystem.enableBlend();
      blit(p_93843_, this.getX(), this.getY(), this.isFocused() ? 20.0F : 0.0F, this.selected ? 20.0F : 0.0F, 20, this.height, 64, 64);
      RenderSystem.setShaderColor(1.0F, 1.0F, 1.0F, 1.0F);
      if (this.showLabel) {
         drawString(p_93843_, font, this.getMessage(), this.getX() + 24, this.getY() + (this.height - 8) / 2, 14737632 | Mth.ceil(this.alpha * 255.0F) << 24);
      }

   }
}