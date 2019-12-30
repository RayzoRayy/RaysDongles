package com.rbs.slurpiesdongles.handlers;

import com.rbs.slurpiesdongles.Reference;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.OreBlock;
import net.minecraft.client.renderer.ActiveRenderInfo;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.network.play.server.SChangeBlockPacket;
import net.minecraft.util.Direction;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.BlockRayTraceResult;
import net.minecraft.util.math.RayTraceResult;
import net.minecraft.world.World;
import net.minecraftforge.common.ForgeHooks;
import net.minecraftforge.common.Tags;
import net.minecraftforge.common.ToolType;
import net.minecraftforge.registries.ForgeRegistries;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/*
Source code belongs to SilentChaos512, his code can be found here: https://github.com/SilentChaos512/Silent-Gear
A few of the changes made to this class were done by TwiistsGaming, his page can be found here: https://www.curseforge.com/members/twiistsgaming/projects
*/

public interface SDTool {

    /**
     * The tool class of the item (pickaxe, shovel, axe)
     *
     * @return The tool type
     */
    @Nonnull
    ToolType getSDToolClass();

    /**
     * Call the item's rayTrace method inside this.
     *
     * @param world  The world
     * @param player The player
     * @return The ray trace result
     */
    @Nullable
    RayTraceResult rayTraceBlocks(World world, PlayerEntity player);

    default List<BlockPos> getExtraBlocks(World world, @Nullable BlockRayTraceResult rt, PlayerEntity player, ItemStack stack) {
        List<BlockPos> positions = new ArrayList<>();

        if (player.isCrouching() || rt == null || rt.getPos() == null || rt.getFace() == null)
            return positions;

        BlockPos pos = rt.getPos();
        BlockState state = world.getBlockState(pos);

        if (isEffectiveOnBlock(stack, world, pos, state)) {
            switch (rt.getFace().getAxis()) {
                case Y:
                    attemptAddExtraBlock(world, state, pos.offset(Direction.NORTH), stack, positions);
                    attemptAddExtraBlock(world, state, pos.offset(Direction.EAST), stack, positions);
                    attemptAddExtraBlock(world, state, pos.offset(Direction.SOUTH), stack, positions);
                    attemptAddExtraBlock(world, state, pos.offset(Direction.WEST), stack, positions);
                    attemptAddExtraBlock(world, state, pos.offset(Direction.NORTH).offset(Direction.EAST), stack, positions);
                    attemptAddExtraBlock(world, state, pos.offset(Direction.EAST).offset(Direction.SOUTH), stack, positions);
                    attemptAddExtraBlock(world, state, pos.offset(Direction.SOUTH).offset(Direction.WEST), stack, positions);
                    attemptAddExtraBlock(world, state, pos.offset(Direction.WEST).offset(Direction.NORTH), stack, positions);
                    break;
                case X:
                    attemptAddExtraBlock(world, state, pos.offset(Direction.NORTH), stack, positions);
                    attemptAddExtraBlock(world, state, pos.offset(Direction.UP), stack, positions);
                    attemptAddExtraBlock(world, state, pos.offset(Direction.SOUTH), stack, positions);
                    attemptAddExtraBlock(world, state, pos.offset(Direction.DOWN), stack, positions);
                    attemptAddExtraBlock(world, state, pos.offset(Direction.NORTH).offset(Direction.UP), stack, positions);
                    attemptAddExtraBlock(world, state, pos.offset(Direction.UP).offset(Direction.SOUTH), stack, positions);
                    attemptAddExtraBlock(world, state, pos.offset(Direction.SOUTH).offset(Direction.DOWN), stack, positions);
                    attemptAddExtraBlock(world, state, pos.offset(Direction.DOWN).offset(Direction.NORTH), stack, positions);
                    break;
                case Z:
                    attemptAddExtraBlock(world, state, pos.offset(Direction.DOWN), stack, positions);
                    attemptAddExtraBlock(world, state, pos.offset(Direction.EAST), stack, positions);
                    attemptAddExtraBlock(world, state, pos.offset(Direction.UP), stack, positions);
                    attemptAddExtraBlock(world, state, pos.offset(Direction.WEST), stack, positions);
                    attemptAddExtraBlock(world, state, pos.offset(Direction.DOWN).offset(Direction.EAST), stack, positions);
                    attemptAddExtraBlock(world, state, pos.offset(Direction.EAST).offset(Direction.UP), stack, positions);
                    attemptAddExtraBlock(world, state, pos.offset(Direction.UP).offset(Direction.WEST), stack, positions);
                    attemptAddExtraBlock(world, state, pos.offset(Direction.WEST).offset(Direction.DOWN), stack, positions);
                    break;
            }
        }
//        SilentGear.log.debug("{}", positions);
        return positions;
    }

    default boolean isEffectiveOnBlock(ItemStack stack, World world, BlockPos pos, BlockState state) {
        // The Forge.canToolHarvestBlock method seems to be very unreliable...
        return stack.getItem().canHarvestBlock(stack, state) || ForgeHooks.canToolHarvestBlock(world, pos, stack);
    }

    default void attemptAddExtraBlock(World world, BlockState state1, BlockPos pos2, ItemStack stack, List<BlockPos> list) {
        final BlockState state2 = world.getBlockState(pos2);
        // Prevent breaking of unbreakable blocks, like bedrock
        if (state2.getBlockHardness(world, pos2) < 0) return;

        if (!world.isAirBlock(pos2)
                && BreakHandler.areBlocksSimilar(state1, state2)
                && (state2.getBlock().isToolEffective(state2, getSDToolClass()) || stack.getItem().canHarvestBlock(stack, state2))) {
            list.add(pos2);
        }
    }

    enum MatchMode {
        LOOSE, MODERATE, STRICT
    }

    /**
     * Handles actual AOE block breaking. Call {@link #onBlockStartBreak(ItemStack, BlockPos,
     * PlayerEntity)} inside the {@code onBlockStartBreak} method of the tool's item.
     */
    final class BreakHandler {
        private BreakHandler() {}

        public static boolean onBlockStartBreak(ItemStack tool, BlockPos pos, PlayerEntity player) {
            World world = player.getEntityWorld();
            if (world.isRemote || !(player instanceof ServerPlayerEntity) || !(tool.getItem() instanceof SDTool))
                return false;

            SDTool item = (SDTool) tool.getItem();
            RayTraceResult rt = item.rayTraceBlocks(world, player);
            BlockState stateOriginal = world.getBlockState(pos);

            if (rt != null && rt.getType() == RayTraceResult.Type.BLOCK && item.isEffectiveOnBlock(tool, world, pos, stateOriginal)) {
                BlockRayTraceResult brt = (BlockRayTraceResult) rt;
                Direction side = brt.getFace();
                List<BlockPos> extraBlocks = item.getExtraBlocks(world, brt, player, tool);

                for (BlockPos pos2 : extraBlocks) {
                    BlockState state = world.getBlockState(pos2);
                    if (!world.isBlockLoaded(pos2) || !player.canPlayerEdit(pos2, side, tool) || !(state.canHarvestBlock(world, pos2, player)))
                        continue;

                    if (player.abilities.isCreativeMode) {
                        state.getBlock().onBlockHarvested(world, pos2, state, player);
                        if (state.getBlock().removedByPlayer(state, world, pos2, player, false, state.getFluidState()))
                            state.getBlock().onPlayerDestroy(world, pos2, state);
                    } else {
                        int xp = ForgeHooks.onBlockBreakEvent(world, ((ServerPlayerEntity) player).interactionManager.getGameType(), (ServerPlayerEntity) player, pos2);
                        //state.getBlock().onBlockHarvested(world, pos2, state, player);
                        tool.getItem().onBlockDestroyed(tool, world, state, pos2, player);
                        if (state.getBlock().removedByPlayer(state, world, pos2, player, true, state.getFluidState())) {
                            state.getBlock().onPlayerDestroy(world, pos2, state);
                            state.getBlock().harvestBlock(world, player, pos2, state, world.getTileEntity(pos2), tool);
                            state.getBlock().dropXpOnBlockBreak(world, pos2, xp);
                        }
                    }

                    world.playEvent(2001, pos, Block.getStateId(state));
                    ((ServerPlayerEntity) player).connection.sendPacket(new SChangeBlockPacket(world, pos));
                }
            }
            return false;
        }

        private static final Set<Block> ORE_BLOCKS = new HashSet<>();

        public static void buildOreBlocksSet() {
            ORE_BLOCKS.clear();

            for (Block block : ForgeRegistries.BLOCKS) {
                if (block instanceof OreBlock || Tags.Blocks.ORES.contains(block)) {
                    ORE_BLOCKS.add(block);
                }
            }
        }

        /**
         * Determine if the blocks are similar enough to be considered the same. This depends on the
         * match mode configs. STRICT will only match the same block (ignoring exact state),
         * MODERATE will break anything with the same or lower harvest level (except level -1 and 0
         * blocks will still break together regardless of which is targeted), and LOOSE will match
         * anything.
         *
         * @return True if the blocks are the same (equal) or similar, false otherwise
         */
        static boolean areBlocksSimilar(BlockState state1, BlockState state2) {
            Block block1 = state1.getBlock();
            Block block2 = state2.getBlock();
            boolean isOre1 = ORE_BLOCKS.contains(block1);
            boolean isOre2 = ORE_BLOCKS.contains(block2);

            // Otherwise, anything with same or lower harvest level should be okay
            int level1 = block1.getHarvestLevel(state1);
            int level2 = block2.getHarvestLevel(state2);
            return level1 >= level2 || level2 == 0;
        }
    }

    /*@Mod.EventBusSubscriber(modid = Reference.MODID, value = Dist.CLIENT)
    final class HighlightHandler {
        private HighlightHandler() {}

        @SubscribeEvent
        public static void onDrawBlockHighlight(DrawBlockHighlightEvent event) {
            ActiveRenderInfo info = event.getInfo();
            Entity entity = info.getRenderViewEntity();
            if (!(entity instanceof PlayerEntity)) return;

            PlayerEntity player = (PlayerEntity) entity;

            RayTraceResult rt = event.getTarget();

            if (event.getSubID() == 0 && rt.getType() == RayTraceResult.Type.BLOCK) {
                ItemStack stack = player.getHeldItemMainhand();

                if (stack.getItem() instanceof SDTool) {
                    World world = player.getEntityWorld();
                    SDTool item = (SDTool) stack.getItem();

                    for (BlockPos pos : item.getExtraBlocks(world, (BlockRayTraceResult) rt, player, stack)) {
                        event.getContext().drawSelectionBox(info, new BlockRayTraceResult(Vec3d.ZERO, Direction.UP, pos, false), 0);
                    }
                }
            }
        }
    }*/
}

