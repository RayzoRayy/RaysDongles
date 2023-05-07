package net.minecraft.client.gui.screens.inventory;

import com.mojang.blaze3d.systems.RenderSystem;
import com.mojang.blaze3d.vertex.PoseStack;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.block.entity.SignBlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import org.joml.Vector3f;

@OnlyIn(Dist.CLIENT)
public class HangingSignEditScreen extends AbstractSignEditScreen {
   public static final float MAGIC_BACKGROUND_SCALE = 4.0F;
   private static final Vector3f TEXT_SCALE = new Vector3f(1.0F, 1.0F, 1.0F);
   private static final int TEXTURE_WIDTH = 16;
   private static final int TEXTURE_HEIGHT = 16;
   private final ResourceLocation texture = new ResourceLocation(this.woodType.name() + ".png").withPrefix("textures/gui/hanging_signs/");

   public HangingSignEditScreen(SignBlockEntity p_249425_, boolean p_250356_) {
      super(p_249425_, p_250356_, Component.translatable("hanging_sign.edit"));
   }

   protected void offsetSign(PoseStack p_249389_, BlockState p_251983_) {
      p_249389_.translate((float)this.width / 2.0F, 125.0F, 50.0F);
   }

   protected void renderSignBackground(PoseStack p_252270_, MultiBufferSource.BufferSource p_252246_, BlockState p_251440_) {
      p_252270_.translate(0.0F, -13.0F, 0.0F);
      RenderSystem.setShaderTexture(0, this.texture);
      p_252270_.scale(4.0F, 4.0F, 1.0F);
      blit(p_252270_, -8, -8, 0.0F, 0.0F, 16, 16, 16, 16);
   }

   protected Vector3f getSignTextScale() {
      return TEXT_SCALE;
   }
}
