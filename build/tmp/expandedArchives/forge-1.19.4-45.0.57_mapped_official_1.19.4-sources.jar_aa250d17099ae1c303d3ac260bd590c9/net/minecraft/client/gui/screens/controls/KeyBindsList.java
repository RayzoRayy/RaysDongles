package net.minecraft.client.gui.screens.controls;

import com.google.common.collect.ImmutableList;
import com.mojang.blaze3d.vertex.PoseStack;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import javax.annotation.Nullable;
import net.minecraft.ChatFormatting;
import net.minecraft.client.KeyMapping;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.ComponentPath;
import net.minecraft.client.gui.GuiComponent;
import net.minecraft.client.gui.components.Button;
import net.minecraft.client.gui.components.ContainerObjectSelectionList;
import net.minecraft.client.gui.components.Tooltip;
import net.minecraft.client.gui.components.events.GuiEventListener;
import net.minecraft.client.gui.narration.NarratableEntry;
import net.minecraft.client.gui.narration.NarratedElementType;
import net.minecraft.client.gui.narration.NarrationElementOutput;
import net.minecraft.client.gui.navigation.FocusNavigationEvent;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.MutableComponent;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import org.apache.commons.lang3.ArrayUtils;

@OnlyIn(Dist.CLIENT)
public class KeyBindsList extends ContainerObjectSelectionList<KeyBindsList.Entry> {
   final KeyBindsScreen keyBindsScreen;
   int maxNameWidth;

   public KeyBindsList(KeyBindsScreen p_193861_, Minecraft p_193862_) {
      super(p_193862_, p_193861_.width + 45, p_193861_.height, 20, p_193861_.height - 32, 20);
      this.keyBindsScreen = p_193861_;
      KeyMapping[] akeymapping = ArrayUtils.clone((KeyMapping[])p_193862_.options.keyMappings);
      Arrays.sort((Object[])akeymapping);
      String s = null;

      for(KeyMapping keymapping : akeymapping) {
         String s1 = keymapping.getCategory();
         if (!s1.equals(s)) {
            s = s1;
            this.addEntry(new KeyBindsList.CategoryEntry(Component.translatable(s1)));
         }

         Component component = Component.translatable(keymapping.getName());
         int i = p_193862_.font.width(component);
         if (i > this.maxNameWidth) {
            this.maxNameWidth = i;
         }

         this.addEntry(new KeyBindsList.KeyEntry(keymapping, component));
      }

   }

   public void resetMappingAndUpdateButtons() {
      KeyMapping.resetMapping();
      this.refreshEntries();
   }

   public void refreshEntries() {
      this.children().forEach(KeyBindsList.Entry::refreshEntry);
   }

   protected int getScrollbarPosition() {
      return super.getScrollbarPosition() + 15 + 20;
   }

   public int getRowWidth() {
      return super.getRowWidth() + 32;
   }

   @OnlyIn(Dist.CLIENT)
   public class CategoryEntry extends KeyBindsList.Entry {
      final Component name;
      private final int width;

      public CategoryEntry(Component p_193886_) {
         this.name = p_193886_;
         this.width = KeyBindsList.this.minecraft.font.width(this.name);
      }

      public void render(PoseStack p_193888_, int p_193889_, int p_193890_, int p_193891_, int p_193892_, int p_193893_, int p_193894_, int p_193895_, boolean p_193896_, float p_193897_) {
         KeyBindsList.this.minecraft.font.draw(p_193888_, this.name, (float)(KeyBindsList.this.minecraft.screen.width / 2 - this.width / 2), (float)(p_193890_ + p_193893_ - 9 - 1), 16777215);
      }

      @Nullable
      public ComponentPath nextFocusPath(FocusNavigationEvent p_265391_) {
         return null;
      }

      public List<? extends GuiEventListener> children() {
         return Collections.emptyList();
      }

      public List<? extends NarratableEntry> narratables() {
         return ImmutableList.of(new NarratableEntry() {
            public NarratableEntry.NarrationPriority narrationPriority() {
               return NarratableEntry.NarrationPriority.HOVERED;
            }

            public void updateNarration(NarrationElementOutput p_193906_) {
               p_193906_.add(NarratedElementType.TITLE, CategoryEntry.this.name);
            }
         });
      }

      protected void refreshEntry() {
      }
   }

   @OnlyIn(Dist.CLIENT)
   public abstract static class Entry extends ContainerObjectSelectionList.Entry<KeyBindsList.Entry> {
      abstract void refreshEntry();
   }

   @OnlyIn(Dist.CLIENT)
   public class KeyEntry extends KeyBindsList.Entry {
      private final KeyMapping key;
      private final Component name;
      private final Button changeButton;
      private final Button resetButton;
      private boolean hasCollision = false;

      KeyEntry(KeyMapping p_193916_, Component p_193917_) {
         this.key = p_193916_;
         this.name = p_193917_;
         this.changeButton = Button.builder(p_193917_, (p_269618_) -> {
            KeyBindsList.this.keyBindsScreen.selectedKey = p_193916_;
            KeyBindsList.this.resetMappingAndUpdateButtons();
         }).bounds(0, 0, 75 + 20 /* Forge: Add space */, 20).createNarration((p_253311_) -> {
            return p_193916_.isUnbound() ? Component.translatable("narrator.controls.unbound", p_193917_) : Component.translatable("narrator.controls.bound", p_193917_, p_253311_.get());
         }).build();
         this.resetButton = Button.builder(Component.translatable("controls.reset"), (p_269616_) -> {
            this.key.setToDefault();
            KeyBindsList.this.minecraft.options.setKey(p_193916_, p_193916_.getDefaultKey());
            KeyBindsList.this.resetMappingAndUpdateButtons();
         }).bounds(0, 0, 50, 20).createNarration((p_253313_) -> {
            return Component.translatable("narrator.controls.reset", p_193917_);
         }).build();
         this.refreshEntry();
      }

      public void render(PoseStack p_193923_, int p_193924_, int p_193925_, int p_193926_, int p_193927_, int p_193928_, int p_193929_, int p_193930_, boolean p_193931_, float p_193932_) {
         float f = (float)(p_193926_ + 90 - KeyBindsList.this.maxNameWidth);
         KeyBindsList.this.minecraft.font.draw(p_193923_, this.name, f, (float)(p_193925_ + p_193928_ / 2 - 9 / 2), 16777215);
         this.resetButton.setX(p_193926_ + 190 + 20);
         this.resetButton.setY(p_193925_);
         this.resetButton.render(p_193923_, p_193929_, p_193930_, p_193932_);
         this.changeButton.setX(p_193926_ + 105);
         this.changeButton.setY(p_193925_);
         if (this.hasCollision) {
            int i = 3;
            int j = this.changeButton.getX() - 6;
            GuiComponent.fill(p_193923_, j, p_193925_ + 2, j + 3, p_193925_ + p_193928_ + 2, ChatFormatting.RED.getColor() | -16777216);
         }

         this.changeButton.render(p_193923_, p_193929_, p_193930_, p_193932_);
      }

      public List<? extends GuiEventListener> children() {
         return ImmutableList.of(this.changeButton, this.resetButton);
      }

      public List<? extends NarratableEntry> narratables() {
         return ImmutableList.of(this.changeButton, this.resetButton);
      }

      protected void refreshEntry() {
         this.changeButton.setMessage(this.key.getTranslatedKeyMessage());
         this.resetButton.active = !this.key.isDefault();
         this.hasCollision = false;
         MutableComponent mutablecomponent = Component.empty();
         if (!this.key.isUnbound()) {
            for(KeyMapping keymapping : KeyBindsList.this.minecraft.options.keyMappings) {
               if ((keymapping != this.key && this.key.same(keymapping)) || keymapping.hasKeyModifierConflict(this.key)) { // FORGE: gracefully handle conflicts like SHIFT vs SHIFT+G
                  if (this.hasCollision) {
                     mutablecomponent.append(", ");
                  }

                  this.hasCollision = true;
                  mutablecomponent.append(Component.translatable(keymapping.getName()));
               }
            }
         }

         if (this.hasCollision) {
            this.changeButton.setMessage(Component.literal("[ ").append(this.changeButton.getMessage().copy().withStyle(ChatFormatting.WHITE)).append(" ]").withStyle(ChatFormatting.RED));
            this.changeButton.setTooltip(Tooltip.create(Component.translatable("controls.keybinds.duplicateKeybinds", mutablecomponent)));
         } else {
            this.changeButton.setTooltip((Tooltip)null);
         }

         if (KeyBindsList.this.keyBindsScreen.selectedKey == this.key) {
            this.changeButton.setMessage(Component.literal("> ").append(this.changeButton.getMessage().copy().withStyle(ChatFormatting.WHITE, ChatFormatting.UNDERLINE)).append(" <").withStyle(ChatFormatting.YELLOW));
         }

      }
   }
}
