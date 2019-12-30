package com.rbs.slurpiesdongles.init;

import com.rbs.slurpiesdongles.Reference;
import com.rbs.slurpiesdongles.config.ConfigGeneral;
import com.rbs.slurpiesdongles.items.*;
import com.rbs.slurpiesdongles.items.charms.*;
import net.minecraft.block.Block;
import net.minecraft.fluid.Fluids;
import net.minecraft.item.BlockItem;
import net.minecraft.item.Item;
import net.minecraft.item.WallOrFloorItem;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.registries.ObjectHolder;

import java.util.ArrayList;
import java.util.List;

@Mod.EventBusSubscriber(modid = Reference.MODID, bus = Mod.EventBusSubscriber.Bus.MOD) //This line tells forge this class has events we want to listen to, we also tell forge we want to listen to the Mod bus. (This is new in 1.13)
@ObjectHolder(Reference.MODID) //We use ObjectHolder to let forge inject the item into our variables, this to make sure when people replace our item we use the correct one.


public class ModItems {
   //Charms
   public static Item absorption_charm = null;
 public static Item absorption_charm_tier_2 = null;
 public static Item fire_resistance_charm = null;
 public static Item night_vision_charm = null;
 public static Item regen_charm = null;
 public static Item regen_charm_tier_2 = null;
 public static Item speed_charm = null;
 public static Item speed_charm_tier_2 = null;
 public static Item strength_charm = null;
 public static Item strength_charm_tier_2 = null;
 public static Item water_breathing_charm = null;
    //Dusts
    public static final Item blue_glowstone_dust = null;
    public static final Item gray_glowstone_dust = null;
    public static final Item green_glowstone_dust = null;
    public static final Item orange_glowstone_dust = null;
    public static final Item pink_glowstone_dust = null;
    public static final Item purple_glowstone_dust = null;
    public static final Item red_glowstone_dust = null;
    //Gems
    public static final Item amazonite = null;
    public static final Item amethyst = null;
    public static final Item hardened_topaz = null;
    public static final Item peridot = null;
    public static final Item ruby = null;
    public static final Item sapphire = null;
    public static final Item topaz = null;
    //Actual Items
    public static final Item blender = null;
    public static Item infinite_water_bucket = null;
    public static final Item knife = null;
    public static Item nether_star_chunk = null;
    public static Item pops_sign = null;
    public static Item stone_rod = null;
    public static Item topaz_handle = null;
    public static final Item vme_upgrade = null;
    public static final Item vmh_upgrade = null;
    public static final Item vmp_upgrade = null;

    @SubscribeEvent
    public static void registerItems(RegistryEvent.Register<Item> event) {
        event.getRegistry().registerAll(
                //Dusts
                new ItemBase(new Item.Properties().group(Reference.tabSlurpiesDongles), "blue_glowstone_dust"),
                new ItemBase(new Item.Properties().group(Reference.tabSlurpiesDongles), "gray_glowstone_dust"),
                new ItemBase(new Item.Properties().group(Reference.tabSlurpiesDongles), "green_glowstone_dust"),
                new ItemBase(new Item.Properties().group(Reference.tabSlurpiesDongles), "orange_glowstone_dust"),
                new ItemBase(new Item.Properties().group(Reference.tabSlurpiesDongles), "pink_glowstone_dust"),
                new ItemBase(new Item.Properties().group(Reference.tabSlurpiesDongles), "purple_glowstone_dust"),
                new ItemBase(new Item.Properties().group(Reference.tabSlurpiesDongles), "red_glowstone_dust"),
                //Gems
                new ItemBase(new Item.Properties().group(Reference.tabSlurpiesDongles), "amazonite"),
                new ItemBase(new Item.Properties().group(Reference.tabSlurpiesDongles), "amethyst"),
                new ItemBase(new Item.Properties().group(Reference.tabSlurpiesDongles), "hardened_topaz"),
                new ItemBase(new Item.Properties().group(Reference.tabSlurpiesDongles), "peridot"),
                new ItemBase(new Item.Properties().group(Reference.tabSlurpiesDongles), "ruby"),
                new ItemBase(new Item.Properties().group(Reference.tabSlurpiesDongles), "sapphire"),
                new ItemBase(new Item.Properties().group(Reference.tabSlurpiesDongles), "topaz"),
                //Actual Items
                new ContainerItem(new Item.Properties().group(Reference.tabSlurpiesDongles), "blender"),
                new ItemBase(new Item.Properties().group(Reference.tabSlurpiesDongles), "hot_water"),
                new ContainerItem(new Item.Properties().group(Reference.tabSlurpiesDongles), "knife"),
                new WallOrFloorItem(ModBlocks.stone_torch, ModBlocks.wall_stone_torch, new Item.Properties().group(Reference.tabSlurpiesDongles)).setRegistryName(ModBlocks.stone_torch.getRegistryName()),
                new ItemBase(new Item.Properties().group(Reference.tabSlurpiesDongles), "vme_upgrade"),
                new ItemBase(new Item.Properties().group(Reference.tabSlurpiesDongles), "vmh_upgrade"),
                new ItemBase(new Item.Properties().group(Reference.tabSlurpiesDongles), "vmp_upgrade"),
                //Block Items
                //Blocks
                createItemBlockForBlock(ModBlocks.blue_bricks, new Item.Properties().group(Reference.tabSlurpiesDongles)),
                createItemBlockForBlock(ModBlocks.cyan_bricks, new Item.Properties().group(Reference.tabSlurpiesDongles)),
                createItemBlockForBlock(ModBlocks.green_bricks, new Item.Properties().group(Reference.tabSlurpiesDongles)),
                createItemBlockForBlock(ModBlocks.orange_bricks, new Item.Properties().group(Reference.tabSlurpiesDongles)),
                createItemBlockForBlock(ModBlocks.purple_bricks, new Item.Properties().group(Reference.tabSlurpiesDongles)),
                createItemBlockForBlock(ModBlocks.rainbow_bricks, new Item.Properties().group(Reference.tabSlurpiesDongles)),
                createItemBlockForBlock(ModBlocks.yellow_bricks, new Item.Properties().group(Reference.tabSlurpiesDongles)),
                createItemBlockForBlock(ModBlocks.blue_glowstone, new Item.Properties().group(Reference.tabSlurpiesDongles)),
                createItemBlockForBlock(ModBlocks.gray_glowstone, new Item.Properties().group(Reference.tabSlurpiesDongles)),
                createItemBlockForBlock(ModBlocks.green_glowstone, new Item.Properties().group(Reference.tabSlurpiesDongles)),
                createItemBlockForBlock(ModBlocks.orange_glowstone, new Item.Properties().group(Reference.tabSlurpiesDongles)),
                createItemBlockForBlock(ModBlocks.pink_glowstone, new Item.Properties().group(Reference.tabSlurpiesDongles)),
                createItemBlockForBlock(ModBlocks.purple_glowstone, new Item.Properties().group(Reference.tabSlurpiesDongles)),
                createItemBlockForBlock(ModBlocks.red_glowstone, new Item.Properties().group(Reference.tabSlurpiesDongles)),
                createItemBlockForBlock(ModBlocks.amazonite_block, new Item.Properties().group(Reference.tabSlurpiesDongles)),
                createItemBlockForBlock(ModBlocks.amethyst_block, new Item.Properties().group(Reference.tabSlurpiesDongles)),
                createItemBlockForBlock(ModBlocks.hardened_topaz_block, new Item.Properties().group(Reference.tabSlurpiesDongles)),
                createItemBlockForBlock(ModBlocks.peridot_block, new Item.Properties().group(Reference.tabSlurpiesDongles)),
                createItemBlockForBlock(ModBlocks.reinforced_obsidian, new Item.Properties().group(Reference.tabSlurpiesDongles)),
                createItemBlockForBlock(ModBlocks.ruby_block, new Item.Properties().group(Reference.tabSlurpiesDongles)),
                createItemBlockForBlock(ModBlocks.sapphire_block, new Item.Properties().group(Reference.tabSlurpiesDongles)),
                createItemBlockForBlock(ModBlocks.topaz_block, new Item.Properties().group(Reference.tabSlurpiesDongles)),
                //Crops
                createItemBlockForBlock(ModBlocks.cabbage_crop, new Item.Properties().group(Reference.tabSlurpiesDongles)),
                createItemBlockForBlock(ModBlocks.corn_crop, new Item.Properties().group(Reference.tabSlurpiesDongles)),
                createItemBlockForBlock(ModBlocks.lemon_crop, new Item.Properties().group(Reference.tabSlurpiesDongles)),
                createItemBlockForBlock(ModBlocks.orange_crop, new Item.Properties().group(Reference.tabSlurpiesDongles)),
                createItemBlockForBlock(ModBlocks.strawberry_crop, new Item.Properties().group(Reference.tabSlurpiesDongles)),
                createItemBlockForBlock(ModBlocks.tomato_crop, new Item.Properties().group(Reference.tabSlurpiesDongles)),
                createItemBlockForBlock(ModBlocks.wild_crops, new Item.Properties().group(Reference.tabSlurpiesDongles)),
                //Ores
                createItemBlockForBlock(ModBlocks.amazonite_ore, new Item.Properties().group(Reference.tabSlurpiesDongles)),
                createItemBlockForBlock(ModBlocks.amethyst_ore, new Item.Properties().group(Reference.tabSlurpiesDongles)),
                createItemBlockForBlock(ModBlocks.peridot_ore, new Item.Properties().group(Reference.tabSlurpiesDongles)),
                createItemBlockForBlock(ModBlocks.ruby_ore, new Item.Properties().group(Reference.tabSlurpiesDongles)),
                createItemBlockForBlock(ModBlocks.sapphire_ore, new Item.Properties().group(Reference.tabSlurpiesDongles)),
                createItemBlockForBlock(ModBlocks.topaz_ore, new Item.Properties().group(Reference.tabSlurpiesDongles)),
                //Nether Ores
                createItemBlockForBlock(ModBlocks.nether_coal_ore, new Item.Properties().group(Reference.tabSlurpiesDongles)),
                createItemBlockForBlock(ModBlocks.nether_diamond_ore, new Item.Properties().group(Reference.tabSlurpiesDongles)),
                createItemBlockForBlock(ModBlocks.nether_emerald_ore, new Item.Properties().group(Reference.tabSlurpiesDongles)),
                createItemBlockForBlock(ModBlocks.nether_gold_ore, new Item.Properties().group(Reference.tabSlurpiesDongles)),
                createItemBlockForBlock(ModBlocks.nether_iron_ore, new Item.Properties().group(Reference.tabSlurpiesDongles)),
                createItemBlockForBlock(ModBlocks.nether_lapis_ore, new Item.Properties().group(Reference.tabSlurpiesDongles)),
                createItemBlockForBlock(ModBlocks.nether_redstone_ore, new Item.Properties().group(Reference.tabSlurpiesDongles))
        );
     event.getRegistry().registerAll();
     //Actual Items
     if (ConfigGeneral.disableNetherStarChunk.get()) {
      event.getRegistry().register(nether_star_chunk = new ItemBase(new Item.Properties().group(Reference.tabSlurpiesDongles), "nether_star_chunk"));
     }
     if (ConfigGeneral.disablePopsSign.get()) {
      event.getRegistry().register(pops_sign = new ItemBase(new Item.Properties().group(Reference.tabSlurpiesDongles), "pops_sign"));
     }
     if (ConfigGeneral.disableTopazHandle.get()) {
      event.getRegistry().register(topaz_handle = new ItemBase(new Item.Properties().group(Reference.tabSlurpiesDongles), "topaz_handle"));
     }
        if (ConfigGeneral.disableStoneRod.get()) {
        event.getRegistry().register(stone_rod = new ItemBase(new Item.Properties().group(Reference.tabSlurpiesDongles), "stone_rod"));
        }
        if (ConfigGeneral.disableInfiniteWaterBucket.get()) {
            event.getRegistry().register(infinite_water_bucket = new InfiniteWaterBucket(new Item.Properties().group(Reference.tabSlurpiesDongles).maxStackSize(1), "infinite_water_bucket", Fluids.WATER));
        }

     //Charms
     if (ConfigGeneral.disableFireResistanceCharm.get()) {
      event.getRegistry().register(fire_resistance_charm = new FireCharm(new Item.Properties().group(Reference.tabSlurpiesDongles).maxStackSize(1), "fire_resistance_charm"));
     }
     if (ConfigGeneral.disableAbsorptionCharm.get()) {
      event.getRegistry().register(absorption_charm = new AbsorptionCharm(new Item.Properties().group(Reference.tabSlurpiesDongles).maxStackSize(1), "absorption_charm"));
     }
     if (ConfigGeneral.disableAbsorptionCharmTier2.get()) {
      event.getRegistry().register(absorption_charm_tier_2 = new AbsorptionCharmTier2(new Item.Properties().group(Reference.tabSlurpiesDongles).maxStackSize(1), "absorption_charm_tier_2"));
     }
     if (ConfigGeneral.disableNightVisionCharm.get()) {
      event.getRegistry().register(night_vision_charm = new NightVisionCharm(new Item.Properties().group(Reference.tabSlurpiesDongles).maxStackSize(1), "night_vision_charm"));
     }
     if (ConfigGeneral.disableRegenerationCharm.get()) {
      event.getRegistry().register(regen_charm = new RegenerationCharm(new Item.Properties().group(Reference.tabSlurpiesDongles).maxStackSize(1), "regen_charm"));
     }
     if (ConfigGeneral.disableRegenerationCharmTier2.get()) {
      event.getRegistry().register(regen_charm_tier_2 = new RegenerationCharmTier2(new Item.Properties().group(Reference.tabSlurpiesDongles).maxStackSize(1), "regen_charm_tier_2"));
     }
     if (ConfigGeneral.disableSpeedCharm.get()) {
      event.getRegistry().register(speed_charm = new SpeedCharm(new Item.Properties().group(Reference.tabSlurpiesDongles).maxStackSize(1), "speed_charm"));
     }
     if (ConfigGeneral.disableSpeedCharmTier2.get()) {
      event.getRegistry().register(speed_charm_tier_2 = new SpeedCharmTier2(new Item.Properties().group(Reference.tabSlurpiesDongles).maxStackSize(1), "speed_charm_tier_2"));
     }
     if (ConfigGeneral.disableStrengthCharm.get()) {
      event.getRegistry().register(strength_charm = new StrengthCharm(new Item.Properties().group(Reference.tabSlurpiesDongles).maxStackSize(1), "strength_charm"));
     }
     if (ConfigGeneral.disableStrengthCharmTier2.get()) {
      event.getRegistry().register(strength_charm_tier_2 = new StrengthCharmTier2(new Item.Properties().group(Reference.tabSlurpiesDongles).maxStackSize(1), "strength_charm_tier_2"));
     }
     if (ConfigGeneral.disableWaterbreathingCharm.get()) {
      event.getRegistry().register(water_breathing_charm = new WaterBreathingCharm(new Item.Properties().group(Reference.tabSlurpiesDongles).maxStackSize(1), "water_breathing_charm"));
     }
    }


    private static BlockItem createItemBlockForBlock (Block block, Item.Properties properties) {
        return (BlockItem) new BlockItem(block, properties).setRegistryName(block.getRegistryName());
    }


}
