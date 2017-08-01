package com.rbs.slurpiesdongles.init;
import com.rbs.slurpiesdongles.SlurpiesDongles;
import com.rbs.slurpiesdongles.blocks.*;
import net.minecraft.block.Block;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.item.Item;
import net.minecraftforge.registries.IForgeRegistry;

/**
 * Created by Consular on 7/19/2017.
 */
public class SDBlocks {

    //Blocks
    public static BlockCustomBricks blueBricks = new BlockCustomBricks("blue_bricks");
    public static BlockCustomBricks cyanBricks = new BlockCustomBricks("cyan_bricks");
    public static BlockCustomBricks greenBricks = new BlockCustomBricks("green_bricks");
    public static BlockCustomBricks orangeBricks = new BlockCustomBricks("orange_bricks");
    public static BlockCustomBricks purpleBricks = new BlockCustomBricks("purple_bricks");
    public static BlockCustomBricks rainbowBricks = new BlockCustomBricks("rainbow_bricks");
    public static BlockCustomBricks yellowBricks = new BlockCustomBricks("yellow_bricks");
    public static BlockCustomGlowstone blueGlowstone = new BlockCustomGlowstone("blue_glowstone");
    public static BlockCustomGlowstone grayGlowstone = new BlockCustomGlowstone("gray_glowstone");
    public static BlockCustomGlowstone greenGlowstone = new BlockCustomGlowstone("green_glowstone");
    public static BlockCustomGlowstone orangeGlowstone = new BlockCustomGlowstone("orange_glowstone");
    public static BlockCustomGlowstone pinkGlowstone = new BlockCustomGlowstone("pink_glowstone");
    public static BlockCustomGlowstone purpleGlowstone = new BlockCustomGlowstone("purple_glowstone");
    public static BlockCustomGlowstone redGlowstone = new BlockCustomGlowstone("red_glowstone");
    public static BlockHardenedTopazBlock blockHardenedTopaz = new BlockHardenedTopazBlock("hardened_topaz_block");
    public static BlockLignite blockLignite = new BlockLignite("lignite_block");
    public static BlockReinforcedObsidian blockReinforcedObsidian = new BlockReinforcedObsidian("reinforced_obsidian");
    public static BlockRubyBlock blockRuby = new BlockRubyBlock("ruby_block");
    public static BlockSapphireBlock blockSapphire = new BlockSapphireBlock("sapphire_block");
    public static BlockTopazBlock blockTopaz = new BlockTopazBlock("topaz_block");

    //Bushes & Crops
    public static BlockCornCrop cornCrop = new BlockCornCrop("corn_crop");
    public static BlockLemonBush lemonBush = new BlockLemonBush("lemon_bush");
    public static BlockLemonCrop lemonCrop = new BlockLemonCrop("lemon_crop");
    public static BlockOrangeBush orangeBush = new BlockOrangeBush("orange_bush");
    public static BlockOrangeCrop orangeCrop = new BlockOrangeCrop("orange_crop");

    //Ores
    public static BlockLigniteOre oreLignite = new BlockLigniteOre("lignite_ore");
    public static BlockRubyOre oreRuby = new BlockRubyOre("ruby_ore");
    public static BlockSapphireOre oreSapphire = new BlockSapphireOre("sapphire_ore");
    public static BlockTopazOre oreTopaz = new BlockTopazOre("topaz_ore");
    //Nether Ores
    public static BlockNetherCoalOre netherCoalOre = new BlockNetherCoalOre("nether_coal_ore");
    public static BlockNetherDiamondOre netherDiamondOre = new BlockNetherDiamondOre("nether_diamond_ore");
    public static BlockNetherEmeraldOre netherEmeraldOre = new BlockNetherEmeraldOre("nether_emerald_ore");
    public static BlockNetherGoldOre netherGoldOre = new BlockNetherGoldOre("nether_gold_ore");
    public static BlockNetherIronOre netherIronOre = new BlockNetherIronOre("nether_iron_ore");
    public static BlockNetherLapisOre netherLapisOre = new BlockNetherLapisOre("nether_lapis_ore");
    public static BlockNetherRedstoneOre netherRedstoneOre = new BlockNetherRedstoneOre("nether_redstone_ore");

    public static void registerBlocks(final IForgeRegistry<Block> event) {
        final Block[] blocks = {

                //Blocks
                blueBricks,
                cyanBricks,
                greenBricks,
                orangeBricks,
                purpleBricks,
                rainbowBricks,
                yellowBricks,
                blueGlowstone,
                grayGlowstone,
                greenGlowstone,
                orangeGlowstone,
                pinkGlowstone,
                purpleGlowstone,
                redGlowstone,
                blockHardenedTopaz,
                blockLignite,
                blockReinforcedObsidian,
                blockRuby,
                blockSapphire,
                blockTopaz,

                //Bushes & Crops
                cornCrop,
                lemonBush,
                lemonCrop,
                orangeBush,
                orangeCrop,

                //Ores
                oreLignite,
                oreRuby,
                oreSapphire,
                oreTopaz,
                //Nether Ores
                netherCoalOre,
                netherDiamondOre,
                netherEmeraldOre,
                netherGoldOre,
                netherIronOre,
                netherLapisOre,
                netherRedstoneOre,
        };

        event.registerAll(blocks);
    }

    public static void registerModels() {

        //Blocks
        registerRender(blueBricks);
        registerRender(cyanBricks);
        registerRender(greenBricks);
        registerRender(orangeBricks);
        registerRender(purpleBricks);
        registerRender(rainbowBricks);
        registerRender(yellowBricks);
        registerRender(blueGlowstone);
        registerRender(grayGlowstone);
        registerRender(greenGlowstone);
        registerRender(orangeGlowstone);
        registerRender(pinkGlowstone);
        registerRender(purpleGlowstone);
        registerRender(redGlowstone);
        registerRender(blockHardenedTopaz);
        registerRender(blockLignite);
        registerRender(blockReinforcedObsidian);
        registerRender(blockRuby);
        registerRender(blockSapphire);
        registerRender(blockTopaz);

        //Bushes & Crops
        registerRender(cornCrop);
        registerRender(lemonBush);
        registerRender(lemonCrop);
        registerRender(orangeBush);
        registerRender(orangeCrop);

        //Ores
        registerRender(oreLignite);
        registerRender(oreRuby);
        registerRender(oreSapphire);
        registerRender(oreTopaz);
        //Nether Ores
        registerRender(netherCoalOre);
        registerRender(netherDiamondOre);
        registerRender(netherEmeraldOre);
        registerRender(netherGoldOre);
        registerRender(netherIronOre);
        registerRender(netherLapisOre);
        registerRender(netherRedstoneOre);

    }

    public static void registerItemBlocks(IForgeRegistry<Item> registry) {
        registry.registerAll(

                //Blocks
                blueBricks.createItemBlock(),
                cyanBricks.createItemBlock(),
                greenBricks.createItemBlock(),
                orangeBricks.createItemBlock(),
                purpleBricks.createItemBlock(),
                rainbowBricks.createItemBlock(),
                yellowBricks.createItemBlock(),
                blueGlowstone.createItemBlock(),
                grayGlowstone.createItemBlock(),
                greenGlowstone.createItemBlock(),
                orangeGlowstone.createItemBlock(),
                pinkGlowstone.createItemBlock(),
                purpleGlowstone.createItemBlock(),
                redGlowstone.createItemBlock(),
                blockHardenedTopaz.createItemBlock(),
                blockLignite.createItemBlock(),
                blockReinforcedObsidian.createItemBlock(),
                blockRuby.createItemBlock(),
                blockSapphire.createItemBlock(),
                blockTopaz.createItemBlock(),

                //Bushes & Crops
                cornCrop.createItemBlock(),
                lemonBush.createItemBlock(),
                lemonCrop.createItemBlock(),
                orangeBush.createItemBlock(),
                orangeCrop.createItemBlock(),


                //Ores
                oreLignite.createItemBlock(),
                oreRuby.createItemBlock(),
                oreSapphire.createItemBlock(),
                oreTopaz.createItemBlock(),
                //Nether Ores
                netherCoalOre.createItemBlock(),
                netherDiamondOre.createItemBlock(),
                netherEmeraldOre.createItemBlock(),
                netherGoldOre.createItemBlock(),
                netherIronOre.createItemBlock(),
                netherLapisOre.createItemBlock(),
                netherRedstoneOre.createItemBlock()

        );



    }
    public static void registerRender(Block block) {
        Item item = Item.getItemFromBlock(block);
        Minecraft.getMinecraft().getRenderItem().getItemModelMesher().register(item, 0, new ModelResourceLocation(SlurpiesDongles.modId + ":" + item.getUnlocalizedName().substring(5), "inventory"));
    }

}
