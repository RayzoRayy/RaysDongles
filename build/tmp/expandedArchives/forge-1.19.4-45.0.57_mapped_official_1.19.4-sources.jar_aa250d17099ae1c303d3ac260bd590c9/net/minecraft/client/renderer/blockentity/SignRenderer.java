package net.minecraft.client.renderer.blockentity;

import com.google.common.collect.ImmutableMap;
import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import com.mojang.math.Axis;
import java.util.List;
import java.util.Map;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.Font;
import net.minecraft.client.model.Model;
import net.minecraft.client.model.geom.EntityModelSet;
import net.minecraft.client.model.geom.ModelLayers;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.client.model.geom.PartPose;
import net.minecraft.client.model.geom.builders.CubeListBuilder;
import net.minecraft.client.model.geom.builders.LayerDefinition;
import net.minecraft.client.model.geom.builders.MeshDefinition;
import net.minecraft.client.model.geom.builders.PartDefinition;
import net.minecraft.client.player.LocalPlayer;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.Sheets;
import net.minecraft.client.resources.model.Material;
import net.minecraft.util.FastColor;
import net.minecraft.util.FormattedCharSequence;
import net.minecraft.util.Mth;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.item.DyeColor;
import net.minecraft.world.level.block.SignBlock;
import net.minecraft.world.level.block.StandingSignBlock;
import net.minecraft.world.level.block.WallSignBlock;
import net.minecraft.world.level.block.entity.SignBlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.properties.RotationSegment;
import net.minecraft.world.level.block.state.properties.WoodType;
import net.minecraft.world.phys.Vec3;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

@OnlyIn(Dist.CLIENT)
public class SignRenderer implements BlockEntityRenderer<SignBlockEntity> {
   private static final String STICK = "stick";
   private static final int BLACK_TEXT_OUTLINE_COLOR = -988212;
   private static final int OUTLINE_RENDER_DISTANCE = Mth.square(16);
   private final Map<WoodType, SignRenderer.SignModel> signModels;
   private final Font font;

   public SignRenderer(BlockEntityRendererProvider.Context p_173636_) {
      this.signModels = WoodType.values().collect(ImmutableMap.toImmutableMap((p_173645_) -> {
         return p_173645_;
      }, (p_173651_) -> {
         return new SignRenderer.SignModel(p_173636_.bakeLayer(ModelLayers.createSignModelName(p_173651_)));
      }));
      this.font = p_173636_.getFont();
   }

   public void render(SignBlockEntity p_112497_, float p_112498_, PoseStack p_112499_, MultiBufferSource p_112500_, int p_112501_, int p_112502_) {
      BlockState blockstate = p_112497_.getBlockState();
      p_112499_.pushPose();
      float f = 0.6666667F;
      WoodType woodtype = SignBlock.getWoodType(blockstate.getBlock());
      SignRenderer.SignModel signrenderer$signmodel = this.signModels.get(woodtype);
      if (blockstate.getBlock() instanceof StandingSignBlock) {
         p_112499_.translate(0.5F, 0.5F, 0.5F);
         float f1 = -RotationSegment.convertToDegrees(blockstate.getValue(StandingSignBlock.ROTATION));
         p_112499_.mulPose(Axis.YP.rotationDegrees(f1));
         signrenderer$signmodel.stick.visible = true;
      } else {
         p_112499_.translate(0.5F, 0.5F, 0.5F);
         float f2 = -blockstate.getValue(WallSignBlock.FACING).toYRot();
         p_112499_.mulPose(Axis.YP.rotationDegrees(f2));
         p_112499_.translate(0.0F, -0.3125F, -0.4375F);
         signrenderer$signmodel.stick.visible = false;
      }

      this.renderSign(p_112499_, p_112500_, p_112501_, p_112502_, 0.6666667F, woodtype, signrenderer$signmodel);
      this.renderSignText(p_112497_, p_112499_, p_112500_, p_112501_, 0.6666667F);
   }

   void renderSign(PoseStack p_248726_, MultiBufferSource p_248896_, int p_249365_, int p_249267_, float p_249454_, WoodType p_249724_, Model p_250100_) {
      p_248726_.pushPose();
      p_248726_.scale(p_249454_, -p_249454_, -p_249454_);
      Material material = this.getSignMaterial(p_249724_);
      VertexConsumer vertexconsumer = material.buffer(p_248896_, p_250100_::renderType);
      this.renderSignModel(p_248726_, p_249365_, p_249267_, p_250100_, vertexconsumer);
      p_248726_.popPose();
   }

   void renderSignModel(PoseStack p_250252_, int p_249399_, int p_249042_, Model p_250082_, VertexConsumer p_251093_) {
      SignRenderer.SignModel signrenderer$signmodel = (SignRenderer.SignModel)p_250082_;
      signrenderer$signmodel.root.render(p_250252_, p_251093_, p_249399_, p_249042_);
   }

   Material getSignMaterial(WoodType p_251961_) {
      return Sheets.getSignMaterial(p_251961_);
   }

   void renderSignText(SignBlockEntity p_250524_, PoseStack p_251442_, MultiBufferSource p_249729_, int p_248845_, float p_249971_) {
      float f = 0.015625F * p_249971_;
      Vec3 vec3 = this.getTextOffset(p_249971_);
      p_251442_.translate(vec3.x, vec3.y, vec3.z);
      p_251442_.scale(f, -f, f);
      int i = getDarkColor(p_250524_);
      int j = 4 * p_250524_.getTextLineHeight() / 2;
      FormattedCharSequence[] aformattedcharsequence = p_250524_.getRenderMessages(Minecraft.getInstance().isTextFilteringEnabled(), (p_247938_) -> {
         List<FormattedCharSequence> list = this.font.split(p_247938_, p_250524_.getMaxTextLineWidth());
         return list.isEmpty() ? FormattedCharSequence.EMPTY : list.get(0);
      });
      int k;
      boolean flag;
      int l;
      if (p_250524_.hasGlowingText()) {
         k = p_250524_.getColor().getTextColor();
         flag = isOutlineVisible(p_250524_, k);
         l = 15728880;
      } else {
         k = i;
         flag = false;
         l = p_248845_;
      }

      for(int i1 = 0; i1 < 4; ++i1) {
         FormattedCharSequence formattedcharsequence = aformattedcharsequence[i1];
         float f1 = (float)(-this.font.width(formattedcharsequence) / 2);
         if (flag) {
            this.font.drawInBatch8xOutline(formattedcharsequence, f1, (float)(i1 * p_250524_.getTextLineHeight() - j), k, i, p_251442_.last().pose(), p_249729_, l);
         } else {
            this.font.drawInBatch(formattedcharsequence, f1, (float)(i1 * p_250524_.getTextLineHeight() - j), k, false, p_251442_.last().pose(), p_249729_, Font.DisplayMode.NORMAL, 0, l);
         }
      }

      p_251442_.popPose();
   }

   Vec3 getTextOffset(float p_248916_) {
      return new Vec3(0.0D, (double)(0.5F * p_248916_), (double)(0.07F * p_248916_));
   }

   static boolean isOutlineVisible(SignBlockEntity p_173642_, int p_173643_) {
      if (p_173643_ == DyeColor.BLACK.getTextColor()) {
         return true;
      } else {
         Minecraft minecraft = Minecraft.getInstance();
         LocalPlayer localplayer = minecraft.player;
         if (localplayer != null && minecraft.options.getCameraType().isFirstPerson() && localplayer.isScoping()) {
            return true;
         } else {
            Entity entity = minecraft.getCameraEntity();
            return entity != null && entity.distanceToSqr(Vec3.atCenterOf(p_173642_.getBlockPos())) < (double)OUTLINE_RENDER_DISTANCE;
         }
      }
   }

   static int getDarkColor(SignBlockEntity p_173640_) {
      int i = p_173640_.getColor().getTextColor();
      if (i == DyeColor.BLACK.getTextColor() && p_173640_.hasGlowingText()) {
         return -988212;
      } else {
         double d0 = 0.4D;
         int j = (int)((double)FastColor.ARGB32.red(i) * 0.4D);
         int k = (int)((double)FastColor.ARGB32.green(i) * 0.4D);
         int l = (int)((double)FastColor.ARGB32.blue(i) * 0.4D);
         return FastColor.ARGB32.color(0, j, k, l);
      }
   }

   public static SignRenderer.SignModel createSignModel(EntityModelSet p_173647_, WoodType p_173648_) {
      return new SignRenderer.SignModel(p_173647_.bakeLayer(ModelLayers.createSignModelName(p_173648_)));
   }

   public static LayerDefinition createSignLayer() {
      MeshDefinition meshdefinition = new MeshDefinition();
      PartDefinition partdefinition = meshdefinition.getRoot();
      partdefinition.addOrReplaceChild("sign", CubeListBuilder.create().texOffs(0, 0).addBox(-12.0F, -14.0F, -1.0F, 24.0F, 12.0F, 2.0F), PartPose.ZERO);
      partdefinition.addOrReplaceChild("stick", CubeListBuilder.create().texOffs(0, 14).addBox(-1.0F, -2.0F, -1.0F, 2.0F, 14.0F, 2.0F), PartPose.ZERO);
      return LayerDefinition.create(meshdefinition, 64, 32);
   }

   @OnlyIn(Dist.CLIENT)
   public static final class SignModel extends Model {
      public final ModelPart root;
      public final ModelPart stick;

      public SignModel(ModelPart p_173657_) {
         super(RenderType::entityCutoutNoCull);
         this.root = p_173657_;
         this.stick = p_173657_.getChild("stick");
      }

      public void renderToBuffer(PoseStack p_112510_, VertexConsumer p_112511_, int p_112512_, int p_112513_, float p_112514_, float p_112515_, float p_112516_, float p_112517_) {
         this.root.render(p_112510_, p_112511_, p_112512_, p_112513_, p_112514_, p_112515_, p_112516_, p_112517_);
      }
   }
}