package com.rbs.slurpiesdongles.init;

import com.rbs.slurpiesdongles.SlurpiesDongles;
import com.rbs.slurpiesdongles.food.*;
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
    public static FoodDrinkBase appleJuice = new FoodDrinkBase(5, 0.7F, false, "apple_juice");
    public static DrinkBlackCoffee blackCoffee = new DrinkBlackCoffee(2, 0.6F, false, "black_coffee");
    public static FoodDrinkBase carrotJuice = new FoodDrinkBase(4, 0.5F, false, "carrot_juice");
    public static DrinkCoffee coffee = new DrinkCoffee(4, 0.8F, false, "coffee");
    public static FoodDrinkBase lemonade = new FoodDrinkBase(4, 0.6F, false, "lemonade");
    public static FoodDrinkBase melonJuice = new FoodDrinkBase(4, 0.4F, false, "melon_juice");
    public static FoodDrinkBase melonSlushie = new FoodDrinkBase(6, 0.7F, false, "melon_slushie");
    public static FoodDrinkBase orangeJuice = new FoodDrinkBase(5, 0.6F, false, "orange_juice");
    //Food
    public static FoodBase bacon = new FoodBase(6, 0.8F, true, "bacon");
    public static FoodBase beefJerky = new FoodBase(3, 0.5F, true, "beef_jerky");
    public static FoodBase chickenNugget = new FoodBase(1, 0.3F, true, "chicken_nugget");
    public static FoodBase corn = new FoodBase(5, 0.4F, false, "corn");
    public static FoodBase eggs = new FoodBase(4, 0.8F, false, "eggs");
    public static FoodHolyBread holyBread = new FoodHolyBread(8, 1.5F, false, "holy_bread");
    public static SeedFoodBase lemon = new SeedFoodBase(2, 0.2F, SDBlocks.lemonCrop, Blocks.FARMLAND, "lemon");
    public static FoodBase mixedSeeds = new FoodBase(6, 0.6F, false, "mixed_seeds");
    public static SeedFoodBase orange = new SeedFoodBase(4, 0.7F, SDBlocks.orangeCrop, Blocks.FARMLAND, "orange");
    public static FoodRawBacon rawBacon = new FoodRawBacon(2, 0.2F, false, "raw_bacon");
    public static FoodRawBeefSlice rawBeefSlice = new FoodRawBeefSlice(1, 0.2F, false, "raw_beef_slice");
    public static FoodBase roastedApple = new FoodBase(6, 0.7F, false, "roasted_apple");
    public static FoodBase roastedBeetrootSeeds = new FoodBase(2, 0.3F, false, "roasted_beetroot_seeds");
    public static FoodBase roastedMelonSeeds = new FoodBase(2, 0.3F, false, "roasted_melon_seeds");
    public static FoodBase roastedPumpkinSeeds = new FoodBase(2, 0.3F, false, "roasted_pumpkin_seeds");
    public static FoodBase roastedSeeds = new FoodBase(2, 0.3F, false, "roasted_seeds");
    public static FoodBase toast = new FoodBase(6, 1.0F, false, "toast");

    public static void register(IForgeRegistry<Item> registry) {
        registry.registerAll(
                //Drinks
                appleJuice,
                blackCoffee,
                carrotJuice,
                coffee,
                lemonade,
                melonJuice,
                melonSlushie,
                orangeJuice,

                //Food
                bacon,
                beefJerky,
                chickenNugget,
                corn,
                eggs,
                holyBread,
                lemon,
                mixedSeeds,
                orange,
                rawBacon,
                rawBeefSlice,
                roastedApple,
                roastedBeetrootSeeds,
                roastedMelonSeeds,
                roastedPumpkinSeeds,
                roastedSeeds,
                toast

        );

    }

    public static void registerModels() {
        //Drinks
        registerRender(appleJuice);
        registerRender(blackCoffee);
        registerRender(carrotJuice);
        registerRender(coffee);
        registerRender(lemonade);
        registerRender(melonJuice);
        registerRender(melonSlushie);
        registerRender(orangeJuice);

        //Food
        registerRender(bacon);
        registerRender(beefJerky);
        registerRender(chickenNugget);
        registerRender(corn);
        registerRender(eggs);
        registerRender(holyBread);
        registerRender(lemon);
        registerRender(mixedSeeds);
        registerRender(orange);
        registerRender(rawBacon);
        registerRender(rawBeefSlice);
        registerRender(roastedApple);
        registerRender(roastedBeetrootSeeds);
        registerRender(roastedMelonSeeds);
        registerRender(roastedPumpkinSeeds);
        registerRender(roastedSeeds);
        registerRender(toast);
    }

    public static void registerRender(Item item) {
        Minecraft.getMinecraft().getRenderItem().getItemModelMesher().register(item, 0, new ModelResourceLocation(SlurpiesDongles.modId + ":" + item.getUnlocalizedName().substring(5), "inventory"));

    }
}
