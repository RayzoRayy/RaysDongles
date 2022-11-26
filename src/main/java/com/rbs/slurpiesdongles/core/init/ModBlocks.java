package com.rbs.slurpiesdongles.core.init;

import com.rbs.slurpiesdongles.SlurpiesDongles;
import com.rbs.slurpiesdongles.common.blocks.*;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.StandingAndWallBlockItem;
import net.minecraft.world.level.block.*;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.material.Material;
import net.minecraftforge.registries.RegistryObject;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

import java.util.function.Function;
import java.util.function.Supplier;

import static com.rbs.slurpiesdongles.core.itemgroup.RDItemGroup.*;
import static net.minecraft.world.level.block.Blocks.*;
public class ModBlocks {

    public static final DeferredRegister<Block> BLOCKS = DeferredRegister.create(ForgeRegistries.BLOCKS, SlurpiesDongles.MOD_ID);

    public static final RegistryObject<Block> BLUE_BRICKS = register("blue_bricks",
            () -> new Block(BlockBehaviour.Properties.copy(BRICKS)
                    .requiresCorrectToolForDrops()));
    public static final RegistryObject<Block> CYAN_BRICKS = register("cyan_bricks",
            () -> new Block(BlockBehaviour.Properties.copy(BRICKS)
                    .requiresCorrectToolForDrops()));
    public static final RegistryObject<Block> GREEN_BRICKS = register("green_bricks",
            () -> new Block(BlockBehaviour.Properties.copy(BRICKS)
                    .requiresCorrectToolForDrops()));
    public static final RegistryObject<Block> ORANGE_BRICKS = register("orange_bricks",
            () -> new Block(BlockBehaviour.Properties.copy(BRICKS)
                    .requiresCorrectToolForDrops()));
    public static final RegistryObject<Block> PURPLE_BRICKS = register("purple_bricks",
            () -> new Block(BlockBehaviour.Properties.copy(BRICKS)
                    .requiresCorrectToolForDrops()));
    public static final RegistryObject<Block> RAINBOW_BRICKS = register("rainbow_bricks",
            () -> new Block(BlockBehaviour.Properties.copy(BRICKS)
                    .requiresCorrectToolForDrops()));
    public static final RegistryObject<Block> YELLOW_BRICKS = register("yellow_bricks",
            () -> new Block(BlockBehaviour.Properties.copy(BRICKS)
                    .requiresCorrectToolForDrops()));
    //Glowstone
    public static final RegistryObject<Block> BLUE_GLOWSTONE = register("blue_glowstone",
            () -> new Block(BlockBehaviour.Properties.copy(GLOWSTONE)));
    public static final RegistryObject<Block> GRAY_GLOWSTONE = register("gray_glowstone",
            () -> new Block(BlockBehaviour.Properties.copy(GLOWSTONE)));
    public static final RegistryObject<Block> GREEN_GLOWSTONE = register("green_glowstone",
            () -> new Block(BlockBehaviour.Properties.copy(GLOWSTONE)));
    public static final RegistryObject<Block> ORANGE_GLOWSTONE = register("orange_glowstone",
            () -> new Block(BlockBehaviour.Properties.copy(GLOWSTONE)));
    public static final RegistryObject<Block> PINK_GLOWSTONE = register("pink_glowstone",
            () -> new Block(BlockBehaviour.Properties.copy(GLOWSTONE)));
    public static final RegistryObject<Block> PURPLE_GLOWSTONE = register("purple_glowstone",
            () -> new Block(BlockBehaviour.Properties.copy(GLOWSTONE)));
    public static final RegistryObject<Block> RED_GLOWSTONE = register("red_glowstone",
            () -> new Block(BlockBehaviour.Properties.copy(GLOWSTONE)));
    public static final RegistryObject<Block> REINFORCED_OBSIDIAN = register("reinforced_obsidian",
            () -> new Block(BlockBehaviour.Properties.of(Material.STONE)
                    .strength(100.0F, 2400.0F)
                    .requiresCorrectToolForDrops()));
    public static final RegistryObject<Block> RUBY_BLOCK = register("ruby_block",
            () -> new Block(BlockBehaviour.Properties.of(Material.STONE)
                    .strength(5.0F, 6.0F)
                    .requiresCorrectToolForDrops()));
    public static final RegistryObject<TorchBlock> STONE_TORCH = register("stone_torch",
            () -> new TorchBlock(BlockBehaviour.Properties.of(Material.DECORATION)
                    .noCollission()
                    .strength(0)
                    .lightLevel(state -> 8)
                    .sound(SoundType.STONE),
                    ParticleTypes.FLAME),
            bro -> getStoneTorchItem());

    public static final RegistryObject<Block> TOPAZ_BLOCK = register("topaz_block",
            () -> new Block(BlockBehaviour.Properties.of(Material.STONE)
                    .strength(5.0F, 6.0F)
                    .requiresCorrectToolForDrops()));

    public static final RegistryObject<WallTorchBlock> WALL_STONE_TORCH = registerBlockNoItem("wall_stone_torch",
            () -> new WallTorchBlock(BlockBehaviour.Properties.of(Material.DECORATION)
                    .noCollission()
                    .strength(0)
                    .sound(SoundType.STONE)
                    .lightLevel(state -> 8)
                    .lootFrom(STONE_TORCH::get),
                    ParticleTypes.FLAME),
                    tabSlurpiesDongles);

    //Crops
    public static final RegistryObject<Block> CABBAGE_CROP = register("cabbage_crop",
            () -> new CropBlock(BlockBehaviour.Properties.copy(CARROTS)));
    public static final RegistryObject<Block> CORN_CROP = register("corn_crop",
            () -> new CornCropBlock(BlockBehaviour.Properties.copy(WHEAT)));
    public static final RegistryObject<Block> LEMON_CROP = register("lemon_crop",
            () -> new LemonCropBlock(BlockBehaviour.Properties.copy(CARROTS)));
    public static final RegistryObject<Block> ORANGE_CROP = register("orange_crop",
            () -> new OrangeCropBlock(BlockBehaviour.Properties.copy(CARROTS)));
    public static final RegistryObject<Block> STRAWBERRY_CROP = register("strawberry_crop",
            () -> new StrawberryCropBlock(BlockBehaviour.Properties.copy(WHEAT)));
    public static final RegistryObject<Block> TOMATO_CROP = register("tomato_crop",
            () -> new TomatoCropBlock(BlockBehaviour.Properties.copy(CARROTS)));
    public static final RegistryObject<Block> WILD_CROPS = register("wild_crops",
            () -> new TallGrassBlock(BlockBehaviour.Properties.copy(GRASS)));

    //Ores
    public static final RegistryObject<Block> RUBY_ORE = register("ruby_ore",
            () -> new DropExperienceBlock(BlockBehaviour.Properties.of(Material.STONE)
                    .strength(3.0F, 3.0F)
                    .requiresCorrectToolForDrops()));
    public static final RegistryObject<Block> DEEPSLATE_RUBY_ORE = register("deepslate_ruby_ore",
            () -> new DropExperienceBlock(BlockBehaviour.Properties.of(Material.STONE)
                    .strength(3.0F, 3.0F)
                    .requiresCorrectToolForDrops()));
    public static final RegistryObject<Block> TOPAZ_ORE = register("topaz_ore",
            () -> new DropExperienceBlock(BlockBehaviour.Properties.of(Material.STONE)
                    .strength(3.0F, 3.0F)
                    .requiresCorrectToolForDrops()));
    public static final RegistryObject<Block> DEEPSLATE_TOPAZ_ORE = register("deepslate_topaz_ore",
            () -> new DropExperienceBlock(BlockBehaviour.Properties.of(Material.STONE)
                    .strength(3.0F, 3.0F)
                    .requiresCorrectToolForDrops()));


    private static <T extends Block> RegistryObject<T> registerBlock(String name, Supplier<T> block, CreativeModeTab tab) {
        RegistryObject<T> toReturn = BLOCKS.register(name, block);
        return toReturn;
    }
    private static <T extends Block> RegistryObject<T> registerBlockNoItem(String name, Supplier<T> block, CreativeModeTab tab) {
        RegistryObject<T> tooReturn = BLOCKS.register(name, block);
        return tooReturn;
    }
    private static <T extends Block> RegistryObject<T> register(String name, Supplier<T> block, Function<RegistryObject<T>, Supplier<? extends BlockItem>> item) {
        RegistryObject<T> ret = registerBlock(name, block, tabSlurpiesDongles);
        ModItems.ITEMS.register(name, item.apply(ret));
        return ret;
    }
    private static <T extends Block> RegistryObject<T> register(String name, Supplier<T> block) {
        return register(name, block, ModBlocks::defaultItem);
    }
    private static <T extends Block> Supplier<BlockItem> defaultItem(RegistryObject<T> block) {
        return () -> new BlockItem(block.get(), new Item.Properties().tab(tabSlurpiesDongles));
    }

        private static Supplier<BlockItem> getStoneTorchItem () {
            return () -> new StandingAndWallBlockItem(STONE_TORCH.get(), WALL_STONE_TORCH.get(), new Item.Properties());
        }
}