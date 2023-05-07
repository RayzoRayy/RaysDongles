package net.minecraft.client.gui.screens.inventory.tooltip;

import net.minecraft.client.gui.screens.Screen;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import org.joml.Vector2ic;

@OnlyIn(Dist.CLIENT)
public interface ClientTooltipPositioner {
   Vector2ic positionTooltip(Screen p_263072_, int p_263026_, int p_262969_, int p_262971_, int p_263058_);
}