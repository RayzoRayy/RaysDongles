package net.minecraft.data.recipes;

import com.google.gson.JsonObject;
import java.util.function.Consumer;
import javax.annotation.Nullable;
import net.minecraft.advancements.Advancement;
import net.minecraft.advancements.AdvancementRewards;
import net.minecraft.advancements.CriterionTriggerInstance;
import net.minecraft.advancements.RequirementsStrategy;
import net.minecraft.advancements.critereon.RecipeUnlockedTrigger;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.item.crafting.RecipeSerializer;

/** @deprecated */
@Deprecated(
   forRemoval = true
)
public class LegacyUpgradeRecipeBuilder {
   private final Ingredient base;
   private final Ingredient addition;
   private final RecipeCategory category;
   private final Item result;
   private final Advancement.Builder advancement = Advancement.Builder.advancement();
   private final RecipeSerializer<?> type;

   public LegacyUpgradeRecipeBuilder(RecipeSerializer<?> p_266753_, Ingredient p_267080_, Ingredient p_267246_, RecipeCategory p_267024_, Item p_266767_) {
      this.category = p_267024_;
      this.type = p_266753_;
      this.base = p_267080_;
      this.addition = p_267246_;
      this.result = p_266767_;
   }

   public static LegacyUpgradeRecipeBuilder smithing(Ingredient p_266949_, Ingredient p_267302_, RecipeCategory p_266837_, Item p_266863_) {
      return new LegacyUpgradeRecipeBuilder(RecipeSerializer.SMITHING, p_266949_, p_267302_, p_266837_, p_266863_);
   }

   public LegacyUpgradeRecipeBuilder unlocks(String p_267310_, CriterionTriggerInstance p_266808_) {
      this.advancement.addCriterion(p_267310_, p_266808_);
      return this;
   }

   public void save(Consumer<FinishedRecipe> p_266900_, String p_266899_) {
      this.save(p_266900_, new ResourceLocation(p_266899_));
   }

   public void save(Consumer<FinishedRecipe> p_266852_, ResourceLocation p_267253_) {
      this.ensureValid(p_267253_);
      this.advancement.parent(RecipeBuilder.ROOT_RECIPE_ADVANCEMENT).addCriterion("has_the_recipe", RecipeUnlockedTrigger.unlocked(p_267253_)).rewards(AdvancementRewards.Builder.recipe(p_267253_)).requirements(RequirementsStrategy.OR);
      p_266852_.accept(new LegacyUpgradeRecipeBuilder.Result(p_267253_, this.type, this.base, this.addition, this.result, this.advancement, p_267253_.withPrefix("recipes/" + this.category.getFolderName() + "/")));
   }

   private void ensureValid(ResourceLocation p_266958_) {
      if (this.advancement.getCriteria().isEmpty()) {
         throw new IllegalStateException("No way of obtaining recipe " + p_266958_);
      }
   }

   public static class Result implements FinishedRecipe {
      private final ResourceLocation id;
      private final Ingredient base;
      private final Ingredient addition;
      private final Item result;
      private final Advancement.Builder advancement;
      private final ResourceLocation advancementId;
      private final RecipeSerializer<?> type;

      public Result(ResourceLocation p_267216_, RecipeSerializer<?> p_266997_, Ingredient p_266970_, Ingredient p_266975_, Item p_267271_, Advancement.Builder p_266866_, ResourceLocation p_266867_) {
         this.id = p_267216_;
         this.type = p_266997_;
         this.base = p_266970_;
         this.addition = p_266975_;
         this.result = p_267271_;
         this.advancement = p_266866_;
         this.advancementId = p_266867_;
      }

      public void serializeRecipeData(JsonObject p_267275_) {
         p_267275_.add("base", this.base.toJson());
         p_267275_.add("addition", this.addition.toJson());
         JsonObject jsonobject = new JsonObject();
         jsonobject.addProperty("item", BuiltInRegistries.ITEM.getKey(this.result).toString());
         p_267275_.add("result", jsonobject);
      }

      public ResourceLocation getId() {
         return this.id;
      }

      public RecipeSerializer<?> getType() {
         return this.type;
      }

      @Nullable
      public JsonObject serializeAdvancement() {
         return this.advancement.serializeToJson();
      }

      @Nullable
      public ResourceLocation getAdvancementId() {
         return this.advancementId;
      }
   }
}