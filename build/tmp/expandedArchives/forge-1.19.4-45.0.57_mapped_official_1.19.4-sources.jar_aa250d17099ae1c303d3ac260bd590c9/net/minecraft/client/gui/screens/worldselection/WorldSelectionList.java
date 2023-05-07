package net.minecraft.client.gui.screens.worldselection;

import com.google.common.base.Preconditions;
import com.google.common.collect.ImmutableList;
import com.google.common.hash.Hashing;
import com.mojang.blaze3d.platform.NativeImage;
import com.mojang.blaze3d.systems.RenderSystem;
import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.datafixers.util.Pair;
import com.mojang.logging.LogUtils;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.Optional;
import java.util.concurrent.CancellationException;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.CompletionException;
import javax.annotation.Nullable;
import net.minecraft.ChatFormatting;
import net.minecraft.CrashReport;
import net.minecraft.SharedConstants;
import net.minecraft.Util;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiComponent;
import net.minecraft.client.gui.components.ObjectSelectionList;
import net.minecraft.client.gui.components.toasts.SystemToast;
import net.minecraft.client.gui.narration.NarrationElementOutput;
import net.minecraft.client.gui.screens.AlertScreen;
import net.minecraft.client.gui.screens.BackupConfirmScreen;
import net.minecraft.client.gui.screens.ConfirmScreen;
import net.minecraft.client.gui.screens.ErrorScreen;
import net.minecraft.client.gui.screens.GenericDirtMessageScreen;
import net.minecraft.client.gui.screens.LoadingDotsText;
import net.minecraft.client.gui.screens.ProgressScreen;
import net.minecraft.client.gui.screens.Screen;
import net.minecraft.client.renderer.texture.DynamicTexture;
import net.minecraft.client.resources.language.I18n;
import net.minecraft.client.resources.sounds.SimpleSoundInstance;
import net.minecraft.network.chat.CommonComponents;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.MutableComponent;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.world.level.LevelSettings;
import net.minecraft.world.level.storage.LevelResource;
import net.minecraft.world.level.storage.LevelStorageException;
import net.minecraft.world.level.storage.LevelStorageSource;
import net.minecraft.world.level.storage.LevelSummary;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;

@OnlyIn(Dist.CLIENT)
public class WorldSelectionList extends ObjectSelectionList<WorldSelectionList.Entry> {
   static final Logger LOGGER = LogUtils.getLogger();
   static final DateFormat DATE_FORMAT = new SimpleDateFormat();
   static final ResourceLocation ICON_MISSING = new ResourceLocation("textures/misc/unknown_server.png");
   static final ResourceLocation ICON_OVERLAY_LOCATION = new ResourceLocation("textures/gui/world_selection.png");
   private static final ResourceLocation FORGE_EXPERIMENTAL_WARNING_ICON = new ResourceLocation("forge","textures/gui/experimental_warning.png");
   static final Component FROM_NEWER_TOOLTIP_1 = Component.translatable("selectWorld.tooltip.fromNewerVersion1").withStyle(ChatFormatting.RED);
   static final Component FROM_NEWER_TOOLTIP_2 = Component.translatable("selectWorld.tooltip.fromNewerVersion2").withStyle(ChatFormatting.RED);
   static final Component SNAPSHOT_TOOLTIP_1 = Component.translatable("selectWorld.tooltip.snapshot1").withStyle(ChatFormatting.GOLD);
   static final Component SNAPSHOT_TOOLTIP_2 = Component.translatable("selectWorld.tooltip.snapshot2").withStyle(ChatFormatting.GOLD);
   static final Component WORLD_LOCKED_TOOLTIP = Component.translatable("selectWorld.locked").withStyle(ChatFormatting.RED);
   static final Component WORLD_REQUIRES_CONVERSION = Component.translatable("selectWorld.conversion.tooltip").withStyle(ChatFormatting.RED);
   private final SelectWorldScreen screen;
   private CompletableFuture<List<LevelSummary>> pendingLevels;
   @Nullable
   private List<LevelSummary> currentlyDisplayedLevels;
   private String filter;
   private final WorldSelectionList.LoadingHeader loadingHeader;

   public WorldSelectionList(SelectWorldScreen p_239540_, Minecraft p_239541_, int p_239542_, int p_239543_, int p_239544_, int p_239545_, int p_239546_, String p_239547_, @Nullable WorldSelectionList p_239548_) {
      super(p_239541_, p_239542_, p_239543_, p_239544_, p_239545_, p_239546_);
      this.screen = p_239540_;
      this.loadingHeader = new WorldSelectionList.LoadingHeader(p_239541_);
      this.filter = p_239547_;
      if (p_239548_ != null) {
         this.pendingLevels = p_239548_.pendingLevels;
      } else {
         this.pendingLevels = this.loadLevels();
      }

      this.handleNewLevels(this.pollLevelsIgnoreErrors());
   }

   @Nullable
   private List<LevelSummary> pollLevelsIgnoreErrors() {
      try {
         return this.pendingLevels.getNow((List<LevelSummary>)null);
      } catch (CancellationException | CompletionException completionexception) {
         return null;
      }
   }

   void reloadWorldList() {
      this.pendingLevels = this.loadLevels();
   }

   public void render(PoseStack p_239123_, int p_239124_, int p_239125_, float p_239126_) {
      List<LevelSummary> list = this.pollLevelsIgnoreErrors();
      if (list != this.currentlyDisplayedLevels) {
         this.handleNewLevels(list);
      }

      super.render(p_239123_, p_239124_, p_239125_, p_239126_);
   }

   private void handleNewLevels(@Nullable List<LevelSummary> p_239665_) {
      if (p_239665_ == null) {
         this.fillLoadingLevels();
      } else {
         this.fillLevels(this.filter, p_239665_);
      }

      this.currentlyDisplayedLevels = p_239665_;
   }

   public void updateFilter(String p_239901_) {
      if (this.currentlyDisplayedLevels != null && !p_239901_.equals(this.filter)) {
         this.fillLevels(p_239901_, this.currentlyDisplayedLevels);
      }

      this.filter = p_239901_;
   }

   private CompletableFuture<List<LevelSummary>> loadLevels() {
      LevelStorageSource.LevelCandidates levelstoragesource$levelcandidates;
      try {
         levelstoragesource$levelcandidates = this.minecraft.getLevelSource().findLevelCandidates();
      } catch (LevelStorageException levelstorageexception) {
         LOGGER.error("Couldn't load level list", (Throwable)levelstorageexception);
         this.handleLevelLoadFailure(levelstorageexception.getMessageComponent());
         return CompletableFuture.completedFuture(List.of());
      }

      if (levelstoragesource$levelcandidates.isEmpty()) {
         CreateWorldScreen.openFresh(this.minecraft, (Screen)null);
         return CompletableFuture.completedFuture(List.of());
      } else {
         return this.minecraft.getLevelSource().loadLevelSummaries(levelstoragesource$levelcandidates).exceptionally((p_233202_) -> {
            this.minecraft.delayCrash(CrashReport.forThrowable(p_233202_, "Couldn't load level list"));
            return List.of();
         });
      }
   }

   private void fillLevels(String p_233199_, List<LevelSummary> p_233200_) {
      this.clearEntries();
      p_233199_ = p_233199_.toLowerCase(Locale.ROOT);

      for(LevelSummary levelsummary : p_233200_) {
         if (this.filterAccepts(p_233199_, levelsummary)) {
            this.addEntry(new WorldSelectionList.WorldListEntry(this, levelsummary));
         }
      }

      this.notifyListUpdated();
   }

   private boolean filterAccepts(String p_233196_, LevelSummary p_233197_) {
      return p_233197_.getLevelName().toLowerCase(Locale.ROOT).contains(p_233196_) || p_233197_.getLevelId().toLowerCase(Locale.ROOT).contains(p_233196_);
   }

   private void fillLoadingLevels() {
      this.clearEntries();
      this.addEntry(this.loadingHeader);
      this.notifyListUpdated();
   }

   private void notifyListUpdated() {
      this.screen.triggerImmediateNarration(true);
   }

   private void handleLevelLoadFailure(Component p_233212_) {
      this.minecraft.setScreen(new ErrorScreen(Component.translatable("selectWorld.unable_to_load"), p_233212_));
   }

   protected int getScrollbarPosition() {
      return super.getScrollbarPosition() + 20;
   }

   public int getRowWidth() {
      return super.getRowWidth() + 50;
   }

   public void setSelected(@Nullable WorldSelectionList.Entry p_233190_) {
      super.setSelected(p_233190_);
      this.screen.updateButtonStatus(p_233190_ != null && p_233190_.isSelectable(), p_233190_ != null);
   }

   public Optional<WorldSelectionList.WorldListEntry> getSelectedOpt() {
      WorldSelectionList.Entry worldselectionlist$entry = this.getSelected();
      if (worldselectionlist$entry instanceof WorldSelectionList.WorldListEntry worldselectionlist$worldlistentry) {
         return Optional.of(worldselectionlist$worldlistentry);
      } else {
         return Optional.empty();
      }
   }

   public SelectWorldScreen getScreen() {
      return this.screen;
   }

   public void updateNarration(NarrationElementOutput p_233188_) {
      if (this.children().contains(this.loadingHeader)) {
         this.loadingHeader.updateNarration(p_233188_);
      } else {
         super.updateNarration(p_233188_);
      }
   }

   @OnlyIn(Dist.CLIENT)
   public abstract static class Entry extends ObjectSelectionList.Entry<WorldSelectionList.Entry> implements AutoCloseable {
      public abstract boolean isSelectable();

      public void close() {
      }
   }

   @OnlyIn(Dist.CLIENT)
   public static class LoadingHeader extends WorldSelectionList.Entry {
      private static final Component LOADING_LABEL = Component.translatable("selectWorld.loading_list");
      private final Minecraft minecraft;

      public LoadingHeader(Minecraft p_233222_) {
         this.minecraft = p_233222_;
      }

      public void render(PoseStack p_233225_, int p_233226_, int p_233227_, int p_233228_, int p_233229_, int p_233230_, int p_233231_, int p_233232_, boolean p_233233_, float p_233234_) {
         int i = (this.minecraft.screen.width - this.minecraft.font.width(LOADING_LABEL)) / 2;
         int j = p_233227_ + (p_233230_ - 9) / 2;
         this.minecraft.font.draw(p_233225_, LOADING_LABEL, (float)i, (float)j, 16777215);
         String s = LoadingDotsText.get(Util.getMillis());
         int k = (this.minecraft.screen.width - this.minecraft.font.width(s)) / 2;
         int l = j + 9;
         this.minecraft.font.draw(p_233225_, s, (float)k, (float)l, 8421504);
      }

      public Component getNarration() {
         return LOADING_LABEL;
      }

      public boolean isSelectable() {
         return false;
      }
   }

   @OnlyIn(Dist.CLIENT)
   public final class WorldListEntry extends WorldSelectionList.Entry implements AutoCloseable {
      private static final int ICON_WIDTH = 32;
      private static final int ICON_HEIGHT = 32;
      private static final int ICON_OVERLAY_X_JOIN = 0;
      private static final int ICON_OVERLAY_X_JOIN_WITH_NOTIFY = 32;
      private static final int ICON_OVERLAY_X_WARNING = 64;
      private static final int ICON_OVERLAY_X_ERROR = 96;
      private static final int ICON_OVERLAY_Y_UNSELECTED = 0;
      private static final int ICON_OVERLAY_Y_SELECTED = 32;
      private final Minecraft minecraft;
      private final SelectWorldScreen screen;
      private final LevelSummary summary;
      private final ResourceLocation iconLocation;
      @Nullable
      private Path iconFile;
      @Nullable
      private final DynamicTexture icon;
      private long lastClickTime;

      public WorldListEntry(WorldSelectionList p_101702_, LevelSummary p_101703_) {
         this.minecraft = p_101702_.minecraft;
         this.screen = p_101702_.getScreen();
         this.summary = p_101703_;
         String s = p_101703_.getLevelId();
         this.iconLocation = new ResourceLocation("minecraft", "worlds/" + Util.sanitizeName(s, ResourceLocation::validPathChar) + "/" + Hashing.sha1().hashUnencodedChars(s) + "/icon");
         this.iconFile = p_101703_.getIcon();
         if (!Files.isRegularFile(this.iconFile)) {
            this.iconFile = null;
         }

         this.icon = this.loadServerIcon();
      }

      public Component getNarration() {
         Component component = Component.translatable("narrator.select.world", this.summary.getLevelName(), new Date(this.summary.getLastPlayed()), this.summary.isHardcore() ? Component.translatable("gameMode.hardcore") : Component.translatable("gameMode." + this.summary.getGameMode().getName()), this.summary.hasCheats() ? Component.translatable("selectWorld.cheats") : CommonComponents.EMPTY, this.summary.getWorldVersionName());
         Component component1;
         if (this.summary.isLocked()) {
            component1 = CommonComponents.joinForNarration(component, WorldSelectionList.WORLD_LOCKED_TOOLTIP);
         } else {
            component1 = component;
         }

         return Component.translatable("narrator.select", component1);
      }

      public void render(PoseStack p_101721_, int p_101722_, int p_101723_, int p_101724_, int p_101725_, int p_101726_, int p_101727_, int p_101728_, boolean p_101729_, float p_101730_) {
         String s = this.summary.getLevelName();
         String s1 = this.summary.getLevelId() + " (" + WorldSelectionList.DATE_FORMAT.format(new Date(this.summary.getLastPlayed())) + ")";
         if (StringUtils.isEmpty(s)) {
            s = I18n.get("selectWorld.world") + " " + (p_101722_ + 1);
         }

         Component component = this.summary.getInfo();
         this.minecraft.font.draw(p_101721_, s, (float)(p_101724_ + 32 + 3), (float)(p_101723_ + 1), 16777215);
         this.minecraft.font.draw(p_101721_, s1, (float)(p_101724_ + 32 + 3), (float)(p_101723_ + 9 + 3), 8421504);
         this.minecraft.font.draw(p_101721_, component, (float)(p_101724_ + 32 + 3), (float)(p_101723_ + 9 + 9 + 3), 8421504);
         RenderSystem.setShaderTexture(0, this.icon != null ? this.iconLocation : WorldSelectionList.ICON_MISSING);
         RenderSystem.enableBlend();
         GuiComponent.blit(p_101721_, p_101724_, p_101723_, 0.0F, 0.0F, 32, 32, 32, 32);
         RenderSystem.disableBlend();
         renderExperimentalWarning(p_101721_, p_101727_, p_101728_, p_101723_, p_101724_);
         if (this.minecraft.options.touchscreen().get() || p_101729_) {
            RenderSystem.setShaderTexture(0, WorldSelectionList.ICON_OVERLAY_LOCATION);
            GuiComponent.fill(p_101721_, p_101724_, p_101723_, p_101724_ + 32, p_101723_ + 32, -1601138544);
            int i = p_101727_ - p_101724_;
            boolean flag = i < 32;
            int j = flag ? 32 : 0;
            if (this.summary.isLocked()) {
               GuiComponent.blit(p_101721_, p_101724_, p_101723_, 96.0F, (float)j, 32, 32, 256, 256);
               if (flag) {
                  this.screen.setTooltipForNextRenderPass(this.minecraft.font.split(WorldSelectionList.WORLD_LOCKED_TOOLTIP, 175));
               }
            } else if (this.summary.requiresManualConversion()) {
               GuiComponent.blit(p_101721_, p_101724_, p_101723_, 96.0F, (float)j, 32, 32, 256, 256);
               if (flag) {
                  this.screen.setTooltipForNextRenderPass(this.minecraft.font.split(WorldSelectionList.WORLD_REQUIRES_CONVERSION, 175));
               }
            } else if (this.summary.markVersionInList()) {
               GuiComponent.blit(p_101721_, p_101724_, p_101723_, 32.0F, (float)j, 32, 32, 256, 256);
               if (this.summary.askToOpenWorld()) {
                  GuiComponent.blit(p_101721_, p_101724_, p_101723_, 96.0F, (float)j, 32, 32, 256, 256);
                  if (flag) {
                     this.screen.setTooltipForNextRenderPass(ImmutableList.of(WorldSelectionList.FROM_NEWER_TOOLTIP_1.getVisualOrderText(), WorldSelectionList.FROM_NEWER_TOOLTIP_2.getVisualOrderText()));
                  }
               } else if (!SharedConstants.getCurrentVersion().isStable()) {
                  GuiComponent.blit(p_101721_, p_101724_, p_101723_, 64.0F, (float)j, 32, 32, 256, 256);
                  if (flag) {
                     this.screen.setTooltipForNextRenderPass(ImmutableList.of(WorldSelectionList.SNAPSHOT_TOOLTIP_1.getVisualOrderText(), WorldSelectionList.SNAPSHOT_TOOLTIP_2.getVisualOrderText()));
                  }
               }
            } else {
               GuiComponent.blit(p_101721_, p_101724_, p_101723_, 0.0F, (float)j, 32, 32, 256, 256);
            }
         }

      }

      public boolean mouseClicked(double p_101706_, double p_101707_, int p_101708_) {
         if (this.summary.isDisabled()) {
            return true;
         } else {
            WorldSelectionList.this.setSelected((WorldSelectionList.Entry)this);
            if (p_101706_ - (double)WorldSelectionList.this.getRowLeft() <= 32.0D) {
               this.joinWorld();
               return true;
            } else if (Util.getMillis() - this.lastClickTime < 250L) {
               this.joinWorld();
               return true;
            } else {
               this.lastClickTime = Util.getMillis();
               return true;
            }
         }
      }

      public void joinWorld() {
         if (!this.summary.isDisabled()) {
            LevelSummary.BackupStatus levelsummary$backupstatus = this.summary.backupStatus();
            if (levelsummary$backupstatus.shouldBackup()) {
               String s = "selectWorld.backupQuestion." + levelsummary$backupstatus.getTranslationKey();
               String s1 = "selectWorld.backupWarning." + levelsummary$backupstatus.getTranslationKey();
               MutableComponent mutablecomponent = Component.translatable(s);
               if (levelsummary$backupstatus.isSevere()) {
                  mutablecomponent.withStyle(ChatFormatting.BOLD, ChatFormatting.RED);
               }

               Component component = Component.translatable(s1, this.summary.getWorldVersionName(), SharedConstants.getCurrentVersion().getName());
               this.minecraft.setScreen(new BackupConfirmScreen(this.screen, (p_101736_, p_101737_) -> {
                  if (p_101736_) {
                     String s2 = this.summary.getLevelId();

                     try (LevelStorageSource.LevelStorageAccess levelstoragesource$levelstorageaccess = this.minecraft.getLevelSource().createAccess(s2)) {
                        EditWorldScreen.makeBackupAndShowToast(levelstoragesource$levelstorageaccess);
                     } catch (IOException ioexception) {
                        SystemToast.onWorldAccessFailure(this.minecraft, s2);
                        WorldSelectionList.LOGGER.error("Failed to backup level {}", s2, ioexception);
                     }
                  }

                  this.loadWorld();
               }, mutablecomponent, component, false));
            } else if (this.summary.askToOpenWorld()) {
               this.minecraft.setScreen(new ConfirmScreen((p_101741_) -> {
                  if (p_101741_) {
                     try {
                        this.loadWorld();
                     } catch (Exception exception) {
                        WorldSelectionList.LOGGER.error("Failure to open 'future world'", (Throwable)exception);
                        this.minecraft.setScreen(new AlertScreen(() -> {
                           this.minecraft.setScreen(this.screen);
                        }, Component.translatable("selectWorld.futureworld.error.title"), Component.translatable("selectWorld.futureworld.error.text")));
                     }
                  } else {
                     this.minecraft.setScreen(this.screen);
                  }

               }, Component.translatable("selectWorld.versionQuestion"), Component.translatable("selectWorld.versionWarning", this.summary.getWorldVersionName()), Component.translatable("selectWorld.versionJoinButton"), CommonComponents.GUI_CANCEL));
            } else {
               this.loadWorld();
            }

         }
      }

      public void deleteWorld() {
         this.minecraft.setScreen(new ConfirmScreen((p_170322_) -> {
            if (p_170322_) {
               this.minecraft.setScreen(new ProgressScreen(true));
               this.doDeleteWorld();
            }

            this.minecraft.setScreen(this.screen);
         }, Component.translatable("selectWorld.deleteQuestion"), Component.translatable("selectWorld.deleteWarning", this.summary.getLevelName()), Component.translatable("selectWorld.deleteButton"), CommonComponents.GUI_CANCEL));
      }

      public void doDeleteWorld() {
         LevelStorageSource levelstoragesource = this.minecraft.getLevelSource();
         String s = this.summary.getLevelId();

         try (LevelStorageSource.LevelStorageAccess levelstoragesource$levelstorageaccess = levelstoragesource.createAccess(s)) {
            levelstoragesource$levelstorageaccess.deleteLevel();
         } catch (IOException ioexception) {
            SystemToast.onWorldDeleteFailure(this.minecraft, s);
            WorldSelectionList.LOGGER.error("Failed to delete world {}", s, ioexception);
         }

         WorldSelectionList.this.reloadWorldList();
      }

      public void editWorld() {
         this.queueLoadScreen();
         String s = this.summary.getLevelId();

         try {
            LevelStorageSource.LevelStorageAccess levelstoragesource$levelstorageaccess = this.minecraft.getLevelSource().createAccess(s);
            this.minecraft.setScreen(new EditWorldScreen((p_233244_) -> {
               try {
                  levelstoragesource$levelstorageaccess.close();
               } catch (IOException ioexception1) {
                  WorldSelectionList.LOGGER.error("Failed to unlock level {}", s, ioexception1);
               }

               if (p_233244_) {
                  WorldSelectionList.this.reloadWorldList();
               }

               this.minecraft.setScreen(this.screen);
            }, levelstoragesource$levelstorageaccess));
         } catch (IOException ioexception) {
            SystemToast.onWorldAccessFailure(this.minecraft, s);
            WorldSelectionList.LOGGER.error("Failed to access level {}", s, ioexception);
            WorldSelectionList.this.reloadWorldList();
         }

      }

      public void recreateWorld() {
         this.queueLoadScreen();

         try (LevelStorageSource.LevelStorageAccess levelstoragesource$levelstorageaccess = this.minecraft.getLevelSource().createAccess(this.summary.getLevelId())) {
            Pair<LevelSettings, WorldCreationContext> pair = this.minecraft.createWorldOpenFlows().recreateWorldData(levelstoragesource$levelstorageaccess);
            LevelSettings levelsettings = pair.getFirst();
            WorldCreationContext worldcreationcontext = pair.getSecond();
            Path path = CreateWorldScreen.createTempDataPackDirFromExistingWorld(levelstoragesource$levelstorageaccess.getLevelPath(LevelResource.DATAPACK_DIR), this.minecraft);
            if (worldcreationcontext.options().isOldCustomizedWorld()) {
               this.minecraft.setScreen(new ConfirmScreen((p_275882_) -> {
                  this.minecraft.setScreen((Screen)(p_275882_ ? CreateWorldScreen.createFromExisting(this.minecraft, this.screen, levelsettings, worldcreationcontext, path) : this.screen));
               }, Component.translatable("selectWorld.recreate.customized.title"), Component.translatable("selectWorld.recreate.customized.text"), CommonComponents.GUI_PROCEED, CommonComponents.GUI_CANCEL));
            } else {
               this.minecraft.setScreen(CreateWorldScreen.createFromExisting(this.minecraft, this.screen, levelsettings, worldcreationcontext, path));
            }
         } catch (Exception exception) {
            WorldSelectionList.LOGGER.error("Unable to recreate world", (Throwable)exception);
            this.minecraft.setScreen(new AlertScreen(() -> {
               this.minecraft.setScreen(this.screen);
            }, Component.translatable("selectWorld.recreate.error.title"), Component.translatable("selectWorld.recreate.error.text")));
         }

      }

      private void loadWorld() {
         this.minecraft.getSoundManager().play(SimpleSoundInstance.forUI(SoundEvents.UI_BUTTON_CLICK, 1.0F));
         if (this.minecraft.getLevelSource().levelExists(this.summary.getLevelId())) {
            this.queueLoadScreen();
            this.minecraft.createWorldOpenFlows().loadLevel(this.screen, this.summary.getLevelId());
         }

      }

      private void queueLoadScreen() {
         this.minecraft.forceSetScreen(new GenericDirtMessageScreen(Component.translatable("selectWorld.data_read")));
      }

      @Nullable
      private DynamicTexture loadServerIcon() {
         boolean flag = this.iconFile != null && Files.isRegularFile(this.iconFile);
         if (flag) {
            try {
               DynamicTexture dynamictexture1;
               try (InputStream inputstream = Files.newInputStream(this.iconFile)) {
                  NativeImage nativeimage = NativeImage.read(inputstream);
                  Preconditions.checkState(nativeimage.getWidth() == 64, "Must be 64 pixels wide");
                  Preconditions.checkState(nativeimage.getHeight() == 64, "Must be 64 pixels high");
                  DynamicTexture dynamictexture = new DynamicTexture(nativeimage);
                  this.minecraft.getTextureManager().register(this.iconLocation, dynamictexture);
                  dynamictexture1 = dynamictexture;
               }

               return dynamictexture1;
            } catch (Throwable throwable1) {
               WorldSelectionList.LOGGER.error("Invalid icon for world {}", this.summary.getLevelId(), throwable1);
               this.iconFile = null;
               return null;
            }
         } else {
            this.minecraft.getTextureManager().release(this.iconLocation);
            return null;
         }
      }

      public void close() {
         if (this.icon != null) {
            this.icon.close();
         }

      }

      public String getLevelName() {
         return this.summary.getLevelName();
      }

      public boolean isSelectable() {
         return !this.summary.isDisabled();
      }

      // FORGE: Patch in experimental warning icon for worlds in the world selection screen
      private void renderExperimentalWarning(PoseStack stack, int mouseX, int mouseY, int top, int left) {
         if (this.summary.isLifecycleExperimental()) {
            int leftStart = left + WorldSelectionList.this.getRowWidth();
            RenderSystem.setShaderTexture(0, WorldSelectionList.FORGE_EXPERIMENTAL_WARNING_ICON);
            GuiComponent.blit(stack, leftStart - 36, top, 0.0F, 0.0F, 32, 32, 32, 32);
            // Reset texture to what it was before
            RenderSystem.setShaderTexture(0, this.icon != null ? this.iconLocation : WorldSelectionList.ICON_MISSING);
            if (WorldSelectionList.this.getEntryAtPosition(mouseX, mouseY) == this && mouseX > leftStart - 36 && mouseX < leftStart) {
               List<net.minecraft.util.FormattedCharSequence> tooltip = Minecraft.getInstance().font.split(Component.translatable("forge.experimentalsettings.tooltip"), 200);
               WorldSelectionList.this.screen.renderTooltip(stack, tooltip, mouseX, mouseY);
            }
         }
      }
   }
}
