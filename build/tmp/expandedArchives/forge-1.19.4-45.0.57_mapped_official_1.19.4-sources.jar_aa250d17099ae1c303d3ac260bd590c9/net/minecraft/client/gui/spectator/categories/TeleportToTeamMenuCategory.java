package net.minecraft.client.gui.spectator.categories;

import com.mojang.authlib.GameProfile;
import com.mojang.blaze3d.systems.RenderSystem;
import com.mojang.blaze3d.vertex.PoseStack;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiComponent;
import net.minecraft.client.gui.components.PlayerFaceRenderer;
import net.minecraft.client.gui.components.spectator.SpectatorGui;
import net.minecraft.client.gui.spectator.SpectatorMenu;
import net.minecraft.client.gui.spectator.SpectatorMenuCategory;
import net.minecraft.client.gui.spectator.SpectatorMenuItem;
import net.minecraft.client.multiplayer.PlayerInfo;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.Mth;
import net.minecraft.util.RandomSource;
import net.minecraft.world.level.GameType;
import net.minecraft.world.scores.PlayerTeam;
import net.minecraft.world.scores.Scoreboard;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

@OnlyIn(Dist.CLIENT)
public class TeleportToTeamMenuCategory implements SpectatorMenuCategory, SpectatorMenuItem {
   private static final Component TELEPORT_TEXT = Component.translatable("spectatorMenu.team_teleport");
   private static final Component TELEPORT_PROMPT = Component.translatable("spectatorMenu.team_teleport.prompt");
   private final List<SpectatorMenuItem> items;

   public TeleportToTeamMenuCategory() {
      Minecraft minecraft = Minecraft.getInstance();
      this.items = createTeamEntries(minecraft, minecraft.level.getScoreboard());
   }

   private static List<SpectatorMenuItem> createTeamEntries(Minecraft p_260258_, Scoreboard p_259249_) {
      return p_259249_.getPlayerTeams().stream().flatMap((p_260025_) -> {
         return TeleportToTeamMenuCategory.TeamSelectionItem.create(p_260258_, p_260025_).stream();
      }).toList();
   }

   public List<SpectatorMenuItem> getItems() {
      return this.items;
   }

   public Component getPrompt() {
      return TELEPORT_PROMPT;
   }

   public void selectItem(SpectatorMenu p_101886_) {
      p_101886_.selectCategory(this);
   }

   public Component getName() {
      return TELEPORT_TEXT;
   }

   public void renderIcon(PoseStack p_101882_, float p_101883_, int p_101884_) {
      RenderSystem.setShaderTexture(0, SpectatorGui.SPECTATOR_LOCATION);
      GuiComponent.blit(p_101882_, 0, 0, 16.0F, 0.0F, 16, 16, 256, 256);
   }

   public boolean isEnabled() {
      return !this.items.isEmpty();
   }

   @OnlyIn(Dist.CLIENT)
   static class TeamSelectionItem implements SpectatorMenuItem {
      private final PlayerTeam team;
      private final ResourceLocation iconSkin;
      private final List<PlayerInfo> players;

      private TeamSelectionItem(PlayerTeam p_259176_, List<PlayerInfo> p_259231_, ResourceLocation p_260113_) {
         this.team = p_259176_;
         this.players = p_259231_;
         this.iconSkin = p_260113_;
      }

      public static Optional<SpectatorMenuItem> create(Minecraft p_260048_, PlayerTeam p_259058_) {
         List<PlayerInfo> list = new ArrayList<>();

         for(String s : p_259058_.getPlayers()) {
            PlayerInfo playerinfo = p_260048_.getConnection().getPlayerInfo(s);
            if (playerinfo != null && playerinfo.getGameMode() != GameType.SPECTATOR) {
               list.add(playerinfo);
            }
         }

         if (list.isEmpty()) {
            return Optional.empty();
         } else {
            GameProfile gameprofile = list.get(RandomSource.create().nextInt(list.size())).getProfile();
            ResourceLocation resourcelocation = p_260048_.getSkinManager().getInsecureSkinLocation(gameprofile);
            return Optional.of(new TeleportToTeamMenuCategory.TeamSelectionItem(p_259058_, list, resourcelocation));
         }
      }

      public void selectItem(SpectatorMenu p_101902_) {
         p_101902_.selectCategory(new TeleportToPlayerMenuCategory(this.players));
      }

      public Component getName() {
         return this.team.getDisplayName();
      }

      public void renderIcon(PoseStack p_101898_, float p_101899_, int p_101900_) {
         Integer integer = this.team.getColor().getColor();
         if (integer != null) {
            float f = (float)(integer >> 16 & 255) / 255.0F;
            float f1 = (float)(integer >> 8 & 255) / 255.0F;
            float f2 = (float)(integer & 255) / 255.0F;
            GuiComponent.fill(p_101898_, 1, 1, 15, 15, Mth.color(f * p_101899_, f1 * p_101899_, f2 * p_101899_) | p_101900_ << 24);
         }

         RenderSystem.setShaderTexture(0, this.iconSkin);
         RenderSystem.setShaderColor(p_101899_, p_101899_, p_101899_, (float)p_101900_ / 255.0F);
         PlayerFaceRenderer.draw(p_101898_, 2, 2, 12);
         RenderSystem.setShaderColor(1.0F, 1.0F, 1.0F, 1.0F);
      }

      public boolean isEnabled() {
         return true;
      }
   }
}