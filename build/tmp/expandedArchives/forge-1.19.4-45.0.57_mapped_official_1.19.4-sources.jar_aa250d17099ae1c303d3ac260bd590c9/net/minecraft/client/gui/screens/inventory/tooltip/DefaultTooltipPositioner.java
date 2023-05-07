package net.minecraft.client.gui.screens.inventory.tooltip;

import net.minecraft.client.gui.screens.Screen;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import org.joml.Vector2i;
import org.joml.Vector2ic;

@OnlyIn(Dist.CLIENT)
public class DefaultTooltipPositioner implements ClientTooltipPositioner {
   public static final ClientTooltipPositioner INSTANCE = new DefaultTooltipPositioner();

   private DefaultTooltipPositioner() {
   }

   public Vector2ic positionTooltip(Screen p_263057_, int p_263033_, int p_263000_, int p_263006_, int p_262994_) {
      Vector2i vector2i = (new Vector2i(p_263033_, p_263000_)).add(12, -12);
      this.positionTooltip(p_263057_, vector2i, p_263006_, p_262994_);
      return vector2i;
   }

   private void positionTooltip(Screen p_263103_, Vector2i p_262944_, int p_263062_, int p_263106_) {
      if (p_262944_.x + p_263062_ > p_263103_.width) {
         p_262944_.x = Math.max(p_262944_.x - 24 - p_263062_, 4);
      }

      int i = p_263106_ + 3;
      if (p_262944_.y + i > p_263103_.height) {
         p_262944_.y = p_263103_.height - i;
      }

   }
}