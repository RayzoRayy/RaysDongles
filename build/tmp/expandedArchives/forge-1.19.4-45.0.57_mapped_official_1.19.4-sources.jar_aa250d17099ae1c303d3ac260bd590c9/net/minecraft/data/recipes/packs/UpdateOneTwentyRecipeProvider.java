package net.minecraft.data.recipes.packs;

import java.util.function.Consumer;
import net.minecraft.data.PackOutput;
import net.minecraft.data.recipes.FinishedRecipe;
import net.minecraft.data.recipes.RecipeCategory;
import net.minecraft.data.recipes.RecipeProvider;
import net.minecraft.data.recipes.ShapedRecipeBuilder;
import net.minecraft.data.recipes.SpecialRecipeBuilder;
import net.minecraft.tags.ItemTags;
import net.minecraft.world.flag.FeatureFlagSet;
import net.minecraft.world.flag.FeatureFlags;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.crafting.RecipeSerializer;
import net.minecraft.world.level.block.Blocks;

public class UpdateOneTwentyRecipeProvider extends RecipeProvider {
   public UpdateOneTwentyRecipeProvider(PackOutput p_249171_) {
      super(p_249171_);
   }

   protected void buildRecipes(Consumer<FinishedRecipe> p_250771_) {
      generateForEnabledBlockFamilies(p_250771_, FeatureFlagSet.of(FeatureFlags.UPDATE_1_20));
      threeByThreePacker(p_250771_, RecipeCategory.BUILDING_BLOCKS, Blocks.BAMBOO_BLOCK, Items.BAMBOO);
      planksFromLogs(p_250771_, Blocks.BAMBOO_PLANKS, ItemTags.BAMBOO_BLOCKS, 2);
      mosaicBuilder(p_250771_, RecipeCategory.DECORATIONS, Blocks.BAMBOO_MOSAIC, Blocks.BAMBOO_SLAB);
      woodenBoat(p_250771_, Items.BAMBOO_RAFT, Blocks.BAMBOO_PLANKS);
      chestBoat(p_250771_, Items.BAMBOO_CHEST_RAFT, Items.BAMBOO_RAFT);
      hangingSign(p_250771_, Items.OAK_HANGING_SIGN, Blocks.STRIPPED_OAK_LOG);
      hangingSign(p_250771_, Items.SPRUCE_HANGING_SIGN, Blocks.STRIPPED_SPRUCE_LOG);
      hangingSign(p_250771_, Items.BIRCH_HANGING_SIGN, Blocks.STRIPPED_BIRCH_LOG);
      hangingSign(p_250771_, Items.JUNGLE_HANGING_SIGN, Blocks.STRIPPED_JUNGLE_LOG);
      hangingSign(p_250771_, Items.ACACIA_HANGING_SIGN, Blocks.STRIPPED_ACACIA_LOG);
      hangingSign(p_250771_, Items.CHERRY_HANGING_SIGN, Blocks.STRIPPED_CHERRY_LOG);
      hangingSign(p_250771_, Items.DARK_OAK_HANGING_SIGN, Blocks.STRIPPED_DARK_OAK_LOG);
      hangingSign(p_250771_, Items.MANGROVE_HANGING_SIGN, Blocks.STRIPPED_MANGROVE_LOG);
      hangingSign(p_250771_, Items.BAMBOO_HANGING_SIGN, Items.STRIPPED_BAMBOO_BLOCK);
      hangingSign(p_250771_, Items.CRIMSON_HANGING_SIGN, Blocks.STRIPPED_CRIMSON_STEM);
      hangingSign(p_250771_, Items.WARPED_HANGING_SIGN, Blocks.STRIPPED_WARPED_STEM);
      ShapedRecipeBuilder.shaped(RecipeCategory.BUILDING_BLOCKS, Blocks.CHISELED_BOOKSHELF).define('#', ItemTags.PLANKS).define('X', ItemTags.WOODEN_SLABS).pattern("###").pattern("XXX").pattern("###").unlockedBy("has_book", has(Items.BOOK)).save(p_250771_);
      trimSmithing(p_250771_, Items.TIDE_ARMOR_TRIM_SMITHING_TEMPLATE);
      trimSmithing(p_250771_, Items.SNOUT_ARMOR_TRIM_SMITHING_TEMPLATE);
      trimSmithing(p_250771_, Items.COAST_ARMOR_TRIM_SMITHING_TEMPLATE);
      trimSmithing(p_250771_, Items.VEX_ARMOR_TRIM_SMITHING_TEMPLATE);
      trimSmithing(p_250771_, Items.SPIRE_ARMOR_TRIM_SMITHING_TEMPLATE);
      trimSmithing(p_250771_, Items.WARD_ARMOR_TRIM_SMITHING_TEMPLATE);
      trimSmithing(p_250771_, Items.EYE_ARMOR_TRIM_SMITHING_TEMPLATE);
      trimSmithing(p_250771_, Items.DUNE_ARMOR_TRIM_SMITHING_TEMPLATE);
      trimSmithing(p_250771_, Items.WILD_ARMOR_TRIM_SMITHING_TEMPLATE);
      trimSmithing(p_250771_, Items.RIB_ARMOR_TRIM_SMITHING_TEMPLATE);
      trimSmithing(p_250771_, Items.SENTRY_ARMOR_TRIM_SMITHING_TEMPLATE);
      netheriteSmithing(p_250771_, Items.DIAMOND_CHESTPLATE, RecipeCategory.COMBAT, Items.NETHERITE_CHESTPLATE);
      netheriteSmithing(p_250771_, Items.DIAMOND_LEGGINGS, RecipeCategory.COMBAT, Items.NETHERITE_LEGGINGS);
      netheriteSmithing(p_250771_, Items.DIAMOND_HELMET, RecipeCategory.COMBAT, Items.NETHERITE_HELMET);
      netheriteSmithing(p_250771_, Items.DIAMOND_BOOTS, RecipeCategory.COMBAT, Items.NETHERITE_BOOTS);
      netheriteSmithing(p_250771_, Items.DIAMOND_SWORD, RecipeCategory.COMBAT, Items.NETHERITE_SWORD);
      netheriteSmithing(p_250771_, Items.DIAMOND_AXE, RecipeCategory.TOOLS, Items.NETHERITE_AXE);
      netheriteSmithing(p_250771_, Items.DIAMOND_PICKAXE, RecipeCategory.TOOLS, Items.NETHERITE_PICKAXE);
      netheriteSmithing(p_250771_, Items.DIAMOND_HOE, RecipeCategory.TOOLS, Items.NETHERITE_HOE);
      netheriteSmithing(p_250771_, Items.DIAMOND_SHOVEL, RecipeCategory.TOOLS, Items.NETHERITE_SHOVEL);
      copySmithingTemplate(p_250771_, Items.NETHERITE_UPGRADE_SMITHING_TEMPLATE, Items.NETHERRACK);
      copySmithingTemplate(p_250771_, Items.SENTRY_ARMOR_TRIM_SMITHING_TEMPLATE, Items.COBBLESTONE);
      copySmithingTemplate(p_250771_, Items.DUNE_ARMOR_TRIM_SMITHING_TEMPLATE, Items.SANDSTONE);
      copySmithingTemplate(p_250771_, Items.COAST_ARMOR_TRIM_SMITHING_TEMPLATE, Items.COBBLESTONE);
      copySmithingTemplate(p_250771_, Items.WILD_ARMOR_TRIM_SMITHING_TEMPLATE, Items.MOSSY_COBBLESTONE);
      copySmithingTemplate(p_250771_, Items.WARD_ARMOR_TRIM_SMITHING_TEMPLATE, Items.COBBLED_DEEPSLATE);
      copySmithingTemplate(p_250771_, Items.EYE_ARMOR_TRIM_SMITHING_TEMPLATE, Items.END_STONE);
      copySmithingTemplate(p_250771_, Items.VEX_ARMOR_TRIM_SMITHING_TEMPLATE, Items.COBBLESTONE);
      copySmithingTemplate(p_250771_, Items.TIDE_ARMOR_TRIM_SMITHING_TEMPLATE, Items.PRISMARINE);
      copySmithingTemplate(p_250771_, Items.SNOUT_ARMOR_TRIM_SMITHING_TEMPLATE, Items.BLACKSTONE);
      copySmithingTemplate(p_250771_, Items.RIB_ARMOR_TRIM_SMITHING_TEMPLATE, Items.NETHERRACK);
      copySmithingTemplate(p_250771_, Items.SPIRE_ARMOR_TRIM_SMITHING_TEMPLATE, Items.PURPUR_BLOCK);
      oneToOneConversionRecipe(p_250771_, Items.ORANGE_DYE, Blocks.TORCHFLOWER, "orange_dye");
      planksFromLog(p_250771_, Blocks.CHERRY_PLANKS, ItemTags.CHERRY_LOGS, 4);
      woodFromLogs(p_250771_, Blocks.CHERRY_WOOD, Blocks.CHERRY_LOG);
      woodFromLogs(p_250771_, Blocks.STRIPPED_CHERRY_WOOD, Blocks.STRIPPED_CHERRY_LOG);
      woodenBoat(p_250771_, Items.CHERRY_BOAT, Blocks.CHERRY_PLANKS);
      chestBoat(p_250771_, Items.CHERRY_CHEST_BOAT, Items.CHERRY_BOAT);
      oneToOneConversionRecipe(p_250771_, Items.PINK_DYE, Items.PINK_PETALS, "pink_dye", 1);
      ShapedRecipeBuilder.shaped(RecipeCategory.TOOLS, Items.BRUSH).define('X', Items.FEATHER).define('#', Items.COPPER_INGOT).define('I', Items.STICK).pattern("X").pattern("#").pattern("I").unlockedBy("has_copper_ingot", has(Items.COPPER_INGOT)).save(p_250771_);
      ShapedRecipeBuilder.shaped(RecipeCategory.DECORATIONS, Items.DECORATED_POT).define('#', Items.BRICK).pattern(" # ").pattern("# #").pattern(" # ").unlockedBy("has_brick", has(ItemTags.DECORATED_POT_SHARDS)).save(p_250771_, "decorated_pot_simple");
      SpecialRecipeBuilder.special(RecipeSerializer.DECORATED_POT_RECIPE).save(p_250771_, "decorated_pot");
   }
}