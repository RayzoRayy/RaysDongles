package com.mojang.realmsclient;

import com.google.common.collect.ImmutableList;
import com.google.common.collect.Lists;
import com.google.common.util.concurrent.RateLimiter;
import com.mojang.blaze3d.systems.RenderSystem;
import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.logging.LogUtils;
import com.mojang.math.Axis;
import com.mojang.realmsclient.client.Ping;
import com.mojang.realmsclient.client.RealmsClient;
import com.mojang.realmsclient.dto.PingResult;
import com.mojang.realmsclient.dto.RealmsNotification;
import com.mojang.realmsclient.dto.RealmsServer;
import com.mojang.realmsclient.dto.RealmsServerPlayerList;
import com.mojang.realmsclient.dto.RegionPingResult;
import com.mojang.realmsclient.exception.RealmsServiceException;
import com.mojang.realmsclient.gui.RealmsDataFetcher;
import com.mojang.realmsclient.gui.RealmsNewsManager;
import com.mojang.realmsclient.gui.RealmsServerList;
import com.mojang.realmsclient.gui.screens.RealmsClientOutdatedScreen;
import com.mojang.realmsclient.gui.screens.RealmsConfigureWorldScreen;
import com.mojang.realmsclient.gui.screens.RealmsCreateRealmScreen;
import com.mojang.realmsclient.gui.screens.RealmsGenericErrorScreen;
import com.mojang.realmsclient.gui.screens.RealmsLongConfirmationScreen;
import com.mojang.realmsclient.gui.screens.RealmsLongRunningMcoTaskScreen;
import com.mojang.realmsclient.gui.screens.RealmsParentalConsentScreen;
import com.mojang.realmsclient.gui.screens.RealmsPendingInvitesScreen;
import com.mojang.realmsclient.gui.task.DataFetcher;
import com.mojang.realmsclient.util.RealmsPersistence;
import com.mojang.realmsclient.util.RealmsUtil;
import com.mojang.realmsclient.util.task.GetServerDetailsTask;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.UUID;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.ReentrantLock;
import java.util.function.Consumer;
import javax.annotation.Nullable;
import net.minecraft.ChatFormatting;
import net.minecraft.Util;
import net.minecraft.client.GameNarrator;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiComponent;
import net.minecraft.client.gui.components.AbstractWidget;
import net.minecraft.client.gui.components.Button;
import net.minecraft.client.gui.components.ImageWidget;
import net.minecraft.client.gui.components.MultiLineLabel;
import net.minecraft.client.gui.components.MultiLineTextWidget;
import net.minecraft.client.gui.components.ObjectSelectionList;
import net.minecraft.client.gui.components.Tooltip;
import net.minecraft.client.gui.layouts.FrameLayout;
import net.minecraft.client.gui.layouts.GridLayout;
import net.minecraft.client.gui.layouts.LinearLayout;
import net.minecraft.client.gui.layouts.SpacerElement;
import net.minecraft.client.gui.screens.ConfirmLinkScreen;
import net.minecraft.client.gui.screens.Screen;
import net.minecraft.network.chat.CommonComponents;
import net.minecraft.network.chat.Component;
import net.minecraft.realms.RealmsObjectSelectionList;
import net.minecraft.realms.RealmsScreen;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.packs.resources.ResourceManager;
import net.minecraft.util.CommonLinks;
import net.minecraft.util.Mth;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import org.slf4j.Logger;

@OnlyIn(Dist.CLIENT)
public class RealmsMainScreen extends RealmsScreen {
   static final Logger LOGGER = LogUtils.getLogger();
   private static final ResourceLocation ON_ICON_LOCATION = new ResourceLocation("realms", "textures/gui/realms/on_icon.png");
   private static final ResourceLocation OFF_ICON_LOCATION = new ResourceLocation("realms", "textures/gui/realms/off_icon.png");
   private static final ResourceLocation EXPIRED_ICON_LOCATION = new ResourceLocation("realms", "textures/gui/realms/expired_icon.png");
   private static final ResourceLocation EXPIRES_SOON_ICON_LOCATION = new ResourceLocation("realms", "textures/gui/realms/expires_soon_icon.png");
   private static final ResourceLocation INVITATION_ICONS_LOCATION = new ResourceLocation("realms", "textures/gui/realms/invitation_icons.png");
   private static final ResourceLocation INVITE_ICON_LOCATION = new ResourceLocation("realms", "textures/gui/realms/invite_icon.png");
   static final ResourceLocation WORLDICON_LOCATION = new ResourceLocation("realms", "textures/gui/realms/world_icon.png");
   private static final ResourceLocation LOGO_LOCATION = new ResourceLocation("realms", "textures/gui/title/realms.png");
   private static final ResourceLocation NEWS_LOCATION = new ResourceLocation("realms", "textures/gui/realms/news_icon.png");
   private static final ResourceLocation POPUP_LOCATION = new ResourceLocation("realms", "textures/gui/realms/popup.png");
   private static final ResourceLocation DARKEN_LOCATION = new ResourceLocation("realms", "textures/gui/realms/darken.png");
   static final ResourceLocation CROSS_ICON_LOCATION = new ResourceLocation("realms", "textures/gui/realms/cross_icon.png");
   private static final ResourceLocation TRIAL_ICON_LOCATION = new ResourceLocation("realms", "textures/gui/realms/trial_icon.png");
   static final ResourceLocation INFO_ICON_LOCATION = new ResourceLocation("minecraft", "textures/gui/info_icon.png");
   static final Component NO_PENDING_INVITES_TEXT = Component.translatable("mco.invites.nopending");
   static final Component PENDING_INVITES_TEXT = Component.translatable("mco.invites.pending");
   static final List<Component> TRIAL_MESSAGE_LINES = ImmutableList.of(Component.translatable("mco.trial.message.line1"), Component.translatable("mco.trial.message.line2"));
   static final Component SERVER_UNITIALIZED_TEXT = Component.translatable("mco.selectServer.uninitialized");
   static final Component SUBSCRIPTION_EXPIRED_TEXT = Component.translatable("mco.selectServer.expiredList");
   private static final Component SUBSCRIPTION_RENEW_TEXT = Component.translatable("mco.selectServer.expiredRenew");
   static final Component TRIAL_EXPIRED_TEXT = Component.translatable("mco.selectServer.expiredTrial");
   static final Component SELECT_MINIGAME_PREFIX = Component.translatable("mco.selectServer.minigame").append(CommonComponents.SPACE);
   private static final Component POPUP_TEXT = Component.translatable("mco.selectServer.popup");
   private static final Component PLAY_TEXT = Component.translatable("mco.selectServer.play");
   private static final Component LEAVE_SERVER_TEXT = Component.translatable("mco.selectServer.leave");
   private static final Component CONFIGURE_SERVER_TEXT = Component.translatable("mco.selectServer.configure");
   private static final Component SERVER_EXPIRED_TOOLTIP = Component.translatable("mco.selectServer.expired");
   private static final Component SERVER_EXPIRES_SOON_TOOLTIP = Component.translatable("mco.selectServer.expires.soon");
   private static final Component SERVER_EXPIRES_IN_DAY_TOOLTIP = Component.translatable("mco.selectServer.expires.day");
   private static final Component SERVER_OPEN_TOOLTIP = Component.translatable("mco.selectServer.open");
   private static final Component SERVER_CLOSED_TOOLTIP = Component.translatable("mco.selectServer.closed");
   private static final Component NEWS_TOOLTIP = Component.translatable("mco.news");
   static final Component UNITIALIZED_WORLD_NARRATION = Component.translatable("gui.narrate.button", SERVER_UNITIALIZED_TEXT);
   static final Component TRIAL_TEXT = CommonComponents.joinLines(TRIAL_MESSAGE_LINES);
   private static final int BUTTON_WIDTH = 100;
   private static final int BUTTON_TOP_ROW_WIDTH = 308;
   private static final int BUTTON_BOTTOM_ROW_WIDTH = 204;
   private static final int FOOTER_HEIGHT = 64;
   private static List<ResourceLocation> teaserImages = ImmutableList.of();
   @Nullable
   private DataFetcher.Subscription dataSubscription;
   private RealmsServerList serverList;
   private final Set<UUID> handledSeenNotifications = new HashSet<>();
   private static boolean overrideConfigure;
   private static int lastScrollYPosition = -1;
   static volatile boolean hasParentalConsent;
   static volatile boolean checkedParentalConsent;
   static volatile boolean checkedClientCompatability;
   @Nullable
   static Screen realmsGenericErrorScreen;
   private static boolean regionsPinged;
   private final RateLimiter inviteNarrationLimiter;
   private boolean dontSetConnectedToRealms;
   final Screen lastScreen;
   RealmsMainScreen.RealmSelectionList realmSelectionList;
   private boolean realmsSelectionListAdded;
   private Button playButton;
   private Button backButton;
   private Button renewButton;
   private Button configureButton;
   private Button leaveButton;
   private List<RealmsServer> realmsServers = ImmutableList.of();
   volatile int numberOfPendingInvites;
   int animTick;
   private boolean hasFetchedServers;
   boolean popupOpenedByUser;
   private boolean justClosedPopup;
   private volatile boolean trialsAvailable;
   private volatile boolean createdTrial;
   private volatile boolean showingPopup;
   volatile boolean hasUnreadNews;
   @Nullable
   volatile String newsLink;
   private int carouselIndex;
   private int carouselTick;
   private boolean hasSwitchedCarouselImage;
   private List<KeyCombo> keyCombos;
   long lastClickTime;
   private ReentrantLock connectLock = new ReentrantLock();
   private MultiLineLabel formattedPopup = MultiLineLabel.EMPTY;
   private final List<RealmsNotification> notifications = new ArrayList<>();
   private Button showPopupButton;
   private RealmsMainScreen.PendingInvitesButton pendingInvitesButton;
   private Button newsButton;
   private Button createTrialButton;
   private Button buyARealmButton;
   private Button closeButton;

   public RealmsMainScreen(Screen p_86315_) {
      super(GameNarrator.NO_TITLE);
      this.lastScreen = p_86315_;
      this.inviteNarrationLimiter = RateLimiter.create((double)0.016666668F);
   }

   private boolean shouldShowMessageInList() {
      if (hasParentalConsent() && this.hasFetchedServers) {
         if (this.trialsAvailable && !this.createdTrial) {
            return true;
         } else {
            for(RealmsServer realmsserver : this.realmsServers) {
               if (realmsserver.ownerUUID.equals(this.minecraft.getUser().getUuid())) {
                  return false;
               }
            }

            return true;
         }
      } else {
         return false;
      }
   }

   public boolean shouldShowPopup() {
      if (hasParentalConsent() && this.hasFetchedServers) {
         return this.popupOpenedByUser ? true : this.realmsServers.isEmpty();
      } else {
         return false;
      }
   }

   public void init() {
      this.keyCombos = Lists.newArrayList(new KeyCombo(new char[]{'3', '2', '1', '4', '5', '6'}, () -> {
         overrideConfigure = !overrideConfigure;
      }), new KeyCombo(new char[]{'9', '8', '7', '1', '2', '3'}, () -> {
         if (RealmsClient.currentEnvironment == RealmsClient.Environment.STAGE) {
            this.switchToProd();
         } else {
            this.switchToStage();
         }

      }), new KeyCombo(new char[]{'9', '8', '7', '4', '5', '6'}, () -> {
         if (RealmsClient.currentEnvironment == RealmsClient.Environment.LOCAL) {
            this.switchToProd();
         } else {
            this.switchToLocal();
         }

      }));
      if (realmsGenericErrorScreen != null) {
         this.minecraft.setScreen(realmsGenericErrorScreen);
      } else {
         this.connectLock = new ReentrantLock();
         if (checkedClientCompatability && !hasParentalConsent()) {
            this.checkParentalConsent();
         }

         this.checkClientCompatability();
         if (!this.dontSetConnectedToRealms) {
            this.minecraft.setConnectedToRealms(false);
         }

         this.showingPopup = false;
         this.realmSelectionList = new RealmsMainScreen.RealmSelectionList();
         if (lastScrollYPosition != -1) {
            this.realmSelectionList.setScrollAmount((double)lastScrollYPosition);
         }

         this.addWidget(this.realmSelectionList);
         this.realmsSelectionListAdded = true;
         this.setInitialFocus(this.realmSelectionList);
         this.addMiddleButtons();
         this.addFooterButtons();
         this.addTopButtons();
         this.updateButtonStates((RealmsServer)null);
         this.formattedPopup = MultiLineLabel.create(this.font, POPUP_TEXT, 100);
         RealmsNewsManager realmsnewsmanager = this.minecraft.realmsDataFetcher().newsManager;
         this.hasUnreadNews = realmsnewsmanager.hasUnreadNews();
         this.newsLink = realmsnewsmanager.newsLink();
         if (this.serverList == null) {
            this.serverList = new RealmsServerList(this.minecraft);
         }

         if (this.dataSubscription != null) {
            this.dataSubscription.forceUpdate();
         }

      }
   }

   private static boolean hasParentalConsent() {
      return checkedParentalConsent && hasParentalConsent;
   }

   public void addTopButtons() {
      this.pendingInvitesButton = this.addRenderableWidget(new RealmsMainScreen.PendingInvitesButton());
      this.newsButton = this.addRenderableWidget(new RealmsMainScreen.NewsButton());
      this.showPopupButton = this.addRenderableWidget(Button.builder(Component.translatable("mco.selectServer.purchase"), (p_86597_) -> {
         this.popupOpenedByUser = !this.popupOpenedByUser;
      }).bounds(this.width - 90, 6, 80, 20).build());
   }

   public void addMiddleButtons() {
      this.createTrialButton = this.addRenderableWidget(Button.builder(Component.translatable("mco.selectServer.trial"), (p_86565_) -> {
         if (this.trialsAvailable && !this.createdTrial) {
            Util.getPlatform().openUri("https://aka.ms/startjavarealmstrial");
            this.minecraft.setScreen(this.lastScreen);
         }
      }).bounds(this.width / 2 + 52, this.popupY0() + 137 - 20, 98, 20).build());
      this.buyARealmButton = this.addRenderableWidget(Button.builder(Component.translatable("mco.selectServer.buy"), (p_231255_) -> {
         Util.getPlatform().openUri("https://aka.ms/BuyJavaRealms");
      }).bounds(this.width / 2 + 52, this.popupY0() + 160 - 20, 98, 20).build());
      this.closeButton = this.addRenderableWidget(new RealmsMainScreen.CloseButton());
   }

   public void addFooterButtons() {
      this.playButton = Button.builder(PLAY_TEXT, (p_86659_) -> {
         this.play(this.getSelectedServer(), this);
      }).width(100).build();
      this.configureButton = Button.builder(CONFIGURE_SERVER_TEXT, (p_86672_) -> {
         this.configureClicked(this.getSelectedServer());
      }).width(100).build();
      this.renewButton = Button.builder(SUBSCRIPTION_RENEW_TEXT, (p_86622_) -> {
         this.onRenew(this.getSelectedServer());
      }).width(100).build();
      this.leaveButton = Button.builder(LEAVE_SERVER_TEXT, (p_86679_) -> {
         this.leaveClicked(this.getSelectedServer());
      }).width(100).build();
      this.backButton = Button.builder(CommonComponents.GUI_BACK, (p_86647_) -> {
         if (!this.justClosedPopup) {
            this.minecraft.setScreen(this.lastScreen);
         }

      }).width(100).build();
      GridLayout gridlayout = new GridLayout();
      GridLayout.RowHelper gridlayout$rowhelper = gridlayout.createRowHelper(1);
      LinearLayout linearlayout = gridlayout$rowhelper.addChild(new LinearLayout(308, 20, LinearLayout.Orientation.HORIZONTAL), gridlayout$rowhelper.newCellSettings().paddingBottom(4));
      linearlayout.addChild(this.playButton);
      linearlayout.addChild(this.configureButton);
      linearlayout.addChild(this.renewButton);
      LinearLayout linearlayout1 = gridlayout$rowhelper.addChild(new LinearLayout(204, 20, LinearLayout.Orientation.HORIZONTAL), gridlayout$rowhelper.newCellSettings().alignHorizontallyCenter());
      linearlayout1.addChild(this.leaveButton);
      linearlayout1.addChild(this.backButton);
      gridlayout.visitWidgets((p_272289_) -> {
         AbstractWidget abstractwidget = this.addRenderableWidget(p_272289_);
      });
      gridlayout.arrangeElements();
      FrameLayout.centerInRectangle(gridlayout, 0, this.height - 64, this.width, 64);
   }

   void updateButtonStates(@Nullable RealmsServer p_86514_) {
      this.backButton.active = true;
      if (hasParentalConsent() && this.hasFetchedServers) {
         boolean flag = this.shouldShowPopup() && this.trialsAvailable && !this.createdTrial;
         this.createTrialButton.visible = flag;
         this.createTrialButton.active = flag;
         this.buyARealmButton.visible = this.shouldShowPopup();
         this.closeButton.visible = this.shouldShowPopup() && this.popupOpenedByUser;
         this.newsButton.active = true;
         this.newsButton.visible = this.newsLink != null;
         this.pendingInvitesButton.active = true;
         this.pendingInvitesButton.visible = true;
         this.showPopupButton.active = !this.shouldShowPopup();
         this.playButton.visible = !this.shouldShowPopup();
         this.renewButton.visible = !this.shouldShowPopup();
         this.leaveButton.visible = !this.shouldShowPopup();
         this.configureButton.visible = !this.shouldShowPopup();
         this.backButton.visible = !this.shouldShowPopup();
         this.playButton.active = this.shouldPlayButtonBeActive(p_86514_);
         this.renewButton.active = this.shouldRenewButtonBeActive(p_86514_);
         this.leaveButton.active = this.shouldLeaveButtonBeActive(p_86514_);
         this.configureButton.active = this.shouldConfigureButtonBeActive(p_86514_);
      } else {
         hideWidgets(new AbstractWidget[]{this.playButton, this.renewButton, this.configureButton, this.createTrialButton, this.buyARealmButton, this.closeButton, this.newsButton, this.pendingInvitesButton, this.showPopupButton, this.leaveButton});
      }
   }

   private boolean shouldShowPopupButton() {
      return (!this.shouldShowPopup() || this.popupOpenedByUser) && hasParentalConsent() && this.hasFetchedServers;
   }

   boolean shouldPlayButtonBeActive(@Nullable RealmsServer p_86563_) {
      return p_86563_ != null && !p_86563_.expired && p_86563_.state == RealmsServer.State.OPEN;
   }

   private boolean shouldRenewButtonBeActive(@Nullable RealmsServer p_86595_) {
      return p_86595_ != null && p_86595_.expired && this.isSelfOwnedServer(p_86595_);
   }

   private boolean shouldConfigureButtonBeActive(@Nullable RealmsServer p_86620_) {
      return p_86620_ != null && this.isSelfOwnedServer(p_86620_);
   }

   private boolean shouldLeaveButtonBeActive(@Nullable RealmsServer p_86645_) {
      return p_86645_ != null && !this.isSelfOwnedServer(p_86645_);
   }

   public void tick() {
      super.tick();
      if (this.pendingInvitesButton != null) {
         this.pendingInvitesButton.tick();
      }

      this.justClosedPopup = false;
      ++this.animTick;
      boolean flag = hasParentalConsent();
      if (this.dataSubscription == null && flag) {
         this.dataSubscription = this.initDataFetcher(this.minecraft.realmsDataFetcher());
      } else if (this.dataSubscription != null && !flag) {
         this.dataSubscription = null;
      }

      if (this.dataSubscription != null) {
         this.dataSubscription.tick();
      }

      if (this.shouldShowPopup()) {
         ++this.carouselTick;
      }

      if (this.showPopupButton != null) {
         this.showPopupButton.visible = this.shouldShowPopupButton();
         this.showPopupButton.active = this.showPopupButton.visible;
      }

   }

   private DataFetcher.Subscription initDataFetcher(RealmsDataFetcher p_238836_) {
      DataFetcher.Subscription datafetcher$subscription = p_238836_.dataFetcher.createSubscription();
      datafetcher$subscription.subscribe(p_238836_.serverListUpdateTask, (p_275856_) -> {
         List<RealmsServer> list = this.serverList.updateServersList(p_275856_);
         boolean flag = false;

         for(RealmsServer realmsserver : list) {
            if (this.isSelfOwnedNonExpiredServer(realmsserver)) {
               flag = true;
            }
         }

         this.realmsServers = list;
         this.hasFetchedServers = true;
         this.refreshRealmsSelectionList();
         if (!regionsPinged && flag) {
            regionsPinged = true;
            this.pingRegions();
         }

      });
      callRealmsClient(RealmsClient::getNotifications, (p_274622_) -> {
         this.notifications.clear();
         this.notifications.addAll(p_274622_);
         this.refreshRealmsSelectionList();
      });
      datafetcher$subscription.subscribe(p_238836_.pendingInvitesTask, (p_240510_) -> {
         this.numberOfPendingInvites = p_240510_;
         if (this.numberOfPendingInvites > 0 && this.inviteNarrationLimiter.tryAcquire(1)) {
            this.minecraft.getNarrator().sayNow(Component.translatable("mco.configure.world.invite.narration", this.numberOfPendingInvites));
         }

      });
      datafetcher$subscription.subscribe(p_238836_.trialAvailabilityTask, (p_238839_) -> {
         if (!this.createdTrial) {
            if (p_238839_ != this.trialsAvailable && this.shouldShowPopup()) {
               this.trialsAvailable = p_238839_;
               this.showingPopup = false;
            } else {
               this.trialsAvailable = p_238839_;
            }

         }
      });
      datafetcher$subscription.subscribe(p_238836_.liveStatsTask, (p_238847_) -> {
         for(RealmsServerPlayerList realmsserverplayerlist : p_238847_.servers) {
            for(RealmsServer realmsserver : this.realmsServers) {
               if (realmsserver.id == realmsserverplayerlist.serverId) {
                  realmsserver.updateServerPing(realmsserverplayerlist);
                  break;
               }
            }
         }

      });
      datafetcher$subscription.subscribe(p_238836_.newsTask, (p_231355_) -> {
         p_238836_.newsManager.updateUnreadNews(p_231355_);
         this.hasUnreadNews = p_238836_.newsManager.hasUnreadNews();
         this.newsLink = p_238836_.newsManager.newsLink();
         this.updateButtonStates((RealmsServer)null);
      });
      return datafetcher$subscription;
   }

   private static <T> void callRealmsClient(RealmsMainScreen.RealmsCall<T> p_275561_, Consumer<T> p_275686_) {
      Minecraft minecraft = Minecraft.getInstance();
      CompletableFuture.supplyAsync(() -> {
         try {
            return p_275561_.request(RealmsClient.create(minecraft));
         } catch (RealmsServiceException realmsserviceexception) {
            throw new RuntimeException(realmsserviceexception);
         }
      }).thenAcceptAsync(p_275686_, minecraft).exceptionally((p_274626_) -> {
         LOGGER.error("Failed to execute call to Realms Service", p_274626_);
         return null;
      });
   }

   private void refreshRealmsSelectionList() {
      boolean flag = !this.hasFetchedServers;
      this.realmSelectionList.clear();
      List<UUID> list = new ArrayList<>();

      for(RealmsNotification realmsnotification : this.notifications) {
         this.addEntriesForNotification(this.realmSelectionList, realmsnotification);
         if (!realmsnotification.seen() && !this.handledSeenNotifications.contains(realmsnotification.uuid())) {
            list.add(realmsnotification.uuid());
         }
      }

      if (!list.isEmpty()) {
         callRealmsClient((p_274625_) -> {
            p_274625_.notificationsSeen(list);
            return null;
         }, (p_274630_) -> {
            this.handledSeenNotifications.addAll(list);
         });
      }

      if (this.shouldShowMessageInList()) {
         this.realmSelectionList.addEntry(new RealmsMainScreen.TrialEntry());
      }

      RealmsMainScreen.Entry realmsmainscreen$entry = null;
      RealmsServer realmsserver1 = this.getSelectedServer();

      for(RealmsServer realmsserver : this.realmsServers) {
         RealmsMainScreen.ServerEntry realmsmainscreen$serverentry = new RealmsMainScreen.ServerEntry(realmsserver);
         this.realmSelectionList.addEntry(realmsmainscreen$serverentry);
         if (realmsserver1 != null && realmsserver1.id == realmsserver.id) {
            realmsmainscreen$entry = realmsmainscreen$serverentry;
         }
      }

      if (flag) {
         this.updateButtonStates((RealmsServer)null);
      } else {
         this.realmSelectionList.setSelected(realmsmainscreen$entry);
      }

   }

   private void addEntriesForNotification(RealmsMainScreen.RealmSelectionList p_275392_, RealmsNotification p_275492_) {
      if (p_275492_ instanceof RealmsNotification.VisitUrl realmsnotification$visiturl) {
         p_275392_.addEntry(new RealmsMainScreen.NotificationMessageEntry(realmsnotification$visiturl.getMessage(), realmsnotification$visiturl));
         p_275392_.addEntry(new RealmsMainScreen.ButtonEntry(realmsnotification$visiturl.buildOpenLinkButton(this)));
      }

   }

   void refreshFetcher() {
      if (this.dataSubscription != null) {
         this.dataSubscription.reset();
      }

   }

   private void pingRegions() {
      (new Thread(() -> {
         List<RegionPingResult> list = Ping.pingAllRegions();
         RealmsClient realmsclient = RealmsClient.create();
         PingResult pingresult = new PingResult();
         pingresult.pingResults = list;
         pingresult.worldIds = this.getOwnedNonExpiredWorldIds();

         try {
            realmsclient.sendPingResults(pingresult);
         } catch (Throwable throwable) {
            LOGGER.warn("Could not send ping result to Realms: ", throwable);
         }

      })).start();
   }

   private List<Long> getOwnedNonExpiredWorldIds() {
      List<Long> list = Lists.newArrayList();

      for(RealmsServer realmsserver : this.realmsServers) {
         if (this.isSelfOwnedNonExpiredServer(realmsserver)) {
            list.add(realmsserver.id);
         }
      }

      return list;
   }

   public void setCreatedTrial(boolean p_167191_) {
      this.createdTrial = p_167191_;
   }

   private void onRenew(@Nullable RealmsServer p_193500_) {
      if (p_193500_ != null) {
         String s = CommonLinks.extendRealms(p_193500_.remoteSubscriptionId, this.minecraft.getUser().getUuid(), p_193500_.expiredTrial);
         this.minecraft.keyboardHandler.setClipboard(s);
         Util.getPlatform().openUri(s);
      }

   }

   private void checkClientCompatability() {
      if (!checkedClientCompatability) {
         checkedClientCompatability = true;
         (new Thread("MCO Compatability Checker #1") {
            public void run() {
               RealmsClient realmsclient = RealmsClient.create();

               try {
                  RealmsClient.CompatibleVersionResponse realmsclient$compatibleversionresponse = realmsclient.clientCompatible();
                  if (realmsclient$compatibleversionresponse != RealmsClient.CompatibleVersionResponse.COMPATIBLE) {
                     RealmsMainScreen.realmsGenericErrorScreen = new RealmsClientOutdatedScreen(RealmsMainScreen.this.lastScreen);
                     RealmsMainScreen.this.minecraft.execute(() -> {
                        RealmsMainScreen.this.minecraft.setScreen(RealmsMainScreen.realmsGenericErrorScreen);
                     });
                     return;
                  }

                  RealmsMainScreen.this.checkParentalConsent();
               } catch (RealmsServiceException realmsserviceexception) {
                  RealmsMainScreen.checkedClientCompatability = false;
                  RealmsMainScreen.LOGGER.error("Couldn't connect to realms", (Throwable)realmsserviceexception);
                  if (realmsserviceexception.httpResultCode == 401) {
                     RealmsMainScreen.realmsGenericErrorScreen = new RealmsGenericErrorScreen(Component.translatable("mco.error.invalid.session.title"), Component.translatable("mco.error.invalid.session.message"), RealmsMainScreen.this.lastScreen);
                     RealmsMainScreen.this.minecraft.execute(() -> {
                        RealmsMainScreen.this.minecraft.setScreen(RealmsMainScreen.realmsGenericErrorScreen);
                     });
                  } else {
                     RealmsMainScreen.this.minecraft.execute(() -> {
                        RealmsMainScreen.this.minecraft.setScreen(new RealmsGenericErrorScreen(realmsserviceexception, RealmsMainScreen.this.lastScreen));
                     });
                  }
               }

            }
         }).start();
      }

   }

   void checkParentalConsent() {
      (new Thread("MCO Compatability Checker #1") {
         public void run() {
            RealmsClient realmsclient = RealmsClient.create();

            try {
               Boolean obool = realmsclient.mcoEnabled();
               if (obool) {
                  RealmsMainScreen.LOGGER.info("Realms is available for this user");
                  RealmsMainScreen.hasParentalConsent = true;
               } else {
                  RealmsMainScreen.LOGGER.info("Realms is not available for this user");
                  RealmsMainScreen.hasParentalConsent = false;
                  RealmsMainScreen.this.minecraft.execute(() -> {
                     RealmsMainScreen.this.minecraft.setScreen(new RealmsParentalConsentScreen(RealmsMainScreen.this.lastScreen));
                  });
               }

               RealmsMainScreen.checkedParentalConsent = true;
            } catch (RealmsServiceException realmsserviceexception) {
               RealmsMainScreen.LOGGER.error("Couldn't connect to realms", (Throwable)realmsserviceexception);
               RealmsMainScreen.this.minecraft.execute(() -> {
                  RealmsMainScreen.this.minecraft.setScreen(new RealmsGenericErrorScreen(realmsserviceexception, RealmsMainScreen.this.lastScreen));
               });
            }

         }
      }).start();
   }

   private void switchToStage() {
      if (RealmsClient.currentEnvironment != RealmsClient.Environment.STAGE) {
         (new Thread("MCO Stage Availability Checker #1") {
            public void run() {
               RealmsClient realmsclient = RealmsClient.create();

               try {
                  Boolean obool = realmsclient.stageAvailable();
                  if (obool) {
                     RealmsClient.switchToStage();
                     RealmsMainScreen.LOGGER.info("Switched to stage");
                     RealmsMainScreen.this.refreshFetcher();
                  }
               } catch (RealmsServiceException realmsserviceexception) {
                  RealmsMainScreen.LOGGER.error("Couldn't connect to Realms: {}", (Object)realmsserviceexception.toString());
               }

            }
         }).start();
      }

   }

   private void switchToLocal() {
      if (RealmsClient.currentEnvironment != RealmsClient.Environment.LOCAL) {
         (new Thread("MCO Local Availability Checker #1") {
            public void run() {
               RealmsClient realmsclient = RealmsClient.create();

               try {
                  Boolean obool = realmsclient.stageAvailable();
                  if (obool) {
                     RealmsClient.switchToLocal();
                     RealmsMainScreen.LOGGER.info("Switched to local");
                     RealmsMainScreen.this.refreshFetcher();
                  }
               } catch (RealmsServiceException realmsserviceexception) {
                  RealmsMainScreen.LOGGER.error("Couldn't connect to Realms: {}", (Object)realmsserviceexception.toString());
               }

            }
         }).start();
      }

   }

   private void switchToProd() {
      RealmsClient.switchToProd();
      this.refreshFetcher();
   }

   private void configureClicked(@Nullable RealmsServer p_86657_) {
      if (p_86657_ != null && (this.minecraft.getUser().getUuid().equals(p_86657_.ownerUUID) || overrideConfigure)) {
         this.saveListScrollPosition();
         this.minecraft.setScreen(new RealmsConfigureWorldScreen(this, p_86657_.id));
      }

   }

   private void leaveClicked(@Nullable RealmsServer p_86670_) {
      if (p_86670_ != null && !this.minecraft.getUser().getUuid().equals(p_86670_.ownerUUID)) {
         this.saveListScrollPosition();
         Component component = Component.translatable("mco.configure.world.leave.question.line1");
         Component component1 = Component.translatable("mco.configure.world.leave.question.line2");
         this.minecraft.setScreen(new RealmsLongConfirmationScreen((p_231253_) -> {
            this.leaveServer(p_231253_, p_86670_);
         }, RealmsLongConfirmationScreen.Type.Info, component, component1, true));
      }

   }

   private void saveListScrollPosition() {
      lastScrollYPosition = (int)this.realmSelectionList.getScrollAmount();
   }

   @Nullable
   private RealmsServer getSelectedServer() {
      if (this.realmSelectionList == null) {
         return null;
      } else {
         RealmsMainScreen.Entry realmsmainscreen$entry = this.realmSelectionList.getSelected();
         return realmsmainscreen$entry != null ? realmsmainscreen$entry.getServer() : null;
      }
   }

   private void leaveServer(boolean p_193494_, final RealmsServer p_193495_) {
      if (p_193494_) {
         (new Thread("Realms-leave-server") {
            public void run() {
               try {
                  RealmsClient realmsclient = RealmsClient.create();
                  realmsclient.uninviteMyselfFrom(p_193495_.id);
                  RealmsMainScreen.this.minecraft.execute(() -> {
                     RealmsMainScreen.this.removeServer(p_193495_);
                  });
               } catch (RealmsServiceException realmsserviceexception) {
                  RealmsMainScreen.LOGGER.error("Couldn't configure world");
                  RealmsMainScreen.this.minecraft.execute(() -> {
                     RealmsMainScreen.this.minecraft.setScreen(new RealmsGenericErrorScreen(realmsserviceexception, RealmsMainScreen.this));
                  });
               }

            }
         }).start();
      }

      this.minecraft.setScreen(this);
   }

   void removeServer(RealmsServer p_86677_) {
      this.realmsServers = this.serverList.removeItem(p_86677_);
      this.realmSelectionList.children().removeIf((p_231250_) -> {
         RealmsServer realmsserver = p_231250_.getServer();
         return realmsserver != null && realmsserver.id == p_86677_.id;
      });
      this.realmSelectionList.setSelected((RealmsMainScreen.Entry)null);
      this.updateButtonStates((RealmsServer)null);
      this.playButton.active = false;
   }

   void dismissNotification(UUID p_275349_) {
      callRealmsClient((p_274628_) -> {
         p_274628_.notificationsDismiss(List.of(p_275349_));
         return null;
      }, (p_274632_) -> {
         this.notifications.removeIf((p_274621_) -> {
            return p_274621_.dismissable() && p_275349_.equals(p_274621_.uuid());
         });
         this.refreshRealmsSelectionList();
      });
   }

   public void resetScreen() {
      if (this.realmSelectionList != null) {
         this.realmSelectionList.setSelected((RealmsMainScreen.Entry)null);
      }

   }

   public boolean keyPressed(int p_86401_, int p_86402_, int p_86403_) {
      if (p_86401_ == 256) {
         this.keyCombos.forEach(KeyCombo::reset);
         this.onClosePopup();
         return true;
      } else {
         return super.keyPressed(p_86401_, p_86402_, p_86403_);
      }
   }

   void onClosePopup() {
      if (this.shouldShowPopup() && this.popupOpenedByUser) {
         this.popupOpenedByUser = false;
      } else {
         this.minecraft.setScreen(this.lastScreen);
      }

   }

   public boolean charTyped(char p_86388_, int p_86389_) {
      this.keyCombos.forEach((p_231245_) -> {
         p_231245_.keyPressed(p_86388_);
      });
      return true;
   }

   public void render(PoseStack p_86413_, int p_86414_, int p_86415_, float p_86416_) {
      this.renderBackground(p_86413_);
      this.realmSelectionList.render(p_86413_, p_86414_, p_86415_, p_86416_);
      this.drawRealmsLogo(p_86413_, this.width / 2 - 50, 7);
      if (RealmsClient.currentEnvironment == RealmsClient.Environment.STAGE) {
         this.renderStage(p_86413_);
      }

      if (RealmsClient.currentEnvironment == RealmsClient.Environment.LOCAL) {
         this.renderLocal(p_86413_);
      }

      if (this.shouldShowPopup()) {
         p_86413_.pushPose();
         p_86413_.translate(0.0F, 0.0F, 100.0F);
         this.drawPopup(p_86413_);
         p_86413_.popPose();
      } else {
         if (this.showingPopup) {
            this.updateButtonStates((RealmsServer)null);
            if (!this.realmsSelectionListAdded) {
               this.addWidget(this.realmSelectionList);
               this.realmsSelectionListAdded = true;
            }

            this.playButton.active = this.shouldPlayButtonBeActive(this.getSelectedServer());
         }

         this.showingPopup = false;
      }

      super.render(p_86413_, p_86414_, p_86415_, p_86416_);
      if (this.trialsAvailable && !this.createdTrial && this.shouldShowPopup()) {
         RenderSystem.setShaderTexture(0, TRIAL_ICON_LOCATION);
         int i = 8;
         int j = 8;
         int k = 0;
         if ((Util.getMillis() / 800L & 1L) == 1L) {
            k = 8;
         }

         GuiComponent.blit(p_86413_, this.createTrialButton.getX() + this.createTrialButton.getWidth() - 8 - 4, this.createTrialButton.getY() + this.createTrialButton.getHeight() / 2 - 4, 0.0F, (float)k, 8, 8, 8, 16);
      }

   }

   private void drawRealmsLogo(PoseStack p_86409_, int p_86410_, int p_86411_) {
      RenderSystem.setShaderTexture(0, LOGO_LOCATION);
      p_86409_.pushPose();
      p_86409_.scale(0.5F, 0.5F, 0.5F);
      GuiComponent.blit(p_86409_, p_86410_ * 2, p_86411_ * 2 - 5, 0.0F, 0.0F, 200, 50, 200, 50);
      p_86409_.popPose();
   }

   public boolean mouseClicked(double p_86397_, double p_86398_, int p_86399_) {
      if (this.isOutsidePopup(p_86397_, p_86398_) && this.popupOpenedByUser) {
         this.popupOpenedByUser = false;
         this.justClosedPopup = true;
         return true;
      } else {
         return super.mouseClicked(p_86397_, p_86398_, p_86399_);
      }
   }

   private boolean isOutsidePopup(double p_86394_, double p_86395_) {
      int i = this.popupX0();
      int j = this.popupY0();
      return p_86394_ < (double)(i - 5) || p_86394_ > (double)(i + 315) || p_86395_ < (double)(j - 5) || p_86395_ > (double)(j + 171);
   }

   private void drawPopup(PoseStack p_202330_) {
      int i = this.popupX0();
      int j = this.popupY0();
      if (!this.showingPopup) {
         this.carouselIndex = 0;
         this.carouselTick = 0;
         this.hasSwitchedCarouselImage = true;
         this.updateButtonStates((RealmsServer)null);
         if (this.realmsSelectionListAdded) {
            this.removeWidget(this.realmSelectionList);
            this.realmsSelectionListAdded = false;
         }

         this.minecraft.getNarrator().sayNow(POPUP_TEXT);
      }

      if (this.hasFetchedServers) {
         this.showingPopup = true;
      }

      RenderSystem.setShaderColor(1.0F, 1.0F, 1.0F, 0.7F);
      RenderSystem.enableBlend();
      RenderSystem.setShaderTexture(0, DARKEN_LOCATION);
      int k = 0;
      int l = 32;
      GuiComponent.blit(p_202330_, 0, 32, 0.0F, 0.0F, this.width, this.height - 40 - 32, 310, 166);
      RenderSystem.disableBlend();
      RenderSystem.setShaderColor(1.0F, 1.0F, 1.0F, 1.0F);
      RenderSystem.setShaderTexture(0, POPUP_LOCATION);
      GuiComponent.blit(p_202330_, i, j, 0.0F, 0.0F, 310, 166, 310, 166);
      if (!teaserImages.isEmpty()) {
         RenderSystem.setShaderTexture(0, teaserImages.get(this.carouselIndex));
         GuiComponent.blit(p_202330_, i + 7, j + 7, 0.0F, 0.0F, 195, 152, 195, 152);
         if (this.carouselTick % 95 < 5) {
            if (!this.hasSwitchedCarouselImage) {
               this.carouselIndex = (this.carouselIndex + 1) % teaserImages.size();
               this.hasSwitchedCarouselImage = true;
            }
         } else {
            this.hasSwitchedCarouselImage = false;
         }
      }

      this.formattedPopup.renderLeftAlignedNoShadow(p_202330_, this.width / 2 + 52, j + 7, 10, 16777215);
   }

   int popupX0() {
      return (this.width - 310) / 2;
   }

   int popupY0() {
      return this.height / 2 - 80;
   }

   void drawInvitationPendingIcon(PoseStack p_86425_, int p_86426_, int p_86427_, int p_86428_, int p_86429_, boolean p_86430_, boolean p_86431_) {
      int i = this.numberOfPendingInvites;
      boolean flag = this.inPendingInvitationArea((double)p_86426_, (double)p_86427_);
      boolean flag1 = p_86431_ && p_86430_;
      if (flag1) {
         float f = 0.25F + (1.0F + Mth.sin((float)this.animTick * 0.5F)) * 0.25F;
         int j = -16777216 | (int)(f * 64.0F) << 16 | (int)(f * 64.0F) << 8 | (int)(f * 64.0F) << 0;
         int k = p_86428_ - 2;
         int l = p_86428_ + 16;
         int i1 = p_86429_ + 1;
         int j1 = p_86429_ + 16;
         fillGradient(p_86425_, k, i1, l, j1, j, j);
         j = -16777216 | (int)(f * 255.0F) << 16 | (int)(f * 255.0F) << 8 | (int)(f * 255.0F) << 0;
         fillGradient(p_86425_, k, p_86429_, l, p_86429_ + 1, j, j);
         fillGradient(p_86425_, k - 1, p_86429_, k, j1 + 1, j, j);
         fillGradient(p_86425_, l, p_86429_, l + 1, j1, j, j);
         fillGradient(p_86425_, k, j1, l + 1, j1 + 1, j, j);
      }

      RenderSystem.setShaderTexture(0, INVITE_ICON_LOCATION);
      boolean flag2 = p_86431_ && p_86430_;
      float f1 = flag2 ? 16.0F : 0.0F;
      GuiComponent.blit(p_86425_, p_86428_, p_86429_ - 6, f1, 0.0F, 15, 25, 31, 25);
      boolean flag3 = p_86431_ && i != 0;
      if (flag3) {
         int l1 = (Math.min(i, 6) - 1) * 8;
         int j2 = (int)(Math.max(0.0F, Math.max(Mth.sin((float)(10 + this.animTick) * 0.57F), Mth.cos((float)this.animTick * 0.35F))) * -6.0F);
         RenderSystem.setShaderTexture(0, INVITATION_ICONS_LOCATION);
         float f2 = flag ? 8.0F : 0.0F;
         GuiComponent.blit(p_86425_, p_86428_ + 4, p_86429_ + 4 + j2, (float)l1, f2, 8, 8, 48, 16);
      }

      int i2 = p_86426_ + 12;
      boolean flag4 = p_86431_ && flag;
      if (flag4) {
         Component component = i == 0 ? NO_PENDING_INVITES_TEXT : PENDING_INVITES_TEXT;
         int k1 = this.font.width(component);
         fillGradient(p_86425_, i2 - 3, p_86427_ - 3, i2 + k1 + 3, p_86427_ + 8 + 3, -1073741824, -1073741824);
         this.font.drawShadow(p_86425_, component, (float)i2, (float)p_86427_, -1);
      }

   }

   private boolean inPendingInvitationArea(double p_86572_, double p_86573_) {
      int i = this.width / 2 + 50;
      int j = this.width / 2 + 66;
      int k = 11;
      int l = 23;
      if (this.numberOfPendingInvites != 0) {
         i -= 3;
         j += 3;
         k -= 5;
         l += 5;
      }

      return (double)i <= p_86572_ && p_86572_ <= (double)j && (double)k <= p_86573_ && p_86573_ <= (double)l;
   }

   public void play(@Nullable RealmsServer p_86516_, Screen p_86517_) {
      if (p_86516_ != null) {
         try {
            if (!this.connectLock.tryLock(1L, TimeUnit.SECONDS)) {
               return;
            }

            if (this.connectLock.getHoldCount() > 1) {
               return;
            }
         } catch (InterruptedException interruptedexception) {
            return;
         }

         this.dontSetConnectedToRealms = true;
         this.minecraft.setScreen(new RealmsLongRunningMcoTaskScreen(p_86517_, new GetServerDetailsTask(this, p_86517_, p_86516_, this.connectLock)));
      }

   }

   boolean isSelfOwnedServer(RealmsServer p_86684_) {
      return p_86684_.ownerUUID != null && p_86684_.ownerUUID.equals(this.minecraft.getUser().getUuid());
   }

   private boolean isSelfOwnedNonExpiredServer(RealmsServer p_86689_) {
      return this.isSelfOwnedServer(p_86689_) && !p_86689_.expired;
   }

   void drawExpired(PoseStack p_86577_, int p_86578_, int p_86579_, int p_86580_, int p_86581_) {
      RenderSystem.setShaderTexture(0, EXPIRED_ICON_LOCATION);
      GuiComponent.blit(p_86577_, p_86578_, p_86579_, 0.0F, 0.0F, 10, 28, 10, 28);
      if (p_86580_ >= p_86578_ && p_86580_ <= p_86578_ + 9 && p_86581_ >= p_86579_ && p_86581_ <= p_86579_ + 27 && p_86581_ < this.height - 40 && p_86581_ > 32 && !this.shouldShowPopup()) {
         this.setTooltipForNextRenderPass(SERVER_EXPIRED_TOOLTIP);
      }

   }

   void drawExpiring(PoseStack p_86538_, int p_86539_, int p_86540_, int p_86541_, int p_86542_, int p_86543_) {
      RenderSystem.setShaderTexture(0, EXPIRES_SOON_ICON_LOCATION);
      if (this.animTick % 20 < 10) {
         GuiComponent.blit(p_86538_, p_86539_, p_86540_, 0.0F, 0.0F, 10, 28, 20, 28);
      } else {
         GuiComponent.blit(p_86538_, p_86539_, p_86540_, 10.0F, 0.0F, 10, 28, 20, 28);
      }

      if (p_86541_ >= p_86539_ && p_86541_ <= p_86539_ + 9 && p_86542_ >= p_86540_ && p_86542_ <= p_86540_ + 27 && p_86542_ < this.height - 40 && p_86542_ > 32 && !this.shouldShowPopup()) {
         if (p_86543_ <= 0) {
            this.setTooltipForNextRenderPass(SERVER_EXPIRES_SOON_TOOLTIP);
         } else if (p_86543_ == 1) {
            this.setTooltipForNextRenderPass(SERVER_EXPIRES_IN_DAY_TOOLTIP);
         } else {
            this.setTooltipForNextRenderPass(Component.translatable("mco.selectServer.expires.days", p_86543_));
         }
      }

   }

   void drawOpen(PoseStack p_86602_, int p_86603_, int p_86604_, int p_86605_, int p_86606_) {
      RenderSystem.setShaderTexture(0, ON_ICON_LOCATION);
      GuiComponent.blit(p_86602_, p_86603_, p_86604_, 0.0F, 0.0F, 10, 28, 10, 28);
      if (p_86605_ >= p_86603_ && p_86605_ <= p_86603_ + 9 && p_86606_ >= p_86604_ && p_86606_ <= p_86604_ + 27 && p_86606_ < this.height - 40 && p_86606_ > 32 && !this.shouldShowPopup()) {
         this.setTooltipForNextRenderPass(SERVER_OPEN_TOOLTIP);
      }

   }

   void drawClose(PoseStack p_86627_, int p_86628_, int p_86629_, int p_86630_, int p_86631_) {
      RenderSystem.setShaderTexture(0, OFF_ICON_LOCATION);
      GuiComponent.blit(p_86627_, p_86628_, p_86629_, 0.0F, 0.0F, 10, 28, 10, 28);
      if (p_86630_ >= p_86628_ && p_86630_ <= p_86628_ + 9 && p_86631_ >= p_86629_ && p_86631_ <= p_86629_ + 27 && p_86631_ < this.height - 40 && p_86631_ > 32 && !this.shouldShowPopup()) {
         this.setTooltipForNextRenderPass(SERVER_CLOSED_TOOLTIP);
      }

   }

   void renderNews(PoseStack p_86433_, int p_86434_, int p_86435_, boolean p_86436_, int p_86437_, int p_86438_, boolean p_86439_, boolean p_86440_) {
      boolean flag = false;
      if (p_86434_ >= p_86437_ && p_86434_ <= p_86437_ + 20 && p_86435_ >= p_86438_ && p_86435_ <= p_86438_ + 20) {
         flag = true;
      }

      RenderSystem.setShaderTexture(0, NEWS_LOCATION);
      if (!p_86440_) {
         RenderSystem.setShaderColor(0.5F, 0.5F, 0.5F, 1.0F);
      }

      boolean flag1 = p_86440_ && p_86439_;
      float f = flag1 ? 20.0F : 0.0F;
      GuiComponent.blit(p_86433_, p_86437_, p_86438_, f, 0.0F, 20, 20, 40, 20);
      if (flag && p_86440_) {
         this.setTooltipForNextRenderPass(NEWS_TOOLTIP);
      }

      RenderSystem.setShaderColor(1.0F, 1.0F, 1.0F, 1.0F);
      if (p_86436_ && p_86440_) {
         int i = flag ? 0 : (int)(Math.max(0.0F, Math.max(Mth.sin((float)(10 + this.animTick) * 0.57F), Mth.cos((float)this.animTick * 0.35F))) * -6.0F);
         RenderSystem.setShaderTexture(0, INVITATION_ICONS_LOCATION);
         GuiComponent.blit(p_86433_, p_86437_ + 10, p_86438_ + 2 + i, 40.0F, 0.0F, 8, 8, 48, 16);
      }

   }

   private void renderLocal(PoseStack p_86532_) {
      String s = "LOCAL!";
      p_86532_.pushPose();
      p_86532_.translate((float)(this.width / 2 - 25), 20.0F, 0.0F);
      p_86532_.mulPose(Axis.ZP.rotationDegrees(-20.0F));
      p_86532_.scale(1.5F, 1.5F, 1.5F);
      this.font.draw(p_86532_, "LOCAL!", 0.0F, 0.0F, 8388479);
      p_86532_.popPose();
   }

   private void renderStage(PoseStack p_86575_) {
      String s = "STAGE!";
      p_86575_.pushPose();
      p_86575_.translate((float)(this.width / 2 - 25), 20.0F, 0.0F);
      p_86575_.mulPose(Axis.ZP.rotationDegrees(-20.0F));
      p_86575_.scale(1.5F, 1.5F, 1.5F);
      this.font.draw(p_86575_, "STAGE!", 0.0F, 0.0F, -256);
      p_86575_.popPose();
   }

   public RealmsMainScreen newScreen() {
      RealmsMainScreen realmsmainscreen = new RealmsMainScreen(this.lastScreen);
      realmsmainscreen.init(this.minecraft, this.width, this.height);
      return realmsmainscreen;
   }

   public static void updateTeaserImages(ResourceManager p_86407_) {
      Collection<ResourceLocation> collection = p_86407_.listResources("textures/gui/images", (p_193492_) -> {
         return p_193492_.getPath().endsWith(".png");
      }).keySet();
      teaserImages = collection.stream().filter((p_231247_) -> {
         return p_231247_.getNamespace().equals("realms");
      }).toList();
   }

   private void pendingButtonPress(Button p_86519_) {
      this.minecraft.setScreen(new RealmsPendingInvitesScreen(this.lastScreen));
   }

   @OnlyIn(Dist.CLIENT)
   class ButtonEntry extends RealmsMainScreen.Entry {
      private final Button button;
      private final int xPos = RealmsMainScreen.this.width / 2 - 75;

      public ButtonEntry(Button p_275726_) {
         this.button = p_275726_;
      }

      public boolean mouseClicked(double p_275240_, double p_275616_, int p_275528_) {
         return this.button.isMouseOver(p_275240_, p_275616_) ? this.button.mouseClicked(p_275240_, p_275616_, p_275528_) : false;
      }

      public boolean keyPressed(int p_275630_, int p_275328_, int p_275519_) {
         return this.button.keyPressed(p_275630_, p_275328_, p_275519_) ? true : super.keyPressed(p_275630_, p_275328_, p_275519_);
      }

      public void render(PoseStack p_275483_, int p_275722_, int p_275699_, int p_275728_, int p_275557_, int p_275427_, int p_275348_, int p_275426_, boolean p_275366_, float p_275350_) {
         this.button.setPosition(this.xPos, p_275699_ + 4);
         this.button.render(p_275483_, p_275348_, p_275426_, p_275350_);
      }

      public Component getNarration() {
         return this.button.getMessage();
      }
   }

   @OnlyIn(Dist.CLIENT)
   class CloseButton extends RealmsMainScreen.CrossButton {
      public CloseButton() {
         super(RealmsMainScreen.this.popupX0() + 4, RealmsMainScreen.this.popupY0() + 4, (p_86775_) -> {
            RealmsMainScreen.this.onClosePopup();
         }, Component.translatable("mco.selectServer.close"));
      }
   }

   @OnlyIn(Dist.CLIENT)
   static class CrossButton extends Button {
      protected CrossButton(Button.OnPress p_275420_, Component p_275193_) {
         this(0, 0, p_275420_, p_275193_);
      }

      protected CrossButton(int p_275644_, int p_275716_, Button.OnPress p_275547_, Component p_275717_) {
         super(p_275644_, p_275716_, 14, 14, p_275717_, p_275547_, DEFAULT_NARRATION);
         this.setTooltip(Tooltip.create(p_275717_));
      }

      public void renderWidget(PoseStack p_275594_, int p_275558_, int p_275461_, float p_275331_) {
         RenderSystem.setShaderTexture(0, RealmsMainScreen.CROSS_ICON_LOCATION);
         float f = this.isHoveredOrFocused() ? 14.0F : 0.0F;
         blit(p_275594_, this.getX(), this.getY(), 0.0F, f, 14, 14, 14, 28);
      }
   }

   @OnlyIn(Dist.CLIENT)
   abstract class Entry extends ObjectSelectionList.Entry<RealmsMainScreen.Entry> {
      @Nullable
      public RealmsServer getServer() {
         return null;
      }
   }

   @OnlyIn(Dist.CLIENT)
   class NewsButton extends Button {
      public NewsButton() {
         super(RealmsMainScreen.this.width - 115, 6, 20, 20, Component.translatable("mco.news"), (p_274636_) -> {
            if (RealmsMainScreen.this.newsLink != null) {
               ConfirmLinkScreen.confirmLinkNow(RealmsMainScreen.this.newsLink, RealmsMainScreen.this, true);
               if (RealmsMainScreen.this.hasUnreadNews) {
                  RealmsPersistence.RealmsPersistenceData realmspersistence$realmspersistencedata = RealmsPersistence.readFile();
                  realmspersistence$realmspersistencedata.hasUnreadNews = false;
                  RealmsMainScreen.this.hasUnreadNews = false;
                  RealmsPersistence.writeFile(realmspersistence$realmspersistencedata);
               }

            }
         }, DEFAULT_NARRATION);
      }

      public void renderWidget(PoseStack p_268182_, int p_268272_, int p_268161_, float p_267944_) {
         RealmsMainScreen.this.renderNews(p_268182_, p_268272_, p_268161_, RealmsMainScreen.this.hasUnreadNews, this.getX(), this.getY(), this.isHoveredOrFocused(), this.active);
      }
   }

   @OnlyIn(Dist.CLIENT)
   class NotificationMessageEntry extends RealmsMainScreen.Entry {
      private static final int SIDE_MARGINS = 40;
      private static final int ITEM_HEIGHT = 36;
      private static final int OUTLINE_COLOR = -12303292;
      private final Component text;
      private final List<AbstractWidget> children = new ArrayList<>();
      @Nullable
      private final RealmsMainScreen.CrossButton dismissButton;
      private final MultiLineTextWidget textWidget;
      private final GridLayout gridLayout;
      private final FrameLayout textFrame;
      private int lastEntryWidth = -1;

      public NotificationMessageEntry(Component p_275215_, RealmsNotification p_275494_) {
         this.text = p_275215_;
         this.gridLayout = new GridLayout();
         int i = 7;
         this.gridLayout.addChild(new ImageWidget(20, 20, RealmsMainScreen.INFO_ICON_LOCATION), 0, 0, this.gridLayout.newCellSettings().padding(7, 7, 0, 0));
         this.gridLayout.addChild(SpacerElement.width(40), 0, 0);
         this.textFrame = this.gridLayout.addChild(new FrameLayout(0, 9 * 3), 0, 1, this.gridLayout.newCellSettings().paddingTop(7));
         this.textWidget = this.textFrame.addChild((new MultiLineTextWidget(p_275215_, RealmsMainScreen.this.font)).setCentered(true).setMaxRows(3), this.textFrame.newChildLayoutSettings().alignHorizontallyCenter().alignVerticallyTop());
         this.gridLayout.addChild(SpacerElement.width(40), 0, 2);
         if (p_275494_.dismissable()) {
            this.dismissButton = this.gridLayout.addChild(new RealmsMainScreen.CrossButton((p_275478_) -> {
               RealmsMainScreen.this.dismissNotification(p_275494_.uuid());
            }, Component.translatable("mco.notification.dismiss")), 0, 2, this.gridLayout.newCellSettings().alignHorizontallyRight().padding(0, 7, 7, 0));
         } else {
            this.dismissButton = null;
         }

         this.gridLayout.visitWidgets(this.children::add);
      }

      public boolean keyPressed(int p_275646_, int p_275453_, int p_275621_) {
         return this.dismissButton != null && this.dismissButton.keyPressed(p_275646_, p_275453_, p_275621_) ? true : super.keyPressed(p_275646_, p_275453_, p_275621_);
      }

      private void updateEntryWidth(int p_275670_) {
         if (this.lastEntryWidth != p_275670_) {
            this.refreshLayout(p_275670_);
            this.lastEntryWidth = p_275670_;
         }

      }

      private void refreshLayout(int p_275267_) {
         int i = p_275267_ - 80;
         this.textFrame.setMinWidth(i);
         this.textWidget.setMaxWidth(i);
         this.gridLayout.arrangeElements();
      }

      public void renderBack(PoseStack p_275597_, int p_275746_, int p_275287_, int p_275495_, int p_275270_, int p_275216_, int p_275339_, int p_275617_, boolean p_275253_, float p_275346_) {
         super.renderBack(p_275597_, p_275746_, p_275287_, p_275495_, p_275270_, p_275216_, p_275339_, p_275617_, p_275253_, p_275346_);
         GuiComponent.renderOutline(p_275597_, p_275495_ - 2, p_275287_ - 2, p_275270_, 70, -12303292);
      }

      public void render(PoseStack p_275732_, int p_275375_, int p_275358_, int p_275447_, int p_275694_, int p_275477_, int p_275710_, int p_275677_, boolean p_275542_, float p_275323_) {
         this.gridLayout.setPosition(p_275447_, p_275358_);
         this.updateEntryWidth(p_275694_ - 4);
         this.children.forEach((p_275622_) -> {
            p_275622_.render(p_275732_, p_275710_, p_275677_, p_275323_);
         });
      }

      public boolean mouseClicked(double p_275209_, double p_275338_, int p_275560_) {
         return this.dismissButton != null && this.dismissButton.mouseClicked(p_275209_, p_275338_, p_275560_) ? true : super.mouseClicked(p_275209_, p_275338_, p_275560_);
      }

      public Component getNarration() {
         return this.text;
      }
   }

   @OnlyIn(Dist.CLIENT)
   class PendingInvitesButton extends Button {
      public PendingInvitesButton() {
         super(RealmsMainScreen.this.width / 2 + 50, 6, 22, 22, CommonComponents.EMPTY, RealmsMainScreen.this::pendingButtonPress, DEFAULT_NARRATION);
      }

      public void tick() {
         this.setMessage(RealmsMainScreen.this.numberOfPendingInvites == 0 ? RealmsMainScreen.NO_PENDING_INVITES_TEXT : RealmsMainScreen.PENDING_INVITES_TEXT);
      }

      public void renderWidget(PoseStack p_268276_, int p_268267_, int p_268092_, float p_268234_) {
         RealmsMainScreen.this.drawInvitationPendingIcon(p_268276_, p_268267_, p_268092_, this.getX(), this.getY(), this.isHoveredOrFocused(), this.active);
      }
   }

   @OnlyIn(Dist.CLIENT)
   class RealmSelectionList extends RealmsObjectSelectionList<RealmsMainScreen.Entry> {
      public RealmSelectionList() {
         super(RealmsMainScreen.this.width, RealmsMainScreen.this.height, 32, RealmsMainScreen.this.height - 64, 36);
      }

      public boolean keyPressed(int p_86840_, int p_86841_, int p_86842_) {
         if (p_86840_ == 257 || p_86840_ == 32 || p_86840_ == 335) {
            RealmsMainScreen.Entry realmsmainscreen$entry = this.getSelected();
            if (realmsmainscreen$entry == null) {
               return super.keyPressed(p_86840_, p_86841_, p_86842_);
            }

            realmsmainscreen$entry.keyPressed(p_86840_, p_86841_, p_86842_);
         }

         return super.keyPressed(p_86840_, p_86841_, p_86842_);
      }

      public boolean mouseClicked(double p_86828_, double p_86829_, int p_86830_) {
         if (p_86830_ == 0 && p_86828_ < (double)this.getScrollbarPosition() && p_86829_ >= (double)this.y0 && p_86829_ <= (double)this.y1) {
            int i = RealmsMainScreen.this.realmSelectionList.getRowLeft();
            int j = this.getScrollbarPosition();
            int k = (int)Math.floor(p_86829_ - (double)this.y0) - this.headerHeight + (int)this.getScrollAmount() - 4;
            int l = k / this.itemHeight;
            if (p_86828_ >= (double)i && p_86828_ <= (double)j && l >= 0 && k >= 0 && l < this.getItemCount()) {
               this.itemClicked(k, l, p_86828_, p_86829_, this.width, p_86830_);
               this.selectItem(l);
            }

            return true;
         } else {
            return super.mouseClicked(p_86828_, p_86829_, p_86830_);
         }
      }

      public void setSelected(@Nullable RealmsMainScreen.Entry p_86849_) {
         super.setSelected(p_86849_);
         if (p_86849_ != null) {
            RealmsMainScreen.this.updateButtonStates(p_86849_.getServer());
         } else {
            RealmsMainScreen.this.updateButtonStates((RealmsServer)null);
         }

      }

      public void itemClicked(int p_275372_, int p_275725_, double p_275315_, double p_275486_, int p_275588_, int p_275503_) {
         RealmsMainScreen.Entry realmsmainscreen$entry = this.getEntry(p_275725_);
         if (!realmsmainscreen$entry.mouseClicked(p_275315_, p_275486_, p_275503_)) {
            if (realmsmainscreen$entry instanceof RealmsMainScreen.TrialEntry) {
               RealmsMainScreen.this.popupOpenedByUser = true;
            } else {
               RealmsServer realmsserver = realmsmainscreen$entry.getServer();
               if (realmsserver != null) {
                  if (realmsserver.state == RealmsServer.State.UNINITIALIZED) {
                     Minecraft.getInstance().setScreen(new RealmsCreateRealmScreen(realmsserver, RealmsMainScreen.this));
                  } else {
                     if (RealmsMainScreen.this.shouldPlayButtonBeActive(realmsserver)) {
                        if (Util.getMillis() - RealmsMainScreen.this.lastClickTime < 250L && this.isSelectedItem(p_275725_)) {
                           RealmsMainScreen.this.play(realmsserver, RealmsMainScreen.this);
                        }

                        RealmsMainScreen.this.lastClickTime = Util.getMillis();
                     }

                  }
               }
            }
         }
      }

      public int getMaxPosition() {
         return this.getItemCount() * 36;
      }

      public int getRowWidth() {
         return 300;
      }
   }

   @OnlyIn(Dist.CLIENT)
   interface RealmsCall<T> {
      T request(RealmsClient p_275639_) throws RealmsServiceException;
   }

   @OnlyIn(Dist.CLIENT)
   class ServerEntry extends RealmsMainScreen.Entry {
      private static final int SKIN_HEAD_LARGE_WIDTH = 36;
      private final RealmsServer serverData;

      public ServerEntry(RealmsServer p_86856_) {
         this.serverData = p_86856_;
      }

      public void render(PoseStack p_86866_, int p_86867_, int p_86868_, int p_86869_, int p_86870_, int p_86871_, int p_86872_, int p_86873_, boolean p_86874_, float p_86875_) {
         this.renderMcoServerItem(this.serverData, p_86866_, p_86869_, p_86868_, p_86872_, p_86873_);
      }

      public boolean mouseClicked(double p_86858_, double p_86859_, int p_86860_) {
         if (this.serverData.state == RealmsServer.State.UNINITIALIZED) {
            RealmsMainScreen.this.minecraft.setScreen(new RealmsCreateRealmScreen(this.serverData, RealmsMainScreen.this));
         }

         return true;
      }

      private void renderMcoServerItem(RealmsServer p_86879_, PoseStack p_86880_, int p_86881_, int p_86882_, int p_86883_, int p_86884_) {
         this.renderLegacy(p_86879_, p_86880_, p_86881_ + 36, p_86882_, p_86883_, p_86884_);
      }

      private void renderLegacy(RealmsServer p_86886_, PoseStack p_86887_, int p_86888_, int p_86889_, int p_86890_, int p_86891_) {
         if (p_86886_.state == RealmsServer.State.UNINITIALIZED) {
            RenderSystem.setShaderTexture(0, RealmsMainScreen.WORLDICON_LOCATION);
            GuiComponent.blit(p_86887_, p_86888_ + 10, p_86889_ + 6, 0.0F, 0.0F, 40, 20, 40, 20);
            float f = 0.5F + (1.0F + Mth.sin((float)RealmsMainScreen.this.animTick * 0.25F)) * 0.25F;
            int l = -16777216 | (int)(127.0F * f) << 16 | (int)(255.0F * f) << 8 | (int)(127.0F * f);
            GuiComponent.drawCenteredString(p_86887_, RealmsMainScreen.this.font, RealmsMainScreen.SERVER_UNITIALIZED_TEXT, p_86888_ + 10 + 40 + 75, p_86889_ + 12, l);
         } else {
            int i = 225;
            int j = 2;
            this.renderStatusLights(p_86886_, p_86887_, p_86888_, p_86889_, p_86890_, p_86891_, 225, 2);
            if (!"0".equals(p_86886_.serverPing.nrOfPlayers)) {
               String s = ChatFormatting.GRAY + p_86886_.serverPing.nrOfPlayers;
               RealmsMainScreen.this.font.draw(p_86887_, s, (float)(p_86888_ + 207 - RealmsMainScreen.this.font.width(s)), (float)(p_86889_ + 3), 8421504);
               if (p_86890_ >= p_86888_ + 207 - RealmsMainScreen.this.font.width(s) && p_86890_ <= p_86888_ + 207 && p_86891_ >= p_86889_ + 1 && p_86891_ <= p_86889_ + 10 && p_86891_ < RealmsMainScreen.this.height - 40 && p_86891_ > 32 && !RealmsMainScreen.this.shouldShowPopup()) {
                  RealmsMainScreen.this.setTooltipForNextRenderPass(Component.literal(p_86886_.serverPing.playerList));
               }
            }

            if (RealmsMainScreen.this.isSelfOwnedServer(p_86886_) && p_86886_.expired) {
               Component component = p_86886_.expiredTrial ? RealmsMainScreen.TRIAL_EXPIRED_TEXT : RealmsMainScreen.SUBSCRIPTION_EXPIRED_TEXT;
               int j1 = p_86889_ + 11 + 5;
               RealmsMainScreen.this.font.draw(p_86887_, component, (float)(p_86888_ + 2), (float)(j1 + 1), 15553363);
            } else {
               if (p_86886_.worldType == RealmsServer.WorldType.MINIGAME) {
                  int i1 = 13413468;
                  int k = RealmsMainScreen.this.font.width(RealmsMainScreen.SELECT_MINIGAME_PREFIX);
                  RealmsMainScreen.this.font.draw(p_86887_, RealmsMainScreen.SELECT_MINIGAME_PREFIX, (float)(p_86888_ + 2), (float)(p_86889_ + 12), 13413468);
                  RealmsMainScreen.this.font.draw(p_86887_, p_86886_.getMinigameName(), (float)(p_86888_ + 2 + k), (float)(p_86889_ + 12), 7105644);
               } else {
                  RealmsMainScreen.this.font.draw(p_86887_, p_86886_.getDescription(), (float)(p_86888_ + 2), (float)(p_86889_ + 12), 7105644);
               }

               if (!RealmsMainScreen.this.isSelfOwnedServer(p_86886_)) {
                  RealmsMainScreen.this.font.draw(p_86887_, p_86886_.owner, (float)(p_86888_ + 2), (float)(p_86889_ + 12 + 11), 5000268);
               }
            }

            RealmsMainScreen.this.font.draw(p_86887_, p_86886_.getName(), (float)(p_86888_ + 2), (float)(p_86889_ + 1), 16777215);
            RealmsUtil.renderPlayerFace(p_86887_, p_86888_ - 36, p_86889_, 32, p_86886_.ownerUUID);
         }
      }

      private void renderStatusLights(RealmsServer p_272798_, PoseStack p_273673_, int p_273706_, int p_272591_, int p_273561_, int p_273468_, int p_273073_, int p_273187_) {
         int i = p_273706_ + p_273073_ + 22;
         if (p_272798_.expired) {
            RealmsMainScreen.this.drawExpired(p_273673_, i, p_272591_ + p_273187_, p_273561_, p_273468_);
         } else if (p_272798_.state == RealmsServer.State.CLOSED) {
            RealmsMainScreen.this.drawClose(p_273673_, i, p_272591_ + p_273187_, p_273561_, p_273468_);
         } else if (RealmsMainScreen.this.isSelfOwnedServer(p_272798_) && p_272798_.daysLeft < 7) {
            RealmsMainScreen.this.drawExpiring(p_273673_, i, p_272591_ + p_273187_, p_273561_, p_273468_, p_272798_.daysLeft);
         } else if (p_272798_.state == RealmsServer.State.OPEN) {
            RealmsMainScreen.this.drawOpen(p_273673_, i, p_272591_ + p_273187_, p_273561_, p_273468_);
         }

      }

      public Component getNarration() {
         return (Component)(this.serverData.state == RealmsServer.State.UNINITIALIZED ? RealmsMainScreen.UNITIALIZED_WORLD_NARRATION : Component.translatable("narrator.select", this.serverData.name));
      }

      @Nullable
      public RealmsServer getServer() {
         return this.serverData;
      }
   }

   @OnlyIn(Dist.CLIENT)
   class TrialEntry extends RealmsMainScreen.Entry {
      public void render(PoseStack p_86921_, int p_86922_, int p_86923_, int p_86924_, int p_86925_, int p_86926_, int p_86927_, int p_86928_, boolean p_86929_, float p_86930_) {
         this.renderTrialItem(p_86921_, p_86922_, p_86924_, p_86923_, p_86927_, p_86928_);
      }

      public boolean mouseClicked(double p_86910_, double p_86911_, int p_86912_) {
         RealmsMainScreen.this.popupOpenedByUser = true;
         return true;
      }

      private void renderTrialItem(PoseStack p_86914_, int p_86915_, int p_86916_, int p_86917_, int p_86918_, int p_86919_) {
         int i = p_86917_ + 8;
         int j = 0;
         boolean flag = false;
         if (p_86916_ <= p_86918_ && p_86918_ <= (int)RealmsMainScreen.this.realmSelectionList.getScrollAmount() && p_86917_ <= p_86919_ && p_86919_ <= p_86917_ + 32) {
            flag = true;
         }

         int k = 8388479;
         if (flag && !RealmsMainScreen.this.shouldShowPopup()) {
            k = 6077788;
         }

         for(Component component : RealmsMainScreen.TRIAL_MESSAGE_LINES) {
            GuiComponent.drawCenteredString(p_86914_, RealmsMainScreen.this.font, component, RealmsMainScreen.this.width / 2, i + j, k);
            j += 10;
         }

      }

      public Component getNarration() {
         return RealmsMainScreen.TRIAL_TEXT;
      }
   }
}