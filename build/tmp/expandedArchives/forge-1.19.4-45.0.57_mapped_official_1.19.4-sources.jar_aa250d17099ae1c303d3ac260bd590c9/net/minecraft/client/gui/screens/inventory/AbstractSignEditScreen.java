package net.minecraft.client.gui.screens.inventory;

import com.mojang.blaze3d.platform.GlStateManager;
import com.mojang.blaze3d.platform.Lighting;
import com.mojang.blaze3d.systems.RenderSystem;
import com.mojang.blaze3d.vertex.PoseStack;
import java.util.stream.IntStream;
import net.minecraft.client.gui.Font;
import net.minecraft.client.gui.components.Button;
import net.minecraft.client.gui.font.TextFieldHelper;
import net.minecraft.client.gui.screens.Screen;
import net.minecraft.client.multiplayer.ClientPacketListener;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.network.chat.CommonComponents;
import net.minecraft.network.chat.Component;
import net.minecraft.network.protocol.game.ServerboundSignUpdatePacket;
import net.minecraft.world.level.block.SignBlock;
import net.minecraft.world.level.block.entity.SignBlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.properties.WoodType;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import org.joml.Matrix4f;
import org.joml.Vector3f;

@OnlyIn(Dist.CLIENT)
public abstract class AbstractSignEditScreen extends Screen {
   protected final SignBlockEntity sign;
   protected final String[] messages;
   protected final WoodType woodType;
   private int frame;
   private int line;
   private TextFieldHelper signField;

   public AbstractSignEditScreen(SignBlockEntity p_251089_, boolean p_251797_) {
      this(p_251089_, p_251797_, Component.translatable("sign.edit"));
   }

   public AbstractSignEditScreen(SignBlockEntity p_248630_, boolean p_252232_, Component p_248522_) {
      super(p_248522_);
      this.woodType = SignBlock.getWoodType(p_248630_.getBlockState().getBlock());
      this.messages = IntStream.range(0, 4).mapToObj((p_249021_) -> {
         return p_248630_.getMessage(p_249021_, p_252232_);
      }).map(Component::getString).toArray((p_249111_) -> {
         return new String[p_249111_];
      });
      this.sign = p_248630_;
   }

   protected void init() {
      this.addRenderableWidget(Button.builder(CommonComponents.GUI_DONE, (p_251194_) -> {
         this.onDone();
      }).bounds(this.width / 2 - 100, this.height / 4 + 120, 200, 20).build());
      this.sign.setEditable(false);
      this.signField = new TextFieldHelper(() -> {
         return this.messages[this.line];
      }, (p_248850_) -> {
         this.messages[this.line] = p_248850_;
         this.sign.setMessage(this.line, Component.literal(p_248850_));
      }, TextFieldHelper.createClipboardGetter(this.minecraft), TextFieldHelper.createClipboardSetter(this.minecraft), (p_250707_) -> {
         return this.minecraft.font.width(p_250707_) <= this.sign.getMaxTextLineWidth();
      });
   }

   public void removed() {
      ClientPacketListener clientpacketlistener = this.minecraft.getConnection();
      if (clientpacketlistener != null) {
         clientpacketlistener.send(new ServerboundSignUpdatePacket(this.sign.getBlockPos(), this.messages[0], this.messages[1], this.messages[2], this.messages[3]));
      }

      this.sign.setEditable(true);
   }

   public void tick() {
      ++this.frame;
      if (!this.sign.getType().isValid(this.sign.getBlockState())) {
         this.onDone();
      }

   }

   private void onDone() {
      this.sign.setChanged();
      this.minecraft.setScreen((Screen)null);
   }

   public boolean charTyped(char p_252008_, int p_251178_) {
      this.signField.charTyped(p_252008_);
      return true;
   }

   public void onClose() {
      this.onDone();
   }

   public boolean keyPressed(int p_252300_, int p_250424_, int p_250697_) {
      if (p_252300_ == 265) {
         this.line = this.line - 1 & 3;
         this.signField.setCursorToEnd();
         return true;
      } else if (p_252300_ != 264 && p_252300_ != 257 && p_252300_ != 335) {
         return this.signField.keyPressed(p_252300_) ? true : super.keyPressed(p_252300_, p_250424_, p_250697_);
      } else {
         this.line = this.line + 1 & 3;
         this.signField.setCursorToEnd();
         return true;
      }
   }

   public void render(PoseStack p_251192_, int p_250089_, int p_249667_, float p_251958_) {
      Lighting.setupForFlatItems();
      this.renderBackground(p_251192_);
      drawCenteredString(p_251192_, this.font, this.title, this.width / 2, 40, 16777215);
      this.renderSign(p_251192_);
      Lighting.setupFor3DItems();
      super.render(p_251192_, p_250089_, p_249667_, p_251958_);
   }

   protected abstract void renderSignBackground(PoseStack p_250452_, MultiBufferSource.BufferSource p_250160_, BlockState p_250054_);

   protected abstract Vector3f getSignTextScale();

   protected void offsetSign(PoseStack p_248687_, BlockState p_250435_) {
      p_248687_.translate((float)this.width / 2.0F, 90.0F, 50.0F);
   }

   private void renderSign(PoseStack p_252304_) {
      MultiBufferSource.BufferSource multibuffersource$buffersource = this.minecraft.renderBuffers().bufferSource();
      BlockState blockstate = this.sign.getBlockState();
      p_252304_.pushPose();
      this.offsetSign(p_252304_, blockstate);
      p_252304_.pushPose();
      this.renderSignBackground(p_252304_, multibuffersource$buffersource, blockstate);
      p_252304_.popPose();
      this.renderSignText(p_252304_, multibuffersource$buffersource);
      p_252304_.popPose();
   }

   private void renderSignText(PoseStack p_250711_, MultiBufferSource.BufferSource p_250502_) {
      p_250711_.translate(0.0F, 0.0F, 4.0F);
      Vector3f vector3f = this.getSignTextScale();
      p_250711_.scale(vector3f.x(), vector3f.y(), vector3f.z());
      int i = this.sign.getColor().getTextColor();
      boolean flag = this.frame / 6 % 2 == 0;
      int j = this.signField.getCursorPos();
      int k = this.signField.getSelectionPos();
      int l = 4 * this.sign.getTextLineHeight() / 2;
      int i1 = this.line * this.sign.getTextLineHeight() - l;
      Matrix4f matrix4f = p_250711_.last().pose();

      for(int j1 = 0; j1 < this.messages.length; ++j1) {
         String s = this.messages[j1];
         if (s != null) {
            if (this.font.isBidirectional()) {
               s = this.font.bidirectionalShaping(s);
            }

            float f = (float)(-this.minecraft.font.width(s) / 2);
            this.minecraft.font.drawInBatch(s, f, (float)(j1 * this.sign.getTextLineHeight() - l), i, false, matrix4f, p_250502_, Font.DisplayMode.NORMAL, 0, 15728880, false);
            if (j1 == this.line && j >= 0 && flag) {
               int k1 = this.minecraft.font.width(s.substring(0, Math.max(Math.min(j, s.length()), 0)));
               int l1 = k1 - this.minecraft.font.width(s) / 2;
               if (j >= s.length()) {
                  this.minecraft.font.drawInBatch("_", (float)l1, (float)i1, i, false, matrix4f, p_250502_, Font.DisplayMode.NORMAL, 0, 15728880, false);
               }
            }
         }
      }

      p_250502_.endBatch();

      for(int j3 = 0; j3 < this.messages.length; ++j3) {
         String s1 = this.messages[j3];
         if (s1 != null && j3 == this.line && j >= 0) {
            int k3 = this.minecraft.font.width(s1.substring(0, Math.max(Math.min(j, s1.length()), 0)));
            int l3 = k3 - this.minecraft.font.width(s1) / 2;
            if (flag && j < s1.length()) {
               fill(p_250711_, l3, i1 - 1, l3 + 1, i1 + this.sign.getTextLineHeight(), -16777216 | i);
            }

            if (k != j) {
               int i4 = Math.min(j, k);
               int i2 = Math.max(j, k);
               int j2 = this.minecraft.font.width(s1.substring(0, i4)) - this.minecraft.font.width(s1) / 2;
               int k2 = this.minecraft.font.width(s1.substring(0, i2)) - this.minecraft.font.width(s1) / 2;
               int l2 = Math.min(j2, k2);
               int i3 = Math.max(j2, k2);
               RenderSystem.enableColorLogicOp();
               RenderSystem.logicOp(GlStateManager.LogicOp.OR_REVERSE);
               fill(p_250711_, l2, i1, i3, i1 + this.sign.getTextLineHeight(), -16776961);
               RenderSystem.disableColorLogicOp();
            }
         }
      }

   }
}