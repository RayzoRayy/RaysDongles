package com.rbs.slurpiesdongles.init;

import com.rbs.slurpiesdongles.Reference;
import com.rbs.slurpiesdongles.blocks.*;
import com.rbs.slurpiesdongles.config.ConfigGeneral;
import com.rbs.slurpiesdongles.helpers.HarvestLevelHelper;
import net.minecraft.block.Block;
import net.minecraft.block.SoundType;
import net.minecraft.block.TallGrassBlock;
import net.minecraft.block.TorchBlock;
import net.minecraft.block.material.Material;
import net.minecraft.block.material.MaterialColor;
import net.minecraft.client.audio.Sound;
import net.minecraft.item.Item;
import net.minecraft.item.WallOrFloorItem;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.registries.ObjectHolder;

import java.util.ArrayList;
import java.util.List;

@Mod.EventBusSubscriber(modid = Reference.MODID, bus = Mod.EventBusSubscriber.Bus.MOD)//This line tells forge this class has events we want to listen to, we also tell forge we want to listen to the Mod bus. (This is new in 1.13)
@ObjectHolder(Reference.MODID) //We use ObjectHolder to let forge inject the block into our variables, this to make sure when people replace our block we use the correct one.
public class ModBlocks {
//Blocks
    public static final Block blue_bricks = null;
    public static final Block cyan_bricks = null;
    public static final Block green_bricks = null;
    public static final Block orange_bricks = null;
    public static final Block purple_bricks = null;
    public static final Block rainbow_bricks = null;
    public static final Block yellow_bricks = null;
    public static final Block blue_glowstone = null;
    public static final Block gray_glowstone = null;
    public static final Block green_glowstone = null;
    public static final Block orange_glowstone = null;
    public static final Block pink_glowstone = null;
    public static final Block purple_glowstone = null;
    public static final Block red_glowstone = null;
    public static final Block amazonite_block = null;
    public static final Block amethyst_block = null;
    public static final Block hardened_topaz_block = null;
    public static final Block peridot_block = null;
    public static final Block reinforced_obsidian = null;
    public static final Block ruby_block = null;
    public static final Block sapphire_block = null;
    public static final Block stone_torch = null;
    public static final Block wall_stone_torch = null;
    public static final Block topaz_block = null;
    //Crops
    public static final Block cabbage_crop = null;
    public static final Block corn_crop = null;
    public static final Block lemon_crop = null;
    public static final Block orange_crop = null;
    public static final Block strawberry_crop = null;
    public static final Block tomato_crop = null;
    public static final Block wild_crops = null;
    //Ores
    public static final Block amazonite_ore = null;
    public static final Block amethyst_ore = null;
    public static final Block peridot_ore = null;
    public static final Block ruby_ore = null;
    public static final Block sapphire_ore = null;
    public static final Block topaz_ore = null;
    //Nether Ores
    public static final Block nether_coal_ore = null;
    public static final Block nether_diamond_ore = null;
    public static final Block nether_emerald_ore = null;
    public static final Block nether_gold_ore = null;
    public static final Block nether_iron_ore = null;
    public static final Block nether_lapis_ore = null;
    public static final Block nether_redstone_ore = null;
    @SubscribeEvent
    public static void registerBlocks(RegistryEvent.Register<Block> event) {
        event.getRegistry().registerAll(
                //Blocks
                new CustomBricks(Block.Properties.create(Material.ROCK).hardnessAndResistance(2.0F, 6.0F), HarvestLevelHelper.STONE, "blue_bricks"),
                new CustomBricks(Block.Properties.create(Material.ROCK).hardnessAndResistance(2.0F, 6.0F), HarvestLevelHelper.STONE, "cyan_bricks"),
                new CustomBricks(Block.Properties.create(Material.ROCK).hardnessAndResistance(2.0F, 6.0F), HarvestLevelHelper.STONE, "green_bricks"),
                new CustomBricks(Block.Properties.create(Material.ROCK).hardnessAndResistance(2.0F, 6.0F), HarvestLevelHelper.STONE, "orange_bricks"),
                new CustomBricks(Block.Properties.create(Material.ROCK).hardnessAndResistance(2.0F, 6.0F), HarvestLevelHelper.STONE, "purple_bricks"),
                new CustomBricks(Block.Properties.create(Material.ROCK).hardnessAndResistance(2.0F, 6.0F), HarvestLevelHelper.STONE, "rainbow_bricks"),
                new CustomBricks(Block.Properties.create(Material.ROCK).hardnessAndResistance(2.0F, 6.0F), HarvestLevelHelper.STONE, "yellow_bricks"),
                new CustomGlowstone(Block.Properties.create(Material.GLASS).hardnessAndResistance(0.3F, 0.3F).lightValue(15).sound(SoundType.GLASS), HarvestLevelHelper.WOOD, "blue_glowstone"),
                new CustomGlowstone(Block.Properties.create(Material.GLASS).hardnessAndResistance(0.3F, 0.3F).lightValue(15).sound(SoundType.GLASS), HarvestLevelHelper.WOOD, "gray_glowstone"),
                new CustomGlowstone(Block.Properties.create(Material.GLASS).hardnessAndResistance(0.3F, 0.3F).lightValue(15).sound(SoundType.GLASS), HarvestLevelHelper.WOOD, "green_glowstone"),
                new CustomGlowstone(Block.Properties.create(Material.GLASS).hardnessAndResistance(0.3F, 0.3F).lightValue(15).sound(SoundType.GLASS), HarvestLevelHelper.WOOD, "orange_glowstone"),
                new CustomGlowstone(Block.Properties.create(Material.GLASS).hardnessAndResistance(0.3F, 0.3F).lightValue(15).sound(SoundType.GLASS), HarvestLevelHelper.WOOD, "pink_glowstone"),
                new CustomGlowstone(Block.Properties.create(Material.GLASS).hardnessAndResistance(0.3F, 0.3F).lightValue(15).sound(SoundType.GLASS), HarvestLevelHelper.WOOD, "purple_glowstone"),
                new CustomGlowstone(Block.Properties.create(Material.GLASS).hardnessAndResistance(0.3F, 0.3F).lightValue(15).sound(SoundType.GLASS), HarvestLevelHelper.WOOD, "red_glowstone"),
                new BlockBase(Block.Properties.create(Material.ROCK).hardnessAndResistance(5.0F, 6.0F), HarvestLevelHelper.DIAMOND, "amazonite_block"),
                new BlockBase(Block.Properties.create(Material.ROCK).hardnessAndResistance(5.0F, 6.0F), HarvestLevelHelper.DIAMOND, "amethyst_block"),
                new BlockBase(Block.Properties.create(Material.ROCK).hardnessAndResistance(10.0F, 25.0F), HarvestLevelHelper.DIAMOND, "hardened_topaz_block"),
                new BlockBase(Block.Properties.create(Material.ROCK).hardnessAndResistance(5.0F, 6.0F), HarvestLevelHelper.STONE, "peridot_block"),
                new ReinforcedObsidian(Block.Properties.create(Material.ROCK).hardnessAndResistance(100.0F, 2400.0F), HarvestLevelHelper.DIAMOND, "reinforced_obsidian"),
                new BlockBase(Block.Properties.create(Material.ROCK).hardnessAndResistance(5.0F, 6.0F), HarvestLevelHelper.IRON, "ruby_block"),
                new BlockBase(Block.Properties.create(Material.ROCK).hardnessAndResistance(5.0F, 6.0F), HarvestLevelHelper.STONE, "sapphire_block"),
                new StoneTorch(Block.Properties.create(Material.MISCELLANEOUS).doesNotBlockMovement().hardnessAndResistance(0.0F, 0.0F).lightValue(12), SoundType.STONE, "stone_torch"),
                new WallStoneTorch(Block.Properties.create(Material.MISCELLANEOUS).doesNotBlockMovement().hardnessAndResistance(0.0F, 0.0F).lightValue(12), SoundType.STONE, "wall_stone_torch"),
                new BlockBase(Block.Properties.create(Material.ROCK).hardnessAndResistance(5.0F, 6.0F), HarvestLevelHelper.DIAMOND, "topaz_block"),
                //Crops
                new CabbageCrop(Block.Properties.create(Material.PLANTS).doesNotBlockMovement().tickRandomly().sound(SoundType.CROP).hardnessAndResistance(0.0F), "cabbage_crop"),
                new CornCrop(Block.Properties.create(Material.PLANTS).doesNotBlockMovement().tickRandomly().sound(SoundType.CROP).hardnessAndResistance(0.0F), "corn_crop"),
                new LemonCrop(Block.Properties.create(Material.PLANTS).doesNotBlockMovement().tickRandomly().sound(SoundType.CROP).hardnessAndResistance(0.0F), "lemon_crop"),
                new OrangeCrop(Block.Properties.create(Material.PLANTS).doesNotBlockMovement().tickRandomly().sound(SoundType.CROP).hardnessAndResistance(0.0F), "orange_crop"),
                new StrawberryCrop(Block.Properties.create(Material.PLANTS).doesNotBlockMovement().tickRandomly().sound(SoundType.CROP).hardnessAndResistance(0.0F), "strawberry_crop"),
                new TomatoCrop(Block.Properties.create(Material.PLANTS).doesNotBlockMovement().tickRandomly().sound(SoundType.CROP).hardnessAndResistance(0.0F), "tomato_crop"),
                new WildCrops(Block.Properties.create(Material.PLANTS).doesNotBlockMovement().sound(SoundType.PLANT).hardnessAndResistance(0), "wild_crops" ),
                //Ores
                new AmazoniteOre(Block.Properties.create(Material.ROCK).hardnessAndResistance(3.0F, 3.0F), HarvestLevelHelper.DIAMOND, "amazonite_ore"),
                new AmethystOre(Block.Properties.create(Material.ROCK).hardnessAndResistance(3.0F, 3.0F), HarvestLevelHelper.DIAMOND, "amethyst_ore"),
                new PeridotOre(Block.Properties.create(Material.ROCK).hardnessAndResistance(3.0F, 3.0F), HarvestLevelHelper.STONE, "peridot_ore"),
                new RubyOre(Block.Properties.create(Material.ROCK).hardnessAndResistance(3.0F, 3.0F), HarvestLevelHelper.STONE, "ruby_ore"),
                new SapphireOre(Block.Properties.create(Material.ROCK).hardnessAndResistance(3.0F, 3.0F), HarvestLevelHelper.STONE, "sapphire_ore"),
                new TopazOre(Block.Properties.create(Material.ROCK).hardnessAndResistance(3.0F, 3.0F), HarvestLevelHelper.DIAMOND, "topaz_ore"),
                //Nether Ores
                new NetherCoalOre(Block.Properties.create(Material.ROCK).hardnessAndResistance(3.0F, 3.0F), HarvestLevelHelper.WOOD, "nether_coal_ore"),
                new NetherDiamondOre(Block.Properties.create(Material.ROCK).hardnessAndResistance(3.0F, 3.0F), HarvestLevelHelper.IRON, "nether_diamond_ore"),
                new NetherEmeraldOre(Block.Properties.create(Material.ROCK).hardnessAndResistance(3.0F, 3.0F), HarvestLevelHelper.IRON, "nether_emerald_ore"),
                new NetherGoldOre(Block.Properties.create(Material.ROCK).hardnessAndResistance(3.0F, 3.0F), HarvestLevelHelper.IRON, "nether_gold_ore"),
                new NetherIronOre(Block.Properties.create(Material.ROCK).hardnessAndResistance(3.0F, 3.0F), HarvestLevelHelper.STONE, "nether_iron_ore"),
                new NetherLapisOre(Block.Properties.create(Material.ROCK).hardnessAndResistance(3.0F, 3.0F), HarvestLevelHelper.STONE, "nether_lapis_ore"),
                new NetherRedstoneOre(Block.Properties.create(Material.ROCK).hardnessAndResistance(3.0F, 3.0F), HarvestLevelHelper.IRON, "nether_redstone_ore")
        );
    }
}
