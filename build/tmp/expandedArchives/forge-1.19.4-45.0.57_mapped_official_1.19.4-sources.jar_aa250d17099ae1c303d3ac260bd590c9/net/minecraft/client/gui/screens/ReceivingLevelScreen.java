package net.minecraft.client.gui.screens;

import com.mojang.blaze3d.vertex.PoseStack;
import net.minecraft.client.GameNarrator;
import net.minecraft.core.BlockPos;
import net.minecraft.network.chat.Component;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

@OnlyIn(Dist.CLIENT)
public class ReceivingLevelScreen extends Screen {
   private static final Component DOWNLOADING_TERRAIN_TEXT = Component.translatable("multiplayer.downloadingTerrain");
   private static final long CHUNK_LOADING_START_WAIT_LIMIT_MS = 30000L;
   private boolean loadingPacketsReceived = false;
   private boolean oneTickSkipped = false;
   private final long createdAt = System.currentTimeMillis();

   public ReceivingLevelScreen() {
      super(GameNarrator.NO_TITLE);
   }

   public boolean shouldCloseOnEsc() {
      return false;
   }

   protected boolean shouldNarrateNavigation() {
      return false;
   }

   public void render(PoseStack p_96530_, int p_96531_, int p_96532_, float p_96533_) {
      this.renderDirtBackground(p_96530_);
      drawCenteredString(p_96530_, this.font, DOWNLOADING_TERRAIN_TEXT, this.width / 2, this.height / 2 - 50, 16777215);
      super.render(p_96530_, p_96531_, p_96532_, p_96533_);
   }

   public void tick() {
      if (System.currentTimeMillis() > this.createdAt + 30000L) {
         this.onClose();
      } else {
         if (this.oneTickSkipped) {
            if (this.minecraft.player == null) {
               return;
            }

            BlockPos blockpos = this.minecraft.player.blockPosition();
            boolean flag = this.minecraft.level != null && this.minecraft.level.isOutsideBuildHeight(blockpos.getY());
            if (flag || this.minecraft.levelRenderer.isChunkCompiled(blockpos) || this.minecraft.player.isSpectator() || !this.minecraft.player.isAlive()) {
               this.onClose();
            }
         } else {
            this.oneTickSkipped = this.loadingPacketsReceived;
         }

      }
   }

   public void onClose() {
      this.minecraft.getNarrator().sayNow(Component.translatable("narrator.ready_to_play"));
      super.onClose();
   }

   public void loadingPacketsReceived() {
      this.loadingPacketsReceived = true;
   }

   public boolean isPauseScreen() {
      return false;
   }
}