package com.rbs.slurpiesdongles.items.tools;

import com.google.common.collect.ImmutableSet;
import com.google.common.collect.Sets;
import com.rbs.slurpiesdongles.Reference;
import com.rbs.slurpiesdongles.helpers.ToolHelper;
import com.rbs.slurpiesdongles.helpers.HarvestLevelHelper;
import com.rbs.slurpiesdongles.init.ModTools;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.block.material.Material;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.AxeItem;
import net.minecraft.item.IItemTier;
import net.minecraft.item.ItemStack;
import net.minecraft.tags.BlockTags;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.StringTextComponent;
import net.minecraft.util.text.TextFormatting;
import net.minecraft.world.World;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.TickEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;

import javax.annotation.Nullable;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

/*
 Copyright (c) 2019 Alexander Strada - MIT License (This header, with links, must not be removed)
     https://github.com/astradamus/PracticalTools
     https://curseforge.com/minecraft/mc-mods/practical-tools
     https://twitch.tv/neurodr0me
 */

public class LumbarAxe extends AxeItem {

    private static final Set<Block> EFFECTIVE_ON = Sets.newHashSet(Blocks.OAK_PLANKS, Blocks.SPRUCE_PLANKS, Blocks.BIRCH_PLANKS, Blocks.JUNGLE_PLANKS, Blocks.ACACIA_PLANKS, Blocks.DARK_OAK_PLANKS, Blocks.BOOKSHELF, Blocks.OAK_WOOD, Blocks.SPRUCE_WOOD, Blocks.BIRCH_WOOD, Blocks.JUNGLE_WOOD, Blocks.ACACIA_WOOD, Blocks.DARK_OAK_WOOD, Blocks.OAK_LOG, Blocks.SPRUCE_LOG, Blocks.BIRCH_LOG, Blocks.JUNGLE_LOG, Blocks.ACACIA_LOG, Blocks.DARK_OAK_LOG, Blocks.CHEST, Blocks.PUMPKIN, Blocks.CARVED_PUMPKIN, Blocks.JACK_O_LANTERN, Blocks.MELON, Blocks.LADDER, Blocks.SCAFFOLDING, Blocks.OAK_BUTTON, Blocks.SPRUCE_BUTTON, Blocks.BIRCH_BUTTON, Blocks.JUNGLE_BUTTON, Blocks.DARK_OAK_BUTTON, Blocks.ACACIA_BUTTON, Blocks.OAK_PRESSURE_PLATE, Blocks.SPRUCE_PRESSURE_PLATE, Blocks.BIRCH_PRESSURE_PLATE, Blocks.JUNGLE_PRESSURE_PLATE, Blocks.DARK_OAK_PRESSURE_PLATE, Blocks.ACACIA_PRESSURE_PLATE);
    public static final Set<Material> EFFECTIVE_MATERIALS = ImmutableSet.of(Material.WOOD, Material.GOURD, Material.CACTUS);
    public static final int LOG_BREAK_DELAY = 1;


    public LumbarAxe(IItemTier tier, float attackDamageIn, float attackSpeedIn, Properties builder, String name) {
        super(tier, attackDamageIn, attackSpeedIn, builder);

        this.setRegistryName(Reference.MODID, name);
    }

    @Override
    public boolean onBlockDestroyed(ItemStack stack, World world, BlockState state, BlockPos pos, LivingEntity entityLiving) {
        stack.attemptDamageItem(6, ToolHelper.random, null);//Set to 6 because generally a tree is 6 tall ish

        if (entityLiving instanceof PlayerEntity) {
            PlayerEntity player = (PlayerEntity) entityLiving;

            if (!attemptFellTree(world, pos, player)) {
                ToolHelper.attemptBreakNeighbors(world, pos, player, EFFECTIVE_ON, EFFECTIVE_MATERIALS);
            }
        }

        return super.onBlockDestroyed(stack, world, state, pos, entityLiving);
    }

    private boolean attemptFellTree(World world, BlockPos pos, PlayerEntity player) {

        ArrayList<BlockPos> logs = new ArrayList<>();
        ArrayList<BlockPos> candidates = new ArrayList<>();
        candidates.add(pos);

        int leaves = 0;

        for (int i = 0; i < candidates.size(); i++) {
            if (logs.size() > 200) return false;

            BlockPos candidate = candidates.get(i);
            Block block = world.getBlockState(candidate).getBlock();

            if (BlockTags.LEAVES.contains(block)) {
                leaves++;
            } else if (BlockTags.LOGS.contains(block)) {
                logs.add(candidate);


                for (int x = -1; x <= 1; x++) {
                    for (int y = 0; y <= 1; y++) {
                        for (int z = -1; z <= 1; z++) {
                            BlockPos neighbor = candidate.add(x, y, z);
                            if (candidates.contains(neighbor)) continue;
                            candidates.add(neighbor);
                        }
                    }
                }
            }
        }

        if (logs.size() == 0) return false;

        if (leaves >= logs.size() / 6.0) {
            MinecraftForge.EVENT_BUS.register(new Object() {

                int delay = LOG_BREAK_DELAY;
                int i = 0;

                @SubscribeEvent
                public void onTick(TickEvent.WorldTickEvent event) {
                    if (delay-- > 0) return;
                    delay = LOG_BREAK_DELAY;
                    if (i < logs.size()) {
                        BlockPos log = logs.get(i);
                        ToolHelper.attemptBreak(world, log, player, EFFECTIVE_ON, EFFECTIVE_MATERIALS);
                        i++;
                    } else {
                        MinecraftForge.EVENT_BUS.unregister(this);
                    }
                }
            });

            return true;
        }

        return false;
    }

    @Override
    public void addInformation(ItemStack stack, @Nullable World worldIn, List<ITextComponent> tooltip, ITooltipFlag flagIn) {
        tooltip.add(new StringTextComponent(TextFormatting.YELLOW + "Cuts an entire tree down upon breaking the bottom log"));
    }
}
