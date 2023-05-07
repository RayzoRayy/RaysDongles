package net.minecraft.client.gui.components.toasts;

import com.google.common.collect.Lists;
import com.mojang.blaze3d.systems.RenderSystem;
import com.mojang.blaze3d.vertex.PoseStack;
import java.util.List;
import net.minecraft.client.gui.GuiComponent;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.Recipe;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

@OnlyIn(Dist.CLIENT)
public class RecipeToast implements Toast {
   private static final long DISPLAY_TIME = 5000L;
   private static final Component TITLE_TEXT = Component.translatable("recipe.toast.title");
   private static final Component DESCRIPTION_TEXT = Component.translatable("recipe.toast.description");
   private final List<Recipe<?>> recipes = Lists.newArrayList();
   private long lastChanged;
   private boolean changed;

   public RecipeToast(Recipe<?> p_94810_) {
      this.recipes.add(p_94810_);
   }

   public Toast.Visibility render(PoseStack p_94814_, ToastComponent p_94815_, long p_94816_) {
      if (this.changed) {
         this.lastChanged = p_94816_;
         this.changed = false;
      }

      if (this.recipes.isEmpty()) {
         return Toast.Visibility.HIDE;
      } else {
         RenderSystem.setShaderTexture(0, TEXTURE);
         GuiComponent.blit(p_94814_, 0, 0, 0, 32, this.width(), this.height());
         p_94815_.getMinecraft().font.draw(p_94814_, TITLE_TEXT, 30.0F, 7.0F, -11534256);
         p_94815_.getMinecraft().font.draw(p_94814_, DESCRIPTION_TEXT, 30.0F, 18.0F, -16777216);
         Recipe<?> recipe = this.recipes.get((int)((double)p_94816_ / Math.max(1.0D, 5000.0D * p_94815_.getNotificationDisplayTimeMultiplier() / (double)this.recipes.size()) % (double)this.recipes.size()));
         ItemStack itemstack = recipe.getToastSymbol();
         p_94814_.pushPose();
         p_94814_.scale(0.6F, 0.6F, 1.0F);
         p_94815_.getMinecraft().getItemRenderer().renderAndDecorateFakeItem(p_94814_, itemstack, 3, 3);
         p_94814_.popPose();
         p_94815_.getMinecraft().getItemRenderer().renderAndDecorateFakeItem(p_94814_, recipe.getResultItem(p_94815_.getMinecraft().level.registryAccess()), 8, 8);
         return (double)(p_94816_ - this.lastChanged) >= 5000.0D * p_94815_.getNotificationDisplayTimeMultiplier() ? Toast.Visibility.HIDE : Toast.Visibility.SHOW;
      }
   }

   private void addItem(Recipe<?> p_94812_) {
      this.recipes.add(p_94812_);
      this.changed = true;
   }

   public static void addOrUpdate(ToastComponent p_94818_, Recipe<?> p_94819_) {
      RecipeToast recipetoast = p_94818_.getToast(RecipeToast.class, NO_TOKEN);
      if (recipetoast == null) {
         p_94818_.addToast(new RecipeToast(p_94819_));
      } else {
         recipetoast.addItem(p_94819_);
      }

   }
}