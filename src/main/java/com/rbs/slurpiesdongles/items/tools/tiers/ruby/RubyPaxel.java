package com.rbs.slurpiesdongles.items.tools.tiers.ruby;

import com.google.common.collect.ImmutableMap;
import com.google.common.collect.Maps;
import com.google.common.collect.Sets;
import com.rbs.slurpiesdongles.Reference;
import com.rbs.slurpiesdongles.init.ModBlocks;
import com.rbs.slurpiesdongles.init.ModItems;
import net.minecraft.block.*;
import net.minecraft.block.material.Material;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.enchantment.Enchantment;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.enchantment.Enchantments;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.inventory.EquipmentSlotType;
import net.minecraft.item.*;
import net.minecraft.util.ActionResultType;
import net.minecraft.util.Direction;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.SoundEvents;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.StringTextComponent;
import net.minecraft.util.text.TextFormatting;
import net.minecraft.world.World;
import net.minecraftforge.common.ToolType;
import net.minecraftforge.common.util.Constants;

import javax.annotation.Nullable;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class RubyPaxel extends ToolItem {
    public ItemTier material;
    //Effective ON is the blocks that the Paxel can mine with the proper efficiency
    public static final Set<Block> EFFECTIVE_ON = Sets.newHashSet(Blocks.ACTIVATOR_RAIL, Blocks.COAL_ORE,
            Blocks.COBBLESTONE, Blocks.DETECTOR_RAIL, Blocks.DIAMOND_BLOCK, Blocks.DIAMOND_ORE, Blocks.POWERED_RAIL,
            Blocks.GOLD_BLOCK, Blocks.GOLD_ORE, Blocks.ICE, Blocks.IRON_BLOCK, Blocks.IRON_ORE, Blocks.LAPIS_BLOCK,
            Blocks.LAPIS_ORE, Blocks.MOSSY_COBBLESTONE, Blocks.NETHERRACK, Blocks.PACKED_ICE, Blocks.BLUE_ICE,
            Blocks.RAIL, Blocks.REDSTONE_ORE, Blocks.SANDSTONE, Blocks.CHISELED_SANDSTONE, Blocks.CUT_SANDSTONE,
            Blocks.CHISELED_RED_SANDSTONE, Blocks.CUT_RED_SANDSTONE, Blocks.RED_SANDSTONE, Blocks.STONE, Blocks.GRANITE,
            Blocks.POLISHED_GRANITE, Blocks.DIORITE, Blocks.POLISHED_DIORITE, Blocks.ANDESITE, Blocks.POLISHED_ANDESITE,
            Blocks.STONE_SLAB, Blocks.SANDSTONE_SLAB, Blocks.PETRIFIED_OAK_SLAB, Blocks.COBBLESTONE_SLAB,
            Blocks.BRICK_SLAB, Blocks.STONE_BRICK_SLAB, Blocks.NETHER_BRICK_SLAB, Blocks.QUARTZ_SLAB,
            Blocks.RED_SANDSTONE_SLAB, Blocks.PURPUR_SLAB, Blocks.SMOOTH_QUARTZ, Blocks.SMOOTH_RED_SANDSTONE,
            Blocks.SMOOTH_SANDSTONE, Blocks.SMOOTH_STONE, Blocks.STONE_BUTTON, Blocks.STONE_PRESSURE_PLATE,
            Blocks.OAK_PLANKS, Blocks.SPRUCE_PLANKS, Blocks.BIRCH_PLANKS, Blocks.JUNGLE_PLANKS, Blocks.ACACIA_PLANKS,
            Blocks.DARK_OAK_PLANKS, Blocks.BOOKSHELF, Blocks.OAK_WOOD, Blocks.SPRUCE_WOOD, Blocks.BIRCH_WOOD,
            Blocks.JUNGLE_WOOD, Blocks.ACACIA_WOOD, Blocks.DARK_OAK_WOOD, Blocks.OAK_LOG, Blocks.SPRUCE_LOG,
            Blocks.BIRCH_LOG, Blocks.JUNGLE_LOG, Blocks.ACACIA_LOG, Blocks.DARK_OAK_LOG, Blocks.CHEST, Blocks.PUMPKIN,
            Blocks.CARVED_PUMPKIN, Blocks.JACK_O_LANTERN, Blocks.MELON, Blocks.LADDER, Blocks.OAK_BUTTON,
            Blocks.SPRUCE_BUTTON, Blocks.BIRCH_BUTTON, Blocks.JUNGLE_BUTTON, Blocks.DARK_OAK_BUTTON,
            Blocks.ACACIA_BUTTON, Blocks.OAK_PRESSURE_PLATE, Blocks.SPRUCE_PRESSURE_PLATE, Blocks.BIRCH_PRESSURE_PLATE,
            Blocks.JUNGLE_PRESSURE_PLATE, Blocks.DARK_OAK_PRESSURE_PLATE, Blocks.ACACIA_PRESSURE_PLATE, Blocks.CLAY,
            Blocks.DIRT, Blocks.COARSE_DIRT, Blocks.PODZOL, Blocks.FARMLAND, Blocks.GRASS_BLOCK, Blocks.GRAVEL,
            Blocks.MYCELIUM, Blocks.SAND, Blocks.RED_SAND, Blocks.SNOW_BLOCK, Blocks.SNOW, Blocks.SOUL_SAND,
            Blocks.GRASS_PATH, Blocks.WHITE_CONCRETE_POWDER, Blocks.ORANGE_CONCRETE_POWDER,
            Blocks.MAGENTA_CONCRETE_POWDER, Blocks.LIGHT_BLUE_CONCRETE_POWDER, Blocks.YELLOW_CONCRETE_POWDER,
            Blocks.LIME_CONCRETE_POWDER, Blocks.PINK_CONCRETE_POWDER, Blocks.GRAY_CONCRETE_POWDER,
            Blocks.LIGHT_GRAY_CONCRETE_POWDER, Blocks.CYAN_CONCRETE_POWDER, Blocks.PURPLE_CONCRETE_POWDER,
            Blocks.BLUE_CONCRETE_POWDER, Blocks.BROWN_CONCRETE_POWDER, Blocks.GREEN_CONCRETE_POWDER,
            Blocks.RED_CONCRETE_POWDER, Blocks.BLACK_CONCRETE_POWDER, Blocks.BAMBOO, Blocks.CACTUS, Blocks.MELON, Blocks.PUMPKIN,
            Blocks.COARSE_DIRT, Blocks.DIRT, Blocks.GRASS_BLOCK, Blocks.GRASS_PATH,
            ModBlocks.blue_bricks, ModBlocks.cyan_bricks, ModBlocks.green_bricks, ModBlocks.orange_bricks, ModBlocks.purple_bricks, ModBlocks.rainbow_bricks,
            ModBlocks.yellow_bricks, ModBlocks.blue_glowstone, ModBlocks.gray_glowstone, ModBlocks.green_glowstone, ModBlocks.orange_glowstone, ModBlocks.pink_glowstone,
            ModBlocks.purple_glowstone, ModBlocks.red_glowstone, ModBlocks.amethyst_block, ModBlocks.ruby_block, ModBlocks.topaz_block, ModBlocks.reinforced_obsidian,
            ModBlocks.amethyst_ore, ModBlocks.ruby_ore, ModBlocks.topaz_ore, ModBlocks.nether_coal_ore, ModBlocks.nether_diamond_ore, ModBlocks.nether_emerald_ore,
            ModBlocks.nether_gold_ore, ModBlocks.nether_iron_ore, ModBlocks.nether_iron_ore, ModBlocks.nether_lapis_ore, ModBlocks.nether_redstone_ore);
    public static final Map<Block, Block> BLOCK_STRIPPING_MAP = (new ImmutableMap.Builder<Block, Block>()).put(Blocks.OAK_WOOD,
            Blocks.STRIPPED_OAK_WOOD).put(Blocks.OAK_LOG,
            Blocks.STRIPPED_OAK_LOG).put(Blocks.DARK_OAK_WOOD,
            Blocks.STRIPPED_DARK_OAK_WOOD).put(Blocks.DARK_OAK_LOG,
            Blocks.STRIPPED_DARK_OAK_LOG).put(Blocks.ACACIA_WOOD,
            Blocks.STRIPPED_ACACIA_WOOD).put(Blocks.ACACIA_LOG,
            Blocks.STRIPPED_ACACIA_LOG).put(Blocks.BIRCH_WOOD,
            Blocks.STRIPPED_BIRCH_WOOD).put(Blocks.BIRCH_LOG,
            Blocks.STRIPPED_BIRCH_LOG).put(Blocks.JUNGLE_WOOD,
            Blocks.STRIPPED_JUNGLE_WOOD).put(Blocks.JUNGLE_LOG,
            Blocks.STRIPPED_JUNGLE_LOG).put(Blocks.SPRUCE_WOOD,
            Blocks.STRIPPED_SPRUCE_WOOD).put(Blocks.SPRUCE_LOG,
            Blocks.STRIPPED_SPRUCE_LOG).build();
    private static final Map<Block, BlockState> field_195955_e = Maps.newHashMap(ImmutableMap.of(Blocks.GRASS_BLOCK, Blocks.GRASS_PATH.getDefaultState()));
    private static final Map<Block, BlockState> field_195973_b = Maps.newHashMap(ImmutableMap.of(Blocks.GRASS_BLOCK, Blocks.FARMLAND.getDefaultState(),
            Blocks.GRASS_PATH, Blocks.FARMLAND.getDefaultState(), Blocks.DIRT, Blocks.FARMLAND.getDefaultState(), Blocks.COARSE_DIRT, Blocks.DIRT.getDefaultState()));

    public RubyPaxel(IItemTier tier, int attackDamageIn, float attackSpeedIn, Set<Block> EFFECTIVE_ON, Properties builder, String name) {
        super(attackDamageIn, attackSpeedIn, tier, EFFECTIVE_ON, builder);
        this.setRegistryName(Reference.MODID, name);
    }

    public boolean canHarvestBlock(BlockState blockIn) {
        int i = this.getTier().getHarvestLevel();
        return i >= blockIn.getHarvestLevel();
    }

    public boolean hitEntity(ItemStack stack, LivingEntity target, LivingEntity attacker) {
        stack.damageItem(1, target, (p_220045_0_) -> {
            p_220045_0_.sendBreakAnimation(EquipmentSlotType.MAINHAND);
        });
        return true;
    }

    public float getDestroySpeed(ItemStack stack, BlockState state) {
        Material material = state.getMaterial();
        return material != Material.IRON && material != Material.ANVIL && material != Material.ROCK
                && material != Material.WOOD && material != Material.PLANTS ? super.getDestroySpeed(stack, state)
                : this.efficiency;
    }

    public ActionResultType onItemUse(ItemUseContext useContext) {

        World world = useContext.getWorld();
        BlockPos blockpos = useContext.getPos();
        BlockState blockstate = world.getBlockState(blockpos);
        BlockState resultToSet = null;
        Block block = BLOCK_STRIPPING_MAP.get(blockstate.getBlock());
        if (block != null) {
            PlayerEntity playerentity = useContext.getPlayer();
            world.playSound(playerentity, blockpos, SoundEvents.ITEM_AXE_STRIP, SoundCategory.BLOCKS, 1.0F, 1.0F);
            if (!world.isRemote) {
                world.setBlockState(blockpos, block.getDefaultState().with(RotatedPillarBlock.AXIS, blockstate.get(RotatedPillarBlock.AXIS)), 11);
                if (playerentity != null) {
                    useContext.getItem().damageItem(1, playerentity, (p_220040_1_) -> {
                        p_220040_1_.sendBreakAnimation(useContext.getHand());
                    });
                }
            }

            return ActionResultType.SUCCESS;
        }

        World world1 = useContext.getWorld();
        BlockPos blockpos1 = useContext.getPos();
        BlockState blockstate3 = world.getBlockState(blockpos);
        if (useContext.getFace() == Direction.DOWN) {
            return ActionResultType.PASS;
        } else {
            PlayerEntity playerentity = useContext.getPlayer();
            BlockState blockstate1 = field_195955_e.get(blockstate.getBlock());
            BlockState blockstate2 = null;
            if (blockstate1 != null && world.isAirBlock(blockpos.up())) {
                world.playSound(playerentity, blockpos, SoundEvents.ITEM_SHOVEL_FLATTEN, SoundCategory.BLOCKS, 1.0F, 1.0F);
                blockstate2 = blockstate1;
            } else if (blockstate.getBlock() instanceof CampfireBlock && blockstate.get(CampfireBlock.LIT)) {
                world.playEvent((PlayerEntity)null, 1009, blockpos, 0);
                blockstate2 = blockstate.with(CampfireBlock.LIT, Boolean.valueOf(false));
            }

            if (blockstate2 != null) {
                if (!world.isRemote) {
                    world.setBlockState(blockpos, blockstate2, 11);
                    if (playerentity != null) {
                        useContext.getItem().damageItem(0, playerentity, (p_220041_1_) -> {
                            p_220041_1_.sendBreakAnimation(useContext.getHand());
                        });
                    }
                }

            }
        }
        if(useContext.getPlayer().isCrouching()) {

            World world2 = useContext.getWorld();
            BlockPos blockpos2 = useContext.getPos();
            int hook = net.minecraftforge.event.ForgeEventFactory.onHoeUse(useContext);
            if (hook != 0) return hook > 0 ? ActionResultType.SUCCESS : ActionResultType.FAIL;
            if (useContext.getFace() != Direction.DOWN && world.isAirBlock(blockpos.up())) {
                BlockState iblockstate3 = field_195973_b.get(world.getBlockState(blockpos).getBlock());
                if (iblockstate3 != null) {
                    PlayerEntity entityplayer2 = useContext.getPlayer();
                    world2.playSound(entityplayer2, blockpos2, SoundEvents.ITEM_HOE_TILL, SoundCategory.BLOCKS, 1.0F, 1.0F);
                    if (!world.isRemote) {
                        world2.setBlockState(blockpos2, iblockstate3, 11);
                        if (entityplayer2 != null) {
                            useContext.getItem().damageItem(1, entityplayer2, (p_220040_1_) -> {
                                p_220040_1_.sendBreakAnimation(useContext.getHand());
                            });
                        }
                    }
                }
                return ActionResultType.SUCCESS;
            }
        }


        return ActionResultType.PASS;
    }

    @Override
    public boolean isBookEnchantable(ItemStack stack, ItemStack book) {
        if (EnchantmentHelper.getEnchantments(book).containsKey(Enchantments.SHARPNESS)) {
            return true;
        }
        return super.isBookEnchantable(stack, book);
    }
    @Override
    public boolean canApplyAtEnchantingTable(ItemStack stack, Enchantment enchantment) {
        if (enchantment == Enchantments.SHARPNESS) {
            return true;
        }
        return super.canApplyAtEnchantingTable(stack, enchantment);
    }
    @Override
    public void addInformation(ItemStack stack, @Nullable World worldIn, List<ITextComponent> tooltip, ITooltipFlag flagIn) {
        tooltip.add(new StringTextComponent(TextFormatting.YELLOW + "Right click Grass to make a path, shift right click Grass to till it, and right click Logs to strip them"));
    }
    @Override
    public boolean getIsRepairable(ItemStack toRepair, ItemStack repair) {
        return repair.getItem() == ModItems.ruby;
    }
}
