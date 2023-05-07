package net.minecraft.client.renderer.entity;

import com.google.common.collect.Sets;
import com.mojang.blaze3d.platform.Lighting;
import com.mojang.blaze3d.systems.RenderSystem;
import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.SheetedDecalTextureGenerator;
import com.mojang.blaze3d.vertex.Tesselator;
import com.mojang.blaze3d.vertex.VertexConsumer;
import com.mojang.blaze3d.vertex.VertexMultiConsumer;
import com.mojang.math.MatrixUtil;
import java.util.List;
import java.util.Set;
import javax.annotation.Nullable;
import net.minecraft.CrashReport;
import net.minecraft.CrashReportCategory;
import net.minecraft.ReportedException;
import net.minecraft.client.Minecraft;
import net.minecraft.client.color.item.ItemColors;
import net.minecraft.client.gui.Font;
import net.minecraft.client.gui.GuiComponent;
import net.minecraft.client.multiplayer.ClientLevel;
import net.minecraft.client.player.LocalPlayer;
import net.minecraft.client.renderer.BlockEntityWithoutLevelRenderer;
import net.minecraft.client.renderer.ItemBlockRenderTypes;
import net.minecraft.client.renderer.ItemModelShaper;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.Sheets;
import net.minecraft.client.renderer.block.model.BakedQuad;
import net.minecraft.client.renderer.texture.OverlayTexture;
import net.minecraft.client.renderer.texture.TextureManager;
import net.minecraft.client.resources.model.BakedModel;
import net.minecraft.client.resources.model.ModelManager;
import net.minecraft.client.resources.model.ModelResourceLocation;
import net.minecraft.core.Direction;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.packs.resources.ResourceManager;
import net.minecraft.server.packs.resources.ResourceManagerReloadListener;
import net.minecraft.tags.ItemTags;
import net.minecraft.util.Mth;
import net.minecraft.util.RandomSource;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemDisplayContext;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.HalfTransparentBlock;
import net.minecraft.world.level.block.StainedGlassPaneBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import org.joml.Matrix4f;

@OnlyIn(Dist.CLIENT)
public class ItemRenderer implements ResourceManagerReloadListener {
   public static final ResourceLocation ENCHANTED_GLINT_ENTITY = new ResourceLocation("textures/misc/enchanted_glint_entity.png");
   public static final ResourceLocation ENCHANTED_GLINT_ITEM = new ResourceLocation("textures/misc/enchanted_glint_item.png");
   private static final Set<Item> IGNORED = Sets.newHashSet(Items.AIR);
   private static final int GUI_SLOT_CENTER_X = 8;
   private static final int GUI_SLOT_CENTER_Y = 8;
   public static final int ITEM_COUNT_BLIT_OFFSET = 200;
   public static final float COMPASS_FOIL_UI_SCALE = 0.5F;
   public static final float COMPASS_FOIL_FIRST_PERSON_SCALE = 0.75F;
   public static final float COMPASS_FOIL_TEXTURE_SCALE = 0.0078125F;
   private static final ModelResourceLocation TRIDENT_MODEL = ModelResourceLocation.vanilla("trident", "inventory");
   public static final ModelResourceLocation TRIDENT_IN_HAND_MODEL = ModelResourceLocation.vanilla("trident_in_hand", "inventory");
   private static final ModelResourceLocation SPYGLASS_MODEL = ModelResourceLocation.vanilla("spyglass", "inventory");
   public static final ModelResourceLocation SPYGLASS_IN_HAND_MODEL = ModelResourceLocation.vanilla("spyglass_in_hand", "inventory");
   private final Minecraft minecraft;
   private final ItemModelShaper itemModelShaper;
   private final TextureManager textureManager;
   private final ItemColors itemColors;
   private final BlockEntityWithoutLevelRenderer blockEntityRenderer;

   public ItemRenderer(Minecraft p_266926_, TextureManager p_266774_, ModelManager p_266850_, ItemColors p_267016_, BlockEntityWithoutLevelRenderer p_267049_) {
      this.minecraft = p_266926_;
      this.textureManager = p_266774_;
      this.itemModelShaper = new net.minecraftforge.client.model.ForgeItemModelShaper(p_266850_);
      this.blockEntityRenderer = p_267049_;

      for(Item item : BuiltInRegistries.ITEM) {
         if (!IGNORED.contains(item)) {
            this.itemModelShaper.register(item, new ModelResourceLocation(BuiltInRegistries.ITEM.getKey(item), "inventory"));
         }
      }

      this.itemColors = p_267016_;
   }

   public ItemModelShaper getItemModelShaper() {
      return this.itemModelShaper;
   }

   public void renderModelLists(BakedModel p_115190_, ItemStack p_115191_, int p_115192_, int p_115193_, PoseStack p_115194_, VertexConsumer p_115195_) {
      RandomSource randomsource = RandomSource.create();
      long i = 42L;

      for(Direction direction : Direction.values()) {
         randomsource.setSeed(42L);
         this.renderQuadList(p_115194_, p_115195_, p_115190_.getQuads((BlockState)null, direction, randomsource), p_115191_, p_115192_, p_115193_);
      }

      randomsource.setSeed(42L);
      this.renderQuadList(p_115194_, p_115195_, p_115190_.getQuads((BlockState)null, (Direction)null, randomsource), p_115191_, p_115192_, p_115193_);
   }

   public void render(ItemStack p_115144_, ItemDisplayContext p_270188_, boolean p_115146_, PoseStack p_115147_, MultiBufferSource p_115148_, int p_115149_, int p_115150_, BakedModel p_115151_) {
      if (!p_115144_.isEmpty()) {
         p_115147_.pushPose();
         boolean flag = p_270188_ == ItemDisplayContext.GUI || p_270188_ == ItemDisplayContext.GROUND || p_270188_ == ItemDisplayContext.FIXED;
         if (flag) {
            if (p_115144_.is(Items.TRIDENT)) {
               p_115151_ = this.itemModelShaper.getModelManager().getModel(TRIDENT_MODEL);
            } else if (p_115144_.is(Items.SPYGLASS)) {
               p_115151_ = this.itemModelShaper.getModelManager().getModel(SPYGLASS_MODEL);
            }
         }

         p_115151_ = net.minecraftforge.client.ForgeHooksClient.handleCameraTransforms(p_115147_, p_115151_, p_270188_, p_115146_);
         p_115147_.translate(-0.5F, -0.5F, -0.5F);
         if (!p_115151_.isCustomRenderer() && (!p_115144_.is(Items.TRIDENT) || flag)) {
            boolean flag1;
            if (p_270188_ != ItemDisplayContext.GUI && !p_270188_.firstPerson() && p_115144_.getItem() instanceof BlockItem) {
               Block block = ((BlockItem)p_115144_.getItem()).getBlock();
               flag1 = !(block instanceof HalfTransparentBlock) && !(block instanceof StainedGlassPaneBlock);
            } else {
               flag1 = true;
            }
            for (var model : p_115151_.getRenderPasses(p_115144_, flag1)) {
            for (var rendertype : model.getRenderTypes(p_115144_, flag1)) {
            VertexConsumer vertexconsumer;
            if (p_115144_.is(ItemTags.COMPASSES) && p_115144_.hasFoil()) {
               p_115147_.pushPose();
               PoseStack.Pose posestack$pose = p_115147_.last();
               if (p_270188_ == ItemDisplayContext.GUI) {
                  MatrixUtil.mulComponentWise(posestack$pose.pose(), 0.5F);
               } else if (p_270188_.firstPerson()) {
                  MatrixUtil.mulComponentWise(posestack$pose.pose(), 0.75F);
               }

               if (flag1) {
                  vertexconsumer = getCompassFoilBufferDirect(p_115148_, rendertype, posestack$pose);
               } else {
                  vertexconsumer = getCompassFoilBuffer(p_115148_, rendertype, posestack$pose);
               }

               p_115147_.popPose();
            } else if (flag1) {
               vertexconsumer = getFoilBufferDirect(p_115148_, rendertype, true, p_115144_.hasFoil());
            } else {
               vertexconsumer = getFoilBuffer(p_115148_, rendertype, true, p_115144_.hasFoil());
            }

            this.renderModelLists(model, p_115144_, p_115149_, p_115150_, p_115147_, vertexconsumer);
            }
            }
         } else {
            net.minecraftforge.client.extensions.common.IClientItemExtensions.of(p_115144_).getCustomRenderer().renderByItem(p_115144_, p_270188_, p_115147_, p_115148_, p_115149_, p_115150_);
         }

         p_115147_.popPose();
      }
   }

   public static VertexConsumer getArmorFoilBuffer(MultiBufferSource p_115185_, RenderType p_115186_, boolean p_115187_, boolean p_115188_) {
      return p_115188_ ? VertexMultiConsumer.create(p_115185_.getBuffer(p_115187_ ? RenderType.armorGlint() : RenderType.armorEntityGlint()), p_115185_.getBuffer(p_115186_)) : p_115185_.getBuffer(p_115186_);
   }

   public static VertexConsumer getCompassFoilBuffer(MultiBufferSource p_115181_, RenderType p_115182_, PoseStack.Pose p_115183_) {
      return VertexMultiConsumer.create(new SheetedDecalTextureGenerator(p_115181_.getBuffer(RenderType.glint()), p_115183_.pose(), p_115183_.normal(), 0.0078125F), p_115181_.getBuffer(p_115182_));
   }

   public static VertexConsumer getCompassFoilBufferDirect(MultiBufferSource p_115208_, RenderType p_115209_, PoseStack.Pose p_115210_) {
      return VertexMultiConsumer.create(new SheetedDecalTextureGenerator(p_115208_.getBuffer(RenderType.glintDirect()), p_115210_.pose(), p_115210_.normal(), 0.0078125F), p_115208_.getBuffer(p_115209_));
   }

   public static VertexConsumer getFoilBuffer(MultiBufferSource p_115212_, RenderType p_115213_, boolean p_115214_, boolean p_115215_) {
      if (p_115215_) {
         return Minecraft.useShaderTransparency() && p_115213_ == Sheets.translucentItemSheet() ? VertexMultiConsumer.create(p_115212_.getBuffer(RenderType.glintTranslucent()), p_115212_.getBuffer(p_115213_)) : VertexMultiConsumer.create(p_115212_.getBuffer(p_115214_ ? RenderType.glint() : RenderType.entityGlint()), p_115212_.getBuffer(p_115213_));
      } else {
         return p_115212_.getBuffer(p_115213_);
      }
   }

   public static VertexConsumer getFoilBufferDirect(MultiBufferSource p_115223_, RenderType p_115224_, boolean p_115225_, boolean p_115226_) {
      return p_115226_ ? VertexMultiConsumer.create(p_115223_.getBuffer(p_115225_ ? RenderType.glintDirect() : RenderType.entityGlintDirect()), p_115223_.getBuffer(p_115224_)) : p_115223_.getBuffer(p_115224_);
   }

   public void renderQuadList(PoseStack p_115163_, VertexConsumer p_115164_, List<BakedQuad> p_115165_, ItemStack p_115166_, int p_115167_, int p_115168_) {
      boolean flag = !p_115166_.isEmpty();
      PoseStack.Pose posestack$pose = p_115163_.last();

      for(BakedQuad bakedquad : p_115165_) {
         int i = -1;
         if (flag && bakedquad.isTinted()) {
            i = this.itemColors.getColor(p_115166_, bakedquad.getTintIndex());
         }

         float f = (float)(i >> 16 & 255) / 255.0F;
         float f1 = (float)(i >> 8 & 255) / 255.0F;
         float f2 = (float)(i & 255) / 255.0F;
         p_115164_.putBulkData(posestack$pose, bakedquad, f, f1, f2, 1.0F, p_115167_, p_115168_, true);
      }

   }

   public BakedModel getModel(ItemStack p_174265_, @Nullable Level p_174266_, @Nullable LivingEntity p_174267_, int p_174268_) {
      BakedModel bakedmodel;
      if (p_174265_.is(Items.TRIDENT)) {
         bakedmodel = this.itemModelShaper.getModelManager().getModel(TRIDENT_IN_HAND_MODEL);
      } else if (p_174265_.is(Items.SPYGLASS)) {
         bakedmodel = this.itemModelShaper.getModelManager().getModel(SPYGLASS_IN_HAND_MODEL);
      } else {
         bakedmodel = this.itemModelShaper.getItemModel(p_174265_);
      }

      ClientLevel clientlevel = p_174266_ instanceof ClientLevel ? (ClientLevel)p_174266_ : null;
      BakedModel bakedmodel1 = bakedmodel.getOverrides().resolve(bakedmodel, p_174265_, clientlevel, p_174267_, p_174268_);
      return bakedmodel1 == null ? this.itemModelShaper.getModelManager().getMissingModel() : bakedmodel1;
   }

   public void renderStatic(ItemStack p_270761_, ItemDisplayContext p_270648_, int p_270410_, int p_270894_, PoseStack p_270430_, MultiBufferSource p_270457_, @Nullable Level p_270149_, int p_270509_) {
      this.renderStatic((LivingEntity)null, p_270761_, p_270648_, false, p_270430_, p_270457_, p_270149_, p_270410_, p_270894_, p_270509_);
   }

   public void renderStatic(@Nullable LivingEntity p_270101_, ItemStack p_270637_, ItemDisplayContext p_270437_, boolean p_270434_, PoseStack p_270230_, MultiBufferSource p_270411_, @Nullable Level p_270641_, int p_270595_, int p_270927_, int p_270845_) {
      if (!p_270637_.isEmpty()) {
         BakedModel bakedmodel = this.getModel(p_270637_, p_270641_, p_270101_, p_270845_);
         this.render(p_270637_, p_270437_, p_270434_, p_270230_, p_270411_, p_270595_, p_270927_, bakedmodel);
      }
   }

   public void renderGuiItem(PoseStack p_275410_, ItemStack p_275575_, int p_275265_, int p_275235_) {
      this.renderGuiItem(p_275410_, p_275575_, p_275265_, p_275235_, this.getModel(p_275575_, (Level)null, (LivingEntity)null, 0));
   }

   protected void renderGuiItem(PoseStack p_275246_, ItemStack p_275195_, int p_275214_, int p_275658_, BakedModel p_275740_) {
      p_275246_.pushPose();
      p_275246_.translate((float)p_275214_, (float)p_275658_, 100.0F);
      p_275246_.translate(8.0F, 8.0F, 0.0F);
      p_275246_.mulPoseMatrix((new Matrix4f()).scaling(1.0F, -1.0F, 1.0F));
      p_275246_.scale(16.0F, 16.0F, 16.0F);
      MultiBufferSource.BufferSource multibuffersource$buffersource = this.minecraft.renderBuffers().bufferSource();
      boolean flag = !p_275740_.usesBlockLight();
      if (flag) {
         Lighting.setupForFlatItems();
      }

      PoseStack posestack = RenderSystem.getModelViewStack();
      posestack.pushPose();
      posestack.mulPoseMatrix(p_275246_.last().pose());
      RenderSystem.applyModelViewMatrix();
      this.render(p_275195_, ItemDisplayContext.GUI, false, new PoseStack(), multibuffersource$buffersource, 15728880, OverlayTexture.NO_OVERLAY, p_275740_);
      multibuffersource$buffersource.endBatch();
      RenderSystem.enableDepthTest();
      if (flag) {
         Lighting.setupFor3DItems();
      }

      p_275246_.popPose();
      posestack.popPose();
      RenderSystem.applyModelViewMatrix();
   }

   public void renderAndDecorateItem(PoseStack p_275197_, ItemStack p_275552_, int p_275198_, int p_275738_) {
      this.tryRenderGuiItem(p_275197_, this.minecraft.player, this.minecraft.level, p_275552_, p_275198_, p_275738_, 0);
   }

   public void renderAndDecorateItem(PoseStack p_275539_, ItemStack p_275685_, int p_275360_, int p_275448_, int p_275456_) {
      this.tryRenderGuiItem(p_275539_, this.minecraft.player, this.minecraft.level, p_275685_, p_275360_, p_275448_, p_275456_);
   }

   public void renderAndDecorateItem(PoseStack p_275305_, ItemStack p_275612_, int p_275743_, int p_275591_, int p_275231_, int p_275345_) {
      this.tryRenderGuiItem(p_275305_, this.minecraft.player, this.minecraft.level, p_275612_, p_275743_, p_275591_, p_275231_, p_275345_);
   }

   public void renderAndDecorateFakeItem(PoseStack p_275285_, ItemStack p_275595_, int p_275671_, int p_275526_) {
      this.tryRenderGuiItem(p_275285_, (LivingEntity)null, this.minecraft.level, p_275595_, p_275671_, p_275526_, 0);
   }

   public void renderAndDecorateItem(PoseStack p_275668_, LivingEntity p_275691_, ItemStack p_275472_, int p_275283_, int p_275239_, int p_275587_) {
      this.tryRenderGuiItem(p_275668_, p_275691_, p_275691_.level, p_275472_, p_275283_, p_275239_, p_275587_);
   }

   private void tryRenderGuiItem(PoseStack p_275571_, @Nullable LivingEntity p_275317_, @Nullable Level p_275599_, ItemStack p_275500_, int p_275679_, int p_275377_, int p_275380_) {
      this.tryRenderGuiItem(p_275571_, p_275317_, p_275599_, p_275500_, p_275679_, p_275377_, p_275380_, 0);
   }

   private void tryRenderGuiItem(PoseStack p_275419_, @Nullable LivingEntity p_275405_, @Nullable Level p_275402_, ItemStack p_275431_, int p_275347_, int p_275504_, int p_275678_, int p_275555_) {
      if (!p_275431_.isEmpty()) {
         BakedModel bakedmodel = this.getModel(p_275431_, p_275402_, p_275405_, p_275678_);
         p_275419_.pushPose();
         p_275419_.translate(0.0F, 0.0F, (float)(50 + (bakedmodel.isGui3d() ? p_275555_ : 0)));

         try {
            this.renderGuiItem(p_275419_, p_275431_, p_275347_, p_275504_, bakedmodel);
         } catch (Throwable throwable) {
            CrashReport crashreport = CrashReport.forThrowable(throwable, "Rendering item");
            CrashReportCategory crashreportcategory = crashreport.addCategory("Item being rendered");
            crashreportcategory.setDetail("Item Type", () -> {
               return String.valueOf((Object)p_275431_.getItem());
            });
            crashreportcategory.setDetail("Registry Name", () -> String.valueOf(net.minecraftforge.registries.ForgeRegistries.ITEMS.getKey(p_275431_.getItem())));
            crashreportcategory.setDetail("Item Damage", () -> {
               return String.valueOf(p_275431_.getDamageValue());
            });
            crashreportcategory.setDetail("Item NBT", () -> {
               return String.valueOf((Object)p_275431_.getTag());
            });
            crashreportcategory.setDetail("Item Foil", () -> {
               return String.valueOf(p_275431_.hasFoil());
            });
            throw new ReportedException(crashreport);
         }

         p_275419_.popPose();
      }
   }

   public void renderGuiItemDecorations(PoseStack p_275553_, Font p_275636_, ItemStack p_275514_, int p_275695_, int p_275460_) {
      this.renderGuiItemDecorations(p_275553_, p_275636_, p_275514_, p_275695_, p_275460_, (String)null);
   }

   public void renderGuiItemDecorations(PoseStack p_275269_, Font p_275652_, ItemStack p_275590_, int p_275202_, int p_275508_, @Nullable String p_275302_) {
      if (!p_275590_.isEmpty()) {
         p_275269_.pushPose();
         if (p_275590_.getCount() != 1 || p_275302_ != null) {
            String s = p_275302_ == null ? String.valueOf(p_275590_.getCount()) : p_275302_;
            p_275269_.translate(0.0F, 0.0F, 200.0F);
            MultiBufferSource.BufferSource multibuffersource$buffersource = MultiBufferSource.immediate(Tesselator.getInstance().getBuilder());
            p_275652_.drawInBatch(s, (float)(p_275202_ + 19 - 2 - p_275652_.width(s)), (float)(p_275508_ + 6 + 3), 16777215, true, p_275269_.last().pose(), multibuffersource$buffersource, Font.DisplayMode.NORMAL, 0, 15728880);
            multibuffersource$buffersource.endBatch();
         }

         if (p_275590_.isBarVisible()) {
            RenderSystem.disableDepthTest();
            int k = p_275590_.getBarWidth();
            int l = p_275590_.getBarColor();
            int i = p_275202_ + 2;
            int j = p_275508_ + 13;
            GuiComponent.fill(p_275269_, i, j, i + 13, j + 2, -16777216);
            GuiComponent.fill(p_275269_, i, j, i + k, j + 1, l | -16777216);
            RenderSystem.enableDepthTest();
         }

         LocalPlayer localplayer = this.minecraft.player;
         float f = localplayer == null ? 0.0F : localplayer.getCooldowns().getCooldownPercent(p_275590_.getItem(), this.minecraft.getFrameTime());
         if (f > 0.0F) {
            RenderSystem.disableDepthTest();
            int i1 = p_275508_ + Mth.floor(16.0F * (1.0F - f));
            int j1 = i1 + Mth.ceil(16.0F * f);
            GuiComponent.fill(p_275269_, p_275202_, i1, p_275202_ + 16, j1, Integer.MAX_VALUE);
            RenderSystem.enableDepthTest();
         }

         p_275269_.popPose();
         net.minecraftforge.client.ItemDecoratorHandler.of(p_275590_).render(p_275269_, p_275652_, p_275590_, p_275202_, p_275508_);
      }
   }

   public void onResourceManagerReload(ResourceManager p_115105_) {
      this.itemModelShaper.rebuildCache();
   }

   public BlockEntityWithoutLevelRenderer getBlockEntityRenderer() {
       return blockEntityRenderer;
   }
}
