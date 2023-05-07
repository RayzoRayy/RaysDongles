package com.mojang.realmsclient.gui.screens;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.logging.LogUtils;
import com.mojang.realmsclient.client.RealmsClient;
import com.mojang.realmsclient.dto.RealmsServer;
import com.mojang.realmsclient.dto.Subscription;
import com.mojang.realmsclient.exception.RealmsServiceException;
import java.text.DateFormat;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.TimeZone;
import javax.annotation.Nullable;
import net.minecraft.Util;
import net.minecraft.client.GameNarrator;
import net.minecraft.client.gui.components.Button;
import net.minecraft.client.gui.components.MultiLineTextWidget;
import net.minecraft.client.gui.screens.Screen;
import net.minecraft.network.chat.CommonComponents;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.MutableComponent;
import net.minecraft.realms.RealmsScreen;
import net.minecraft.util.CommonLinks;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import org.slf4j.Logger;

@OnlyIn(Dist.CLIENT)
public class RealmsSubscriptionInfoScreen extends RealmsScreen {
   static final Logger LOGGER = LogUtils.getLogger();
   private static final Component SUBSCRIPTION_TITLE = Component.translatable("mco.configure.world.subscription.title");
   private static final Component SUBSCRIPTION_START_LABEL = Component.translatable("mco.configure.world.subscription.start");
   private static final Component TIME_LEFT_LABEL = Component.translatable("mco.configure.world.subscription.timeleft");
   private static final Component DAYS_LEFT_LABEL = Component.translatable("mco.configure.world.subscription.recurring.daysleft");
   private static final Component SUBSCRIPTION_EXPIRED_TEXT = Component.translatable("mco.configure.world.subscription.expired");
   private static final Component SUBSCRIPTION_LESS_THAN_A_DAY_TEXT = Component.translatable("mco.configure.world.subscription.less_than_a_day");
   private static final Component MONTH_SUFFIX = Component.translatable("mco.configure.world.subscription.month");
   private static final Component MONTHS_SUFFIX = Component.translatable("mco.configure.world.subscription.months");
   private static final Component DAY_SUFFIX = Component.translatable("mco.configure.world.subscription.day");
   private static final Component DAYS_SUFFIX = Component.translatable("mco.configure.world.subscription.days");
   private static final Component UNKNOWN = Component.translatable("mco.configure.world.subscription.unknown");
   private static final Component RECURRING_INFO = Component.translatable("mco.configure.world.subscription.recurring.info");
   private final Screen lastScreen;
   final RealmsServer serverData;
   final Screen mainScreen;
   private Component daysLeft = UNKNOWN;
   private Component startDate = UNKNOWN;
   @Nullable
   private Subscription.SubscriptionType type;

   public RealmsSubscriptionInfoScreen(Screen p_89979_, RealmsServer p_89980_, Screen p_89981_) {
      super(GameNarrator.NO_TITLE);
      this.lastScreen = p_89979_;
      this.serverData = p_89980_;
      this.mainScreen = p_89981_;
   }

   public void init() {
      this.getSubscription(this.serverData.id);
      this.addRenderableWidget(Button.builder(Component.translatable("mco.configure.world.subscription.extend"), (p_276232_) -> {
         String s = CommonLinks.extendRealms(this.serverData.remoteSubscriptionId, this.minecraft.getUser().getUuid());
         this.minecraft.keyboardHandler.setClipboard(s);
         Util.getPlatform().openUri(s);
      }).bounds(this.width / 2 - 100, row(6), 200, 20).build());
      this.addRenderableWidget(Button.builder(CommonComponents.GUI_BACK, (p_90006_) -> {
         this.minecraft.setScreen(this.lastScreen);
      }).bounds(this.width / 2 - 100, row(12), 200, 20).build());
      if (this.serverData.expired) {
         this.addRenderableWidget(Button.builder(Component.translatable("mco.configure.world.delete.button"), (p_89999_) -> {
            Component component = Component.translatable("mco.configure.world.delete.question.line1");
            Component component1 = Component.translatable("mco.configure.world.delete.question.line2");
            this.minecraft.setScreen(new RealmsLongConfirmationScreen(this::deleteRealm, RealmsLongConfirmationScreen.Type.Warning, component, component1, true));
         }).bounds(this.width / 2 - 100, row(10), 200, 20).build());
      } else {
         this.addRenderableWidget((new MultiLineTextWidget(this.width / 2 - 100, row(8), RECURRING_INFO, this.font)).setColor(10526880).setMaxWidth(200));
      }

   }

   public Component getNarrationMessage() {
      return CommonComponents.joinLines(SUBSCRIPTION_TITLE, SUBSCRIPTION_START_LABEL, this.startDate, TIME_LEFT_LABEL, this.daysLeft);
   }

   private void deleteRealm(boolean p_90012_) {
      if (p_90012_) {
         (new Thread("Realms-delete-realm") {
            public void run() {
               try {
                  RealmsClient realmsclient = RealmsClient.create();
                  realmsclient.deleteWorld(RealmsSubscriptionInfoScreen.this.serverData.id);
               } catch (RealmsServiceException realmsserviceexception) {
                  RealmsSubscriptionInfoScreen.LOGGER.error("Couldn't delete world", (Throwable)realmsserviceexception);
               }

               RealmsSubscriptionInfoScreen.this.minecraft.execute(() -> {
                  RealmsSubscriptionInfoScreen.this.minecraft.setScreen(RealmsSubscriptionInfoScreen.this.mainScreen);
               });
            }
         }).start();
      }

      this.minecraft.setScreen(this);
   }

   private void getSubscription(long p_89990_) {
      RealmsClient realmsclient = RealmsClient.create();

      try {
         Subscription subscription = realmsclient.subscriptionFor(p_89990_);
         this.daysLeft = this.daysLeftPresentation(subscription.daysLeft);
         this.startDate = localPresentation(subscription.startDate);
         this.type = subscription.type;
      } catch (RealmsServiceException realmsserviceexception) {
         LOGGER.error("Couldn't get subscription");
         this.minecraft.setScreen(new RealmsGenericErrorScreen(realmsserviceexception, this.lastScreen));
      }

   }

   private static Component localPresentation(long p_182539_) {
      Calendar calendar = new GregorianCalendar(TimeZone.getDefault());
      calendar.setTimeInMillis(p_182539_);
      return Component.literal(DateFormat.getDateTimeInstance().format(calendar.getTime()));
   }

   public boolean keyPressed(int p_89986_, int p_89987_, int p_89988_) {
      if (p_89986_ == 256) {
         this.minecraft.setScreen(this.lastScreen);
         return true;
      } else {
         return super.keyPressed(p_89986_, p_89987_, p_89988_);
      }
   }

   public void render(PoseStack p_89992_, int p_89993_, int p_89994_, float p_89995_) {
      this.renderBackground(p_89992_);
      int i = this.width / 2 - 100;
      drawCenteredString(p_89992_, this.font, SUBSCRIPTION_TITLE, this.width / 2, 17, 16777215);
      this.font.draw(p_89992_, SUBSCRIPTION_START_LABEL, (float)i, (float)row(0), 10526880);
      this.font.draw(p_89992_, this.startDate, (float)i, (float)row(1), 16777215);
      if (this.type == Subscription.SubscriptionType.NORMAL) {
         this.font.draw(p_89992_, TIME_LEFT_LABEL, (float)i, (float)row(3), 10526880);
      } else if (this.type == Subscription.SubscriptionType.RECURRING) {
         this.font.draw(p_89992_, DAYS_LEFT_LABEL, (float)i, (float)row(3), 10526880);
      }

      this.font.draw(p_89992_, this.daysLeft, (float)i, (float)row(4), 16777215);
      super.render(p_89992_, p_89993_, p_89994_, p_89995_);
   }

   private Component daysLeftPresentation(int p_89984_) {
      if (p_89984_ < 0 && this.serverData.expired) {
         return SUBSCRIPTION_EXPIRED_TEXT;
      } else if (p_89984_ <= 1) {
         return SUBSCRIPTION_LESS_THAN_A_DAY_TEXT;
      } else {
         int i = p_89984_ / 30;
         int j = p_89984_ % 30;
         MutableComponent mutablecomponent = Component.empty();
         if (i > 0) {
            mutablecomponent.append(Integer.toString(i)).append(CommonComponents.SPACE);
            if (i == 1) {
               mutablecomponent.append(MONTH_SUFFIX);
            } else {
               mutablecomponent.append(MONTHS_SUFFIX);
            }
         }

         if (j > 0) {
            if (i > 0) {
               mutablecomponent.append(", ");
            }

            mutablecomponent.append(Integer.toString(j)).append(CommonComponents.SPACE);
            if (j == 1) {
               mutablecomponent.append(DAY_SUFFIX);
            } else {
               mutablecomponent.append(DAYS_SUFFIX);
            }
         }

         return mutablecomponent;
      }
   }
}