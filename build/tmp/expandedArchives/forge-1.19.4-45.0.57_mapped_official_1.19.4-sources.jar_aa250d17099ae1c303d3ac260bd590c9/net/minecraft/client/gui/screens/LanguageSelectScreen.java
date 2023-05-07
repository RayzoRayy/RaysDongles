package net.minecraft.client.gui.screens;

import com.mojang.blaze3d.vertex.PoseStack;
import net.minecraft.ChatFormatting;
import net.minecraft.client.Minecraft;
import net.minecraft.client.Options;
import net.minecraft.client.gui.components.Button;
import net.minecraft.client.gui.components.ObjectSelectionList;
import net.minecraft.client.resources.language.LanguageInfo;
import net.minecraft.client.resources.language.LanguageManager;
import net.minecraft.network.chat.CommonComponents;
import net.minecraft.network.chat.Component;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

@OnlyIn(Dist.CLIENT)
public class LanguageSelectScreen extends OptionsSubScreen {
   private static final Component WARNING_LABEL = Component.literal("(").append(Component.translatable("options.languageWarning")).append(")").withStyle(ChatFormatting.GRAY);
   private LanguageSelectScreen.LanguageSelectionList packSelectionList;
   final LanguageManager languageManager;

   public LanguageSelectScreen(Screen p_96085_, Options p_96086_, LanguageManager p_96087_) {
      super(p_96085_, p_96086_, Component.translatable("options.language"));
      this.languageManager = p_96087_;
   }

   protected void init() {
      this.packSelectionList = new LanguageSelectScreen.LanguageSelectionList(this.minecraft);
      this.addWidget(this.packSelectionList);
      this.addRenderableWidget(this.options.forceUnicodeFont().createButton(this.options, this.width / 2 - 155, this.height - 38, 150));
      this.addRenderableWidget(Button.builder(CommonComponents.GUI_DONE, (p_264679_) -> {
         LanguageSelectScreen.LanguageSelectionList.Entry languageselectscreen$languageselectionlist$entry = this.packSelectionList.getSelected();
         if (languageselectscreen$languageselectionlist$entry != null && !languageselectscreen$languageselectionlist$entry.code.equals(this.languageManager.getSelected())) {
            this.languageManager.setSelected(languageselectscreen$languageselectionlist$entry.code);
            this.options.languageCode = languageselectscreen$languageselectionlist$entry.code;
            this.minecraft.reloadResourcePacks();
            this.options.save();
         }

         this.minecraft.setScreen(this.lastScreen);
      }).bounds(this.width / 2 - 155 + 160, this.height - 38, 150, 20).build());
      super.init();
   }

   public void render(PoseStack p_96089_, int p_96090_, int p_96091_, float p_96092_) {
      this.packSelectionList.render(p_96089_, p_96090_, p_96091_, p_96092_);
      drawCenteredString(p_96089_, this.font, this.title, this.width / 2, 16, 16777215);
      drawCenteredString(p_96089_, this.font, WARNING_LABEL, this.width / 2, this.height - 56, 8421504);
      super.render(p_96089_, p_96090_, p_96091_, p_96092_);
   }

   @OnlyIn(Dist.CLIENT)
   class LanguageSelectionList extends ObjectSelectionList<LanguageSelectScreen.LanguageSelectionList.Entry> {
      public LanguageSelectionList(Minecraft p_96103_) {
         super(p_96103_, LanguageSelectScreen.this.width, LanguageSelectScreen.this.height, 32, LanguageSelectScreen.this.height - 65 + 4, 18);
         String s = LanguageSelectScreen.this.languageManager.getSelected();
         LanguageSelectScreen.this.languageManager.getLanguages().forEach((p_265492_, p_265377_) -> {
            LanguageSelectScreen.LanguageSelectionList.Entry languageselectscreen$languageselectionlist$entry = new LanguageSelectScreen.LanguageSelectionList.Entry(p_265492_, p_265377_);
            this.addEntry(languageselectscreen$languageselectionlist$entry);
            if (s.equals(p_265492_)) {
               this.setSelected(languageselectscreen$languageselectionlist$entry);
            }

         });
         if (this.getSelected() != null) {
            this.centerScrollOn(this.getSelected());
         }

      }

      protected int getScrollbarPosition() {
         return super.getScrollbarPosition() + 20;
      }

      public int getRowWidth() {
         return super.getRowWidth() + 50;
      }

      protected void renderBackground(PoseStack p_96105_) {
         LanguageSelectScreen.this.renderBackground(p_96105_);
      }

      @OnlyIn(Dist.CLIENT)
      public class Entry extends ObjectSelectionList.Entry<LanguageSelectScreen.LanguageSelectionList.Entry> {
         final String code;
         private final Component language;

         public Entry(String p_265319_, LanguageInfo p_265357_) {
            this.code = p_265319_;
            this.language = p_265357_.toComponent();
         }

         public void render(PoseStack p_96126_, int p_96127_, int p_96128_, int p_96129_, int p_96130_, int p_96131_, int p_96132_, int p_96133_, boolean p_96134_, float p_96135_) {
            LanguageSelectScreen.this.font.drawShadow(p_96126_, this.language, (float)(LanguageSelectionList.this.width / 2 - LanguageSelectScreen.this.font.width(this.language) / 2), (float)(p_96128_ + 1), 16777215);
         }

         public boolean mouseClicked(double p_96122_, double p_96123_, int p_96124_) {
            if (p_96124_ == 0) {
               this.select();
               return true;
            } else {
               return false;
            }
         }

         private void select() {
            LanguageSelectionList.this.setSelected(this);
         }

         public Component getNarration() {
            return Component.translatable("narrator.select", this.language);
         }
      }
   }
}