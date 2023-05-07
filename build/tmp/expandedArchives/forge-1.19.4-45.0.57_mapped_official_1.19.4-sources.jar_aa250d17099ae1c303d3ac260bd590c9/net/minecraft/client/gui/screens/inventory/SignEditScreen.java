package net.minecraft.client.gui.screens.inventory;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import javax.annotation.Nullable;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.Sheets;
import net.minecraft.client.renderer.blockentity.SignRenderer;
import net.minecraft.client.renderer.texture.OverlayTexture;
import net.minecraft.client.resources.model.Material;
import net.minecraft.world.level.block.StandingSignBlock;
import net.minecraft.world.level.block.entity.SignBlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import org.joml.Vector3f;

@OnlyIn(Dist.CLIENT)
public class SignEditScreen extends AbstractSignEditScreen {
   public static final float MAGIC_SCALE_NUMBER = 62.500004F;
   public static final float MAGIC_TEXT_SCALE = 0.9765628F;
   private static final Vector3f TEXT_SCALE = new Vector3f(0.9765628F, 0.9765628F, 0.9765628F);
   @Nullable
   private SignRenderer.SignModel signModel;

   public SignEditScreen(SignBlockEntity p_169811_, boolean p_169812_) {
      super(p_169811_, p_169812_);
   }

   protected void init() {
      super.init();
      this.signModel = SignRenderer.createSignModel(this.minecraft.getEntityModels(), this.woodType);
   }

   protected void offsetSign(PoseStack p_251392_, BlockState p_250180_) {
      super.offsetSign(p_251392_, p_250180_);
      boolean flag = p_250180_.getBlock() instanceof StandingSignBlock;
      if (!flag) {
         p_251392_.translate(0.0F, 35.0F, 0.0F);
      }

   }

   protected void renderSignBackground(PoseStack p_250159_, MultiBufferSource.BufferSource p_250604_, BlockState p_251220_) {
      if (this.signModel != null) {
         boolean flag = p_251220_.getBlock() instanceof StandingSignBlock;
         p_250159_.translate(0.0F, 31.0F, 0.0F);
         p_250159_.scale(62.500004F, 62.500004F, -62.500004F);
         Material material = Sheets.getSignMaterial(this.woodType);
         VertexConsumer vertexconsumer = material.buffer(p_250604_, this.signModel::renderType);
         this.signModel.stick.visible = flag;
         this.signModel.root.render(p_250159_, vertexconsumer, 15728880, OverlayTexture.NO_OVERLAY);
      }
   }

   protected Vector3f getSignTextScale() {
      return TEXT_SCALE;
   }
}