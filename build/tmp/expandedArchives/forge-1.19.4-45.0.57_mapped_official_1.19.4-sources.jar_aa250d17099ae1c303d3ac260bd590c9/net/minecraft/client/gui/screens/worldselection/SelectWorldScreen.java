package net.minecraft.client.gui.screens.worldselection;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.logging.LogUtils;
import net.minecraft.client.gui.components.Button;
import net.minecraft.client.gui.components.EditBox;
import net.minecraft.client.gui.screens.Screen;
import net.minecraft.network.chat.CommonComponents;
import net.minecraft.network.chat.Component;
import net.minecraft.world.level.levelgen.WorldOptions;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import org.slf4j.Logger;

@OnlyIn(Dist.CLIENT)
public class SelectWorldScreen extends Screen {
   private static final Logger LOGGER = LogUtils.getLogger();
   public static final WorldOptions TEST_OPTIONS = new WorldOptions((long)"test1".hashCode(), true, false);
   protected final Screen lastScreen;
   private Button deleteButton;
   private Button selectButton;
   private Button renameButton;
   private Button copyButton;
   protected EditBox searchBox;
   private WorldSelectionList list;

   public SelectWorldScreen(Screen p_101338_) {
      super(Component.translatable("selectWorld.title"));
      this.lastScreen = p_101338_;
   }

   public boolean mouseScrolled(double p_101343_, double p_101344_, double p_101345_) {
      return super.mouseScrolled(p_101343_, p_101344_, p_101345_);
   }

   public void tick() {
      this.searchBox.tick();
   }

   protected void init() {
      this.searchBox = new EditBox(this.font, this.width / 2 - 100, 22, 200, 20, this.searchBox, Component.translatable("selectWorld.search"));
      this.searchBox.setResponder((p_232980_) -> {
         this.list.updateFilter(p_232980_);
      });
      this.list = new WorldSelectionList(this, this.minecraft, this.width, this.height, 48, this.height - 64, 36, this.searchBox.getValue(), this.list);
      this.addWidget(this.searchBox);
      this.addWidget(this.list);
      this.selectButton = this.addRenderableWidget(Button.builder(Component.translatable("selectWorld.select"), (p_232984_) -> {
         this.list.getSelectedOpt().ifPresent(WorldSelectionList.WorldListEntry::joinWorld);
      }).bounds(this.width / 2 - 154, this.height - 52, 150, 20).build());
      this.addRenderableWidget(Button.builder(Component.translatable("selectWorld.create"), (p_232982_) -> {
         CreateWorldScreen.openFresh(this.minecraft, this);
      }).bounds(this.width / 2 + 4, this.height - 52, 150, 20).build());
      this.renameButton = this.addRenderableWidget(Button.builder(Component.translatable("selectWorld.edit"), (p_101378_) -> {
         this.list.getSelectedOpt().ifPresent(WorldSelectionList.WorldListEntry::editWorld);
      }).bounds(this.width / 2 - 154, this.height - 28, 72, 20).build());
      this.deleteButton = this.addRenderableWidget(Button.builder(Component.translatable("selectWorld.delete"), (p_101376_) -> {
         this.list.getSelectedOpt().ifPresent(WorldSelectionList.WorldListEntry::deleteWorld);
      }).bounds(this.width / 2 - 76, this.height - 28, 72, 20).build());
      this.copyButton = this.addRenderableWidget(Button.builder(Component.translatable("selectWorld.recreate"), (p_101373_) -> {
         this.list.getSelectedOpt().ifPresent(WorldSelectionList.WorldListEntry::recreateWorld);
      }).bounds(this.width / 2 + 4, this.height - 28, 72, 20).build());
      this.addRenderableWidget(Button.builder(CommonComponents.GUI_CANCEL, (p_101366_) -> {
         this.minecraft.setScreen(this.lastScreen);
      }).bounds(this.width / 2 + 82, this.height - 28, 72, 20).build());
      this.updateButtonStatus(false, false);
      this.setInitialFocus(this.searchBox);
   }

   public boolean keyPressed(int p_101347_, int p_101348_, int p_101349_) {
      return super.keyPressed(p_101347_, p_101348_, p_101349_) ? true : this.searchBox.keyPressed(p_101347_, p_101348_, p_101349_);
   }

   public void onClose() {
      this.minecraft.setScreen(this.lastScreen);
   }

   public boolean charTyped(char p_101340_, int p_101341_) {
      return this.searchBox.charTyped(p_101340_, p_101341_);
   }

   public void render(PoseStack p_101351_, int p_101352_, int p_101353_, float p_101354_) {
      this.list.render(p_101351_, p_101352_, p_101353_, p_101354_);
      this.searchBox.render(p_101351_, p_101352_, p_101353_, p_101354_);
      drawCenteredString(p_101351_, this.font, this.title, this.width / 2, 8, 16777215);
      super.render(p_101351_, p_101352_, p_101353_, p_101354_);
   }

   public void updateButtonStatus(boolean p_276122_, boolean p_276113_) {
      this.selectButton.active = p_276122_;
      this.renameButton.active = p_276122_;
      this.copyButton.active = p_276122_;
      this.deleteButton.active = p_276113_;
   }

   public void removed() {
      if (this.list != null) {
         this.list.children().forEach(WorldSelectionList.Entry::close);
      }

   }
}