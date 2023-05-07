package net.minecraft.client.gui.screens;

import com.mojang.blaze3d.vertex.PoseStack;
import net.minecraft.client.gui.components.Button;
import net.minecraft.client.multiplayer.ClientPacketListener;
import net.minecraft.network.chat.Component;
import net.minecraft.network.protocol.game.ServerboundPlayerCommandPacket;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

@OnlyIn(Dist.CLIENT)
public class InBedChatScreen extends ChatScreen {
   private Button leaveBedButton;

   public InBedChatScreen() {
      super("");
   }

   protected void init() {
      super.init();
      this.leaveBedButton = Button.builder(Component.translatable("multiplayer.stopSleeping"), (p_96074_) -> {
         this.sendWakeUp();
      }).bounds(this.width / 2 - 100, this.height - 40, 200, 20).build();
      this.addRenderableWidget(this.leaveBedButton);
   }

   public void render(PoseStack p_263353_, int p_263400_, int p_263373_, float p_263339_) {
      if (!this.minecraft.getChatStatus().isChatAllowed(this.minecraft.isLocalServer())) {
         this.leaveBedButton.render(p_263353_, p_263400_, p_263373_, p_263339_);
      } else {
         super.render(p_263353_, p_263400_, p_263373_, p_263339_);
      }
   }

   public void onClose() {
      this.sendWakeUp();
   }

   public boolean charTyped(char p_263331_, int p_263427_) {
      return !this.minecraft.getChatStatus().isChatAllowed(this.minecraft.isLocalServer()) ? true : super.charTyped(p_263331_, p_263427_);
   }

   public boolean keyPressed(int p_96070_, int p_96071_, int p_96072_) {
      if (p_96070_ == 256) {
         this.sendWakeUp();
      }

      if (!this.minecraft.getChatStatus().isChatAllowed(this.minecraft.isLocalServer())) {
         return true;
      } else if (p_96070_ != 257 && p_96070_ != 335) {
         return super.keyPressed(p_96070_, p_96071_, p_96072_);
      } else {
         if (this.handleChatInput(this.input.getValue(), true)) {
            this.minecraft.setScreen((Screen)null);
            this.input.setValue("");
            this.minecraft.gui.getChat().resetChatScroll();
         }

         return true;
      }
   }

   private void sendWakeUp() {
      ClientPacketListener clientpacketlistener = this.minecraft.player.connection;
      clientpacketlistener.send(new ServerboundPlayerCommandPacket(this.minecraft.player, ServerboundPlayerCommandPacket.Action.STOP_SLEEPING));
   }

   public void onPlayerWokeUp() {
      if (this.input.getValue().isEmpty()) {
         this.minecraft.setScreen((Screen)null);
      } else {
         this.minecraft.setScreen(new ChatScreen(this.input.getValue()));
      }

   }
}