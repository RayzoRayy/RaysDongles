package net.minecraft.client.gui.screens.inventory.tooltip;

import net.minecraft.client.gui.components.AbstractWidget;
import net.minecraft.client.gui.screens.Screen;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import org.joml.Vector2i;
import org.joml.Vector2ic;

@OnlyIn(Dist.CLIENT)
public class BelowOrAboveWidgetTooltipPositioner implements ClientTooltipPositioner {
   private final AbstractWidget widget;

   public BelowOrAboveWidgetTooltipPositioner(AbstractWidget p_263116_) {
      this.widget = p_263116_;
   }

   public Vector2ic positionTooltip(Screen p_263063_, int p_263059_, int p_262935_, int p_262972_, int p_263089_) {
      Vector2i vector2i = new Vector2i();
      vector2i.x = this.widget.getX() + 3;
      vector2i.y = this.widget.getY() + this.widget.getHeight() + 3 + 1;
      if (vector2i.y + p_263089_ + 3 > p_263063_.height) {
         vector2i.y = this.widget.getY() - p_263089_ - 3 - 1;
      }

      if (vector2i.x + p_262972_ > p_263063_.width) {
         vector2i.x = Math.max(this.widget.getX() + this.widget.getWidth() - p_262972_ - 3, 4);
      }

      return vector2i;
   }
}