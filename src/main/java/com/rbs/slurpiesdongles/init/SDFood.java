package com.rbs.slurpiesdongles.init;

import com.rbs.slurpiesdongles.SlurpiesDongles;
import com.rbs.slurpiesdongles.food.*;
import com.rbs.slurpiesdongles.items.ItemBase;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.init.Blocks;
import net.minecraft.item.Item;
import net.minecraftforge.registries.IForgeRegistry;

/**
 * Created by Consular on 7/20/2017.
 */
public class SDFood {

    //Drinks
    public static FoodDrinkBase appleJuice = new FoodDrinkBase(5, 0.5F, false, "apple_juice");
    public static FoodDrinkBase appleSlushie = new FoodDrinkBase(6, 0.7F, false, "apple_slushie");
    public static DrinkBlackCoffee blackCoffee = new DrinkBlackCoffee(2, 0.6F, false, "black_coffee");
    public static FoodDrinkBase carrotJuice = new FoodDrinkBase(4, 0.6F, false, "carrot_juice");
    public static DrinkCoffee coffee = new DrinkCoffee(3, 0.8F, false, "coffee");
    public static FoodDrinkBase lemonade = new FoodDrinkBase(5, 0.6F, false, "lemonade");
    public static FoodDrinkBase melonJuice = new FoodDrinkBase(4, 0.6F, false, "melon_juice");
    public static FoodDrinkBase melonSlushie = new FoodDrinkBase(5, 0.8F, false, "melon_slushie");
    public static FoodDrinkBase orangeJuice = new FoodDrinkBase(5, 0.6F, false, "orange_juice");
    public static FoodDrinkBase orangeSlushie = new FoodDrinkBase(6, 0.8F, false, "orange_slushie");
    public static FoodDrinkBase strawberryJuice = new FoodDrinkBase(4, 0.6F, false, "strawberry_juice");
    public static FoodDrinkBase strawberrySlushie = new FoodDrinkBase(5, 0.8F, false, "strawberry_slushie");
    //Food
    public static FoodBase bacon = new FoodBase(6, 0.5F, true, "bacon");
    public static FoodBase beefJerky = new FoodBase(3, 0.5F, true, "beef_jerky");
    public static BigSandwich beefChickenSandwich = new BigSandwich("beef_chicken_sandwich");
    public static BigSandwich beefPorkSandwich = new BigSandwich("beef_pork_sandwich");
    public static FoodBase beefSandwich = new FoodBase(12, 0.7F, true, "beef_sandwich");
    public static FoodBase chickenNugget = new FoodBase(1, 0.3F, true, "chicken_nugget");
    public static FoodBase chickenSandwich = new FoodBase(12, 0.7F, true, "chicken_sandwich");
    public static BigSandwich chickenPorkSandwich = new BigSandwich("chicken_pork_sandwich");
    public static FoodBase corn = new FoodBase(6, 1.0F, false, "corn");
    public static FoodBase eggs = new FoodBase(4, 0.4F, false, "eggs");
    public static SeedFoodBase lemon = new SeedFoodBase(2, 0.2F, SDBlocks.lemonCrop, Blocks.FARMLAND, "lemon");
    public static FoodBase mixedSeeds = new FoodBase(6, 0.6F, false, "mixed_seeds");
    public static SeedFoodBase orange = new SeedFoodBase(4, 0.3F, SDBlocks.orangeCrop, Blocks.FARMLAND, "orange");
    public static FoodBase porkSandwich = new FoodBase(12, 0.7F, true, "pork_sandwich");
    public static FoodRawBacon rawBacon = new FoodRawBacon(2, 0.2F, false, "raw_bacon");
    public static FoodRawBeefSlice rawBeefSlice = new FoodRawBeefSlice(1, 0.2F, false, "raw_beef_slice");
    public static ItemBase rawCorn = new ItemBase("raw_corn");
    public static FoodBase roastedApple = new FoodBase(6, 0.7F, false, "roasted_apple");
    public static FoodBase roastedBeetrootSeeds = new FoodBase(2, 0.3F, false, "roasted_beetroot_seeds");
    public static FoodBase roastedMelonSeeds = new FoodBase(2, 0.3F, false, "roasted_melon_seeds");
    public static FoodBase roastedPumpkinSeeds = new FoodBase(2, 0.3F, false, "roasted_pumpkin_seeds");
    public static FoodBase roastedSeeds = new FoodBase(2, 0.3F, false, "roasted_seeds");
    public static FoodBase strawBerry = new FoodBase(2, 0.3F, false, "strawberry");
    public static FoodSugarCoatedBase sugarcoatedApple = new FoodSugarCoatedBase(4, 0.3F, false, "sugarcoated_apple");
    public static FoodSugarCoatedBase sugarcoatedLemon = new FoodSugarCoatedBase(2, 0.2F, false, "sugarcoated_lemon");
    public static FoodSugarCoatedBase sugarcoatedMelon = new FoodSugarCoatedBase(2, 0.4F, false, "sugarcoated_melon");
    public static FoodSugarCoatedBase sugarcoatedOrange = new FoodSugarCoatedBase(4, 0.3F, false, "sugarcoated_orange");
    public static FoodSugarCoatedBase sugarcoatedStrawberry = new FoodSugarCoatedBase(2, 0.3F, false, "sugarcoated_strawberry");
    public static FoodBase toast = new FoodBase(6, 1.0F, false, "toast");
    //Regen Stuff
    public static DiamondApple diamondApple = new DiamondApple(6, 1.3F, false, "diamond_apple");
    public static EmeraldApple emeraldApple = new EmeraldApple(7, 1.4F, false, "emerald_apple");
    public static EnchantedDiamondApple enchantedDiamondApple = new EnchantedDiamondApple(8, 1.5F, false, "enchanted_diamond_apple");
    public static EnchantedEmeraldApple enchantedEmeraldApple = new EnchantedEmeraldApple(9, 1.6F, false, "enchanted_emerald_apple");
    public static EnchantedIronApple enchantedIronApple = new EnchantedIronApple(4, 1.1F, false, "enchanted_iron_apple");
    public static HolyBread holyBread = new HolyBread(8, 1.2F, false, "holy_bread");
    public static IronApple ironApple = new IronApple(3, 1.2F, false, "iron_apple");

    public static void register(IForgeRegistry<Item> registry) {
        registry.registerAll(
                //Drinks
                appleJuice,
                appleSlushie,
                blackCoffee,
                carrotJuice,
                coffee,
                lemonade,
                melonJuice,
                melonSlushie,
                orangeJuice,
                orangeSlushie,
                strawberryJuice,
                strawberrySlushie,

                //Food
                bacon,
                beefJerky,
                beefChickenSandwich,
                beefPorkSandwich,
                beefSandwich,
                chickenNugget,
                chickenSandwich,
                chickenPorkSandwich,
                corn,
                eggs,
                lemon,
                mixedSeeds,
                orange,
                porkSandwich,
                rawBacon,
                rawBeefSlice,
                rawCorn,
                roastedApple,
                roastedBeetrootSeeds,
                roastedMelonSeeds,
                roastedPumpkinSeeds,
                roastedSeeds,
                strawBerry,
                sugarcoatedApple,
                sugarcoatedLemon,
                sugarcoatedMelon,
                sugarcoatedOrange,
                sugarcoatedStrawberry,
                toast,
                //Regen Stuff
                diamondApple,
                emeraldApple,
                enchantedDiamondApple,
                enchantedEmeraldApple,
                enchantedIronApple,
                holyBread,
                ironApple
        );

    }

    public static void registerModels() {
        //Drinks
        registerRender(appleJuice);
        registerRender(appleSlushie);
        registerRender(blackCoffee);
        registerRender(carrotJuice);
        registerRender(coffee);
        registerRender(lemonade);
        registerRender(melonJuice);
        registerRender(melonSlushie);
        registerRender(orangeJuice);
        registerRender(orangeSlushie);
        registerRender(strawberryJuice);
        registerRender(strawberrySlushie);

        //Food
        registerRender(bacon);
        registerRender(beefJerky);
        registerRender(beefChickenSandwich);
        registerRender(beefPorkSandwich);
        registerRender(beefSandwich);
        registerRender(chickenNugget);
        registerRender(chickenSandwich);
        registerRender(chickenPorkSandwich);
        registerRender(corn);
        registerRender(eggs);
        registerRender(lemon);
        registerRender(mixedSeeds);
        registerRender(orange);
        registerRender(porkSandwich);
        registerRender(rawBacon);
        registerRender(rawBeefSlice);
        registerRender(rawCorn);
        registerRender(roastedApple);
        registerRender(roastedBeetrootSeeds);
        registerRender(roastedMelonSeeds);
        registerRender(roastedPumpkinSeeds);
        registerRender(roastedSeeds);
        registerRender(strawBerry);
        registerRender(sugarcoatedApple);
        registerRender(sugarcoatedLemon);
        registerRender(sugarcoatedMelon);
        registerRender(sugarcoatedOrange);
        registerRender(sugarcoatedStrawberry);
        registerRender(toast);
        //Regen Stuff
        registerRender(diamondApple);
        registerRender(emeraldApple);
        registerRender(enchantedDiamondApple);
        registerRender(enchantedEmeraldApple);
        registerRender(enchantedIronApple);
        registerRender(holyBread);
        registerRender(ironApple);
    }

    public static void registerRender(Item item) {
        Minecraft.getMinecraft().getRenderItem().getItemModelMesher().register(item, 0, new ModelResourceLocation(SlurpiesDongles.modId + ":" + item.getUnlocalizedName().substring(5), "inventory"));

    }
}
