package com.mojang.realmsclient.gui.screens;

import com.mojang.blaze3d.systems.RenderSystem;
import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.logging.LogUtils;
import com.mojang.realmsclient.RealmsMainScreen;
import com.mojang.realmsclient.client.RealmsClient;
import com.mojang.realmsclient.dto.PendingInvite;
import com.mojang.realmsclient.exception.RealmsServiceException;
import com.mojang.realmsclient.gui.RowButton;
import com.mojang.realmsclient.util.RealmsUtil;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import javax.annotation.Nullable;
import net.minecraft.client.gui.GuiComponent;
import net.minecraft.client.gui.components.Button;
import net.minecraft.client.gui.components.ObjectSelectionList;
import net.minecraft.client.gui.screens.Screen;
import net.minecraft.network.chat.CommonComponents;
import net.minecraft.network.chat.Component;
import net.minecraft.realms.RealmsObjectSelectionList;
import net.minecraft.realms.RealmsScreen;
import net.minecraft.resources.ResourceLocation;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import org.slf4j.Logger;

@OnlyIn(Dist.CLIENT)
public class RealmsPendingInvitesScreen extends RealmsScreen {
   static final Logger LOGGER = LogUtils.getLogger();
   static final ResourceLocation ACCEPT_ICON_LOCATION = new ResourceLocation("realms", "textures/gui/realms/accept_icon.png");
   static final ResourceLocation REJECT_ICON_LOCATION = new ResourceLocation("realms", "textures/gui/realms/reject_icon.png");
   private static final Component NO_PENDING_INVITES_TEXT = Component.translatable("mco.invites.nopending");
   static final Component ACCEPT_INVITE_TOOLTIP = Component.translatable("mco.invites.button.accept");
   static final Component REJECT_INVITE_TOOLTIP = Component.translatable("mco.invites.button.reject");
   private final Screen lastScreen;
   @Nullable
   Component toolTip;
   boolean loaded;
   RealmsPendingInvitesScreen.PendingInvitationSelectionList pendingInvitationSelectionList;
   int selectedInvite = -1;
   private Button acceptButton;
   private Button rejectButton;

   public RealmsPendingInvitesScreen(Screen p_88890_) {
      super(Component.translatable("mco.invites.title"));
      this.lastScreen = p_88890_;
   }

   public void init() {
      this.pendingInvitationSelectionList = new RealmsPendingInvitesScreen.PendingInvitationSelectionList();
      (new Thread("Realms-pending-invitations-fetcher") {
         public void run() {
            RealmsClient realmsclient = RealmsClient.create();

            try {
               List<PendingInvite> list = realmsclient.pendingInvites().pendingInvites;
               List<RealmsPendingInvitesScreen.Entry> list1 = list.stream().map((p_88969_) -> {
                  return RealmsPendingInvitesScreen.this.new Entry(p_88969_);
               }).collect(Collectors.toList());
               RealmsPendingInvitesScreen.this.minecraft.execute(() -> {
                  RealmsPendingInvitesScreen.this.pendingInvitationSelectionList.replaceEntries(list1);
               });
            } catch (RealmsServiceException realmsserviceexception) {
               RealmsPendingInvitesScreen.LOGGER.error("Couldn't list invites");
            } finally {
               RealmsPendingInvitesScreen.this.loaded = true;
            }

         }
      }).start();
      this.addWidget(this.pendingInvitationSelectionList);
      this.acceptButton = this.addRenderableWidget(Button.builder(Component.translatable("mco.invites.button.accept"), (p_88940_) -> {
         this.accept(this.selectedInvite);
         this.selectedInvite = -1;
         this.updateButtonStates();
      }).bounds(this.width / 2 - 174, this.height - 32, 100, 20).build());
      this.addRenderableWidget(Button.builder(CommonComponents.GUI_DONE, (p_88930_) -> {
         this.minecraft.setScreen(new RealmsMainScreen(this.lastScreen));
      }).bounds(this.width / 2 - 50, this.height - 32, 100, 20).build());
      this.rejectButton = this.addRenderableWidget(Button.builder(Component.translatable("mco.invites.button.reject"), (p_88920_) -> {
         this.reject(this.selectedInvite);
         this.selectedInvite = -1;
         this.updateButtonStates();
      }).bounds(this.width / 2 + 74, this.height - 32, 100, 20).build());
      this.updateButtonStates();
   }

   public boolean keyPressed(int p_88895_, int p_88896_, int p_88897_) {
      if (p_88895_ == 256) {
         this.minecraft.setScreen(new RealmsMainScreen(this.lastScreen));
         return true;
      } else {
         return super.keyPressed(p_88895_, p_88896_, p_88897_);
      }
   }

   void updateList(int p_88893_) {
      this.pendingInvitationSelectionList.removeAtIndex(p_88893_);
   }

   void reject(final int p_88923_) {
      if (p_88923_ < this.pendingInvitationSelectionList.getItemCount()) {
         (new Thread("Realms-reject-invitation") {
            public void run() {
               try {
                  RealmsClient realmsclient = RealmsClient.create();
                  realmsclient.rejectInvitation((RealmsPendingInvitesScreen.this.pendingInvitationSelectionList.children().get(p_88923_)).pendingInvite.invitationId);
                  RealmsPendingInvitesScreen.this.minecraft.execute(() -> {
                     RealmsPendingInvitesScreen.this.updateList(p_88923_);
                  });
               } catch (RealmsServiceException realmsserviceexception) {
                  RealmsPendingInvitesScreen.LOGGER.error("Couldn't reject invite");
               }

            }
         }).start();
      }

   }

   void accept(final int p_88933_) {
      if (p_88933_ < this.pendingInvitationSelectionList.getItemCount()) {
         (new Thread("Realms-accept-invitation") {
            public void run() {
               try {
                  RealmsClient realmsclient = RealmsClient.create();
                  realmsclient.acceptInvitation((RealmsPendingInvitesScreen.this.pendingInvitationSelectionList.children().get(p_88933_)).pendingInvite.invitationId);
                  RealmsPendingInvitesScreen.this.minecraft.execute(() -> {
                     RealmsPendingInvitesScreen.this.updateList(p_88933_);
                  });
               } catch (RealmsServiceException realmsserviceexception) {
                  RealmsPendingInvitesScreen.LOGGER.error("Couldn't accept invite");
               }

            }
         }).start();
      }

   }

   public void render(PoseStack p_88899_, int p_88900_, int p_88901_, float p_88902_) {
      this.toolTip = null;
      this.renderBackground(p_88899_);
      this.pendingInvitationSelectionList.render(p_88899_, p_88900_, p_88901_, p_88902_);
      drawCenteredString(p_88899_, this.font, this.title, this.width / 2, 12, 16777215);
      if (this.toolTip != null) {
         this.renderMousehoverTooltip(p_88899_, this.toolTip, p_88900_, p_88901_);
      }

      if (this.pendingInvitationSelectionList.getItemCount() == 0 && this.loaded) {
         drawCenteredString(p_88899_, this.font, NO_PENDING_INVITES_TEXT, this.width / 2, this.height / 2 - 20, 16777215);
      }

      super.render(p_88899_, p_88900_, p_88901_, p_88902_);
   }

   protected void renderMousehoverTooltip(PoseStack p_88904_, @Nullable Component p_88905_, int p_88906_, int p_88907_) {
      if (p_88905_ != null) {
         int i = p_88906_ + 12;
         int j = p_88907_ - 12;
         int k = this.font.width(p_88905_);
         fillGradient(p_88904_, i - 3, j - 3, i + k + 3, j + 8 + 3, -1073741824, -1073741824);
         this.font.drawShadow(p_88904_, p_88905_, (float)i, (float)j, 16777215);
      }
   }

   void updateButtonStates() {
      this.acceptButton.visible = this.shouldAcceptAndRejectButtonBeVisible(this.selectedInvite);
      this.rejectButton.visible = this.shouldAcceptAndRejectButtonBeVisible(this.selectedInvite);
   }

   private boolean shouldAcceptAndRejectButtonBeVisible(int p_88963_) {
      return p_88963_ != -1;
   }

   @OnlyIn(Dist.CLIENT)
   class Entry extends ObjectSelectionList.Entry<RealmsPendingInvitesScreen.Entry> {
      private static final int TEXT_LEFT = 38;
      final PendingInvite pendingInvite;
      private final List<RowButton> rowButtons;

      Entry(PendingInvite p_88996_) {
         this.pendingInvite = p_88996_;
         this.rowButtons = Arrays.asList(new RealmsPendingInvitesScreen.Entry.AcceptRowButton(), new RealmsPendingInvitesScreen.Entry.RejectRowButton());
      }

      public void render(PoseStack p_89006_, int p_89007_, int p_89008_, int p_89009_, int p_89010_, int p_89011_, int p_89012_, int p_89013_, boolean p_89014_, float p_89015_) {
         this.renderPendingInvitationItem(p_89006_, this.pendingInvite, p_89009_, p_89008_, p_89012_, p_89013_);
      }

      public boolean mouseClicked(double p_88998_, double p_88999_, int p_89000_) {
         RowButton.rowButtonMouseClicked(RealmsPendingInvitesScreen.this.pendingInvitationSelectionList, this, this.rowButtons, p_89000_, p_88998_, p_88999_);
         return true;
      }

      private void renderPendingInvitationItem(PoseStack p_89017_, PendingInvite p_89018_, int p_89019_, int p_89020_, int p_89021_, int p_89022_) {
         RealmsPendingInvitesScreen.this.font.draw(p_89017_, p_89018_.worldName, (float)(p_89019_ + 38), (float)(p_89020_ + 1), 16777215);
         RealmsPendingInvitesScreen.this.font.draw(p_89017_, p_89018_.worldOwnerName, (float)(p_89019_ + 38), (float)(p_89020_ + 12), 7105644);
         RealmsPendingInvitesScreen.this.font.draw(p_89017_, RealmsUtil.convertToAgePresentationFromInstant(p_89018_.date), (float)(p_89019_ + 38), (float)(p_89020_ + 24), 7105644);
         RowButton.drawButtonsInRow(p_89017_, this.rowButtons, RealmsPendingInvitesScreen.this.pendingInvitationSelectionList, p_89019_, p_89020_, p_89021_, p_89022_);
         RealmsUtil.renderPlayerFace(p_89017_, p_89019_, p_89020_, 32, p_89018_.worldOwnerUuid);
      }

      public Component getNarration() {
         Component component = CommonComponents.joinLines(Component.literal(this.pendingInvite.worldName), Component.literal(this.pendingInvite.worldOwnerName), Component.literal(RealmsUtil.convertToAgePresentationFromInstant(this.pendingInvite.date)));
         return Component.translatable("narrator.select", component);
      }

      @OnlyIn(Dist.CLIENT)
      class AcceptRowButton extends RowButton {
         AcceptRowButton() {
            super(15, 15, 215, 5);
         }

         protected void draw(PoseStack p_89031_, int p_89032_, int p_89033_, boolean p_89034_) {
            RenderSystem.setShaderTexture(0, RealmsPendingInvitesScreen.ACCEPT_ICON_LOCATION);
            float f = p_89034_ ? 19.0F : 0.0F;
            GuiComponent.blit(p_89031_, p_89032_, p_89033_, f, 0.0F, 18, 18, 37, 18);
            if (p_89034_) {
               RealmsPendingInvitesScreen.this.toolTip = RealmsPendingInvitesScreen.ACCEPT_INVITE_TOOLTIP;
            }

         }

         public void onClick(int p_89029_) {
            RealmsPendingInvitesScreen.this.accept(p_89029_);
         }
      }

      @OnlyIn(Dist.CLIENT)
      class RejectRowButton extends RowButton {
         RejectRowButton() {
            super(15, 15, 235, 5);
         }

         protected void draw(PoseStack p_89041_, int p_89042_, int p_89043_, boolean p_89044_) {
            RenderSystem.setShaderTexture(0, RealmsPendingInvitesScreen.REJECT_ICON_LOCATION);
            float f = p_89044_ ? 19.0F : 0.0F;
            GuiComponent.blit(p_89041_, p_89042_, p_89043_, f, 0.0F, 18, 18, 37, 18);
            if (p_89044_) {
               RealmsPendingInvitesScreen.this.toolTip = RealmsPendingInvitesScreen.REJECT_INVITE_TOOLTIP;
            }

         }

         public void onClick(int p_89039_) {
            RealmsPendingInvitesScreen.this.reject(p_89039_);
         }
      }
   }

   @OnlyIn(Dist.CLIENT)
   class PendingInvitationSelectionList extends RealmsObjectSelectionList<RealmsPendingInvitesScreen.Entry> {
      public PendingInvitationSelectionList() {
         super(RealmsPendingInvitesScreen.this.width, RealmsPendingInvitesScreen.this.height, 32, RealmsPendingInvitesScreen.this.height - 40, 36);
      }

      public void removeAtIndex(int p_89058_) {
         this.remove(p_89058_);
      }

      public int getMaxPosition() {
         return this.getItemCount() * 36;
      }

      public int getRowWidth() {
         return 260;
      }

      public void renderBackground(PoseStack p_89051_) {
         RealmsPendingInvitesScreen.this.renderBackground(p_89051_);
      }

      public void selectItem(int p_89049_) {
         super.selectItem(p_89049_);
         this.selectInviteListItem(p_89049_);
      }

      public void selectInviteListItem(int p_89061_) {
         RealmsPendingInvitesScreen.this.selectedInvite = p_89061_;
         RealmsPendingInvitesScreen.this.updateButtonStates();
      }

      public void setSelected(@Nullable RealmsPendingInvitesScreen.Entry p_89053_) {
         super.setSelected(p_89053_);
         RealmsPendingInvitesScreen.this.selectedInvite = this.children().indexOf(p_89053_);
         RealmsPendingInvitesScreen.this.updateButtonStates();
      }
   }
}