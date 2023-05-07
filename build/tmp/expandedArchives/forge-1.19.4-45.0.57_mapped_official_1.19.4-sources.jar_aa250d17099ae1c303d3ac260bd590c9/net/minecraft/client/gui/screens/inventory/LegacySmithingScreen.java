package net.minecraft.client.gui.screens.inventory;

import com.mojang.blaze3d.vertex.PoseStack;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.inventory.LegacySmithingMenu;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

/** @deprecated */
@Deprecated(
   forRemoval = true
)
@OnlyIn(Dist.CLIENT)
public class LegacySmithingScreen extends ItemCombinerScreen<LegacySmithingMenu> {
   private static final ResourceLocation SMITHING_LOCATION = new ResourceLocation("textures/gui/container/legacy_smithing.png");

   public LegacySmithingScreen(LegacySmithingMenu p_266823_, Inventory p_266925_, Component p_266749_) {
      super(p_266823_, p_266925_, p_266749_, SMITHING_LOCATION);
      this.titleLabelX = 60;
      this.titleLabelY = 18;
   }

   protected void renderErrorIcon(PoseStack p_267095_, int p_267270_, int p_266714_) {
      if ((this.menu.getSlot(0).hasItem() || this.menu.getSlot(1).hasItem()) && !this.menu.getSlot(this.menu.getResultSlot()).hasItem()) {
         blit(p_267095_, p_267270_ + 99, p_266714_ + 45, this.imageWidth, 0, 28, 21);
      }

   }
}