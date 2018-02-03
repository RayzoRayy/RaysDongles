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
    public static BlockBlender blenderCyan = new BlockBlender("blender_cyan");
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
    public static BlockStrawBerryCrop strawberryCrop = new BlockStrawBerryCrop("strawberry_crop");

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
                blenderCyan,
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

                //Crops
                cornCrop,
                lemonCrop,
                orangeCrop,
                strawberryCrop,
        };
        event.registerAll(blocks);
        //Food
             if (ConfigPreInit.disableLemonBush == false) {
            event.register(lemonBush);
        }
            if (ConfigPreInit.disableOrangeBush == false) {
            event.register(orangeBush);
        }
        //Ore
            if (ConfigPreInit.disableLigniteOre == false) {
            event.register(oreLignite);
        }
            if (ConfigPreInit.disableRubyOre == false) {
            event.register(oreRuby);

            if (ConfigPreInit.disableSapphireOre == false) {
                event.register(oreSapphire);
            }

            if (ConfigPreInit.disableTopazOre == false) {
                event.register(oreTopaz);
            }
            //Nether Ores
            if (ConfigPreInit.disableNetherCoalOre == false) {
                event.register(netherCoalOre);
            }
            if (ConfigPreInit.disableNetherDiamondOre == false) {
                event.register(netherDiamondOre);
            }
            if (ConfigPreInit.disableNetherEmeraldOre == false) {
                event.register(netherEmeraldOre);
            }
            if (ConfigPreInit.disableNetherGoldOre == false) {
                event.register(netherGoldOre);
            }
            if (ConfigPreInit.disableNetherIronOre == false) {
                event.register(netherIronOre);
            }
            if (ConfigPreInit.disableNetherLapisOre == false) {
                event.register(netherLapisOre);
            }
            if (ConfigPreInit.disableNetherRedstoneOre == false) {
                event.register(netherRedstoneOre);
            }
        }
    }

    public static void registerModels() {

        //Blocks
        registerRender(blenderCyan);
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
        registerRender(lemonCrop);
        registerRender(orangeCrop);
        registerRender(strawberryCrop);

        //Food
        if (ConfigPreInit.disableLemonBush == false) {
            registerRender(lemonBush);
        }
        if (ConfigPreInit.disableOrangeBush == false) {
            registerRender(orangeBush);
        }
        //Ores
        if (ConfigPreInit.disableLigniteOre == false) {
            registerRender(oreLignite);
        }
        if (ConfigPreInit.disableRubyOre == false) {
            registerRender(oreRuby);

            if (ConfigPreInit.disableSapphireOre == false) {
                registerRender(oreSapphire);
            }

            if (ConfigPreInit.disableTopazOre == false) {

                registerRender(oreTopaz);
            }
            //Nether Ores
            if (ConfigPreInit.disableNetherCoalOre == false) {
                registerRender(netherCoalOre);
            }
            if (ConfigPreInit.disableNetherDiamondOre == false) {
                registerRender(netherDiamondOre);
            }
            if (ConfigPreInit.disableNetherEmeraldOre == false) {
                registerRender(netherEmeraldOre);
            }
            if (ConfigPreInit.disableNetherGoldOre == false) {
                registerRender(netherGoldOre);
            }
            if (ConfigPreInit.disableNetherIronOre == false) {
                registerRender(netherIronOre);
            }
            if (ConfigPreInit.disableNetherLapisOre == false) {
                registerRender(netherLapisOre);
            }
            if (ConfigPreInit.disableNetherRedstoneOre == false) {
                registerRender(netherRedstoneOre);
            }
        }
    }


    public static void registerItemBlocks(IForgeRegistry<Item> registry) {
        registry.registerAll(

                //Blocks
                blenderCyan.createItemBlock(),
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
                lemonCrop.createItemBlock(),
                orangeCrop.createItemBlock(),
                strawberryCrop.createItemBlock()


        );
        //Food
        if (ConfigPreInit.disableLemonBush == false) {
            registry.register(lemonBush.createItemBlock());
        }
        if (ConfigPreInit.disableOrangeBush == false) {
            registry.register(orangeBush.createItemBlock());
        }
        //Ores
        if (ConfigPreInit.disableLigniteOre == false) {
            registry.register(oreLignite.createItemBlock());
        }
        if (ConfigPreInit.disableRubyOre == false) {
            registry.register(oreRuby.createItemBlock());

            if (ConfigPreInit.disableSapphireOre == false) {
                registry.register(oreSapphire.createItemBlock());
            }

            if (ConfigPreInit.disableTopazOre == false) {

                registry.register(oreTopaz.createItemBlock());
            }
            //Nether Ores
            if (ConfigPreInit.disableNetherCoalOre == false) {
                registry.register(netherCoalOre.createItemBlock());
            }
            if (ConfigPreInit.disableNetherDiamondOre == false) {
                registry.register(netherDiamondOre.createItemBlock());
            }
            if (ConfigPreInit.disableNetherEmeraldOre == false) {
                registry.register(netherEmeraldOre.createItemBlock());
            }
            if (ConfigPreInit.disableNetherGoldOre == false) {
                registry.register(netherGoldOre.createItemBlock());
            }
            if (ConfigPreInit.disableNetherIronOre == false) {
                registry.register(netherIronOre.createItemBlock());
            }
            if (ConfigPreInit.disableNetherLapisOre == false) {
                registry.register(netherLapisOre.createItemBlock());
            }
            if (ConfigPreInit.disableNetherRedstoneOre == false) {
                registry.register(netherRedstoneOre.createItemBlock());
            }
        }
    }
    public static void registerRender(Block block) {
        Item item = Item.getItemFromBlock(block);
        Minecraft.getMinecraft().getRenderItem().getItemModelMesher().register(item, 0, new ModelResourceLocation(SlurpiesDongles.modId + ":" + item.getUnlocalizedName().substring(5), "inventory"));
    }

}
