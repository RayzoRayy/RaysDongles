package net.minecraft.client.gui.screens.worldselection;

import com.mojang.blaze3d.vertex.PoseStack;
import it.unimi.dsi.fastutil.booleans.BooleanConsumer;
import java.util.Collection;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiComponent;
import net.minecraft.client.gui.components.AbstractWidget;
import net.minecraft.client.gui.components.Button;
import net.minecraft.client.gui.components.MultiLineLabel;
import net.minecraft.client.gui.components.MultiLineTextWidget;
import net.minecraft.client.gui.components.ObjectSelectionList;
import net.minecraft.client.gui.components.StringWidget;
import net.minecraft.client.gui.layouts.FrameLayout;
import net.minecraft.client.gui.layouts.GridLayout;
import net.minecraft.client.gui.layouts.LayoutSettings;
import net.minecraft.client.gui.screens.Screen;
import net.minecraft.network.chat.CommonComponents;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.ComponentUtils;
import net.minecraft.network.chat.Style;
import net.minecraft.server.packs.repository.Pack;
import net.minecraft.world.flag.FeatureFlags;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

@OnlyIn(Dist.CLIENT)
public class ConfirmExperimentalFeaturesScreen extends Screen {
   private static final Component TITLE = Component.translatable("selectWorld.experimental.title");
   private static final Component MESSAGE = Component.translatable("selectWorld.experimental.message");
   private static final Component DETAILS_BUTTON = Component.translatable("selectWorld.experimental.details");
   private static final int COLUMN_SPACING = 10;
   private static final int DETAILS_BUTTON_WIDTH = 100;
   private final BooleanConsumer callback;
   final Collection<Pack> enabledPacks;
   private final GridLayout layout = (new GridLayout()).columnSpacing(10).rowSpacing(20);

   public ConfirmExperimentalFeaturesScreen(Collection<Pack> p_252011_, BooleanConsumer p_250152_) {
      super(TITLE);
      this.enabledPacks = p_252011_;
      this.callback = p_250152_;
   }

   public Component getNarrationMessage() {
      return CommonComponents.joinForNarration(super.getNarrationMessage(), MESSAGE);
   }

   protected void init() {
      super.init();
      GridLayout.RowHelper gridlayout$rowhelper = this.layout.createRowHelper(2);
      LayoutSettings layoutsettings = gridlayout$rowhelper.newCellSettings().alignHorizontallyCenter();
      gridlayout$rowhelper.addChild(new StringWidget(this.title, this.font), 2, layoutsettings);
      MultiLineTextWidget multilinetextwidget = gridlayout$rowhelper.addChild((new MultiLineTextWidget(MESSAGE, this.font)).setCentered(true), 2, layoutsettings);
      multilinetextwidget.setMaxWidth(310);
      gridlayout$rowhelper.addChild(Button.builder(DETAILS_BUTTON, (p_251857_) -> {
         this.minecraft.setScreen(new ConfirmExperimentalFeaturesScreen.DetailsScreen());
      }).width(100).build(), 2, layoutsettings);
      gridlayout$rowhelper.addChild(Button.builder(CommonComponents.GUI_PROCEED, (p_252248_) -> {
         this.callback.accept(true);
      }).build());
      gridlayout$rowhelper.addChild(Button.builder(CommonComponents.GUI_BACK, (p_250397_) -> {
         this.callback.accept(false);
      }).build());
      this.layout.visitWidgets((p_269625_) -> {
         AbstractWidget abstractwidget = this.addRenderableWidget(p_269625_);
      });
      this.layout.arrangeElements();
      this.repositionElements();
   }

   protected void repositionElements() {
      FrameLayout.alignInRectangle(this.layout, 0, 0, this.width, this.height, 0.5F, 0.5F);
   }

   public void render(PoseStack p_251344_, int p_248682_, int p_251917_, float p_249477_) {
      this.renderBackground(p_251344_);
      super.render(p_251344_, p_248682_, p_251917_, p_249477_);
   }

   public void onClose() {
      this.callback.accept(false);
   }

   @OnlyIn(Dist.CLIENT)
   class DetailsScreen extends Screen {
      private ConfirmExperimentalFeaturesScreen.DetailsScreen.PackList packList;

      DetailsScreen() {
         super(Component.translatable("selectWorld.experimental.details.title"));
      }

      public void onClose() {
         this.minecraft.setScreen(ConfirmExperimentalFeaturesScreen.this);
      }

      protected void init() {
         super.init();
         this.addRenderableWidget(Button.builder(CommonComponents.GUI_BACK, (p_251286_) -> {
            this.onClose();
         }).bounds(this.width / 2 - 100, this.height / 4 + 120 + 24, 200, 20).build());
         this.packList = new ConfirmExperimentalFeaturesScreen.DetailsScreen.PackList(this.minecraft, ConfirmExperimentalFeaturesScreen.this.enabledPacks);
         this.addWidget(this.packList);
      }

      public void render(PoseStack p_250580_, int p_248664_, int p_251176_, float p_252346_) {
         this.renderBackground(p_250580_);
         this.packList.render(p_250580_, p_248664_, p_251176_, p_252346_);
         drawCenteredString(p_250580_, this.font, this.title, this.width / 2, 10, 16777215);
         super.render(p_250580_, p_248664_, p_251176_, p_252346_);
      }

      @OnlyIn(Dist.CLIENT)
      class PackList extends ObjectSelectionList<ConfirmExperimentalFeaturesScreen.DetailsScreen.PackListEntry> {
         public PackList(Minecraft p_249776_, Collection<Pack> p_251183_) {
            super(p_249776_, DetailsScreen.this.width, DetailsScreen.this.height, 32, DetailsScreen.this.height - 64, (9 + 2) * 3);

            for(Pack pack : p_251183_) {
               String s = FeatureFlags.printMissingFlags(FeatureFlags.VANILLA_SET, pack.getRequestedFeatures());
               if (!s.isEmpty()) {
                  Component component = ComponentUtils.mergeStyles(pack.getTitle().copy(), Style.EMPTY.withBold(true));
                  Component component1 = Component.translatable("selectWorld.experimental.details.entry", s);
                  this.addEntry(DetailsScreen.this.new PackListEntry(component, component1, MultiLineLabel.create(DetailsScreen.this.font, component1, this.getRowWidth())));
               }
            }

         }

         public int getRowWidth() {
            return this.width * 3 / 4;
         }
      }

      @OnlyIn(Dist.CLIENT)
      class PackListEntry extends ObjectSelectionList.Entry<ConfirmExperimentalFeaturesScreen.DetailsScreen.PackListEntry> {
         private final Component packId;
         private final Component message;
         private final MultiLineLabel splitMessage;

         PackListEntry(Component p_250724_, Component p_248883_, MultiLineLabel p_250949_) {
            this.packId = p_250724_;
            this.message = p_248883_;
            this.splitMessage = p_250949_;
         }

         public void render(PoseStack p_251018_, int p_250518_, int p_252123_, int p_249560_, int p_251486_, int p_248887_, int p_251461_, int p_250408_, boolean p_249225_, float p_248795_) {
            GuiComponent.drawString(p_251018_, DetailsScreen.this.minecraft.font, this.packId, p_249560_, p_252123_, 16777215);
            this.splitMessage.renderLeftAligned(p_251018_, p_249560_, p_252123_ + 12, 9, 16777215);
         }

         public Component getNarration() {
            return Component.translatable("narrator.select", CommonComponents.joinForNarration(this.packId, this.message));
         }
      }
   }
}